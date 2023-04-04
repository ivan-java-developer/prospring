package com.prospring.ch10.converters;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Component("appConversionService")
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {
    private static Logger logger = LoggerFactory.getLogger(ApplicationConversionServiceFactoryBean.class);

    private static final String DEFAULT_DATE_PATTERN = "dd-MM-yyyy";
    private String datePattern = DEFAULT_DATE_PATTERN;
    private DateTimeFormatter dateTimeFormat;
    private Set<Formatter<?>> formatters = new HashSet<>();

    public String getDatePattern() {
        return datePattern;
    }

    @Autowired(required = false)
    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

    @PostConstruct
    public void init() {
        dateTimeFormat = DateTimeFormat.forPattern(datePattern);
        formatters.add(getDateTimeFormatter());
        setFormatters(formatters);
    }

    public Formatter<DateTime> getDateTimeFormatter() {
        return new Formatter<>() {
            @Override
            public DateTime parse(String dateString, Locale locale) throws ParseException {
                logger.info("Parsing date string: " + dateString);
                return dateTimeFormat.parseDateTime(dateString);
            }

            @Override
            public String print(DateTime dateTime, Locale locale) {
                logger.info("Formatting dateTime: " + dateTime);
                return dateTimeFormat.print(dateTime);
            }
        };
    }
}
