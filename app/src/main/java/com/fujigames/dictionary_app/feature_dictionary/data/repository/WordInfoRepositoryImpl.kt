package com.fujigames.dictionary_app.feature_dictionary.data.repository

import android.content.Context
import com.fujigames.dictionary_app.R
import com.fujigames.dictionary_app.core.util.Resource
import com.fujigames.dictionary_app.feature_dictionary.data.local.WordInfoDao
import com.fujigames.dictionary_app.feature_dictionary.data.remote.DictionaryApi
import com.fujigames.dictionary_app.feature_dictionary.domain.model.WordInfo
import com.fujigames.dictionary_app.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
): WordInfoRepository {
    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())

        val wordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Loading(wordInfos))

        try{
            val remoteWordInfo = api.getWordInfo(word)
            dao.deleteWordInfos(remoteWordInfo.map { it.word })
            dao.insertWordInfo(remoteWordInfo.map { it.toWordInfoEntity() })
        } catch (e: HttpException){
            emit(Resource.Error("HTTP Error: $e", wordInfos))
        }catch (e: IOException){
            emit(Resource.Error("IO Error: $e", wordInfos))
        }

        val newWordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos))
    }
}