public class Formater {
    public static String getNipString(int Nip)
    {
        String nip = String.valueOf(Nip);
        StringBuilder stringBuilder = new StringBuilder(nip);
        stringBuilder.insert(3,'-');
        stringBuilder.insert(7,'-');

        return stringBuilder.toString();
    }
}
