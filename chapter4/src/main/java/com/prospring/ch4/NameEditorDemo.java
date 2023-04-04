package com.prospring.ch4;

import com.prospring.ch4.editors.FullName;
import org.springframework.context.support.GenericXmlApplicationContext;

public class NameEditorDemo {
    private FullName fullName;

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx =
                new GenericXmlApplicationContext("classpath:spring/app-context-08.xml");
        NameEditorDemo nameDemo = (NameEditorDemo) ctx.getBean("nameDemo");
        System.out.println(nameDemo.getFullName());
        ctx.close();
    }
}
