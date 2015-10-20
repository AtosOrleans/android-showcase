package fr.brgm.georisques.presenter;

import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import fr.brgm.georisques.R;
import fr.brgm.georisques.ui.event.GpsMapEvent;

import fr.brgm.georisques.util.BusProvider;
import fr.brgm.georisques.view.LocalisationView;

/**
 * Created by a601912 on 06/05/2015.
 */

/*
 * Implementation de LocalisationPresenter
 */
public class LocalisationPresenterImpl implements LocalisationPresenter {
    /*
     * View
     */
    public LocalisationView locView;
    /*
     * Latitude and Longitude
     */
    public Double latitude, longitude;

    public String ville;

    public String adresse;

    public LatLng point;

    public GpsMapEvent gpsMapEvent;

    public List<Address> addresses;

    public Geocoder geocoder;


    /*
     * Annotate the constructor that Dagger should use to create instances of a class
     */
    @Inject
    public LocalisationPresenterImpl(LocalisationView locView){
        this.locView = locView;
    }

    /*
     * Get the adress
     */
    @Override
    public void updateOnClick() {

        if(locView.getLatitude().getText().toString() == null || locView.getLatitude().getText().toString().isEmpty()){
            latitude = 0.0;
        }
        else {
            latitude = Double.parseDouble(locView.getLatitude().getText().toString());
            if(latitude < -90 || latitude > 90){
                //Toast.makeText(locView.getFragActivity().getApplicationContext(), "Donnée latitude incorrecte [-90; 90]", Toast.LENGTH_LONG).show();
                latitude = 0.0;
            }
        }
        if(locView.getLongitude().getText().toString() == null || locView.getLongitude().getText().toString().isEmpty()){
            longitude = 0.0;
        }
        else {
            longitude = Double.parseDouble(locView.getLongitude().getText().toString());
            if(longitude < -180 || longitude > 180){
                //Toast.makeText(locView.getFragActivity().getApplicationContext(), "Donnée longitude incorrecte [-180; 180]", Toast.LENGTH_LONG).show();
                longitude = 0.0;
            }
        }
        geocoder = new Geocoder(locView.getFragActivity(), Locale.getDefault());

        point = new LatLng(latitude, longitude);
        gpsMapEvent = new GpsMapEvent(point, geocoder);

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            //Log.d("TATA1", addresses.toString());
            if(addresses.isEmpty()){
                addresses = geocoder.getFromLocation(45.0, 2.0, 1);
                addresses.get(0).setAddressLine(0, "Unknown Adress");
                addresses.get(0).setLocality("Unknown Locality");
            }
            //Log.d("TATA2", addresses.toString());
            //Envoi de l'adresse dans le bus de donnees
            BusProvider.getInstance().post(addresses.get(0).getAddressLine(0) + "\n\n" + addresses.get(0).getLocality());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Envoi l'event dans le bus
        BusProvider.getInstance().post(gpsMapEvent);

    }

    /*
     * Get the adress from Coordonnee Postale
     */
    @Override
    public void updateCoordonneeOnClick() {

        if(locView.getVille().getText().toString() == null || locView.getVille().getText().toString().isEmpty()){
            ville = "Unknown";
        }
        else {
            ville = (locView.getVille().getText().toString());
        }
        if(locView.getAdresse().getText().toString() == null || locView.getAdresse().getText().toString().isEmpty()){
            adresse = "Unknown";
        }
        else {
            adresse = (locView.getAdresse().getText().toString());
        }
        geocoder = new Geocoder(locView.getFragActivity(), Locale.getDefault());

        try {
            addresses = geocoder.getFromLocationName(ville, 5);
            latitude = addresses.get(0).getLatitude();
            longitude = addresses.get(0).getLongitude();
            point = new LatLng(latitude, longitude);
            gpsMapEvent = new GpsMapEvent(point, geocoder);

            BusProvider.getInstance().post(gpsMapEvent);
            //Log.d("NOUVEAU",  latitude.toString());
            //Log.d("NOUVEAU",  longitude.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
            //Envoi de l'adresse dans le bus de donnees
            BusProvider.getInstance().post(locView.getAdresse().getText().toString() + "\n\n" + locView.getVille().getText().toString());

    }

    /*
     * Clear GPS TextView
     */
    @Override
    public void deleteOnClick() {
        locView.getLatitude().setText("");
        locView.getLongitude().setText("");
    }

    /*
    * Clear Coordonnee TextView
    */
    @Override
    public void deleteCoordonneeOnClick() {
        //locView.getCode().setText("");
        locView.getVille().setText("");
        locView.getAdresse().setText("");
       // locView.getNumero().setText("");
    }


}
