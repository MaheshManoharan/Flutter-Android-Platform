package com.example.flutter_native_android_ex

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity: FlutterActivity()
{
    private lateinit var channel: MethodChannel
    private val BATTERY_CHANNEL = "mahidev.com/battery"
    override fun configureFlutterEngine(flutterEngine: FlutterEngine)
    {
        super.configureFlutterEngine(flutterEngine)
        channel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, BATTERY_CHANNEL)
        //receive data from flutter
        channel.setMethodCallHandler { call, result ->
            if(call.method == "getBatteryLevel")
            {
                val batteryLevel = getBatteryLevel()
                result.success("$batteryLevel%")
            }
        }
    }
    private fun getBatteryLevel(): Int {
        val batteryLevel: Int
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            val batteryManager = getSystemService(Context.BATTERY_SERVICE) as BatteryManager
            batteryLevel = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        }
        else
        {
                  val intent = ContextWrapper(applicationContext).registerReceiver(null, IntentFilter(
                      Intent.ACTION_BATTERY_CHANGED))
      batteryLevel = intent!!.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) * 100 / intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        }
        return batteryLevel
    }
}
