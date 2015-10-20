package fr.brgm.georisques.ui.fragment.tablet;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;
import fr.brgm.georisques.R;
import fr.brgm.georisques.presenter.CartePresenterImpl;
import fr.brgm.georisques.ui.event.GpsMapEvent;
import fr.brgm.georisques.util.BusProvider;
import fr.brgm.georisques.view.CarteView;


/**
 * Created by a601912 on 14/04/2015.
 */

/*
 * Fragment which display the map
 */
public class CarteFragment extends Fragment implements CarteView {

    MapView mMapView;
    private GoogleMap googleMap;
    private CartePresenterImpl cartePresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflat and return the layout
        View v = inflater.inflate(R.layout.fragment_carte, container, false);
        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();// needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        googleMap = mMapView.getMap();

        // adding marker
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(47.919, 1.924)).zoom(12).build();
        googleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));

        cartePresenter = new CartePresenterImpl(this);

        // adding marker in a specific point
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            public void onMapClick(LatLng point) {
                cartePresenter.validateChange(point);
            }
        });

        return v;
    }

    // We Get gpsMapEvent from the bus (infos about point and adress)
    @Subscribe
    public void updateMap(GpsMapEvent event){
        cartePresenter.validateChange(event.ptn);
    }

    /*
     * Display appropriate map
     */
    @Subscribe
    public void displayMap(Integer response){
        cartePresenter.displayMap(response);
    }


    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
         //presenter.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
        //presenter.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Produce
    public String produceEvent() {
        return "Starting up";
    }

    /*
     * Getter
     */
    @Override
    public GoogleMap getGoogleMap() {
        return googleMap;
    }

    @Override
    public MapView getMapView() {
        return mMapView;
    }

    @Override
    public FragmentActivity getFragActivity() {
        return getActivity();
    }


}
