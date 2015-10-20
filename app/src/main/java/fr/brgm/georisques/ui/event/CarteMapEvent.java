package fr.brgm.georisques.ui.event;

import android.location.Geocoder;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;



/**
 * Created by a601912 on 07/05/2015.
 */

/*
 * Carte Event
 */
public class CarteMapEvent {

    public final LatLng ptn;
    public final Marker mark;
    public final Geocoder geocoder;

    public CarteMapEvent(LatLng ptn, Marker mark, Geocoder geocoder) {

        this.ptn = ptn;
        this.mark = mark;
        this.geocoder = geocoder;

    }

}
