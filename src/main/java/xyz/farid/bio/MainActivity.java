package xyz.farid.bio;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;



public class MainActivity<button> extends AppCompatActivity {
    private static final int REQUEST_PHONE_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Telepon(View view) {
        Intent intentCall = new Intent(Intent.ACTION_CALL);
        intentCall.setData(Uri.parse("tel:085640967027"));
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
        } else {
            startActivity(intentCall);
        }
    }

    public void Email(View v) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"111202080014@mhs.dinus.ac.id"});

        try {
            startActivity(Intent.createChooser(intent, "Kirim Email"));
        } catch (android.content.ActivityNotFoundException ex) {
        }
    }

    /*Deklarasi variable*/
    //Button btn_navigasi;
    String goolgeMap = "com.google.android.apps.maps"; // identitas package aplikasi google masps android
    Uri gmmIntentUri;
    Intent mapIntent;
    String rumah = "-7.8453946,112.0284053"; // koordinat rumah
    /*Deklarasi variable*/

    public void PETA(View v) {
        // Buat Uri dari intent string. Gunakan hasilnya untuk membuat Intent.
        gmmIntentUri = Uri.parse("google.navigation:q=" + rumah);

        // Buat Uri dari intent gmmIntentUri. Set action => ACTION_VIEW
        mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

        // Set package Google Maps untuk tujuan aplikasi yang di Intent yaitu google maps
        mapIntent.setPackage(goolgeMap);

        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            Toast.makeText(MainActivity.this, "Google Maps Belum Terinstal. Install Terlebih dahulu.",
                    Toast.LENGTH_LONG).show();
            }
    }
}