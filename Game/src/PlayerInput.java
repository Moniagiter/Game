
import java.util.Scanner;

public class PlayerInput {
    public static int[] getCoordinates() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть координату (формат типу: A1): ");
        String input = scanner.nextLine().toUpperCase().trim();
        if (input.length() < 2 || !Character.isDigit(input.charAt(1))) {
            throw new IllegalArgumentException("Неправильний формат вводу!");
        }
        int row = input.charAt(0) - 'A';
        int col = Integer.parseInt(input.substring(1)) - 1;
        return new int[]{row, col};
    }
}