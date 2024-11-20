package player;

import java.util.Scanner;

public class ConsolePlayer implements Player {

    public int[] getCoordinates() {
        Scanner scanner = new Scanner(System.in);
        int row, col;
        while (true) {
            try {
                System.out.print("Введіть координату (формат типу: A1): ");
                String input = scanner.nextLine().toUpperCase().trim();

                // Перевірка довжини введення
                if (input.length() < 2) {
                    throw new IllegalArgumentException("Невірний формат введення! Введіть координати у форматі A1.");
                }

                char rowChar = input.charAt(0); // Перший символ повинен бути буквою
                String colStr = input.substring(1); // Решта повинна бути числом

                if (!Character.isLetter(rowChar) || !colStr.matches("\\d+")) {
                    throw new IllegalArgumentException("Невірний формат введення! Введіть координати у форматі A1.");
                }

                row = rowChar - 'A'; // Перетворення букви на індекс ряду
                col = Integer.parseInt(colStr) - 1; // Перетворення числа на індекс стовпця

                // Перевірка меж поля
                if (row < 0 || row >= 10 || col < 0 || col >= 10) {
                    throw new IllegalArgumentException("Координати виходять за межі поля! Введіть значення від A1 до J10.");
                }

                // Якщо всі перевірки пройдені, повертаємо координати
                return new int[]{row, col};
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
