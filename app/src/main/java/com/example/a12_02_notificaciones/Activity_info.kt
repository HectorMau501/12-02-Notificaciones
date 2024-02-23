package com.example.a12_02_notificaciones

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.app.NotificationManagerCompat
import com.example.a12_02_notificaciones.Activty_Notificacion.Companion.notificationId

class Activity_info : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        //Borra notificacion de barra de estado
        NotificationManagerCompat.from(this).apply {
            cancel(Activty_Notificacion.notificationId)
        }
        //Establecer la barra de estado y agregar la flecha de regreso
        setSupportActionBar(findViewById(R.id.barraInfo))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }//onCreate

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, Activty_Notificacion::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }
}