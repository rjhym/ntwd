package com.heyongming.ntwd.dao;

import com.heyongming.ntwd.entity.PhraseEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hym on 14-6-29.
 */
public interface PhraseDao {
    List<PhraseEntity> getPhraseEntities(@Param("page") int page,@Param("rows") int rows);

    PhraseEntity getPhraseEntityById(long id);

    List<PhraseEntity> getPhraseEntityByPhrase(String phrase);

    int insertPhraseEntity(PhraseEntity phraseEntity);

    int getPhraseTotal();
}
