import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class FormNilaiMhs extends JFrame {

    private static final Logger LOGGER = Logger.getLogger(FormNilaiMhs.class.getName());
    private static final String[] KOLOM_TABEL = {
            "No", "Nim", "Nama", "Nilai Uts", "Nilai Uas", "Nilai Tugas",
            "Nilai Akhir", "Nilai Huruf", "Predikat"
    };

    private final Connection con;
    private List<Mhs> cacheMhs = new ArrayList<>();
    private DefaultTableModel modelTabel;

    private JTextField nim, nama, nUTS, nUAS, nTugas;
    private JTextField nUTS1, nUAS1, nTugas1, nUTS2, nUAS2, nTugas2;
    private JButton proses, simpan, tambahLain, keluar, proses1, update, hapus;
    private JTable tabel;

    // Palet Warna Baru untuk Desain UI Unik & Berbeda
    private static final java.awt.Color COLOR_BG = new java.awt.Color(240, 244, 248);       // Abu-abu terang (Slate)
    private static final java.awt.Color COLOR_CARD_INPUT = new java.awt.Color(255, 255, 255); // Putih bersih
    private static final java.awt.Color COLOR_CARD_OUTPUT = new java.awt.Color(230, 242, 255); // Biru es sangat lembut
    private static final java.awt.Color COLOR_TEXT_DARK = new java.awt.Color(44, 62, 80);     // Navy gelap untuk label

    public FormNilaiMhs() {
        con = new DBConnection().getConnection();

        initComponents();
        setStatusAwal();
        if (con != null) {
            tampilkanDiTabel();
        }

        setTitle("Form Nilai Mahasiswa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void setStatusAwal() {
        nUTS1.setEditable(false);
        nUAS1.setEditable(false);
        nTugas1.setEditable(false);
        nUTS2.setEditable(false);
        nUAS2.setEditable(false);
        nTugas2.setEditable(false);
        simpan.setEnabled(false);
        update.setEnabled(false);
        hapus.setEnabled(false);
        proses1.setEnabled(false);
    }

    // ---------------------------------------------------------------
    // Pembangunan GUI dengan Desain & Format Tata Letak Berbeda
    // ---------------------------------------------------------------

    private void initComponents() {
        buatKomponen();
        setLayout(new BorderLayout(0, 0));

        JPanel containerPanel = new JPanel(new BorderLayout(0, 10));
        containerPanel.setBackground(COLOR_BG);
        containerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // 1. Header Judul Aplikasi Baru yang Lebih Elegan
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new java.awt.Color(41, 128, 185)); // Biru aksen medium
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        JLabel titleLabel = new JLabel("Sistem Informasi Penilaian Mahasiswa", JLabel.LEFT);
        titleLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 15));
        titleLabel.setForeground(java.awt.Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        containerPanel.add(headerPanel, BorderLayout.NORTH);

        // 2. Form Panel Utama (Menampung Input, Buttons, dan Output)
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(COLOR_BG);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // --- KARTU 1: INPUT DATA (Warna Latar Putih) ---
        JPanel inputCard = new JPanel(new GridBagLayout());
        inputCard.setBackground(COLOR_CARD_INPUT);
        inputCard.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new java.awt.Color(189, 195, 199), 1),
                " INPUT FORM ",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 11),
                COLOR_TEXT_DARK
        ));

        GridBagConstraints igbc = new GridBagConstraints();
        igbc.insets = new Insets(8, 12, 8, 12);
        igbc.fill = GridBagConstraints.HORIZONTAL;

        // Row 1: NIM, Nama
        igbc.gridx = 0; igbc.gridy = 0; igbc.weightx = 0.1;
        inputCard.add(createLabel("NIM"), igbc);
        igbc.gridx = 1; igbc.gridy = 0; igbc.weightx = 0.3;
        inputCard.add(nim, igbc);

        igbc.gridx = 2; igbc.gridy = 0; igbc.weightx = 0.1;
        inputCard.add(createLabel("Nama"), igbc);
        igbc.gridx = 3; igbc.gridy = 0; igbc.weightx = 0.5;
        igbc.gridwidth = 3;
        inputCard.add(nama, igbc);

        // Reset gridwidth & weightx
        igbc.gridwidth = 1;
        igbc.weightx = 0.33;

        // Row 2: Nilai UTS, UAS, Tugas
        igbc.gridx = 0; igbc.gridy = 1;
        inputCard.add(createLabel("Nilai UTS"), igbc);
        igbc.gridx = 1; igbc.gridy = 1;
        inputCard.add(nUTS, igbc);

        igbc.gridx = 2; igbc.gridy = 1;
        inputCard.add(createLabel("Nilai UAS"), igbc);
        igbc.gridx = 3; igbc.gridy = 1;
        inputCard.add(nUAS, igbc);

        igbc.gridx = 4; igbc.gridy = 1;
        inputCard.add(createLabel("Nilai Tugas"), igbc);
        igbc.gridx = 5; igbc.gridy = 1;
        inputCard.add(nTugas, igbc);

        // --- BARIS TOMBOL 1 ---
        JPanel btnPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        btnPanel1.setOpaque(false);
        btnPanel1.add(proses);
        btnPanel1.add(simpan);
        btnPanel1.add(tambahLain);

        // --- SEPARATOR & KELUAR ---
        JPanel sepPanel = new JPanel(new GridBagLayout());
        sepPanel.setOpaque(false);
        GridBagConstraints sgbc = new GridBagConstraints();
        sgbc.fill = GridBagConstraints.HORIZONTAL;
        sgbc.weightx = 1.0;
        sgbc.gridx = 0; sgbc.gridy = 0;
        sgbc.insets = new Insets(0, 0, 0, 15);

        JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
        separator.setForeground(java.awt.Color.LIGHT_GRAY);
        sepPanel.add(separator, sgbc);

        sgbc.fill = GridBagConstraints.NONE;
        sgbc.weightx = 0.0;
        sgbc.gridx = 1;
        sgbc.insets = new Insets(0, 0, 0, 0);
        sepPanel.add(keluar, sgbc);

        // --- BARIS TOMBOL 2 (PROSES EDIT) ---
        JPanel btnPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        btnPanel2.setOpaque(false);
        btnPanel2.add(proses1);
        btnPanel2.add(update);
        btnPanel2.add(hapus);

        // --- KARTU 2: HASIL PENILAIAN (Warna Latar Shaded Light Blue) ---
        JPanel outputCard = new JPanel(new GridBagLayout());
        outputCard.setBackground(COLOR_CARD_OUTPUT);
        outputCard.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new java.awt.Color(52, 152, 219), 1),
                " HASIL PERHITUNGAN ",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 11),
                new java.awt.Color(41, 128, 185)
        ));

        GridBagConstraints ogbc = new GridBagConstraints();
        ogbc.insets = new Insets(8, 12, 8, 12);
        ogbc.fill = GridBagConstraints.HORIZONTAL;
        ogbc.weightx = 0.33;

        // Row 1 Output: UTS 35%, UAS 35%, Tugas 30%
        ogbc.gridx = 0; ogbc.gridy = 0;
        outputCard.add(createLabel("UTS 35%"), ogbc);
        ogbc.gridx = 1; ogbc.gridy = 0;
        outputCard.add(nUTS1, ogbc);

        ogbc.gridx = 2; ogbc.gridy = 0;
        outputCard.add(createLabel("UAS 35%"), ogbc);
        ogbc.gridx = 3; ogbc.gridy = 0;
        outputCard.add(nUAS1, ogbc);

        ogbc.gridx = 4; ogbc.gridy = 0;
        outputCard.add(createLabel("Tugas 30%"), ogbc);
        ogbc.gridx = 5; ogbc.gridy = 0;
        outputCard.add(nTugas1, ogbc);

        // Row 2 Output: Nilai Akhir, Predikat, Nilai Huruf
        ogbc.gridx = 0; ogbc.gridy = 1;
        outputCard.add(createLabel("Nilai Akhir"), ogbc);
        ogbc.gridx = 1; ogbc.gridy = 1;
        outputCard.add(nUTS2, ogbc);

        ogbc.gridx = 2; ogbc.gridy = 1;
        outputCard.add(createLabel("Predikat"), ogbc);
        ogbc.gridx = 3; ogbc.gridy = 1;
        outputCard.add(nTugas2, ogbc);

        ogbc.gridx = 4; ogbc.gridy = 1;
        outputCard.add(createLabel("Nilai Huruf"), ogbc);
        ogbc.gridx = 5; ogbc.gridy = 1;
        outputCard.add(nUAS2, ogbc);

        // Menyusun Kartu & Tombol ke Form Panel Utama
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(inputCard, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(btnPanel1, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(sepPanel, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(btnPanel2, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(outputCard, gbc);

        containerPanel.add(formPanel, BorderLayout.CENTER);

        // 3. JTable Area di Bagian Paling Bawah
        JScrollPane scrollPane = new JScrollPane(tabel);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new java.awt.Color(220, 225, 230), 1),
                " DATA MAHASISWA TERDAFTAR ",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 10),
                java.awt.Color.GRAY
        ));
        tabel.setRowHeight(22);
        
        add(containerPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        pasangAksi();
        pack();
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        label.setForeground(COLOR_TEXT_DARK);
        return label;
    }

    private void buatKomponen() {
        nim = new JTextField(10);
        nama = new JTextField(15);
        nUTS = new JTextField(8);
        nUAS = new JTextField(8);
        nTugas = new JTextField(8);
        nUTS1 = new JTextField(8);
        nUAS1 = new JTextField(8);
        nTugas1 = new JTextField(8);
        nUTS2 = new JTextField(8);
        nUAS2 = new JTextField(8);
        nTugas2 = new JTextField(10);

        proses = new JButton("Proses");
        simpan = new JButton("Simpan");
        tambahLain = new JButton("Tambah Lain");
        proses1 = new JButton("Proses");
        update = new JButton("Update");
        hapus = new JButton("Hapus");
        keluar = new JButton("Keluar");

        modelTabel = new DefaultTableModel(KOLOM_TABEL, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabel = new JTable(modelTabel);
    }

    private void pasangAksi() {
        proses.addActionListener(e -> prosesHitungNilai());
        simpan.addActionListener(e -> simpanData());
        tambahLain.addActionListener(e -> kosongkanUntukInputBaru());
        proses1.addActionListener(e -> {
            prosesHitungNilai();
            update.setEnabled(true);
        });
        update.addActionListener(e -> updateData());
        hapus.addActionListener(e -> hapusData());
        keluar.addActionListener(e -> dispose());

        tabel.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                return;
            }
            int index = tabel.getSelectedRow();
            if (index < 0) {
                return;
            }
            terpilih(index);
            proses1.setEnabled(true);
            hapus.setEnabled(true);
        });
    }

    private void prosesHitungNilai() {
        try {
            String ni = nim.getText().trim();
            String na = nama.getText().trim();
            double ts = Double.parseDouble(nUTS.getText().trim());
            double as = Double.parseDouble(nUAS.getText().trim());
            double tgs = Double.parseDouble(nTugas.getText().trim());

            Mhs m = new Mhs(ni, na, ts, as, tgs);
            nUTS1.setText(String.valueOf(m.uts()));
            nUAS1.setText(String.valueOf(m.uas()));
            nTugas1.setText(String.valueOf(m.tugas()));
            nUTS2.setText(String.valueOf(m.nilaiAkhir()));
            nUAS2.setText(m.getNilaiHuruf());
            nTugas2.setText(m.getPredikat());

            simpan.setEnabled(true);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Inputan Anda Kosong atau Tidak Valid",
                    "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private List<Mhs> ambilDataMahasiswa() {
        List<Mhs> daftar = new ArrayList<>();
        if (con == null) {
            return daftar;
        }
        String query = "SELECT nim, nama, nilai_uts, nilai_uas, nilai_tugas, nilai_akhir, nilai_huruf, predikat FROM mhs";

        try (PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                daftar.add(new Mhs(
                        rs.getString("nim"),
                        rs.getString("nama"),
                        rs.getDouble("nilai_uts"),
                        rs.getDouble("nilai_uas"),
                        rs.getDouble("nilai_tugas"),
                        rs.getDouble("nilai_akhir"),
                        rs.getString("nilai_huruf"),
                        rs.getString("predikat")));
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Gagal mengambil data mahasiswa", ex);
        }
        return daftar;
    }

    private void tampilkanDiTabel() {
        cacheMhs = ambilDataMahasiswa();
        modelTabel.setRowCount(0);

        int no = 1;
        for (Mhs m : cacheMhs) {
            modelTabel.addRow(new Object[] {
                    no++, m.getNim(), m.getNama(), m.uts(), m.uas(), m.tugas(),
                    m.nilaiAkhir(), m.getNilaiHuruf(), m.getPredikat()
            });
        }
    }

    private void terpilih(int index) {
        if (index < 0 || index >= cacheMhs.size()) {
            return;
        }
        Mhs m = cacheMhs.get(index);
        nim.setText(m.getNim());
        nama.setText(m.getNama());
        nUTS.setText(String.valueOf(m.uts()));
        nUAS.setText(String.valueOf(m.uas()));
        nTugas.setText(String.valueOf(m.tugas()));
    }

    private void kosongkanTextField() {
        JTextField[] semuaField = { nim, nama, nUTS, nUAS, nTugas, nUTS1, nUAS1, nTugas1, nUTS2, nUAS2, nTugas2 };
        for (JTextField f : semuaField) {
            f.setText("");
        }
    }

    private void kosongkanUntukInputBaru() {
        kosongkanTextField();
        simpan.setEnabled(false);
    }

    private void isiParameterNilai(PreparedStatement ps, int mulai) throws SQLException {
        ps.setString(mulai, nim.getText().trim());
        ps.setString(mulai + 1, nama.getText().trim());
        ps.setDouble(mulai + 2, Double.parseDouble(nUTS.getText().trim()));
        ps.setDouble(mulai + 3, Double.parseDouble(nUAS.getText().trim()));
        ps.setDouble(mulai + 4, Double.parseDouble(nTugas.getText().trim()));
        ps.setDouble(mulai + 5, Double.parseDouble(nUTS2.getText().trim()));
        ps.setString(mulai + 6, nUAS2.getText().trim());
        ps.setString(mulai + 7, nTugas2.getText().trim());
    }

    private void simpanData() {
        String query = "INSERT INTO mhs(nim,nama,nilai_uts,nilai_uas,nilai_tugas,nilai_akhir,nilai_huruf,predikat) "
                + "VALUES (?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            isiParameterNilai(ps, 1);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data tersimpan");
        } catch (SQLException | NumberFormatException ex) {
            LOGGER.log(Level.SEVERE, "Gagal menyimpan data", ex);
            JOptionPane.showMessageDialog(this, "Data tidak tersimpan");
        } finally {
            muatUlangSetelahPerubahan();
        }
    }

    private void updateData() {
        String query = "UPDATE mhs SET nim=?, nama=?, nilai_uts=?, nilai_uas=?, nilai_tugas=?, nilai_akhir=?, nilai_huruf=?, predikat=? "
                + "WHERE nim=?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            isiParameterNilai(ps, 1);
            ps.setString(9, nim.getText().trim());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data berhasil diupdate");
        } catch (SQLException | NumberFormatException ex) {
            LOGGER.log(Level.SEVERE, "Gagal mengupdate data", ex);
            JOptionPane.showMessageDialog(this, "Data tidak berhasil diupdate");
        } finally {
            muatUlangSetelahPerubahan();
        }
    }

    private void hapusData() {
        String query = "DELETE FROM mhs WHERE nim = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, nim.getText().trim());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Mahasiswa berhasil dihapus");
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Gagal menghapus data", ex);
            JOptionPane.showMessageDialog(this, "Mahasiswa tidak berhasil dihapus");
        } finally {
            muatUlangSetelahPerubahan();
        }
    }

    private void muatUlangSetelahPerubahan() {
        kosongkanTextField();
        tampilkanDiTabel();
        simpan.setEnabled(false);
        update.setEnabled(false);
        hapus.setEnabled(false);
        proses1.setEnabled(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new FormNilaiMhs().setVisible(true);
        });
    }
}