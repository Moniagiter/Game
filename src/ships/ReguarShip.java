package ships;

import java.util.List;

public class ReguarShip implements Ship {
    private final int size;
    private final List<int[]> coordinates;
    private int hits;

    public ReguarShip(int size, List<int[]> coordinates) {
        this.size = size;
        this.coordinates = coordinates;
        this.hits = 0;
    }

    @Override
    public boolean isHit(int x, int y) {
        for (int[] coord : coordinates) {
            if (coord[0] == x && coord[1] == y) {
                hits++;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isSunk() {
        return hits == size;
    }

    @Override
    public List<int[]> getCoordinates() {
        return coordinates;
    }
}
