package ch.epfl.sweng.project;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import ch.epfl.sweng.project.geocoding.GeocodingService;
import ch.epfl.sweng.project.location.Location;
import ch.epfl.sweng.project.location.LocationService;
import ch.epfl.sweng.project.utils.CompletionStageLiveData;
import ch.epfl.sweng.project.weather.WeatherForecast;
import ch.epfl.sweng.project.weather.WeatherService;

public class WeatherActivityViewModel extends ViewModel {

    private LocationService mLocationService;
    private WeatherService mWeatherService;
    private GeocodingService mGeocodingService;

    private MutableLiveData<WeatherForecast> currentWeather = new MutableLiveData<>(null);
    private LiveData<Boolean> canQueryWeather;
    private MutableLiveData<Boolean> isUsingGPS = new MutableLiveData<>(false);
    private MutableLiveData<Location> currentLocation;
    private MutableLiveData<String> selectedAddress = new MutableLiveData<>(null);
    private MutableLiveData<Location> locationAtSelectedAddress;
    private LiveData<Location> selectedLocation;


    @ViewModelInject
    public WeatherActivityViewModel(LocationService locationService, WeatherService weatherService,
                                    GeocodingService geocodingService){
        mLocationService = locationService;
        mGeocodingService = geocodingService;
        mWeatherService = weatherService;

        this.currentLocation = new MutableLiveData<>(null);
        this.mLocationService.subscribeToLocationUpdates(currentLocation::postValue);


        this.locationAtSelectedAddress = Transformations.switchMap(selectedAddress, address -> {
            if (address != null) {
                return new CompletionStageLiveData<>(mGeocodingService
                        .getLocation(address));
            } else {
                return null;
            }
        });

        this.selectedLocation = Transformations.switchMap(isUsingGPS, useGPS -> useGPS ? currentLocation : locationAtSelectedAddress);
        this.canQueryWeather = Transformations.map(selectedLocation, Objects::nonNull);

    }


    public void setIsUsingGPS(Boolean usingGPS) {
        this.isUsingGPS.postValue(usingGPS);
    }

    public void setSelectedAddress(String selectedAddress) {
        this.selectedAddress.postValue(selectedAddress);
    }

    public void refreshWeather() {
        Location loc = this.selectedLocation.getValue();
        if (loc == null) {
            Log.e("WeatherActivityViewModel", "Trying to get weather but no location");
            this.currentWeather.postValue(null);
        } else {
            CompletableFuture<WeatherForecast> future = this.mWeatherService.getForecast(loc);
            future.exceptionally(ex -> {
                ex.printStackTrace();
                return null;
            }).thenAccept(this.currentWeather::postValue);
        }
    }

    public LiveData<Boolean> canQueryWeather() {
        return this.canQueryWeather;
    }
    public LiveData<Boolean> getIsUsingGPS() {
        return this.isUsingGPS;
    }

    public LiveData<String> getSelectedAddress() {
        return this.selectedAddress;
    }

    public LiveData<WeatherForecast> getWeather() {
        return this.currentWeather;
    }

}
