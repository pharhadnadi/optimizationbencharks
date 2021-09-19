package benchmarks;

import java.io.*;

/**
 *
 * @author Farhad
 */
import benchmarks.TypDecomposableProblem;

public class DecomposableProblem {

    public static BufferedReader brfilehandle;
    public static String strFileName;
    public static double maxF;
    public static TypDecomposableProblem DP;

    public DecomposableProblem(){
        DP = new TypDecomposableProblem();
    }

    static public void FileReader(String aFileName) {

        try {
            FileInputStream fstream = new FileInputStream(aFileName);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String strLine="";
            String buff;

            while ((buff = br.readLine()) != null) {

                if (strLine.length()>0)
                    strLine = strLine.concat(" ").concat(buff);
                else
                    strLine = strLine.concat(buff);

            }

                String delims = "[ ]+";
                String[] tokens = strLine.split(delims);

                try {
                    DP.n = Integer.valueOf(tokens[0]);
                    DP.k = Integer.valueOf(tokens[1]);
                    DP.overlap = Integer.valueOf(tokens[2]);

                    int num = (int) Math.pow(2, DP.k);

                    int m = (DP.n - DP.k) / (DP.k - DP.overlap) + 1;

                    DP.c = new double[m][num];
                    for (int i = 0; i < m; i++) {
                        for (int j = 0; j < num; j++) {
                            DP.c[i][j] = Double.valueOf(tokens[3 + (i * num) + j]);
                        }//for j.
                    }//for i.

                    DP.maxF = Double.valueOf(tokens[(m * num) + 3]);
                    DP.pi = new int[DP.n];

                    for (int i = 0; i < DP.n; i++) {
                        DP.pi[i] = Integer.valueOf(tokens[m * num + 3 + 1 + i]);
                    }//for i

                } catch (Exception e) {
                }//catch.

        } catch (IOException e) {
            System.out.println("IO Exception =: " + e);
        }

    }//FileReader.

    static public double evaluateDecomposableProblem(char[] x) {
        int i, j;
        int num = 0;
        double f = 0;
        int nk = DP.n - DP.k;
        int i_step = DP.k - DP.overlap;
        for (i = 0; i <= nk; i += i_step) {
            num = 0;
            for (j = 0; j < DP.k; j++) {
                num = (2 * num) + (Integer.valueOf(x[DP.pi[i + j]])-48);
            }
//            System.out.printf("\ni/i_step:%d, num:%d ", (i / i_step),num);
            f += DP.c[(int)(i / i_step)][num];
        }
        return f;
    }//evaluateDecomposableProblem.

    static public int isOptimalDecomposableProblem(char[] x) {
        int optimal = 1;
        double f = evaluateDecomposableProblem(x);

        if (f < DP.maxF - 1E-07) {
            optimal = 0;
        }
        return optimal;


    }//isOptimalDecomposableProblem
}//decomposable_problem_solver.

