package com.eihror.infinitescroll

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import java.util.stream.Collectors
import java.util.stream.IntStream.range

class MainActivity : AppCompatActivity() {

    private var list: MutableList<Int> = mutableListOf()
    private var lastItem: Int = 0
    private val listAdapter: NumberListAdapter = NumberListAdapter(::onBottomListener)
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view_list)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = listAdapter

        updateList()
    }

    private fun onBottomListener() {
        Handler().postDelayed({ updateList() }, 2000)
    }

    private fun updateList() {
        generateMore()
        listAdapter.setList(list)
    }

    private fun generateMore() {
        val next = lastItem + 20
        val range = range(lastItem, next).boxed().collect(Collectors.toList())
        lastItem = next
        list.addAll(range)
    }


}
