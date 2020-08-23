package com.jeberlen.qrunner

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.jeberlen.qrunner.scanner.QScannerActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        permissionCheck()

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

    private fun permissionCheck() {
        val permission = Manifest.permission.CAMERA
        when {
            ContextCompat.checkSelfPermission(this, permission) ==
                PackageManager.PERMISSION_GRANTED -> {
                    val intent = Intent(
                        this,
                        QScannerActivity::class.java
                    )
                    startActivity(intent)
                }
                shouldShowRequestPermissionRationale(permission) -> {
                    requestPermissionLauncher.launch(permission)
                } else -> {
                    requestPermissionLauncher.launch(permission)
                }
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                val intent = Intent(this, QScannerActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this,
                    "This application needs camera permission " +
                            "to function, please restart the app",
                    Toast.LENGTH_LONG).show()
            }
        }
}