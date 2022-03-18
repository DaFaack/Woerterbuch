package com.fujigames.dictionary_app.feature_dictionary.presentation

import com.fujigames.dictionary_app.feature_dictionary.domain.model.WordInfo

data class WordInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
) {
}