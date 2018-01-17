/**
 * Klasa, odpowiadajaca za formatowanie wartosci.
 */
public class Formater {

    /**
     * @param Nip - wartos nip ktora ma byc sformatowana
     * @return zwraca nip w postaci xxx-xxx-xxx-xx
     */
    public static String getNipString(int Nip)
    {
        String nip = String.valueOf(Nip);
        StringBuilder stringBuilder = new StringBuilder(nip);
        stringBuilder.insert(3,'-');
        stringBuilder.insert(7,'-');
        stringBuilder.insert(10,'-');

        return stringBuilder.toString();
    }

    /**
     * @param cena - cena, ktora ma zostac sformatowana
     * @return zwraca cene z dokladnoscia do dwoch miejsc po przecinku
     */
    public static String formatujCene(double cena)
    {
       return String.format("%.2f zl", cena);
    }
}