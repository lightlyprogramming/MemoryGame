import java.util.Scanner;

public class MemoryGameTest {
    public static void main(String[] args){
        // initializing objects
        Scanner scan = new Scanner(System.in);

        int diff = 0; // choice of difficulty
        int size = 2*diff; // creates grid of min 4, max 20

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

    }

    private static void displayBoard(int[][] board){
        for(int[] row : board){
            for(int cell : row){
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    // private static void checkCell();
}
