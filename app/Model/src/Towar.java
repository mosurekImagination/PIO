public class Towar {

	private int aktualnaIloscTowaru;
	private int id;
	private String nazwa;
	private int kod;
	private int cenaJn;

	public Towar(int id, String nazwa, int iloscTowaru){
		this.id = id;
		this.nazwa = nazwa;
		this.aktualnaIloscTowaru = iloscTowaru;
	}

	public int getIlosc(){
		return aktualnaIloscTowaru;
	}

	public int getCenaJn() {
		return cenaJn;
	}

	public int getId() {
		return id;
	}
}