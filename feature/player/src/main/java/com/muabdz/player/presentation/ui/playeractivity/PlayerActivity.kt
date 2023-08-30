package com.muabdz.player.presentation.ui.playeractivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.muabdz.player.databinding.ActivityPlayerBinding
import com.muabdz.player.presentation.ui.playerfragment.PlayerFragment

class PlayerActivity : AppCompatActivity() {
    private val binding : ActivityPlayerBinding by lazy {
        ActivityPlayerBinding.inflate(layoutInflater)
    }

    private val videoUrl: String? by lazy {
        intent?.extras?.getString(EXTRA_VIDEO_URL)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        videoUrl?.let {
            supportFragmentManager.beginTransaction().apply {
                replace(binding.container.id, PlayerFragment.newInstance(it))
            }.commit()
        }
    }
    companion object {
        private const val EXTRA_VIDEO_URL = "EXTRA_VIDEO_URL"
        fun createIntent(context: Context, videoUrl: String): Intent {
            return Intent(context, PlayerActivity::class.java).apply {
                putExtra(EXTRA_VIDEO_URL, videoUrl)
            }
        }
    }
}