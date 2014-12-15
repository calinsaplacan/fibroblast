/**
 * 
 */
package com.example.modules;

/**
 * @author Calin
 *
 */
public class GRB {
	public int GRB;

	public FRS2 frs2Protein;
	public SRC srcProtein;
	public SPRY spryProtein;
	public SOS sosProtein;
	
	public Rewards compute;
	
	
	public GRB () {
		this.setGRB(1);
		this.setFrs2Protein(new FRS2());
		this.setSrcProtein(new SRC());
		this.setSpryProtein(new SPRY());
		this.setSosProtein(new SOS());
	}
	
	// GRB2 + FRS2306P <-> GRB2:FRS2 [7]
	public int getBindingGRB_state(int frs2_state) {
	
	if(frs2_state==1)
	{
		//grb_bind
		if(this.getGRB()>0)
		{
			this.setGRB(0);
			frs2_state=getGRB();
			this.getCompute().computeBindings();
		}
		 else System.out.println("Error binding GRB");
	}
	else if(frs2_state==0)
	{
		//grb_rel
		if(this.getGRB()==0)
		{
			this.setGRB(this.getFrs2Protein().getFRS2_GRB());
			frs2_state=getGRB();
		}
		 else System.out.println("Error relocating GRB");
	}
		return frs2_state;
	}

	// Grb2 + Sos <-> Grb2:Sos [14]
	public int getBindingGRB_SOS_state(int sos_state){
		//sos_bind
		if(this.getSosProtein().getBindingSOS_state(sos_state)==1)
		{
			if(getGRB()==1) 
			{
				this.setGRB(0);
				sos_state = this.getGRB();
				this.getCompute().computeBindings();
			}
			 else System.out.println("Error binding GRB SOS");
		}
		//sos_rel
		else if(this.getSosProtein().getBindingSOS_state(sos_state)==0)
		{
			if(getGRB()==2) 
			{
				this.setGRB(1);
				sos_state = this.getGRB();
			}
			 else System.out.println("Error relocating GRB SOS");
		}
		
		return sos_state;
	}

	public int getBindingGRB_FRS_2_state(int frs_state) {
	
		//grb_bind_frs
		if(frs_state==1)
		{
			if(getGRB()>0)
			{
				setGRB(0);// spryp:src and src:frs2
				frs_state = getGRB();
				this.getCompute().computeBindings();
			}
		
		else System.out.println("Error binding GRB FRS");
		}
		//grb_rel_frs
		else if(frs_state==0)
		{
			if(getGRB()==0)
			{
				setGRB((this.getFrs2Protein().getFRS2_SRC()<7)?1:2);// spryp:src and src:frs2
				frs_state = getGRB();
			}
			else System.out.println("Error relocating GRB FRS");
		}
		
		
		return frs_state;
	}
	// Spry55p + Grb2 <-> Spry55p:Grb2 [11]
	public int getBindingGRB_SRC_state(int src_state) {
		
			//src_bind_frs
			if(src_state==1)
			{
				if(getGRB()>0)
				{
					setGRB(0);// spryp:src and not src:frs2
					src_state = getGRB();
					this.getCompute().computeBindings();
				}
			
			else System.out.println("Error binding GRB FRS");
			}
			//grb_rel_frs
			else if(src_state==0)
			{
				if(getGRB()==0)
				{
					setGRB((this.getSrcProtein().getSRC()<7)?1:2);// spryp:src and not src:frs2
					src_state = getGRB();
				}
				else System.out.println("Error relocating GRB FRS");
			}
			
			
			return src_state;
		}	
	// Spry55p + Grb2 <-> Spry55p:Grb2 [11]
	public int getBindingGRB_SPRY_state(int spry_state) {

		//spry_bind_frs
		if(spry_state==1)
		{
			if(getGRB()>0)
			{
				setGRB(0);// spryp free
				spry_state = getGRB();
				this.getCompute().computeBindings();
			}
		
		else System.out.println("Error binding GRB SPRY");
		}
		//spry_rel_frs
		else if(spry_state==0)
		{
			if(getGRB()==0)
			{
				setGRB((this.getSpryProtein().getSPRY()<6)?1:2); // spryp free
				spry_state = getGRB();
			}
			else System.out.println("Error relocating GRB SPRY");
		}
		
		return spry_state;
	}
	
	// Spry55p -> Spry55 [13]
	public int getDephosphoSPRY(boolean spry_dephospho) {
		// TODO Auto-generated method stub
		int result = 0;
		if(spry_dephospho == true) 
		{
			this.setGRB((this.getFrs2Protein().getFRS2_SRC()<7)?1:2);
			result = this.getGRB();
			
		}
		else System.out.println("Error at dephosphorilation SPRY from CBL");
		return result;
	}
	

	public int getGRB() {
		return GRB;
	}

	public void setGRB(int gRB) {
		GRB = gRB;
	}

	public FRS2 getFrs2Protein() {
		return frs2Protein;
	}

	public void setFrs2Protein(FRS2 frs2Protein) {
		this.frs2Protein = frs2Protein;
	}

	public SRC getSrcProtein() {
		return srcProtein;
	}

	public void setSrcProtein(SRC srcProtein) {
		this.srcProtein = srcProtein;
	}

	public SPRY getSpryProtein() {
		return spryProtein;
	}

	public void setSpryProtein(SPRY spryProtein) {
		this.spryProtein = spryProtein;
	}

	public SOS getSosProtein() {
		return sosProtein;
	}

	public void setSosProtein(SOS sosProtein) {
		this.sosProtein = sosProtein;
	}

	public Rewards getCompute() {
		return compute;
	}

	public void setCompute(Rewards compute) {
		this.compute = compute;
	}

	

	

}
