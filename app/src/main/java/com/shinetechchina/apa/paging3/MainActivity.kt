package com.shinetechchina.apa.paging3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.linq.kitchen.base.adapter.ReposLoadStateAdapter

class MainActivity : AppCompatActivity() {
    val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val personAdapter = PersonAdapter {

        }


        val footerAdapter = ReposLoadStateAdapter {
            viewModel.loadMore()
            personAdapter.retry()
        }

       val adapter = personAdapter.withLoadStateFooter(footerAdapter)


        recyclerView.adapter = adapter


        viewModel.items.observe(this) {

            personAdapter.submitData(this.lifecycle,it)
        }

    }
}