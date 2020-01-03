package ConwayGOL;
import java.util.Scanner;

public class ConwayGameOfLife {
    public static void main(String[] args){
        ConwayGui cg = new ConwayGui(10);
    }

    public static void testRun(){
        int[][] testInput = 
        {
         {0,0,0,0,0,0},
         {0,0,0,0,0,0},
         {0,0,0,0,0,0},
         {0,0,1,1,1,0},
         {0,0,0,0,0,0},
         {0,0,0,0,0,0}
        };        
    
        Scanner read = new Scanner(System.in);
        int check = 1;

        ConwayMatrix cm = new ConwayMatrix(testInput);

        while(check==1){
            cm.updateMatrix();
            cm.print();
            System.out.println("1 and enter to continue");
            check = read.nextInt();
        }

        read.close();
    }
}