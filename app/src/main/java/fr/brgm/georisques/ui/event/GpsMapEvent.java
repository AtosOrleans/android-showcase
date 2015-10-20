package fr.brgm.georisques.ui.event;

import android.location.Geocoder;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by a601912 on 06/05/2015.
 */

/*
 * Gps Event
 */
public class GpsMapEvent {

    public final LatLng ptn;
    public final Geocoder geocoder;

    public GpsMapEvent(LatLng ptn, Geocoder geocoder) {
        this.ptn = ptn;
        this.geocoder = geocoder;
    }

}