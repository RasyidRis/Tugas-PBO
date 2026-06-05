package abstrak;

public class Mobil extends Kendaraan {
	private int kapasitasBagasi;
	
	@Override
	public void nyalakan() {
		// TODO Auto-generated method stub
		System.out.println("Mobil dinyalakan");
	}

	public int getKapasitasBagasi() {
		return kapasitasBagasi;
	}

	public void setKapasitasBagasi(int kapasitasBagasi) {
		this.kapasitasBagasi = kapasitasBagasi;
	}
}
