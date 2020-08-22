package com.jeberlen.qrunner.viewer

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Base64
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
import android.webkit.WebView
import android.webkit.WebView.RENDERER_PRIORITY_IMPORTANT
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.jeberlen.qrunner.R


class AppViewerActivity : AppCompatActivity() {

    private val TAG = "AppViewerActivity"

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_viewer)

        Log.e(TAG, "onCreate")


        val message = intent.getStringExtra(EXTRA_MESSAGE)

        val webView = findViewById<View>(R.id.webview) as WebView
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.mixedContentMode = MIXED_CONTENT_ALWAYS_ALLOW

        if (Build.VERSION.SDK_INT >= 19) {
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        val encodedHtml = Base64.encodeToString(message?.toByteArray(), Base64.NO_PADDING)
        webView.loadData(encodedHtml, "text/html", "base64")

    }

}

