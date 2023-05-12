package sg.edu.nus.iss.task02.src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    public static void main(String[] args) throws IOException {

        String filePath = args[0];

        File directory = new File(filePath);

        if (!directory.exists()) {

            System.out.println("Files not located");

        } else {
            System.out.println("Directory located");
        }

        File ff[] = directory.listFiles();
        System.out.println("The list of available texts are:\n");
        int order = 1;
        for (File f : ff) {
            
            System.out.println(order + ". " + f.getName().replace("_", " ").replace(".txt", ""));
            order += 1;
        }

        String input = "";

        Console cons = System.console();

        while (!input.equals("done")) {

            
            input = cons.readLine("\nPlease select the text you would like to analyze: \n");

            if (input.equals("done")) {
                System.out.println("Bye bye");
            }

            else {

                File toRead = new File(directory + File.separator + input.trim().replace(" ", "_") + ".txt");

                if (!toRead.exists()) {
                    System.out.println("That text does not exist");
                    toRead.createNewFile();

                } else {

                    FileReader fr = new FileReader(toRead);
                    BufferedReader br = new BufferedReader(fr);
                    String store1 = "";
                    String line;

                    List<Nextword> wordPairList = new ArrayList<>();
                    Map<String, List<String>> nextWord = new HashMap<>();
                    List<String> currentW = new ArrayList<>();
                    List<String> allTheWords = new ArrayList<>();
                    Map<String, Float> freq = new HashMap<>();

                    while ((line = br.readLine()) != null) {

                        String readLine1 = line.trim().toLowerCase().replaceAll("\\p{Punct}", " ");
                        String line1[] = readLine1.split("\\s+");

                        for (String w : line1) {
                            allTheWords.add(w);

                            if (!freq.containsKey(w)) {
                                freq.put(w , 1f);
                            }
                        }
                    }   

                    System.out.println(freq.get("chicken"));

                    for (int i = 1; i < allTheWords.size(); i++) {

                        Nextword nw = new Nextword(allTheWords.get(i-1), allTheWords.get(i));
                        wordPairList.add(nw);
                    }

                    for (int j = 0; j < wordPairList.size(); j++) {

                        if (!nextWord.containsKey(wordPairList.get(j).getPrevWord())) {
                            currentW = new ArrayList<>();
                            currentW.add(wordPairList.get(j).getCurrentWord());
                            nextWord.put(wordPairList.get(j).getPrevWord(), currentW);
                        }

                        else if (nextWord.containsKey(wordPairList.get(j).getPrevWord())
                                && !nextWord.get(wordPairList.get(j).getPrevWord())
                                        .contains(wordPairList.get(j).getCurrentWord())) {

                            currentW = nextWord.get(wordPairList.get(j).getPrevWord());
                            currentW.add(wordPairList.get(j).getCurrentWord());

                        }

                        else {
                            
                            currentW = nextWord.get(wordPairList.get(j).getPrevWord());
                            freq.computeIfPresent(wordPairList.get(j).getPrevWord(), (key, value) -> value + 1);

                        }

                    }

                    //System.out.println(nextWord.get("some"));

                    // to check output without getting a headache
                    FileWriter fw = new FileWriter("Output.txt");
                    BufferedWriter bw = new BufferedWriter(fw);
                    Float probs = 1f;
                    Nextword findSize = new Nextword();

                    for (Map.Entry<String, List<String>> entry : nextWord.entrySet()) {
                        bw.write(entry.getKey() + ": \n");
                        for (String word : entry.getValue()) {
                            Float listSize = findSize.findSize(freq, entry.getValue());
                            bw.write("   " + word + " " + Float.toString(freq.get(word)/listSize) + "\n");
                        }

                    }

                    bw.flush();
                    bw.close();
                    fw.close();

                    System.out.println("The next word probabilities for " + input + " are:");
                    for (Map.Entry<String, List<String>> entry : nextWord.entrySet()) {
                        System.out.println(entry.getKey() + ": ");
                        for (String word : entry.getValue()) {
                            Float listSize = findSize.findSize(freq, entry.getValue());
                            System.out.println("   " + word + " " + Float.toString(freq.get(word) / listSize) + "\n");
                        }
                    }
                    
                    br.close();
                }
            }

        }

    }
}
