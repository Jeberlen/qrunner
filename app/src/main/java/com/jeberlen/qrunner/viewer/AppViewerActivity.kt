package com.jeberlen.qrunner.viewer

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Base64
import android.view.View
import android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.jeberlen.qrunner.R


class AppViewerActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_viewer)

        val webView = findViewById<View>(R.id.webview) as WebView
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.mixedContentMode = MIXED_CONTENT_ALWAYS_ALLOW
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        val encodedHtml = Base64.encodeToString(
            intent.getStringExtra(EXTRA_MESSAGE)?.toByteArray(),
            Base64.NO_PADDING
        )

        webView.loadData(encodedHtml, "text/html", "base64")

    }

}

