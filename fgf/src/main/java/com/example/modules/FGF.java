/**
 * 
 */
package com.example.modules;


/**
 * @author Calin
 *
 */
public class FGF {

	public int FGF;
	public Rewards compute;

	public FGF() {
		setFGF(1); //FGF unbounded
	}
	// fgfr+fgf <-> fgfr:fgf [1]
	public int getBindingFGF_state(int fgf_state) {
		if(fgf_state == 1 )
		{
			// [fgf_bind] 
			 if (this.getFGF() == 1)
			{
				setFGF(0); //FGF = 0 BOUNDED
				fgf_state = this.getFGF();
				this.getCompute().computeBindings();
			
			}
			 else System.out.println("Error binding FGF");
		}
		else if(fgf_state == 0)
				
			{ 
			// [fgf_rel]
			if (this.getFGF() == 0)
				{
				setFGF(1); // FGF = 1 UNBOUNDED/RELOCATED
				fgf_state = this.getFGF();
				}
			else System.out.println("Error relocating FGF");
			}
		
		//return 0 if bounded
		//return 1 if unbounded
	
		return fgf_state;
	}

	public int getFGF() {
		return FGF;
	}

	public void setFGF(int fGF) {
		FGF = fGF;
	}
	public Rewards getCompute() {
		return compute;
	}
	public void setCompute(Rewards compute) {
		this.compute = compute;
	}

	
}