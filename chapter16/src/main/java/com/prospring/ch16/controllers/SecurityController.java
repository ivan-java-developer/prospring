package com.prospring.ch16.controllers;

import com.prospring.ch16.util.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Controller
@RequestMapping("/security")
public class SecurityController {
    private Logger logger = LoggerFactory.getLogger(SecurityController.class);
    private MessageSource messageSource;

    @GetMapping("/loginfail")
    public String loginFail(Model model, Locale locale) {
        logger.info("Login failed detected");
        model.addAttribute("message",
                new Message("error", messageSource.getMessage("message_login_fail", null, locale)));
        return "singers/list";
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
