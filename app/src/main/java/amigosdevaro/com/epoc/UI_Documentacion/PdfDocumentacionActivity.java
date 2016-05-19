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

import java.util.ArrayList;
import java.util.List;

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
        Integer posText = 0;
        String title ="";

        Intent intent = getIntent();
        if(intent.hasExtra("PDF_URL")){
            text = getTextDocumentation(intent).get(0);
            posText = new Integer(getTextDocumentation(intent).get(1));
            title = getResources().getStringArray(R.array.indice_doc)[posText];
        }
        if(intent.hasExtra("PDF_URL_ejercicio")){
            text = getTextEjercicio(intent).get(0);
            posText = new Integer(getTextEjercicio(intent).get(1));
           title = getResources().getStringArray(R.array.indice_ejercicio)[posText];
        }

        url = getResources().getString(R.string.doc_url_local) + text;

        this.loadWebViewUrl(url);


        getSupportActionBar().setTitle(title);


        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    private List<String> getTextDocumentation(Intent intent){
        String text="";
        Integer posText = intent.getIntExtra("PDF_URL", -1);
        if (posText != -1) {
            text = getResources().getStringArray(R.array.url_doc)[posText];
        }
        List<String> ls = new ArrayList<String>();
        ls.add(text);
        ls.add(posText.toString());
        return ls;
    }
    private List<String> getTextEjercicio(Intent intent){
        String text="";
        Integer posText = intent.getIntExtra("PDF_URL_ejercicio", -1);
        if (posText != -1) {
            text = getResources().getStringArray(R.array.url_ejercicio)[posText];
        }
        List<String> ls = new ArrayList<String>();
        ls.add(text);
        ls.add(posText.toString());
        return ls;
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
