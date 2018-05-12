package com.letv.core.utils;

import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;

public class EncryptUtils
{
    public static String SO_LOADED = null;
    public static String DEFAULT_SO_PATH = "/usr/lib/libencrypt.so";

//    static
//    {
////        System.loadLibrary("encrypt");
////        System.load("D:\\workspace\\intellij_workspace\\LetvEncrypt\\so\\libencrypt.so");
//    }

    public static native String letvEncrypt(long paramLong, String paramString);

    public static String letvEncryptByJNative(long paramLong, String paramString, String so) {
        try {
            JNative lib = new JNative(so,
                    "Java_com_letv_core_utils_EncryptUtils_letvEncrypt");
            lib.setParameter(1, (int) paramLong);
            lib.setParameter(2, paramString);
            lib.setRetVal(Type.STRING);
            lib.invoke();
            return lib.getRetVal();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public static String callLetvEncrypt(long paramLong, String paramString, boolean jnative, String so) throws Exception {
        if (jnative) {
            return letvEncryptByJNative(paramLong, paramString, so);
        } else {
            if (so != null && !so.equals(SO_LOADED)) {
                try {
                    System.load(so);
                    SO_LOADED = so;
                } catch (Exception e) {
                    SO_LOADED = null;
                    e.printStackTrace();
                    return e.getMessage();
                }
            }
            return letvEncrypt(paramLong, paramString);
        }
    }
}


/* Location:              /Users/hncg/Downloads/ChromeDownload/leshishipin_220-d2j.jar!/com/letv/core/utils/EncryptUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
