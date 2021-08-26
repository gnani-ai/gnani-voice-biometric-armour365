package com.gnani.voiceauthentication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import cafe.adriel.androidaudiorecorder.AndroidAudioRecorder
import cafe.adriel.androidaudiorecorder.model.AudioChannel
import cafe.adriel.androidaudiorecorder.model.AudioSampleRate
import cafe.adriel.androidaudiorecorder.model.AudioSource
import com.gnani.voiceauth.GnaniClient
import com.gnani.voiceauth.interfaces.VoiceResultListener
import java.io.File

class MainActivity : AppCompatActivity(), VoiceResultListener {

    private val TAG = MainActivity::class.java.simpleName
    private lateinit var gnaniClient: GnaniClient
    private val requestCode = 123
    private var filePath: String? = null
    private lateinit var btnAuth : Button
    private lateinit var btnEnroll : Button
    private lateinit var btnDisEnroll : Button
    private var MODE: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAuth = findViewById(R.id.btnAuth)
        btnEnroll = findViewById(R.id.btnEnroll)
        btnDisEnroll = findViewById(R.id.btnDisEnroll)

        gnaniClient = GnaniClient("", "", "", this)

        btnAuth.setOnClickListener {
            MODE = 1
            recordAudio()
        }
        btnEnroll.setOnClickListener {
            MODE = 2
            recordAudio()
        }
        btnDisEnroll.setOnClickListener {
            gnaniClient.disEnrollUser("")
        }

    }

    override fun success(message: String) {
        Log.d(TAG, "success: $message")
        showDialog(message)
    }

    override fun failed(message: String) {
        Log.d(TAG, "failed: $message")
        showDialog(message)
    }

    private fun recordAudio() {

        filePath = "$filesDir/recorded_audio.wav"

        val color = ContextCompat.getColor(this, R.color.purple_700)
        AndroidAudioRecorder.with(this) // Required
            .setFilePath(filePath)
            .setColor(color)
            .setRequestCode(requestCode) // Optional
            .setSource(AudioSource.MIC)
            .setChannel(AudioChannel.MONO)
            .setSampleRate(AudioSampleRate.HZ_8000)
            .setAutoStart(true)
            .setKeepDisplayOn(true) // Start recording
            .record()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == this.requestCode){
            if(resultCode == RESULT_OK){

                if(MODE == 1){
                    val fileUri = Uri.parse(filePath)

                    val file = File(fileUri.path)

                    Log.d(TAG, "onActivityResult: ${file.absolutePath}")

                    gnaniClient.authenticateUser("", file)
                }
                if(MODE == 2){

                    val fileUri = Uri.parse(filePath)

                    val file = File(fileUri.path)

                    Log.d(TAG, "onActivityResult: ${file.absolutePath}")

                    gnaniClient.enrollUser("", file)
                }
            }
        }
    }

    private fun showDialog(message: String) {
        val tvMessage: TextView
        val alert = AlertDialog.Builder(this)
        val mView: View = layoutInflater.inflate(R.layout.layout_dialog, null)
        tvMessage = mView.findViewById(R.id.tv_message)
        tvMessage.text = message
        alert.setView(mView)
        val alertDialog = alert.create()
        alertDialog.setCanceledOnTouchOutside(true)
        alertDialog.show()
    }

}