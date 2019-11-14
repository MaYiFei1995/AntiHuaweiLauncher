package com.mai.fxxkhuaweilauncher

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        // 自动启动
        if (this.checkPkgName("com.huawei.android.launcher"))
            this.startAntiService()
    }


}