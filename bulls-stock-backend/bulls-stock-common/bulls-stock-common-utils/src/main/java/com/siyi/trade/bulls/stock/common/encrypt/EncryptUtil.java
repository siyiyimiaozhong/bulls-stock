package com.siyi.trade.bulls.stock.common.encrypt;

import com.siyi.trade.bulls.stock.common.exception.ComponentException;
import com.siyi.trade.bulls.stock.common.exception.constants.ApplicationErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * @Author: siyiyimiaozhong
 * @Description:
 * @Project: bulls-stock-backend
 * @Package: com.siyi.trade.bulls.stock.common.encrypt
 * @ClassName: EncryptUtil.java
 * @CreateTime: 2022-11-13  16:23
 * @Version: 1.0
 */
@SuppressWarnings("restriction")
@Slf4j
public class EncryptUtil {

    private static final char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static String BASE64Encrypt;

    public static String md5ToString(String signed) {

        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {

            byte[] res = signed.getBytes("UTF-8");
            MessageDigest mdTemp = MessageDigest.getInstance("MD5".toUpperCase());
            mdTemp.update(res);
            byte[] md = mdTemp.digest();

            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static byte[] md5(String str) {
        try {
            byte[] res = str.getBytes("UTF-8");
            MessageDigest mdTemp = MessageDigest.getInstance("MD5".toUpperCase());
            mdTemp.update(res);
            byte[] hash = mdTemp.digest();
            return hash;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * MD5值计算<p>
     * MD5的算法在RFC1321 中定义:
     * 在RFC 1321中，给出了Test suite用来检验你的实现是否正确：
     * MD5 ("") = d41d8cd98f00b204e9800998ecf8427e
     * MD5 ("a") = 0cc175b9c0f1b6a831c399e269772661
     * MD5 ("abc") = 900150983cd24fb0d6963f7d28e17f72
     * MD5 ("message digest") = f96b697d7cb7938d525a2f31aaf161d0
     * MD5 ("abcdefghijklmnopqrstuvwxyz") = c3fcd3d76192e4007dfb496cca67e13b
     *
     * @param str 源字符串
     * @return md5值
     */
    public static byte[] md5EncryptReturnHexDigitsByteArray(String str) {
        try {
            byte[] res = str.getBytes("UTF-8");
            MessageDigest mdTemp = MessageDigest.getInstance("MD5".toUpperCase());
            mdTemp.update(res);
            byte[] hash = mdTemp.digest();
            return hash;
        } catch (Exception e) {
            return null;
        }
    }

    public static String md5EncryptReturnString(String str) {

        byte[] b = md5EncryptReturnHexDigitsByteArray(str);

        StringBuilder resultSb = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            int n = b[i];
            if (n < 0) {
                n = 256 + n;
            }
            int d1 = n / 16;
            int d2 = n % 16;
            resultSb.append(hexDigits[d1]);
            resultSb.append(hexDigits[d2]);
        }
        return resultSb.toString();

    }

    // 加密后解密
    public static String jm(byte[] inStr) {
        String newStr = new String(inStr);
        char[] a = newStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String k = new String(a);
        return k;
    }

    /**
     * BASE64加密MD5EncrtyReutrnhexDigitsByteArray
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String base64Encrypt(byte[] key) throws ComponentException {
        String edata = null;
        try {
            edata = (new BASE64Encoder()).encodeBuffer(key).trim();
        } catch (Exception e) {
            throw new ComponentException(ApplicationErrorCodeEnum.SYS_ERROR_ENCRYPT_SINGED, "BASE64编码错误！key=" + new String(key) + ", error=" + e.getMessage());
        }
        return edata;
    }

    /**
     * BASE64解密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] base64Decrypt(String data) {
        if (data == null) {
            return null;
        }
        byte[] edata = null;
        try {
            edata = (new BASE64Decoder()).decodeBuffer(data);
            return edata;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * @param key 24位密钥
     * @param str 源字符串
     * @return
     * @throws ComponentException
     */
    public static byte[] des3Encrypt(String key, String str) throws ComponentException {

        byte[] bt;
        try {
            byte[] newkey = key.getBytes();

            SecureRandom sr = new SecureRandom();

            DESedeKeySpec dks = new DESedeKeySpec(newkey);

            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");

            SecretKey securekey = keyFactory.generateSecret(dks);

            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");

            cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

            bt = cipher.doFinal(str.getBytes("utf-8"));

            return bt;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ComponentException(ApplicationErrorCodeEnum.SYS_ERROR_ENCRYPT_SINGED, "DES3加密签名错误！key=" + key + ", str=" + str + ", error=" + e.getMessage());
        }

    }

    /**
     * 解密
     *
     * @param edata
     * @param key
     * @return
     * @throws Exception
     */
    public static String des3Decrypt(byte[] edata, String key) {
        String data = "";
        try {
            if (edata != null) {
                byte[] newkey = key.getBytes();
                DESedeKeySpec dks = new DESedeKeySpec(newkey);
                SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
                SecretKey securekey = keyFactory.generateSecret(dks);
                Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
                cipher.init(Cipher.DECRYPT_MODE, securekey, new SecureRandom());
                // String newData = new String(edata);
                // if (!newData.endsWith("=")){
                // data = URLDecoder.decode(newData,"utf-8");
                // }
                byte[] bb = cipher.doFinal(edata);
                data = new String(bb, "UTF-8");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return data;
    }

    /**
     * 方法用途: 签名加密<br>
     * 实现步骤: <br>
     *
     * @param signed ：签名的字符串
     * @return
     */
    public static String encryptSigned(String signed) throws ComponentException {

        try {
            byte[] md5SignStr = md5EncryptReturnHexDigitsByteArray(signed);
            return base64Encrypt(md5SignStr);
        } catch (Exception e) {
            throw new ComponentException(ApplicationErrorCodeEnum.SYS_ERROR_ENCRYPT_SINGED, "BASE64或MD5加密签名错误！signed=" + signed + ", error=" + e.getMessage());
        }
    }

    public static String encryptSignedSelfTerminal(String signed) throws ComponentException {
        return base64Encrypt(md5ToString(signed).getBytes());
    }

    public static void main(String[] args) throws Exception {
        System.out.println(encryptSigned("app"));
        System.out.println(encryptSigned("123"));
    }

}
