package com.example.fragmentbestpractice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/3/26 0026.
 */

public class NewsContentFragment extends Fragment {
    private View view;
    private static  final String Tag = "NewsContentFragment";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_content_frag, container, false);
        return view;
    }

    public void refresh(String newsTitle, String newsContent){
        Log.d(Tag, newsContent);
        View visibilityLayout = view.findViewById(R.id.visibility_layout);
        visibilityLayout.setVisibility(View.VISIBLE);
        TextView newsTitleText = (TextView)view.findViewById(R.id.news_title);
        TextView newsContentText = (TextView)view.findViewById(R.id.news_content);
        newsTitleText.setText(newsTitle);
        newsContentText.setText(newsContent);

        Log.d("HHHHH", "testWEBVIEW() 1 " );
        testWEBVIEW2();
    }
    private void testWEBVIEW2(){
        Log.d("HHHHH", "testWEBVIEW2() 2 " );
        WebView webView = new WebView(getActivity());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.baidu.com");
    }
    private void testWEBVIEW(){
        Log.d("HHHHH", "testWEBVIEW() 2 " );
        WebView webView = new WebView(getActivity());
        WebSettings webSettings = webView.getSettings();

//如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);

//设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

//缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

//其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

//        webView.loadUrl("http://www.baidu.com/");
        webView.loadUrl("file:///android_asset/index.html");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                Log.d("HHHHH", "loadUrl = " + url);
                view.loadUrl(url);
                return true;
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {


            //获取网站标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                Log.d("HHHHH", "title = " + title);
            }

            //获取加载进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 100) {
                    String progress = newProgress + "%";
                    Log.d("HHHHH", "progress = " + progress);
                } else if (newProgress == 100) {
                    String progress = newProgress + "%";
                    Log.d("HHHHH", "progress = " + progress);
                }
            }
        });
    }
}
