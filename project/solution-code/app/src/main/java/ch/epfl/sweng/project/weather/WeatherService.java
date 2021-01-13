package ch.epfl.sweng.project.weather;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.util.concurrent.CompletionStage;

import ch.epfl.sweng.project.location.Location;
import dagger.Provides;
import dagger.hilt.android.qualifiers.ApplicationContext;

/**
 * This interface represents a service that enables you to get weather forecasts for a specific
 * location.
 */
public interface WeatherService {

    /**
     * Get the weather forecast at a given location.
     *
     * @param location the location for which you want to get the forecast
     * @return the weather forecast for the given location
     * @throws IOException if there is a network error of any kind
     */
    WeatherForecast getForecastSync(Location location) throws IOException;
    CompletionStage<WeatherForecast> getForecast(Location location);

    @Provides
    public static RequestQueue provideRequestQueue(@ApplicationContext Context context) {
        return Volley.newRequestQueue(context);
    }


}
