/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package benchmarks;
/**
 *
 * @author Farhad
 */

import java.util.Random;

public class unconstraintedGO {




    /**
     * Branin function
     * find definition of the function here: http://www.it.lut.fi/ip/evo/functions/node27.html
     * This function is
     *      1- multimodal
     *      2- non-seperable
     *      3- has 3 global optima (which is zero) and NO local optima
     *          3.1- GO1 = (-3.141592,12.274999)
     *          3.2- GO2 = (3.141592,2.275000)
     *          3.3- GO2 = (9.424777,2.474999)
     *      constraints for variables are [-5 ≤ x1 ≤ 10 ; 0 ≤ x2 ≤ 15]
     * @param x1 : x1 value
     * @param x2 : x2 value
     * @return value of Branin function
     */
    static public double Branin(double x1,double x2)
    {
        double fltp1=(x2 - (5.1/(4*Math.PI*Math.PI))*(x1*x1) + ((5/Math.PI)*x1)-6);
        return Math.pow(fltp1, 2) + 10 * ( 1- (1/8*Math.PI)) * Math.cos(x1)+10;
    }



    /**
     * Search domain: −15 ≤ xi ≤ 30, i = 1, 2, . . . , n.
     * Number of local minima: several local minima.
     * The global minimum: x* =  (0, …, 0), f(x*) = 0.
     * @param x1 : vector of variables
     * @return value of ackly fucnction
     */
    static public double Ackly(double[] x)
    {
        int intvarnum = x.length;
        double flta = 20;
        double fltb = 0.2;
 
        double fltsum1 = 0;
        double fltsum2 = 0;
        for (int i=0;i<intvarnum;i++)
        {
           
            fltsum1 += x[i] * x[i];
            fltsum2 += Math.cos(2 * Math.PI*x[i]);
            
        }
        double ackres = -20 * Math.exp(-0.2*Math.sqrt((1/intvarnum)*fltsum1));
        double fltin = ((1/(double)intvarnum))*fltsum2;
        ackres += -1*Math.exp(fltin)+20+Math.exp(1);

        return   ackres;
    }// Ackly

    /**
     * Search domain: −5 ≤ xi ≤ 10, i = 1, 2, . . . , n.
     * Number of local minima: no local minimum except the global one.
     * The global minima: x* =  (0, …, 0), Zn(x*) = 0.
     * @param x : vector of X
     * @return Zakharov value
     * 
     */
    static public double Zakharov(double[] x)
    {
        int intvarnum = x.length;
        double fltsum1 = 0;
        double fltsum2 = 0;
        
        for (int j = 0;j<intvarnum;j++)
        {
            fltsum1 += x[j] * x[j];
            fltsum2 += 0.5 * j * x[j];
        }//for i.

        return  fltsum1+Math.pow(fltsum2,2)+Math.pow(fltsum2,4);
    }//Zakharov

    static public double Binaryf6(double x1, double x2)
    {
        return 0.5 - (Math.pow(Math.sin(Math.sqrt((x1*x1)+(x2*x2))), 2) - 0.5)/(1.0+0.001*Math.pow( (x1*x1)+ (x2*x2), 2));
    }//Binaryf6.

/**
 *Search domain: −5 ≤ xi ≤ 10, i = 1, 2, . . . , n.
 * Number of local minima: several local minima.
 * The global minima: x* =  (1, …, 1), f(x*) = 0.
 * @param x : vector of X
 * @return : Rosenbrock value
 */
    static public double Rosenbrock(double[] x)
    {
        int intvarnum = x.length;
        
        double fltsum = 0;
        for (int i = 0;i<intvarnum-1;i++)
            fltsum += 100*(Math.pow(x[i+1]-Math.pow(x[i], 2),2)+Math.pow(x[i]-1,2));
        return fltsum;
    }//Rosenbrock
    /**
     *
     * Number of variables: n variables.
     * Definition:
     * Search domain: −n2 ≤ xi ≤ n2, i = 1, 2, . . . , n.
     * Number of local minima: no local minimum except the global one.
     * The global minima:          f(x*) = -50    for n=6,
     * f(x*) = -200 for n=10.
     *  GO for
     *      n=6     x[i]= i * (7-i)
     *      n=10    x[i]= i * (11-i)
     * @param x :vector of X
     * @return Trid value
     */
    static public double Trid (double[] x)
    {
        int intvarnum = x.length;
        double fltsum1 = 0;
        double fltsum2 = 0;
        for (int j = 0;j<intvarnum;j++)
            fltsum1 += Math.pow((x[j]-1),2);

        for (int j = 1;j<intvarnum;j++)
            fltsum2 += x[j]*x[j-1];
        return  fltsum1-fltsum2;
    }//Trid


/**
 * Search domain: −10 ≤ xi ≤ 10, i = 1, 2, . . . , n.
 * Number of local minima: no local minimum except the global one.
 * The global minima: x* =  (0, …, 0), f(x*) = 0.
 * @param x : x :vector of X
 * @return Sum Squares value
 */
    static public double SumSquares (double[] x)
    {
        int intvarnum = x.length;
        double fltsum = 0;
        for (int j = 0;j<intvarnum;j++)
            fltsum += j*Math.pow(x[j],2);
        return fltsum;
    }//SumSquares


    static public double Shubert (double[] x)
    {
        int intvarnum = x.length;
        double fltsum1 = 0;
        double fltsum2 = 0;
        for (int i = 0;i<intvarnum;i++)
        {
            fltsum1 += i*Math.cos((i+1)*x[0]+i);
            fltsum2 += i*Math.cos((i+1)*x[1]+i);
        }//for i

        return  fltsum1*fltsum2;
    }//Shubert
/**
 * Search domain: 0 ≤ xi ≤ 10, i = 1, 2, 3, 4.
 * Number of local minima: m local minima.
 * The global minima: x* =  (4, 4 , 4, 4),
 *      f(x*) = -10.1532 for m = 5.
 *      f(x*) = -10.4029 for m = 7.
 *      f(x*) = -10.5364 for m = 10.
 * @param x : vector of X
 * @param m : hsould be either 5, 7, ot 10
 * @return : Shekel value
 */
    static public double shekel(double[] x, int m)
    {
        int intvarnum = x.length;
        double [][] a =
        {
            {4.0000,4.0000,4.0000,4.0000},
            {1.0000,1.0000,1.0000,1.0000},
            {8.0000,8.0000,8.0000,8.0000},
            {6.0000,6.0000,6.0000,6.0000},
            {3.0000,7.0000,3.0000,7.0000},
            {2.0000,9.0000,2.0000,9.0000},
            {5.0000,5.0000,3.0000,3.0000},
            {8.0000,1.0000,8.0000,1.0000},
            {6.0000,2.0000,6.0000,2.0000},
            {7.0000,3.6000,7.0000,3.6000}
        };
        double c[] = new double[10];
        c[0] = 0.1; c[1] = 0.2; c[2] = 0.2; c[3] = 0.4; c[4] = 0.4;
        c[5] = 0.6; c[6] = 0.3; c[7] = 0.7; c[8] = 0.5; c[9]= 0.5;
        double fltsum = 0;
        for (int j = 0;j<m;j++)
        {
            double p = 0;
            for (int i = 0;i<4;i++)
                p += Math.pow(x[i]-a[j][i],2);
            fltsum += 1/(p+c[j]);
        }//for j
        return  -1  * fltsum;
    }//shekel.

    /**
     * Search domain: −n ≤ xi ≤ n, i = 1, 2, . . . , n.
     * The global minima: x* =  (1, 2,…, n), f(x*) = 0.
     * b=0.5
     * @param x : vector of X
     * @return : Perm value
     */
    static public double Perm(double[] x)
    {
        int intvarnum = x.length;

        double b = 0.5;
        double s_out = 0.0;
        for (int k=0;k<intvarnum;k++)
        {
            double s_in = 0.0;
            for (int j = 0;j<intvarnum;j++)
            {
                s_in += (Math.pow(j+1,k+1)+b)*(Math.pow((x[j]/(j+1)),k+1)-1);
//                 System.out.printf("\n%f,%d,%d", s_in,j,k);
            }
            s_out += Math.pow(s_in,2);
        }//for k
        return s_out;
   }//perm
    /**
     * Search domain: −500 ≤ xi ≤ 500, i = 1, 2, . . . , n.
     * Number of local minima: several local minima.
     * global minimum
     *     f(x)=-n·418.9829; x(i)=420.9687, i=1:n.
     * 
     * @param x
     * @return Schwefel value
     */
    static public double Schwefel(double[] x)
    {
        int intvarnum = x.length;
        double s =0;
        for (int i=0;i<intvarnum;i++)
            s +=  -x[i]* Math.sin(Math.sqrt(Math.abs(x[i])));
        return  418.9829 * intvarnum + s;
    }//Schwefel.

    /**
     * Search domain: −5.12 ≤ xi ≤ 5.12, i = 1, 2, . . . , n.
     * Number of local minima: several local minima.
     * The global minima: x* =  (0, …, 0), f(x*) = 0.
     * @param x 
     * @return Rastrigin value
     */
    static public double Rastrigin(double[] x)
    {
        int intvarnum = x.length;
        int A = 10;
        double s = 0;
        for (int j = 0;j<intvarnum;j++)
            s += (Math.pow(x[j],2)-(A*Math.cos(2*Math.PI*x[j])));
        return  (A*intvarnum) + s;
    }//Rastrigin.

    /**
     * Search domain: 0 ≤ xi ≤ n, i = 1, 2, . . . , n.
     * The global minima: x* =  (1,2,3,4), f(x*) = 0 at b = (8,18,44,114).
     * @param x
     * @return
     */
    static public double PowerSum(double[] x)
    {
        int intvarnum = x.length;
        double b[] = {8,18,44,114};
        double s_out = 0;
        for (int k = 0;k<intvarnum;k++)
        {
            double s_in = 0;
            for (int j = 0;j<intvarnum;j++)
                s_in += Math.pow(x[j],k+1);
            s_out += Math.pow(s_in-b[k],2);
        }//for k
        return  s_out;
     }//Power Sum


    /**
     * Search domain: 0 ≤ xi ≤ π, i = 1, 2, . . . , n.
     * Number of local minima: several local minima.
     * The global minima:          at n=2, f(x*) = -1.8013.
     * at n=5, f(x*) = --4.687658.
     * at n=10, f(x*) = -9.66015.
     * 
     * @param x
     * @return Michalewics value
     */
    static public double Michalewics(double[] x)
    {
        int intvarnum = x.length;
        int m = 10;
        int s = 0;
        for (int i = 0;i<intvarnum;i++)
           s += Math.sin(x[i]) * Math.pow( Math.sin( (i+1) * Math.pow(x[i],2) / Math.PI) , (2*m) );

        return  -s;

    }//Michalewics

    /**
     * Search domain: −10 ≤ xi ≤ 10, i = 1, 2.
     * Number of local minima: no local minima except the global one.
     * The global minima: x* =  (0, 0), f(x*) = 0.
     * 
     * @param x1
     * @param x2
     * @return Matyas value
     */
    static public double Matyas(double x1, double x2)
    {
       return 0.26*(Math.pow(x1,2)+Math.pow(x2,2))-0.48*x1*x2;
    }//Matyas.

    /**
     * Search domain: −10 ≤ xi ≤ 10, i = 1, 2, . . . , n.
     * Number of local minima: several local minima.
     * The global minima: x* =  (1, …, 1), f(x*) = 0.
     *
     * @param x
     * @return Levy value
     */
    static public double Levy (double[] x)
    {
        int intvarnum = x.length;
        double z[] = new double[intvarnum];

        for (int i = 0;i<intvarnum;i++)
            z[i] = 1+(x[i]-1)/4;
        double s = Math.pow(Math.sin(Math.PI*z[0]),2);
        
        for (int i = 0;i<intvarnum-1;i++)
            s += Math.pow(z[i]-1,2) *(1+10*Math.pow(Math.sin(Math.PI*z[i]+1),2));

        return  s+Math.pow(z[intvarnum-1]-1,2)*(1+Math.pow(Math.sin(2*Math.PI*z[intvarnum-1]),2));
    }//Levy

    /**
     * Search domain: -5 ≤ xi ≤ 5, i = 1, 2.
     * Number of local minima: no local minimum except the global ones.
     * The global minima: x* =  (0.0898, -0.7126), (-0.0898, 0.7126),
     *          f(x*) = 0.
     * @param x1
     * @param x2
     * @return Hump value
     */
    static public double Hump (double x1, double x2)
    {
       return 1.0316285+4*Math.pow(x1,2)-2.1*Math.pow(x1,4)+Math.pow(x1,6)/3+x1*x2-4*Math.pow(x2,2)+4*Math.pow(x2,4);
    }//Hump .


    /**
     *
     * Search domain: −600 ≤ xi ≤ 600, i = 1, 2, . . . , n.
     * Number of local minima: several local minima.
     * The global minima: x* =  (0, …, 0), f(x*) = 0.
     * 
     * @param x
     * @return Griewank value
     */
    static public double Griewank (double[] x)
    {
        int intvarnum = x.length;
        double fr = 4000;
        double s = 0;
        double p = 1;
        for (int j = 1;j<intvarnum;j++)
            s += Math.pow(x[j],2);

        for (int j = 0;j<intvarnum;j++)
            p *= Math.cos(x[j]/Math.sqrt(j+1));
        return s/fr-p+1;
    }//Griewank

    /**
     * Search domain: −2 ≤ xi ≤ 2, i = 1, 2.
     * Number of local minima: several local minima.
     * The global minima: x* =  (0, -1), f(x*) = 3
     * 
     * @param x1
     * @param x2
     * @return Goldstein value
     */
    static public double Goldstein (double x1, double x2)
    {
        double a = 1+Math.pow(x1+x2+1,2)*(19-14*x1+3*Math.pow(x1,2)-14*x2+6*x1*x2+3*Math.pow(x2,2));
        double b = 30+Math.pow(2*x1-3*x2,2)*(18-32*x1+12*Math.pow(x1,2)+48*x2-36*x1*x2+27*Math.pow(x2,2));
        return a*b;
    }//Goldstein.

    /**
     * Search domain: −100 ≤ xi ≤ 100, i = 1, 2.
     * Number of local minima: several local minima.
     * The global minima: x* =  (π, π), f(x*) = - 1.
     * 
     * @param x1
     * @param x2
     * @return Easom value
     */
    static public double Easom  (double x1, double x2)
    {
        return -Math.cos(x1)*Math.cos(x2)*Math.exp(-Math.pow(x1-Math.PI,2)-Math.pow(x2-Math.PI,2));
    }//Easom.

    /**
     * Search domain: −10 ≤ xi ≤ 10, i = 1, 2, . . . , n.
     * The global minima: f(x*) = 0.
     * xi = 2^ -((z-1)/z), z=s^i-1
     * @param x
     * @return DixonPrice value
     */
    static public double DixonPrice (double[] x)
    {
        int intvarnum = x.length;
        double s1 = 0;
        for (int j = 1;j<intvarnum;j++)
        {
            s1 += j*Math.pow(2*Math.pow(x[j],2)-x[j-1],2);
        }//for j
        return s1+Math.pow(x[1]-1,2);

    }//Dixon and Price

    /**
     * Search domain: −10 ≤ xi ≤ 10, i = 1, . . . , 4.
     * The global minimum: x* =  (1, …, 1), f(x*) = 0.
     * 
     * @param x1
     * @param x2
     * @param x3
     * @param x4
     * @return Colville value
     */
    static public double Colville(double x1, double x2, double x3, double x4)
    {
        return 100*Math.pow(Math.pow(x1,2)-x2,2)+Math.pow(x1-1,2)+
               Math.pow(x3-1,2)+90*Math.pow(Math.pow(x3,2)-x4,2)+
               10.1*(Math.pow(x2-1,2)+Math.pow(x4-1,2))+19.8*(Math.pow(x2,-1))*(x4-1);
    }//Colville


    /**
     * 
     * Search domain: −4.5 ≤ xi ≤ 4.5, i = 1, 2.
     * The global minimum: x* =  (3, 0.5), f(x*) = 0.
     * 
     * @param x1
     * @param x2
     * @return Beale value
     */
    static public double Beale(double x1, double x2)
    {
        return Math.pow(1.5-x1*(1-x2),2)+Math.pow(2.25-x1*(1-Math.pow(x2,2)),2)+Math.pow(2.625-x1*(1-Math.pow(x2,3)),2);
    }//Beale.

    /**
     * Search domain: −5.12 ≤ xi ≤ 5.12, i = 1, 2, . . . , n.
     * Number of local minima: no local minimum except the global one.
     * The global minima: x* =  (0, …, 0), f(x*) = 0.
     * @param x
     * @return Sphere value
     */
    static public double Sphere(double[] x)
    {
        int intvarnum = x.length;
        double s = 0;
        for (int j=0;j<intvarnum;j++)
            s += Math.pow(x[j],2);
        return s;        
    }//Sphere

    /**
     *Search domain: 0 < xi < 1, i = 1, 2, . . . , 6.
     * r Number of local minima: 6 local minima.
     * r The global minima: x* =  (0.20169, 0.150011, 0.476874, 0.275332, 0.311652, 0.6573), f(x*) = - 3.32237.
     * @param x
     * @return Hartmann64 value
     */
    static public double Hartmann64(double[] x)
    {
        double a[][] =
                        {{10.0,3.0,17.0,3.5,1.7,8.0},
                        {0.05,10.0,17.0,0.1,8.0,14.0},
                        {3.0,3.5,1.7,10.0,17.0,8.0},
                        {17.0,8.0,0.05,10.0,0.1,14.0}};
        double c[] = {1.0,1.2,3.0,3.2};
        double p[][]={
                        {0.1312,0.1696,0.5569,0.0124,0.8283,0.5886},
                        {0.2329,0.4135,0.8307,0.3736,0.1004,0.9991},
                        {0.2348,0.1451,0.3522,0.2883,0.3047,0.6650},
                        {0.4047,0.8828,0.8732,0.5743,0.1091,0.0381}};
        double s = 0;
        for (int i=0;i<4;i++)
        {
           double sm=0;
           for (int j=0;j<6;j++)
              sm += a[i][j]*Math.pow(x[j]-p[i][j],2);

           s += c[i]*Math.exp(-sm);
        }//for
        return -s;
    }//Hartmann64


public  int OneMax(char[] aChrom)
{
    int intcnt=0;

    for (int i=0;i<aChrom.length;i++)
    {
        if (aChrom[i]=='1')
            intcnt ++;//=(i+1);

    }//for.
    return intcnt;
}//Fittness.

    public int HIFF(char[] aChrom)
    {
        int res = 0;
        int bb = 2; //building block length
        int sum=0;
        int lvl=2;
        int idx=0;
        int bbidx=0;

        int intmaxlvl = (int) (Math.log10(aChrom.length) / Math.log10(2));

        while (lvl<=intmaxlvl+1)
        {
            while (idx<aChrom.length)
            {
                while (bbidx<bb && aChrom[idx]==aChrom[idx+bbidx])
                {
                    bbidx++;
                }//while.
                if (bbidx==bb)
                    sum += (int) Math.pow(2,lvl-1);

                    bbidx=0;

                idx += bb;
            }//while
            idx = 0;
            bb *= 2;
            lvl++;
        }//while
        return sum;
    }//HIFF.

    public double Ugly(char[] aChrom)
    {
        double fltfit = 0;
        int i=0;
        while (i<aChrom.length-3)
        {
            if (aChrom[i]=='0')
                fltfit += 2;

            if (aChrom[i]=='1' && aChrom[i+1]=='1' && aChrom[i+2]=='1')
                fltfit += 3;

            i += 3;
        }//while i.
        return fltfit/aChrom.length;
    }//Ugly.

    private boolean isallone(char[] aChrom, int anidx, int alength)
    {
        int i;
        for(i=anidx;i<anidx+alength;i++)
        {
            if (aChrom[i]!='1')
                break;
        }//for i.
        return  (i==anidx+alength && aChrom[anidx+alength-1]!='1' || i==anidx+alength);
    }//isallone

    public int RoyalRoad(char[] aChrom, int aBuildingBlockLength)
    {
        int BBl = aBuildingBlockLength;
        int intfit = 0;
        int i = 0;
        int j = BBl;
        while (j <= aChrom.length)
        {
            int intlvlcnt = 0;
            while (i < aChrom.length)
            {
                if (isallone(aChrom, i, BBl))
                    intlvlcnt++;
                i += BBl;
            }//while i.
//            System.out.printf("\n%d*%d", BBl,intlvlcnt);
            intfit += BBl*intlvlcnt;
            BBl *= 2;
            i = 0;
            j =BBl;
        }//while j,
        return intfit;
    }//RoyalRoad.
}
