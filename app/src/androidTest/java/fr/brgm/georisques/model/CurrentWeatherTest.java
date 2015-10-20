package fr.brgm.georisques.model;

import junit.framework.Assert;

import org.junit.Test;


/**
 * Created by a601912 on 13/05/2015.
 */
public class CurrentWeatherTest {

    @Test
    public void ConstructorCurrentWeather() {
        final String locationName = "Paris";
        final long timestamp = 10;
        final String description = "Ciel bleu";
        final float temperature = 37;
        final float minimumTemperature = 10;
        final float maximumTemperature = 37;

        CurrentWeather c = new CurrentWeather(locationName, timestamp, description, temperature, minimumTemperature, maximumTemperature);
        float result = c.getTemperature();
        /*
         * Test if value is correct
         */
        Assert.assertEquals(temperature, result, 0.0);
    }
}
