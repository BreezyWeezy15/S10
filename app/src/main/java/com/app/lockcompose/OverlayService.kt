package com.app.lockcompose

import android.app.Service
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager

class OverlayService : Service() {

    private lateinit var windowManager: WindowManager
    private lateinit var overlayView: View

    override fun onCreate() {
        super.onCreate()
        handler.post(runnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private val handler = Handler(Looper.getMainLooper())
    private val runnable = object : Runnable {
        override fun run() {
            checkForegroundApp()
            handler.postDelayed(this, 1000)
        }
    }

    private fun checkForegroundApp() {
        val usageStatsManager = getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val endTime = System.currentTimeMillis()
        val startTime = endTime - 10000  // 10 seconds range
        val usageStatsList = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, startTime, endTime)
        if (usageStatsList != null && usageStatsList.isNotEmpty()) {
            val sortedMap = usageStatsList.sortedWith(compareByDescending { it.lastTimeUsed })
            val currentApp = sortedMap[0].packageName
            Log.d("OverlayService", "Current foreground app: $currentApp")
            if (currentApp == "com.android.settings") {
                showLockScreen()
            }
        } else {
            Log.d("OverlayService", "No usage stats available.")
        }
    }

    private fun showLockScreen() {
        val lockIntent = Intent(this, LockScreenActivity::class.java)
        lockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(lockIntent)
        Log.d("OverlayService", "Lock screen shown.")
    }
}



