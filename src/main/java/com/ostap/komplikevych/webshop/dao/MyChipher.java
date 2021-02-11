package com.ostap.komplikevych.webshop.dao;

import com.ostap.komplikevych.webshop.constant.Const;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

public class MyChipher {

    private static final String SECRET_KEY_1;
    private static final String SECRET_KEY_2;
    private static final String CHARSET;

    private IvParameterSpec ivParameterSpec;
    private SecretKeySpec secretKeySpec;
    private Cipher cipher;

    static {
        SECRET_KEY_1 = Const.getProperty("cipher.first.key");
        SECRET_KEY_2 = Const.getProperty("cipher.second.key");
        CHARSET = Const.getProperty("cipher.charset");
    }

    public MyChipher() {
        try {
            ivParameterSpec = new IvParameterSpec(SECRET_KEY_1.getBytes(CHARSET));
            secretKeySpec = new SecretKeySpec(SECRET_KEY_2.getBytes(CHARSET), "AES");
            cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        } catch (Exception ex) {
            Const.logger.error(ex.getMessage());
        }
    }

    private String enc(String toBeEncrypt) throws InvalidAlgorithmParameterException, BadPaddingException,
            IllegalBlockSizeException, InvalidKeyException {
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] encrypted = cipher.doFinal(toBeEncrypt.getBytes());
        return Base64.encodeBase64String(encrypted);
    }

    private String dec(String encrypted) throws InvalidAlgorithmParameterException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] decryptedBytes = cipher.doFinal(Base64.decodeBase64(encrypted));
        return new String(decryptedBytes);
    }

    public String encrypt(String toBeEncrypt) {
        try {
            MyChipher myChipher = new MyChipher();
            String text = myChipher.enc(toBeEncrypt) + Const.getProperty("cipher.salt");
            return myChipher.enc(text);
        } catch (Exception ex) {
            Const.logger.error(ex);
            return null;
        }
    }

    public String decrypt(String encrypted) {
        try {
            MyChipher myChipher = new MyChipher();
            String text = myChipher.dec(encrypted);
            text = text.replace(Const.getProperty("cipher.salt"), "");
            return myChipher.dec(text);
        } catch (Exception ex) {
            Const.logger.error(ex.getMessage());
            return null;
        }
    }


    public static void main(String[] args) {
        MyChipher chipher = new MyChipher();
        String pass = chipher.encrypt("secret");
        System.out.println(chipher.decrypt(pass));
    }

}
