// Wrap Geolocator to implement getUserPosition
public final class GeolocatorLocationService implements LocationService {
    private final Geolocator locator = new Geolocator();

    @Override
    public Position getUserPosition() {
        return locator.getUserPosition();
    }
}
