package com.example.youssefbenesma.kvitteringer;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

// Import neccessor namespace

public class ReceiptActivity extends AppCompatActivity {

    Typeface myfont;
    TextView virksomhedsnavn, fornavn, kundetlf, produkt, imei, job, job2, job3, pris, pris2, pris3, total, afhentning;

    private FloatingActionButton btn_getPDF;
    private LinearLayout llPdf;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        String date_current = new SimpleDateFormat("dd. MMM yyyy", Locale.getDefault()).format(new Date());
        TextView date = findViewById(R.id.tv_dato);
        date.setText(date_current);

        virksomhedsnavn = findViewById(R.id.tv_virksomhedsnavn);
        fornavn = findViewById(R.id.tv_fornavn);
        kundetlf = findViewById(R.id.tv_kundetlf);
        produkt = findViewById(R.id.tv_produkt);
        imei = findViewById(R.id.tv_imei);
        job = findViewById(R.id.tv_job);
        job2 = findViewById(R.id.tv_job2);
        job3 = findViewById(R.id.tv_job3);
        pris = findViewById(R.id.tv_pris);
        pris2 = findViewById(R.id.tv_pris2);
        pris3 = findViewById(R.id.tv_pris3);
        total = findViewById(R.id.tv_total);
        afhentning = findViewById(R.id.tv_afhentning);
        btn_getPDF = findViewById(R.id.btn_getPdf);
        llPdf = findViewById(R.id.print_layout);

        myfont = Typeface.createFromAsset(this.getAssets(), "fonts/TheBewlay.otf");
        virksomhedsnavn.setTypeface(myfont);

        Bundle bundle = getIntent().getExtras();
        String kundefornavn = bundle.getString("kundefornavn");
        String kunde_tlf = bundle.getString("kundetlf");
        String kundeprodukt = bundle.getString("kundeprodukt");
        String kundeimei = bundle.getString("kundeimei");
        String kundejob = bundle.getString("kundejob");
        String kundejob2 = bundle.getString("kundejob2");
        String kundejob3 = bundle.getString("kundejob3");
        String kundepris = bundle.getString("kundepris");
        String kundepris2 = bundle.getString("kundepris2");
        String kundepris3 = bundle.getString("kundepris3");
        String kundeafhentning = bundle.getString("kundeafhentning");
        fornavn.setText(kundefornavn);
        if (kunde_tlf.length() > 0) {
            kundetlf.setText("Tlf.: " + kunde_tlf);
        } else {
            kundetlf.setText("");
        }
        produkt.setText(kundeprodukt);
        imei.setText(kundeimei);
        afhentning.setText(kundeafhentning);
        job.setText(kundejob);
        job2.setText(kundejob2);
        job3.setText(kundejob3);

        if (kundepris.equals("")) {
            kundepris = "0";
        } else {
            pris.setText(kundepris + ",00");
        }

        if (kundepris2.equals("")) {
            kundepris2 = "0";
        } else {
            pris2.setText(kundepris2 + ",00");
        }

        if (kundepris3.equals("")) {
            kundepris3 = "0";
        } else {
            pris3.setText(kundepris3 + ",00");
        }

        int i = Integer.parseInt(kundepris);
        int i2 = Integer.parseInt(kundepris2);
        int i3 = Integer.parseInt(kundepris3);


        String total1 = String.valueOf(i + i2 + i3);

        total.setText(total1 + ",00");

        btn_getPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("size", " " + llPdf.getWidth() + "  " + llPdf.getWidth());
                bitmap = loadBitmapFromView(llPdf, llPdf.getWidth(), llPdf.getHeight());
                createPdf();
            }
        });
    }

    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);

        return b;
    }

    private void createPdf() {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        //  Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels;
        float width = displaymetrics.widthPixels;

        int convertHighet = (int) hight, convertWidth = (int) width;

        //Resources mResources = getResources();
        //Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.screenshot);

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        canvas.drawPaint(paint);

        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        document.finishPage(page);

        // write the document content
        String targetPdf = "/sdcard/Download/Kvittering.pdf";
        File filePath;
        filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }

        // close the document
        document.close();
        Toast.makeText(this, "PDF is created!!!", Toast.LENGTH_SHORT).show();

        openGeneratedPDF();

    }

    private void openGeneratedPDF() {
        File file = new File("/sdcard/Download/Kvittering.pdf");
        if (file.exists()) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(file);
            intent.setDataAndType(uri, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(ReceiptActivity.this, "No Application available to view pdf", Toast.LENGTH_LONG).show();
            }
        }
    }
}
