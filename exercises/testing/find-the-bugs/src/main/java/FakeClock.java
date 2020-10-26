import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

public final class FakeClock extends Clock {
    private Instant instant;

    void setInstant(Instant instant) { this.instant = instant; }

    @Override
    public ZoneId getZone() { return ZoneId.of("UTC");}

    @Override
    public Clock withZone(ZoneId zoneId) { throw new RuntimeException("This method should not be called");}

    @Override
    public Instant instant() { return instant; }
}