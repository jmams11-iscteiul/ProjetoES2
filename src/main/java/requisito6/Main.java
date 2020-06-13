package requisito6;

import requisito6.FileCompare;
/**
 * Main class, it runs the program.
 *
 */
public class Main
{	/**
	 *  This is the main method, to run this application the evodiffconfig.ini must be in the same directory.
	 * @param args None
	 */
	public static void main( String args[] )
	{
		FileCompare fileCompare = new FileCompare();
		fileCompare.computeResults();
	}
}

