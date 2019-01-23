package com.ilsxh.blog.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

public class CommonUtil {

    public static String enAes(String data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"),"ASE");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encode(encryptedBytes).toString();
//        return new BASE64Encoder().encode(encryptedBytes);

    }

    public static String deAes(String data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
//        byte[] cipherTextBytes = new BASE64Decoder().decodeBuffer(data);
        byte[] cipherTextBytes = Base64.getDecoder().decode(data);
        byte[] decValue = cipher.doFinal(cipherTextBytes);
        return new String(decValue);
    }

    public static void setCookie(HttpServletResponse response, Integer uid) {
        try {
            String val = CommonUtil.enAes(uid.toString(), Constant.AES_SALT);
            boolean isSSL = false;
            Cookie cookie = new Cookie(Constant.USER_IN_COOKIE, val);
            cookie.setPath("/");
            cookie.setMaxAge(60 * 30);
            cookie.setSecure(isSSL);
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
