package com.app.lockcompose


import android.content.Intent
import android.graphics.PixelFormat
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity


class LockScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lock_screen)

        window.addFlags(
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                    or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                    or WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                    or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
        )
    }


    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
