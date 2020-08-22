package com.jeberlen.qrunner

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jeberlen.qrunner.scanner.QScanner
import com.jeberlen.qrunner.scanner.QScannerActivity
import com.jeberlen.qrunner.viewer.AppViewerActivity
import org.jsoup.Jsoup
import org.jsoup.safety.Whitelist

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, QScannerActivity::class.java)
        startActivity(intent)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.d(TAG, " --- OnActivityResult()")

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                val content = data?.getStringExtra("SCAN_RESULT")


            }

            if (resultCode == RESULT_CANCELED) {
                Log.d(TAG, "Scan cancelled")
            }
        }
    }

}