package com.muabdz.player.manager.player

import androidx.lifecycle.DefaultLifecycleObserver

interface PlayerManager: DefaultLifecycleObserver {
    fun play(videoUrl: String)
}
