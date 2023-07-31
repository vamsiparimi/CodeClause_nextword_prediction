import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class nextwordpre {

    private static Map<String, List<String>> wordMap;

    public static void trainModel(String[] trainingData) {
        wordMap = new HashMap<>();

        for (String sentence : trainingData) {
            String[] words = sentence.split(" ");
            for (int i = 0; i < words.length - 1; i++) {
                String currentWord = words[i];
                String nextWord = words[i + 1];

                if (!wordMap.containsKey(currentWord)) {
                    wordMap.put(currentWord, new ArrayList<>());
                }
                wordMap.get(currentWord).add(nextWord);
            }
        }
    }

    public static String predictNextWord(String inputWord) {
        if (wordMap.containsKey(inputWord)) {
            List<String> possibleWords = wordMap.get(inputWord);
            int randomIndex = (int) (Math.random() * possibleWords.size());
            return possibleWords.get(randomIndex);
        } else {
            return "";
        }
    }

    public static void main(String[] args) {
        // Training data
        String[] trainingData = {
                "I love coding",
                "Java is a programming language",
                "java development",
                "Programming is fun"
        };

        // Train the model
        trainModel(trainingData);

        // User input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a word: ");
        String inputWord = scanner.nextLine();

        // Predict the next word
        String nextWord = predictNextWord(inputWord);

        // Output
        if (!nextWord.isEmpty()) {
            System.out.println("Next word prediction for \"" + inputWord + "\": " + nextWord);
        } else {
            System.out.println("No prediction available for \"" + inputWord + "\"");
        }
        scanner.close();
    }
}
