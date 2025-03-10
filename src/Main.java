import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("1: решить судоку Алгоритмом создателя (нужно будет ввести строчку из 81 числа)\n2: Бенчмарк алгоритмов");
        int choice = console.nextInt();
        console.nextLine();
        if (choice == 1) {
            System.out.println("Введите судоку 1 строкой слитно (81 симовл) ");
            String sudoku = console.nextLine();
            Grid grid = new Grid(Converting.converting(sudoku));
            System.out.println("Before: ");
            grid.printGrid();
            long startTime = System.currentTimeMillis();

            grid.Solver();

            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            System.out.println("After: ");
            grid.printGrid();

            System.out.println("\nВремя выполнения: " + formatTime(totalTime));


        } else{
            long startTime = System.currentTimeMillis();

            SudokuValidator.main(); // Передаем аргументы если нужно

            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;

            System.out.println("\nВремя выполнения: " + formatTime(totalTime));
        }

    }

    private static String formatTime(long millis) {
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;

        return String.format("%02d:%02d:%02d.%03d",
                hours % 24,
                minutes % 60,
                seconds % 60,
                millis % 1000);
    }
}