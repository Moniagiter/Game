import java.util.ArrayList;
import java.util.List;

public class Game {
    private Grid grid;
    private List<Ship> ships;

    public Game() {
        this.grid = new Grid(10); // Сітка розміром 10x10
        this.ships = new ArrayList<>();
        ships.add(new ShipRealization(2, List.of(new int[]{0, 0}, new int[]{0, 1})));
        ships.add(new ShipRealization(3, List.of(new int[]{2, 2}, new int[]{2, 3}, new int[]{2, 4})));
    }

    public void startGame() {
        System.out.println("Ласкаво просимо в Морський бій!");
        while (ships.stream().anyMatch(ship -> !ship.isSunk())) {
            grid.display();
            playTurn();
        }
        System.out.println("Ви виграли! Всі кораблі потоплені.");
        grid.display();
    }

    public void playTurn() {
        try {
            int[] coords = PlayerInput.getCoordinates();
            int x = coords[0];
            int y = coords[1];
            boolean hit = false;

            for (Ship ship : ships) {
                if (ship.isHit(x, y)) {
                    hit = true;
                    grid.updateGrid(x, y, 'X');
                    System.out.println("Попадання!");
                    if (ship.isSunk()) {
                        System.out.println("Ви потопили корабель!");
                        for (int[] coord : ship.getCoordinates()) {
                            grid.updateGrid(coord[0], coord[1], '-');
                        }
                    }
                    break;
                }
            }
            if (!hit) {
                grid.updateGrid(x, y, 'O');
                System.out.println("Промах!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}
