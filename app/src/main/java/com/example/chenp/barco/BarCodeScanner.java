package com.example.chenp.barco;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.vision.barcode.Barcode;
import java.util.List;
import info.androidhive.barcode.BarcodeReader;

public class BarCodeScanner extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener {
    private BarcodeReader barcodeReader;
    String myBarcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_code_scanner);
        //Create a new barcodeReader
        barcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_fragment);
        
    }


    @Override
    public void onScanned(Barcode barcode) {

        //Create new Activity with textbox and allows you to change and post it to the google sheets
        myBarcode=barcode.rawValue;
        //if Barcode value is not 0 open the new activity with scanned barcode value
        if(myBarcode!=null) {
            Intent startIntent = new Intent(getApplicationContext(), AddItemToSheet.class);
            //Intent that passes barcode to new class
            startIntent.putExtra("password", myBarcode);
            startActivity(startIntent);
            finish();
        }
    }

    @Override
    public void onScannedMultiple(List<Barcode> list) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String s) {

    }

    @Override
    public void onCameraPermissionDenied() {
        Toast.makeText(getApplicationContext(), "Camera permission denied!", Toast.LENGTH_LONG).show();
    }
}