package com.example.app15

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Query("SELECT * FROM AllWords LIMIT 5")
    fun getAllWithLimit(): Flow<List<Word>>

    @Insert
    suspend fun addWord(word: Word)

    @Query("SELECT * FROM AllWords WHERE Word LIKE :thisWord")
    suspend fun getWord(thisWord: String): Word?

    @Query("DELETE FROM AllWords")
    suspend fun delete()

    @Query("UPDATE AllWords SET count = count+1 WHERE Word  LIKE :newWord")
    suspend fun update(newWord: String)
}