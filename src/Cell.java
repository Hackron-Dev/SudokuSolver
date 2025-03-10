import java.util.ArrayList;
import java.util.Arrays;

public class Cell {
    protected int number;
    protected int square_id;
    protected int square_pos;
    protected int global_pos;
    protected ArrayList<Integer> possible_nums ;



    private static final int[][] Squares = new int[][] {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8}
    };

    // Конструктор с параметрами
    public Cell(int number, int global_pos) {

        this.number = number;
        this.global_pos = global_pos;


        this.square_id = setSquareId(global_pos);
        this.square_pos = setSquarePos(global_pos);

        this.possible_nums = new ArrayList<>();
        if (number == 0) {
            for (int i = 1; i <= 9; i++) {
                possible_nums.add(i);
            }
        }


    }




    private static int setSquareId(int i) {
        int BigX = (i-9*(i/9))/3;
        int BigY = (i / 9)/3;
        return Squares[BigY][BigX];
    }

    private static int setSquarePos(int i) {
        int SmallX = i-9*(i/9) - 3*((i-9*(i/9))/3);
        int SmallY = ((i/9)-3*(i/27));
        return Squares[SmallY][SmallX];
    }

    public int getNumber() {
        return number;
    }

    public int getGlobal_pos() {
        return global_pos;
    }

    public String getInfo() {
       return "-----\nNumber: " + this.number + "\n Square_id: " + square_id + "\n Square_pos: " + square_pos + "\n Global_pos: " + global_pos + "\n Possible_nums: " + possible_nums.toString();
    }
}
