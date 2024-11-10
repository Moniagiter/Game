import java.util.Scanner;

public class Player {
    private Scanner scanner = new Scanner(System.in);

    public Coordinate getShotCoordinate(int gridSize) {
        while (true) {
            System.out.print("Введіть координати пострілу : ");
            String input = scanner.nextLine().toUpperCase();
            if (input.length() < 2) {
                System.out.println("Некоректний формат. Спробуйте ще раз.");
                continue;
            }
            char xChar = input.charAt(0);
            String yString = input.substring(1);

            int x = xChar - 'A';
            int y;
            try {
                y = Integer.parseInt(yString) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Некоректний номер рядка. Спробуйте ще раз.");
                continue;
            }

            if (Coordinate.isValidCoordinate(x, y, gridSize)) {
                return new Coordinate(x, y);
            } else {
                System.out.println("Координати поза межами сітки. Спробуйте ще раз.");
            }
        }
    }
}
