package uz.itech.mentalarifmetika.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.NonDisposableHandle.parent
import uz.itech.mentalarifmetika.databinding.ItemRecyclerviewBinding
import uz.itech.mentalarifmetika.model.Item
import uz.itech.mentalarifmetika.sceen.AbacusActivity


class Adapter(val context:Context,val itemList:ArrayList<Item>, val natija :ArrayList<Int> ):RecyclerView.Adapter<Adapter.ItemHolder>() {
lateinit var viewPool:RecyclerView.RecycledViewPool
inner class ItemHolder(val binding: ItemRecyclerviewBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
     return ItemHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item=itemList[position]
    holder.binding.tvID.text=item.itemId.toString()
        holder.binding.recyclerviewRow.layoutManager=LinearLayoutManager(context)
        holder.binding.recyclerviewRow.adapter=AdapterRow(item.itemList)
        natija.add(item.itemList.sum())
    }

    override fun getItemCount():Int=itemList.count()


}