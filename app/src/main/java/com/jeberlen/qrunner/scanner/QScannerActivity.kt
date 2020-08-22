package com.jeberlen.qrunner.scanner

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.Result
import com.jeberlen.qrunner.MainActivity
import com.jeberlen.qrunner.viewer.AppViewerActivity
import me.dm7.barcodescanner.zxing.ZXingScannerView
import org.jsoup.Jsoup
import org.jsoup.safety.Whitelist
import org.jsoup.safety.Cleaner


class QScannerActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    private val TAG = "QScannerActivity"

    private lateinit var scannerView: ZXingScannerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scannerView = ZXingScannerView(this)
        setContentView(scannerView)
    }

    override fun onResume() {
        super.onResume()
        scannerView.setResultHandler(this)
        scannerView.startCamera()
    }

    override fun onPause() {
        super.onPause()
        scannerView.stopCamera()
    }

    override fun handleResult(rawResult: Result) {
        val whitelist = Whitelist.relaxed()
        val res = Jsoup.clean(rawResult.toString(), whitelist)


        if (Jsoup.isValid(res, whitelist)) {
            // Intend to open WebView activity using data from QR
            val intent = Intent(this, AppViewerActivity::class.java).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, rawResult.text)
            }

            startActivity(intent)
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

}