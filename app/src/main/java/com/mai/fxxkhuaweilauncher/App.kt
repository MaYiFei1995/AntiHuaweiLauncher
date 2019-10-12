package com.mai.fxxkhuaweilauncher

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context

class App : Application() {

    companion object {
        const val notificationChannelId = "0"
        lateinit var notificationManager: NotificationManager
    }

    override fun onCreate() {
        super.onCreate()
        initNotificationChannel()

        // 自动启动
        if (this.checkPkgName("com.huawei.android.launcher"))
            this.startAntiService()
    }

    private fun initNotificationChannel() {
        // 初始化NotificationChannel
        notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(
            notificationChannelId,
            "前台通知",
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(channel)
    }

}