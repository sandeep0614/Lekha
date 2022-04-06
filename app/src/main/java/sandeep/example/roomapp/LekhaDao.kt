package sandeep.example.roomapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LekhaDao {

    @Insert
    suspend fun insert(item:LekhaItem)

    @Delete
    suspend fun delete(item:LekhaItem)

    @Query("SELECT * FROM lekha_table ORDER by id ASC")
    fun getAllItems():LiveData<List<LekhaItem>>



}