package com.muabdz.shared.router

import androidx.fragment.app.Fragment

interface FragmentRouter {
    fun createPlayerFragment(videoUrl: String): Fragment
}