package board;

public class ComputerBoard {
    private final int size;
    private final char[][] grid;

    public ComputerBoard(int size) {
        this.size = size;
        this.grid = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = '.';
            }
        }
    }

    public void updateGrid(int x, int y, char symbol) {
        if (x < 0 || x >= size || y < 0 || y >= size) {
            throw new IllegalArgumentException("Некорректные координаты: " + x + ", " + y);
        }
        grid[x][y] = symbol;
    }

    public void display() {
        System.out.print("  ");
        for (int i = 1; i <= size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < size; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < size; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
