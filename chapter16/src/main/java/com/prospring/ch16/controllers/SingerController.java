package com.prospring.ch16.controllers;

import com.prospring.ch16.entities.Singer;
import com.prospring.ch16.services.SingerService;
import com.prospring.ch16.util.Message;
import com.prospring.ch16.util.UrlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@RequestMapping("/singers")
@Controller
public class SingerController {
    private final Logger logger = LoggerFactory.getLogger(SingerController.class);

    private SingerService singerService;
    private MessageSource messageSource;

    @GetMapping
    public String list(Model model) {
        logger.info("Listing singers");
        List<Singer> singers = singerService.findAll();
        model.addAttribute("singers", singers);
        logger.info("No. of singers: " + singers.size());
        return "singers/list";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Singer singer = singerService.findById(id);
        model.addAttribute("singer", singer);
        return "singers/show";
    }

    @GetMapping(value = "/{id}", params = "form")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        Singer singer = singerService.findById(id);
        model.addAttribute("singer", singer);
        return "singers/update";
    }

    @PostMapping(value = "{id}", params = "form")
    public String update(@Valid @ModelAttribute("singer") Singer singer, BindingResult bindingResult, Model model,
                         HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
                         Locale locale) {
        logger.info("Updating singer");
        if (bindingResult.hasErrors()) {
            model.addAttribute("message",
                    new Message("error",
                            messageSource.getMessage("singer_save_fail", null, locale)));
            model.addAttribute("singer", singer);
            return "singers/update";
        }
        model.asMap().clear();
        redirectAttributes.addFlashAttribute("message",
                new Message("success",
                        messageSource.getMessage("singer_save_success", null, locale)));
        singerService.save(singer);
        return "redirect:/singers/"
                + UrlUtil.encodeUrlPathSegment(singer.getId().toString(), httpServletRequest);
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("singer") Singer singer, BindingResult bindingResult, Model model,
                         HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
                         Locale locale) {
        logger.info("Creating singer");
        if (bindingResult.hasErrors()) {
            model.addAttribute("message",
                    new Message("error",
                            messageSource.getMessage("singer_save_fail", null, locale)));
            model.addAttribute("singer", singer);
            return "singers/create";
        }
        model.asMap().clear();
        redirectAttributes.addFlashAttribute("message",
                new Message("success",
                        messageSource.getMessage("singer_save_success", null, locale)));
        singerService.save(singer);
        return "redirect:/singers/";
    }

    @GetMapping(params = "form")
    public String createForm(Model model) {
        Singer singer = new Singer();
        model.addAttribute("singer", singer);
        return "singers/create";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        logger.info("Deleting singer with id = " + id);
        Singer singer = singerService.findById(id);
        singerService.delete(singer);
        return "redirect:/singers";
    }

    @Autowired
    public void setSingerService(SingerService singerService) {
        this.singerService = singerService;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
