package sandeep.example.roomapp

import androidx.lifecycle.LiveData

class LekhaRepository(private val lekhaDao:LekhaDao) {

    val allItems:LiveData<List<LekhaItem>> = lekhaDao.getAllItems()

    suspend fun insert(item:LekhaItem){
       lekhaDao.insert(item)
    }
    suspend fun delete(item:LekhaItem){
        lekhaDao.delete(item)
    }



}