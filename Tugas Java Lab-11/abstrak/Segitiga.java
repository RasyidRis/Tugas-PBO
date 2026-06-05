package abstrak;

public class Segitiga extends BangunDatar {
	private double alas;
	private double tinggi;

	public Segitiga(double alas, double tinggi) {
		super(3);
		this.alas = alas;
		this.tinggi = tinggi;
	}

	@Override
	public void draw() {
		System.out.println("Menggambar Segitiga dengan alas " + alas + " dan tinggi " + tinggi);
	}

	@Override
	public void resize() {
		System.out.println("Mengubah ukuran Segitiga.");
	}

	@Override
	public double getLuas() {
		return 0.5 * alas * tinggi;
	}

	@Override
	public double getKeliling() {
		double miring = Math.sqrt((alas * alas) + (tinggi * tinggi));
		return alas + tinggi + miring;
	}

	public double getAlas() {
		return alas;
	}

	public void setAlas(double alas) {
		this.alas = alas;
	}

	public double getTinggi() {
		return tinggi;
	}

	public void setTinggi(double tinggi) {
		this.tinggi = tinggi;
	}
}
