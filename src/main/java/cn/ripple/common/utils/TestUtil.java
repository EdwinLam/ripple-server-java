package cn.ripple.common.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestUtil {
    public static void main(String args[]){
        System.out.println(new BCryptPasswordEncoder().encode("123"));
    }
}
