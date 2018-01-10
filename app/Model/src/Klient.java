public class Klient {

	private int id;
	private String imie;
	private String nazwisko;
	private int nip;
	private String nazwaFirmy;

	public Klient()
	{

	}
	public Klient(String nazwaFirmy, int nip)
	{
		this.nazwaFirmy = nazwaFirmy;
		this.nip = nip;
	}

	public String toString()
	{
		return nazwaFirmy +" "+ nip;
	}

	public String getNazwaFirmy(){
		return nazwaFirmy;
	}

	public int getNip() {
		return nip;
	}

}