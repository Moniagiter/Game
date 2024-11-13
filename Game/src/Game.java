
import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Grid grid;
    private final List<Ship> ships;

    public Game() {
        this.grid = new Grid(10);
        this.ships = new ArrayList<>();
        ships.add(new Ship(2, List.of(new int[]{0, 0}, new int[]{0, 1})));
        ships.add(new Ship(3, List.of(new int[]{2, 2}, new int[]{2, 3}, new int[]{2, 4})));
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
                    grid.updateGrid(x, y, '+');
                    System.out.println("Попадання!");
                    if (ship.isSunk()) {
                        System.out.println("Ви потопили корабель!");
                        for (int[] coord : ship.getCoordinates()) {
                            grid.updateGrid(coord[0], coord[1], 'X');
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

    public void startGame() {
        System.out.println("Ласкаво просимо в Морський бій!");
        while (ships.stream().anyMatch(ship -> !ship.isSunk())) {
            grid.display(); // Виводимо поле на кожному ході
            playTurn();
        }
        System.out.println("Ви виграли! Всі кораблі потоплені.");
        System.out.println("Фінальне поле:");
        grid.display(); // Виводимо поле після завершення гри
    }


    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}