package abstrak;

public class SepedaMain {
	public static void main(String[] args) {
		// Membuat objek dari subclass Sepeda
		Sepeda sepeda1 = new Sepeda(true);
		Sepeda sepeda2 = new Sepeda(false);

		System.out.print("Sepeda 1: ");
		sepeda1.jenis();

		System.out.print("Sepeda 2: ");
		sepeda2.jenis();
	}
}
