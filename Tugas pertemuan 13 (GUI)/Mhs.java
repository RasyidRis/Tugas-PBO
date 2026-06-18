
public class Mhs {

    private static final double BOBOT_UTS = 0.35;
    private static final double BOBOT_UAS = 0.35;
    private static final double BOBOT_TUGAS = 0.30;

    private final String nim;
    private final String nama;
    private final double nilaiUts;
    private final double nilaiUas;
    private final double nilaiTugas;
    private final double nilaiAkhir;
    private final String nilaiHuruf;
    private final String predikat;


    public Mhs(String nim, String nama, double nilaiUts, double nilaiUas, double nilaiTugas) {
        this.nim = nim;
        this.nama = nama;
        this.nilaiUts = nilaiUts;
        this.nilaiUas = nilaiUas;
        this.nilaiTugas = nilaiTugas;
        this.nilaiAkhir = (nilaiUts * BOBOT_UTS) + (nilaiUas * BOBOT_UAS) + (nilaiTugas * BOBOT_TUGAS);
        this.nilaiHuruf = hitungNilaiHuruf(this.nilaiAkhir);
        this.predikat = hitungPredikat(this.nilaiHuruf);
    }


    public Mhs(String nim, String nama, double nilaiUts, double nilaiUas, double nilaiTugas,
               double nilaiAkhir, String nilaiHuruf, String predikat) {
        this.nim = nim;
        this.nama = nama;
        this.nilaiUts = nilaiUts;
        this.nilaiUas = nilaiUas;
        this.nilaiTugas = nilaiTugas;
        this.nilaiAkhir = nilaiAkhir;
        this.nilaiHuruf = nilaiHuruf;
        this.predikat = predikat;
    }

    public String getNim() { return nim; }
    public String getNama() { return nama; }
    public double uts() { return nilaiUts; }
    public double uas() { return nilaiUas; }
    public double tugas() { return nilaiTugas; }
    public double nilaiAkhir() { return nilaiAkhir; }
    public String getNilaiHuruf() { return nilaiHuruf; }
    public String getPredikat() { return predikat; }


    public String getNilHuruf(double nilai) {
        return hitungNilaiHuruf(nilai);
    }


    public String getPredikat(String huruf) {
        return hitungPredikat(huruf);
    }

    private static String hitungNilaiHuruf(double nilai) {
        if (nilai >= 85) return "A";
        if (nilai >= 75) return "AB";
        if (nilai >= 65) return "B";
        if (nilai >= 60) return "BC";
        if (nilai >= 50) return "C";
        if (nilai >= 40) return "D";
        return "E";
    }

    private static String hitungPredikat(String huruf) {
        switch (huruf) {
            case "A":
            case "AB":
                return "Sangat Baik";
            case "B":
            case "BC":
                return "Baik";
            case "C":
                return "Cukup";
            case "D":
                return "Kurang";
            default:
                return "Tidak Lulus";
        }
    }
}
