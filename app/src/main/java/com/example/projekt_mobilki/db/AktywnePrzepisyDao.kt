package com.example.projekt_mobilki.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.example.projekt_mobilki.aktywnePrzepis;
import com.example.projekt_mobilki.kategoria;
import com.example.projekt_mobilki.Przepis;
import com.example.projekt_mobilki.skladnik;
import kotlinx.coroutines.flow.Flow;

@Dao
interface AktywnePrzepisyDao {
    @Insert
    suspend fun insert(aktywnePrzepis: aktywnePrzepis)

    @Query("SELECT * FROM aktywnePrzepis")
    fun getAll(): LiveData<List<aktywnePrzepis>>

    @Query("SELECT * FROM przepisy WHERE id IN (:ids)")
    fun getPrzepisyByIds(ids: List<Int>): LiveData<List<Przepis>>

    @Query("DELETE FROM aktywnePrzepis WHERE idPrzepisu = :id")
    suspend fun deleteById(id : Int)

}
