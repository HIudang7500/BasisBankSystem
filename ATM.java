package chieuthu2;

public class ATM {
	private String soTK;
	private double soDu;
	public ATM(String soTK, double soDu) {
		this.soTK = soTK;
		this.soDu = soDu;
	}
	public String laySTK() {
		return soTK;
	}
	public double laySoDu() {
		return soDu;
	}
	public String toString() {
		String s = new String("So tai khoan:" + soTK + ", so du:" + soDu);
		return s;
	}
	public void napTien(double st) {
		soDu += st;
	}
	public boolean rutTien(double st) {
		if ( soDu < st) return false;
		else {
			soDu -= st;
			return true;
		}
	}
	public boolean chuyenTien(ATM tk, double st) {
		if (soDu >= st) {
			soDu -= st;
			tk.napTien(st);
			return true;
		}
		else return false;
	}
}
