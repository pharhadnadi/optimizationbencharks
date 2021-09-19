package benchmarks;
/**
 *
 * @author Farhad Nadi
 */
public class nPeakMAXSAT {

    private static int[][] peanks = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//1-peak
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//2-peaks
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//3-peaks
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//4-peaks
        {1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},//5-peaks
        {1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}};//6-peaks

    public static double RetAve(int indiv[], int n) {
        double dblres = 0.0;
        for (int i = 0; i < indiv.length; i++) {
            dblres += Math.abs(indiv[i]-peanks[n-1][i]);
        }//for i.
        if (n>1)
            dblres = dblres /(double ) (indiv.length+1);
        else
            dblres = dblres /(double ) indiv.length;

        return  dblres;
    }//RetAve.

    public static double RetMax(double dblaVal1, double dblaVal2) {
        return dblaVal1>dblaVal2?dblaVal1:dblaVal2;
    }//RetMax.
    public static double fitness(char indiv[], int n) {
        int[] atmp = new int[indiv.length];
        
        for (int i=0;i<indiv.length;i++)
        {
            atmp[i] = indiv[i]=='1'?1:0;
        }//for i.        
        return fitness(atmp, n);
    }//fitness
    
    public static double fitness(int indiv[], int n) {
        double dblres = 0.0;
        if (n==1)
            dblres = RetAve(indiv, 1);
        else
            dblres = RetMax(fitness(indiv, n-1), ((double)indiv[0]/(indiv.length+1))+ (RetAve(indiv, n)));
        
//        System.out.printf("\n");       
//        for (int i=0;i<indiv.length;i++)
//        {
//            System.out.printf("%d",indiv[i]);
//        }//for i.
//        System.out.printf(",%f",dblres);
        
        return dblres;
    }
}
