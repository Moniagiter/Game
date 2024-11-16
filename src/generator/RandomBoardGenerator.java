package generator;

import ships.ReguarShip;
import ships.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomBoardGenerator implements BoardGenerator {
    private final int gridSize;
    private final Random random;

    public RandomBoardGenerator(int gridSize) {
        this.gridSize = gridSize;
        this.random = new Random();
    }

    @Override
    public List<Ship> generate() {
        List<Ship> ships = new ArrayList<>();

        // Розмір кораблів
        int[] shipSizes = {2, 3, 4};

        // Спроба додати кораблі
        for (int size : shipSizes) {
            boolean placed = false;

            while (!placed) {
                // Випадковий початок корабля
                int x = random.nextInt(gridSize);
                int y = random.nextInt(gridSize);

                // Випадковий напрямок (0 - горизонтальний, 1 - вертикальний)
                boolean isHorizontal = random.nextBoolean();

                // Перевірка, чи можна додати корабель
                if (canPlaceShip(x, y, size, isHorizontal, ships)) {
                    List<int[]> coordinates = new ArrayList<>();
                    for (int i = 0; i < size; i++) {
                        coordinates.add(isHorizontal ? new int[]{x, y + i} : new int[]{x + i, y});
                    }
                    ships.add(new ReguarShip(size, coordinates));
                    placed = true;
                }
            }
        }

        return ships;
    }

    // Перевірка, чи можна розмістити корабель на заданих координатах
    private boolean canPlaceShip(int x, int y, int size, boolean isHorizontal, List<Ship> ships) {
        for (int i = 0; i < size; i++) {
            int newX = isHorizontal ? x : x + i;
            int newY = isHorizontal ? y + i : y;

            // Перевірка виходу за межі поля
            if (newX < 0 || newX >= gridSize || newY < 0 || newY >= gridSize) {
                return false;
            }

            // Перевірка перетину з іншими кораблями
            for (Ship ship : ships) {
                for (int[] coord : ship.getCoordinates()) {
                    if (coord[0] == newX && coord[1] == newY) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
