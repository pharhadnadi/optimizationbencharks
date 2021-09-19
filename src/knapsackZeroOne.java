/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package benchmarks;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Farhad
 */
public class knapsackZeroOne {

    private static List<int[]> lstitems;
    public static int intmaxload;
    public static int intchromlen;
    private static String strfilename;
    public static boolean  BolIsfeasible = true;

    public knapsackZeroOne(String strconstraintfilename)
    {
        strfilename = strconstraintfilename;

        lstitems = new ArrayList<int[]>();
       
        readconstraints(strfilename);

////        String str = "10011111111100111101101111000010111110000100010111";
//        System.out.printf("\n---------\nitmes:%d,weight:%d\nfit->", intchromlen,intmaxload);
//
//        for (int i=0;i<lstitems.size();i++)
//            System.out.printf("\n%d,%d", lstitems.get(i)[0],lstitems.get(i)[1]);
    }

    private static double Rho()
    {
        //rho = maxi=1..n (p[i]/w[i])

        int tmp[] = new int[2];

        //System.arraycopy(lstitems.get(intmaxload), 0, tmp, 0, tmp.length);
        tmp = lstitems.get(0);

        double maxrho = ((double)tmp[1] / (double)tmp[0]);
//                System.out.printf("\n\t[%d,%d]=%f,%f",tmp[1] , tmp[0] ,maxrho,(double)(tmp[1] / tmp[0]));
        for (int i=0;i<intchromlen;i++)
        {

//            System.arraycopy(lstitems.get(intmaxload), 0, tmp, 0, tmp.length);
            tmp = lstitems.get(i);

            if (maxrho < ((double)tmp[1] / (double)tmp[0]))
                maxrho = ((double)tmp[1] / (double)tmp[0]);
            
        }//for
        return maxrho;
    }//if


    private static void readconstraints(String strinputFilename)
    {
        FileReader fr =  new FileReader(strinputFilename);
           
        String strln = "";
        Scanner input;
        while ((strln = fr.ReadlnFile()) != null)
        {
            
            input = new Scanner(strln);
            
            if (strln.charAt(0)=='w')
            {
                input.next();
                intmaxload = input.nextInt();
             }//if

            if (strln.charAt(0)=='i')
            {
                input.next();
                intchromlen = input.nextInt();
            }//if
            if (strln.charAt(0)=='p')
            {
                int[] intarrtmp = new int[2];

                input.next();
                intarrtmp[0] = input.nextInt();
                intarrtmp[1] = input.nextInt();
                lstitems.add(intarrtmp);
            }//if.
        }//while.
    }//readconstraints.

    public static double Fittness(char[] aChrom)
    {
        //.
        double prof =0;
        double wei =0;
        double pwei =0;
        for (int i=0;i<aChrom.length;i++)
        {
            if (aChrom[i]=='1')
            {
                int[] inttmp = new int[2];
                System.arraycopy(lstitems.get(i), 0, inttmp, 0, 2);
                wei += (double) inttmp[0];
                prof += (double) inttmp[1];
                pwei += (double) inttmp[0] - intmaxload;
            }//.
        }//for i.
        
        double pen=0;
        if (wei > intmaxload)
        {
            double dblrho = Rho();
//            pen =  Math.log( 1.0 + (dblrho * pwei ) )/Math.log(2);
            pen =  dblrho * pwei ;
        }
//        System.out.printf("\n---->%f", prof - Math.abs(pen));
        return prof - Math.abs(pen);
    }//fitness.
    public static double Fittness1(char[] aChrom)
    {

        double intsumval = 0;
        double intsumwit = 0;
        double fltpen=0;
        double pen=0;
        
        for (int i=0;i<aChrom.length;i++)
            if (aChrom[i]=='1')
            {
                int[] inttmp = new int[2];
                System.arraycopy(lstitems.get(i), 0, inttmp, 0, inttmp.length);

                intsumval += (double) inttmp[1]; //profit idx=1
                intsumwit += (double) inttmp[0]; //weight idx=0
                fltpen += (double)inttmp[0] - (double)intmaxload;
            }//if 1.

        if (intsumwit > intmaxload)
        {
            double dblrho = Rho();
            pen =   Math.log(1.0+ dblrho * fltpen) / Math.log(2);
        }
        
        return (intsumval -  Math.abs(pen));
    }//Fittness.
}
