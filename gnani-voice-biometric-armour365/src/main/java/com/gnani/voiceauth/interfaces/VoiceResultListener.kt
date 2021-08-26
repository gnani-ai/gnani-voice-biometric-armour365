package com.gnani.voiceauth.interfaces

interface VoiceResultListener {
    fun success(message: String)
    fun failed(message: String)
}