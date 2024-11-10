import java.util.Arrays;

public class Game {
    private Grid grid;
    private Player player;

    public Game() {
        int gridSize = 10;
        grid = new Grid(gridSize);
        player = new Player();
        placeShipsRandomly();
    }

    private void placeShipsRandomly() {
        // Приклад розміщення кораблів
        // Ви можете розширити логіку для розміщення різних типів кораблів
        Ship ship1 = new Ship();
        grid.placeShip(ship1, Arrays.asList(
                new Coordinate(0, 0),
                new Coordinate(0, 1),
                new Coordinate(0, 2)
        ));

        Ship ship2 = new Ship();
        grid.placeShip(ship2, Arrays.asList(
                new Coordinate(5, 5),
                new Coordinate(6, 5),
                new Coordinate(7, 5),
                new Coordinate(8, 5)
        ));

    }

    public void start() {
        System.out.println("=== Початок гри 'Морський бій' ===");
        while (true) {
            Coordinate coord = player.getShotCoordinate(grid.getSize());
            Cell cell = grid.getCell(coord.getX(), coord.getY());
            if (cell.isHit()) {
                System.out.println("Ви вже стріляли сюди.");
                continue;
            }
            cell.hit();
            if (cell.hasEntity()) {
                Entity entity = cell.getEntity();
                if (entity instanceof Ship) {
                    Ship ship = (Ship) entity;
                    if (ship.isDestroyed()) {
                        System.out.println("Корабель знищено!");
                    } else {
                        System.out.println("Влучено в корабель!");
                    }
                }
            } else {
                System.out.println("Промах.");
            }
            if (grid.allShipsDestroyed()) {
                System.out.println("Вітаємо! Ви знищили всі кораблі.");
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Game().start();
    }
}
