package com.prospring.ch12;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter {

    private static Logger logger = LoggerFactory.getLogger(DateConverter.class);

    private String dateTimeFormat = "dd-MM-yyyy";

    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        Date date = (Date) source;
        SimpleDateFormat sdf = new SimpleDateFormat(dateTimeFormat);
        writer.setValue(sdf.format(date));
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        Date date = new Date();
        String dateTimeString = reader.getValue();
        if (dateTimeString != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(dateTimeString);
            try {
                date = sdf.parse(dateTimeString);
            } catch (ParseException e) {
                logger.error("Not a valid date: " + dateTimeString, e);
            }
        }
        return date;
    }

    @Override
    public boolean canConvert(Class type) {
        return true;
    }
}
