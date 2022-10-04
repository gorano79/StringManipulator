import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StringManipulator {
    enum Action {
        REVERSE,
        UNIQUE_COUNT,
        WHITESPACE,
        PALINDROME,
        CAPITALIZE,
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter path to file:");
        String path = sc.nextLine();

        ArrayList<Pair<String, String>> keyValuePairs = readFromFile(path);

        for (Pair<String, String> pair : keyValuePairs) {
            switch (Action.valueOf(pair.key)) {
                case REVERSE:
                    print(pair.value, Action.REVERSE.name(), reverse(pair.value));
                    break;
                case UNIQUE_COUNT:
                    print(pair.value, Action.UNIQUE_COUNT.name(), countUnique(pair.value));
                    break;
                case WHITESPACE:
                    print(pair.value, Action.WHITESPACE.name(), removeWhitespace(pair.value));
                    break;
                case PALINDROME:
                    print(pair.value, Action.PALINDROME.name(), isPalindrome(pair.value));
                    break;
                case CAPITALIZE:
                    print(pair.value, Action.CAPITALIZE.name(), capitalize(pair.value));
                    break;
                default:
                    System.out.println("Action [" + pair.key +"] is not supported");
                    break;
            }
        }
    }
    private static String capitalize(String word) {
        return Character.toUpperCase(word.charAt(0)) + word.substring(1);
    }

    private static boolean isPalindrome(String word) {
        return word.equals(reverseString(word));
    }

    private static String removeWhitespace(String word) {
        return word.replaceAll("\\s", "");
    }

    private static long countUnique(String word) {
        return word.chars().distinct().count();
    }

    private static String reverse(String word) {
        return reverseString(word);
    }

    private static String reverseString(String word) {
        char[] ch = word.toCharArray();
        StringBuilder reverse = new StringBuilder();
        for (int i = ch.length - 1; i >= 0; i--) {
            reverse.append(ch[i]);
        }
        return reverse.toString();
    }

    private static <T> void print(String word, String action, T result) {
        System.out.println("Text: [" + word + "] ran Action: [" + action + "] with result: [" + result + "]");
    }

    private static ArrayList<Pair<String, String>> readFromFile(String filePath) {
        ArrayList<Pair<String, String>> list = new ArrayList<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while ((line = reader.readLine()) != null) {
                String[] keyValuePair = line.split(":", 2);
                if (keyValuePair.length > 1) {
                    String key = keyValuePair[0];
                    String value = keyValuePair[1];
                    list.add(new Pair<>(key, value));
                } else {
                    System.out.println("No Key:Value found in line, ignoring: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println(("Exception when reading from file: " + e));
        }
        return list;
    }
    private static class Pair<T, U> {
        public final T key;
        public final U value;

        public Pair(T key, U value) {
            this.key = key;
            this.value = value;
        }
    }
}

