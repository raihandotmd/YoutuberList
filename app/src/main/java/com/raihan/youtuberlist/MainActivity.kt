package com.raihan.youtuberlist

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var listYoutuberAdapter: ListYoutuberAdapter
    private lateinit var rvYoutubers: RecyclerView
    private val list = ArrayList<Youtuber>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        rvYoutubers = findViewById(R.id.rv_youtubers)
        rvYoutubers.setHasFixedSize(true)

        list.addAll(getListYoutubers())

        listYoutuberAdapter = ListYoutuberAdapter(list)
        rvYoutubers.adapter = listYoutuberAdapter
        showRecyclerList()
    }


    private fun getListYoutubers(): ArrayList<Youtuber> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataSummary = resources.getStringArray(R.array.data_summary)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataBorn = resources.getIntArray(R.array.data_yt_born)
        val dataSubscriber = resources.getStringArray(R.array.data_yt_subs)
        val dataGenre = resources.getStringArray(R.array.data_yt_genre)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val listYoutuber = ArrayList<Youtuber>()
        for (i in dataName.indices) {
            val youtuber = Youtuber(dataName[i], dataSummary[i], dataPhoto[i], dataBorn[i], dataSubscriber[i], dataGenre[i], dataDescription[i])
            listYoutuber.add(youtuber)
        }
        return listYoutuber
    }

    private fun showRecyclerList() {
        rvYoutubers.layoutManager = LinearLayoutManager(this)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.let {
            menuInflater.inflate(R.menu.menu_main, it)
        }
        return super.onCreateOptionsMenu(menu)
    }

    private val linearLayoutManager = LinearLayoutManager(this)
    private val gridLayoutManager = GridLayoutManager(this, 2)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> rvYoutubers.layoutManager = linearLayoutManager
            R.id.action_grid -> rvYoutubers.layoutManager = gridLayoutManager
        }
        return super.onOptionsItemSelected(item)
    }
}
