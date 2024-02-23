package com.example.a12_02_notificaciones

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat

class activity_pending : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pending)
        //Establecer la barra de estado y agregar la flecha de regreso
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //Asociar con componente grafico
        val texto: TextView = findViewById(R.id.txtPending1)
        //Obtener el parametro enviado
        val respuesta = intent.getIntExtra("accion", 1)
        //Validacion de respuesta
        texto.text = if (respuesta ==1) "Seleccionaste Aceptar" else "Seleccionaste Cancelar"
        //Eliminar notificaciones
        with(NotificationManagerCompat.from(this)){
            cancelAll()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Toast.makeText(this, "REGRESO", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }
}