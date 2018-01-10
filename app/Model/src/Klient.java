public class Klient {

	private int id;
	private String imie;
	private String nazwisko;
	private int nip;
	private String nazwaFirmy;

	public Klient()
	{

	}
	public Klient(String imie, String nazwisko)
	{
		this.imie = imie;
		this.nazwisko = nazwisko;
	}
	public String toString()
	{
		return imie +" "+ nazwisko;
	}

	public String getNazwaFirmy(){
		return nazwaFirmy;
	}

	public int getNip() {
		return nip;
	}
}