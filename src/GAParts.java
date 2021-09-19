package benchmarks;

import java.util.List;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Farhad
 */
public class GAParts {

    public static double RetRawRandomNumber(double aMin, double aMax) {
        return (Math.random() * (aMax - aMin )) + aMin;
    }//doRawRandomNumber.

    public char[] GeneraterandomSolution(int intachromlength) {
        char[] ares = new char[intachromlength];
        Random rnd = new Random(System.currentTimeMillis());

        for (int j = 0; j < intachromlength; j++) {
            if (rnd.nextInt(2) == 1) {
                ares[j] = '1';
            } else {
                ares[j] = '0';
            }
        }//while j.

        return ares;
    }//GeneraterandomSolution

    public void Recombine_1point(char[] aChrom1, char[] aChrom2) {
        Random rnd = new Random();
        char[] chrtmp = new char[aChrom1.length];
        System.arraycopy(aChrom1, 0, chrtmp, 0, aChrom1.length);
        int idx = rnd.nextInt(aChrom1.length);
        System.arraycopy(aChrom2, idx, aChrom1, idx, aChrom1.length - idx);
        System.arraycopy(chrtmp, idx, aChrom2, idx, aChrom1.length - idx);


    }//

    public void Recombine_ArithmeticRecombination(double[] aChrom1, double[] aChrom2, double Alpha) {
        int k;
        Random rnd= new Random();
        k = rnd.nextInt(aChrom1.length);

        for (int i=0;i<aChrom1.length;i++)
        {
            if (i<k)
                aChrom1[i] =  (Alpha * aChrom1[i]) + ((1 - Alpha) * aChrom2[i]);
            else
                aChrom2[i] = (Alpha * aChrom1[i]) + ((1 - Alpha) * aChrom2[i]);
        }//for i.
    }//Recombine_ArithmeticRecombination.
    public void Recombine_WholeArithmeticRecombination(double[] aChrom1, double[] aChrom2, double Alpha) {
        double Xi, Yi;
        /**Based on Page no.51
         * @BOOK{Eiben2007,
        title = {Introduction to Evolutionary Computing},
        publisher = {Springer},
        year = {2007},
        author = {Eiben, A.E. and Smith, J.E. },
        series = {Natural Computing Series },
        edition = {2}
         */
        for (int i = 0; i < aChrom1.length; i++) {
            Xi = aChrom1[i];
            Yi = aChrom2[i];
            aChrom1[i] = (Alpha * Xi) + ((1 - Alpha) * Yi);
            aChrom2[i] = (Alpha * Yi) + ((1 - Alpha) * Xi);

//                if (aChrom1[i]>10||aChrom2[i]>10)
//                    System.out.printf("\n\n\n\t********ch1:%f,ch2:%f",aChrom1[i], aChrom2[i]);
        }//for i.
    }//Recombine_WholeArithmaticRecombination.

    public void Recombine_Uniform(char[] aChrom1, char[] aChrom2) {
        char chrtmp;
        for (int i = 0; i < aChrom1.length; i++) {
            if (Math.random() > 0.5)//swap
            {
                chrtmp = aChrom1[i];
                aChrom1[i] = aChrom2[i];
                aChrom2[i] = chrtmp;
            }//if.
        }//for.
    }//Recombine_Uniform.

    public void Mutation_RandomSeting_FULL(char[] anindiv, double fltMUTrate) {
        Random rnd = new Random(System.currentTimeMillis());
        for (int i = 0; i < anindiv.length; i++) {
            if (Math.random() < fltMUTrate) {
                if (rnd.nextInt(2) == 0) //            {
                //            if (anindiv[i]=='1')
                {
                    anindiv[i] = '0';
                } else {
                    anindiv[i] = '1';
                }
//            }
            }//if.
        }//for i.
    }

    public void Mutation_RandomSeting(char[] anindiv) {
        Random rnd = new Random();
        int plc1 = rnd.nextInt(anindiv.length);
        if (anindiv[plc1] == '1') {
            anindiv[plc1] = '0';
        } else {
            anindiv[plc1] = '1';
        }
    }//Mutation_RandomSeting

    public void Mutation_swap_FULL(char[] anindiv, double fltmutrate) {
        for (int i = 0; i < anindiv.length; i++) {
            if (Math.random() < fltmutrate) {
                Random rnd = new Random();
                int plc2 = rnd.nextInt(anindiv.length);
                char chr = anindiv[i];
                anindiv[i] = anindiv[plc2];
                anindiv[plc2] = chr;
            }//if.
        }//for i.
    }

    public void Mutation_Uniform(double[] aChrom, double L, double U, double fltmutrate) {
        for (int i = 0; i < aChrom.length; i++) {
            if (Math.random() < fltmutrate) {
                Random rnd = new Random();
                aChrom[i] = L + (rnd.nextDouble() * Math.abs(U - L));
            }//if.
        }//for i.
    }//Mutation_Uniform

    public void Mutation_smallchange(double[] aChrom, double L, double U, double fltmutrate) {
        for (int i = 0; i < aChrom.length; i++) {
            if (Math.random() < fltmutrate) {
                Random rnd = new Random();
                double delta = 0.5;
                if (Math.random()>0.5)
                    delta*=-1;
                aChrom[i] += delta;
            }//if.
            if (aChrom[i]<L)
                aChrom[i] = L;
            if (aChrom[i]>U)
                aChrom[i] = U;
        }//for i.
    }//Mutation_smallchange

    public void Mutation_swap(char[] anindiv) {
        Random rnd = new Random();

        int plc1 = rnd.nextInt(anindiv.length);
        int plc2 = rnd.nextInt(anindiv.length);

        char chr = anindiv[plc1];
        anindiv[plc1] = anindiv[plc2];
        anindiv[plc2] = chr;
    }//Mutation_RandomSeting

    public void Recombine_harmony1(List<char[]> parents, char[] child) {
        Random arnd = new Random();
        int intchromlen = parents.get(0).length;

        for (int i = 0; i < intchromlen; i++) {
            child[i] = parents.get(arnd.nextInt(parents.size()))[i];
        }//for i.
    }//Recombine_harmony.
}
