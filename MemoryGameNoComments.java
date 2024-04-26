import java.util.Scanner;

public class MemoryGame {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        int diff = 0; 
        int size = 2*diff; 

        int guesses = 0;
        int unmatched = 0; 

        boolean playGame = true;

        while (!(diff >= 1 && diff <= 5)){ 
            System.out.println("Input your difficulty (1-5): ");
            diff = scan.nextInt();
        }

       size = 2*diff; 

        int[][] hiddenBoard = new int[size][size];
        int[][] gameBoard = new int[size][size];

        int[] numList = new int[size*size];
        int filler = 1;
        
        for(int i = 0; i < numList.length; i+=2){
            numList[i] = filler;
            numList[i+1] = filler;
            filler++;
        }

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
            }
        }

        while(playGame == true){ 
            displayBoard(hiddenBoard); 
            System.out.println("Enter the coordinates of the first cell with the row (1-" + size + ") first and column (1-" + size + ") second. Enter -1 to quit. The board is " + size + " by " + size + "."); 
            int row1 = scan.nextInt() - 1;
            if(row1 == -2){
                playGame = false;
                break;
            }
            int col1 = scan.nextInt() - 1;
            if(col1 == -2){
                playGame = false;
                break;
            }
            
            displayTempBoard(hiddenBoard, gameBoard, row1, col1);

            System.out.println("Enter the coordinates of the second cell with the row (1-" + size + ") first and column (1-" + size + ") second. Enter -1 to quit. The board is " + size + " by " + size + "."); 
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
            displayTempBoard(hiddenBoard, gameBoard, row2, col2); 

            checkCell(hiddenBoard, gameBoard, row1, col1, row2, col2);
            guesses++;

            try { Thread.sleep(1500);} catch (Exception e){} 
            System.out.print(String.format("\033[2J")); 

            unmatched = 0; 
            for(int[] row : hiddenBoard){
                for(int cell : row){
                    if(cell == 0){ 
                        unmatched++; 
                    } 
                }
            }
            if(unmatched == 0){
                playGame = false; 
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

    private static void displayTempBoard(int[][]hiddenBoard, int[][]gameBoard, int r1, int c1){
        hiddenBoard[r1][c1] = gameBoard[r1][c1];
        displayBoard(hiddenBoard);
    }
    
    private static void checkCell(int[][]hiddenBoard, int[][] gameBoard, int r1, int c1, int r2, int c2){
        if(gameBoard[r1][c1] == gameBoard[r2][c2]){
            System.out.println("You found a match!");
        } else {
            hiddenBoard[r1][c1] = 0;
            hiddenBoard[r2][c2] = 0;
        }
    }
}
