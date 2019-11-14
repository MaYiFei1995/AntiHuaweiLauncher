package com.mai.fxxkhuaweilauncher

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.*
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.util.Log
import org.jetbrains.anko.toast
import java.lang.Exception

class FxxkHuaweiLauncherService : Service() {

    private val receiver = CloseSystemDialogsReceiver()

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("FxxkService", "onStartCommand")
        initNotificationChannel()
        val notification = NotificationCompat.Builder(this, "0")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setTicker("监听服务启动")
            .setContentTitle("监听服务运行中")
            .setContentText("正在监听Home键变化")
            .build()

        startForeground(1, notification)
        try {
            unregisterReceiver(receiver)
        } catch (ignore: Exception) {

        }
        registerReceiver(receiver, IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS))
        toast("onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        toast("Service destroying...")
        unregisterReceiver(receiver)
        stopForeground(true)
        super.onDestroy()
    }

    private fun initNotificationChannel() {
        // 初始化NotificationChannel
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(
            "0",
            "前台通知",
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(channel)
    }

}