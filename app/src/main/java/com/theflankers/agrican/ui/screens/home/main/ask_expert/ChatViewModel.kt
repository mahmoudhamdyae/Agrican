package com.theflankers.agrican.ui.screens.home.main.ask_expert

import android.content.Context
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import com.theflankers.agrican.common.utils.audio.VisualizerData
import com.theflankers.agrican.common.utils.audio.VisualizerHelper
import com.theflankers.agrican.domain.model.AudioFile
import com.theflankers.agrican.domain.model.Message
import com.theflankers.agrican.domain.model.MessageType
import com.theflankers.agrican.domain.use_case.BaseUseCase
import com.theflankers.agrican.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val useCase: BaseUseCase
): BaseViewModel() {

    private var _player: MediaPlayer? = null
    private var _visualizer = VisualizerHelper()

    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState = _uiState.asStateFlow()

    private val _visualizerData = MutableStateFlow(VisualizerData())
    val visualizerData = _visualizerData.asStateFlow()

    private val _handler = Handler(Looper.getMainLooper())

    init {
        launchCatching {
            useCase.getChatUseCase().collect {
                _uiState.value = _uiState.value.copy(
                    currentUser = useCase.getCurrentUserUseCase(),
                    messages = it
                )
            }
        }
    }

    fun onEvent(event: AudioPlayerEvent) {
        when (event) {

            is AudioPlayerEvent.InitAudio -> initAudio(
                audioFile = event.audio,
                context = event.context,
                onAudioInitialized = event.onAudioInitialized
            )

            AudioPlayerEvent.Pause -> pause()

            AudioPlayerEvent.Play -> play()

            AudioPlayerEvent.Stop -> stop()
        }
    }

    private fun initAudio(audioFile: AudioFile, context: Context, onAudioInitialized: () -> Unit) {
        launchCatching {

//            _uiState.value = _uiState.value.copy(selectedAudio = AudioFile(audio))

            _player = MediaPlayer().apply {
                setDataSource(context, audioFile.file)
                prepare()
                _uiState.value = _uiState.value.copy(selectedAudio = audioFile)
            }

            _player?.setOnCompletionListener {
                pause()
            }

            _player?.setOnPreparedListener {
                onAudioInitialized()
            }
        }
    }

    private fun play() {
        _uiState.value = _uiState.value.copy(isPlaying = true)
        _player?.start()
        _player?.run {
            _visualizer.start(
                audioSessionId = audioSessionId,
                onData = { data ->
                    _visualizerData.value = data
                }
            )
        }
        _handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    _uiState.value = _uiState.value.copy(currentTime = _player!!.currentPosition)
                    _handler.postDelayed(this, 1000)
                } catch (exp: Exception) {
                    _uiState.value = _uiState.value.copy(currentTime = 0)
                }
            }

        }, 0)
    }

    private fun stop() {
        _visualizer.stop()
        _player?.apply {
            stop()
            reset()
            release()
        }
        _player = null
        _uiState.value = _uiState.value.copy(currentTime = 0, isPlaying = false)
    }

    private fun pause() {
        _visualizer.stop()
        _player?.pause()
        _uiState.value = _uiState.value.copy(isPlaying = false)
    }

    fun sendMessage(
        messageBody: String? = null,
        image: String? = null,
        audioFile: AudioFile = AudioFile(),
        messageType: MessageType
    ) {
        val message = Message(
            body = messageBody,
            userId = _uiState.value.currentUser.userId,
            image = image,
            audioFile = audioFile,
            type = messageType,
        )
        launchCatching {
            useCase.sendMessageUseCase(message)
        }
    }
}