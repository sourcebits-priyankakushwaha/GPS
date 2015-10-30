package com.sourcebits.locationsample;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements LocationListener {

	LocationManager locationManager;
	double latitude;
	double longitude;
	TextView LatitudeTv, LongitudeTv;
	Button button = (Button) findViewById(R.id.refresh);
	ProgressDialog dialog;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		LatitudeTv = (TextView) findViewById(R.id.latitude);
		LongitudeTv = (TextView) findViewById(R.id.longitude);
		button.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				// set the fetched coordinates into text view
				refresh();

			}
		});
		dialog = new ProgressDialog(MainActivity.this);
		dialog.show();
		dialog.setMessage("Getting Coordinates");

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 1, this);
		} else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 1, this);
		} else {
			dialog.dismiss();

			Toast.makeText(getApplicationContext(), "Enable Location", Toast.LENGTH_LONG).show();
		}
	}

	protected void refresh() {

		super.onResume();
		this.onCreate(null);

	}

	@Override
	public void onLocationChanged(Location location) {
		dialog.show();
		latitude = location.getLatitude();
		longitude = location.getLongitude();
		if (latitude != 0 && longitude != 0) {

			LatitudeTv.setText("Latitude is :" + location.getLatitude());
			LongitudeTv.setText("Longitude is :" + location.getLongitude());
			dialog.dismiss();
		}

	}

	@Override
	public void onProviderDisabled(String arg0) {

	}

	@Override
	public void onProviderEnabled(String arg0) {

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {

	}

}
