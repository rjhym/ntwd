package com.heyongming.ntwd.controller;


import com.heyongming.ntwd.dao.PhraseDao;
import com.heyongming.ntwd.entity.PhraseEntity;
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
import java.util.List;

@Controller
@RequestMapping("/phrase")
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Resource(name = "phraseDao")
    PhraseDao phraseDao;

    @RequestMapping("/d-i-{id}")
    public ModelAndView detailById(@PathVariable int id, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("phrase", phraseDao.getPhraseEntityById(id));
        return new ModelAndView("phrase");
    }

    @RequestMapping("/d-n-{phrase}")
    public ModelAndView detailByName(@PathVariable String phrase, HttpServletRequest request, HttpServletResponse response, Model model) {
        List<PhraseEntity> phraseEntityByPhrase = phraseDao.getPhraseEntityByPhrase(phrase);
        if (phraseEntityByPhrase != null && phraseEntityByPhrase.size() > 0) {
            model.addAttribute("phrase", phraseEntityByPhrase.get(0));
            if (phraseEntityByPhrase.size() > 1) {
                model.addAttribute("relationPhrases", phraseEntityByPhrase.subList(1, phraseEntityByPhrase.size() - 1));
            }
        }
        return new ModelAndView("phrase");
    }

    @RequestMapping("/l-{rows}-{page}")
    public ModelAndView list(@PathVariable int rows, @PathVariable int page, HttpServletRequest request, HttpServletResponse response, Model model) {
        int total = phraseDao.getPhraseTotal();

        if (total / rows < page -1) {
            page = 1;
        }
        model.addAttribute("phrases", phraseDao.getPhraseEntities((page - 1) * rows, rows));
        model.addAttribute("total", total);
        model.addAttribute("page", page);
        model.addAttribute("rows", rows);

        return new ModelAndView("phraseList");
    }


}
