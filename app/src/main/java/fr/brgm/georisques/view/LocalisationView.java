package fr.brgm.georisques.view;

import android.support.v4.app.FragmentActivity;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by a601912 on 07/05/2015.
 */
public interface LocalisationView {

    public EditText getLatitude();

    public EditText getLongitude();

   // public EditText getCode();

    public EditText getVille();

    public EditText getAdresse();

   // public EditText getNumero();

    public FragmentActivity getFragActivity();

}
