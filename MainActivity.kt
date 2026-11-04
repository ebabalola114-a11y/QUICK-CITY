package com.quickcity.lagos

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.graphics.Color
import android.view.Gravity

class MainActivity : Activity() {
    private lateinit var webView: WebView
    private lateinit var menu: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showMenu()
    }

    private fun showMenu() {
        menu = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(28, 28, 28, 28)
            setBackgroundColor(Color.rgb(13, 25, 43))
        }
        val title = TextView(this).apply {
            text = "QUICK CITY: LAGOS"
            textSize = 28f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }
        val subtitle = TextView(this).apply {
            text = "Choose how you want to play"
            textSize = 16f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
            setPadding(0, 12, 0, 26)
        }
        val offline = makeButton("PLAY OFFLINE")
        offline.setOnClickListener { launchGame("offline.html") }
        val online = makeButton("PLAY ONLINE 3D")
        online.setOnClickListener { launchGame("online.html") }
        val note = TextView(this).apply {
            text = "Offline mode works without data. Online 3D mode needs internet to load its 3D engine."
            textSize = 12f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
            setPadding(12, 22, 12, 0)
        }
        menu.addView(title)
        menu.addView(subtitle)
        menu.addView(offline, LinearLayout.LayoutParams(280, 58).apply { bottomMargin = 12 })
        menu.addView(online, LinearLayout.LayoutParams(280, 58))
        menu.addView(note)
        setContentView(menu)
    }

    private fun makeButton(label: String): Button = Button(this).apply {
        text = label
        textSize = 16f
        isAllCaps = false
    }

    private fun launchGame(file: String) {
        webView = WebView(this)
        webView.setBackgroundColor(Color.BLACK)
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()
        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            cacheMode = WebSettings.LOAD_DEFAULT
            allowFileAccess = true
            allowContentAccess = true
            mediaPlaybackRequiresUserGesture = false
        }
        setContentView(webView)
        webView.loadUrl("file:///android_asset/$file")
    }

    @Deprecated("Deprecated in Android API")
    override fun onBackPressed() {
        if (::webView.isInitialized && webView.visibility == View.VISIBLE) {
            webView.destroy()
            showMenu()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        if (::webView.isInitialized) webView.destroy()
        super.onDestroy()
    }
}
