package fr.brgm.georisques.ui.fragment.tablet;

/**
 * Created by a601912 on 29/04/2015.
 */


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.gms.maps.model.LatLng;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import fr.brgm.georisques.adapter.WeatherForecastListAdapter;
import fr.brgm.georisques.presenter.WeatherPresenter;
import fr.brgm.georisques.presenter.WeatherPresenterImpl;
import fr.brgm.georisques.util.BusProvider;
import fr.brgm.georisques.R;
import fr.brgm.georisques.model.WeatherForecast;
import fr.brgm.georisques.view.WeatherView;
import rx.subscriptions.CompositeSubscription;

/**
 * Weather Fragment.
 * <p/>
 * Displays the current weather as well as a 7 day forecast for our location. Data is loaded
 * from a web service.
 */
public class WeatherFragment extends Fragment implements WeatherView {

    public WeatherPresenter weatherPresenter;

    private static final String KEY_CURRENT_WEATHER = "key_current_weather";
    private static final String KEY_WEATHER_FORECASTS = "key_weather_forecasts";
    private static final long LOCATION_TIMEOUT_SECONDS = 10;
    private static final String TAG = WeatherFragment.class.getCanonicalName();

    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView mLocationNameTextView;
    private TextView mCurrentTemperatureTextView;
    private ListView mForecastListView;
    private TextView mAttributionTextView;

    public WeatherFragment(){
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        BusProvider.getInstance().register(this);

        // default initialisation
        final LatLng p = new LatLng(50, 1.924);
        weatherPresenter = new WeatherPresenterImpl(this);

        final View rootView = inflater.inflate(R.layout.fragment_weather, container, false);

        mLocationNameTextView = (TextView) rootView.findViewById(R.id.location_name);
        mCurrentTemperatureTextView = (TextView) rootView.findViewById(R.id.current_temperature);

        // Set up list view for weather forecasts.
        mForecastListView = (ListView) rootView.findViewById(R.id.weather_forecast_list);
        final WeatherForecastListAdapter adapter = new WeatherForecastListAdapter(
                new ArrayList<WeatherForecast>(), getActivity());
        mForecastListView.setAdapter(adapter);

        mAttributionTextView = (TextView) rootView.findViewById(R.id.attribution);
        mAttributionTextView.setVisibility(View.INVISIBLE);

        // Set up swipe refresh layout.
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView
                .findViewById(R.id.swipe_refresh_container);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.ColorBlue,
                android.R.color.black,
                R.color.ColorBlue,
                android.R.color.black);

       //To instanciate default meteo
       mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                weatherPresenter.updateWeather(p);
            }

       });

        weatherPresenter.updateWeather(p);

        return rootView;
    }

    //Update weather with click value
    @Subscribe
    public void update(final LatLng l) {
        weatherPresenter.updateWeather(l);
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);

    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);

    }

    @Override
    public void onDestroy() {
        mCompositeSubscription.unsubscribe();
        super.onDestroy();
    }

    /*
     * Getter
     */
    @Override
    public String getKeyCurrentWeather() {
        return KEY_CURRENT_WEATHER;
    }

    @Override
    public String getKeyWeatherForecast() {
        return KEY_WEATHER_FORECASTS;
    }

    @Override
    public Long getLocationTime() {
        return LOCATION_TIMEOUT_SECONDS;
    }

    @Override
    public String getTagWeather() {
        return TAG;
    }

    @Override
    public CompositeSubscription getCompositeSubscription() {
        return mCompositeSubscription;
    }

    @Override
    public SwipeRefreshLayout getSwipeRefrechLayout() {
        return mSwipeRefreshLayout;
    }

    @Override
    public TextView getLocationName() {
        return mLocationNameTextView;
    }

    @Override
    public TextView getCurrentTemperature() {
        return mCurrentTemperatureTextView;
    }

    @Override
    public ListView getForecastList() {
        return mForecastListView;
    }

    @Override
    public TextView getAttribution() {
        return mAttributionTextView;
    }

    @Override
    public FragmentActivity getFragActivity() {
        return getActivity();
    }

}
