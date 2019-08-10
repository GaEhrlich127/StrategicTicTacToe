import java.util.Scanner;
public class StrategicTicTacToe{
    
    private TicTacToe[][] bigBoard;
    
    //Initializes the board
    public StrategicTicTacToe(){
        bigBoard=new TicTacToe[3][3];
        for(int y=0;y<3;y++){
            for(int x=0;x<3;x++){
                bigBoard[x][y]=new TicTacToe();
            }
        }
    }
    
    //Plays the game!
    public void game(){
        //Set bigx and bigy to negative values to indicate an unclear big board.
        Scanner scan=new Scanner(System.in);
        int bigx=-1;
        int bigy=-1;
        char activePlayer='X';
        printBoard();
        while(checkWinner()=='N'){
            if(bigx<0||bigx>2||bigy<0||bigy>2){
                System.out.println(activePlayer+": It's your turn!");
                System.out.println("You get to choose a board to move within!");
                System.out.println("Enter the X Coordinate of the board you'd like to use");
                bigx=scan.nextInt();
                System.out.println("Enter the Y Coordinate of the board you'd like to use");
                bigy=scan.nextInt();
            }
            else if(bigBoard[bigx][bigy].checkWinner()!='N'){
                bigx=-1;
                bigy=-1;
            }
            else{
                System.out.println(activePlayer+": What X Coordinate would you like to go at?");
                int x=scan.nextInt();
                System.out.println(activePlayer+": What Y Coordinate would you like to go at?");
                int y=scan.nextInt();
                System.out.println();
                if(bigBoard[bigx][bigy].makeMove(x, y, activePlayer)){
                    if(activePlayer=='X'){
                        activePlayer='O';
                    }
                    else{
                        activePlayer='X';
                    }
                    bigx=x;
                    bigy=y;
                }
                else{
                    System.out.println("Your move was invalid! Try again!");
                }
                printBoard();
                System.out.println();
            }
        }
        if(checkWinner()=='T'){
            System.out.println("It's a tie!");
        }
        else{
            System.out.println(checkWinner()+" wins!");
        }
    }
    
    //Prints out the board nice and neat
    public void printBoard(){
        for(int bigy=0;bigy<3;bigy++){
            for(int y=0;y<3;y++){
                System.out.print(" ");
                for(int bigx=0;bigx<3;bigx++){
                    for (int x=0;x<3;x++){
                        System.out.print(bigBoard[bigx][bigy].getSpot(x,y)+" ");
                    }
                    if(bigx<2){
                        System.out.print("|"+" ");
                    }
                }
                System.out.println();
            }
            if(bigy<2){
                System.out.println("-------+-------+-------");
            }
        }
    }
    
    /**
    * Checks for a winner and returns their token.
    * 
    * @return the token of the winner (X/O), T if a tie, or N if none of the above
    */
    public char checkWinner(){
        //Horizontal (First index increasing)
        boolean winner;
        for(int y=0;y<3;y++){
            winner=true;
            for(int x=0;x<2;x++){
                //If a board's result is the same as the next board's, and the board's result wasn't no one or a tie.
                if(bigBoard[x][y].checkWinner()==bigBoard[x+1][y].checkWinner()&&bigBoard[x][y].checkWinner()!='N'&&bigBoard[x][y].checkWinner()!='T'){
                    //This if statement doesn't actually do anything.
                    //I just can't wrap my head around the negation so I'd rather keep it as the if and use an else.
                }
                else{
                    winner=false;
                    break;
                }
            }
            if(winner==true){
                return bigBoard[0][y].checkWinner();
            }
        }
        //Vertical, the same as above, but the x and y loops have swapped.
        for(int x=0;x<3;x++){
            winner=true;
            for(int y=0;y<2;y++){
                //If a board's result is the same as the next board's, and the board's result wasn't no one or a tie.
                if(bigBoard[x][y].checkWinner()==bigBoard[x][y+1].checkWinner()&&bigBoard[x][y].checkWinner()!='N'&&bigBoard[x][y].checkWinner()!='T'){
                    //This if statement doesn't actually do anything.
                    //I just can't wrap my head around the negation so I'd rather keep it as the if and use an else.
                }
                else{
                    winner=false;
                    break;
                }
            }
            if(winner==true){
                return bigBoard[x][0].checkWinner();
            }
        }
        //Diagonal, like above but with equal offsets.
        winner=true;
        for(int i=0;i<2;i++){
            if(bigBoard[i][i].checkWinner()==bigBoard[i+1][i+1].checkWinner()&&bigBoard[i][i].checkWinner()!='N'&&bigBoard[i][i].checkWinner()!='T'){
                //This if statement doesn't actually do anything.
                //I just can't wrap my head around the negation so I'd rather keep it as the if and use an else.
            }
            else{
                winner=false;
                break;
            }
        }
        if(winner==true){
            return bigBoard[0][0].checkWinner();
        }
        //Diagonal2, like above but reverse.
        winner=true;
        for(int i=0;i<2;i++){
            if(bigBoard[2-i][i].checkWinner()==bigBoard[2-i-1][i+1].checkWinner()&&bigBoard[2-i-1][i+1].checkWinner()!='N'&&bigBoard[2-i][i].checkWinner()!='T'){
                //This if statement doesn't actually do anything.
                //I just can't wrap my head around the negation so I'd rather keep it as the if and use an else.
            }
            else{
                winner=false;
                break;
            }
        }
        if(winner==true){
            return bigBoard[2][0].checkWinner();
        }
        //Check for a tie, backwards
        //If there's an empty spot, no one has won
        for(int y=0;y<3;y++){
            for(int x=0;x<3;x++){
                if(bigBoard[x][y].checkWinner()=='N'){
                    return 'N';
                }
            }
        }
        //But if no one had any of the win conditions work, and all the spots are filled...
        return 'T';
    }
    
}