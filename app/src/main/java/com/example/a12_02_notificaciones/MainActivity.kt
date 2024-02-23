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
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {
    //Variables
    private val CHANNEL_ID = "Canal_notificacion"
    private val textTitle = "Titulo de notificacion"
    private val textContent = "Este es el texto informativo de la notificacion"
    private val notificationId = 100


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Crear canal de notificacion y definir importancia
        createNotificationChannel()

        //Asociar componentes
        val btnBasica: View = findViewById(R.id.btnBasica)
        val btnToque : View = findViewById(R.id.btnToque)
        val btnAccion : View = findViewById(R.id.btnAccion)
        val btnProgreso: View = findViewById(R.id.btnProgreso)
        //Eventos
        btnBasica.setOnClickListener { notificacionBasica() }
        btnToque.setOnClickListener { notificacionToque() }
        btnAccion.setOnClickListener { notificationBoton() }
        btnProgreso.setOnClickListener{notificationProgress()}
    }//OnCreate

    @SuppressLint("MissingPermission")
    private fun notificationProgress() {
        val builder = NotificationCompat.Builder(this, CHANNEL_ID).apply {
            setContentTitle(textTitle)
            setContentText("Descarga en progreso")
            setSmallIcon(R.drawable.baseline_notifications_24)
            setPriority(NotificationCompat.PRIORITY_LOW)
        }

        val PROGRESSMAX = 100
        val PROGRESS_CURRENT = 0

        NotificationManagerCompat.from(this).apply {
            builder.setProgress(PROGRESSMAX, PROGRESS_CURRENT, false)
            notify(notificationId, builder.build())

            builder.setContentText("Descarga completa")
                .setProgress(PROGRESSMAX, PROGRESS_CURRENT, true)
                //Quitar la barra de progreso
                .setTimeoutAfter(5000)
            notify(notificationId, builder.build())
        }
    }


    @SuppressLint("MissingPermission")
    private fun notificationBoton() {
        val accionSi = Intent(this, activity_pending::class.java).apply{
            putExtra("accion",1)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val accionNo = Intent(this, activity_pending::class.java).apply{
            putExtra("accion",2)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntentSi: PendingIntent = PendingIntent.getActivity(this,0,accionSi,
            PendingIntent.FLAG_IMMUTABLE)

        val pendingIntentNo: PendingIntent = PendingIntent.getActivity(this,0,accionNo,
            PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_notifications_24)
            .setContentTitle(textTitle)
            .setContentText("Notificacion con Boton")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(R.drawable.baseline_notifications_24, getString(R.string.si),
                pendingIntentSi)
            .addAction(R.drawable.baseline_notifications_24, getString(R.string.no),
                pendingIntentNo)
            .setAutoCancel(true)

        //Mostrar notificacion
        with(NotificationManagerCompat.from(this)){
            //
            notify(notificationId, builder.build())
        }
    }//Notificacion Boton

    @SuppressLint("MissingPermission")
    private fun notificacionToque() {
        //Lanzamiento de Activity con el toque a la notificacion
        val intent = Intent(this, activity_pending::class.java).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent : PendingIntent = PendingIntent.getActivity(this, 0, intent,
        PendingIntent.FLAG_IMMUTABLE)

        //Definir caracteristicas de la notificacion
        val builder = NotificationCompat.Builder(this,CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_notifications_24)
            .setContentTitle(textTitle)
            .setContentText("Toque la notificacion para abrir la activity")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        //Set the intent that will fire when the user taps the notification
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        //Mostrar notificacion
        with(NotificationManagerCompat.from(this)){
            //Notificacion is a unique int for each notification that you must define
            notify(notificationId, builder.build())
        }
        Toast.makeText(applicationContext, "Notificacion Basica", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("MissingPermission")
    private fun notificacionBasica() {
        //Definir caracteristicas de la notificacion
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_notifications_24)
            .setContentTitle(textTitle)
            .setContentText(textContent)
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("Un texto mas extenso que sobrepasa de una sola linea y se debe de mostrar"))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        //Mostrar notificacion
        with(NotificationManagerCompat.from(this)){
            //Notificacion is a unique int for each notification that you must define
            notify(notificationId, builder.build())
        }
        Toast.makeText(applicationContext, "Notificacion Basica", Toast.LENGTH_SHORT).show()
    }//NotificacionBasica

    private fun createNotificationChannel() {
        val name = "My Channel"
        val descriptionText = "Descripcion del canal"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }

        val nofificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nofificationManager.createNotificationChannel(channel)
    }//createNotificacion Chanel
}