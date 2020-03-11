package com.qf.util;

public class StringUtil {


    /**
     * 获得文件扩展名，不带“.”
     * @param fileName
     * @return
     */
    public static String getFileExtName(String fileName){


        int las = fileName.lastIndexOf(".");

        return fileName.substring(las+1);

    }

    /**
     * 获得文件扩展名，不带“.”
     * @param fileName
     * @return
     */
    public static String getFileExtNameWithPoint(String fileName){


        int las = fileName.lastIndexOf(".");

        return fileName.substring(las);

    }

    /**
     * 封装Redis键
     */
    public static String getRedisKey(String pre,String key){
        //线程不安全的，但性能高！
        StringBuilder sb = new StringBuilder();
        sb.append(pre);
        sb.append(key);
        return sb.toString();
    }


}
