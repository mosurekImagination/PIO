/**
 * Klasa, która odnosi się do obiektu towaru z modelu.
 */
public class Towar {

	private int aktualnaIloscTowaru;
	private int id;
	private String nazwa;
	private int kat;
	private int maxRab;
	private int kod;
	private double cenaJn;

	public Towar(int id, String nazwa, int iloscTowaru,double cenaJdn){
		this.id = id;
		this.nazwa = nazwa;
		this.aktualnaIloscTowaru = iloscTowaru;
		this.cenaJn = cenaJdn;
	}
	public Towar(int id, String nazwa, int kod, int kat, double cenaJn, int maxRab, int aktualnaIloscTowaru) {
		this.id=id;
		this.nazwa=nazwa;
		this.kod = kod;
		this.kat = kat;
		this.cenaJn =cenaJn;
		this.maxRab = maxRab;
		this.aktualnaIloscTowaru = aktualnaIloscTowaru;
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