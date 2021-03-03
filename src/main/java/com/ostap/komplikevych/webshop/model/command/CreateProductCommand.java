package com.ostap.komplikevych.webshop.model.command;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.constant.Validator;
import com.ostap.komplikevych.webshop.dao.ProductDao;
import com.ostap.komplikevych.webshop.dao.ProductDetailDao;
import com.ostap.komplikevych.webshop.entity.Measurement;
import com.ostap.komplikevych.webshop.entity.Product;
import com.ostap.komplikevych.webshop.entity.ProductDetail;
import com.ostap.komplikevych.webshop.localization.Language;
import com.ostap.komplikevych.webshop.model.ImageConverter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.logging.Logger;

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
        String pColorUa = request.getParameter("pColorUa");
        String pColorEn = request.getParameter("pColorEn");
        String pAboutUa = request.getParameter("pAboutUa");
        String pAboutEn = request.getParameter("pAboutEn");
        String price = request.getParameter("productPrice");
        String amount = request.getParameter("productAmount");

        String pSizeWidth = request.getParameter("pSizeWidth");
        String mSizeWidth = request.getParameter("mSizeWidth");
        String pSizeHeight = request.getParameter("pSizeHeight");
        String mSizeHeight = request.getParameter("mSizeHeight");
        String pSizeDepth = request.getParameter("pSizeDepth");
        String mSizeDepth = request.getParameter("mSizeDepth");

        String errorMessage;
        Part photo1 = request.getPart("photo1");
        if (photo1 == null) {
            errorMessage = "Main photo must be set";
            request.setAttribute("errorMessage", errorMessage);
            Const.logger.warn("errorMessage = " + errorMessage);
            return "controller?command=open-create-product";
        }
        InputStream is1 = getInputStream(photo1);
        Const.logger.trace("photo1 =" + photo1);


        if (Validator.checkIfNullOrEmptyReturnTrue(price, amount, pNameEn, pAboutUa, pColorEn, pColorUa,
                pAboutEn, pAboutUa, pSizeDepth, pSizeHeight, pSizeWidth, mSizeDepth, mSizeHeight, mSizeWidth)) {
            errorMessage = "Required fields are empty";
            request.setAttribute("errorMessage", errorMessage);
            Const.logger.warn("errorMessage = " + errorMessage);
            return "controller?command=open-create-product";
        }

        Part photo2 = request.getPart("photo2");
        InputStream is2 = getInputStream(photo2);
        Const.logger.trace("photo2 =" + photo2);

        Part photo3 = request.getPart("photo3");
        InputStream is3 = getInputStream(photo3);
        Const.logger.trace("photo3 =" + photo3);

        ProductDao productDao = new ProductDao();
        Product product = new Product();

        if (price.matches(Validator.UNSIGNED_DOUBLE)) {
            product.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));
        } else {
            errorMessage = "Price value is incorrect.";
            request.setAttribute("errorMessage", errorMessage);
            return "controller?command=open-create-product";
        }
        product.setOrderedAmount(0);
        product.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
        if (price.matches(Validator.UNSIGNED_INTEGER)) {
            product.setAmount(Integer.parseInt(amount));
        }else{
            errorMessage = "Amount value is incorrect.";
            request.setAttribute("errorMessage", errorMessage);
            return "controller?command=open-create-product";
        }

        errorMessage = validateProduct(product);
        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            return "controller?command=open-create-product";
        }

        int productId = productDao.createProduct(product);
        product.setId(productId);

        errorMessage = (productId == -1 ? "Product is not created" : null);
        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            return "controller?command=open-create-product";
        }


        ProductDetail productDetail = new ProductDetail();
        productDetail.setNameUa(pNameUa);
        productDetail.setNameEn(pNameEn);
        productDetail.setColorUa(pColorUa);
        productDetail.setColorEn(pColorEn);
        productDetail.setSizeUa(createSize(pSizeWidth, mSizeWidth, pSizeHeight, mSizeHeight,
                pSizeDepth, mSizeDepth, Language.UA));
        productDetail.setSizeEn(createSize(pSizeWidth, mSizeWidth, pSizeHeight, mSizeHeight,
                pSizeDepth, mSizeDepth, Language.EN));
        productDetail.setAboutUa(pAboutUa);
        productDetail.setAboutEn(pAboutEn);
        productDetail.setProductId(productId);

        errorMessage = validateProductDetails(productDetail);
        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            productDao.deleteProduct(product);
            return "controller?command=open-create-product";
        }

        ProductDetailDao productDetailDao = new ProductDetailDao();
        int productDetailId = productDetailDao.createProductDetail(productDetail, is1, is2, is3);
        Const.logger.trace("productDetailId = " + productDetailId);

        Const.logger.debug("Create product command end;");

        return Const.REDIRECT+"controller?command=open-home-page";
    }

    private String createSize(String w, String meas1, String h, String meas2,
                              String l, String meas3, Language language) {
        StringBuilder str = new StringBuilder();
        Const.logger.trace("w"+w+"meas1"+meas1+"*"+"h"+h+"meas2"+meas2+"*"+"l"+l+"meas3"+meas3);
        if (language == Language.UA) {
            str.append(w).append(getPrefix(Measurement.getMeasurement(meas1), Language.UA))
                    .append("*")
                    .append(h).append(getPrefix(Measurement.getMeasurement(meas2), Language.UA))
                    .append("*")
                    .append(l).append(getPrefix(Measurement.getMeasurement(meas3), Language.UA));
        } else {
            str.append(w).append(getPrefix(Measurement.getMeasurement(meas1), Language.EN))
                    .append("*")
                    .append(h).append(getPrefix(Measurement.getMeasurement(meas2), Language.EN))
                    .append("*")
                    .append(l).append(getPrefix(Measurement.getMeasurement(meas3), Language.EN));
        }
        return str.toString();
    }

    private String getPrefix(Measurement measurement, Language lang) {
        if (Measurement.MM == measurement) {
            if (Language.EN == lang) {
                return "mm";
            }
            if (Language.UA == lang) {
                return "мм";
            }
        }
        if (Measurement.CM == measurement) {
            if (Language.EN == lang) {
                return "сm";
            }
            if (Language.UA == lang) {
                return "см";
            }
        }
        if (Measurement.M == measurement){
            if (Language.EN == lang) {
                return "m";
            }
            if (Language.UA == lang) {
                return "м";
            }
        }
        return "mm";
    }

    private InputStream getInputStream(Part photo) throws IOException {
        InputStream stream = null;
        if (photo != null) {
            stream = photo.getInputStream();
            Const.logger.trace(photo.getName());
            Const.logger.trace(photo.getSize());
            Const.logger.trace(photo.getContentType());
            if (stream != null) {
                ImageConverter.setDefaultImageBytesIfStreamNull(stream, "design/img/camera.png");
            }
        }
        return stream;
    }

    private String validateProduct(Product product) {
        String errorMessage;
        if (product.getAmount() < 1 || product.getAmount() > 99999999) {
            errorMessage = "Amount is not valid";
            Const.logger.trace(errorMessage);
            return errorMessage;
        }
        if (product.getPrice().compareTo(new BigDecimal(0)) < 0) {
            errorMessage = "Price can not be less than 0";
            Const.logger.trace(errorMessage);
            return errorMessage;
        }
        return null;
    }

    private String validateProductDetails(ProductDetail pd) {
        String errorMessage;
        if (Validator.checkIfNullOrEmptyReturnTrue(pd.getNameEn(), pd.getNameUa(), pd.getColorEn(), pd.getColorUa(),
                pd.getAboutUa(), pd.getAboutEn(), pd.getSizeEn(), pd.getSizeUa())) {

            errorMessage = "Product didn't pass the moderation. Cause: one or more required fields are null";
            Const.logger.trace(errorMessage);
            return errorMessage;
        }

        if (Validator.checkIfContainsRestrictedWords(pd.getNameEn(), pd.getNameUa(),
                pd.getAboutEn(), pd.getAboutUa())) {
            errorMessage = "Product didn't pass the moderation. Cause: restricted words.";
            Const.logger.trace(errorMessage);
            return errorMessage;
        }
        return null;
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
