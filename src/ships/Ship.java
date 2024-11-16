package ships;

import java.util.List;

public interface Ship {
    boolean isHit(int x, int y);
    boolean isSunk();
    List<int[]> getCoordinates();
}
