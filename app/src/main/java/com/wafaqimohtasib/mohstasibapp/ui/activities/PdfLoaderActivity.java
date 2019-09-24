package com.wafaqimohtasib.mohstasibapp.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;
import com.wafaqimohtasib.mohstasibapp.R;
import com.wafaqimohtasib.mohstasibapp.utils.Helpers;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PdfLoaderActivity extends Activity implements OnPageChangeListener, OnLoadCompleteListener {

    private Integer pageNumber = 0;
    private String pdfFileName;
    private static String SAMPLE_FILE = "";
    @BindView(R.id.pdfView) PDFView pdfView;
    @BindView(R.id.tv_header) TextView tvHeaderTitle;
    @BindView(R.id.toolbar_iv_back) ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_loader);
        ButterKnife.bind(this);
        initViews();
        if(Helpers.getPreferenceValues(PdfLoaderActivity.this,getResources().getString(R.string.from)).equals(getResources().getString(R.string.faqs)))
        {
            tvHeaderTitle.setText(getResources().getString(R.string.faqs));
            SAMPLE_FILE="faqs.pdf";
        }
        else
        {
            tvHeaderTitle.setText(getResources().getString(R.string.ombudsman_act));
            SAMPLE_FILE="act.pdf";
        }

        displayFromAsset(SAMPLE_FILE, pageNumber);
    }

    private void initViews()
    {
        tvHeaderTitle.setVisibility(View.VISIBLE);
        ivBack.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PdfLoaderActivity.this,DashboardActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        new Intent(PdfLoaderActivity.this,DashboardActivity.class);
    }

    private void displayFromAsset(String assetFileName,int page) {
        pdfFileName = assetFileName;

        pdfView.fromAsset(SAMPLE_FILE)
                .defaultPage(page)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
    }



    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        printBookmarksTree(pdfView.getTableOfContents(), "-");

    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {
            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }
}
