package com.shinetechchina.apa.paging3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shinetechchina.apa.paging3.databinding.ItemDotBinding
import com.shinetechchina.apa.paging3.databinding.ItemLoadMoreBinding
import com.shinetechchina.apa.paging3.databinding.ItemPersonBinding

/**
 * Created by Arthur on 2021/10/25.
 */
class BoyDotAdapter() : ListAdapter<Boy,
        BoyDotAdapter.ItemViewHolder>
    (BoyComparator){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
      return   ItemViewHolder(
          ItemDotBinding.inflate(
              LayoutInflater.from(parent.context),
              parent,
              false
          )
      )

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val item = getItem(position)

    }




    class ItemViewHolder(val binding: ItemDotBinding) :
        RecyclerView.ViewHolder(binding.root)

}
