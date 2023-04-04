package com.prospring.ch4.editors;

import java.beans.PropertyEditorSupport;

public class NameEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String[] name = text.split("\\s");
        setValue(new FullName(name[0], name[1]));
    }
}
