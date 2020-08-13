import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

//5
//0 3 13 9 7
//3 0 6 12 10
//13 6 0 9 6
//9 12 9 0 8
//7 10 6 8 0
//
// Result = 32

//6
//0 9 14 2 6 11
//9 0 4 1 7 6
//14 4 0 2 10 9
//2 1 2 0 5 12
//6 7 10 5 0 3
//11 6 9 12 3 0
//
// Result = 23
// Best time was 1034600 ns. With last example, where's result is 23.

/**
 * Classical Traveling Salesman Problem with n! permutations
 */
public class TaskGraph {

    private static int size;
    private static List<List<Integer>> distances = new LinkedList<>();
    private static List<Integer> road = new LinkedList<>();

    private static int minLengthRoad = Integer.MAX_VALUE;

    public static void main(String[] args) {
        init();

        long start = System.nanoTime();

        List<Integer> list = new LinkedList<>();
        list.add(0);
        findShortestWay(list, 0);

        long end = System.nanoTime();

        System.out.println(minLengthRoad);
        System.out.println(road.toString());
        System.out.println("Time = " + (end-start) + " ns.");
    }

    private static void init() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            size = Integer.parseInt(reader.readLine());

            for (int i = 0; i < size; i++) {
                List<Integer> temp = new LinkedList<>();
                String[] strings = reader.readLine().split(" ");
                for (String s : strings) {
                    if (s.isEmpty()) continue;
                    temp.add(Integer.parseInt(s));
                }
                distances.add(temp);
            }
        } catch (IOException ignored) {}
    }

    private static void findShortestWay(List<Integer> currentRoad, int cost) {
        if (currentRoad.size() >= size) {
            cost += distances.get(0).get(currentRoad.get(currentRoad.size()-1));
            if (minLengthRoad > cost) {
                minLengthRoad = cost;
                road.clear();
                road.addAll(currentRoad);
                road.add(0);
            }
            return;
        }

        for (int i = 1; i < size; i++) {
            if (currentRoad.contains(i)) continue;

            cost += distances.get(i).get(currentRoad.get(currentRoad.size()-1));
            currentRoad.add(i);
            findShortestWay(currentRoad, cost);
            currentRoad.remove(currentRoad.size()-1);
            cost -= distances.get(i).get(currentRoad.get(currentRoad.size()-1));
        }
    }
}
