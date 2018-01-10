/**
 * Created by Asus on 2018-01-09.
 */
public class Walidator {

    public static boolean czyDobraIlosc(String ilosc){
        if(isInteger(ilosc)){
            return Integer.parseInt(ilosc) > 0;
        }
        else {
            return false;
        }
    }

    public static boolean czyDobryRabat(String rabat){
        if(isInteger(rabat)){
            int intRabat = Integer.parseInt(rabat);
            return intRabat >= 0 && intRabat < 100;
        }
        else {
            return false;
        }
    }

    public static boolean isInteger(String s) {
        return isInteger(s,10);
    }

    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }
}
