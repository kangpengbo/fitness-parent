package com.woniu.fitness.utils;
import org.apache.shiro.crypto.hash.SimpleHash;

public class MD5Maker {
    public static String stringToMd5StringWithSalt(String password, String saltString) {
        String hashAlgorithmName = "MD5";
        int hashIterations = 2048;
        String result = new SimpleHash(hashAlgorithmName, password, saltString, hashIterations).toString();
        return result;
    }
}

