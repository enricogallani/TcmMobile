package com.example.front.ui.client;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.front.R;
import com.example.front.db.entity.Client;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class ShowClientActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_client);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        client = (Client) getIntent().getSerializableExtra("client");
        Log.d("LSKDSLDKLDKS", "LSKDSLDK SLKDSLDKSD client " + client);

        TextView tvName = findViewById(R.id.name);
        TextView tvEmail = findViewById(R.id.email);
        TextView tvPhone = findViewById(R.id.telefone);
        TextView tvCnpj = findViewById(R.id.cnpj);
        TextView tvPaymentMethod = findViewById(R.id.pagamento);
        TextView tvLocation = findViewById(R.id.localizacao);

        tvEmail.setText(client.getMail());
        tvName.setText(client.getName());
        tvCnpj.setText(client.getCnpj());
        tvPhone.setText(client.getPhone());
        tvPaymentMethod.setText(client.getPaymentMethod());
        tvLocation.setText(client.getLocation());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng address = getLocationFromAddress(client.getLocation());
        mMap.addMarker(new MarkerOptions().position(address).title("Localização"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(address));
        mMap.setMinZoomPreference(15);
    }

    public LatLng getLocationFromAddress(String strAddress) {
        Geocoder coder = new Geocoder(ShowClientActivity.this);
        List<Address> address;
        LatLng latLng = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            latLng = new LatLng(location.getLatitude(), location.getLongitude());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return latLng;
    }
}