/**
 * 
 */
package com.example.modules;

/**
 * @author Calin
 *
 */
public class FGFR {

	public int FGFR; // FGFR unbound to FRS2
	public int degFGFR; // FGFR degraded
	public int FGFR_FGF; // FGF bound
	public int FGFR_PLC; // PLC bound
	
	//phosporilation of receptors
	public int Y653P;
	public int Y654P;
	public int Y766P;

	public int fgfr = degFGFR = 0; // fgfr not degraded or relocated
	
	//proteins
	public FGF fgfProtein;
	public FRS2 frs2Protein;
	public PLC plcProtein;
	
	public Rewards compute;
	
	public FGFR() {
		
		this.setFgfProtein(new FGF());
		this.setFrs2Protein(new FRS2());
		this.setPlcProtein(new PLC());
		
		this.setFGFR(1);
		this.setDegFGFR(0);
		
		
		this.setFGFR_FGF(0); // FGFR = 0 bounded
		this.setFGFR_PLC(0); // PLC = 0 bounded
		
		this.setY653P(0);
		this.setY654P(0);
		this.setY766P(0);
		

	}

	
	// fgfr+fgf <-> fgfr:fgf [1]
	public int getBindingFGFR_FGF_state(int fgf_state) {
		
		if (getFgfProtein().getBindingFGF_state(fgf_state) == 1) {
			if (getFgfr() == 0 && this.getFGFR_FGF()== 0) {
				this.setFGFR_FGF(1); //FGFR = 1 BOUNDED
				fgf_state = this.getFGFR_FGF();
				this.getCompute().computeBindings();

			} else
				System.out.println("Error relocating");
		} else if (getFgfProtein().getBindingFGF_state(fgf_state) == 0) {
			if (getFgfr() == 0 && this.getFGFR_FGF()== 1) {
				this.setFGFR_FGF(0); //FGFR = 0 UNBOUNDED
				fgf_state = this.getFGFR_FGF();

			} else
				System.out.println("Error binding");

		}
		return fgf_state;
	}
	// phosporilation of receptors
	public void phosphorilationOfReceptors()
	{
		if(getFgfr() == 0 && getFGFR_FGF() ==1 && getY653P() == 0) 
		{
			setY653P(1);
		}
		
		if(getFgfr() == 0 && getFGFR_FGF() ==1 && getY654P() == 0) 
		{
			setY654P(1);
		}
		if(getFgfr() == 0 && getFGFR_FGF() ==1 && getY766P() == 0)
		{
			setY766P(1);
		}
		
	}
	// fgfr+frs2 <-> fgfr:frs2 [4]
	public int getBindingFGFR_state(int fgfr_state)
	{ 
		if(fgfr_state==1)
		{
			
			if (getFgfr() == 0 && getFGFR() == 1)
			{
				//[fgfr_bind]
				this.setFGFR(0);
				fgfr_state = this.getFGFR();
				this.getCompute().computeBindings();
			}
			else System.out.println("Error binding FGFR_FRS2");
				
		}

		else if(fgfr_state==0)
		{
			if (getFgfr() == 0 && getFGFR() == 0)
				{
				//[fgfr_rel]
				this.setFGFR(1); 
				fgfr_state = this.getFGFR();
				}
			else System.out.println("Error relocating FGFR_FRS2");
		}

		return fgfr_state;
	}
	// PLC + FGFR766P <-> PLC:FGFR [9]
	public int getBindingFGFR_PLC_state(int plc_state)
	{
		if (getPlcProtein().getBindingPLC_state(plc_state) == 1) {
			if (getFgfr() == 0 && getY766P() == 1 && getFGFR_PLC() == 0) {
				this.setFGFR_PLC(1); //PLC BOUNDED
				plc_state = this.getFGFR_PLC();
				this.getCompute().computeBindings();

			} else
				System.out.println("Error PLC binding");
		} else if (getPlcProtein().getBindingPLC_state(plc_state) ==  0) {
			if (getFgfr() == 0  && getFGFR_PLC() == 1) {
				this.setFGFR_PLC(0); //PLC UNBOUNDED
				plc_state = this.getFGFR_PLC();

			} else
				System.out.println("Error relocating");

		}
		return plc_state;
	}
	// PLC:FGFR -> degFGFR [9]
	public void degradingFGFR_PLC_state()
	{
		if(getFgfr() == 0 && getFGFR_PLC() == 1)
		{
			this.setDegFGFR(1);
		}
	}
	public int getFGFR() {
		return FGFR;
	}
	public void setFGFR(int fGFR) {
		FGFR = fGFR;
	}

	
	public int getDegFGFR() {
		return degFGFR;
	}
	public void setDegFGFR(int degFGFR) {
		this.degFGFR = degFGFR;
	}
	public int getFGFR_FGF() {
		return FGFR_FGF;
	}
	public void setFGFR_FGF(int fGFR_FGF) {
		FGFR_FGF = fGFR_FGF;
	}
	
	public int getFGFR_PLC() {
		return FGFR_PLC;
	}
	public void setFGFR_PLC(int fGFR_PLC) {
		FGFR_PLC = fGFR_PLC;
	}
	public int getY653P() {
		return Y653P;
	}
	public void setY653P(int y653p) {
		Y653P = y653p;
	}
	public int getY654P() {
		return Y654P;
	}
	public void setY654P(int y654p) {
		Y654P = y654p;
	}
	public int getY766P() {
		return Y766P;
	}
	public void setY766P(int y766p) {
		Y766P = y766p;
	}
	
	public int getFgfr() {
		return fgfr;
	}
	public void setFgfr(int fgfr) {
		this.fgfr = fgfr;
	}


	public FGF getFgfProtein() {
		return fgfProtein;
	}


	public void setFgfProtein(FGF fgfProtein) {
		this.fgfProtein = fgfProtein;
	}


	public FRS2 getFrs2Protein() {
		return frs2Protein;
	}


	public void setFrs2Protein(FRS2 frs2Protein) {
		this.frs2Protein = frs2Protein;
	}


	public PLC getPlcProtein() {
		return plcProtein;
	}


	public void setPlcProtein(PLC plcProtein) {
		this.plcProtein = plcProtein;
	}


	public Rewards getCompute() {
		return compute;
	}


	public void setCompute(Rewards compute) {
		this.compute = compute;
	}
	
	
}
