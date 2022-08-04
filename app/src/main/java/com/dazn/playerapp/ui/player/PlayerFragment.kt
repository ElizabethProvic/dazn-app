package com.dazn.playerapp.ui.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.dazn.playerapp.databinding.PlayerFragmentBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerView

class PlayerFragment : Fragment(), Player.Listener {

    private var _binding: PlayerFragmentBinding? = null

    private lateinit var player: ExoPlayer
    private lateinit var playerView: PlayerView

    private val binding get() = _binding!!

    private var videoId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = PlayerFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        videoId = this.arguments?.get(ARG_VIDEO_ID).toString()

        player = context?.let { ExoPlayer.Builder(it).build() }!!
        playerView = binding.videoView
        playerView.player = player
        player.addListener(this)

        addVideo()
    }

    private fun addVideo() {
        videoId?.let {
            val mediaItem = MediaItem.fromUri(it)
            player.addMediaItem(mediaItem)
            player.prepare()
            player.play()
        }
    }

    override fun onPlaybackStateChanged(playbackState: Int) {
        super.onPlaybackStateChanged(playbackState)

        when(playbackState) {
            Player.STATE_BUFFERING -> {
                binding.loader.isVisible = true
            }
            Player.STATE_READY -> {
                binding.loader.isVisible = false
            }
        }
    }

    override fun onDestroyView() {
        player.release()
        _binding = null
        super.onDestroyView()
    }

    companion object {

        const val ARG_VIDEO_ID = "com.playerapp.events.video.id"

        fun newInstance(videoId: String?) = bundleOf(
            ARG_VIDEO_ID to videoId
        )
    }
}