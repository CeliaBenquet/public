import org.junit.jupiter.api.Test;

import javax.xml.stream.Location;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

final class AppTest {
    @Test
    void exampleTest() {
        assertThat(1 + 1, is(2));
    }

    @Test
    void locationTriggersNorthPoleMessage(){
        LocationService location = findPosition(80.0,1.0);
        TreasureFinder user = new TreasureFinder(location);
        assertThat(user.getHint(new Position(1.0, 1.0)), is("Nope, the treasure is not at the North Pole."));


    }

    private static LocationService findPosition(double lat, double lon){
        return new LocationService() {
            @Override
            public Position getUserPosition() {
                return new Position(lat, lon);
            }
        };
    }

}
