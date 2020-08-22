package com.jeberlen.qrunner.scanner

import android.content.Context
import android.util.SparseArray
import androidx.core.util.forEach
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector


class QScanner(private val context: Context) {

    private val TAG = "QScanner"

    val barcodeDetector = BarcodeDetector.Builder(context)
        .setBarcodeFormats(Barcode.DATA_MATRIX or Barcode.QR_CODE)
        .build()

    val cameraSource: CameraSource = CameraSource.Builder(context, barcodeDetector)
        .setRequestedPreviewSize(640, 480)
        .setAutoFocusEnabled(true)
        .build()

    fun getCodeValue(qrCode: SparseArray<Barcode?>): String? {
        qrCode.forEach { _, value ->
            return value?.rawValue
        }
        return null
    }

    fun detectFromFrame(frame: Frame): String? {
        val qrCode = barcodeDetector.detect(frame)
        return getCodeValue(qrCode)
    }

}