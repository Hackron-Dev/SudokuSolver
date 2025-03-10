import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SudokuValidator {
    public static void main() {
        Scanner console = new Scanner(System.in);
        //String csvFile = "C:/Users/Hackron/Documents/sudoku.csv";
        System.out.println("Укажите полный путь к файлу\nПример: C:/Users/Hackron/Documents/sudoku.csv" + "\n");
        String csvFile = console.nextLine();
        int total = 0;
        int correct = 0;
        int incorrect = 0;
        System.out.println("Какой алгоритм вы ходите протестировать?\n1) Алгоритм создателя \n2) Алгоритм backtracking");
        int choice = console.nextInt();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Пропускаем заголовок
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                total++;
                String[] parts = line.split(",", 2);
                if (parts.length != 2) {
                    System.err.println("Некорректная строка: " + line);
                    continue;
                }

                String puzzle = parts[0];
                String expectedSolution = parts[1];

                try {
                    String actualSolution;
                    if (choice == 1) {
                        //System.out.println("Benchmark backtracking sudoku solver: \n");
                        Grid grid = new Grid(Converting.converting(puzzle));
                        actualSolution = grid.Solver();
                    } else{
                        //System.out.println("Benchmark backtracking sudoku solver: \n");
                        actualSolution = SudokuSolver.solve(Converting.converting(puzzle));
                    }

                    if (actualSolution == null) {
//                        System.err.println("Не удалось решить судоку: " + puzzle);
                        incorrect++;
                    } else if (actualSolution.equals(expectedSolution)) {
                        correct++;
                    } else {
//                        System.err.println("Неверное решение для головоломки: " + puzzle);
//                        System.err.println("Ожидалось: " + expectedSolution);
//                        System.err.println("Получено:  " + actualSolution);
                        incorrect++;
                    }
                } catch (Exception e) {
//                    System.err.println("Ошибка при обработке: " + puzzle);
//                    e.printStackTrace();
                    incorrect++;
                }

                // Вывод прогресса
                if (total % 100000 == 0) {
                    double correctPercentage = (correct * 100.0) / total;
                    double incorrectPercentage = (incorrect * 100.0) / total;

                    //System.out.println("Обработано: " + total + " | Верных: " + correct + " | Неверных: " + incorrect);
                    System.out.printf("Обработано: %d | Верных: %.2f%% | Неверных: %.2f%%%n",
                            total, correctPercentage, incorrectPercentage);
                }
            }

            System.out.println("\nИтоговый результат:");
            System.out.println("Всего судоку: " + total);
            System.out.println("Правильных решений: " + correct);
            System.out.println("Неправильных решений: " + incorrect);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}