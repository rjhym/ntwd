package com.heyongming.ntwd.admin.controller;

import com.heyongming.ntwd.crontab.WebCrawler;
import com.heyongming.ntwd.crontab.WebCrawlerUrl;
import com.heyongming.ntwd.dao.PhraseDao;
import com.heyongming.ntwd.dao.PhrasePageDao;
import com.heyongming.ntwd.entity.PhraseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hym on 14-6-29.
 */
@Controller
@RequestMapping("/admin/phrase")
public class AdminController {

    @Resource(name = "phraseDao")
    PhraseDao phraseDao;

    @Resource(name = "phrasePageDao")
    PhrasePageDao phrasePageDao;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addPhrase(HttpServletRequest request, HttpServletResponse response, Model model) {
        String phrase = request.getParameter("phrase");
        String phraseSpell = request.getParameter("phraseSpell");
        String phraseParaphrase = request.getParameter("phraseParaphrase");
        String phraseProvenance = request.getParameter("phraseProvenance");
        String phraseDemo = request.getParameter("phraseDemo");
        PhraseEntity phraseEntity = new PhraseEntity();
        phraseEntity.setPhrase(phrase);
        phraseEntity.setPhraseSpell(phraseSpell);
        phraseEntity.setPhraseParaphrase(phraseParaphrase);
        phraseEntity.setPhraseProvenance(phraseProvenance);
        phraseEntity.setPhraseDemo(phraseDemo);
        int i = phraseDao.insertPhraseEntity(phraseEntity);
        model.addAttribute("rows", i);
        return new ModelAndView("/");
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public ModelAndView initPhraseMsg(HttpServletRequest request, HttpServletResponse response, Model model) {
        if (!WebCrawlerUrl.run) {
            WebCrawlerUrl webCrawlerUrl = new WebCrawlerUrl(phrasePageDao);
            webCrawlerUrl.start();
            model.addAttribute("task", "started");
        } else {
            model.addAttribute("task", "is running");
        }
        return new ModelAndView("/");
    }

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public ModelAndView status(HttpServletRequest request, HttpServletResponse response, Model model) {

        model.addAttribute("task running?", WebCrawlerUrl.run);
        model.addAttribute("total", WebCrawlerUrl.total);
        return new ModelAndView("/");
    }


    @RequestMapping(value = "/initWord", method = RequestMethod.GET)
    public ModelAndView initWord(HttpServletRequest request, HttpServletResponse response, Model model) {
        if (!WebCrawler.run) {
            WebCrawler webCrawler = new WebCrawler(phrasePageDao, phraseDao);
            webCrawler.start();
            model.addAttribute("task", "started");
        } else {
            model.addAttribute("task", "is running");
        }
        return new ModelAndView("/");
    }

    @RequestMapping(value = "/statusWord", method = RequestMethod.GET)
    public ModelAndView statusWord(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("task running?", WebCrawler.run);
        model.addAttribute("total", WebCrawler.total);
        return new ModelAndView("/");
    }


}
