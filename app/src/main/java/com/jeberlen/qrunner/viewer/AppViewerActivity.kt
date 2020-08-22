package com.jeberlen.qrunner.viewer

import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Base64
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.jeberlen.qrunner.R

class AppViewerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_viewer)

        val message = intent.getStringExtra(EXTRA_MESSAGE)

        val webView = WebView(this)
        setContentView(webView)

        val encodedHtml = Base64.encodeToString(message?.toByteArray(), Base64.NO_PADDING)
        webView.loadData(encodedHtml, "text/html", "base64")

    }
}