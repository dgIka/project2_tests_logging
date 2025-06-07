import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.util.Objects.isNull;

public class Horse {
    private static final Logger logger = LogManager.getLogger(Horse.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
    private final String name;
    private final double speed;
    private double distance;

    public Horse(String name, double speed, double distance) {
        if (isNull(name)) {
            logger.error("{} ERROR Horse: Name is null", dateFormat.format(new Date()));
            throw new IllegalArgumentException("Name cannot be null.");
        } else if (name.isBlank()) {
            logger.error("{} ERROR Horse: Name is blank", dateFormat.format(new Date()));
            throw new IllegalArgumentException("Name cannot be blank.");
        }
        if (speed < 0) {
            logger.error("{} ERROR Horse: Speed is negative", dateFormat.format(new Date()));
            throw new IllegalArgumentException("Speed cannot be negative.");
        }
        if (distance < 0) {
            logger.error("{} ERROR Horse: Distance is negative", dateFormat.format(new Date()));
            throw new IllegalArgumentException("Distance cannot be negative.");
        }

        this.name = name;
        this.speed = speed;
        this.distance = distance;
        logger.debug("{} DEBUG Horse: Создание Horse, имя [{}], скорость [{}]"
                , dateFormat.format(new Date())
                , name
                , speed);
    }

    public Horse(String name, double speed) {
        this(name, speed, 0);
    }

    public String getName() {
        return name;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDistance() {
        return distance;
    }

    public void move() {
        distance += speed * getRandomDouble(0.2, 0.9);
    }

    public static double getRandomDouble(double min, double max) {
        return (Math.random() * (max - min)) + min;
    }
}
