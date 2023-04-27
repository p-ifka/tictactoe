package TICTAC;
import java.util.concurrent.ThreadLocalRandom;
import java.io.IOException;
import java.util.Scanner;




public class ticBot {
    //stats 
    public static int games;
    public static int X;
    public static int O;
    public static int tie;

    public static int vertical;
    public static int horizontal;
    public static int diagonal;


   public static int rounds = 0;
    //variables
    public static Boolean playing = true;

    //multi-dimensional array defining the value of each board position 
    public static char[][] board = {
    {'#','#','#'},//1st row (0,0  0,1  0,2)
    {'#','#','#'},//2nd row (1,0  1,1  1,2)
    {'#','#','#'},//3rd row (2,0  3,1  3,2) 
};


    public static void main(String []args) throws InterruptedException, ArrayIndexOutOfBoundsException, IOException {
        // title
        System.out.println("|===========|");
        System.out.println("|====| |====| |_|   ||=====|"); 
        System.out.println("     | |       _    ||=====|");
        System.out.println("     | |      | |   ||");
        System.out.println("     | |      | |   ||=====|");
        System.out.println("     |_|      |_|   ||=====|");

        System.out.println("                   __");
        System.out.println("|===========|     /  \"");
        System.out.println("|====| |====|    /    \"   ||=====|");
        System.out.println("     | |       || ___  ||  ||=====|");
        System.out.println("     | |       ||/    \"||  ||");
        System.out.println("     | |       ||      ||  ||=====|");
        System.out.println("     |_|       ||      ||  ||=====|");

        System.out.println("|===========|");
        System.out.println("|====| |====|         ||=====");
        System.out.println("     | |      ||===|| || ");
        System.out.println("     | |      ||   || ||=====");
        System.out.println("     | |      ||   || ||");
        System.out.println("     |_|      ||===|| ||=====");

        System.out.println("you are X");
        manager();
    }
    public static void postMove() throws ArrayIndexOutOfBoundsException, IOException, InterruptedException { //run after each move made, prints result of move and determines if a player has won 
        // throws ArrayIndexOutOfBoundsException to prevent crash if checking a non-existent index

        //display board
        System.out.print(board[0][0]);
        System.out.print(" ");
        System.out.print(board[0][1]);
        System.out.print(" ");
        System.out.print(board[0][2]);
        System.out.println();

        System.out.print(board[1][0]);
        System.out.print(" ");
        System.out.print(board[1][1]);
        System.out.print(" ");
        System.out.print(board[1][2]);
        System.out.println();

        System.out.print(board[2][0]);
        System.out.print(" ");
        System.out.print(board[2][1]);
        System.out.print(" ");
        System.out.print(board[2][2]);
        System.out.println();

        System.out.println(board.toString());

        //check for wins
        //check vertical victory
        if (board[0][spotX] == board[1][spotX] && board[1][spotX] == board[2][spotX]) {
            System.out.println(prevPlayer + " WINS");
            System.out.println("won by vertical");
            vertical += 1;
            reset();
        //check for horizontal victory
        } else if (board[spotY][0] == board[spotY][1] && board[spotY][1] == board[spotY][2]) {
            System.out.println(prevPlayer + " WINS");
            System.out.println("won by horizontal");
            horizontal += 1;
            reset();
        }
        if (board[1][1] != '#') { //only check for diagonal victory if the center spot is not blank (#)
        //check for diagonal victory from right (/)
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            System.out.println(prevPlayer + " WINS");
            System.out.println("won by diagonal right");
           diagonal += 1;
           reset();
        //check for diagonal victory from left (\)
        } else if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            System.out.println(prevPlayer + " WINS");
            System.out.println("won by diagonal left");
            reset();
           diagonal += 1;
        } else if (rounds >= 8) {
            System.out.println("TIE");
            reset();
        }
    }
        
    }
    
    public static void manager() throws InterruptedException, ArrayIndexOutOfBoundsException, IOException { //manages order of play
        while (playing == true) {
            System.out.println("Your turn:");
            System.out.printlm("type number of space to place, type 'help' for further instructions");
            XPlays();
            rounds ++;
            postMove();
    
            Thread.sleep(300);

            System.out.println("O IS PLAYING");
            OPlays();
            postMove();
            rounds ++;
        } 
    }
    
    //variables for picking x and y coordinates of spot to change 
    public static int spotX = ThreadLocalRandom.current().nextInt(1);
    public static int spotY = ThreadLocalRandom.current().nextInt(1);

    public static String prevPlayer = "";


    public static void XPlays() {        
        Boolean XTurnOver = false;
        Scanner scan = new Scanner(System.in);

        String playerIN = scan.nextLine();

        if (playerIN == 1) {
            board[0][0] = 'X';
        } else if (playerIN == 2) {
            board[0][1] = 'X'; 
        } else if (playerIN == 3) {
            board[0][2] = 'X';
        } else if (playerIN == 4) {
            board[1][0] = 'X';
        } else if (playerIN == 5) {
            board[1][1] = 'X';        
        } else if (playerIN == 6) {
            board[1][2] = 'X';
        } else if (playerIN == 7) {
            board[2][0] = 'X';
        } else if (playerIN == 8) {
            board[2][1] = 'X';
        } else if (playerIN == 9) {
            board[2][2] = 'X';
        } 
        prevPlayer = 'X';
        XTurnOver = true;
    }

    public static void OPlays() {
        Boolean OTurnOver = false;
        while (OTurnOver == false) {
            spotX = ThreadLocalRandom.current().nextInt(0,3);
            spotY = ThreadLocalRandom.current().nextInt(0,3);

            if (board[spotY][spotX] == '#') {
                board[spotY][spotX] = 'O';
                OTurnOver = true;
                prevPlayer = "O";
            }
        }
    }

   
    
    public static void reset() throws IOException, ArrayIndexOutOfBoundsException, InterruptedException {
        if (prevPlayer == "X") {
            X += 1;
        } else if (prevPlayer == "O") {
            O += 1; 
        } else {
            tie += 1;
        }
        games += 1;
  

        
        System.out.println(games + " games have been recorded");
        System.out.println("X has won "+X+ " times");
        System.out.println("Y has won "+O+ " times");
        System.out.println("There have been "+tie+ " ties");

        System.out.println("win types");
        System.out.println("vertical "+vertical);
        System.out.println("horizonal "+horizontal);
        System.out.println("diagonal " +diagonal);

        //reset board
        board[0][0] = '#'; //row 1 
        board[0][1] = '#';
        board[0][2] = '#';

        board[1][0] = '#';//row 2
        board[1][1] = '#';
        board[1][2] = '#';

        board[2][0] = '#';
        board[2][1] = '#';
        board[2][2] = '#';
        
        rounds = 0;
        Thread.sleep(3000);
        manager();
}
}

