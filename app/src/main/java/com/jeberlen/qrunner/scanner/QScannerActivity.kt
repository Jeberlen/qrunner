package com.jeberlen.qrunner.scanner

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.AlarmClock
import android.util.Log
import android.util.SparseArray
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.Detector.Detections
import com.google.android.gms.vision.barcode.Barcode
import com.jeberlen.qrunner.R
import com.jeberlen.qrunner.viewer.AppViewerActivity
import java.io.IOException


class QScannerActivity : AppCompatActivity() {

    private val TAG = "QScannerActivity"

    private lateinit var qScanner: QScanner
    private lateinit var cameraView: SurfaceView
    private lateinit var barcodeInfo: TextView
    private val context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_q_scanner)
        Log.e(TAG, "onCreate")

        qScanner = QScanner(this)
        cameraView = findViewById<View>(R.id.camera_view) as SurfaceView
        barcodeInfo = findViewById<View>(R.id.textView) as TextView

        cameraView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.CAMERA
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }

                try {
                    qScanner.cameraSource.start(cameraView.holder)
                } catch (ie: IOException) {
                    Log.e(TAG, "Camera source failed to load.")
                }
            }

            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                qScanner.cameraSource.stop();
            }
        })
        qScanner.barcodeDetector.setProcessor(object : Detector.Processor<Barcode?> {
            override fun release() {}
            override fun receiveDetections(detections: Detections<Barcode?>) {
                val barcode: SparseArray<Barcode?> = detections.detectedItems

                if (barcode.size() != 0) {
                    val qrCodeValue = qScanner.getCodeValue(barcode)

                    barcodeInfo.post(Runnable
                    {
                        barcodeInfo.text = qrCodeValue
                    })

                    val intent = Intent(context, AppViewerActivity::class.java).apply {
                        putExtra(AlarmClock.EXTRA_MESSAGE, qrCodeValue)
                    }

                    cameraView.post {
                        qScanner.cameraSource.stop()
                    }

                    context.startActivity(intent)

                }
            }
        })
    }
}
