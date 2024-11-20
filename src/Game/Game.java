package Game;

import board.Board;
import board.RegularBoard;
import generator.RandomBoardGenerator;
import player.Player;
import ships.Ship;
import player.ConsolePlayer;

import java.util.List;

public class Game {
    private final Board grid;
    private final List<Ship> ships;
    private final Player player;

    public Game(Board grid, List<Ship> ships, Player player) {
        this.grid = grid;
        this.ships = ships;
        this.player = player;
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
            int[] coords = player.getCoordinates();
            int x = coords[0];
            int y = coords[1];
            boolean hit = false;
            for (Ship ship : ships) {
                if (ship.isHit(x, y)) {
                    hit = true;
                    ((RegularBoard) grid).updateGrid(x, y, 'X');
                    System.out.println("Попадання!");
                    if (ship.isSunk()) {
                        System.out.println("Ви потопили корабель!");
                        for (int[] coord : ship.getCoordinates()) {
                            ((RegularBoard) grid).updateGrid(coord[0], coord[1], '+');
                        }
                    }
                    break;
                }
            }
            if (!hit) {
                ((RegularBoard) grid).updateGrid(x, y, 'O');
                System.out.println("Промах!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Game game = new Game(new RegularBoard(10), new RandomBoardGenerator(10).generate(), new ConsolePlayer());
        game.startGame();
    }
}
