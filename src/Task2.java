import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    private static int n;
    private static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        init();
        printPermutations();
    }

    private static void init() {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] numbers = reader.readLine().split(" ");
            n = numbers.length;

            for (String s : numbers)
                list.add(Integer.parseInt(s));

        } catch (IOException e) {}
    }

    private static void printPermutations() {
        int m = n-1;

        System.out.print(list.get(m) + " ");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                for (int k = 0; k < n; k++) {
                    if (k == j || k == i) continue;

                    System.out.print(list.get(i) + " ");
                    System.out.print(list.get(j) + " ");
                    System.out.println(list.get(k));
                }
            }
        }
    }
}
