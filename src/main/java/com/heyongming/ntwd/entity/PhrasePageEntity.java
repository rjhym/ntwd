package com.heyongming.ntwd.entity;

import java.util.Date;

/**
 * Created by hym on 14-6-29.
 */
public class PhrasePageEntity {
    private long id;
    private String phrase;
    private String phraseUrl;
    private String phraseInitial;
    private int phrasePage;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getPhraseUrl() {
        return phraseUrl;
    }

    public void setPhraseUrl(String phraseUrl) {
        this.phraseUrl = phraseUrl;
    }

    public String getPhraseInitial() {
        return phraseInitial;
    }

    public void setPhraseInitial(String phraseInitial) {
        this.phraseInitial = phraseInitial;
    }

    public int getPhrasePage() {
        return phrasePage;
    }

    public void setPhrasePage(int phrasePage) {
        this.phrasePage = phrasePage;
    }
}
