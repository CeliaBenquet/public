package ch.epfl.sweng.project.geocoding;

import android.content.Context;
import android.location.Geocoder;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import ch.epfl.sweng.project.location.Location;

public class AndroidGeocodingService implements GeocodingService {
    private final Geocoder geocoder;

    AndroidGeocodingService(Geocoder geocoder) {
        this.geocoder = geocoder;
    }

    public static final GeocodingService fromContext(Context context) {
        return new AndroidGeocodingService(new Geocoder(context));
    }

    @Override
    public Location getLocationSync(@NonNull String address) throws IOException {
        return this.geocoder.getFromLocationName(address, 5)
                .stream()
                .filter(addr -> addr.hasLatitude() && addr.hasLongitude())
                .map(addr -> new Location(addr.getLatitude(), addr.getLongitude()))
                .findFirst()
                .get();
    }

    @Override
    public CompletionStage<Location> getLocation(@NonNull String address) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return getLocationSync(address);
            } catch (IOException e) {
                throw new CompletionException(e);
            }
        });
    }

    @Override
    public Address getAddressSync(@NonNull Location location) throws IOException {
        android.location.Address address = this.geocoder.getFromLocation(location.latitude, location.longitude, 1).get(0);
        List<String> addressLines = new ArrayList<>();
        for (int i = 0; i <= address.getMaxAddressLineIndex(); ++i)
            addressLines.add(address.getAddressLine(i));

        return new Address(addressLines);
    }

    @Override
    public CompletionStage<Address> getAddress(@NonNull Location location) throws IOException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return getAddressSync(location);
            } catch (IOException e) {
                throw new CompletionException(e);
            }
        });
    }


}
