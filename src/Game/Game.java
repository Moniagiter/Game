package Game;

import board.ComputerBoard;
import board.HumanBoard;
import generator.ComputerBoardGenerator;
import generator.RandomBoardGenerator;
import player.ComputerPlayer;
import player.ConsolePlayer;
import player.Player;
import ships.Ship;

import java.util.List;

public class Game {
    private final HumanBoard humanBoard;
    private final ComputerBoard computerBoard;
    private final List<Ship> humanShips;
    private final List<Ship> computerShips;
    private final Player humanPlayer;
    private final Player computerPlayer;

    public Game(HumanBoard humanBoard, ComputerBoard computerBoard, List<Ship> humanShips, List<Ship> computerShips, Player humanPlayer, Player computerPlayer) {
        this.humanBoard = humanBoard;
        this.computerBoard = computerBoard;
        this.humanShips = humanShips;
        this.computerShips = computerShips;
        this.humanPlayer = humanPlayer;
        this.computerPlayer = computerPlayer;
    }

    public void startGame() {
        System.out.println("Ласкаво просимо в Морський бій!");
        boolean isHumanTurn = true;

        while (humanShips.stream().anyMatch(ship -> !ship.isSunk()) &&
                computerShips.stream().anyMatch(ship -> !ship.isSunk())) {
            if (isHumanTurn) {
                System.out.println("Хід гравця:");
                computerBoard.display();
                if (!playTurn(humanPlayer, computerShips, computerBoard, 'X', 'O', '+')) {
                    isHumanTurn = false;
                }
            } else {
                System.out.println("Хід комп'ютера:");
                humanBoard.display();
                if (!playTurn(computerPlayer, humanShips, humanBoard, '=', '#', '*')) {
                    isHumanTurn = true;
                }
            }
        }

        System.out.println("Гра завершена!");
        humanBoard.display();
        computerBoard.display();
        if (humanShips.stream().noneMatch(ship -> !ship.isSunk())) {
            System.out.println("Комп'ютер переміг!");
        } else {
            System.out.println("Гравець переміг!");
        }
    }

    private boolean playTurn(Player player, List<Ship> targetShips, Object targetGrid, char hitSymbol, char missSymbol, char sunkSymbol) {
        int[] coords = player.getCoordinates();

        if (player instanceof ComputerPlayer) {
            System.out.println("Комп'ютер атакує клітинку: " + (char) ('A' + coords[0]) + (coords[1] + 1));
        }

        int x = coords[0];
        int y = coords[1];
        boolean hit = false;

        for (Ship ship : targetShips) {
            if (ship.isHit(x, y)) {
                hit = true;
                if (targetGrid instanceof HumanBoard) {
                    ((HumanBoard) targetGrid).updateGrid(x, y, hitSymbol);
                } else if (targetGrid instanceof ComputerBoard) {
                    ((ComputerBoard) targetGrid).updateGrid(x, y, hitSymbol);
                }
                System.out.println(player instanceof ComputerPlayer ? "Комп'ютер влучив!" : "Попадання!");

                if (ship.isSunk()) {
                    System.out.println(player instanceof ComputerPlayer ? "Комп'ютер потопив корабель!" : "Ви потопили корабель!");
                    for (int[] coord : ship.getCoordinates()) {
                        if (targetGrid instanceof HumanBoard) {
                            ((HumanBoard) targetGrid).updateGrid(coord[0], coord[1], sunkSymbol);
                        } else if (targetGrid instanceof ComputerBoard) {
                            ((ComputerBoard) targetGrid).updateGrid(coord[0], coord[1], sunkSymbol);
                        }
                    }
                }
                return true;
            }
        }

        if (targetGrid instanceof HumanBoard) {
            ((HumanBoard) targetGrid).updateGrid(x, y, missSymbol);
        } else if (targetGrid instanceof ComputerBoard) {
            ((ComputerBoard) targetGrid).updateGrid(x, y, missSymbol);
        }
        System.out.println(player instanceof ComputerPlayer ? "Комп'ютер промахнувся!" : "Промах!");

        return false;
    }

    public static void main(String[] args) {
        Game game = new Game(new HumanBoard(10), new ComputerBoard(10), new RandomBoardGenerator(10).generate(), new ComputerBoardGenerator(10).generate(), new ConsolePlayer(), new ComputerPlayer(10));
        game.startGame();
    }
}
