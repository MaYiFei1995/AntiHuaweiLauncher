package com.mai.fxxkhuaweilauncher

import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * 检查包名是否安装
 * 需要授权获取已安装应用列表
 */
fun Context.checkPkgName(inputStr: String): Boolean {
    for (pkgName in this.packageManager.getInstalledPackages(0)) {
        if (inputStr == pkgName.packageName) {
            Log.i("Utils", "checkPkgName result true")
            return true
        }
    }
    Log.i("Utils", "checkPkgName result false")
    return false
}

/**
 * 启动监听服务
 */
fun Context.startAntiService(): Boolean {
    startForegroundService(Intent(this, FxxkHuaweiLauncherService::class.java))
    return false
}

