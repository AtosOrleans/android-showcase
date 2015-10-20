package fr.brgm.georisques.model;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by a601912 on 13/05/2015.
 */
public class WeatherForecastTest {

    @Test
    public void ConstructorCurrentWeather() {
        final String mLocationName = "Orleans";
        final long mTimestamp = 10;
        final String mDescription = "Ciel couvert";
        final float mMinimumTemperature = 2;
        final float mMaximumTemperature = 11;

        WeatherForecast w = new WeatherForecast(mLocationName, mTimestamp, mDescription, mMinimumTemperature, mMaximumTemperature);

        String locationResult = w.getLocationName();
        long timeResult = w.getTimestamp();
        String descriptionResult = w.getDescription();
        float minimumResult = w.getMinimumTemperature();
        float maximumResult = w.getMaximumTemperature();

        /*
         * Test if values are correct
         */

        Assert.assertEquals(mLocationName, locationResult);
        Assert.assertEquals(mTimestamp, timeResult);
        Assert.assertEquals(mDescription, descriptionResult);
        Assert.assertEquals(mMinimumTemperature, minimumResult, 0.0);
        Assert.assertEquals(mMaximumTemperature, maximumResult, 0.0 );
    }
}

