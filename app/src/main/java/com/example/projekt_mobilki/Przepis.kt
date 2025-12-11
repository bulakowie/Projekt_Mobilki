package com.example.projekt_mobilki

import android.R
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "przepisy")
data class Przepis(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var nazwa: String,
    var opis: String,
    var zdjecie: String,
    var kategoria: Int,
    var skladnik: Int,
    var trescPrzepisu :String,
    var zaIleGodzinKoniec : Int
)

@Entity(tableName = "kategoria")
data class kategoria(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var nazwa: String
)

@Entity(tableName = "skladnik")
data class skladnik(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var nazwa: String
)

@Entity(tableName = "aktywnePrzepis")
data class aktywnePrzepis(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var idPrzepisu : Int,
    var stan: Int = 0
)

@Entity(tableName = "zrobionePrzepisy")
data class zrobionePrzepisy(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var idPrzepisu : Int,
    var dataPoczatku : String,
    var dataSkoczenia : String
)

@Entity(tableName = "komunikaty")
data class komunikaty(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var tekst: String,
    var czyPowtarza : Boolean,
    var ileRazy : Int,
    var czasDni : Int,
    var czasGodziny : Int
)

@Entity(tableName = "komunikatyDoPrzepisow")
data class komunikatyDoPrzepisow(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var idPrzepisu: Int,
    var idKomunikatu: Int
)

@Entity(tableName = "aktywneKomunikaty")
data class aktywneKomunikaty(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var idKomunikatu : Int,
    var DataKomunikatu : String,
    var idzrobionegoPrzepisu: Int
)