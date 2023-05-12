package sg.edu.nus.iss.task02.src;

import java.util.List;
import java.util.Map;

public class Nextword {
    
    public String prevWord;
    public String currentWord;

    public String getPrevWord() {
        return prevWord;
    }

    public void setPrevWord(String prevWord) {
        this.prevWord = prevWord;
    }

    public static String storeWord (String word) {

        return word;
    }

    public String getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
    }

    public Nextword(String prevWord, String currentWord) {
        this.prevWord = prevWord;
        this.currentWord = currentWord;
    }

    public Nextword() {

    }

    @Override
    public String toString() {
        return "Nextword [prevWord=" + prevWord + ", currentWord=" + currentWord + "]";
    }

    public float findSize(Map <String, Float> m, List <String> s) {

        float result = 0f;
        for (String items : s) {
            result += m.get(items);
        }
     
        return result;
        
    }
}
