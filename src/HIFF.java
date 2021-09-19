/*

 
 This is an implementation based on the following paper:

        Real options approach to evaluating genetic algorithms
        Sunisa Rimcharoen a, Daricha Sutivong b,*, Prabhas Chongstitvatana a


 */

package benchmarks;

/**
 *
 * @author Farhad
 */
public class HIFF {
    public HIFF()
    {
        
    }//HIFF
    public int HIFF(char[] aStr)
    {
        int res = 0;
        int bb = 2; //building block length
        int sum=0;
        int lvl=2;
        int idx=0;
        int bbidx=0;

        int intmaxlvl = (int) (Math.log10(aStr.length) / Math.log10(2));

        while (lvl<=intmaxlvl+1)
        {
            while (idx<aStr.length)
            {
                while (bbidx<bb && aStr[idx]==aStr[idx+bbidx])
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

}//HIFF
