package sandeep.example.roomapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() ,LekhaAdapter.LekhaItemClickInterface{

    private var TAG = "MainActivity"
    lateinit var recyclerView:RecyclerView
    lateinit var addBtn:FloatingActionButton
    lateinit var list:List<LekhaItem>
    lateinit var lekhaAdapter:LekhaAdapter
    lateinit var lekhaViewModel:LekhaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.Rclr)
        addBtn = findViewById(R.id.insertBtn)
        list = ArrayList<LekhaItem>()
        lekhaAdapter = LekhaAdapter(this,list,this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = lekhaAdapter
        lekhaViewModel = ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(LekhaViewModel::class.java)
        lekhaViewModel.allItems.observe(this, Observer {
            lekhaAdapter.list = it
            lekhaAdapter.notifyDataSetChanged()
        })

        addBtn.setOnClickListener {
            startDialog()
        }


    }
    fun startDialog(){
        Log.d(TAG,"startDialog:started")
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.add_view)

        val cancelBtn = dialog.findViewById<Button>(R.id.CancelBtn)
        val okBtn = dialog.findViewById<Button>(R.id.okBtn)
        val itemName = dialog.findViewById<EditText>(R.id.EditName)
        val itemAmount = dialog.findViewById<EditText>(R.id.EditAmount)
        val radioGrp = dialog.findViewById<RadioGroup>(R.id.RadioGrp)
        var itemtype = ""
       radioGrp.setOnCheckedChangeListener { group, i ->
           when(i){
               R.id.radioButton1 -> itemtype = "Cleared"
               R.id.radioButton2 -> itemtype = "Borrowed"
               R.id.radioButton3 -> itemtype = "Lend"

           }
       }

        okBtn.setOnClickListener {
            val name = itemName.text.toString()
            val SAmount = itemAmount.text.toString()
            val Amount = SAmount.toInt()
            val type =itemtype

            val currDate = Calendar.getInstance().getTime()
            val date = currDate.toString()
            if(name.isNotEmpty() && SAmount.isNotEmpty() && type.isNotEmpty()){
                val item = LekhaItem(name,Amount,type,date)
                lekhaViewModel.insert(item)
                Toast.makeText(application,"Inserted",Toast.LENGTH_SHORT).show()
                lekhaAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            else
            {
                Toast.makeText(application,"Please enter all the data",Toast.LENGTH_SHORT).show()
            }

        }

        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()

    }

    override fun onItemClicked(lekhaItem: LekhaItem) {
        lekhaViewModel.delete(lekhaItem)
        lekhaAdapter.notifyDataSetChanged()
        Toast.makeText(applicationContext,"Item Deleted",Toast.LENGTH_SHORT).show()
    }
}