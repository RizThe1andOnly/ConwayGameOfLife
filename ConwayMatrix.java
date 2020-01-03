package ConwayGOL;
import java.util.*;

public class ConwayMatrix{

    List<List<Integer>> matrix = new ArrayList<>();

    public ConwayMatrix(int[][] input){
        this.matrix = setMatrix(input);
    }

    public ConwayMatrix(List<List<Integer>> input){
        this.matrix = setMatrix(input);
    }

    public ConwayMatrix(int input){
        this.matrix = setMatrix(input);
    }


    /**
     * three versions of setMatrix, one for if input is a regular array
     * and one for if input is a List<List<Integer>>, and another if just a number of cells
     * 
     * The first one is for regular array input
     * @param input : regular 2d array
     * @return : new 2d List to be processed
     */
    private List<List<Integer>> setMatrix(int[][] input){
        
        List<List<Integer>> newMatrix = new ArrayList<>();
        
        for(int i=0;i<input.length;i++){
            List<Integer> row = new ArrayList<Integer>();
            newMatrix.add(row);
            for(int j=0;j<input[i].length;j++){
                newMatrix.get(i).add(input[i][j]);
            }
        }

        return newMatrix;
    }

    /**
     * 
     * @param input : 2d list
     * @return : new 2d list so that reference address not the same
     */
    private List<List<Integer>> setMatrix(List<List<Integer>> input){
        List<List<Integer>> newMatrix = new ArrayList<>();
        
        ListIterator rows = input.listIterator();
        while(rows.hasNext()){
            List<Integer> newRow = new ArrayList<>();
            List<Integer> col = (List<Integer>) rows.next();
            ListIterator columns = col.listIterator();
            while(columns.hasNext()){
                newRow.add((Integer)columns.next());
            }
            newMatrix.add(newRow);
        }

        return newMatrix;
    }

    /**
     * @param input: integer that denotes the number of cells for the dimensions of the square matrix
     */
    private List<List<Integer>> setMatrix(int input){
        List<List<Integer>> toBeReturned = initConwayArray(input);
        return toBeReturned;
    }


    /**
     * updates the matrix based on conway's rules. This function is called
     * by classes that have this object such as test and the actual conway 
     * gui.
     */
    public void updateMatrix(){
        
        //set up arrays that will be used to check neighbors and create
        //updated matrix
        List<List<Integer>> updatedMatrix = new ArrayList<>();
        List<List<Integer>> oldMatrix = this.matrix;

        /** 
         * for each element in oldMatrix:
         * 
         * - pass the element's idices and the oldMatrix to readNeighbors
         *   which will return matrix of neighbors
         * 
         * - pass the neighbor matrix to checkRules whichi will return 1 or 0
         *   which will be the new state of the cell based on rules
         * 
         * each of the new states will be recorded in updatedMatrix
         **/

         for(int i=0;i<oldMatrix.size();i++){
             List<Integer> newRow = new ArrayList<>();
             updatedMatrix.add(newRow);
             for(int j=0;j<oldMatrix.get(i).size();j++){
                 int neighbors = readNeighbors(oldMatrix,i,j);
                 int currentState = oldMatrix.get(i).get(j);
                 //System.out.println("("+i+","+j+")"+"  : CurrentState= "+currentState+"  Neighbors= "+neighbors);
                 int newState = checkRules(neighbors,currentState);
                 updatedMatrix.get(i).add(newState);
             }
         }

         this.matrix = updatedMatrix;
        
    }

    private int readNeighbors(List<List<Integer>> oldMatrix,int r, int c){
        /**
         * "r" is row number and "c" is column number (they are indices of the element)
         * readNeighors will go through the neighbors of the current cell:
         *   - go through row index -1 to row index + 1 and same for column
         *   - check the indices found in prev step to see if 1 or 0
         *   - up count of neighbors for each "1" found
         * 
         * a try/catch block will be there for indexOutOfBounds, in which case neighbor will not
         * be updated and the loop will continue, this is for the elements at the edge of the matrix
         * 
         * also neighbors will not be updated with +1 for the element itself
         */

         int neighbors = 0;
         for(int i=r-1;i<=(r+1);i++){
             for(int j=c-1;j<=(c+1);j++){
                 if((i==r)&&(j==c)) continue;
                 try {
                     int neighbor = oldMatrix.get(i).get(j);
                     if(neighbor==1) neighbors++;
                     continue;
                 } catch (IndexOutOfBoundsException e) {
                     //TODO: handle exception
                     continue;
                 }
             }
         }

         return neighbors;
    }

    private int checkRules(int neighbors,int currentState){
        /**
         * gets the number of neighbors an element has
         * checks the rules based on the number of neighbors and currentState
         * if pass then returns 1 else returns 0
         * 
         * Rules:
         *  1- Any live cell with fewer than two live neighbours dies
         *  2- Any live cell with two or three live neighbours lives
         *  3- Any live cell with more than three live neighbours dies
         *  4- Any dead cell with exactly three live neighbours becomes a live cell
         * 
         * condensed version: 
         *  1-  (neighbors == 2||3) && (currentState==1) -> live
         *  2-  (currentState==0)&&(neighbors==3) -> live
         *  3-  else -> dead
         * 
         * Rules from Wikipedia
         */

         int liveVar = neighbors; //testing purposes
         if((currentState==1)&&((neighbors==2)||(neighbors==3))) return 1;
         if((currentState==0)&&(neighbors==3)) return 1;
         return 0;

    }

    /**
     * If input for constructor is an int for how many cells then a new list will be created initialized with all zeros
     * 
     * @param cells: number of cells for square matrix
     * 
     */
    private List<List<Integer>> initConwayArray(int cells){
        List<List<Integer>> newArray = new ArrayList<>();
        for(int i=0;i<cells;i++){
            List<Integer> newRow = new ArrayList<>();
            newArray.add(newRow);
            for(int j=0;j<cells;j++){
                newArray.get(i).add(0);
            }
        }

        return newArray;
    }

    
    public void print(){
        ListIterator rows = this.matrix.listIterator();
        while(rows.hasNext()){
            List<Integer> columns = (List<Integer>) rows.next();
            ListIterator columnElements = columns.listIterator();
            while(columnElements.hasNext()){
                System.out.print(columnElements.next()+"  ");
            }
            System.out.print("\n");
        }
    }
}