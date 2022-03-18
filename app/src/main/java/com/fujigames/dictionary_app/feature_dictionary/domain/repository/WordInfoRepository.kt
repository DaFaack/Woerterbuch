package com.fujigames.dictionary_app.feature_dictionary.domain.repository

import com.fujigames.dictionary_app.core.util.Resource
import com.fujigames.dictionary_app.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {
    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}