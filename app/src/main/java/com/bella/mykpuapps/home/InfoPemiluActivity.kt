package com.bella.mykpuapps.home

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.bella.mykpuapps.R

class InfoPemiluActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_pemilu)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val webView = findViewById<WebView>(R.id.webView)

        webView.settings.javaScriptEnabled = true

        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {

                view.loadUrl("javascript:alert('Web KPU berhasil dimuat')")
            }
        }

        webView.webChromeClient = object : WebChromeClient() {
            override fun onJsAlert(
                view: WebView,
                url: String,
                message: String,
                result: android.webkit.JsResult
            ): Boolean {

                Toast.makeText(this@InfoPemiluActivity, message, Toast.LENGTH_LONG).show()
                result.confirm()
                return true
            }
        }

        webView.loadUrl("https://www.kpu.go.id/")
    }

    override fun onSupportNavigateUp(): Boolean {

        onBackPressed()
        return true
    }
}