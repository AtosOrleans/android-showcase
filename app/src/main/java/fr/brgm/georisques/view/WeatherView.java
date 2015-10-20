package fr.brgm.georisques.view;

import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;
import android.widget.TextView;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by a601912 on 11/05/2015.
 */
public interface WeatherView {

    public String getKeyCurrentWeather();

    public String getKeyWeatherForecast();

    public Long getLocationTime();

    public String getTagWeather();

    public CompositeSubscription getCompositeSubscription();

    public SwipeRefreshLayout getSwipeRefrechLayout();

    public TextView getLocationName();

    public TextView getCurrentTemperature();

    public ListView getForecastList();

    public TextView getAttribution();

    public FragmentActivity getFragActivity();
}
