/**
 * 
 */
package com.example.modules;

/**
 * @author Calin
 *
 */
public class SOS {
	public int SOS;
	
	public FRS2 frs2Protein;
	public SRC srcProtein;
	public SPRY spryProtein;

	public Rewards compute;
	
	public SOS () {
		this.setSOS(1);
		
		this.setFrs2Protein(new FRS2());
		this.setSrcProtein(new SRC());
		this.setSpryProtein(new SPRY());
	
	}

	public int getBindingSOS_state(int sos_state) {
		//sos_bind
		if(sos_state==1)
		{
			if(getSOS()==1)
			{
				setSOS(0); // grb2 free
				sos_state = getSOS();
				this.getCompute().computeBindings();
			}
			else System.out.println("Error binding SOS");
		}
		//sos_rel
		else if(sos_state==0)
		{
			if(getSOS()==0)
			{
				setSOS(1); // grb2 free
				sos_state = getSOS();
			}
			else System.out.println("Error binding SOS");
		}
		return 0;
	}
	public int getBindingSOS_SRC_state(int src_state) {

		//sos_bind
		if(src_state==1)
		{
			if(getSOS()==1)
			{
				setSOS(0); // grb2 free
				src_state = getSOS();
				this.getCompute().computeBindings();
			}
			else System.out.println("Error binding SRC");
		}
		//sos_rel
		else if(src_state==0)
		{
			if(getSOS()==0)
			{
				setSOS(1); // grb2 free
				src_state = getSOS();
				this.getCompute().computeBindings();
			}
			else System.out.println("Error relocating SRC");
		}
		return src_state;
	}
	

	public int getBindingSOS_FRS_state(int frs_state) {
	//SOS_bind_FRS
	if(frs_state==1)
	{
		if(getSOS()==1)
		{
			setSOS(0); // grb2:spryp and spryp:src and src:frs2
			frs_state = getSOS();
			this.getCompute().computeBindings();
		}
		else System.out.println("Error binding frs");
	}
	//SOS_rel_FRS
	else if(frs_state==0)
	{
		if(getSOS()==0)
		{
			setSOS(1); // grb2:spryp and spryp:src and src:frs2
			frs_state = getSOS();
		}
		else System.out.println("Error relocating frs");
	}
	return frs_state;
}
	public int getBindingSOS_SPRY_state(int sos_state) {
	
	//SOS_bind_SPRY
	if(sos_state==1)
	{
		if(getSOS()==1)
		{
			setSOS(0); // grb2:spryp and spryp:src and src:frs2
			sos_state = getSOS();
			this.getCompute().computeBindings();
		}
		else System.out.println("Error binding frs");
	}
	//SOS_rel_SPRY
	else if(sos_state==0)
	{
		if(getSOS()==0)
		{
			setSOS(1); // grb2:spryp and spryp:src and not src:frs2
			sos_state = getSOS();
		}
		else System.out.println("Error relocating frs");
	}
	return sos_state;
}
	public int getSOS() {
		return SOS;
	}

	public void setSOS(int sOS) {
		SOS = sOS;
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

	public Rewards getCompute() {
		return compute;
	}

	public void setCompute(Rewards compute) {
		this.compute = compute;
	}
	
}
