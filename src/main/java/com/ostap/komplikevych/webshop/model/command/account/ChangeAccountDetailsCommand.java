package com.ostap.komplikevych.webshop.model.command.account;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.constant.SessionAttribute;
import com.ostap.komplikevych.webshop.constant.Validator;
import com.ostap.komplikevych.webshop.dao.AccountDao;
import com.ostap.komplikevych.webshop.dao.AccountDetailDao;
import com.ostap.komplikevych.webshop.entity.Account;
import com.ostap.komplikevych.webshop.entity.AccountDetail;
import com.ostap.komplikevych.webshop.entity.DetailedAccount;
import com.ostap.komplikevych.webshop.model.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeAccountDetailsCommand implements Command {
    /**
     * Execution method for command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Const.logger.info("Start change account details command");
        AccountDetail newDetails = new AccountDetail();
        HttpSession session = request.getSession();

        newDetails.setSurnameUa(request.getParameter("surname-ua"));
        newDetails.setSurnameEn(request.getParameter("surname-en"));
        newDetails.setFirstNameUa(request.getParameter("first-name-ua"));
        newDetails.setFirstNameEn(request.getParameter("first-name-en"));
        newDetails.setPatronymicUa(request.getParameter("patronymic-ua"));
        newDetails.setPatronymicEn(request.getParameter("patronymic-en"));

        newDetails.setCountryUa(request.getParameter("country-ua"));
        newDetails.setCountryEn(request.getParameter("country-en"));
        newDetails.setCityUa(request.getParameter("city-ua"));
        newDetails.setCityEn(request.getParameter("city-en"));
        newDetails.setStreetUa(request.getParameter("street-ua"));
        newDetails.setStreetEn(request.getParameter("street-en"));
        newDetails.setBuildingUa(request.getParameter("building-ua"));
        newDetails.setBuildingEn(request.getParameter("building-en"));
        newDetails.setFlatUa(request.getParameter("flat-ua"));
        newDetails.setFlatEn(request.getParameter("flat-en"));

        newDetails.setPhone(request.getParameter("mobile"));
        String zip = request.getParameter("zip-code");
        String email = request.getParameter("email");
        if (zip != null && zip.matches("[0-9]+")) {
            newDetails.setZipCode(Integer.parseInt(zip));
        }


        Const.logger.trace("newDetails = " + newDetails);

        String errorMessage;

        if (Validator.checkIfNullOrEmptyReturnTrue(newDetails.getSurnameEn(), newDetails.getSurnameUa(),
                newDetails.getFirstNameEn(), newDetails.getFirstNameUa(), newDetails.getPatronymicEn(),
                newDetails.getPatronymicUa(), email)) {
            errorMessage = "Operation rejected. Main account information cannot be deleted.";
            Const.logger.trace("errorMessage = " + errorMessage);
            request.setAttribute("errorMessage", errorMessage);
            return Const.PAGE_PROFILE;
        }

        AccountDetailDao detailDao = new AccountDetailDao();
        Account account = (Account) session.getAttribute(SessionAttribute.ACCOUNT);
        AccountDetail accountDetail;
        if (account != null) {
            accountDetail = detailDao.readAccountDetailByAccountId(account.getId());
            updateAccountDetailByNewData(accountDetail, newDetails);
            updateAccountByNewEmail(account,email);
            detailDao.updateAccountDetail(accountDetail);

            DetailedAccount detailedAccount = new DetailedAccount(account.getId());
            Const.logger.trace(detailedAccount);
            session.setAttribute(SessionAttribute.DETAILED_ACCOUNT, detailedAccount);
            request.setAttribute(SessionAttribute.DETAILED_ACCOUNT, detailedAccount);
            Const.logger.info("Update finished redirect to page Profile ");
        }



        return Const.REDIRECT + "controller?command=open-profile-page";
    }

    private void updateAccountDetailByNewData(AccountDetail oldDetails, AccountDetail newDetails) {

        Const.logger.debug("Was details = " + oldDetails);

        String surnameEn = newDetails.getSurnameEn();
        Const.logger.trace("surnameEn = " + surnameEn);
        if (!Validator.checkIfNullOrEmptyReturnTrue(surnameEn)) {
            oldDetails.setSurnameEn(surnameEn);
            Const.logger.trace("SurnameEn updated");
        }

        String surnameUa = newDetails.getSurnameUa();
        Const.logger.trace("surnameUa = " + surnameUa);
        if (!Validator.checkIfNullOrEmptyReturnTrue(surnameUa)) {
            oldDetails.setSurnameUa(surnameUa);
            Const.logger.trace("SurnameUa updated");
        }

        String firstNameEn = newDetails.getFirstNameEn();
        Const.logger.trace("firstNameEn = " + firstNameEn);
        if (!Validator.checkIfNullOrEmptyReturnTrue(firstNameEn)) {
            oldDetails.setFirstNameEn(firstNameEn);
            Const.logger.trace("FirstnameEn updated");
        }

        String firstNameUa = newDetails.getFirstNameUa();
        Const.logger.trace("firstNameUa = " + firstNameUa);
        if (!Validator.checkIfNullOrEmptyReturnTrue(firstNameUa)) {
            oldDetails.setFirstNameUa(firstNameUa);
            Const.logger.trace("FirstnameUa updated");
        }

        String patronymicUa = newDetails.getPatronymicUa();
        Const.logger.trace("patronymicUa = " + patronymicUa);
        if (!Validator.checkIfNullOrEmptyReturnTrue(patronymicUa)) {
            oldDetails.setPatronymicUa(patronymicUa);
            Const.logger.trace("PatronymicUa updated");
        }

        String patronymicEn = newDetails.getPatronymicEn();
        Const.logger.trace("patronymicEn = " + patronymicEn);
        if (!Validator.checkIfNullOrEmptyReturnTrue(patronymicEn)) {
            oldDetails.setPatronymicEn(patronymicEn);
            Const.logger.trace("PatronymicEn updated");
        }

        String countryUa = newDetails.getCountryUa();
        Const.logger.trace("countryUa = " + countryUa);
        if (!Validator.checkIfNullOrEmptyReturnTrue(countryUa)) {
            oldDetails.setCountryUa(countryUa);
            Const.logger.trace("CountryUa updated");
        }

        String countryEn = newDetails.getCountryEn();
        Const.logger.trace("countryEn = " + countryEn);
        if (!Validator.checkIfNullOrEmptyReturnTrue(countryEn)) {
            oldDetails.setCountryEn(countryEn);
            Const.logger.trace("PCountryEn updated");
        }

        String cityUa = newDetails.getCityUa();
        Const.logger.trace("cityUa = " + cityUa);
        if (!Validator.checkIfNullOrEmptyReturnTrue(cityUa)) {
            oldDetails.setCityUa(cityUa);
            Const.logger.trace("CityUa updated");
        }

        String cityEn = newDetails.getCityEn();
        Const.logger.trace("cityEn = " + cityEn);
        if (!Validator.checkIfNullOrEmptyReturnTrue(cityEn)) {
            oldDetails.setCityEn(cityEn);
            Const.logger.trace("CityEn updated");
        }

        String streetUa = newDetails.getStreetUa();
        Const.logger.trace("streetUa = " + streetUa);
        if (!Validator.checkIfNullOrEmptyReturnTrue(streetUa)) {
            oldDetails.setStreetUa(streetUa);
            Const.logger.trace("StreetUa updated");
        }

        String streetEn = newDetails.getStreetEn();
        Const.logger.trace("streetEn = " + streetEn);
        if (!Validator.checkIfNullOrEmptyReturnTrue(streetEn)) {
            oldDetails.setStreetEn(streetEn);
            Const.logger.trace("StreetEn updated");
        }

        String buildingUa = newDetails.getBuildingUa();
        Const.logger.trace("buildingUa = " + buildingUa);
        if (!Validator.checkIfNullOrEmptyReturnTrue(buildingUa)) {
            oldDetails.setBuildingUa(buildingUa);
            Const.logger.trace("BuildingUa updated");
        }

        String buildingEn = newDetails.getBuildingEn();
        Const.logger.trace("buildingEn = " + buildingEn);
        if (!Validator.checkIfNullOrEmptyReturnTrue(buildingEn)) {
            oldDetails.setBuildingEn(buildingEn);
            Const.logger.trace("BuildingEn updated");
        }

        String flatUa = newDetails.getFlatUa();
        Const.logger.trace("flatUa = " + flatUa);
        if (!Validator.checkIfNullOrEmptyReturnTrue(flatUa)) {
            oldDetails.setFlatUa(flatUa);
            Const.logger.trace("FlatUa updated");

        }

        String flatEn = newDetails.getFlatEn();
        Const.logger.trace("flatEn = " + flatEn);
        if (!Validator.checkIfNullOrEmptyReturnTrue(flatEn)) {
            oldDetails.setFlatEn(flatEn);
            Const.logger.trace("FlatEn updated");
        }

        String phone = newDetails.getPhone();
        Const.logger.trace("phone = " + phone);
        if (!Validator.checkIfNullOrEmptyReturnTrue(phone)) {
            oldDetails.setPhone(phone);
            Const.logger.trace("Phone updated");
        }

        String zip = String.valueOf(newDetails.getZipCode());
        Const.logger.trace("zip = " + zip);
        if (!Validator.checkIfNullOrEmptyReturnTrue(zip)) {
            oldDetails.setZipCode(Integer.parseInt(zip));
            Const.logger.trace("ZipCode updated");
        }

        Const.logger.debug("Now details = " + oldDetails);
    }

    private void updateAccountByNewEmail(Account account,String email){
        AccountDao accountDao = new AccountDao();
        account.setEmail(email);
        accountDao.updateAccount(account);
    }
}
