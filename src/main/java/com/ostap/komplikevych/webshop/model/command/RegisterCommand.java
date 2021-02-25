package com.ostap.komplikevych.webshop.model.command;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.constant.SessionAttribute;
import com.ostap.komplikevych.webshop.constant.Validator;
import com.ostap.komplikevych.webshop.dao.AccountDao;
import com.ostap.komplikevych.webshop.dao.AccountDetailDao;
import com.ostap.komplikevych.webshop.dao.ShoppingCartDao;
import com.ostap.komplikevych.webshop.entity.*;
import com.ostap.komplikevych.webshop.model.security.MyChipher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

public class RegisterCommand implements Command {

    /**
     * Execution method for command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {

        Const.logger.debug("Command " + this.getClass().getName().toUpperCase());

        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String checkPassword = request.getParameter("check-password");

        String surnameUa = request.getParameter("surname-ua");
        String firstNameUa = request.getParameter("first-name-ua");
        String patronymicUa = request.getParameter("patronymic-ua");
        String surnameEn = request.getParameter("surname-en");
        String firstNameEn = request.getParameter("first-name-en");
        String patronymicEn = request.getParameter("patronymic-en");
        String errorMessage = null;
        String forward = Const.PAGE_REGISTRATION;

        if (Validator.checkIfNullOrEmptyReturnTrue(email, password, checkPassword,
                surnameEn, surnameUa, firstNameEn, firstNameUa, patronymicEn, patronymicUa)) {
            errorMessage = "Required fields are empty.";
            request.setAttribute("errorMessage", errorMessage);
            Const.logger.error("errorMessage --> " + errorMessage);
            return forward;
        }

        AccountDetailDao dao = new AccountDetailDao();
        AccountDao accountDao = new AccountDao();
        ShoppingCartDao shoppingCartDao = new ShoppingCartDao();
        Account account;
        AccountDetail accountDetail;

        if (!password.equals(checkPassword)) {
            errorMessage = "Passwords are different.";
            request.setAttribute("errorMessage", errorMessage);
            Const.logger.error("errorMessage --> " + errorMessage);
            return forward;
        }
        if (!password.matches(Validator.PASSWORD)) {
            errorMessage = "Password validation error (must be [0-9][a-z][A-Z] and at least 8 symbols).";
            request.setAttribute("errorMessage", errorMessage);
            Const.logger.error("errorMessage --> " + errorMessage);
            return forward;
        }
        if (!email.matches(Validator.EMAIL)) {
            errorMessage = "E-mail is invalid.";
            request.setAttribute("errorMessage", errorMessage);
            Const.logger.error("errorMessage --> " + errorMessage);
            return forward;
        }
        account = accountDao.readAccountByEmail(email);
        if (account != null) {
            errorMessage = "You already have an account, please login.";
            request.setAttribute("errorMessage", errorMessage);
            Const.logger.error("errorMessage --> You already have an account (" + account + "), please login.");
            return forward;
        }
        if (!Validator.checkIfMatchValidator(Validator.NAME_SURNAME_PATRONYMIC_EN, patronymicEn, surnameEn, firstNameEn)) {
            errorMessage = "First Name,Surname or Patronymic not match EN validator.";
            request.setAttribute("errorMessage", errorMessage);
            Const.logger.error("errorMessage --> " + errorMessage);
            return forward;
        }
        if (Validator.checkIfMatchValidator(Validator.NAME_SURNAME_PATRONYMIC_UA, patronymicUa, surnameUa, firstNameUa)) {
            errorMessage = "First Name,Surname or Patronymic not match UA validator.";
            request.setAttribute("errorMessage", errorMessage);
            Const.logger.error("errorMessage --> " + errorMessage);
            return forward;
        }

        account = new Account();

        MyChipher myChipher = new MyChipher();
        String encryptedPassword = myChipher.encrypt(password);
        account.setPassword(encryptedPassword);
        Const.logger.trace("password " + account.getPassword());

        account.setEmail(email);
        Const.logger.trace("email " + email + " --> " + account.getEmail());

        account.setRoleId(Role.USER.getId());
        Const.logger.trace("role " + Role.USER.getId() + " --> " + account.getRoleId());

        account.setAccountStatusId(AccountStatus.ENABLED.getId());
        Const.logger.trace("status id " + AccountStatus.ENABLED.getId() + " --> " + account.getAccountStatusId());

        Const.logger.info("account --> " + account);

        accountDetail = new AccountDetail();

        accountDetail.setSurnameUa(surnameUa);
        Const.logger.trace("surname ua " + surnameUa + " --> " + accountDetail.getSurnameUa());
        accountDetail.setFirstNameUa(firstNameUa);
        Const.logger.trace("first name ua " + firstNameUa + " --> " + accountDetail.getFirstNameUa());
        accountDetail.setPatronymicUa(patronymicUa);
        Const.logger.trace("patronymic ua " + patronymicUa + " --> " + accountDetail.getPatronymicUa());

        accountDetail.setSurnameEn(surnameEn);
        Const.logger.trace("surname en " + surnameEn + " --> " + accountDetail.getSurnameEn());
        accountDetail.setFirstNameEn(firstNameEn);
        Const.logger.trace("first name en " + firstNameEn + " --> " + accountDetail.getFirstNameEn());
        accountDetail.setPatronymicEn(patronymicEn);
        Const.logger.trace("patronymic en " + patronymicEn + " --> " + accountDetail.getPatronymicEn());

        Const.logger.info("account detail --> " + accountDetail);

        ShoppingCart cart = new ShoppingCart();
        cart.setLastUpdate(LocalDateTime.now());

        Const.logger.info("shopping cart --> " + cart);

        int shoppingCartId = shoppingCartDao.createShoppingCart(cart);
        Const.logger.info("shopping cart created id --> " + shoppingCartId);

        account.setShoppingCartId(shoppingCartId);
        Const.logger.info("account detail shopping cart id --> " + account.getShoppingCartId());

        int accountId = accountDao.createAccount(account);
        Const.logger.info("account created id --> " + accountId);


        accountDetail.setAccountId(accountId);
        int accDetailId = dao.createAccountDetail(accountDetail, null);
        Const.logger.info("account detail created id --> " + accDetailId);

        Const.logger.info("Created Account --> " + account +
                "\nwith account details --> " + accountDetail +
                "\nwith shopping cart --> " + cart);

        session.setAttribute(SessionAttribute.ACCOUNT, account);
        session.setAttribute(SessionAttribute.ROLE, Role.USER);

        return Const.PAGE_LOGIN;
    }
}
