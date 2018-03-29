package com.healthmanager.util;

import com.github.pagehelper.util.StringUtil;
import com.healthmanager.exception.CryptException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class CryptUtil {


    public static String binary(byte[] bytes, int radix){
        return new BigInteger(1, bytes).toString(radix);
    }


    public static String base64Encode(byte[] bytes){
        return new BASE64Encoder().encode(bytes);
    }


    public static byte[] base64Decode(String base64Code){
        try {
            return StringUtil.isEmpty(base64Code) ? null : new BASE64Decoder().decodeBuffer(base64Code);
        }
        catch (Exception e){
            throw new CryptException(e);
        }
    }


    public static byte[] md5(byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytes);

            return md.digest();
        }
        catch (Exception e){
            throw new CryptException(e);
        }
    }


    public static byte[] md5(String msg) {
        return StringUtil.isEmpty(msg) ? null : md5(msg.getBytes());
    }


    public static String md5Encrypt(String msg) {
        return StringUtil.isEmpty(msg) ? null : base64Encode(md5(msg));
    }

    public static byte[] aesEncryptToBytes(String content, String encryptKey) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(encryptKey.getBytes()));

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));

            return cipher.doFinal(content.getBytes("utf-8"));
        }
        catch (Exception e){
            throw new CryptException(e);
        }
    }

    public static String aesEncrypt(String content, String encryptKey) {
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }


    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(decryptKey.getBytes()));

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
            byte[] decryptBytes = cipher.doFinal(encryptBytes);

            return new String(decryptBytes);
        }
        catch (Exception e){
            throw new CryptException(e);
        }
    }


    public static String aesDecrypt(String encryptStr, String decryptKey) {
        return StringUtil.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
    }
}
