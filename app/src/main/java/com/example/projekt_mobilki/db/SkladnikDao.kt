package com.example.projekt_mobilki.db

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.projekt_mobilki.kategoria
import com.example.projekt_mobilki.skladnik

interface SkladnikDao {

        @Query("SELECT * FROM skladnik ORDER BY Nazwa ASC")
        fun getAll(): LiveData<List<skladnik>>

}