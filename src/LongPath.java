/*
 * Re-implemented based on the following paper on 30th November 2010 By Farhad Nadi
 * Long  Path  Problems 
 * Jeffrey Horn 1, David  E.  Goldberg 1,  and  Kalyanmoy Deb ~
 * http://www.springerlink.com/content/7738173012k57861/fulltext.pdf
 * 
 * 
 * Are Long Path Problems Hard for Genetic Algorithms?
 * Christian HShn and Colin Reeves
 * http://www.springerlink.com/content/f602481m70042537/fulltext.pdf
 *
 * AND
 *
 * Long Path Problems (1994)
 * by Edited Y. Davidor ,  H. -p. Schwefel ,  R. Manner ,  Jeffrey Horn ,  Jeffrey Horn ,  David E. Goldberg ,  David E. Goldberg ,  Kalyanmoy Deb ,  Kalyanmoy Deb
 * Proceedings of the 3 rd Conference on Parallel Problems Solving from Nature
 * http://citeseerx.ist.psu.edu/viewdoc/download;jsessionid=170A09320E6E1F06162D51BF6C0AEF08?doi=10.1.1.51.9758&rep=rep1&type=pdf
 *
 */
package benchmarks;

/**
 *
 * @author Farhad
 */
public class LongPath {

    private static int intdimension;
    private static int intpathsiz;
    private static String[] Strpath;
    private static boolean  bolnotmatch = false;

    private static int unitation(String aChrom) {
        int intcnt = 0;

        for (int i = 0; i < aChrom.length(); i++) {
            if (aChrom.charAt(i) == '1') {
                intcnt++;
            }
        }//for.

        return intcnt;
    }//Fittness.

    public static int fitness(String anode) {
        int res = 0;
        int pth = position(anode);
        if (pth < 0) {
            res = anode.length() - unitation(anode);
        } else {
            res = pth + anode.length();
        }

        return res;
    }//fitness.

    private static int position(String anode) {

        int res = 5 * (int) Math.pow(2, anode.length());
        if (anode.length() == 1) {
            bolnotmatch = false;
            if (anode.compareTo("0") == 0) {
                res = 0;//(str  ==  "0")  RETURN  0;  //  First  step  on  path.  
            }
            if (anode.compareTo("1") == 0) {
                res = 1; //(str  ==  "1")  RETURN  1;  ]//  Second step  on  path.              
            }
        }//0 or 1
        else {
            bolnotmatch = false;
            int l = anode.length();
            if (anode.startsWith("00"))//(str  == "00--rest-of-string")  //  On  1st  half of path.  Recur.              
            {
                res = position(anode.substring(2, l));
            } else if (anode.startsWith("11"))//(str  ==  "ll--rest-of-string")  //  On  2nd  half of path.  Recur.  
            {
                res = 3 * (int) Math.pow(2, Math.floor((l - 1) / 2)) - 2 - position(anode.substring(2, l)); //RETURN  3*2"Floor[(Length[str]-l)/2]-  2- Pat hPosition[rest-of-st ring]; 
            } else if ((l == 3 && anode.compareTo("101") == 0) || (anode.startsWith("1011") && anode.indexOf('1', 4) == -1))//(str  ==  "101"  OR"  1011--all-zeroes")/*  At  bridge  pt.  (halfway)  
            {
                res = 3 * (int) Math.pow(2, Math.floor((l - 1) / 2) - 1) - 1; //RETURN  3*2"Floor[(Length[str]-l)/2]-  2- Pat hPosition[rest-of-st ring]; 
            }
            else
                bolnotmatch = true;
        }
        
        if (bolnotmatch)
            res = -1;
        return res;
    }//fitness.

    public static int RetPathSize(int l) {
        return 3 * ((int) (Math.pow(2, (int) Math.floor((l - 1) / 2)))) - 1;
    }

    public LongPath(int aDimension) {
        intdimension = aDimension;
        intpathsiz = RetPathSize(aDimension);
//        Strpath = new String[intpathsiz];
//          System.out.printf("\nsss%d",intpathsiz);
//        int as = Constructlongpath(Strpath, intdimension);
//        for (int i = 0; i < as; i++) {
//            System.out.printf("\n%s", Strpath[i]);
//        }

    }
// this is just for display purposes // it take lots of memory
    private static int Constructlongpath(String[] aPath, int D) {
        int intRes;
        if (D == 1) {
            aPath[0] = "0";
            aPath[1] = "1";
            intRes = 2;
        }//if
        else {
            int siz;
            siz = 2 * Constructlongpath(aPath, D - 2) + 1;
            int lsiz = RetPathSize(D - 2);
            aPath[lsiz] = "01" + aPath[lsiz - 1];
            for (int i = 0; i < lsiz; i++) {
                aPath[2 * lsiz - i] = "11" + aPath[i];
                aPath[i] = "00" + aPath[i];
            }//for i.
            intRes = siz;
        }//else
        return intRes;
    }//Constructlongpath
}
