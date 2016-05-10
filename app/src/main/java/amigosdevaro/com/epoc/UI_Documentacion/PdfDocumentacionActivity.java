package amigosdevaro.com.epoc.UI_Documentacion;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import amigosdevaro.com.epoc.R;

public class PdfDocumentacionActivity extends AppCompatActivity {

    String url ;
    WebView webView;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_documentacion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        webView = (WebView) findViewById(R.id.pdf_webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        webSettings.setDisplayZoomControls(false);

        String text = "";

        Intent intent = getIntent();
        Integer posText = intent.getIntExtra("PDF_URL", -1);
        if (posText != -1) {
            text = getResources().getStringArray(R.array.url_doc)[posText];
        }

        url = getResources().getString(R.string.doc_url_local) + text;

        this.loadWebViewUrl(url);

        String title = getResources().getStringArray(R.array.indice_doc)[posText];
        getSupportActionBar().setTitle(title);


        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    private void loadWebViewUrl(String url){
        progressDialog = new ProgressDialog(PdfDocumentacionActivity.this);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if(progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
            }
        });

        progressDialog.setMessage(getString(R.string.cargando_documentacion));
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        webView.loadUrl(url);
    }
}
