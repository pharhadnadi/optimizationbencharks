/*
 * Universiti Sains Malaysia 2010
 * A Java imelemenation by Farhad Nadi of Royal Road function
 * Based on : The Royal Road for Genetic Algorithms: Fitness Landscapes and GA Performance by Melanie Mitchell (1991)
 * http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.114.5245&rep=rep1&type=pdf
 * 
 */
package benchmarks;

public class RoyalRoad {
    
    private static int intBBSiz;
    private static int intNoofBBs;
    
    
    /**
     * 
     * @param aBuildingBlockSize : length of building block
     * @param aNumberofBlocks  : number of building blocks within a chromosome. This number should be a a devisor of 2.
     */
    public RoyalRoad(int aBuildingBlockSize, int aNumberofBlocks){        
        intBBSiz = aBuildingBlockSize;
        intNoofBBs = aNumberofBlocks;        
    }//RoyalRoad.
    
     public static double fitness(char[] anInd) {
  
        int TotalNumberofLevels = (int) (Math.log(intNoofBBs) / Math.log(2)) + 1;

        //count number of ones in each block
        int i = 0;
        int bn = 0;//block no.
        int[] lvl0Ones = new int[intNoofBBs];

        double res = 0.0;

        while (i < anInd.length) {
            //counting number of 1s within current block
            int cnt = 0;

            for (int j = i; j < i + intBBSiz; j++) {
                if (anInd[j] == '1') {
                    cnt++;
                }
            }
            lvl0Ones[bn] = cnt;          

            bn++;
            i = bn * intBBSiz;
        }//while

        int[] filledBlocks = new int[lvl0Ones.length];
        for (int j = 0; j < lvl0Ones.length; j++) {
            if (lvl0Ones[j] == intBBSiz) {
                filledBlocks[j] = 1;
                res += intBBSiz;
            } else {
                filledBlocks[j] = 0;              
            }//if
        }//for j.
        //calculaing the fitness of higher level.
                    

        int intlvlblk = 2;
        i=0;
//                      System.out.printf("\n+%d", TotalNumberofLevels) ; 
        while(intlvlblk <= intBBSiz*intNoofBBs)
        {
            int sum = 0; 
            i= 0;
            while(i<filledBlocks.length)
            {
             sum += filledBlocks[i];
              
              i++;
 
             if (i%intlvlblk==0 && i>0)
             {
                 
              
                 if (sum==intlvlblk)
                     res += sum * intBBSiz;
                  sum=0; 
             }//if.     
             
            }//while
            intlvlblk *=2;
        }//while

        return res;
    }//fitness

}
