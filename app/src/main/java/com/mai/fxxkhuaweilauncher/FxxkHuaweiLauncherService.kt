package com.mai.fxxkhuaweilauncher

import android.app.PendingIntent
import android.app.Service
import android.content.*
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.util.Log
import org.jetbrains.anko.toast

class FxxkHuaweiLauncherService : Service() {

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == Intent.ACTION_CLOSE_SYSTEM_DIALOGS) {
                val stringExtra: String = intent.getStringExtra("reason") ?: return
                if (stringExtra == "homekey") {
                    Log.i("FxxkService", "home button pressed")

                    val intent = Intent(Intent.ACTION_MAIN)
                    intent.addCategory(Intent.CATEGORY_LAUNCHER)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    intent.component = ComponentName(
                        "amirz.rootless.nexuslauncher",
                        "com.google.android.apps.nexuslauncher.NexusLauncherActivity"
                    )
//                        startActivity(intent)

                    val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
                    try {
                        pendingIntent.send()
                    } catch (e: PendingIntent.CanceledException) {
                        e.printStackTrace()
                    }
                }
            }
        }

    }

    override fun onCreate() {
        Log.i("FxxkService", "onCreate")
        super.onCreate()
        val notification = NotificationCompat.Builder(application, App.notificationChannelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setTicker("监听服务启动")
            .setContentTitle("监听服务运行中")
            .setContentText("正在监听Home键变化")
            .build()

        startForeground(1, notification)

        registerReceiver(receiver, IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS))
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("FxxkService", "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        toast("Service destroying...")
        unregisterReceiver(receiver)
        stopForeground(true)
        super.onDestroy()
    }

}