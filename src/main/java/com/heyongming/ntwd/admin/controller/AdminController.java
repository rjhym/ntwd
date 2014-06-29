package com.heyongming.ntwd.admin.controller;

import com.heyongming.ntwd.dao.PhraseDao;
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
}
