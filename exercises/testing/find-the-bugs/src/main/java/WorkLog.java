import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public final class WorkLog {
    private final Map<Person, Integer> hoursWorked;
    private final Map<Person, ZonedDateTime> workStart;
    private final FakeClock clock;

    public WorkLog(FakeClock clock_) {
        hoursWorked = new HashMap<>();
        workStart = new HashMap<>();
        clock = clock_;
    }

    public void startWorking(Person person) {
        if (person.isMinor() || person.isElderly()) {
            throw new IllegalArgumentException("This person is not allowed to log hours for work!");
        }
        if (workStart.containsKey(person)) {
            throw new IllegalStateException("This person has already started working!");
        }

        workStart.put(person, ZonedDateTime.now(clock));
    }

    public void stopWorking(Person person) {
        ZonedDateTime start = workStart.getOrDefault(person, null);
        if (start == null) {
            throw new IllegalStateException("This person hasn't started working!");
        }

        ZonedDateTime stop = ZonedDateTime.now(clock);

        // Automatic calculation of the duration thanks to Java library
        int hours = (int) start.until(stop, ChronoUnit.HOURS);

        hoursWorked.merge(person, hours, Integer::sum);
        workStart.remove(person);
    }

    public int getHoursWorked(Person person) {
        return hoursWorked.get(person);
    }
}

