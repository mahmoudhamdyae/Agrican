package com.theflankers.agrican.ui.screens.home.main.ask_expert

import android.content.Context
import com.theflankers.agrican.domain.model.AudioFile

sealed class AudioPlayerEvent{

     data class InitAudio(
         val audio: AudioFile,
         val context: Context,
         val onAudioInitialized: () -> Unit
     ): AudioPlayerEvent()

    object Play: AudioPlayerEvent()

    object Pause: AudioPlayerEvent()

    object Stop: AudioPlayerEvent()
}
