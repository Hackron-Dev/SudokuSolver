public class SudokuSolver {

    public static String solve(int[] board) {
        if (board.length != 81) {
            throw new IllegalArgumentException("Invalid Sudoku size");
        }

        int[][] grid = new int[9][9];
        for (int i = 0; i < 81; i++) {
            grid[i / 9][i % 9] = board[i];
        }

        if (solveSudoku(grid)) {
            return toString(grid);
        }
        return null; // Если решение не найдено
    }

    private static boolean solveSudoku(int[][] grid) {
        int[] emptyCell = findEmptyCell(grid);
        if (emptyCell == null) {
            return true; // Все клетки заполнены
        }

        int row = emptyCell[0];
        int col = emptyCell[1];

        for (int num = 1; num <= 9; num++) {
            if (isValid(grid, row, col, num)) {
                grid[row][col] = num;

                if (solveSudoku(grid)) {
                    return true;
                }

                grid[row][col] = 0; // Откат
            }
        }
        return false;
    }

    private static int[] findEmptyCell(int[][] grid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    private static boolean isValid(int[][] grid, int row, int col, int num) {
        // Проверка строки
        for (int j = 0; j < 9; j++) {
            if (grid[row][j] == num) {
                return false;
            }
        }

        // Проверка столбца
        for (int i = 0; i < 9; i++) {
            if (grid[i][col] == num) {
                return false;
            }
        }

        // Проверка подквадрата 3x3
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    private static String toString(int[][] grid) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(grid[i][j]);
            }
        }
        return sb.toString();
    }
}

