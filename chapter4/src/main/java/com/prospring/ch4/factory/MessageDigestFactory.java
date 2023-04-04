package com.prospring.ch4.factory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestFactory {
    private String algorithm = "MD5";

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public MessageDigest createInstance() throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(algorithm);
    }
}
