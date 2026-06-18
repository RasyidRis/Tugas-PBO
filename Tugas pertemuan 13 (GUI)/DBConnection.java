import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Kelas utilitas untuk membuka koneksi ke database MySQL "pbo".
 *
 * Optimasi dibanding versi di slide:
 * - Logger dan parameter koneksi (URL/USER/PASSWORD) dijadikan konstanta
 *   statis sehingga tidak dibuat ulang setiap kali getConnection() dipanggil,
 *   dan Logger tidak lagi salah merujuk ke kelas lain (FormNilaiMhs.class).
 */
public class DBConnection {

    private static final Logger LOGGER = Logger.getLogger(DBConnection.class.getName());
    private static final String URL = "jdbc:mysql://localhost/pbo";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Koneksi ke database gagal", ex);
            javax.swing.JOptionPane.showMessageDialog(null,
                    "Detail Error: " + ex.getMessage() + "\n\n" +
                    "Solusi Umum:\n" +
                    "1. Pastikan Apache & MySQL di XAMPP/Laragon sudah di-START.\n" +
                    "2. Pastikan database 'pbo' sudah dibuat (jalankan file skema_database.sql di phpMyAdmin).\n" +
                    "3. Jika port MySQL Anda bukan 3306 (misal 3307), ubah URL di DBConnection.java.",
                    "Koneksi Database Gagal", javax.swing.JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
