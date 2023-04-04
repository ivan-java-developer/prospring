package com.prospring.ch4.factory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

public class MessageDigester {
    private MessageDigest messageDigestOne;
    private MessageDigest messageDigestTwo;

    public void setMessageDigestOne(MessageDigest messageDigestOne) {
        this.messageDigestOne = messageDigestOne;
    }

    public void setMessageDigestTwo(MessageDigest messageDigestTwo) {
        this.messageDigestTwo = messageDigestTwo;
    }

    public void digest(String message) {
        System.out.println(message);
        System.out.println(messageDigestOne.getAlgorithm());
        digest(message, messageDigestOne);
        System.out.println(messageDigestTwo.getAlgorithm());
        digest(message, messageDigestTwo);
    }

    private void digest(String msg, MessageDigest digest) {
        digest.reset();
        byte[] bytes = msg.getBytes();
        byte[] digestBytes = digest.digest(bytes);
        System.out.println(new String(digestBytes));
    }
}
