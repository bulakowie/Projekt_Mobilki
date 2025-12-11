package com.example.projekt_mobilki


import androidx.lifecycle.LiveData
import androidx.lifecycle.switchMap
import com.example.projekt_mobilki.db.AktywnePrzepisyDao
import com.example.projekt_mobilki.db.PrzepisDao
import com.example.projekt_mobilki.aktywnePrzepis
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PrzepisRepository(private val przepisDao: PrzepisDao, private val A_PrzepisDao  : AktywnePrzepisyDao) {
    val allPrzepisy: LiveData<List<Przepis>> = przepisDao.getAll()
    val allAktywnePrzepis : LiveData<List<aktywnePrzepis>> = A_PrzepisDao.getAll()
    suspend fun insert(przepis: Przepis) {
        przepisDao.insert(przepis)
    }

    fun allPrzepisyKtoreSaAktywne(
        aktywne: LiveData<List<aktywnePrzepis>>
    ): LiveData<List<Przepis>> {

        return aktywne.switchMap { aktywneList ->
            val ids = aktywneList.map { it.idPrzepisu }
            A_PrzepisDao.getPrzepisyByIds(ids)
        }
    }

    suspend fun update(przepis: Przepis) {
        przepisDao.update(przepis)
    }
    suspend fun deleteById_A(id: Int){
        A_PrzepisDao.deleteById(id);
    }
    suspend fun czyJestAktywny(id: Int) = A_PrzepisDao.existsByIdPrzepisu(id)


    fun getById(id: Int): LiveData<Przepis?>
    {
        return przepisDao.getById(id)
    }
    suspend fun delete(przepis: Przepis) {
        przepisDao.delete(przepis)
    }
     fun getAllKategoria(): LiveData<List<kategoria>>
    {
        return przepisDao.getAllKategoria()
    }
     fun getAllSkladnik(): LiveData<List<skladnik>>
    {
        return przepisDao.getAllSkladnik()
    }
    fun getByKategoria(a :Int): LiveData<List<Przepis>>
    {
        return przepisDao.getByKategoria(a)
    }
    fun getBySkladniki(a :Int): LiveData<List<Przepis>>
    {
        return przepisDao.getBySkladnik(a)
    }
    suspend fun dodajAktywnyPrzepis(id: Int) = withContext(Dispatchers.IO){
        val nowy = aktywnePrzepis(idPrzepisu = id)
        A_PrzepisDao.insert(nowy)
    }
}
