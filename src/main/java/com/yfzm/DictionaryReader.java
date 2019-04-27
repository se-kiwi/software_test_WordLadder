package com.yfzm;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class DictionaryReader {

    private static Set<String> dictionary = new HashSet<String>();

    public static Set<String> getDictioary(){
        FileInputStream in = null;
        try {
            Resource resource = new ClassPathResource("dictionary.txt");
            File file = resource.getFile();
            in = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(reader);

            String line;
            while ((line = br.readLine()) != null) {
                dictionary.add(line.trim());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return dictionary;
    }
}
