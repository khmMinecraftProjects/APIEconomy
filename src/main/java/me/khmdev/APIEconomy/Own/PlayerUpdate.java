package me.khmdev.APIEconomy.Own;

public class PlayerUpdate {
	private String pl;
	private double m;
	private apli aplic;
	enum apli{reduce,added,set};
	public PlayerUpdate(String p,double f,apli a){
		setPl(p);m=f;setAplic(a);
	}

	public double getM() {
		return m;
	}
	public void setM(double m) {
		this.m = m;
	}

	public String getPl() {
		return pl;
	}

	public void setPl(String pl) {
		this.pl = pl;
	}

	public apli getAplic() {
		return aplic;
	}

	public void setAplic(apli aplic) {
		this.aplic = aplic;
	}

}
