package benchmarks;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Farhad
 */
public class TypDecomposableProblem {

    public static int n;

    /*! size of each subproblem (order of decomposition) */
    public static int k;

    /*! amount of overlap, should be from 0 to k/2 */
    public static int overlap;

    /*! internal representation of the decomposable problem */
    public static double[][] c;

    /*! internal representation of the decomposable problem */
    public static int pi[];
    public static double maxF;
}