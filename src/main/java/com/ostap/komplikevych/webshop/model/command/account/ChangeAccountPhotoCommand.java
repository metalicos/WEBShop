package com.ostap.komplikevych.webshop.model.command.account;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.constant.SessionAttribute;
import com.ostap.komplikevych.webshop.dao.AccountDetailDao;
import com.ostap.komplikevych.webshop.entity.Account;
import com.ostap.komplikevych.webshop.entity.AccountDetail;
import com.ostap.komplikevych.webshop.model.ImageConverter;
import com.ostap.komplikevych.webshop.model.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

public class ChangeAccountPhotoCommand implements Command {
    /**
     * Execution method for command.
     *
     * @param request
     * @param response
     * @return Returns address to go or redirect to place (return Const.REDIRECT+{placeToRedirect};).
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Part photo = request.getPart("photo");
        Account account = (Account) request.getSession().getAttribute(SessionAttribute.ACCOUNT);
        AccountDetailDao accountDetailDao = new AccountDetailDao();
        String errorMessage;
        if (account != null) {
            InputStream inputStream;
            if (photo != null) {
                inputStream = getInputStream(photo);
                AccountDetail ad = accountDetailDao.readAccountDetailByAccountId(account.getId());
                accountDetailDao.updateAccountPhoto(ad.getId(), inputStream);
                return Const.REDIRECT + "controller?command=open-profile-page";
            }
            errorMessage = "Photo haven't been chosen.";
            Const.logger.trace("errorMessage = " + errorMessage);
            request.setAttribute("errorMessage", errorMessage);
        }
        return Const.PAGE_PROFILE;
    }

    private InputStream getInputStream(Part photo) throws IOException {
        InputStream stream = null;
        if (photo != null) {
            stream = photo.getInputStream();
            Const.logger.trace(photo.getName());
            Const.logger.trace(photo.getSize());
            Const.logger.trace(photo.getContentType());
            if (stream != null) {
                ImageConverter.setDefaultImageBytesIfStreamNull(stream, "design/img/account.png");
            }
        }
        return stream;
    }
}
