/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package benchmarks;

/**
 * @author Farhad
 */
public class maxone {

public  int Fittness(char[] aChrom)
{
    int intcnt=0;

    for (int i=0;i<aChrom.length;i++)
    {
        if (aChrom[i]=='1')
            intcnt ++;//=(i+1);
        
    }//for.
    return intcnt;
}//Fittness.
}

