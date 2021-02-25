package com.ostap.komplikevych.webshop.model.command;

import com.ostap.komplikevych.webshop.constant.Const;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class CreateProductCommand implements Command {

    /**
     * Execution method for command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Const.logger.debug("Create product command starts");


        String pNameUa = request.getParameter("pNameUa");
        String pNameEn = request.getParameter("pNameEn");
        String pAboutUa = request.getParameter("pAboutUa");
        String pAboutEn = request.getParameter("pAboutEn");
        String pColorUa = request.getParameter("pColorUa");
        String pColorEn = request.getParameter("pColorEn");

        String pSizeWidth = request.getParameter("pSizeWidth");
        String mSizeWidth = request.getParameter("mSizeWidth");
        String pSizeHeight = request.getParameter("pSizeHeight");
        String mSizeHeight = request.getParameter("mSizeHeight");
        String pSizeDepth = request.getParameter("pSizeDepth");
        String mSizeDepth = request.getParameter("mSizeDepth");

        String categoryId = request.getParameter("categoryId");
        String productAmount = request.getParameter("productAmount");

        Part photo1 = request.getPart("photo1");
        //savePartAsFile(photo1,Const.RESOURCE_IMAGE_PATH);
        Const.logger.trace("photo1 =" + photo1);
        //String description = request.getParameter("description"); // Retrieves <input type="text" name="description">
        // Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">

        Part photo2 = request.getPart("photo2");
        //savePartAsFile(photo2,Const.RESOURCE_IMAGE_PATH);
        Const.logger.trace("photo2 =" + photo2);

        Part photo3 = request.getPart("photo3");
        // savePartAsFile(photo3,Const.RESOURCE_IMAGE_PATH);
        Const.logger.trace("photo3 =" + photo3);

        Const.logger.trace("pNameUa=" + pNameUa);
        Const.logger.trace("pNameEn=" + pNameEn);
        Const.logger.trace("pAboutUa=" + pAboutUa);
        Const.logger.trace("pAboutEn=" + pAboutEn);
        Const.logger.trace("pColorUa=" + pColorUa);
        Const.logger.trace("pColorEn=" + pColorEn);
        Const.logger.trace("pSizeWidth=" + pSizeWidth);
        Const.logger.trace("mSizeWidth=" + mSizeWidth);
        Const.logger.trace("pSizeHeight=" + pSizeHeight);
        Const.logger.trace("mSizeHeight=" + mSizeHeight);
        Const.logger.trace("pSizeDepth=" + pSizeDepth);
        Const.logger.trace("mSizeDepth=" + mSizeDepth);
        Const.logger.trace("categoryId=" + categoryId);
        Const.logger.trace("productAmount=" + productAmount);

        Const.logger.warn("Data received");

        Const.logger.debug("Create product command end;");

        return Const.PAGE_HOME;
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    private void savePartAsFile(Part part, String location) throws IOException {
        String fileName = extractFileName(part);
        part.write(location + File.separator + fileName);
    }
}
