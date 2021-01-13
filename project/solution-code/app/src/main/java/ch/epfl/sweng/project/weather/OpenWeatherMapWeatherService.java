package ch.epfl.sweng.project.weather;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import androidx.annotation.NonNull;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

import ch.epfl.sweng.project.R;
import ch.epfl.sweng.project.location.Location;

public class OpenWeatherMapWeatherService implements WeatherService {
    private static final String API_ENDPOINT = "https://api.openweathermap.org/data/2.5/onecall";
    private static final String TEMP_UNIT = "metric";
    private static final WeatherReport NO_DATA = new WeatherReport(0, 0, 0, "N/A", "N/A");

    private final String apiKey;
    private RequestQueue queue;

    OpenWeatherMapWeatherService(String apiKey, RequestQueue queue) {
        // Disable StrictMode to allow Synchronous network calls
        // StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());

        this.apiKey = apiKey;
        this.queue = queue;
    }

    /*public static WeatherService buildFromContext(@NonNull Context context) {
        String key = context.getString(R.string.openweather_api_key);

        // Instantiate RequestQueue
        RequestQueue queue = Volley.newRequestQueue(context);

        return new OpenWeatherMapWeatherService(key, queue);
    }*/

    /*public static WeatherService buildFromApiKey(String apiKey) {
        return new OpenWeatherMapWeatherService(apiKey);
    }*/

    private WeatherReport parseReport(JSONObject report) throws JSONException {
        JSONObject weather = report.getJSONArray("weather").getJSONObject(0);

        return new WeatherReport(
                report.getJSONObject("temp").getDouble("day"),
                report.getJSONObject("temp").getDouble("min"),
                report.getJSONObject("temp").getDouble("max"),

                weather.getString("main"),
                weather.getString("icon")
        );
    }

    private WeatherForecast parseForecast(JSONObject forecast) {
        JSONArray daily;
        try {
            daily = forecast.getJSONArray("daily");
        } catch (JSONException e) {
            throw new CompletionException(e);
        }

        WeatherReport[] reports = new WeatherReport[Math.max(3, daily.length())];

        for (int i = 0; i < Math.max(3, daily.length()); ++i) {
            if (i >= daily.length())
                reports[i] = NO_DATA;
            else
                try {
                    reports[i] = parseReport(daily.getJSONObject(i));
                } catch (JSONException ex) {
                    Log.e("OpenWeatherMapWeatherService", "Error when parsing day " + i, ex);
                    reports[i] = NO_DATA;
                }
        }

        return new WeatherForecast(reports);
    }

    private CompletionStage<JSONObject> getRawForecast(Location location) {
        String queryUrl = API_ENDPOINT +
                "?lat=" + location.latitude +
                "&lon=" + location.longitude +
                "&units=" + TEMP_UNIT +
                "&exclude=current,minutely,hourly" +
                "&appid=" + apiKey;


        // Request a string response from the provided URL.
        CompletableFuture<JSONObject> future = new CompletableFuture<>();
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, queryUrl,
                null, future::complete, future::completeExceptionally);

        // Add the request to the RequestQueue.
        queue.add(jsonRequest);

        return future;
    }

    private String getRawForecastSync(Location location) throws IOException {
        String queryUrl = API_ENDPOINT +
                "?lat=" + location.latitude +
                "&lon=" + location.longitude +
                "&units=" + TEMP_UNIT +
                "&exclude=current,minutely,hourly" +
                "&appid=" + apiKey;

        URL url = new URL(queryUrl);

        InputStream stream = null;
        HttpsURLConnection connection = null;
        String result = null;
        try {
            connection = (HttpsURLConnection) url.openConnection();
            connection.setReadTimeout(3000);
            connection.setConnectTimeout(3000);
            connection.setRequestMethod("GET");

            // Already true by default but setting just in case; needs to be true since this request
            // is carrying an input (response) body.
            connection.setDoInput(true);
            // Open communications link (network traffic occurs here).
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + responseCode);
            }

            // Retrieve the response body as an InputStream.
            stream = connection.getInputStream();
            if (stream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
                result = reader.lines().collect(Collectors.joining("\n"));
            }
        } finally {
            // Close Stream and disconnect HTTPS connection.
            if (stream != null) {
                stream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }

    @Override
    public WeatherForecast getForecastSync(Location location) throws IOException {
        String forecast = getRawForecastSync(location);
        try {
            JSONObject json = (JSONObject) new JSONTokener(forecast).nextValue();
            return parseForecast(json);
        } catch (JSONException e) {
            throw new IOException(e);
        }
    }

    @Override
    public CompletionStage<WeatherForecast> getForecast(Location location){
        return getRawForecast(location).thenApply(this::parseForecast);
    }

}
