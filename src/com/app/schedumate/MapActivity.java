//Google Maps implementation
//Author : Nikhil Mehta

package com.app.schedumate;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.widget.Toast;


public class MapActivity extends Activity {
   static final LatLng TutorialsPoint = new LatLng(21 , 57);
   private GoogleMap googleMap;
   @SuppressLint("NewApi")
@Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      try { 
            if (googleMap == null) {
               googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            }
         googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
         Marker TP = googleMap.addMarker(new MarkerOptions().
         position(TutorialsPoint).title("TutorialsPoint"));

      } catch (Exception e) {
         e.printStackTrace();
      }

   }

}
