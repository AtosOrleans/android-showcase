package fr.brgm.georisques.services;

import com.google.gson.annotations.SerializedName;

import org.apache.http.HttpException;

import java.util.ArrayList;
import java.util.List;

import fr.brgm.georisques.model.CurrentCity;
import fr.brgm.georisques.model.WeatherForecast;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by a601912 on 20/05/2015.
 */
public class SearchCityService {

    private static final String WEB_SERVICE_GEONAMES_BASE_URL = "http://services.gisgraphy.com";
    private final ListCityWebService listCityWebService;

    public SearchCityService(){
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestInterceptor.RequestFacade request) {
                request.addHeader("Accept", "application/json");
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(WEB_SERVICE_GEONAMES_BASE_URL)
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        listCityWebService = restAdapter.create(ListCityWebService.class);
    }

    private interface ListCityWebService {
        @GET("/fulltext/fulltextsearch?")
        Observable<CurrentCityDataEnvelope> fetchCurrentCity(@Query("p") String text);
    }

    public Observable<CurrentCity> fetchCurrentCity(final String text) {
       return listCityWebService.fetchCurrentCity(text)
                .flatMap(new Func1<CurrentCityDataEnvelope,
                        Observable<? extends CurrentCityDataEnvelope>>() {

                    // Error out if the request was not successful.
                    @Override
                    public Observable<? extends CurrentCityDataEnvelope> call(
                            final CurrentCityDataEnvelope data) {
                        return data.filterWebServiceErrors();
                    }
                    public Observable<? extends CurrentCity> call(CurrentCity currentCity) {
                        return null;
                    }
                }).map(new Func1<CurrentCityDataEnvelope, CurrentCity>() {

                    // Parse the result and build a CurrentWeather object.
                    @Override
                    public CurrentCity call(final CurrentCityDataEnvelope data) {
                        return new CurrentCity(data.locationName);
                    }
                });
    }

    /**
     * Base class for results returned by the weather web service.
     */
    private class CityDataEnvelope {
        @SerializedName("cod")
        private int httpCode;

        class City {
            public String description;
        }

        /**
         * The web service always returns a HTTP header code of 200 and communicates errors
         * through a 'cod' field in the JSON payload of the response body.
         */
        public Observable filterWebServiceErrors() {
            if (httpCode == 200) {
                return Observable.just(this);
            } else {
                return Observable.error(
                        new HttpException("There was a problem fetching the weather data."));
            }
        }
    }

    /**
     * Data structure for current city results returned by the web service.
     */
    private class CurrentCityDataEnvelope extends CityDataEnvelope {
        public String locationName;

        class Main {
            public String name;
        }
    }


}
