package com.example.youssefbenesma.kvitteringer;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;

public class ImeiCheckerActivity extends AppCompatActivity {

    //PDFView receipt;

    /*private static final int PERMISSIONS_REQUEST_READ_PHONE_STATE = 999;

    private TelephonyManager mTelephonyManager;

    Typeface myfont;
    TextView virksomhedsnavn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imeichecker);

        //receipt = (PDFView) findViewById(R.id.pdfViewer);
        virksomhedsnavn = findViewById(R.id.tv_virksomhedsnavn);

        myfont = Typeface.createFromAsset(this.getAssets(), "fonts/TheBewlay.otf");
        virksomhedsnavn.setTypeface(myfont);

        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},
                    PERMISSIONS_REQUEST_READ_PHONE_STATE);
        } else {
            getDeviceImei();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_PHONE_STATE
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getDeviceImei();
        }
    }

    private void getDeviceImei() {

        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String deviceid = mTelephonyManager.getDeviceId();
        Log.d("msg", "DeviceImei " + deviceid);
        if (deviceid.equals("865464047357902")) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }*/
}
