/**
 * 
 */
package com.example.engine;

import com.example.modules.*;

/**
 * @author Calin
 *
 */
public class Engine {

	// proteins
	public static FGFR fgfrProtein;
	public static FRS2 frs2Protein;
	public static PLC plcProtein;
	public static SRC srcProtein;
	public static SPRY spryProtein;
	public static SHP shpProtein;
	public static GRB grbProtein;
	public static CBL cblProtein;
	public static SOS sosProtein;
	public static FGF fgfProtein;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// proteins initialization

		fgfrProtein = new FGFR();
		plcProtein = new PLC();
		srcProtein = new SRC();
		grbProtein = new GRB();
		spryProtein = new SPRY();
		shpProtein = new SHP();
		cblProtein = new CBL();
		sosProtein = new SOS();

	}

}
