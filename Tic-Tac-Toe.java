import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class TicTacToe {
    static int[] board = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    static boolean keepPlaying = true;
    static boolean gameOver = false;
    static boolean playerWin = false;
    static boolean tie = false;
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static int row = -1;
    static int col = -1;
    static Random rand = new Random();
    static boolean go = true;
    
    public static void main(String[] args) throws IOException, InterruptedException {
        while(keepPlaying) {
            playGame();

            if(playerWin) {
                System.out.println("You win!");
            } 
            else if(tie) {
                System.out.println("It's a tie!");
            } 
            else {
                System.out.println("You lose!");
            }

            System.out.println("Do you want to play again? (y/n)");

            if(reader.readLine().equalsIgnoreCase("n")) {
                keepPlaying = false;
            }

            board = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};
            gameOver = false;
            tie = false;
        }
    
    }

    public static void playGame() throws IOException, InterruptedException {
        printBoard();
    	while(!gameOver && !tie) {
            System.out.println("Your turn! Enter the row and column you want to place your X in (1-3) separated by space:");
            go = true;

            while(go) {
                String input = reader.readLine();
                row = Integer.parseInt(input.substring(0, 1)) - 1;
                col = Integer.parseInt(input.substring(2, 3)) - 1;

                if(row > 2 || row < 0 || col > 2 || col < 0) {
                	System.out.println("out of bounds!");
                	continue;
                }

                if(board[row * 3 + col] == -1) {
                    board[row * 3 + col] = 1;
                    go = false;
                }
                else {
                	System.out.println("that spot is already taken!");
                }
            }

            printBoard();

            if(winner(1)) {
                gameOver = true;
                playerWin = true;
                break;
            }

            while(board[row * 3 + col] != -1) {
                row = rand.nextInt(3);
                col = rand.nextInt(3);
            }

            board[row * 3 + col] = 0;
            System.out.println("Computer's turn!");
            Thread.sleep(2000);
            System.out.println("It placed an O in row " + (row + 1) + " and column " + (col + 1));
            printBoard();

            if(winner(0)) {
                gameOver = true;
                playerWin = false;
            }
        }
    }

    public static void printBoard() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[i * 3 + j] == 1) {
                    System.out.print("X");
                } 
                else if(board[i * 3 + j] == 0) {
                    System.out.print("O");
                } 
                else {
                    System.out.print(" ");
                }
                if(j < 2) {
                	System.out.print(" | ");
                }
            }
            System.out.println();
        }
    }

    public static boolean winner(int num) {
        for(int i = 0; i < 3; i++) {
            if(board[i * 3 + 0] == num && board[i * 3 + 1] == num && board[i * 3 + 2] == num) {
                return true;
            }
            if(board[0 * 3 + i] == num && board[1 * 3 + i] == num && board[2 * 3 + i] == num) {
                return true;
            }
        }

        if(board[0 * 3 + 0] == num && board[1 * 3 + 1] == num && board[2 * 3 + 2] == num) {
            return true;
        }
        if(board[0 * 3 + 2] == num && board[1 * 3 + 1] == num && board[2 * 3 + 0] == num) {
            return true;
        }

        if(num == 1) {
            for(int i = 0; i < 9; i++) {
                if(board[i] == -1) {
                    return false;
                }
            }
            
            gameOver = true;
            tie = true;
            playerWin = false;
        }
        return false;
    }
}