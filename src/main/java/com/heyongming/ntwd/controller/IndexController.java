package com.heyongming.ntwd.controller;


import com.heyongming.ntwd.dao.PhraseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/phrase")
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Resource(name = "phraseDao")
    PhraseDao phraseDao;

    @RequestMapping("/index")
    public ModelAndView test(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("phrases", phraseDao.getPhraseEntities());
        return new ModelAndView("index");
    }


}
