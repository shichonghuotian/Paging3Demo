package com.shinetechchina.apa.paging3

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationSet
import android.widget.Button
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.linq.kitchen.base.adapter.ReposLoadStateAdapter

class MainActivity : AppCompatActivity() {
    val viewModel by viewModels<MainViewModel>()

    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val button = findViewById<Button>(R.id.button)

        recyclerView.layoutManager = LinearLayoutManager(this)
//        val personAdapter = PersonAdapter {
//
//        }
//
//
//        val footerAdapter = ReposLoadStateAdapter {
//            viewModel.loadMore()
//            personAdapter.retry()
//        }
//
//       val adapter = personAdapter.withLoadStateFooter(footerAdapter)
//
//
//        recyclerView.adapter = adapter
//
//
//        viewModel.items.observe(this) {
//
//            personAdapter.submitData(this.lifecycle,it)
//        }

        val boyAdapter = BoyAdapter()

        val boyDotAdapter = BoyDotAdapter()

        recyclerView.adapter = boyAdapter

        viewModel.listData.observe(this) {

            boyAdapter.submitList(it)
            boyDotAdapter.submitList(it)
        }

        button.setOnClickListener {

            if (recyclerView.adapter == boyAdapter) {
                recyclerView.adapter = boyDotAdapter

                startAnim(false)


            }else {

                recyclerView.adapter = boyAdapter
                startAnim(true)

            }



        }


    }


    fun startAnim(full: Boolean) {

        recyclerView.pivotX = 0f

        val scalx =  if (!full) {
            ObjectAnimator.ofFloat(recyclerView, View.SCALE_X,1f, 0.1f )


        }else {
              ObjectAnimator.ofFloat(recyclerView, View.SCALE_X,0.1f, 1f )


        }


        val scaly = if (!full) {
            ObjectAnimator.ofFloat(recyclerView, View.SCALE_Y,1f, 0.2f )



        }else {
            ObjectAnimator.ofFloat(recyclerView, View.SCALE_Y,0.2f, 1f )

        }


        val set = AnimatorSet()
        set.duration = 400

        set.playTogether(scalx,scaly)
        set.start()


    }
}


val Float.dp get() = (this * Resources.getSystem().displayMetrics.density + 0.5f)
val Int.dp: Int get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

