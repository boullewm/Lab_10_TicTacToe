import java.util.Scanner;
public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String [][] board = new String [ROWS][COLS];
    private static int moveCount=0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String player = "X";

        boolean playAgain = false;
        do {
            clearBoard();
            moveCount = 0;
            display();
            boolean gameWon = false;

            while (!gameWon && !isTie()) {
                int row, col;
                do {
                    System.out.print(player + "'s turn:");
                    row = SafeInput.getRangedInt(in, "Enter row (1-3)", 1, 3) - 1;
                    col = SafeInput.getRangedInt(in, "Enter col (1-3)", 1, 3) - 1;
                    if (!isValidMove(row,col)){
                        System.out.println("Invalid move! Choose another spot!");
                    }
                } while (!isValidMove(row, col));

                board[row][col] = player;
                moveCount++;
                display();

                if (isWin(player)) {
                    System.out.println("Player " + player + " wins!");
                    gameWon = true;
                } else if (isTie()) {
                    System.out.println("The game is a tie!");
                }

                player = player.equals("X") ? "O" : "X";
            }

            playAgain = SafeInput.getYNConfirm(in, "Would you like to play again?");
        } while (playAgain);

        System.out.println("Thanks for playing Tic Tac Toe!");
    }

    private static void clearBoard() {
    for (int row=0; row< ROWS; row++){
        for (int col=0; col<COLS; col++){
            board[row][col]=" ";
        }
    }
    moveCount = 0;
    }

    private static void display() {
    for (int row=0;row<ROWS;row++){
        for (int col=0;col<COLS;col++){
            System.out.print(board[row][col]);
            if (col<2){
                System.out.print("|");
            }
        }
        System.out.println();
    }
    }

    private static boolean isValidMove(int row, int col) {
        boolean retVal = false;
        if (board[row][col].equals(" ")){
            retVal=true;
        }
        return retVal;
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROWS; row++) {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int col = 0; col < COLS; col++) {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static boolean isTie() {
        if (moveCount < 9) return false;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
}
