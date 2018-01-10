public class Towar {

	private int aktualnaIloscTowaru;
	private int id;
	private String nazwa;
	private int kod;
	private double cenaJn;

	public Towar(int id, String nazwa, int iloscTowaru,double cenaJdn){
		this.id = id;
		this.nazwa = nazwa;
		this.aktualnaIloscTowaru = iloscTowaru;
		this.cenaJn = cenaJdn;
	}

	public int getIlosc(){
		return aktualnaIloscTowaru;
	}

	public double getCenaJn() {
		return cenaJn;
	}

	public int getId() {
		return id;
	}

	public String getNazwa()
	{
		return nazwa;
	}
}