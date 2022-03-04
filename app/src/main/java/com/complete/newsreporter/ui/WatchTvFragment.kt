package com.complete.newsreporter.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.complete.newsreporter.R
import com.complete.newsreporter.databinding.FragmentWatchTvBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_article.*

class WatchTvFragment : Fragment(R.layout.fragment_watch_tv) {
    val args : WatchTvFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val b = FragmentWatchTvBinding.inflate(inflater)
        Log.d("taget","Entered")
        val url = args.urlToWebsite
        b.webView.apply {
            webViewClient = WebViewClient()
            loadUrl("https://www.ndtv.com/video/live/channel/ndtv24x7")
            Log.d("taget","this")
        }
        return b.root
    }

}