import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		BangunDatar bd;
		Scanner inputan = new Scanner(System.in);
		
		// 2.1 A & B: Persegi Panjang dan Segitiga
		SegiEmpat persegiPanjang = new SegiEmpat();
		bd = persegiPanjang;
		System.out.println("Masukkan Panjang:");
		bd.panjang = inputan.nextDouble();
		
		System.out.println("Masukkan Lebar:");
		bd.lebar = inputan.nextDouble();
		
		double luas = bd.luas();
		bd.cetakLuas("Persegi panjang",luas);
		
		Segitiga sikusiku = new Segitiga();
		bd = sikusiku;
		
		System.out.println("Masukkan Panjang:");
		bd.panjang = inputan.nextDouble();
		
		System.out.println("Masukkan Tinggi:");
		bd.tinggi = inputan.nextDouble();
		
		double luas1 = bd.luas(bd.panjang, bd.tinggi);
		bd.cetakLuas("Segitiga siku-siku", luas1);
		
		// 2.1 C: Tambahan sesuai kreasi
		SegiEmpat persegi = new SegiEmpat();
		
		System.out.println("Masukkan Sisi Persegi:");
		int sisi = inputan.nextInt();
		
		int luas2 = persegi.luas(sisi);
		persegi.cetakLuas("Persegi", luas2);
		
		// Kreasi Subclass Lingkaran
		Lingkaran lk = new Lingkaran();
		
		System.out.println("Masukkan Jari-jari Lingkaran:");
		double r = inputan.nextDouble();
		
		double luasLk = lk.luasLingkaran(r);
		lk.cetakLuas("Lingkaran", luasLk);
		
		inputan.close();
	}
}