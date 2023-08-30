package com.muabdz.netfix.router

import androidx.fragment.app.Fragment
import com.muabdz.player.presentation.ui.playerfragment.PlayerFragment
import com.muabdz.shared.router.FragmentRouter

class FragmentRouterImpl: FragmentRouter {
    override fun createPlayerFragment(videoUrl: String): Fragment {
        return PlayerFragment.newInstance(videoUrl)
    }
}