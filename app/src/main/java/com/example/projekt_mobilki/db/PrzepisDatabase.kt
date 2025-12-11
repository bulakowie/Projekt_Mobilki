package com.example.projekt_mobilki.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.projekt_mobilki.Przepis
import com.example.projekt_mobilki.aktywneKomunikaty
import com.example.projekt_mobilki.aktywnePrzepis
import com.example.projekt_mobilki.kategoria
import com.example.projekt_mobilki.komunikaty
import com.example.projekt_mobilki.komunikatyDoPrzepisow
import com.example.projekt_mobilki.skladnik
import com.example.projekt_mobilki.zrobionePrzepisy

@Database(entities = [Przepis::class, kategoria::class, skladnik::class, aktywnePrzepis::class, komunikaty::class, komunikatyDoPrzepisow::class, aktywneKomunikaty::class,zrobionePrzepisy::class],
    version = 12, exportSchema = false)
abstract class PrzepisDatabase : RoomDatabase() {
    abstract fun getPrzepisDao(): PrzepisDao
    abstract fun getAktywnePrzepisDao(): AktywnePrzepisyDao
    companion object {
        @Volatile
        private var INSTANCE: PrzepisDatabase? = null

        fun getDatabase(context: Context): PrzepisDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PrzepisDatabase::class.java,
                    "przepisy_database"
                ).fallbackToDestructiveMigration(true)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}