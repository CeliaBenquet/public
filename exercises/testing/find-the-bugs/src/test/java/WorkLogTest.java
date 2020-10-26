import com.sun.corba.se.spi.orbutil.threadpool.Work;

import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

final class WorkLogTest {
    @Test
    void startAndEndInSameDayHandled() {
        FakeClock clock = new FakeClock();
        WorkLog log = new WorkLog(clock);
        Person person = new Person("Lili", "Bibi", 24);

        clock.setInstant(ZonedDateTime.of(2019, 10, 11, 18, 0, 0, 0, ZoneId.of("UTC")).toInstant());
        log.startWorking(person);
        clock.setInstant(ZonedDateTime.of(2019, 10, 11, 22, 0, 0, 0, ZoneId.of("UTC")).toInstant());
        log.stopWorking(person);
        assertThat(log.getHoursWorked(person), is(4));
    }

    @Test
    void startAndEndInDifferentDaysHandled() {
        FakeClock clock = new FakeClock();
        WorkLog log = new WorkLog(clock);
        Person person = new Person("Lili", "Bibi", 24);

        clock.setInstant(ZonedDateTime.of(2019, 10, 11, 18, 0, 0, 0, ZoneId.of("UTC")).toInstant());
        log.startWorking(person);
        clock.setInstant(ZonedDateTime.of(2019, 10, 12, 10, 0, 0, 0, ZoneId.of("UTC")).toInstant());
        log.stopWorking(person);
        assertThat(log.getHoursWorked(person), is(16));
    }
}