package abstrak;

public abstract class BangunDatar {
	private int jumlahSisi;
	private int x;
	private int y;

	public BangunDatar(int sisi) {
		this.jumlahSisi = sisi;
	}

	public void pindahkan(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public abstract void draw();
	public abstract void resize();

	public int getJumlahSisi() {
		return this.jumlahSisi;
	}

	public abstract double getLuas();
	public abstract double getKeliling();

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
