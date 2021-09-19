/*
* Implementation is based on:
     * On the Utility of the Multimodal Problem Generator for
     * Assessing the Performance of Evolutionary Algorithms
     * Fernando G. Lobo, Cl´audio F. Lima
 * The peak heights are linearly interpolated between a maximum
 *   value of 1.0 and a minimum value of h
 */
package benchmarks;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Farhad
 */

// Multi-modal Problem Generator (MPG)

public class MPG {
    private static List<char[]> lstpeaks;
    private static int P;
    private static double LH;
    private static int L;


    private static void GeneratePeaks()
    {
        int i=0;
        while (i<P)
        {
            char[] chrtmp = new char[L];
            for (int j=0;j<L;j++)
            {
                if (Math.random()>0.5)
                    chrtmp[j] = '1';
                else
                    chrtmp[j] = '0';
            }//for j.
            
            lstpeaks.add(chrtmp);
//            System.out.printf("\n%s", String.valueOf(chrtmp));
            i++;
        }//while
    }//GeneratePeaks.

    private static int RetNearestPeak(char[] anind)
    {
        int nearestidx=-1;
        int nearestdist = L+1;
        for (int i=0;i<P;i++)
        {
            int tmppdist = HammingDistance(lstpeaks.get(i), anind);
            if (tmppdist<=nearestdist)
            {
                nearestidx = i;
                nearestdist = tmppdist;
            }//if.
        }//for i.

        return nearestidx;

    }//RetNearestPeak.

    public static int HammingDistance(char[] anIndiv1, char[] anIndiv2)
    {
        int intdis = 0;
        for (int i=0;i<anIndiv1.length;i++)
        {
            if (anIndiv1[i]!=anIndiv2[i])
                intdis++;
        }//for i.
        
//        System.out.printf("\nHD :%s,\t%s:%d", String.valueOf(anIndiv1), String.valueOf(anIndiv2),intdis);
        return intdis;

    }//HammingDistance.

    /**
     * 
     * @param aChromLength: length of the chromosome
     * @param aP: number of peaks
     * @param aLowestHeight: lowest height
     */
    
    public MPG(int aChromLength, int aP, double aLowestHeight)
    {
        P = aP;
        L = aChromLength;
        LH = aLowestHeight;

        lstpeaks = new ArrayList<char[]>();

        GeneratePeaks();
    }//MPG
    
    private static double Height(int idx)
    {
        if (P!=1)
            return LH + (idx * ((1-LH)/(P-1)));
        else
            return 1;
    }//Height

    public static double fitness(char[] anindividual)
    {
        int i = RetNearestPeak(anindividual);

        int HMD = HammingDistance(anindividual, lstpeaks.get(i));
//        System.out.printf("\n**************%d,%d,%f ", i,HMD, Height(i));
        return (((double)L-(double)HMD)/(double)L) * Height(i);

    }//fitness
}
