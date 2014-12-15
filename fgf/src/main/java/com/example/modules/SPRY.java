package com.example.modules;

public class SPRY {

	public int SPRY;
	// 0 - bound elsewhere or not appeared
		// 1 SPRY
		// 2 SPRYP
		// 3 SPRYP_CBL
		// 4 SPRYP_GRB
		// 5 SPRYP_GRB_CBL
		// 6 SPRYP_GRB_SOS
		// 7 SPRYP_GRB_SOS_CBL
	public int app;
	public GRB grbProtein;
	public CBL cblProtein;
	public SOS sosProtein;
	public SRC srcProtein;
	public FRS2 frsProtein;

	public Rewards compute;
	public SPRY ()
	{
		this.setApp(0); // has spry entered the system yet
		this.setSPRY(0);
		
		this.setCblProtein(new CBL());
		this.setGrbProtein(new GRB());
		this.setSosProtein(new SOS());
		this.setSrcProtein(new SRC());
		this.setFrsProtein(new FRS2());
		
	}
	// Spry + Src <-> Spry55:Src/Spryp + Src -> Spry55p:Src [11]
	public int getBindingSPRY_state(int spry_state) {
			if(spry_state == 1 )
			{
				// [spry_bind] 
				 if (this.getSPRY() > 0)
				{
					setSPRY(0); //src free
					spry_state = this.getSPRY();
					this.getCompute().computeBindings();
				}
				 else System.out.println("Error binding SPRY");
			}
			else if(spry_state == 0)
					
				{ 
				// [spry_rel]
				if (this.getSPRY() == 0 && this.getSrcProtein().getSRC() > 0)
					{
					setSPRY(this.getSrcProtein().getSRC()-1); //src free
					spry_state = this.getSPRY();

					}
				else System.out.println("Error relocating SPRY");
				}
			return spry_state;
	}
	
	public int getBindingSPRY_FRS_state(int spry_state) {
		if(spry_state == 1 )
		{
			// [spry_bind_frs] 
			 if (this.getSPRY() > 0)
			{
				setSPRY(0); //src free
				spry_state = this.getSPRY();
				this.getCompute().computeBindings();
			}
			 else System.out.println("Error binding SPRY_FRS");
		}
		else if(spry_state == 0)
				
			{ 
			// [spry_rel_frs]
			if (this.getSPRY() == 0 && this.getFrsProtein().getFRS2_SRC() > 0)
				{
				setSPRY(this.getFrsProtein().getFRS2_SRC()-1); //src free
				spry_state = this.getSPRY();

				}
			else System.out.println("Error relocating SPRY_FRS");
			}
		return spry_state;
	}
	// -> Spry [10]
	public void appSpry()
	{
		if(getSPRY()==0 && getApp()==0) 
		{
			setSPRY(1);
			setApp(1);
			this.getCompute().computeTime();
		}
	}
	
	// Spry55p + Cbl <-> Spry55p:Cbl [11]
	public int getBindingSPRY_CBL_state(int cbl_state)
	{
		//cbl_bind
		if(this.getCblProtein().getBindingCBL_state(cbl_state)==1)
		{
			if(this.getSPRY()==2 || this.getSPRY()==4 || this.getSPRY()==6)
			{
				
				this.setSPRY(this.getSPRY()+1);
				cbl_state = getSPRY();
				this.getCompute().computeBindings();
			}
			else System.out.println("Error binding SPRY_CBL");
		}
		//cbl_rel
		else if(this.getCblProtein().getBindingCBL_state(cbl_state)==0)
		{
			if(this.getSPRY()==3 || this.getSPRY()==5 || this.getSPRY()==7)
			{
				this.setSPRY(this.getSPRY()-1);
				cbl_state = getSPRY();
			}
			else System.out.println("Error relocating SPRY_CBL");
			}
		return cbl_state;
		}
	// Spry55p + Grb2 <-> Spry55p:Grb2 [11]
	public int getBindingSPRY_GRB_state(int grb_state)
	{
		//grb_bind_spry
		if(this.getGrbProtein().getBindingGRB_SPRY_state(grb_state)==1)
		{
			if(this.getSPRY()==2 || this.getSPRY()==3)
			{
				this.setSPRY(this.getSPRY()+2*this.getGrbProtein().getGRB());
				grb_state = getSPRY();
				this.getCompute().computeBindings();
			}
			else System.out.println("Error binding SPRY_CBL");
		}
		else if(this.getGrbProtein().getBindingGRB_SPRY_state(grb_state)==0)
		{
			if(this.getSPRY()==4 || this.getSPRY()==5)
			{
				this.setSPRY(this.getSPRY()-2); // not sos:grb2
				grb_state = getSPRY();
			}
			else if(this.getSPRY()==6 || this.getSPRY()==7)
			{
				this.setSPRY(this.getSPRY()-4); //  sos:grb2
				grb_state = getSPRY();
			} 
			else System.out.println("Error relocating SPRY_CBL");
		}
		return grb_state;
	}
	// Grb2 + Sos <-> Grb2:Sos [14]
	public int getBindingSPRY_SOS_state(int sos_state)
	{
		//sos_bind_spry
		if(this.getSosProtein().getBindingSOS_SPRY_state(sos_state)==1)
		{
			if(this.getSPRY()==4 || this.getSPRY()==5)
			{
				this.setSPRY(this.getSPRY()+2);
				sos_state = getSPRY();
				this.getCompute().computeBindings();
			}
			else System.out.println("Error binding SPRY_SOS");
		}
		//grb_rel_spry
		else if(this.getSosProtein().getBindingSOS_SPRY_state(sos_state)==0)
		{
			if(this.getSPRY()==6 || this.getSPRY()==7)
			{
				this.setSPRY(this.getSPRY()-2);
				sos_state = getSPRY();
			}
			else System.out.println("Error relocating SPRY_SOS");
		}
		return sos_state;
	}
	
	public int getSPRY() {
		return SPRY;
	}

	public void setSPRY(int sPRY) {
		SPRY = sPRY;
	}

	public int getApp() {
		return app;
	}

	public void setApp(int app) {
		this.app = app;
	}

	public GRB getGrbProtein() {
		return grbProtein;
	}

	public void setGrbProtein(GRB grbProtein) {
		this.grbProtein = grbProtein;
	}

	public CBL getCblProtein() {
		return cblProtein;
	}

	public void setCblProtein(CBL cblProtein) {
		this.cblProtein = cblProtein;
	}

	public SOS getSosProtein() {
		return sosProtein;
	}

	public void setSosProtein(SOS sosProtein) {
		this.sosProtein = sosProtein;
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
