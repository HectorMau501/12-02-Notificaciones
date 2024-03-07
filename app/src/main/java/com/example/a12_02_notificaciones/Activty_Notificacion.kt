package com.example.a12_02_notificaciones

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class Activty_Notificacion : AppCompatActivity() {

    //Intancias
    private lateinit var peso: EditText
    private lateinit var estatura: EditText
    private lateinit var pendingIntentSi: PendingIntent
    private lateinit var pendingIntentNo: PendingIntent
    private val CHANNEL_ID = "Notificacion_IMC"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activty_notificacion)

        //Asociar
        peso = findViewById(R.id.edtPeso)
        estatura = findViewById(R.id.edtEstatura)

    }//onCreate

    fun calcularIMC(view: View){
        //Variables
        var imc : Float
        var est: Float
        var pes: Float

        //Validar informacion
        if(peso.text.isNotEmpty() && peso.text.isNotBlank() &&
            estatura.text.isNotEmpty() && estatura.text.isNotBlank()){
            pes = peso.text.toString().toFloat()
            est = estatura.text.toString().toFloat()
            imc = pes / (est * est)

            Toast.makeText(applicationContext, "IMC: $imc",Toast.LENGTH_SHORT).show()

            if(imc > 25.0f){
                Toast.makeText(applicationContext, "Estas pasado de peso!!!: $imc",Toast.LENGTH_SHORT).show()
                //Preparar y lanzar notificacion
                createNotificationChannel()
                configureActions()
                buildNotification()
            }else{
                Toast.makeText(applicationContext, "Estas en buen estado", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(applicationContext, "Campos Vacios", Toast.LENGTH_SHORT).show()
        }
    }//calcularIMC

    @SuppressLint("MissingPermission")
    private fun buildNotification() {
        //Definir caracteristicas de la notificacion
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_notifications_24)
            .setContentTitle("Nutriologos AC")
            .setContentText("Visitanos, nosotros te podemos apoyar. ¿Desea agendar una cita?")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(R.drawable.baseline_notifications_24,"Aceptar", pendingIntentSi )
            .addAction(R.drawable.baseline_notifications_24,"Cancelar", pendingIntentNo )
            .setAutoCancel(true)

        //Mostrar notificacion
        with(NotificationManagerCompat.from(this)){
            //Notificacion is a unique int for each notification that you must define
            notify(notificationId, builder.build())
        }
    }//buildNotification

    private fun configureActions() {
        //Afirmativa
        val accionSI = Intent(this, Activity_Formulario::class.java).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        pendingIntentSi = PendingIntent.getActivity(this,0,accionSI,
            PendingIntent.FLAG_IMMUTABLE)

        //Negativa
        val accionNO = Intent(this, Activity_info::class.java).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        pendingIntentNo = PendingIntent.getActivity(this,0,accionNO,
            PendingIntent.FLAG_IMMUTABLE)

    }//configureActions

    //EquivaLENTE EN JAVA: PUBLIC FINAL STATTC
    //Para acender al valor desde otra clase
    companion object{
        const val notificationId = 200
    }

    private fun createNotificationChannel() {
        val name = "Canal_IMC"
        val descriptionText = "Canal nutricional"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }

        val nofificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nofificationManager.createNotificationChannel(channel)
    }//createNotificacion Chanel


}