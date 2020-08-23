package com.jeberlen.qrunner

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.AlarmClock
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.vision.Frame
import com.jeberlen.qrunner.scanner.QScanner
import com.jeberlen.qrunner.scanner.QScannerActivity
import com.jeberlen.qrunner.viewer.AppViewerActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, QScannerActivity::class.java)
        startActivity(intent)

        /*
        // Keep this for later use - might be moved to a test class
        val myQRCode = BitmapFactory.decodeResource(resources, R.drawable.test)

        val myFrame: Frame = Frame.Builder()
            .setBitmap(myQRCode)
            .build()

        val qScanner = QScanner(this)


        val intent = Intent(this, AppViewerActivity::class.java).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, qScanner.detectFromFrame(myFrame))
        }

        startActivity(intent)
        */

    }

}