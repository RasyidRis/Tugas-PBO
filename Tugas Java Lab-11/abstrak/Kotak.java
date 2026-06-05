package abstrak;

public class Kotak extends BangunDatar {
	private double panjang;
	private double lebar;

	public Kotak(double panjang, double lebar) {
		super(4);
		this.panjang = panjang;
		this.lebar = lebar;
	}

	@Override
	public void draw() {
		System.out.println("Menggambar Kotak dengan panjang " + panjang + " dan lebar " + lebar);
	}

	@Override
	public void resize() {
		System.out.println("Mengubah ukuran Kotak.");
	}

	@Override
	public double getLuas() {
		return panjang * lebar;
	}

	@Override
	public double getKeliling() {
		return 2 * (panjang + lebar);
	}

	public double getPanjang() {
		return panjang;
	}

	public void setPanjang(double panjang) {
		this.panjang = panjang;
	}

	public double getLebar() {
		return lebar;
	}

	public void setLebar(double lebar) {
		this.lebar = lebar;
	}
}
