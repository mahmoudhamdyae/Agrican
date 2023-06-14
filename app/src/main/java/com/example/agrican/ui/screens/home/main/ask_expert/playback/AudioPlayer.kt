package com.example.agrican.ui.screens.home.main.ask_expert.playback

import java.io.File

interface AudioPlayer {
    fun playFile(file: File)
    fun stop()
}