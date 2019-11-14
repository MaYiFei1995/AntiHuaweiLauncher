package com.mai.fxxkhuaweilauncher

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log

class CloseSystemDialogsReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_CLOSE_SYSTEM_DIALOGS) {
            val stringExtra: String = intent.getStringExtra("reason") ?: return
            if (stringExtra == "homekey") {
                Log.i("FxxkService", "home button pressed")

                val actionIntent = Intent(Intent.ACTION_MAIN)
                actionIntent.addCategory(Intent.CATEGORY_LAUNCHER)
                actionIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                actionIntent.component = ComponentName(
                    "amirz.rootless.nexuslauncher",
                    "com.google.android.apps.nexuslauncher.NexusLauncherActivity"
                )
//                        startActivity(intent)

                val pendingIntent = PendingIntent.getActivity(context, 0, actionIntent, 0)
                try {
                    pendingIntent.send()
                } catch (e: PendingIntent.CanceledException) {
                    e.printStackTrace()
                }
            }
        }
    }

}