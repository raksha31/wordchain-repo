package com.wordchain;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DictionaryMain {

    public static void main(String args[]) {
        System.out.println("Welcome to program for word chain finder");
        System.out.println("Enter the starting word : ");

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String startWord = bufferedReader.readLine();
            System.out.println("Enter the ending word : ");

            String endWord = bufferedReader.readLine();
            List<String> wordChain = new DictionaryMain().findWordChain(startWord, endWord);


            if (wordChain.size() > 0) {
                System.out.println("YES");
                System.out.println(wordChain);
            } else {
                System.out.println("NO");
            }

        } catch (IOException e) {
            System.out.println("Exception while reading input values");
            System.exit(0);
        }

    }

    public ArrayList<String> findWordChain(String start, String end) {

        ArrayList<String> result = new ArrayList<>();
        start = start.toLowerCase();
        end = end.toLowerCase();
        //Handle for empty cases
        if (start.length() == 0 || end.length() == 0) {
			return result;
		}

        //Check if lengths of both start and end are equal before proceeding
        if (start.length() != end.length()) {
            return result;
        }

        //Check for same word at beginning and end
        if(start.equals(end)) {
			 result.add(start);
			 return result;
		}

        //Lengths are equal, so we create Dictionary object which loads words from github repo
        Dictionary dict = new Dictionary();

        //Queue for storage
        LinkedList<WordDetails> queue = new LinkedList<>();
        queue.add(new WordDetails(start, new ArrayList<>()));


        while (!queue.isEmpty()) {
            WordDetails top = queue.remove();
            String word = top.word;

            ArrayList<String> wordsTillNow = new ArrayList<>();
            wordsTillNow.addAll(top.wordList);
            wordsTillNow.add(top.word);

            //If word matches the end word
            if (word.equals(end)) {
                //If result is blank, add the current list to result
                if (result.size() == 0) {
					result = wordsTillNow;
				} else if (wordsTillNow.size() <= result.size()) {
                    result = wordsTillNow;
                }
            }

            char[] currWord = word.toCharArray();

            //Looping over all characters one by one
            for (int i = 0; i < currWord.length; i++) {
                //Replace each character once
                for (char c = 'a'; c <= 'z'; c++) {

                    //Storing the character temporary as we are going to replace it with other characters
                    char charReplaced = currWord[i];

                    //If characters are equal, don't compare
                    if (currWord[i] != c) {
                        currWord[i] = c;
                    }

                    String newWord = new String(currWord);

                    if (dict.contains(newWord)) {

                        //Add current string to queue for comparision
                        queue.add(new WordDetails(newWord, wordsTillNow));
                        //As current word is already under comparision, remove it from dictionary
                        dict.remove(newWord);
                    }

                    //Replacing the original character back in correct place
                    currWord[i] = charReplaced;
                }
            }
        }

        return result;
    }
}