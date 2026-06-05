package implementasi;

public class ResizeMain {
	public static void main(String[] args) {
		System.out.println("--- Menguji Resizeable Kotak ---");
		
		// a. Mendefinisikan objek kotak (panjang = 4, lebar = 5)
		Kotak kotak = new Kotak(4, 5);
		kotak.pindahkan(0, 0);
		kotak.draw();
		
		// b. Cetak luas dan keliling objek
		System.out.println("[Sebelum Resize]");
		System.out.println("Panjang: " + kotak.getPanjang());
		System.out.println("Lebar: " + kotak.getLebar());
		System.out.println("Luas: " + kotak.getLuas());
		System.out.println("Keliling: " + kotak.getKeliling());
		
		// c. Ubah ukuran panjang dan lebar sebesar 2x dari ukuran semula
		System.out.println("\n--- Melakukan resize(2.0) ---");
		kotak.resize(2.0);
		
		// d. Cetak kembali luas dan keliling objek
		System.out.println("\n[Setelah Resize]");
		System.out.println("Panjang: " + kotak.getPanjang());
		System.out.println("Lebar: " + kotak.getLebar());
		System.out.println("Luas: " + kotak.getLuas());
		System.out.println("Keliling: " + kotak.getKeliling());
	}
}
