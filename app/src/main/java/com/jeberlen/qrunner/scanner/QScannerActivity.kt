package com.jeberlen.qrunner.scanner

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.Result
import com.jeberlen.qrunner.R
import me.dm7.barcodescanner.zxing.ZXingScannerView


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
        // Stop camera on pause
        scannerView.stopCamera()
    }

    override fun handleResult(rawResult: Result) {
        Log.d("result", rawResult.text)
        Log.d("result", rawResult.barcodeFormat.toString())
        //If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);
        val intent = Intent()
        intent.putExtra("SCAN_RESULT", rawResult.text)
        setResult(RESULT_OK, intent)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                val contents = data?.getStringExtra("SCAN_RESULT")
                val qScanner = QScanner(this)
                // TODO: Change to handling image from phone
                val frame = qScanner.createFrame(this)
                qScanner.detect(frame)
            }

            if (resultCode == RESULT_CANCELED) {
                Log.d(TAG, "Scan cancelled")
            }
        }
    }

}