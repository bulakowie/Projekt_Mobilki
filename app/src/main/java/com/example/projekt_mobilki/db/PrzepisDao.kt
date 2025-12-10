package com.example.projekt_mobilki.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.projekt_mobilki.kategoria
import com.example.projekt_mobilki.Przepis
import com.example.projekt_mobilki.skladnik
import kotlinx.coroutines.flow.Flow

@Dao
interface PrzepisDao {
    @Query("SELECT * FROM przepisy ORDER BY Nazwa ASC")
    fun getAll(): LiveData<List<Przepis>>

    @Query("SELECT * FROM przepisy where kategoria = :a")
    fun getByKategoria (a : Int) : LiveData<List<Przepis>>

    @Query("SELECT * FROM przepisy where skladnik = :a")
    fun getBySkladnik (a : Int) : LiveData<List<Przepis>>

    @Query("SELECT * FROM kategoria")
    fun getAllKategoria () : LiveData<List<kategoria>>
    @Query("SELECT * FROM skladnik")
    fun getAllSkladnik () : LiveData<List<skladnik>>
    @Insert
    suspend fun insert(przepis: Przepis)

    @Update
    suspend fun update(przepis: Przepis)

    @Delete
    suspend fun delete(przepis: Przepis)

    @Query("SELECT * FROM przepisy WHERE id = :id")
    fun getById(id: Int): LiveData<Przepis?>

    @Query("DELETE FROM przepisy")
    suspend fun deleteAll()
}