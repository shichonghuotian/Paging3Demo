package com.shinetechchina.apa.paging3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shinetechchina.apa.paging3.databinding.ItemLoadMoreBinding
import com.shinetechchina.apa.paging3.databinding.ItemPersonBinding

/**
 * Created by Arthur on 2021/10/25.
 */
class BoyAdapter() : ListAdapter<Boy,
        BoyAdapter.ItemViewHolder>
    (BoyComparator){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
      return   ItemViewHolder(
          ItemPersonBinding.inflate(
              LayoutInflater.from(parent.context),
              parent,
              false
          )
      )

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val item = getItem(position)

        holder.binding.title.text = item.name

    }




    class ItemViewHolder(val binding: ItemPersonBinding) :
        RecyclerView.ViewHolder(binding.root)

}


object BoyComparator : DiffUtil.ItemCallback<Boy>() {
    override fun areItemsTheSame(
        oldItem: Boy,
        newItem: Boy
    ): Boolean {
        return  oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: Boy,
        newItem: Boy
    ) = oldItem == newItem
}
