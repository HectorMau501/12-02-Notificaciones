package com.example.a12_02_notificaciones

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat
import com.example.a12_02_notificaciones.Activty_Notificacion.Companion.notificationId

class Activity_Formulario : AppCompatActivity() {

    //Instancias
    private lateinit var nom: EditText
    private lateinit var ape: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)
        //Borrar notificacion de barra de estado
        NotificationManagerCompat.from(this).apply {
            cancel(notificationId)
        }
        //Establecer la barra de estado y agregar la flecha de regreso
        setSupportActionBar(findViewById(R.id.barraForm))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //Asociar componentes
        nom = findViewById(R.id.edtNombre)
        ape = findViewById(R.id.edtApellidos)
    }

    fun agendarCita(view: View){
        //Validar cajas de texto
        if(nom.text.isNotEmpty() && nom.text.isNotBlank() &&
            ape.text.isNotEmpty() && ape.text.isNotBlank()){
            Toast.makeText(this, "Cita registrada a nombre de ${nom.text} ${ape.text}",Toast.LENGTH_SHORT).show()
            nom.text = null
            ape.text = null
            nom.requestFocus()
        }else{
            Toast.makeText(applicationContext, "Campos vacios.", Toast.LENGTH_SHORT).show()
            nom.requestFocus()
        }
    }//agendar cita

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, Activty_Notificacion::class.java).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }
}