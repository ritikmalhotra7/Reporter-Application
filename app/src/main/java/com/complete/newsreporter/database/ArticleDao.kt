package com.complete.newsreporter.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.complete.newsreporter.model.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article)

    @Query("Select * from articles")
    fun getAllArticles():LiveData<List<Article>>

    @Delete
    suspend fun delete(article: Article)
}