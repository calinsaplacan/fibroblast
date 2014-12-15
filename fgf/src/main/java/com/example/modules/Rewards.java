/**
 * 
 */
package com.example.modules;

/**
 * @author Calin
 *
 */
public class Rewards {

	public GRB grbProtein;
	public FRS2 frsProtein;
	public FGFR fgfrProtein;
	public int bindCounter=0;
	public int timeCounter=0;
	
	public Rewards()
	{
		
	}
	public void computeBindings()
	{
		
		//grb_bind
		if(this.getGrbProtein().getBindingGRB_state(1)==1)
		{
			
			if(this.getFgfrProtein().getDegFGFR()==0 && this.getFrsProtein().getRelocFRS2()==0 
					&& this.getFrsProtein().getDegFRS2()==0)
			{
			 this.setBindCounter(getBindCounter()+1);
			 System.out.println("Actual value of BINDings "+ this.getBindCounter());
			}
		}
		else System.out.println("No Bind computed");
	}
	
	public void computeTime()
	{
		if(this.getFrsProtein().getFRS2_GRB()>0 && this.getFrsProtein().getRelocFRS2()==0
				&& this.getFrsProtein().getDegFRS2()==0)
		{
			this.setTimeCounter(getTimeCounter()+1);
			 System.out.println("Actual value of time bindings "+ this.getTimeCounter());
		}
		else System.out.println("No time bind computed");
	}
	
	public GRB getGrbProtein() {
		return grbProtein;
	}


	public void setGrbProtein(GRB grbProtein) {
		this.grbProtein = grbProtein;
	}


	public FRS2 getFrsProtein() {
		return frsProtein;
	}


	public void setFrsProtein(FRS2 frsProtein) {
		this.frsProtein = frsProtein;
	}


	public FGFR getFgfrProtein() {
		return fgfrProtein;
	}


	public void setFgfrProtein(FGFR fgfrProtein) {
		this.fgfrProtein = fgfrProtein;
	}


	public int getBindCounter() {
		return bindCounter;
	}


	public void setBindCounter(int counter) {
		this.bindCounter = counter;
	}

	public int getTimeCounter() {
		return timeCounter;
	}

	public void setTimeCounter(int timeCounter) {
		this.timeCounter = timeCounter;
	}

}
