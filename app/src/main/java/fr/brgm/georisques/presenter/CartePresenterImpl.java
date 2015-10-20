package fr.brgm.georisques.presenter;

import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;

import fr.brgm.georisques.R;
import fr.brgm.georisques.ui.event.CarteMapEvent;
import fr.brgm.georisques.util.BusProvider;
import fr.brgm.georisques.view.CarteView;
import icepick.Icicle;

/**
 * Created by a601912 on 07/05/2015.
 */

/*
 * CartePresenter Implementation
 */
public class CartePresenterImpl implements CartePresenter {

    /*
     * View
     */
    private CarteView carteView;
    /*
     * Marker on map
     */

    Marker mark;  // This will be automatically saved and restored

    Geocoder geocoder;

    /*
     * List of adress
     */

    List<Address> addresses;

    /*
     * Position of camera on the map
     */

    CameraPosition cameraPosition;

    /*
     * Carte Event
     */

    CarteMapEvent carteMapEvent;

    /*
     * Annotate the constructor that Dagger should use to create instances of a class
     */
    @Inject
    public CartePresenterImpl(CarteView carteView){
        this.carteView = carteView;
    }


    /*
     * Change the weather with the parameters values
     */
    public void validateChange(LatLng ptn){

        //Camera Zoom
        cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(ptn.latitude, ptn.longitude)).zoom(12).build();
        carteView.getGoogleMap().animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        //Delete all markers
        carteView.getGoogleMap().clear();

        geocoder = new Geocoder(carteView.getFragActivity(), Locale.getDefault());

        //Create new marker
        mark = carteView.getGoogleMap().addMarker(new MarkerOptions().position(
                new LatLng(ptn.latitude, ptn.longitude)).title("Atos"));
        try {

            //Get the adress
            addresses = geocoder.getFromLocation(ptn.latitude, ptn.longitude, 1);
            if(addresses.isEmpty()){
                addresses = geocoder.getFromLocation(45.0, 2.0, 1);
                addresses.get(0).setAddressLine(0, "Unknown Adress");
                addresses.get(0).setLocality("Unknown Locality");
            }
            //Insert adress in bus
            BusProvider.getInstance().post(addresses.get(0).getAddressLine(0)+"\n\n"+addresses.get(0).getLocality());

        } catch (IOException e) {

            e.printStackTrace();
        }
        //Instanciate new CarteMapEvent
        carteMapEvent = new CarteMapEvent(ptn, mark, geocoder);

        BusProvider.getInstance().post(carteMapEvent);

        //Get and send the point values in the bus
        BusProvider.getInstance().post(ptn);

           }

    /*
     * Display the appropriate map
     */
    public void displayMap(Integer response){
        switch (response) {
            case 0:
                carteView.getGoogleMap().setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case 1:
                carteView.getGoogleMap().setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
        }
    }

    @Override
    public void onResume(){

    }

    @Override
    public void onPause() {

    }

   @Override
    public void onDestroy() {

   }

   @Override
    public void onLowMemory() {

    }


}
