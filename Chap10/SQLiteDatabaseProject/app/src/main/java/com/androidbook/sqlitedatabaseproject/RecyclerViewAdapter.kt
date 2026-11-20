package com.androidbook.sqlitedatabaseproject
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(val context:Context, val dataList: ArrayList<HouseKeeping>)
    :RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>(){

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val RcSeq : TextView= itemView.findViewById(R.id.RcSeq)
        private val RcCategory: TextView = itemView.findViewById(R.id.RcCategory)
        private val Rcpurpose: TextView = itemView.findViewById(R.id.Rcpurpose)
        private val RcMoney: TextView = itemView.findViewById(R.id.RcMoney)
        private val RcMemo : TextView= itemView.findViewById(R.id.RcMemo)
        private val RcDate: TextView = itemView.findViewById(R.id.RCdate)

        fun bind(dataVo: HouseKeeping,context: Context){

            RcSeq.text = dataVo.seq.toString()
            RcCategory.text = dataVo.category
            Rcpurpose.text =dataVo.purpose
            RcMoney.text = dataVo.money.toString()
            RcMemo.text = dataVo.memo
            RcDate.text = dataVo.date

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.view_item_layout,parent, false)
        return ItemViewHolder(view)

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataList[position], context)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}
