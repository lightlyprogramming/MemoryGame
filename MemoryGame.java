import java.util.Scanner;

public class MemoryGame {
    public static void main(String[] args){
        // initializing objects
        Scanner scan = new Scanner(System.in);

        int diff = 0; // choice of difficulty
        int size = 2*diff; // creates grid of min 4, max 20

        boolean playGame = true;

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

        for(int i = 0; i < numList.length; i+=2){
            numList[i] = filler;
            numList[i+1] = filler;
            filler++;
        }

        // for(int i = 0; i < numList.length; i++){
        //     System.out.print(numList[i] + ", ");
        // }

        System.out.println();

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
                hiddenBoard[i][j] = 0;
                // gameBoard[i][j] = (index != 0) ? index : 
            }
        }


        // for(int i = 0; i < length; i++){
        //     for(int j = 0; j < size; j++){
        //         System.out.print(gameBoard[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        displayBoard(gameBoard);
        System.out.println();
        displayBoard(hiddenBoard);
        System.out.print(String.format("\033[2J")); // Attributed to StackOverflow, user LabOctoCat


        while(playGame){
            displayBoard(hiddenBoard);
            System.out.println("Enter the coordinates of the first cell with the row (1-" + size + ") first and column (1-" + size + ") second. Enter -1 to quit. The board is " + size + " by " + size + ".");
            int row1 = scan.nextInt() - 1;
            int col1 = scan.nextInt() - 1;
            if(row1 == -1 || col1 == -1) playGame = false;
            displayTempBoard(hiddenBoard, gameBoard, row1, col1);

            System.out.println("Enter the coordinates of the second cell with the row (1-" + size + ") first and column (1-" + size + ") second. Enter -1 to quit. The board is " + size + " by " + size + ".");
            int row2 = scan.nextInt() - 1;
            int col2 = scan.nextInt() - 1;
            if(row2 == -1 || col2 == -1) playGame = false;
            displayTempBoard(hiddenBoard, gameBoard, row2, col2);
        }

        System.out.println("Thanks for playing!");
    }

    private static void displayBoard(int[][] board){
        for(int[] row : board){
            for(int cell : row){
                System.out.print(cell + " "); // implement if statement
            }
            System.out.println();
        }
    }

    

    private static void displayTempBoard(int[][]hiddenBoard, int[][]gameBoard, int r1, int c1){
        hiddenBoard[r1][c1] = gameBoard[r1][c1];
        displayBoard(hiddenBoard);
    }
}
