package com.apress.prospring5.ch2.replacer;

public class ReplacementTarget {
    public String format(String str) {
        return "<h3>" + str + "</h3>";
    }

    public String formatMessage(String msg) {
        return format(msg);
    }

//    public String formatMessage(Object msg) {
//        return "<h1>" + msg + "</h1>";
//    }
}
