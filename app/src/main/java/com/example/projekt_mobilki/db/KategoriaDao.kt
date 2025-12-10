package com.example.projekt_mobilki.db

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.projekt_mobilki.kategoria
import com.example.projekt_mobilki.Przepis

interface KategoriaDao {

    @Query("SELECT * FROM kategoria ORDER BY Nazwa ASC")
    fun getAll(): LiveData<List<kategoria>>
}