package ch.epfl.sweng.project.location;

import java.util.function.Consumer;

/**
 * This service allows you to get the current location of the user.
 */
public interface LocationService {
    /**
     * Finds the current location of the user and pass it to the given callback function.
     *
     * @return the current location of the user
     */
    Location getCurrentLocation();


    void subscribeToLocationUpdates(Consumer<Location> consumer);
}
