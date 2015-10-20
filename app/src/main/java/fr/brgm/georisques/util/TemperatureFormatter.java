package fr.brgm.georisques.util;

/**
 * Created by a601912 on 29/04/2015.
 */

public class TemperatureFormatter {

        public static String format(float temperature) {
            return String.valueOf(Math.round(temperature)) + "°";
        }
    }

