package sandeep.example.roomapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.sql.Time

@Entity(tableName = "lekha_table")
data class LekhaItem(
    @ColumnInfo(name = "Name") var name:String,
    @ColumnInfo(name = "Amount") var amount:Int,
    @ColumnInfo(name = "Type") var type:String,
    @ColumnInfo(name = "Date") var date:String){

    @PrimaryKey(autoGenerate = true)
    var id:Int = 0

}
