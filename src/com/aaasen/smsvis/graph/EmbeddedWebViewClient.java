package com.aaasen.smsvis.graph;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class EmbeddedWebViewClient extends WebViewClient {
	
	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		view.loadUrl(url);
		return true;
	}

	@Override
	public void onLoadResource(WebView  view, String  url){

	}
}
