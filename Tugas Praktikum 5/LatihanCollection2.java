import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;

public class LatihanCollection2 {
    public static void main(String[] args) {
        Set<String> daftarBuku = new TreeSet<>();

        daftarBuku.add("Dilan 1990");
        daftarBuku.add("Laskar Pelangi");
        daftarBuku.add("Mengejar Matahari");
        daftarBuku.add("Dilan 1991");
        daftarBuku.add("Mahaguru");
        daftarBuku.add("Milea");
        daftarBuku.add("Perahu Kertas");
        daftarBuku.add("Laskar Pelangi");
        daftarBuku.add("Perahu Kertas");

        System.out.println("Daftar Buku:");
        Iterator<String> iterator = daftarBuku.iterator();
        int nomor = 1;
        while (iterator.hasNext()) {
            String string = (String) iterator.next();
            System.out.println(nomor + ". " + string);
            nomor++;
        }

        System.out.println();

        if (daftarBuku.contains("Mahaguru")) {
            System.out.println("Buku Mahaguru ada");

            daftarBuku.remove("Mahaguru");
        }

        System.out.println("\nDaftar Buku setelah Mahaguru dipinjam:");

        nomor = 1;
        for (String string : daftarBuku) {
            System.out.println(nomor + ". " + string);
            nomor++;
        }
    }
}