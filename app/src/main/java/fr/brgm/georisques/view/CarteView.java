package fr.brgm.georisques.view;

import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;


/**
 * Created by a601912 on 07/05/2015.
 */
public interface CarteView {

    public GoogleMap getGoogleMap();

    public MapView getMapView();

    public FragmentActivity getFragActivity();


}
