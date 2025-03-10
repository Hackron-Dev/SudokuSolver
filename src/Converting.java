import java.util.Scanner;

public class Converting {
    public static int[] converting(String input) {
//        Scanner scanner = new Scanner(System.in);
//        String input;
        //System.out.println(input);

        // Просим ввести строку из 81 цифры
//        while (true) {
//            System.out.print("Введите строку из 81 цифры: ");
//            input = scanner.nextLine().trim();
//
//            if (input.length() == 81 && input.matches("\\d{81}")) {
//                break;
//            } else {
//                System.out.println("Ошибка! Введите ровно 81 цифру.");
//            }
//        }

        // Преобразуем строку в массив чисел
        int[] numbers = new int[81];
        for (int i = 0; i < 81; i++) {
            numbers[i] = Character.getNumericValue(input.charAt(i));
        }

        return numbers;
    }

}
