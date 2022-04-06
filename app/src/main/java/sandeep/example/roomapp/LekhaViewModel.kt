package sandeep.example.roomapp

import android.app.Application
import android.provider.Settings
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.*


class LekhaViewModel(application: Application) : AndroidViewModel(application) {

    val allItems:LiveData<List<LekhaItem>>
    private val repository:LekhaRepository
    init {
        val dao = LekhaDatabase.getDatabase(application).getLekhaDao()
        repository = LekhaRepository(dao)
        allItems = repository.allItems
    }

    fun delete(item:LekhaItem) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(item)
    }
    fun insert(item: LekhaItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(item)
    }



}