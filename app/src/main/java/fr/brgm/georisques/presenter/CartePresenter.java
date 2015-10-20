package fr.brgm.georisques.presenter;


import com.google.android.gms.maps.model.LatLng;

/**
 * Created by a601912 on 07/05/2015.
 */

/*
 * CartePresenter Interface
 */

public interface CartePresenter {

    public void validateChange(LatLng point);

    public void onResume();

    public void onPause();

    public void onDestroy();

    public void onLowMemory();


}
