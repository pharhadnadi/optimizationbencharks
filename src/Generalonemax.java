/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Farhad
 */


public class Generalonemax {

    private static double fltalpha;
    private static double fltbeta;

    /**
     *                 -
     *                 | alpha,  x(i)=beta
     * f[x(0)..x(n)] = |
     *                 | 0    ,  x(i) != Beta
     *                 -
     * set beta = -1 for x(i)=i;
     */
    public Generalonemax(double alpha, double beta)
    {
        fltalpha = alpha;
        fltbeta = beta;
    }//Generalonemax initializor.

public  double Fittness(double[] aChrom)
{
    double fltcnt = 0.0;

    for (int i=0;i<aChrom.length;i++)
    {
        if (aChrom[i] == fltbeta || (aChrom[i] == i+1 && fltbeta == -2))
            fltcnt += fltalpha;
    }//for.
    return fltcnt;
}//Fittness.
}
