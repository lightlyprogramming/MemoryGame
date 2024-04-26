import java.util.Scanner;

public class MemoryGame {
    public static void main(String[] args){
        // initializing objects
        Scanner scan = new Scanner(System.in);

        int diff = 0; // choice of difficulty
        int size = 2*diff; // creates grid of min 4, max 20

        int guesses = 0; // amount of guesses
        int unmatched = 0; // amount of unmatched numbers

        boolean playGame = true; // loop control variable

        while (!(diff >= 1 && diff <= 5)){ // ensures valid int when int is inputted
            System.out.println("Input your difficulty (1-5): ");
            diff = scan.nextInt();
        }

       size = 2*diff; 
        

        // initializing boards
        int[][] hiddenBoard = new int[size][size];
        int[][] gameBoard = new int[size][size];

        // intializing numbers for boards
        int[] numList = new int[size*size];
        int filler = 1;

        // filling list with numbers to be matched
        for(int i = 0; i < numList.length; i+=2){
            numList[i] = filler;
            numList[i+1] = filler;
            filler++;
        }

        // fills up gameBoard with a randomly generated set of numbers from numList of range [1, size]
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                int index = (int)(Math.random() * (numList.length));
                int number = numList[index];
                while (number == 0){
                    index = (int)(Math.random() * (numList.length));
                    number = numList[index];
                }
                gameBoard[i][j] = numList[index];
                numList[index] = 0;
                hiddenBoard[i][j] = 0; // intialize all values within hiddenBoard to be 0
            }
        }

        // gameplay loop
        while(playGame == true){ 
            displayBoard(hiddenBoard); // first displays board for player 
            System.out.println("Enter the coordinates of the first cell with the row (1-" + size + ") first and column (1-" + size + ") second. Enter -1 to quit. The board is " + size + " by " + size + "."); // asks for first guess from the user
            int row1 = scan.nextInt() - 1; // input and input adjustment for valid index 
            if(row1 == -2){
                playGame = false; // check for exit
                break;
            }
            int col1 = scan.nextInt() - 1;
            if(col1 == -2){
                playGame = false;
                break;
            }
            
            displayTempBoard(hiddenBoard, gameBoard, row1, col1); // displays board with first guess 

            System.out.println("Enter the coordinates of the second cell with the row (1-" + size + ") first and column (1-" + size + ") second. Enter -1 to quit. The board is " + size + " by " + size + "."); // asks for second guess from the user
            int row2 = scan.nextInt() - 1;
            if(row2 == -2){
                playGame = false;
                break;
            }
            int col2 = scan.nextInt() - 1;
            if(col2 == -2){
                playGame = false;
                break;
            }
            displayTempBoard(hiddenBoard, gameBoard, row2, col2); // displays board with both guesses

            checkCell(hiddenBoard, gameBoard, row1, col1, row2, col2); // checks if elements are matching
            guesses++;

            try { Thread.sleep(1500);} catch (Exception e){} // delay to allow user to memorize numbers
            System.out.print(String.format("\033[2J")); // deletes previous lines to prevent cheating. via https://stackoverflow.com/a/22083329 

            unmatched = 0; // resetting variable to zero 
            for(int[] row : hiddenBoard){
                for(int cell : row){
                    if(cell == 0){ // checks if any cells are unmatched
                        unmatched++; 
                    } 
                }
            }
            if(unmatched == 0){
                playGame = false; // stops gameplay if all cells are matched
            }
        }

        System.out.println("Thanks for playing!");
        System.out.println("You took " + guesses + " guesses!");
        if(unmatched == 0 ){
            System.out.println("You succesfully matched all the numbers!");
        } else {
            System.out.println("You did not successfully match all the numbers.");
        }
        scan.close(); 
    }

    /*
     * displays the given board
     * 
     * @PARAM {board} a 2D int array meant to be displayed 
     */
    
    private static void displayBoard(int[][] board){
        for(int[] row : board){
            for(int cell : row){
                if(cell == 0){
                    System.out.print("* ");
                } else { 
                    System.out.print(cell + " "); 
                }
            }
            System.out.println();
        }
    }

    /*
     * temporarily changes the values of hiddenBoard with values of gameBoard with given element
     * 
     * @PARAM {hiddenBoard} a 2d int array that is changed
     * @PARAM {gameBoard} a 2d int array that is accessed for values
     * @PARAM {r1} an int that specifies the row
     * @PARAM {c1} an int that specifies the column
     */

    private static void displayTempBoard(int[][]hiddenBoard, int[][]gameBoard, int r1, int c1){
        hiddenBoard[r1][c1] = gameBoard[r1][c1];
        displayBoard(hiddenBoard);
    }

    /*
     * compares the values of two different cells of gameBoard and changes the values of hiddenBoard
     * 
     * @PARAM {hiddenBoard} a 2d int array that is changed
     * @PARAM {gameBoard} a 2d int array that is accessed for values
     * @PARAM {r1} an int that specifies the first row
     * @PARAM {c1} an int that specifies the first column
     * @PARAM {r2} an int that specifies the second row
     * @PARAM {c2} an int that specifies the second column
     */
    private static void checkCell(int[][]hiddenBoard, int[][] gameBoard, int r1, int c1, int r2, int c2){
        if(gameBoard[r1][c1] == gameBoard[r2][c2]){
            System.out.println("You found a match!");
        } else {
            hiddenBoard[r1][c1] = 0;
            hiddenBoard[r2][c2] = 0;
        }
    }
}
