package com.jeberlen.qrunner.scanner

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.util.SparseArray
import androidx.core.util.forEach
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import com.jeberlen.qrunner.R
import com.jeberlen.qrunner.viewer.AppViewerActivity
import org.jsoup.Jsoup
import org.jsoup.safety.Whitelist

class QScanner(private val context: Context) {

    private val tag = "QScanner"

    private val barcodeDetector = BarcodeDetector.Builder(context)
        .setBarcodeFormats(Barcode.DATA_MATRIX or Barcode.QR_CODE)
        .build()

    fun createFrame(context: Context) : Frame {
        val bitmap = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.test
        )

        return Frame.Builder().setBitmap(bitmap).build()
    }

    private fun getCodeValue(qrCode: SparseArray<Barcode>): String? {
        qrCode.forEach { _, value ->
            return value.rawValue
        }

        return null
    }

    fun detect(frame: Frame) {
        val qrCode = barcodeDetector.detect(frame)

        val qrCodeValue = getCodeValue(qrCode)
        Log.d(tag, "detect 2: $qrCodeValue")
        val whitelist = Whitelist.relaxed()
        if (Jsoup.isValid(qrCode.toString(), whitelist)) {
            // Intend to open WebView activity using data from QR
            val intent = Intent(context, AppViewerActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, qrCodeValue)
            }
            context.startActivity(intent)
        }
    }


}