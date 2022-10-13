package edu.miu.cs590.util;


import org.springframework.security.core.context.SecurityContextHolder;

public class AppUtil {

    public static String getCurrentUser(){
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
