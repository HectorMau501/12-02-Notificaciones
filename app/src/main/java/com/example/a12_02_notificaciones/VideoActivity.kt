package com.example.a12_02_notificaciones

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView

class VideoActivity : AppCompatActivity() {

    //los estamos inicializando en nulos
    //Las variables las pide que las inicialices con un valor
    private var videoView: VideoView? = null
    private var mediaController: MediaController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        //Componentes
        videoView = findViewById(R.id.videoView)
        mediaController = MediaController(this)

        mediaController!!.setAnchorView(videoView)

        //Reproducir un video especifico
        val uri = Uri.parse("android.resource://"+ packageName + "/"+ R.raw.video)
        videoView!!.setVideoURI(uri)
        videoView!!.requestFocus()
        videoView!!.start()
        Toast.makeText(this, "Comienza video", Toast.LENGTH_SHORT).show()
        videoView!!.setMediaController(mediaController)

    }
}