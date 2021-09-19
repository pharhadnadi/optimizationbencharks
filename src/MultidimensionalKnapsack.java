
package benchmarks;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//import java.io.FileReader;
//import benchmarks.FileReader;

/*------------------------------------------------------------------------------
 * Multidimensional Knapsack Problem (MKP) =
 * m-dimensional knapsack problem =
 * multiconstraint knapsack problem =
 * multi-knapsack problem =
 * multiple knapsack problem =
 * multidimensional zero-one knapsack problem
 *Implemented based on definition in:
 *      Solving the Multidimensional Knapsack Problem
 *      Using an Evolutionary Algorithm Hybridized
 *      with Branch and Bound
 *      Jose E. Gallardo, Carlos Cotta, and Antonio J. Fernandez
 * -----------------------------------------------------------------------------
 * input files format are suposed to be like in OR-library
 *              (http://people.brunel.ac.uk/~mastjjb/jeb/orlib/mknapinfo.html)
 * there is also a formal definition of the problem there
 *   which is copy-pasted here.
 *
 *  The problem to be solved is:
 *      Max  sum{j=1,...,n} p(j)x(j)
 *      st   sum{j=1,...,n} r(i,j)x(j) <= b(i)       i=1,...,m
 *                          x(j)=0 or 1
 *----------------------------------------------------------------------------*/

/**
 * @author Farhad Nadi
 */

public class MultidimensionalKnapsack {

    public static List<Double> lstItems; //0..n
    public static List<Integer> lstCapacities; //0..m
    public static List<int[]> lstWeight; //m*n
    
    public static int intchromlen;
    public static int intdimension;
    public static int intitems;
    public static int intglobaloptima;

    private static  String strln = "";
    private static  Scanner input;


    private static String strfilename;

    /**
     * 
     * @param strconstraintfilename : a path to the file contraining the instance information
     */
    public MultidimensionalKnapsack(String strconstraintfilename)
    {
        strfilename = strconstraintfilename;
        lstItems = new ArrayList<Double>();
        lstCapacities = new ArrayList<Integer>();
        lstWeight = new ArrayList<int[]>();

        readconstraints(strfilename);
    }



    private static double ReadNextdouble(FileReader fr)
    {
        double res=-1;
        if (input.hasNext())
            res = input.nextDouble();
        else
        {
            if ((strln = fr.ReadlnFile()) == null)
                res = -1;
            else
            {
                input = new Scanner(strln);
                res = input.nextDouble();
            }//else
        }//else
        return res;
    }//ReadNextint.

/**
 *
 *  Format of this data file is:
 * number of test problems (K)
 * then for each test problem k (k=1,...,K) in turn:
 *    number of variables (n), number of constraints (m), optimal
 *    solution value (zero if unavailable)
 *    the coefficients p(j); j=1,...,n
 *    for each constraint i (i=1,...,m): the coefficients r(i,j); j=1,...,n
 *    the constraint right-hand sides b(i); i=1,...,m
 * In the other words,  there are n items and m knapsacks. there 1 set (i=1..n) 
 *          profits and m sets (i=1..n) costs (weights) for each knapsack.
 */
    private static void readconstraints(String strinputFilename)
    {
        benchmarks.FileReader fr =  new benchmarks.FileReader(strinputFilename);
        
        input = new Scanner(strln);

        intitems = (int) ReadNextdouble(fr); // number of variables (n)

        intchromlen = intitems;
        intdimension = (int) ReadNextdouble(fr); // number of constraints (m)
        intglobaloptima = (int) ReadNextdouble(fr); // optimal solution value (zero if unavailable)
        
        //the coefficients p(j); j=1,...,n
        int j=0;
        while ( j<intitems )
        {
            j++;
            lstItems.add(ReadNextdouble(fr)); // number of variables (n)
        }//if.

        //for each constraint i (i=1,...,m): the coefficients r(i,j); j=1,...,n
        for (int i=0;i<intdimension;i++)
        {
            int inttmp[] = new int[intchromlen];
            for (j=0;j<intchromlen;j++)
            {
              inttmp[j] = (int) ReadNextdouble(fr);
            }//for j.
            lstWeight.add(inttmp);
        }//for i.

        for ( j=0;j<intdimension;j++)
                lstCapacities.add((int) ReadNextdouble(fr)); // number of variables (n)
        
    }//readconstraints.

    private static double Rho(int midx)
    {
        //rho = maxi=1..n (p[i]/w[i])

        int wei[] = new int[intchromlen];
        System.arraycopy(lstWeight.get(midx), 0, wei, 0, intchromlen);

        
        double maxrho = lstItems.get(0)/wei[0];
        for (int i=1;i<intchromlen;i++)
        {
            if (maxrho < ((double)lstItems.get(i) / (double)wei[i]))
                maxrho = ((double)lstItems.get(i) / (double)wei[i]);
        }//for
        return maxrho;
    }//if

    
    public static double Fittness(char[] aChrom)
    {

        double intsumval = 0;
        int intsumweit = 0;
        double intsumj = 0;
        double pen = 0;

        int pwei = 0;

        for (int j=0;j<intchromlen;j++) //calculating Sigma(Pj*Xj) j=1..n
        {
            if (aChrom[j]=='1')
                intsumval += lstItems.get(j);
        }//for j.

        for (int i=0;i<intdimension;i++)
        {
            int[] inttmp = new int[intchromlen]; //reading the weights for this knapsack
            System.arraycopy(lstWeight.get(i), 0, inttmp, 0, inttmp.length);

            intsumweit = 0;

            for (int j=0;j<intchromlen;j++)
            {
                if (aChrom[j]=='1')
                    intsumweit += inttmp[j];

                if (intsumweit > lstCapacities.get(i))
                {
                    pwei += (double) inttmp[j] - lstCapacities.get(i);
                }//if
            }//for j.
            //calculating penalty for this knapsack
            pen += Rho(i) * pwei;
            pwei = 0;
        }//for i.
      //  System.out.printf("\n%d, %f\n", intsumval , pen);
        return intsumval - Math.abs(pen);
    }//Fittness.
}
