package me.dio.android.hilt.urlshortener.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.dio.android.hilt.urlshortener.data.model.ShortenedUrlEntity

@Dao
interface ShortenedUrlDao {

    @Insert
    fun insert(url: ShortenedUrlEntity)

    @Query("SELECT * FROM urls ORDER BY id DESC")
    fun getAll(): Flow<List<ShortenedUrlEntity>>

}
