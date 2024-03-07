package com.example.a12_02_notificaciones

import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import java.io.IOException

class MediaActivity : AppCompatActivity() {

    private var play: Button? = null
    private var stop: Button? = null
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media)

            //Componentes
        play = findViewById(R.id.btnReproducir)
        stop = findViewById(R.id.btnDetener)
        play!!.setOnClickListener {
            mediaPlayer = MediaPlayer()
            mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
            val uri = Uri.parse("android.resource://"+packageName+"/"+R.raw.holdtheline)
            try{
                mediaPlayer!!.setDataSource(this@MediaActivity, uri)
                mediaPlayer!!.prepare()
                mediaPlayer!!.start()
                Toast.makeText(
                    this@MediaActivity, "Comienza reproduccion",
                    Toast.LENGTH_LONG).show()
            }catch(e: IOException){
                Toast.makeText(
                    this@MediaActivity, "Error al reproducir",
                    Toast.LENGTH_SHORT).show()
            }
        }
        stop!!.setOnClickListener {
            if(mediaPlayer != null && mediaPlayer!!.isPlaying)
                mediaPlayer!!.stop()
            mediaPlayer = null
            Toast.makeText(
                this@MediaActivity, "Se detiene reproduccion",
                Toast.LENGTH_LONG
            )//if
        }//stop
    }
}