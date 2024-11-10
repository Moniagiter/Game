public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Геттери
    public int getX() { return x; }
    public int getY() { return y; }

    // Метод для перевірки валідності координат
    public static boolean isValidCoordinate(int x, int y, int size) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }
}
