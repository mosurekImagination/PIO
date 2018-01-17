/**
 * Klasa, ktora reprezentuje obiekt towaru z modelu.
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

	/**
	 * @return zwraca aktualna ilosc towaru w magazynie
	 */
	public int getIlosc(){
		return aktualnaIloscTowaru;
	}

	/**
	 * @return zwraca cene jednej sztuki towaru
	 */
	public double getCenaJn() {
		return cenaJn;
	}

	/**
	 * @return zwraca id towaru
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return zwraca nazwe towaru
	 */
	public String getNazwa()
	{
		return nazwa;
	}
}