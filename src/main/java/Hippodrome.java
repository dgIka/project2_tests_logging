import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static java.util.Objects.isNull;

public class Hippodrome {
    private static final Logger logger = LogManager.getLogger(Hippodrome.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
    private final List<Horse> horses;

    public Hippodrome(List<Horse> horses) {
        if (isNull(horses)) {
            logger.error("{} ERROR Hippodrome: Horses list is null", dateFormat.format(new Date()));
            throw new IllegalArgumentException("Horses cannot be null.");
        } else if (horses.isEmpty()) {
            logger.error("{} ERROR Hippodrome: Horses list is empty", dateFormat.format(new Date()));
            throw new IllegalArgumentException("Horses cannot be empty.");
        }

        this.horses = horses;
        logger.debug("{} DEBUG Hippodrome: Создание Hippodrome, лошадей [{}]"
                , dateFormat.format(new Date())
                , horses.size());
    }

    public List<Horse> getHorses() {
        return Collections.unmodifiableList(horses);
    }

    public void move() {
        horses.forEach(Horse::move);
    }

    public Horse getWinner() {
        return horses.stream()
                .max(Comparator.comparing(Horse::getDistance))
                .get();
    }
}
