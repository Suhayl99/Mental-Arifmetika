package uz.itech.mentalarifmetika.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.itech.mentalarifmetika.databinding.ItemRecyclerviewrowBinding

class AdapterRow (val itemList:ArrayList<Int>):RecyclerView.Adapter<AdapterRow.ItemHolder>(){

    inner class ItemHolder(val binding:ItemRecyclerviewrowBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(ItemRecyclerviewrowBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
       val item = itemList[position]
        holder.binding.tvmisol.text=item.toString()
    }

    override fun getItemCount(): Int = itemList.count()

}