/*
 * Universiti Sains Malaysia 2010
 * A Java imelemenation by Farhad Nadi of Royal Road function
 * Based on : A description of Holland Royal Road function by Terry Jones (1994)
 * http://www.santafe.edu/media/workingpapers/94-11-059.pdf
 * 
 */
package benchmarks;

public class HollandRoyalRoad {

    public static int k = 4;
    public static int b = 8;
    public static int g = 7;
    public static double M1 = 4;
    public static double V = 0.02;
    public static double U1 = 1;
    public static double U = 0.3;

    public HollandRoyalRoad(int ak, int ab, int ag, double aM1, double aV, double aU1, double aU) {
        k = ak;
        b = ab;
        g = ag;
        M1 = aM1;
        V = aV;
        U1 = aU1;
        U = aU;
    }//RoyalRoad

    public HollandRoyalRoad(int ak, int ab, int ag) {
        k = ak;
        b = ab;
        g = ag;
    }//RoyalRoad

    public HollandRoyalRoad() {
        k = 4;
        b = 8;
        g = 7;
        M1 = 4;
        V = 0.02;
        U1 = 1;
        U = 0.3;
    }//RoyalRoad

    public static double fitness(char[] anInd) {

//        

        int TotalNumberofLevels = k+1;// (int) (Math.log(b) / Math.log(2)) + 1;

        //count number of ones in each block
        int i = 0;
        int bn = 0;//block no.
        int TotalnumberofBlock = (int) Math.pow(2, k);
        int[] lvl0Ones = new int[TotalnumberofBlock];

        double res = 0.0;

        while (i < anInd.length) {
            //counting number of 1s within current block
            int cnt = 0;

            for (int j = i; j < i + b; j++) {
                if (anInd[j] == '1') {
                    cnt++;
                }
            }
            lvl0Ones[bn] = cnt;          
//            System.out.printf("\n+%d", lvl0Ones[bn]) ;
            bn++;
            i = bn * (b + g);
        }//while

        // from now on we will wrok on the counted ones of each block and PART share of fitness will be calculated.
        // anpther vector will be constructed first each of its cell corresponds to one block.
        // if a block is filled with all 1s the corresponding cell value will be 1 otherwise it will be 0.
        int[] filledBlocks = new int[lvl0Ones.length];
        for (int j = 0; j < lvl0Ones.length; j++) {
            if (lvl0Ones[j] == b) {
                filledBlocks[j] = 1;
            } else {
                if (lvl0Ones[j] < b && lvl0Ones[j] > M1) {
//                    System.out.printf("\n-%f", (b - lvl0Ones[j]) * -1 * V) ;
                    res += (b - lvl0Ones[j]) * -1 * V;
                }
                if (lvl0Ones[j] <= M1) {                    
                    res += lvl0Ones[j] * V;
                }
                filledBlocks[j] = 0;
            }//if
        }//for j.
  
        // on the following BONUS share of the fitness will be calculated.
        int t = 0;
        int lvlreads = 1;
        
        while (t < TotalNumberofLevels) {
            int bb = 0;
            int lvlfilldblkcnt = 0;
            while (bb < filledBlocks.length) {
                int sum = 0;
                for (i = bb; i < bb + lvlreads; i++) {
                    sum += filledBlocks[i];                    
                }//for i.

                if (sum == lvlreads) {
                    lvlfilldblkcnt++;
                }//if.                
                bb += lvlreads;
            }//while.

            //calculating bonus of level
            if (lvlfilldblkcnt >= 1) {
                res += (lvlfilldblkcnt - 1) * U + U1;
            }
            lvlreads *= 2;
            t++;
        }//while t;

        return res;
    }//fitness
}