package com.raihan.youtuberlist

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.raihan.youtuberlist.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var dataYoutuber: Youtuber

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        dataYoutuber = getYoutuberFromIntent()

        bindDataToViews()

        binding.actionShare.setOnClickListener { shareYoutuberData() }
    }

    private fun getYoutuberFromIntent(): Youtuber {
        return if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("key_youtuber", Youtuber::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("key_youtuber")
        } ?: throw IllegalStateException("Youtuber data not found in intent")
    }

    private fun bindDataToViews() {
        with(binding) {
            tvName.text = dataYoutuber.name
            tvDesc.text = dataYoutuber.description
            tvBorn.text = dataYoutuber.born.toString()
            tvSubscriber.text = dataYoutuber.subscriber
            tvGenre.text = dataYoutuber.genre

            Glide.with(this@DetailActivity)
                .load(dataYoutuber.photo)
                .into(photo)
        }
    }

    private fun shareYoutuberData() {
        val shareText = """
            Check out this YouTuber:
            Name: ${dataYoutuber.name}
            Description: ${dataYoutuber.description}
            Born: ${dataYoutuber.born}
            Subscribers: ${dataYoutuber.subscriber}
            Genre: ${dataYoutuber.genre}
        """.trimIndent()

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}
