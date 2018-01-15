public class Formater {
    public static String getNipString(int Nip)
    {
        String nip = String.valueOf(Nip);
        StringBuilder stringBuilder = new StringBuilder(nip);
        stringBuilder.insert(3,'-');
        stringBuilder.insert(7,'-');
        stringBuilder.insert(10,'-');

        return stringBuilder.toString();
    }

    public static String formatujCene(double cena)
    {
       return String.format("%.2f zl", cena);
    }
}