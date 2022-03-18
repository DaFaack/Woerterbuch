package com.fujigames.dictionary_app.feature_dictionary.domain.model

data class Definition(
    val antonyms: List<String>,
    val definition: String,
    val synonyms: List<String>
)
