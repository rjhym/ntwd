package com.heyongming.ntwd.dao;

import com.heyongming.ntwd.entity.PhraseEntity;

import java.util.List;

/**
 * Created by hym on 14-6-29.
 */
public interface PhraseDao {
    List<PhraseEntity> getPhraseEntities();

    PhraseEntity getPhraseEntityById(long id);

    int insertPhraseEntity(PhraseEntity phraseEntity);
}
