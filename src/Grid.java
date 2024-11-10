import java.util.ArrayList;
import java.util.List;

public class Grid {
    private Cell[][] cells;
    private int size;
    private List<Ship> ships;

    public Grid(int size) {
        this.size = size;
        cells = new Cell[size][size];
        ships = new ArrayList<>();
        initializeGrid();
    }

    private void initializeGrid() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                cells[x][y] = new Cell(x, y);
            }
        }
    }

    public Cell getCell(int x, int y) {
        if (Coordinate.isValidCoordinate(x, y, size)) {
            return cells[x][y];
        }
        return null;
    }

    public boolean placeShip(Ship ship, List<Coordinate> coordinates) {
        for (Coordinate coord : coordinates) {
            Cell cell = getCell(coord.getX(), coord.getY());
            if (cell == null || cell.hasEntity()) {
                return false; // Не можна розмістити корабель тут
            }
        }
        for (Coordinate coord : coordinates) {
            Cell cell = getCell(coord.getX(), coord.getY());
            ship.addCell(cell);
        }
        ships.add(ship);
        return true;
    }

    public boolean allShipsDestroyed() {
        for (Ship ship : ships) {
            if (!ship.isDestroyed()) {
                return false;
            }
        }
        return true;
    }

    public int getSize() {
        return size;
    }
}
