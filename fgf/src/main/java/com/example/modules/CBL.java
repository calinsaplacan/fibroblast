/**
 * 
 */
package com.example.modules;

/**
 * @author Calin
 *
 */
public class CBL {
	
	public int CBL;
	public SRC srcProtein;
	public FRS2 frsProtein;
	
	public Rewards compute;
	
	public CBL()
	{
		this.setCBL(1);
		this.setSrcProtein(new SRC());
		this.setFrsProtein(new FRS2());
	
	}
	// Spry55p + Cbl <-> Spry55p:Cbl [11]
	public int getBindingCBL_state(int cbl_state) {
		// TODO Auto-generated method stub
		if(cbl_state ==1 )
		{//[cbl_bind]
			if(this.getCBL()==1)
			{
				this.setCBL(0);
				cbl_state=this.getCBL();
				this.getCompute().computeBindings();
			}
			else System.out.println("Error binding CBL");
		}
		//[cbl_rel]
		else if(cbl_state==0)
		{
			if(this.getCBL()==0)
			{
				this.setCBL(1); 
				cbl_state=this.getCBL();
			}
			else System.out.println("Error relocating CBL");
		}
		return cbl_state;
	}

	public int getBindingCBL_FRS2_state(int cbl_state) {
		// TODO Auto-generated method stub
		//[cbl_bind_frs]
		if(cbl_state==1)
		{
			if(this.getCBL()==1)
			{
				this.setCBL(0); // spryp:src and src:frs2
				cbl_state=this.getCBL();
				this.getCompute().computeBindings();
			}
			else System.out.println("Error binding CBL_FRS2");
		}
		//[cbl_rel_frs]
		else if(cbl_state==0)
		{
			if(this.getCBL()==0)
			{
				this.setCBL(1);  // spryp:src and src:frs2
				cbl_state=this.getCBL();
			}
		}
		else System.out.println("Error relocating CBL_FRS2");
		return cbl_state;
	}
	public int getBindingCBL_SRC_state(int cbl_state) {
		// TODO Auto-generated method stub
		//[cbl_bind_frs]
		if(cbl_state==1)
		{
			if(this.getCBL()==1)
			{
				this.setCBL(0); // spryp:src and not src:frs2
				cbl_state=this.getCBL();
				this.getCompute().computeBindings();
			}
			else System.out.println("Error binding CBL_SRC");
		}
		//[cbl_rel_frs]
		else if(cbl_state==0)
		{
			if(this.getCBL()==0)
			{
				this.setCBL(1); // spryp:src and not src:frs2
				cbl_state=this.getCBL();
			}
		}
		else System.out.println("Error relocating CBL_SRC");
		return cbl_state;
	}

	// Spry55p -> Spry55 [13]
	public int getDephosphoSPRY(boolean cbl_state) {
		// TODO Auto-generated method stub
		int result = 0;
		if(cbl_state == true) 
		{
			this.setCBL(1);
			result = this.getCBL();
			
		}
		else System.out.println("Error at dephosphorilation SPRY from CBL");
		return result;
	}
	public int getCBL() {
		return CBL;
	}

	public void setCBL(int cBL) {
		CBL = cBL;
	}

	public SRC getSrcProtein() {
		return srcProtein;
	}

	public void setSrcProtein(SRC srcProtein) {
		this.srcProtein = srcProtein;
	}

	public FRS2 getFrsProtein() {
		return frsProtein;
	}

	public void setFrsProtein(FRS2 frsProtein) {
		this.frsProtein = frsProtein;
	}
	public Rewards getCompute() {
		return compute;
	}
	public void setCompute(Rewards compute) {
		this.compute = compute;
	}
}
