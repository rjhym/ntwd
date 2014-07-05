package com.heyongming.ntwd.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hym on 14-6-29.
 */
public class PhraseEntity {
    private long id;
    private String phrase;
    private String phraseSpell;
    private String phraseParaphrase;
    private String phraseProvenance;
    private String phraseDemo;
    private Date lastUpdateTime;


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

    public String getPhraseSpell() {
        return phraseSpell;
    }

    public void setPhraseSpell(String phraseSpell) {
        this.phraseSpell = phraseSpell;
    }

    public String getPhraseParaphrase() {
        return phraseParaphrase;
    }

    public void setPhraseParaphrase(String phraseParaphrase) {
        this.phraseParaphrase = phraseParaphrase;
    }

    public String getPhraseProvenance() {
        return phraseProvenance;
    }

    public void setPhraseProvenance(String phraseProvenance) {
        this.phraseProvenance = phraseProvenance;
    }

    public String getPhraseDemo() {
        return phraseDemo;
    }

    public void setPhraseDemo(String phraseDemo) {
        this.phraseDemo = phraseDemo;
    }

    public String getLastUpdateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(lastUpdateTime);
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
