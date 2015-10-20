package fr.brgm.georisques.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.brgm.georisques.R;
import fr.brgm.georisques.model.WeatherForecast;
import fr.brgm.georisques.util.DayFormatter;
import fr.brgm.georisques.util.TemperatureFormatter;

/**
 * Created by a601912 on 04/05/2015.
 */

/**
 * Provides items for our list view.
 */
public class WeatherForecastListAdapter extends ArrayAdapter {

    public WeatherForecastListAdapter(final List<WeatherForecast> weatherForecasts,
                                      final Context context) {
        super(context, 0, weatherForecasts);
    }

    @Override
    public boolean isEnabled(final int position) {
        return false;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.weather_forecast_list_item, null);

            viewHolder = new ViewHolder();
            viewHolder.dayTextView = (TextView) convertView.findViewById(R.id.day);
            viewHolder.descriptionTextView = (TextView) convertView
                    .findViewById(R.id.description);
            viewHolder.maximumTemperatureTextView = (TextView) convertView
                    .findViewById(R.id.maximum_temperature);
            viewHolder.minimumTemperatureTextView = (TextView) convertView
                    .findViewById(R.id.minimum_temperature);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final WeatherForecast weatherForecast = (WeatherForecast) getItem(position);

        final DayFormatter dayFormatter = new DayFormatter(getContext());
        final String day = dayFormatter.format(weatherForecast.getTimestamp());
        viewHolder.dayTextView.setText(day);
        viewHolder.descriptionTextView.setText(weatherForecast.getDescription());
        viewHolder.maximumTemperatureTextView.setText(
                TemperatureFormatter.format(weatherForecast.getMaximumTemperature()));
        viewHolder.minimumTemperatureTextView.setText(
                TemperatureFormatter.format(weatherForecast.getMinimumTemperature()));

        return convertView;
    }

    /**
     * Cache to avoid doing expensive findViewById() calls for each getView().
     */
    private class ViewHolder {
        private TextView dayTextView;
        private TextView descriptionTextView;
        private TextView maximumTemperatureTextView;
        private TextView minimumTemperatureTextView;
    }
}