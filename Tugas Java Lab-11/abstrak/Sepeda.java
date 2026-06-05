package abstrak;

public class Sepeda extends Kendaraan {
	private boolean isAuto;

	public Sepeda(boolean isAuto) {
		this.isAuto = isAuto;
		setJumlahRoda(2);
	}

	public void jenis() {
		if (isAuto) {
			System.out.println("Sepeda otomatis");
		} else {
			System.out.println("Sepeda kayuh");
		}
	}

	@Override
	public void nyalakan() {
		System.out.println("Sepeda siap dikendarai.");
	}

	public boolean isAuto() {
		return isAuto;
	}

	public void setAuto(boolean auto) {
		this.isAuto = auto;
	}
}
