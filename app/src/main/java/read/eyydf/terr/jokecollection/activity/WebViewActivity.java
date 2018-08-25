package read.eyydf.terr.jokecollection.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import read.eyydf.terr.jokecollection.R;
import read.eyydf.terr.jokecollection.tools.ActivityUtils;

/**
 * Created by fenghu on 2017/5/18.
 */
public class WebViewActivity extends Activity {
    private TextView text_title;
    private RelativeLayout web_layout;
    private ProgressBar web_progressBar;
    private ImageView image_back;
    private ImageView image_close;
    private String url;
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fmsd_web_activity);
        ActivityUtils.initTranslucentStatus(this);
        text_title = (TextView) findViewById(R.id.text_title);
        web_layout = (RelativeLayout) findViewById(R.id.web_layout);
        web_progressBar = (ProgressBar) findViewById(R.id.web_progressBar);
        image_back = (ImageView) findViewById(R.id.content_back);
        image_close = (ImageView) findViewById(R.id.image_close);
        init();
    }

    private void init() {
        webview = new WebView(this);
        web_layout.addView(webview, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT));
        Bundle bundle = getIntent().getBundleExtra("url");
        url = bundle.getString("geturl");
        Log.d("WebViewActivity", url);
        if (url != null) {
            load_url(url);
            text_title.setText("抢红包");
        }
        image_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webview.canGoBack())
                    webview.goBack();
                else
                    finish();
            }
        });
        image_close.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @SuppressLint({"SetJavaScriptEnabled", "InlinedApi", "NewApi"})
    private void load_url(String url) {
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    web_progressBar.setVisibility(View.GONE);
                } else {
                    if (View.INVISIBLE == web_progressBar.getVisibility()) {
                        web_progressBar.setVisibility(View.VISIBLE);
                    }
                    web_progressBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });

        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (view.getTitle() != null) {
                    if (!view.getTitle().contains("."))
                        text_title.setText(view.getTitle());

                }
            }
        });
        webview.loadUrl(url);
        webview.getSettings().setUseWideViewPort(true);// 设定支持viewport
        webview.getSettings().setLoadWithOverviewMode(true); // 自适应屏幕
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setDisplayZoomControls(false);
        webview.getSettings().setSupportZoom(true);// 设定支持缩放
        webview.getSettings().setDisplayZoomControls(false);
        webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webview.getSettings().setAppCacheEnabled(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDefaultTextEncodingName("UTF-8");
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setAllowFileAccess(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.setWebViewClient(new WebViewClient() {
            private final Pattern ACCEPTED_URI_SCHEME = Pattern.compile("(?i)" + // switch
                    // on
                    // case
                    // insensitive
                    // matching
                    '(' + // begin group for scheme
                    "(?:http|https|ftp|file)://" + "|(?:inline|data|about|javascript):" + "|(?:.*:.*@)" + ')' + "(.*)");

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("testLog", url);
                if (shouldOverrideUrlLoadingByAppInternal(view, url, true)) {
                    return true;
                }
                return false;
            }

            @SuppressLint("NewApi")
            private boolean shouldOverrideUrlLoadingByAppInternal(WebView view, String url,
                                                                  boolean interceptExternalProtocol) {
                if (isAcceptedScheme(url))
                    return false;
                Intent intent;
                try {
                    intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                } catch (Exception e) {
                    return interceptExternalProtocol;
                }

                intent.setComponent(null);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                    intent.setSelector(null);
                }

                if (getPackageManager().resolveActivity(intent, 0) == null) {
                    return tryHandleByMarket(intent) || interceptExternalProtocol;
                }

                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    return interceptExternalProtocol;
                }
                return true;
            }

            private boolean tryHandleByMarket(Intent intent) {
                String packagename = intent.getPackage();
                if (packagename != null) {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packagename));
                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        return false;
                    }
                    return true;
                } else {
                    return false;
                }
            }

            /**
             * 该url是否属于浏览器能处理的内部协议
             */
            @SuppressLint("DefaultLocale")
            private boolean isAcceptedScheme(String url) {
                // 正则中忽略了大小写，保险起见，还是转成小写
                String lowerCaseUrl = url.toLowerCase();
                Matcher acceptedUrlSchemeMatcher = ACCEPTED_URI_SCHEME.matcher(lowerCaseUrl);
                if (acceptedUrlSchemeMatcher.matches()) {
                    return true;
                }
                return false;
            }
        });
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            webview.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webview.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype,
                                        long contentLength) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(url);
                intent.setData(content_url);
                startActivity(intent);
            }
        });

        // webview.getSettings().setTextZoom(80);
    }

    @Override
    public void onBackPressed() {
        if (webview.canGoBack())
            webview.goBack();
        else
            finish();
    }
}
