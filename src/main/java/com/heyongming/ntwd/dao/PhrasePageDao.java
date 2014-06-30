package com.heyongming.ntwd.dao;

import com.heyongming.ntwd.entity.PhraseEntity;
import com.heyongming.ntwd.entity.PhrasePageEntity;

import java.util.List;

/**
 * Created by hym on 14-6-29.
 */
public interface PhrasePageDao {

    PhrasePageEntity getPhrasePageEntityById(int id);

    int insertPhrasePageEntity(PhrasePageEntity phrasePageEntity);

    List<PhrasePageEntity> getPhrasePageEntities();


}
