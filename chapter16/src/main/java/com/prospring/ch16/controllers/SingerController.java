package com.prospring.ch16.controllers;

import com.prospring.ch16.entities.Singer;
import com.prospring.ch16.services.SingerService;
import com.prospring.ch16.util.Message;
import com.prospring.ch16.util.SingerGrid;
import com.prospring.ch16.util.UrlUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
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


    @GetMapping(value = "/listgrid",
            consumes = {MediaType.ALL_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody SingerGrid listGrid(@RequestParam(value = "page", required = false) Integer page,
                                               @RequestParam(value = "rows", required = false) Integer rows,
                                               @RequestParam(value = "sidx", required = false) String sortBy,
                                               @RequestParam(value = "sord", required = false) String order) {

        logger.info("Listing singers for grid with page: {}, rows: {}", page, rows);
        logger.info("Listing singers for grid with sort: {}, order: {}", sortBy, order);

        Sort sort = null;
        String orderBy = sortBy;
        if (order != null && orderBy.equals("birthDateString")) {
            orderBy = "birthDate";
        }
        if (orderBy != null && order != null) {
            if (order.equalsIgnoreCase("DESC")) {
                sort = Sort.by(Sort.Direction.DESC, orderBy);
            } else {
                sort = Sort.by(Sort.Direction.ASC, orderBy);
            }
        }

        PageRequest pageRequest = null;
        if (sort != null) {
            pageRequest = PageRequest.of(page - 1, rows, sort);
        } else {
            pageRequest = PageRequest.of(page - 1, rows);
        }

        Page<Singer> singerPage = singerService.findAllByPage(pageRequest);

        SingerGrid singerGrid = new SingerGrid();
        singerGrid.setCurrentPage(singerPage.getNumber() + 1);
        singerGrid.setTotalPages(singerPage.getTotalPages());
        singerGrid.setTotalRecords(singerPage.getTotalElements());
        singerGrid.setSingerData(singerPage.stream().toList());
        return singerGrid;
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

    @PostMapping(value = "{id}", params = "form", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String update(@Valid @ModelAttribute("singer") Singer singer, BindingResult bindingResult, Model model,
                         HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
                         Locale locale, @RequestParam(value = "file", required = false) Part file) {
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

        byte[] fileContent = null;
        if (file != null && file.getSize() != 0) {
            logger.info("File name: {}", file.getName());
            logger.info("File size: {}", file.getSize());
            logger.info("File content type: {}", file.getContentType());
            try (InputStream inputStream = file.getInputStream()) {
                if (inputStream != null) {
                    fileContent = IOUtils.toByteArray(inputStream);
                } else {
                    logger.info("File input stream is null");
                }
            } catch (IOException e) {
                logger.error("Error updating uploaded file");
            }
        } else {
            fileContent = singerService.findById(singer.getId()).getPhoto();
        }
        singer.setPhoto(fileContent);

        singerService.save(singer);
        return "redirect:/singers/"
                + UrlUtil.encodeUrlPathSegment(singer.getId().toString(), httpServletRequest);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String create(@Valid @ModelAttribute("singer") Singer singer, BindingResult bindingResult, Model model,
                         HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
                         Locale locale, @RequestParam(value = "file", required = false) Part file) {
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

        byte[] fileContent = null;
        if (file != null) {
            logger.info("File name: {}", file.getName());
            logger.info("File size: {}", file.getSize());
            logger.info("File content type: {}", file.getContentType());
            try (InputStream inputStream = file.getInputStream()) {
                if (inputStream != null) {
                     fileContent = IOUtils.toByteArray(inputStream);
                } else {
                    logger.info("File input stream is null");
                }
            } catch (IOException e) {
                logger.error("Error saving uploaded file");
            }
        }
        singer.setPhoto(fileContent);
        singerService.save(singer);
        return "redirect:/singers/";
    }

    @GetMapping("/photo/{id}")
    public @ResponseBody byte[] downloadPhoto(@PathVariable("id") Long id) {
        Singer singer = singerService.findById(id);
        if (singer == null) {
            return null;
        }
        if (singer.getPhoto() != null) {
            logger.info("Downloading photo for id: {} with size: {}",
                    singer.getId(), singer.getPhoto().length);
        }
        return singer.getPhoto();
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
