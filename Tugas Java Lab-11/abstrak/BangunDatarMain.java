package abstrak;

public class BangunDatarMain {
	public static void main(String[] args) {
		System.out.println("--- Menguji Class Kotak ---");
		Kotak kotak = new Kotak(10, 5);
		kotak.pindahkan(2, 3);
		kotak.draw();
		System.out.println("Jumlah Sisi Kotak: " + kotak.getJumlahSisi());
		System.out.println("Posisi Kotak: (" + kotak.getX() + ", " + kotak.getY() + ")");
		System.out.println("Luas Kotak: " + kotak.getLuas());
		System.out.println("Keliling Kotak: " + kotak.getKeliling());

		System.out.println("\n--- Menguji Class Segitiga (Siku-siku) ---");
		Segitiga segitiga = new Segitiga(6, 8);
		segitiga.pindahkan(5, 5);
		segitiga.draw();
		System.out.println("Jumlah Sisi Segitiga: " + segitiga.getJumlahSisi());
		System.out.println("Posisi Segitiga: (" + segitiga.getX() + ", " + segitiga.getY() + ")");
		System.out.println("Luas Segitiga: " + segitiga.getLuas());
		System.out.println("Keliling Segitiga: " + segitiga.getKeliling());
	}
}
