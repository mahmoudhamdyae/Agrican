package com.example.agrican.ui.screens.home.main.ask_expert.record

import java.io.File

interface AudioRecorder {
    fun start(outputFile: File)
    fun stop()
}