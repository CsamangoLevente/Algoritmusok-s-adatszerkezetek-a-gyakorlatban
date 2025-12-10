import java.io.*;
import java.util.*;

public class Main {
    /*
    Adott egy aranyérme értéke: n.
    1. Nem váltod be → akkor az értéke marad n.
    2. Beváltod három másik érmére:
        n/2
        n/3
        n/4
        (egész osztás! tehát pl. 12/5 → 2)
    A beváltott érméket újra be lehet váltani, ugyanilyen szabállyal.
    A cél: maximalizálni a végén a kapott összértéket.
    A programnak minden bemeneti n-re ki kell írnia a maximális összegét.
     */

    //ha egyszer már kiszámoltuk f(n) értékét, tároljuk el egy HashMap-ben
    static Map<Long, Long> map = new HashMap<>();

    static long Coins(long n) {
        //Ha nem lenne itt a rekurzió nem érne véget
        if (n == 0) return 0;

        //ha solve(n) már egyszer lefutott, akkor az eredmény a map-ben van
        //Nem kell újta számolni, tehát idő sporolás
        if (map.containsKey(n))
            return map.get(n);
        //Rekurzívan tovább bontja a számokat, amíg el nem érjük a 0-t
        long exchanged = Coins(n / 2) + Coins(n / 3) +  Coins(n / 4);
        //Két lehetőség
        long result = Math.max(n, exchanged);

        map.put(n, result);
        return result;
    }

    //soronként olvassa a számokat (n)
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        StringBuilder sb = new StringBuilder();

        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) continue;
            long n = Long.parseLong(line.trim());
            sb.append(Coins(n)).append("\n");
        }

        System.out.print(sb);
    }
}
