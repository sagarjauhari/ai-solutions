package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author sagarjauhari@gmail.com
 *
 */
class InputScanner {
    private final BufferedReader br;
    StringTokenizer              tokenizer;

    public InputScanner(InputStream stream) {
        br = new BufferedReader(new InputStreamReader(stream));
    }

    public InputScanner(File file) {
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(br.readLine()," (),.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    public int nextInt() {
        int num;
        try {
            num = Integer.parseInt(next());
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
        return num;
    }
    
    public float nextFloat(){
        float num;
        try{
            num = Float.parseFloat(next());
        }catch(NumberFormatException e){
            throw new RuntimeException(e);
        }
        return num;
    }
    
    public float nextLong(){
        long num;
        try{
            num = Long.parseLong(next());
        }catch(NumberFormatException e){
            throw new RuntimeException(e);
        }
        return num;
    }

    public String nextLine() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken("\n").trim();
    }

}
