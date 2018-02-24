package com.crossfire.instagramclone.Utils;

/**
 * @author Akshat Pandey
 * @version 1.0
 * @date 27-10-2017
 */

public class StringManipulation {

    public static String expandUsername(String username){
        return username.replace("."," ");
    }

    public static String condenseUsername(String username){
        return username.replace(" ",".");
    }
}
