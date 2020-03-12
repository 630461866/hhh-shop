package com.qf.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SpringSecurityUtil {


    /**
     * 根据明文 生成密文
     * @param passowrd
     * @return
     */
    public static String getEncodePassword(String passowrd){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.encode(passowrd);
    }

}
