package fr.brgm.georisques.ui.fragment.tablet;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.squareup.otto.Subscribe;
import fr.brgm.georisques.R;
import fr.brgm.georisques.presenter.BandeauPresenterImpl;
import fr.brgm.georisques.ui.event.CarteMapEvent;
import fr.brgm.georisques.ui.event.GpsMapEvent;
import fr.brgm.georisques.util.BusProvider;
import fr.brgm.georisques.view.BandeauView;

/**
 * Created by a601912 on 14/04/2015.
 */

/*
 * Fragment which display adress, latitude and longitude
 */
public class BandeauFragment extends Fragment implements BandeauView {


    private TextView adresse;

    //@Icicle
    //ExampleParcel exampleParcel;
    /*
     * Latitude and longitude
     */
    private TextView gps;
    public BandeauPresenterImpl bandeauPresenter;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.fragment_bandeau, container, false);
        mainView.setBackgroundColor(Color.parseColor("#ffffff"));
        adresse = (TextView) mainView.findViewById(R.id.adress);
        gps = (TextView) mainView.findViewById(R.id.gps);
        bandeauPresenter = new BandeauPresenterImpl(this);
        //Icepick.restoreInstanceState(this, savedInstanceState);
        return mainView;
    }

    @Override public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    //We get the adress from the bus
    @Subscribe
    public void getAdress(String adress) {
        bandeauPresenter.updateAdress(adress);

    }

    //We get the CarteMapEvent from the bus
    @Subscribe
    public void getGps(CarteMapEvent carteMapEvent) {
        bandeauPresenter.updatePointFromCarte(carteMapEvent);
    }

    //We get the GpsMapEvent from the bus
    @Subscribe
    public void getGpsFromLoc(GpsMapEvent gpsMapEvent) {
        bandeauPresenter.updatePointFromGPS(gpsMapEvent);
    }

    /*
     * Getter
     */
    @Override
    public TextView getAdress() {
        return adresse;
    }

    @Override
    public TextView getPoint() {
        return gps;
    }

}
