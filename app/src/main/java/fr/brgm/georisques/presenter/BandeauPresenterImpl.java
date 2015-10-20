package fr.brgm.georisques.presenter;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import javax.inject.Inject;
import fr.brgm.georisques.ui.event.CarteMapEvent;
import fr.brgm.georisques.ui.event.GpsMapEvent;
import fr.brgm.georisques.view.BandeauView;

/**
 * Created by a601912 on 11/05/2015.
 */

/*
 * BandeauPresenter Implementation
 */
public class BandeauPresenterImpl implements BandeauPresenter {
    /*
     * View
     */
    public BandeauView bandeauView;

    /*
     * String to display latitude and longitude
     */
    public Double latitude;

    public Double longitude;

    public String sub;

    /*
     * Annotate the constructor that Dagger should use to create instances of a class
     */
    @Inject
    public BandeauPresenterImpl(BandeauView bandeauView){
        this.bandeauView = bandeauView;
    }

    /*
     * Insert adress in the bandeau
     */
    @Override
    public void updateAdress(String adress) {
        bandeauView.getAdress().setText(adress);
        //Log.d("recup adress", "++"+adress+"++");
    }

    /*
     * Insert point values which come from the map
     */
    @Override
    public void updatePointFromCarte(CarteMapEvent carteMapEvent) {
        latitude = carteMapEvent.ptn.latitude;
        longitude = carteMapEvent.ptn.longitude;
        bandeauView.getPoint().setText("Latitude: "+latitude + "\n\nLongitude: " + longitude);

    }

    /*
     * Insert point values which come from LocalisationFragment
     */
    @Override
    public void updatePointFromGPS(GpsMapEvent gpsMapEvent) {
        latitude = gpsMapEvent.ptn.latitude;
        longitude = gpsMapEvent.ptn.longitude;
        bandeauView.getPoint().setText("Latitude: "+latitude + "\n\nLongitude: " + longitude);
    }

    @Override
    public void OnResume() {

    }

    @Override
    public void OnPause() {

    }
}
