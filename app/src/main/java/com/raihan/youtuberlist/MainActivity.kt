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
    private lateinit var rvYoutubers: RecyclerView
    private val list = ArrayList<Youtuber>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        rvYoutubers = findViewById(R.id.rv_youtubers)
        rvYoutubers.setHasFixedSize(true)

        list.addAll(getListYoutubers())
        showRecyclerList()
    }

    private fun getListYoutubers(): ArrayList<Youtuber> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataBorn = resources.getIntArray(R.array.data_yt_born)
        val dataSubscriber = resources.getStringArray(R.array.data_yt_subs)
        val dataGenre = resources.getStringArray(R.array.data_yt_genre)
        val listYoutuber = ArrayList<Youtuber>()
        for (i in dataName.indices) {
            val youtuber = Youtuber(dataName[i], dataDescription[i], dataPhoto[i], dataBorn[i], dataSubscriber[i], dataGenre[i])
            listYoutuber.add(youtuber)
        }
        return listYoutuber
    }

    private fun showRecyclerList() {
        rvYoutubers.layoutManager = LinearLayoutManager(this)
        val listYoutuberAdapter = ListYoutuberAdapter(list)
        rvYoutubers.adapter = listYoutuberAdapter

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvYoutubers.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvYoutubers.layoutManager = GridLayoutManager(this, 2)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
