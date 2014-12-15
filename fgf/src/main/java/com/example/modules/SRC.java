package com.example.modules;

/**
 * @author Calin
 *
 */
public class SRC {
	public int SRC;
	
	//proteins
	public SPRY spryProtein; 
	public GRB grbProtein;
	public SOS sosProtein;
	public FRS2 frs2Protein;
	public CBL cblProtein;
	public Rewards compute;
	
		// 0 - bound elsewhere
		// 1 - SRC
		// 2 - SRC_SPRY
		// 3 - SRC_SPRYP
		// 4 - SRC_SPRYP_CBL
		// 5 - SRC_SPRYP_GRB
		// 6 - SRC_SPRYP_GRB_CBL
		// 7 - SRC_SPRYP_GRB_SOS
		// 8 - SRC_SPRYP_GRB_SOS_CBL
	
	public SRC () {
		this.setSRC(1); 
		this.setGrbProtein(new GRB());
		this.setSpryProtein(new SPRY());
		this.setSosProtein(new SOS());
		this.setFrs2Protein(new FRS2());
		this.setCblProtein(new CBL());
	}


	public int getBindingSRC_state(int src_state) {
		if(src_state == 1 )
		{
			// [src_bind]
			 if (this.getSRC() > 0)
			{
				setSRC(0);
				src_state = this.getSRC();
				this.getCompute().computeBindings();
			}
			 else System.out.println("Error binding at SRC");
		}
		else if(src_state == 0)
			{ 
			// [src_rel]
			if (this.getSRC() == 0)
				{
				setSRC(this.getFrs2Protein().getFRS2_SRC());
				src_state = this.getSRC();
				}
			else System.out.println("Error relocating at SRC");
			}
		return src_state;
	}

	
	// Spry + Src -> Spry55:Src/Spryp + Src -> Spry55p:Src [11]
	//[spry_bind] SRC=1 -> 1      : (SRC'=SPRY+1);
	
	public int getBindingSRC_SPRY_state(int src_state)
	{
		//spry_bind
		if(getSpryProtein().getBindingSPRY_state(src_state)==1)
		{
			if(getSRC()==1)
			{
				setSRC(this.getSpryProtein().getSPRY()+1);
				src_state = this.getSRC();
				this.getCompute().computeBindings();
			}
			System.out.println("Error binding at SPRY_SRC");
		}
		// Spry + Src <- Spry55:Src [11]
		//spry_rel
		else if(getSpryProtein().getBindingSPRY_state(src_state)==0)
		{
			if(getSRC()==2)
			{
				// Spry + Src <- Spry55:Src [11]
				setSRC(1);
				src_state = this.getSRC();
			}
			else if(getSRC()>2)
			{
				// Spryp + Src <- Spry55p:Src [11]
				setSRC(1);
				src_state = this.getSRC();
			}
		}
		return src_state;
	}
		// Spry55:Src -> Spry55p:Src [11]
		
	public void phosphorilationSpry()
	{
		if(this.getSRC()==2)
		{
			this.setSRC(3);
		}
	}
	
	public int getBindingSRC_CBL_state(int cbl_state)
	{
		if(this.getCblProtein().getBindingCBL_SRC_state(cbl_state)==1)
		{
			//cbl_bind
			if(this.getSRC()==3 || this.getSRC()==5 ||this.getSRC()==7)
			{
				this.setSRC(this.getSRC()+1);
				cbl_state = this.getSRC();
				this.getCompute().computeBindings();
			}
			else System.out.println("Error binding at SRC_CBL");
			
		}
		//cbl_rel
		else if(this.getCblProtein().getBindingCBL_SRC_state(cbl_state)==0)
		{
			if(this.getSRC()==4 || this.getSRC()==6 ||this.getSRC()==8)
			{
				this.setSRC(this.getSRC()-1);
				cbl_state = this.getSRC();
			}
			else System.out.println("Error relocating at SRC_CBL");
		}
		
		return cbl_state;
	}
	// Spry55p + Grb2 <-> Spry55p:Grb2 [11]
	
	public int getBindingSRC_GRB_state(int grb_state)
	{
		
		if(this.getGrbProtein().getBindingGRB_SRC_state(grb_state)==1)
		{
			//src_bind
			if(this.getSRC()==3 || this.getSRC()==4 )
			{
				this.setSRC(this.getSRC()+2*this.getGrbProtein().getGRB());
				grb_state = this.getSRC();
				this.getCompute().computeBindings();
			}
			else System.out.println("Error binding at SRC_GRB");
		}
		else if(this.getGrbProtein().getBindingGRB_SRC_state(grb_state)==0)
		{
			//src_rel
			if(this.getSRC()==5 || this.getSRC()==6 )
			{
				this.setSRC(this.getSRC()-2);
				grb_state = this.getSRC();
			}
			else if(this.getSRC()==7 || this.getSRC()==8 )
			{
				this.setSRC(this.getSRC()-4);
				grb_state = this.getSRC();
			}
			else System.out.println("Error relocating at SRC_GRB");
		}
		return grb_state;
		
	}
	
	public int getBindingSRC_SOS_state(int sos_state)
	{
		if(this.getSosProtein().getBindingSOS_SRC_state(sos_state)==1)
		{
		//sos_bind
			if(this.getSRC()==5 || this.getSRC()==6 )
			{
				this.setSRC(this.getSRC()+2);
				sos_state = this.getSRC();
				this.getCompute().computeBindings();
			}
			else System.out.println("Error binding at SRC_SOS");
		}
		//sos_rel
		else if(this.getSosProtein().getBindingSOS_SRC_state(sos_state)==0)
		{
			if(this.getSRC()==7 || this.getSRC()==8 )
			{
				this.setSRC(this.getSRC()-2);
				sos_state = this.getSRC();
			}
			else System.out.println("Error relocating at SRC_SOS");
		}
		return sos_state;
	}

		
	public int getSRC() {
		return SRC;
	}


	public void setSRC(int sRC) {
		SRC = sRC;
	}



	public SPRY getSpryProtein() {
		return spryProtein;
	}


	public void setSpryProtein(SPRY spryProtein) {
		this.spryProtein = spryProtein;
	}


	public GRB getGrbProtein() {
		return grbProtein;
	}


	public void setGrbProtein(GRB grbProtein) {
		this.grbProtein = grbProtein;
	}


	public SOS getSosProtein() {
		return sosProtein;
	}


	public void setSosProtein(SOS sosProtein) {
		this.sosProtein = sosProtein;
	}


	public FRS2 getFrs2Protein() {
		return frs2Protein;
	}


	public void setFrs2Protein(FRS2 frs2Protein) {
		this.frs2Protein = frs2Protein;
	}


	public CBL getCblProtein() {
		return cblProtein;
	}


	public void setCblProtein(CBL cblProtein) {
		this.cblProtein = cblProtein;
	}


	public Rewards getCompute() {
		return compute;
	}


	public void setCompute(Rewards compute) {
		this.compute = compute;
	}
	
}
