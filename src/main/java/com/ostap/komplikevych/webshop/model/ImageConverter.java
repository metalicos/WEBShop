package com.ostap.komplikevych.webshop.model;

import com.ostap.komplikevych.webshop.constant.Const;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Base64;

public class ImageConverter {

    /**
     * 1000px < ImageWidth < 1800px
     */
    public static final int MAX_IMAGE_SIZE_1MB = 1_048_576;
    /**
     * 800px < ImageWidth < 1000px
     */
    public static final int MAX_IMAGE_SIZE_555KB = 568_320;
    /**
     * 500px < ImageWidth < 800px
     */
    public static final int MAX_IMAGE_SIZE_368KB = 376_832;
    /**
     * 300px < ImageWidth < 500px
     */
    public static final int MAX_IMAGE_SIZE_149KB = 152_576;
    /**
     * 250px < ImageWidth < 300px
     */
    public static final int MAX_IMAGE_SIZE_56KB = 57_344;
    /**
     * 200px < ImageWidth < 250px
     */
    public static final int MAX_IMAGE_SIZE_40KB = 40_960;
    /**
     * 100px < ImageWidth < 200px
     */
    public static final int MAX_IMAGE_SIZE_26KB = 26_624;
    /**
     * 50px < ImageWidth < 100px
     */
    public static final int MAX_IMAGE_SIZE_8KB = 8_192;
    /**
     * 0px < ImageWidth < 50px
     */
    public static final int MAX_IMAGE_SIZE_3KB = 3_072;


    public static String convertBlobToBase64StringImage(final Blob blob, final int MAX_IMAGE_SIZE) {
        String image = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        if(blob!=null) {
            try {
                is = blob.getBinaryStream();
                baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[MAX_IMAGE_SIZE];
                int bytesRead = -1;

                while ((bytesRead = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, bytesRead);
                }
                byte[] imageBytes = baos.toByteArray();
                image = Base64.getEncoder().encodeToString(imageBytes);

            } catch (Exception ex) {
                Const.logger.error(ex);
            } finally {
                close(is);
                close(baos);
            }
        }
        return image;
    }

    public static InputStream convertImageToBlobAsBinaryStream(BufferedImage image) {
        ByteArrayOutputStream baos = null;
        ByteArrayInputStream bais = null;
        try {
            baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            bais = new ByteArrayInputStream(baos.toByteArray());
        } catch (Exception ex) {
            Const.logger.error(ex);
        } finally {
            close(baos);
        }
        return bais;
    }

    public static InputStream convertImageFromFileAsBinaryStream(String filePath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(filePath));
        } catch (Exception ex) {
            Const.logger.error(ex);
            return null;
        }
        return convertImageToBlobAsBinaryStream(image);
    }

    public static InputStream setDefaultImageBytesIfStreamNull(InputStream input, String defaultImagePath) {
        ByteArrayOutputStream baos = null;
        if (input != null)
            return input;
        try {
            if(defaultImagePath!=null) {
                return convertImageFromFileAsBinaryStream(defaultImagePath);
            }
        } catch (Exception ex) {
            Const.logger.error(ex);
        }
        return null;
    }

    private static void close(AutoCloseable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception ex) {
            Const.logger.error("Cannot close --> " + closeable.toString() + " " + ex);
        }
    }
}
