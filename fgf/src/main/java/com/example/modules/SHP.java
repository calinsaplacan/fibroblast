/**
 * 
 */
package com.example.modules;

/**
 * @author Calin
 *
 */
public class SHP {
	public int SHP;

	public Rewards compute;
	
	public SHP()
	{
		this.setSHP(1);	
	}
	// SHP2 + FRS2471P <-> SHP2:FRS2 [7]
	public int getBindingSHP_state(int shp_state) {
		// TODO Auto-generated method stub
		//shp_bind
		if(shp_state==1)
		{
			if(this.getSHP()==1)
			{
				this.setSHP(0);
				shp_state = this.getSHP();
				this.getCompute().computeBindings();
			}
			else System.out.println("Error binding SHP");
		}
		//shp_rel
		else if(shp_state==0)
		{
			if(this.getSHP()==0)
			{
				this.setSHP(1);
				shp_state = this.getSHP();
			}
			else System.out.println("Error relocating SPRY");
		}
		return shp_state;
	}

	public int getSHP() {
		return SHP;
	}

	public void setSHP(int sHP) {
		SHP = sHP;
	}
	public Rewards getCompute() {
		return compute;
	}
	public void setCompute(Rewards compute) {
		this.compute = compute;
	}
	

}
