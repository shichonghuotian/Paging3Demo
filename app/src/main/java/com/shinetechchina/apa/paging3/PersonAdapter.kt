package com.shinetechchina.apa.paging3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shinetechchina.apa.paging3.databinding.ItemLoadMoreBinding
import com.shinetechchina.apa.paging3.databinding.ItemPersonBinding

/**
 * Created by Arthur on 2021/10/25.
 */
class PersonAdapter(val loadMoreAction: ((PersonAdapter) -> Unit)?) : PagingDataAdapter<PersonUiModel,
        RecyclerView
.ViewHolder>
    (UiModelComparator){

    override fun getItemViewType(position: Int): Int {

        return when(getItem(position)) {
            is PersonUiModel.PersonModel ->  R.layout.item_person
            is PersonUiModel.LoadMoreModel ->  R.layout.item_load_more
            else -> throw IllegalStateException("Unknown view")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {

            R.layout.item_person -> {
                ItemViewHolder(
                    ItemPersonBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            R.layout.item_load_more -> {
                LoadMoreViewHolder(
                    ItemLoadMoreBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

            }
            else -> throw IllegalStateException("Unknown view")
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val item = getItem(position)

        when(holder) {
            is ItemViewHolder ->  {
                item as PersonUiModel.PersonModel
                holder.binding.title.text = item.title

            }
            is LoadMoreViewHolder ->  {

                holder.binding.button.setOnClickListener {

                    loadMoreAction?.invoke(this)
                }

            }
        }

    }




    class ItemViewHolder(val binding: ItemPersonBinding) :
        RecyclerView.ViewHolder(binding.root)

    class LoadMoreViewHolder(val binding: ItemLoadMoreBinding) :
        RecyclerView.ViewHolder(binding.root)
}