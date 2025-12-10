package com.example.projekt_mobilki

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.util.query
import com.example.projekt_mobilki.db.AktywnePrzepisyDao
import com.example.projekt_mobilki.db.PrzepisDatabase
import kotlinx.coroutines.launch

class PrzepisViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PrzepisRepository

    val allPrzepisy: LiveData<List<Przepis>>
    init {
        val przepisDao = PrzepisDatabase.getDatabase(application).getPrzepisDao()
        val AktywnePrzepisyDao = PrzepisDatabase.getDatabase(application).getAktywnePrzepisDao()
        repository = PrzepisRepository(przepisDao, AktywnePrzepisyDao)
        allPrzepisy = repository.allPrzepisy
    }

    fun insert(przepis: Przepis) = viewModelScope.launch {
        repository.insert(przepis)
    }
    fun ustawNaZrobiony(id : Int)
    {

    }


    fun delete(przepis: Przepis) = viewModelScope.launch {
        repository.delete(przepis)
    }

    fun update(przepis: Przepis) = viewModelScope.launch {
        repository.update(przepis)
    }
    fun usunZActiveListy(id :Int)= viewModelScope.launch {
        repository.deleteById_A(id)
    }

     fun getById(id: Int): LiveData<Przepis?> {
        return repository.getById(id)
    }

        fun GetByKategorie(id: Int): LiveData<List<Przepis>> {
           return repository.getByKategoria(id)
        }

        fun GetBySkladniki(id: Int): LiveData<List<Przepis>> {
            return repository.getBySkladniki(id)
        }
    fun dodajAktywnyPrzepis(id: Int) {
        viewModelScope.launch {
            repository.dodajAktywnyPrzepis(id)
        }
    }


    fun allKategorie(): LiveData<List<kategoria>>
    {
        return repository.getAllKategoria()
    }

    fun allSkladniki():LiveData<List<skladnik>>
    {
        return repository.getAllSkladnik()
    }
}