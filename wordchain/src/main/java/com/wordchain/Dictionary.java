package com.wordchain;
import org.apache.commons.io.FileUtils;

import java.net.*;
import java.io.*;
import java.util.HashSet;
import java.util.*;

public class Dictionary
{
    private Set<String> wordsSet=new HashSet<>();

    public List<String> getAllWords()
    {
        List<String> words=new ArrayList<>();
        words.addAll(wordsSet);
        return words;
    }


    public Dictionary()
    {
        try {
            String urlString = "https://raw.githubusercontent.com/dwyl/english-words/master/words.txt";

            // create the url
            URL url = new URL(urlString);

            File f=new File("words.txt");
            FileUtils.copyURLToFile(url,f);

            FileReader fileReader = new FileReader(f);
            // open the url stream, wrap it an a few "readers"
            BufferedReader reader = new BufferedReader(fileReader);

            // write the output to stdout
            String line;
            while ((line = reader.readLine()) != null) {
                wordsSet.add(line.toLowerCase());
            }

            // close our reader
            reader.close();
            fileReader.close();



        }catch(IOException e)
        {
            System.out.println("Exception");
            e.printStackTrace();
        }

    }

    public boolean contains(String word)
    {
        return wordsSet.contains(word.toLowerCase());
    }

    public void remove(String word)
    {
        wordsSet.remove(word);
    }

}