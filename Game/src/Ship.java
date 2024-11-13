
import java.util.List;

public class Ship {
    private final int size;
    private final List<int[]> coordinates;
    private int hits;

    public Ship(int size, List<int[]> coordinates) {
        this.size = size;
        this.coordinates = coordinates;
        this.hits = 0;
    }

    public boolean isHit(int x, int y) {
        for (int[] coord : coordinates) {
            if (coord[0] == x && coord[1] == y) {
                hits++;
                return true;
            }
        }
        return false;
    }

    public boolean isSunk() {
        return hits == size;
    }

    public List<int[]> getCoordinates() {
        return coordinates;
    }
}