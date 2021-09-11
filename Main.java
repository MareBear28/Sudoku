public class Main
{
    private static final int GridSize = 9;
    public static void main(String[] args)
    {
        int[][] board = {
                {7, 0, 2, 0, 5, 0, 6, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0},
                {1, 0, 0, 0, 0, 9, 5, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 9, 0},
                {0, 4, 3, 0, 0, 0, 7, 5, 0},
                {0, 9, 0, 0, 0, 0, 0, 0, 8},
                {0, 0, 9, 7, 0, 0, 0, 0, 5},
                {0, 0, 0, 2, 0, 0, 0, 0, 0},
                {0, 0, 7, 0, 4, 0, 2, 0, 3},
        };
        if (solve(board))
        {
            System.out.println("Solved!");
        }
        else{
            System.out.println("Unsolvable :(");
        }
        printBoard(board);
    }

    private static void printBoard(int[][] board)
    {
        for(int i = 0; i < GridSize; i++)
        {
            for (int j = 0; j < GridSize; j++)
            {
                System.out.print(board[i][j]);
                if (((j + 1) % 3 == 0 ) && ((j + 1) != GridSize))
                {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (((i + 1) % 3 == 0 ) && ((i + 1) != GridSize))
            {
                System.out.println("-----------");
            }

        }
    }

    private static boolean isNumberInRow(int[][] board, int number, int row)
    {
        for (int i = 0; i < GridSize; i++)
        {
            if (board[row][i] == number )
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInCol(int[][] board, int number, int col)
    {
        for (int i = 0; i < GridSize; i++)
        {
            if (board[i][col] == number )
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInBox(int[][] board, int number, int col, int row)
    {
        int localBoxRow = row - (row % 3);
        int localBoxCol = col - (col % 3);

        for (int i = localBoxRow; i < localBoxRow + 3; i++)
        {
            for (int j = localBoxCol; j < localBoxCol + 3; j++)
            {
                if (board[i][j] == number)
                {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isValid(int[][] board, int number, int col, int row)
    {
        return !isNumberInRow(board, number, row) && !isNumberInCol(board, number, col) && !isNumberInBox(board, number, col, row);
    }

    private static boolean solve(int[][] board)
    {
        for(int row = 0; row < GridSize; row++)
        {
            for(int col = 0; col < GridSize; col++)
            {
                if (board[row][col] == 0)
                {
                    for(int num = 1; num <= GridSize; num++)
                    {
                        if(isValid(board, num, col, row))
                        {
                            board[row][col] = num;
                            if (solve(board))
                            {
                                return true;
                            }
                            else{
                                board[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
