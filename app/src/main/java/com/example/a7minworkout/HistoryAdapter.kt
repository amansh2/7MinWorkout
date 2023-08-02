package com.example.a7minworkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minworkout.Data.HistoryEntity
import com.example.a7minworkout.databinding.ActivityHistoryBinding
import com.example.a7minworkout.databinding.HistoryItemBinding

class HistoryAdapter(val list: ArrayList<HistoryEntity>):RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    class HistoryViewHolder(val binding: HistoryItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(HistoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.binding.tvPosition.text=(position+1).toString()
        holder.binding.tvItem.text=list[position].date
        if(position%2!=0){
            holder.binding.llHistoryItemMain.setBackgroundResource(R.color.white)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}