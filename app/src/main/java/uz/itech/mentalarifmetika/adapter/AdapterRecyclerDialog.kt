package uz.itech.mentalarifmetika.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.itech.mentalarifmetika.databinding.ItemDialogRecyclerviewBinding

class AdapterRecyclerDialog(val items:ArrayList<Int>): RecyclerView.Adapter<AdapterRecyclerDialog.ItemHolder>() {

    inner class ItemHolder(val binding:ItemDialogRecyclerviewBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
       return ItemHolder(ItemDialogRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]
        var i = position+1
        holder.binding.tvidtitle.text="$i - misol"
        holder.binding.tvValues.text=item.toString()
    }

    override fun getItemCount(): Int = items.count()
}