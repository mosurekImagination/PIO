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
	public Klient(int id,String imie, String nazwisko, int nip, String nazwaFirmy) {
		this.id=id;
		this.imie=imie;
		this.nazwisko=nazwisko;
		this.nip=nip;
		this.nazwaFirmy=nazwaFirmy;
	}

	public Klient(int id, String nazwaFirmy) {
		this.id=id;
		this.nazwaFirmy=nazwaFirmy;
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
	public int getId() {
		return id;
	}

}