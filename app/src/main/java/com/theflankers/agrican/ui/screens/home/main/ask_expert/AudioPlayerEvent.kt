package com.theflankers.agrican.ui.screens.home.main.ask_expert

import android.content.Context
import android.net.Uri

sealed class AudioPlayerEvent{

     data class InitAudio(
         val audio: Uri,
         val context: Context,
         val onAudioInitialized: () -> Unit
     ): AudioPlayerEvent()

    data class Seek(val position: Float): AudioPlayerEvent()

    object Play: AudioPlayerEvent()

    object Pause: AudioPlayerEvent()

    object Stop: AudioPlayerEvent()
}
