import java.util.Scanner;

public class TicTacToe {
    
    private char[][] board;
    
    //Initializes the board
    public TicTacToe() {
        board = new char[3][3];
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                board[x][y] = '-';
            }
        }
    }
    
    /**
    * @param x     the left/right coordinate, 0 being the left, board.length-1
    *              being the right
    * @param y     the up/down coordinate, 0 being the top, board.length-1 being
    *              the bottom
    * @param token the symbol used to represent the player who is making the move
    *              (X or O)
    * @return true when the move is made, false otherwise
    */
    public boolean makeMove(int x, int y, char token) {
        // If x or y are out of bounds
        if (y<0 || y >=3 || x<0 || x>=3) {
            return false;
        }
        // If the spot isn't blank
        else if (board[x][y]!='-') {
            return false;
        }
        // Move should be valid
        board[x][y]=token;
        return true;
    }
    
    /**
    * Checks for a winner and returns their token.
    * 
    * @return the token of the winner (X/O), T if a tie, or N if none of the above
    */
    public char checkWinner() {
        // Vertical
        if (board[0][0] == board[0][1] && board[0][1] == board[0][2] && board[0][0] != '-') {
            return board[0][0];
        }
        else if (board[1][0] == board[1][1] && board[1][1] == board[1][2] && board[1][0] != '-') {
            return board[1][0];
        }
        else if (board[2][0] == board[2][1] && board[2][1] == board[2][2] && board[2][0] != '-') {
            return board[2][0];
        }
        // Horizontal
        else if (board[0][0] == board[1][0] && board[1][0] == board[2][0] && board[0][0] != '-') {
            return board[0][0];
        }
        else if (board[0][1] == board[1][1] && board[1][1] == board[2][1] && board[0][1] != '-') {
            return board[0][1];
        }
        else if (board[0][2] == board[1][2] && board[1][2] == board[2][2] && board[0][2] != '-') {
            return board[0][2];
        }
        // Diagonal
        else if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
            return board[0][0];
        }
        else if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != '-') {
            return board[0][2];
        }
        // Tie
        else {
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 3; x++) {
                    if (board[x][y] == '-')
                    return 'N';
                }
            }
            return 'T';
        }
    }

    /**
     * Returns the token at a specified spot - Exclusively exists for StrategicTicTacToe.java
     * @param x The left/right coordinate
     * @param y The up/down coordinate
     * @return  The token at the given position, if out of bounds BEL (7)
     */
    public char getSpot(int x,int y){
        if(x<0||x>=3||y<0||y>=3){return 7;}
        return board[x][y];
    }
    
    // Prints out the board in a somewhat pretty way.
    // Functional for playing TicTacToe, deprecated in StrategicTicTacToe.java
    private void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[j][i] + " ");
            }
            System.out.println();
        }
    }
    
    //Plays a game of Tic-Tac-Toe!
    //Made for testing before implementation of StrategicTicTacToe.java
    public void game(){
        char activePlayer='X';
        printBoard();
        System.out.println();
        while(checkWinner()=='N'){
            Scanner scan=new Scanner(System.in);
            System.out.println(activePlayer+": What X Coordinate would you like to go at?");
            int x=scan.nextInt();
            System.out.println(activePlayer+": What Y Coordinate would you like to go at?");
            int y=scan.nextInt();
            System.out.println();
            if(makeMove(x, y, activePlayer)){
                if(activePlayer=='X'){
                    activePlayer='O';
                }
                else{
                    activePlayer='X';
                }
            }
            else{
                System.out.println("Sorry "+activePlayer+" your move was invalid. Try again!");
            }
            printBoard();
            System.out.println();
        }
        if(checkWinner()=='T'){
            System.out.println("It's a Tie!");
        }
        else{
            System.out.println(checkWinner()+" Wins!");
        }
        
    }
}