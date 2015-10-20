package fr.brgm.georisques.presenter;

import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;

import org.apache.http.HttpException;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.inject.Inject;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import fr.brgm.georisques.R;
import fr.brgm.georisques.adapter.WeatherForecastListAdapter;
import fr.brgm.georisques.model.CurrentWeather;
import fr.brgm.georisques.model.WeatherForecast;
import fr.brgm.georisques.services.LocationService;
import fr.brgm.georisques.services.WeatherService;
import fr.brgm.georisques.util.BusProvider;
import fr.brgm.georisques.util.TemperatureFormatter;
import fr.brgm.georisques.view.CarteView;
import fr.brgm.georisques.view.MainActivityTabletView;
import retrofit.RetrofitError;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by a601912 on 21/05/2015.
 */
public class MainActivityTabletPresenterImpl implements MainActivityTabletPresenter{

    private MainActivityTabletView mainView;

    @Inject
    public MainActivityTabletPresenterImpl(MainActivityTabletView mainView){
        this.mainView = mainView;
    }

    @Override
    public void updateMap(Integer response) {
         BusProvider.getInstance().post(response);
    }
/*
    @Override
    public void updateSearch(final LatLng latLng) {

        weatherView.getSwipeRefrechLayout().setRefreshing(true);

        final LocationManager locationManager = (LocationManager) weatherView.getFragActivity()
                .getSystemService(android.content.Context.LOCATION_SERVICE);

        final LocationService locationService = new LocationService(locationManager);
        //Log.d(weatherView.getTagWeather(), "PREMIER TEST");
        // Get our current location.
        final Observable fetchDataObservable = locationService.getLocation()
                .timeout(weatherView.getLocationTime(), TimeUnit.SECONDS)
                .flatMap(new Func1<Location, Observable<HashMap<String, WeatherForecast>>>() {
                    @Override
                    public Observable<HashMap<String, WeatherForecast>> call(final Location location) {
                        //Log.d(weatherView.getTagWeather(), "DEUXIEME TEST");

                        final WeatherService weatherService = new WeatherService();
                        final double longitude = latLng.longitude;
                        final double latitude = latLng.latitude;

                        return Observable.zip(
                                // Fetch current and 7 day forecasts for the location.
                                weatherService.fetchCurrentWeather(longitude, latitude),
                                weatherService.fetchWeatherForecasts(longitude, latitude),

                                // Only handle the fetched results when both sets are available.
                                new Func2<CurrentWeather, List<WeatherForecast>,
                                        HashMap<String, WeatherForecast>>() {
                                    @Override
                                    public HashMap call(final CurrentWeather currentWeather,
                                                        final List<WeatherForecast> weatherForecasts) {

                                        HashMap weatherData = new HashMap();
                                        weatherData.put(weatherView.getKeyCurrentWeather(), currentWeather);
                                        weatherData.put(weatherView.getKeyWeatherForecast(), weatherForecasts);
                                        return weatherData;
                                    }
                                }
                        );
                    }
                });

        weatherView.getCompositeSubscription().add(fetchDataObservable
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<HashMap<String, WeatherForecast>>() {
                            @Override
                            public void onNext(final HashMap<String, WeatherForecast> weatherData) {
                                // Update UI with current weather.
                                final CurrentWeather currentWeather = (CurrentWeather) weatherData
                                        .get(weatherView.getKeyCurrentWeather());
                                weatherView.getLocationName().setText(currentWeather.getLocationName());
                                weatherView.getCurrentTemperature().setText(
                                        TemperatureFormatter.format(currentWeather.getTemperature()));

                                // Update weather forecast list.
                                final List<WeatherForecast> weatherForecasts = (List<WeatherForecast>)
                                        weatherData.get(weatherView.getKeyWeatherForecast());
                                final WeatherForecastListAdapter adapter = (WeatherForecastListAdapter)
                                        weatherView.getForecastList().getAdapter();
                                adapter.clear();
                                adapter.addAll(weatherForecasts);
                            }

                            @Override
                            public void onCompleted() {
                                weatherView.getSwipeRefrechLayout().setRefreshing(false);
                                weatherView.getAttribution().setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onError(final Throwable error) {
                                weatherView.getSwipeRefrechLayout().setRefreshing(false);

                                if (error instanceof TimeoutException) {
                                    Crouton.makeText((android.app.Activity) weatherView.getFragActivity(),
                                            R.string.error_location_unavailable, Style.ALERT).show();
                                } else if (error instanceof RetrofitError
                                        || error instanceof HttpException) {
                                    Crouton.makeText((android.app.Activity) weatherView.getFragActivity(),
                                            R.string.error_fetch_weather, Style.ALERT).show();
                                } else {
                                    Log.e(weatherView.getTagWeather(), error.getMessage());
                                    error.printStackTrace();
                                    throw new RuntimeException("See inner exception");
                                }
                            }
                        })
        );
    }*/
}
