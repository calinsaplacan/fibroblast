/**
 * 
 */
package com.example.modules;

/**
 * @author Calin
 *
 */
public class PLC {
	public int PLC;
	public int PLC_1;
	
	public PLC () {
		this.setPLC(1);
	}
	// PLC + FGFR766P <-> PLC:FGFR [9]
	public int getBindingPLC_state(int plc_state) {
		if(plc_state == 1 )
		{
			// [plc_bind]
			 if (this.getPLC() == 1)
			{
				this.setPLC_1(0); // PLC BOUNDED
				plc_state = this.getPLC_1();
			}
			 else System.out.println("Error binding");
		}
		else if(plc_state == 0)
				
			{ 
			// [plc_rel]
			if (this.getPLC() == 0)
				{
					this.setPLC_1(1); // PLC UNBOUNDED
					plc_state = this.getPLC_1();
				}
			else System.out.println("Error relocating");
			}
		return plc_state;
	}
	public int getPLC() {
		return PLC;
	}
	public void setPLC(int pLC) {
		PLC = pLC;
	}
	public int getPLC_1() {
		return PLC_1;
	}
	public void setPLC_1(int pLC_1) {
		PLC_1 = pLC_1;
	}

}
