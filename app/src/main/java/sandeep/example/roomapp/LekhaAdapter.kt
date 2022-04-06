package sandeep.example.roomapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LekhaAdapter(val context: Context,var list :List<LekhaItem>,val lekhaItemClickInterface: LekhaItemClickInterface):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_C = 1
    val ITEM_B = 2
    val ITEM_L = 3

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if(viewType == 1){

            val view: View = LayoutInflater.from(context).inflate(R.layout.cleared_view,parent,false)
            return ClearViewHolder(view)

        }
        else if(viewType == 2){

            val view:View = LayoutInflater.from(context).inflate(R.layout.borrow_view,parent,false)
            return BorrowViewHolder(view)

        }
        else{

            val view:View = LayoutInflater.from(context).inflate(R.layout.lend_view,parent,false)
            return LendViewHolder(view)

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val currItem = list[position]

        if(holder.javaClass == ClearViewHolder::class.java){
             val viewHolder = holder as ClearViewHolder
            holder.Cname.text = currItem.name
            holder.Camount.text = currItem.amount.toString() + "Rs."
            holder.Ctype.text = currItem.type
            holder.Cdate.text = currItem.date
            holder.Cdlt.setOnClickListener {
                lekhaItemClickInterface.onItemClicked(currItem)
            }
        }
        else if(holder.javaClass == BorrowViewHolder::class.java){
            val viewHolder = holder as BorrowViewHolder
            holder.Bname.text = currItem.name
            holder.Bamount.text = currItem.amount.toString() + " Rs."
            holder.Btype.text = currItem.type
            holder.Bdate.text = currItem.date
            holder.Bdlt.setOnClickListener {
                lekhaItemClickInterface.onItemClicked(currItem)
            }
        }
        else{
                val viewHolder = holder as LendViewHolder
                holder.Lname.text = currItem.name
                holder.Lamount.text = currItem.amount.toString() + " Rs."
                holder.Ltype.text = currItem.type
                holder.Ldate.text = currItem.date
            holder.Ldlt.setOnClickListener {
                lekhaItemClickInterface.onItemClicked(currItem)
            }

        }
    }

    override fun getItemViewType(position: Int): Int {

        val currItem = list[position]
        if(currItem.type == "Cleared")
        {
            return ITEM_C
        }
        else if(currItem.type == "Borrowed")
        {
            return ITEM_B
        }
        else
        {
            return ITEM_L
        }
    }

    override fun getItemCount(): Int {

        return list.size

    }
    class ClearViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        val Cname = itemView.findViewById<TextView>(R.id.cName)
        val Camount = itemView.findViewById<TextView>(R.id.cAmount)
        val Ctype = itemView.findViewById<TextView>(R.id.cType)
        val Cdate = itemView.findViewById<TextView>(R.id.cDate)
        val Cdlt = itemView.findViewById<ImageView>(R.id.cDlt)
    }
    class BorrowViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        val Bname = itemView.findViewById<TextView>(R.id.bName)
        val Bamount = itemView.findViewById<TextView>(R.id.bAmount)
        val Btype = itemView.findViewById<TextView>(R.id.bType)
        val Bdate = itemView.findViewById<TextView>(R.id.bDate)
        val Bdlt = itemView.findViewById<ImageView>(R.id.bDlt)
    }
    class LendViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        val Lname = itemView.findViewById<TextView>(R.id.lName)
        val Lamount = itemView.findViewById<TextView>(R.id.lAmount)
        val Ltype = itemView.findViewById<TextView>(R.id.lType)
        val Ldate = itemView.findViewById<TextView>(R.id.lDate)
        val Ldlt = itemView.findViewById<ImageView>(R.id.lDlt)
    }
    interface LekhaItemClickInterface{
        fun onItemClicked(lekhaItem:LekhaItem)
    }

}