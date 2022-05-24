package com.complete.newsreporter.database

import androidx.annotation.RestrictTo
import com.complete.newsreporter.api.RetrofitInstance
import com.complete.newsreporter.model.Article
import retrofit2.Retrofit


class NewsRepository (val db :ArticleDatabase){

    suspend fun getBreakingNews(countryCode:String, pageNumber:Int) = RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)
    suspend fun getSearchQuery(searchedQuery:String,pageNumber: Int) = RetrofitInstance.api.searchForNews(searchedQuery,pageNumber)

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)
    suspend fun delete(article: Article) = db.getArticleDao().delete(article)
    fun getAll() = db.getArticleDao().getAllArticles()

    suspend fun getNumber() = RetrofitInstance.api.getNumber()
}