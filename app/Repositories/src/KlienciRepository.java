import java.util.ArrayList;

/**
 * Klasa realizująca połączenie się z obiektami modelu klienta i przechowywaniem ich.
 */
public class KlienciRepository {

	public 	KlienciRepository() {
		
	}
	/**
	 * 
	 * @param nip
	 */
	public void sprawdzCzyIstniejeKlient(String nip) {
		// TODO - implement KlienciRepository.sprawdzCzyIstniejeKlient
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param klient
	 */
	public void przeslijDaneNowegoKlienta(Klient klient) {
		// TODO - implement KlienciRepository.przeslijDaneNowegoKlienta
		throw new UnsupportedOperationException();
	}

    public ArrayList<Klient> getKlienci() {
    	Context context = new Context();
    	context.openDB();
    	KlienciContext kc = new KlienciContext(context);
    	ArrayList<Klient> listaKlientow= kc.getKlientZBazy();
    	context.closeDB();
        return listaKlientow;
    }
}