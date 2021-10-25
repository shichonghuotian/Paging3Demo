package com.linq.kitchen.base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView

import com.shinetechchina.apa.paging3.LoadMoreException
import com.shinetechchina.apa.paging3.R
import com.shinetechchina.apa.paging3.databinding.ItemLoadMoreBinding

/**
 * Created by Arthur on 2020/10/24.
 */
class ReposLoadStateViewHolder (private val binding: ItemLoadMoreBinding, retry:()->Unit): RecyclerView
.ViewHolder(binding.root) {


    init {
        binding.button.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {

        //可以处理一下其他情况, 根据error不同， 处理一下不同的事件

        binding.button.visibility = toVisibility(false)

        if (loadState is LoadState.Error) {

            if(loadState.error is LoadMoreException) {
                binding.button.visibility = toVisibility(true)


            }
        }
        binding.progressBar.visibility = toVisibility(loadState is LoadState.Loading)
//        binding.retryButton.visibility = toVisibility(loadState !is LoadState.Loading)
//        binding.errorMsg.visibility = toVisibility(loadState !is LoadState.Loading)
    }

    fun toVisibility(show: Boolean) : Int{


        if(show) {
            return View.VISIBLE
        }else {
            return View.GONE
        }
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): ReposLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_load_more, parent, false)
            val binding = ItemLoadMoreBinding.bind(view)
            return ReposLoadStateViewHolder(
                binding,
                retry
            )
        }
    }
}