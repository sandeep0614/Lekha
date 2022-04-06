package sandeep.example.roomapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LekhaItem::class],version = 1)
abstract class LekhaDatabase: RoomDatabase(){

    abstract fun getLekhaDao():LekhaDao

    companion object{
       @Volatile
        private var instance : LekhaDatabase? = null



        fun getDatabase(context: Context):LekhaDatabase{
            return instance?: synchronized(this){
                val INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    LekhaDatabase::class.java,
                    "lekha.db"
                ).build()
                instance = INSTANCE
                INSTANCE
            }
        }

    }
}