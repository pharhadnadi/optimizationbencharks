/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package benchmarks;

/**
 *
 * @author nadi
 */
import java.io.BufferedReader;
import java.io.IOException;


public class FileReader {
	public  BufferedReader brfilehandle;
	public String strFileName;

public FileReader (String aFileName)
{
    try
    {
        strFileName = aFileName;
        brfilehandle = new BufferedReader(new java.io.FileReader(strFileName));
    }//try
    catch (IOException e)
    {
    }//try.
}//FileReader.
public String ReadlnFile()
{
	String strres;
	try
	{
        do {
            strres = brfilehandle.readLine();

        } while (strres!=null && strres.charAt(0)=='/'  );

	}//try.
	catch (IOException e)
    {
		strres = null;
	}//try.
	return strres;
}//ReadlnFile.

public int Close()
{
    return 1;
}//close.
	
}//FileReader.