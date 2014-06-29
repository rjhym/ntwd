package com.heyongming.ntwd.controller;


import com.heyongming.ntwd.dao.TestDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Resource(name = "testDao")
    TestDao testDao;

    @RequestMapping("/index")
    public ModelAndView test(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("username", request.getParameter("username"));
        model.addAttribute("test", testDao.getTestEntities());
        return new ModelAndView("index");
    }



}
