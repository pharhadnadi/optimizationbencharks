/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package benchmarks;

import javax.print.DocFlavor.STRING;

/**
 *
 * @author Farhad
 */
public class Trap {
    static private double fltFlow;
    static private double fltFhigh;
    static private int intak;
    static private int intam;

    /**
     * 
     * @param aK : K value for Trap function
     * @param aM : M value for trap function
     *   A K*M Trap function will be calculated as follows:
     *      - A K*M bit string is the input for the trap function.
     *      - The input string will be divided into M substring of length K.
     *      - Either of the M substrings will be send to the gneral K-bit trap function.
     *      - the final result will be summation of valus of the M substrings.
     */
    public Trap(int aK, int aM)
    {
        //Usually, fhigh is set at k and flow is set at k-1
        fltFhigh = aK;
        fltFlow = aK-1;
        intak = aK;
        intam = aM;
    }//trap.
    public Trap(int aK, int aM, double aFhigh, double aFlow)
    {
        //Usually, fhigh is set at k and flow is set at k-1
        fltFhigh = aFhigh;
        fltFlow = aFlow;
        intak = aK;
        intam = aM;
    }//trap.

    public double Fitness(String AnIndiv)
    {
        double fltres = 0;
        if (AnIndiv.length()>=intak*intam)
        {
            //.
            for (int i=0;i<intam;i++)
            {
//                System.out.printf("\n[k=%d,m=%d]", intak, intam );
                String asubstr = AnIndiv.substring(i*intak, i*intak+intak);
//                System.out.printf("\n%s(%s[%d,%d])", asubstr, AnIndiv, i*intak, i*intak+intak );
                fltres += Fk(asubstr);
            }//for i.
        }//if
        return fltres;
    }//Fitness.

    private int U(String anIndiv)
    {
        int intsum = 0;
        for (int i=0;i<anIndiv.length();i++)
        {
            if (anIndiv.charAt(i) == '1' )
            {
                intsum++;
            }//if.
        }//for i.
        return intsum;
    }//u.

    private double Fk(String AnIndiv)
    {
        int intK = AnIndiv.length();
        int intU = U(AnIndiv);
        double fltres;

        if (intK == intU)
            fltres = fltFhigh;
        else
            fltres = fltFlow - (intU * (fltFlow / (intK-1)));

        return  fltres;
    }//Fk.
    private double Fk1(String AnIndiv)
    {
        int intK = AnIndiv.length();
        int intU = U(AnIndiv);
        double fltres = 0;

        switch (intU) {
            case 0:  fltres = 0.75; break;
            case 1:  fltres = 0.50; break;
            case 2:  fltres = 0.25; break;
            case 3:  fltres = 0.00; break;
            case 4:  fltres = 1.00; break;
        }//switch.
        return  fltres;
    }//Fk.
}//Trap calss
