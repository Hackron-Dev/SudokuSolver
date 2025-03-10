import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Grid {
    private final Cell[] cells;
    boolean AnyChanges = true;

    int counter = 0;
    int WinNumber = 0;


    public Grid(int[] numbers) {

        if (numbers.length != 81) {
            throw new IllegalArgumentException("Array Error");
        }

        cells = new Cell[81];
        for (int i = 0; i < 81; i++) {
            cells[i] = new Cell(numbers[i], i);
        }
    }

    public void getWinNumber() {
        for (int i = 0; i <= 80; i++) {
            if (cells[i].getNumber() == 0) {
                WinNumber++;
            }
        }
        System.out.println("WinNumber: " + WinNumber);
    }

    public void printGrid() {
        for (int i = 0; i < 81; i++) {
            System.out.print(cells[i].getNumber() + " ");
            if ((i + 1) % 9 == 0) System.out.println();
        }
    }

    private void horizontalCheck() {
        for (int i = 0; i <= 72; i += 9) { // Строки (1-9)

            int start = 9 * (i / 9); //
            int end = (9 * ((i / 9) + 1)) - 1;
            int shift = 1;


            arrCleaner(numListGenerator(start, end, shift));// Можно полностью заменить
            numberSwitcher(numListGenerator(start, end, shift));
        }
    }

    private void verticalCheck() { // переписать?
        int shift = 9;
        for (int i = 0; i <= 9; i++) {
            int start = i - (i / 9) * 9;
            int end = (8 - (i / 9)) * 9 + i;


            arrCleaner(numListGenerator(start, end, shift));
            numberSwitcher(numListGenerator(start, end, shift));
        } //Переход с столбца на столбец
    }

    private void ClearHorizonteByID(int i) {
        int start = 9 * (i / 9);
        int end = (9 * ((i / 9) + 1)) - 1;
        int shift = 1;

        arrCleaner(numListGenerator(start, end, shift));

    }

    private void ClearVerticalByID(int i) {
        int start = i - (i / 9) * 9;
        int end = (8 - (i / 9)) * 9 + i;
        int shift = 9;

        arrCleaner(numListGenerator(start, end, shift));
    }

    private void ClearSquareByID(int i) {

        arrCleaner(SquareGetNum(cells[i].square_id));

    }

    private void ClearAllByID(int i) {

        ClearSquareByID(i);
        ClearHorizonteByID(i);
        ClearVerticalByID(i);
    }

    private void squareCheck() {

        for (int i = 0; i < 9; i++) {

            arrCleaner(SquareGetNum(i));
            numberSwitcher(SquareGetNum(i));

        }
    }

    private void arrCleaner(ArrayList<Integer> numList) {
        ArrayList<Integer> UnPos_nums = new ArrayList<>();
        for (int i = 0; i <= numList.size() - 1; i++) {

            if (cells[numList.get(i)].number != 0) {
                UnPos_nums.add(cells[numList.get(i)].number);//создание массива невозможных чисел
            }
        }

        for (int i = 0; i <= numList.size() - 1; i++) {

            if (cells[numList.get(i)].number == 0) {
                int len1 = cells[numList.get(i)].possible_nums.size();
                cells[numList.get(i)].possible_nums.removeAll(UnPos_nums); // Просто удаляем лишние числа
                int len2 = cells[numList.get(i)].possible_nums.size();

                if (len2 > len1) {  //TODO: Проверка изменений чтоб отсечь лишние повторы
                    AnyChanges = true;
                }
            }
        }

    }

    private void numberSwitcher(ArrayList<Integer> numList) { // Rename!!1@#@#!@#!
        ArrayList<Integer> UniqNums = new ArrayList<>();
        for (int i = 0; i <= numList.size() - 1; i++) {


            if (cells[numList.get(i)].number == 0) {
                UniqNums.addAll(cells[numList.get(i)].possible_nums);

                if (cells[numList.get(i)].possible_nums.size() == 1) {

                    UniqNums.removeAll(cells[numList.get(i)].possible_nums);//

                    cells[numList.get(i)].number = cells[numList.get(i)].possible_nums.get(0);
                    cells[numList.get(i)].possible_nums.clear();
                    ClearAllByID(numList.get(i));

                    numberSwitcher(numList);  //рекурсия?!?!?!?!?
                }
            }
        }
        UniqNums = getUniqNum(UniqNums);
        if (UniqNums.size() > 0) {
            for (int i = 0; i <= UniqNums.size() - 1; i++) {
                for (int j = 0; j <= numList.size() - 1; j++) {

                    if (cells[numList.get(j)].possible_nums.contains(UniqNums.get(i))) {

                        cells[numList.get(j)].number = UniqNums.get(i);
                        cells[numList.get(j)].possible_nums.clear();
                        ClearAllByID(numList.get(j));
                        numberSwitcher(numList);

                    }
                }
            }
        }

    }

    private ArrayList<Integer> getUniqNum(ArrayList<Integer> arr) {

        List<Integer> result = arr.stream()
                .filter(n -> Collections.frequency(arr, n) == 1)
                .collect(Collectors.toList());
        return (ArrayList<Integer>) result;
    }


    private ArrayList<Integer> SquareGetNum(int square_id) {
        ArrayList<Integer> numList = new ArrayList<>();
        for (int i = 0; i <= 80; i++) {

            if (cells[i].square_id == square_id) {
                numList.add(i);
            }
        }

        return numList;
    }

    private ArrayList<Integer> numListGenerator(int start, int end, int shift) {
        ArrayList<Integer> numList = new ArrayList<>();
        for (int move = start; move <= end; move += shift) {

            numList.add(move);
        }
        return numList;
    }

    private String Answer() {
        StringBuilder answ = new StringBuilder();
        for (int i = 0; i <= 80; i++) {
            answ.append(cells[i].number);
        }
        return answ.toString();
    }


    public String Solver() { //Переделать полностью
        squareCheck();
        horizontalCheck();
        verticalCheck();
        if (!AnyChanges) {
            return Answer();
        }
        for (int i = 0; i <= 80; i++) {
            if (cells[i].number == 0) {
                AnyChanges = false;
                counter++;
                Solver();
            }
        }
        return Answer();
    }

    //100060908000800003087030000045000000000000842006008030090500600500012300000087010

}


