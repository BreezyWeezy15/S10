package com.app.lockcompose


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity


class LockScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!Settings.canDrawOverlays(this)) {
            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
            intent.data = Uri.parse("package:${packageName}")
            startActivityForResult(intent, 1)
        }
        // Continue with your activity setup
        window.setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY)
        window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        window.addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
        setContentView(R.layout.activity_lock_screen)
    }


    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
