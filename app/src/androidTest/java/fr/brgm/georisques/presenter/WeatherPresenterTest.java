package fr.brgm.georisques.presenter;

import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;
import android.widget.TextView;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.brgm.georisques.ui.fragment.tablet.WeatherFragment;
import fr.brgm.georisques.view.WeatherView;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by a601912 on 13/05/2015.
 */
public class WeatherPresenterTest {

  /*  @Test
    public void TestWeatherPresenter() {

        final String KEY_CURRENT_WEATHER = "key_current_weather";
        final String KEY_WEATHER_FORECASTS = "key_weather_forecasts";
        final long LOCATION_TIMEOUT_SECONDS = 10;
        final String TAG = WeatherFragment.class.getCanonicalName();
        final CompositeSubscription mCompositeSubscription = new CompositeSubscription();

        final SwipeRefreshLayout mSwipeRefreshLayout = new SwipeRefreshLayout(null);
        final TextView mLocationNameTextView = new TextView(null);
        final TextView mCurrentTemperatureTextView = new TextView(null);
        final ListView mForecastListView = new ListView(null);
        final TextView mAttributionTextView = new TextView(null);
        final FragmentActivity fragActivity = new FragmentActivity();

        mLocationNameTextView.setText("Lyon");
        mCurrentTemperatureTextView.setText("26");
        mForecastListView.addFooterView(mLocationNameTextView);
        mAttributionTextView.setText("attribution");

        final WeatherView w = new WeatherView() {

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
                return fragActivity;
            }
        };*/
    /*
     * Instanciate WeatherPresenterImpl
     */
     //   WeatherPresenterImpl weatherPresenterImp = new WeatherPresenterImpl(w);
    /*
     * Get parameters of the constructor
     */
      //  WeatherView weather = weatherPresenterImp.weatherView;

    /*
     * Get values from w
     */
     /*   String keyCurrentResult = w.getKeyCurrentWeather();
        String keyForecastResult = w.getKeyWeatherForecast();

        Long timeOutResult = w.getLocationTime();
        String tagResult = w.getTagWeather();

        CompositeSubscription compositeResult = w.getCompositeSubscription();
        SwipeRefreshLayout swipeResult = w.getSwipeRefrechLayout();

        TextView locationResult = w.getLocationName();
        TextView currentTempResult = w.getCurrentTemperature();
        ListView forecastListResult = w.getForecastList();
        TextView attributionResult = w.getAttribution();

        FragmentActivity fragActivityResult = w.getFragActivity();*/

        /*
         * Assert
         */
      /*  Assert.assertEquals(w, weather);

        Assert.assertEquals(KEY_CURRENT_WEATHER, keyCurrentResult);
        Assert.assertEquals(KEY_WEATHER_FORECASTS, keyForecastResult);

        Assert.assertEquals(LOCATION_TIMEOUT_SECONDS, timeOutResult, 0.0);
        Assert.assertEquals(TAG, tagResult);

        Assert.assertEquals(mCompositeSubscription, compositeResult);
        Assert.assertEquals(mSwipeRefreshLayout, swipeResult);

        Assert.assertEquals(mLocationNameTextView, locationResult);
        Assert.assertEquals(mCurrentTemperatureTextView, currentTempResult);
        Assert.assertEquals(mForecastListView, forecastListResult);
        Assert.assertEquals(mAttributionTextView, attributionResult);

        Assert.assertEquals(fragActivity, fragActivityResult);
    }

    @Test
    public void TestUpdateWeather(){
        //todo
    }*/

}
