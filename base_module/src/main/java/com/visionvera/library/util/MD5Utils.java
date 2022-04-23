package com.visionvera.library.util;

import java.security.MessageDigest;

/**
 * @author 刘传政
 * @date 2019-06-04 18:56
 * QQ:1052374416
 * telephone:18501231486
 * 作用:
 * 注意事项:
 */
public class MD5Utils {
    public static String MD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
}
