/**
 * 
 */
package com.example.modules;

/**
 * @author Calin
 *
 */
public class FRS2 {

	public int relocFRS2;// ubiquitin mdification of FRS2

	public int degFRS2;// FRS2 relocated

	public int FRS2_Ubi;// FRS2 degraded

	public int frs = relocFRS2 & degFRS2; // frs2 not degraded or relocated
	// phosphorilation of receptors

	public int Y196P;
	public int Y306P;
	public int Y471P;

	public int Y653P;
	public int Y654P;
	public int Y766P;

	// compounds: FRS2
	public int FRS2_SRC;

	// 0 - SRC not bound
	// 1 - SRC bound
	// 2 - SRC:SPRY bound
	// 3 - SRC:SPRYP bound
	// 4 - SRC:SPRYP:CBL bound
	// 5 - SRC:SPRYP:GRB bound
	// 6 - SRC:SPRYP:GRB:CBL bound
	// 7 - SRC:SPRYP:GRB:SOS bound
	// 8 - SRC:SPRYP:GRB:SOS:CBL bound

	public int FRS2_FGFR; // [0..1] init 0; // 0 - FGFR not bound, 1 - FGFR
							// bound

	public int FRS2_GRB; // [0..2] init 0; // 0 - GRB2 not bound, 1 - GRB2
							// bound, 2 - GRB2:SOS bound
	public int FRS2_SHP; // 0 - SHP2 not bound, 1 - SHP2 bound

	// proteins
	public FGFR fgfrProtein;
	public PLC plcProtein;
	public SRC srcProtein;
	public SPRY spryProtein;
	public SHP shpProtein;
	public GRB grbProtein;
	public CBL cblProtein;
	public SOS sosProtein;

	public Rewards compute;
	
	public FRS2() {

		// proteins initialization
		this.setFgfrProtein(new FGFR());
		this.setPlcProtein(new PLC());
		this.setSrcProtein(new SRC());
		this.setGrbProtein(new GRB());
		this.setSpryProtein(new SPRY());
		this.setShpProtein(new SHP());
		this.setCblProtein(new CBL());
		this.setSosProtein(new SOS());

		this.setRelocFRS2(0);
		this.setDegFRS2(0);
		this.setFRS2_Ubi(0);
		this.setY196P(0);
		this.setY306P(0);
		this.setY471P(0);

		this.setFRS2_FGFR(0);
		this.setFRS2_GRB(0);
		this.setFRS2_SHP(0);
		this.setFRS2_SRC(0);

		// this.Y653P = 0;
		// this.Y654P = 0;
		// this.Y766P = 0;

	}

	// formula frs = relocFRS2=0 & degFRS2=0;

	// fgfr+frs2 <-> fgfr:frs2 [4]
	public int getBindingFRS2_FGFR_state(int fgfr_state) {
		if (getFgfrProtein().getBindingFGFR_state(fgfr_state) == 1) {
			// [fgfr_bind]
			if (this.getFrs() == 0 && this.getFRS2_FGFR() == 0) {
				this.setFRS2_FGFR(1); 
				fgfr_state = this.getFRS2_FGFR();
				this.getCompute().computeBindings();
			} else
				System.out.println("Error binding at FRS2_FGFR");
		} else if (getFgfrProtein().getBindingFGFR_state(fgfr_state) == 0) {
			// [fgfr_rel]
			if (this.getFrs() == 0 && this.getFRS2_FGFR() == 1) {
				this.setFRS2_FGFR(0);
				fgfr_state = this.getFRS2_FGFR();
			} else
				System.out.println("Error relocating at FRS2_FGFR");
		}
		return fgfr_state;
	}

	// phosporilation of receptors
	public void phosporilationOfReceptors() {

		if (getFrs() == 0 && getY653P() == 1 && getY654P() == 1
				&& FRS2_FGFR == 1 && getY196P() == 0) {
			this.setY196P(1);
		}
		if (getFrs() == 0 && getY653P() == 1 && getY654P() == 1
				&& FRS2_FGFR == 1 && getY306P() == 0) {
			this.setY306P(1);
		}
		if (getFrs() == 0 && getY653P() == 1 && getY654P() == 1
				&& FRS2_FGFR == 1 && getY471P() == 0) {
			this.setY471P(1);
		}
	}

	// FRS2196 <- FRS2196P [6]
	public void dephosphorilation_FRS2196P(int frs2_state) {

		// src not bound
		if (getFrs() == 0 && getFRS2_SHP() == 1 && getY196P() == 1
				&& getFRS2_SRC() == 0) {
			this.setY196P(0);
		}
		// src bound

		if (getSrcProtein().getBindingSRC_state(frs2_state) == 0) {
			if (getFrs() == 0 && getFRS2_SHP() == 1 && getY196P() == 1
					&& getFRS2_SRC() > 0) {
				this.setY196P(0);
				this.setFRS2_SRC(0);
			}
		}

	}

	// FRS2306 <- FRS2306P [6]
	public void dephosphorilation_FRS2306P(int frs2_state) {

		// grb2 not bound
		if (getFrs() == 0 && getFRS2_SHP() == 1 && getY306P() == 1
				&& getFRS2_GRB() == 0) {
			this.setY306P(0);
		}
		// grb2 bound

		if (getGrbProtein().getBindingGRB_state(frs2_state) == 0) {
			if (getFrs() == 0 && getFRS2_SHP() == 1 && getY306P() == 1
					&& getFRS2_GRB() > 0) {
				this.setY306P(0);
				this.setFRS2_GRB(0);
			}

		}
	}

	// FRS2471 <- FRS2471P [6]
	public void dephosphorilation_FRS2471P(int frs2_state) {

		if (getShpProtein().getBindingSHP_state(frs2_state) == 0) {
			if (getFrs() == 0 & getFRS2_SHP() == 1 & getY471P() == 1) {
				this.setY471P(0);
				this.setFRS2_SHP(0);
			}
		}
	}

	// SRC + FRS2196P <-> SRC:FRS2 [7]
	public int getBindingFRS2_SRC_state(int src_state) {

		if (getSrcProtein().getBindingSRC_state(src_state) == 1) {
			// src_bind
			if (getFrs() == 0 && getY196P() == 1 && getFRS2_SRC() > 0) {
				setFRS2_SRC(this.getSrcProtein().getSRC());
				src_state = this.getFRS2_SRC();
				this.getCompute().computeBindings();
			} else
				System.out.println("Error binding at FRS2_SRC");

		} else if (getSrcProtein().getBindingSRC_state(src_state) == 0) {

			// [src_rel]
			if (this.getFrs() == 0 && this.getFRS2_SRC() > 0) {
				this.setFRS2_SRC(0);
				src_state = this.getFRS2_SRC();
			} else
				System.out.println("Error relocating at FRS2_SRC");
		}
		return src_state;

	}

	// GRB2 + FRS2306P <-> GRB2:FRS2 [7]
	public int getBindingFRS2_GRB_state(int grb_state) {

		if (getGrbProtein().getBindingGRB_state(grb_state) == 1)
			// grb bind
			if (getFrs() == 0 && getY306P() == 1 && getFRS2_GRB() == 0) {
				setFRS2_GRB(this.getGrbProtein().getGRB());
				grb_state = this.getFRS2_GRB();
				this.getCompute().computeBindings();
			} else
				System.out.println("Error binding at FRS2_GRB");
		else if (getGrbProtein().getBindingGRB_state(grb_state) > 0) {
			// grb rel
			if (getFrs() == 0 && getFRS2_GRB() == 0) {
				setFRS2_GRB(0);
				grb_state = this.getFRS2_GRB();

			}
			System.out.println("Error relocating at FRS2_GRB");
		}
		return grb_state;
	}

	// SHP2 + FRS2471P <-> SHP2:FRS2 [7]
	public int getBindingFRS2_SHP2_state(int shp2_state) {

		if (getShpProtein().getBindingSHP_state(shp2_state) == 1)
			// shp bind
			if (getFrs() == 0 && getY471P() == 1 && getFRS2_SHP() == 0) {
				setFRS2_SHP(this.getShpProtein().getSHP());
				shp2_state = this.getFRS2_SHP();
				this.getCompute().computeBindings();
			} else
				System.out.println("Error binding at FRS2_SHP");
		else if (getShpProtein().getBindingSHP_state(shp2_state) == 0) {
			// shp rel
			if (getFrs() == 0 && getFRS2_SHP() == 1) {
				setFRS2_SHP(0);
				shp2_state = this.getFRS2_SHP();

			}
			System.out.println("Error relocating at FRS2_SHP");
		}
		return shp2_state;
	}

	// Src:FRS2 -> degFRS2 [8]
	public void dephosporilation_FRS2() {
		if (getFrs() == 0 && getFRS2_SRC() > 0) {
			this.setRelocFRS2(1);
		} else
			System.out.println("Error dephosphorilation FRS2");
	}

	// Spry + Src -> Spry55:Src/Spryp + Src -> Spry55p:Src [11]

	public int getBindingFRS2_SPRY_state(int spry_state) {
		//spry_bind_frs
		if (getSpryProtein().getBindingSPRY_FRS_state(spry_state) == 1) {
			if (getFrs() == 0 && getFRS2_SRC() == 1) {
				setFRS2_SRC(this.getSpryProtein().getSPRY() + 1);
				spry_state = this.getFRS2_SRC();
				this.getCompute().computeBindings();
			} else
				System.out.println("Error binding at FRS2_SPRY");
		}

		// Spry + Src <- Spry55:Src [11]
		//spry_rel_frs
		else if (getSpryProtein().getBindingSPRY_FRS_state(spry_state) == 0) {
			if (getFrs() == 0 && getFRS2_SRC() == 2) {
				setFRS2_SRC(1);
				spry_state = this.getFRS2_SRC();
			}
			// Spryp + Src <- Spry55p:Src [11]
			else if (getFrs() == 0 && getFRS2_SRC() > 2) {
				setFRS2_SRC(1);
				spry_state = this.getFRS2_SRC();
			} else
				System.out.println("Error relocating at FRS2_SPRY");
		}

		return spry_state;
	}

	// Spry55:Src -> Spry55p:Src [11]

	public void phosphorilationSPRY_SRC() {
		if (getFrs() == 0 && getFRS2_SRC() == 2) {
			this.setFRS2_SRC(3);
		} else
			System.out.println("Error phosphorilation SPRY_SRC");
	}

	// Spry55p + Cbl <-> Spry55p:Cbl [11]

	public int getBindingFRS2_CBL_state(int cbl_state) {

		// cbl_bind
		if (getCblProtein().getBindingCBL_FRS2_state(cbl_state) == 1) {
			if (getFrs() == 0
					&& (getFRS2_SRC() == 3 || getFRS2_SRC() == 5 || getFRS2_SRC() == 7)) {
				setFRS2_SRC(getFRS2_SRC() + 1);
				cbl_state = getFRS2_SRC();
				this.getCompute().computeBindings();
			} else
				System.out.println("Error binding at SPRY_CBL");

		} else if (getCblProtein().getBindingCBL_FRS2_state(cbl_state) == 0) {
			if (getFrs() == 0
					&& (getFRS2_SRC() == 4 || getFRS2_SRC() == 6 || getFRS2_SRC() == 8)) {
				setFRS2_SRC(getFRS2_SRC() - 1);
				cbl_state = getFRS2_SRC();
			} else
				System.out.println("Error relocating at SPRY_CBL");
		}
		return cbl_state;

	}

	// Spry55p + Grb2 <-> Spry55p:Grb2 [11]

	public int getBindingFRS2_GRB_2_state(int grb_state) {
		// grb_bind_frs
		if (getGrbProtein().getBindingGRB_FRS_2_state(grb_state) == 1) {
			if (getFrs() == 0 && (getFRS2_SRC() == 3 || getFRS2_SRC() == 4)) {
				this.setFRS2_SRC(this.getFRS2_SRC() + 2 * this.getGrbProtein().getGRB());
				grb_state = this.getFRS2_SRC();
				this.getCompute().computeBindings();
			} else
				System.out.println("Error binding at SPRY_GRB");
		}
		// grb_rel_frs
		else if (getGrbProtein().getBindingGRB_FRS_2_state(grb_state) == 0) {
			if (getFrs() == 0 && (getFRS2_SRC() == 5 || getFRS2_SRC() == 6)) {
				this.setFRS2_SRC(this.getFRS2_SRC() - 2);
				grb_state = this.getFRS2_SRC();
			} else if (getFrs() == 0
					&& (getFRS2_SRC() == 7 || getFRS2_SRC() == 8)) {
				this.setFRS2_SRC(this.getFRS2_SRC() - 4);
				grb_state = this.getFRS2_SRC();
			} else
				System.out.println("Error relocating at SPRY_GRB");
		}
		return grb_state;
	}

	// Spry55p:Cbl +FRS2 -> Frs-Ubi [12]
	public void phosphorizationFrs_Ubi() {
		if (getFrs() == 0
				&& (getFRS2_SRC() == 4 || getFRS2_SRC() == 6 || getFRS2_SRC() == 8)
				&& this.getFRS2_Ubi() == 0) {
			this.setFRS2_Ubi(1);
		} else
			System.out.println("Error phosphorilation at FRS2_Ubi");
	}

	// FRS2-Ubi -> degFRS2 [12]

	public void degradeFRS2_Ubi() {
		this.phosphorizationFrs_Ubi();
		if (getFrs() == 0 && getFRS2_Ubi() == 1) {
			this.setDegFRS2(1);
		} else
			System.out.println("Error degradation at FRS2_Ubi");
	}

	// Spry55p -> Spry55 [13]

	public void dephosphorizationSPRY() {
		
		if (getFrs() == 0 && getFRS2_SHP() == 1 && getFRS2_SRC() > 2) {
			this.setFRS2_SRC(2);
		} else
			System.out.println("Error dephosphorization at SPRY ");
			
	}

	// Grb2 + Sos <-> Grb2:Sos [14]

	public int getBindingFRS_SOS_state(int sos_state) {
		// sos_bind_frs_grb
		if (getSosProtein().getBindingSOS_FRS_state(sos_state)== 1) {
			if (getFrs() == 0 && getFRS2_GRB() == 1) 
			{
				this.setFRS2_GRB(2);
				sos_state = this.getFRS2_GRB();
				this.getCompute().computeBindings();
			} else
				System.out.println("Error binding at GRB_SOS");
		}
		// sos_rel_frs_grb
		else if (getSosProtein().getBindingSOS_FRS_state(sos_state) == 0) {
			if (getFrs() == 0 && getFRS2_GRB() == 2) {
				this.setFRS2_GRB(1);
				sos_state = this.getFRS2_SRC();
			} else
				System.out.println("Error relocating at GRB_SOS");
		}
		return sos_state;
	}
	public int getBindingFRS_SOS_2_state(int sos_state) {
		// sos_bind_frs_src
		if (getSosProtein().getBindingSOS_FRS_state(sos_state)== 1) {
			if (getFrs() == 0 && getFRS2_SRC() == 5|| getFRS2_SRC() == 6) {
				this.setFRS2_SRC(getFRS2_SRC() + 2);
				sos_state = this.getFRS2_SRC();
				this.getCompute().computeBindings();
			} else
				System.out.println("Error binding at SRC_SOS");
		}
		// sos_rel_frs_src
		else if (getSosProtein().getBindingSOS_FRS_state(sos_state) == 0) {
			 if (getFrs() == 0 && getFRS2_SRC() == 7 || getFRS2_SRC() == 8) {
				this.setFRS2_SRC(getFRS2_SRC() - 2);
				sos_state = this.getFRS2_SRC();
			} else
				System.out.println("Error relocating at GRB_SOS");
		}
		return sos_state;
	}

	public int getRelocFRS2() {
		return relocFRS2;
	}

	public void setRelocFRS2(int relocFRS2) {
		this.relocFRS2 = relocFRS2;
	}

	public int getDegFRS2() {
		return degFRS2;
	}

	public void setDegFRS2(int degFRS2) {
		this.degFRS2 = degFRS2;
	}

	public int getFRS2_Ubi() {
		return FRS2_Ubi;
	}

	public void setFRS2_Ubi(int fRS2_Ubi) {
		FRS2_Ubi = fRS2_Ubi;
	}


	public int getFrs() {
		return frs;
	}

	public void setFrs(int frs) {
		this.frs = frs;
	}

	public int getY196P() {
		return Y196P;
	}

	public void setY196P(int y196p) {
		Y196P = y196p;
	}

	public int getY306P() {
		return Y306P;
	}

	public void setY306P(int y306p) {
		Y306P = y306p;
	}

	public int getY471P() {
		return Y471P;
	}

	public void setY471P(int y471p) {
		Y471P = y471p;
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

	public int getFRS2_SRC() {
		return FRS2_SRC;
	}

	public void setFRS2_SRC(int fRS2_SRC) {
		FRS2_SRC = fRS2_SRC;
	}

	public int getFRS2_FGFR() {
		return FRS2_FGFR;
	}

	public void setFRS2_FGFR(int fRS2_FGFR) {
		FRS2_FGFR = fRS2_FGFR;
	}

	public int getFRS2_GRB() {
		return FRS2_GRB;
	}

	public void setFRS2_GRB(int fRS2_GRB) {
		FRS2_GRB = fRS2_GRB;
	}

	public int getFRS2_SHP() {
		return FRS2_SHP;
	}

	public void setFRS2_SHP(int fRS2_SHP) {
		FRS2_SHP = fRS2_SHP;
	}

	public SOS getSosProtein() {
		return sosProtein;
	}

	public void setSosProtein(SOS sosProtein) {
		this.sosProtein = sosProtein;
	}

	public PLC getPlcProtein() {
		return plcProtein;
	}

	public void setPlcProtein(PLC plcProtein) {
		this.plcProtein = plcProtein;
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

	public SHP getShpProtein() {
		return shpProtein;
	}

	public void setShpProtein(SHP shpProtein) {
		this.shpProtein = shpProtein;
	}

	public GRB getGrbProtein() {
		return grbProtein;
	}

	public void setGrbProtein(GRB grbProtein) {
		this.grbProtein = grbProtein;
	}

	public FGFR getFgfrProtein() {
		return fgfrProtein;
	}

	public void setFgfrProtein(FGFR fgfrProtein) {
		this.fgfrProtein = fgfrProtein;
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
