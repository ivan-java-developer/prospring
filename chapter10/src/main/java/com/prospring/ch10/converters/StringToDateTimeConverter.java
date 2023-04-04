package com.prospring.ch10.converters;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.core.convert.converter.Converter;

import javax.annotation.PostConstruct;

public class StringToDateTimeConverter implements Converter<String, DateTime> {
    private static final String DEFAULT_DATE_PATTERN = "dd-MM-yyyy";
    private String datePattern = DEFAULT_DATE_PATTERN;
    private DateTimeFormatter dateTimeFormatter;

    public String getDatePattern() {
        return datePattern;
    }

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

    @PostConstruct
    public void init() {
        dateTimeFormatter = DateTimeFormat.forPattern(datePattern);
    }

    @Override
    public DateTime convert(String dateTimeString) {
        return dateTimeFormatter.parseDateTime(dateTimeString);
    }
}
