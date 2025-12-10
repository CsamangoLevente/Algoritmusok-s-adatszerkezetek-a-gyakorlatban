import java.io.*;
import java.util.*;

public class BFS_Shortest_Reach_in_a_Graph {

    public static void main(String[] args) {
        /* Adott egy nem irányitott gráf n csúcsal és mélel,
        ahol minden él ugyananyiba kerül azaz 6 egységbe.
        Van egy ki induló csúcsunk. Ki kell írni a többi csúcsba a
        legrövidebb távolságot. Ha egy csúcs nem elérhető, akkor oda -1
        a távolsáag.

        A gráf alapvetően nem sulyozott, mert az összes él súlya azonos (6).
        A legrövidebb út hossza kell, amit úgy tudunk megnézni hány élből áll az út.
        A kód először a kezdő pontól 1 lépésre lévő csúcsokat nézi,
        aztán 2 lépésre lévőket.

        Tehát akód így müködik:
        BFS → szintenként terjed → legrövidebb utak száma élekben.
Utána   megszorozzuk 6-tal.
         */

        Scanner sc = new Scanner(System.in);

        int q = sc.nextInt();

        //Több feladat elvégzése is lehetséges
        while (q-- > 0) {

            int n = sc.nextInt();
            int m = sc.nextInt();

            /*
            Gráf ábrázolása, azaz szomszédsági lista
             */
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            //Élek beolvasása és a gráf felépítése
            for (int i = 0; i < m; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                //Ez építi fel a szomszédsági listát
                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            int start = sc.nextInt(); //Kezdő csúcs beolvasása

            int[] dist = bfs(graph, n, start); //A bfs fügvény meghívása

            //Ki íratás, a ki indulát nem írjuk ki
            for (int i = 1; i <= n; i++) {
                if (i == start) continue;
                System.out.print(dist[i] + " ");
            }
            System.out.println();
        }
        sc.close();
    }


    private static int[] bfs(List<List<Integer>> graph, int n, int start) {
        //Távolság tömb, kezdetbe minden csúcs -1, ami azt jelenti, hogy még nem értük el
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);

        /*
        Sor BFS-hez(Breadth-First Search, szélességi keresés)
         */
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        dist[start] = 0;

        //BFS lépésről lépésre
        while (!queue.isEmpty()) { //Amíg a sor nem üres
            int node = queue.poll(); // kiveszünk egy csúcsot

            for (int next : graph.get(node)) {
                if (dist[next] == -1) { // ha még nem láttuk
                    dist[next] = dist[node] + 6; // távolsága: szülő + 6
                    queue.add(next); // betesszük a sorba
                }
            }
        }
        //A legrövidebb távolságot adja vissza
        return dist;
    }
}
