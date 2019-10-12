package com.mai.fxxkhuaweilauncher

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import org.jetbrains.anko.toast
import android.content.Intent



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val home = Intent(Intent.ACTION_MAIN)
            home.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            home.addCategory(Intent.CATEGORY_HOME)
            startActivity(home)
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        toast("AntiHuaweiLauncher is destroying...")
        stopAntiService()
        super.onDestroy()
    }
}
