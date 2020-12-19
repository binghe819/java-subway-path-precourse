package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import subway.error.StationNotExistException;

public class StationRepository {
    private static final List<Station> stations = new ArrayList<>();

    public static List<Station> stations() {
        return Collections.unmodifiableList(stations);
    }

    public static void addStation(Station station) {
        stations.add(station);
    }

    public static void addStations(List<Station> stations) {
        stations.addAll(stations);
    }

    public static boolean checkExistsStation(String name) {
        for (Station station : stations) {
            if (station.getName().equals(name)) {
                return true;
            }
        }
        throw new StationNotExistException();
    }

    public static Station findByName(String name) {
        return stations.stream()
            .filter(station -> station.getName().equals(name))
            .findAny()
            .orElseThrow(() -> {throw new StationNotExistException();});
    }

    public static boolean deleteStation(String name) {
        return stations.removeIf(station -> Objects.equals(station.getName(), name));
    }

    public static void deleteAll() {
        stations.clear();
    }
}
