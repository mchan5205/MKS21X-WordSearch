import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.File;

public class WordSearch{
    private char[][] data;
    private int seed;
    private Random randgen;
    private ArrayList<String> wordsToAdd;
    private ArrayList<String> wordsAdded;

    public static void main(String[] args){
      if (args.length < 3){
        System.out.println("use java WordSearch rows cols filename [randomSeed [answers]]");
        System.exit(1);
      }
      try{
        if (Integer.parseInt(args[0]) < 1 || Integer.parseInt(args[1]) < 1){
          throw new IllegalArgumentException();
        }
        if (args.length == 3){
          Random y = new Random();
          int row = Integer.parseInt(args[0]);
          int col = Integer.parseInt(args[1]);
          String filename = args[2];
          WordSearch z = new WordSearch(row, col, filename, y.nextInt() % 5001 + 5000, false);
          System.out.println(z);
        }
        if (args.length == 4){
          if (Integer.parseInt(args[3]) > 10000 || Integer.parseInt(args[3]) < 0){
            throw new IllegalArgumentException();
          }
          Random y = new Random();
          int row = Integer.parseInt(args[0]);
          int col = Integer.parseInt(args[1]);
          String filename = args[2];
          int ran = Integer.parseInt(args[3]);
          WordSearch z = new WordSearch(row, col, filename, ran, false);
          System.out.println(z);
        }
        if (args.length == 5){
          if (Integer.parseInt(args[3]) > 10000 || Integer.parseInt(args[3]) < 0){
            throw new IllegalArgumentException();
          }
          Random y = new Random();
          int row = Integer.parseInt(args[0]);
          int col = Integer.parseInt(args[1]);
          String filename = args[2];
          int ran = Integer.parseInt(args[3]);
          WordSearch z;
          if (! args[4].equals("key")){
            z = new WordSearch(row, col, filename, ran, false);
          }
          else{
            z = new WordSearch(row, col, filename, ran, true);
          }
          System.out.println(z);
        }
      }
      catch(FileNotFoundException e){
        System.out.println("usage: java WordSearch [rows cols filename [randomSeed [answers]]]");
        System.exit(1);
      }
      catch(NumberFormatException e){
        System.out.println("usage: java WordSearch [rows cols filename [randomSeed [answers]]]");
        System.exit(1);
      }
      catch(IllegalArgumentException e){
        System.out.println("usage: java WordSearch [rows cols filename [randomSeed [answers]]]");
        System.exit(1);
      }
    }

    public WordSearch(int rows, int cols, String fileName, int randSeed, boolean answers) throws FileNotFoundException{
      data = new char[rows][cols];
      wordsToAdd = new ArrayList<>();
      wordsAdded = new ArrayList<>();
      seed = randSeed;
      clear();
      try{
        File f = new File(fileName);
        Scanner words = new Scanner(f);
        while(words.hasNext()){
          wordsToAdd.add(words.nextLine());
        }
      }
      catch(FileNotFoundException e){
        System.out.println("usage: java WordSearch [rows cols filename [randomSeed [answers]]]");
        System.exit(1);
      }
      randgen = new Random(randSeed);
      addAllWords();
      if (! answers){
        for (int i = 0; i < data.length; i ++){
          for (int x = 0; x < data[0].length; x ++){
            if (! Character.isLetter(data[i][x])){
              data[i][x] = (char)(Math.abs(randgen.nextInt()) % 26 + 'A');
            }
          }
        }
      }
    }

    private void clear(){
      for (int i = 0; i < data.length; i ++){
        for (int x = 0; x < data[i].length; x ++){
          data[i][x] = '_';
         }
      }
    }

    public String toString(){
      String newstr = "";
      for (int i = 0; i < data.length; i ++){
        newstr += "|";
        for (int x = 0; x < data[i].length; x ++){
          if (Character.isLetter(data[i][x])){
            newstr += data[i][x];
          }
          else{
            newstr += " ";
          }
          if (x < data[i].length - 1){
            newstr += " ";
          }
        }
        newstr += "|\n";
      }
      newstr += "Words: ";
      for (int i = 0; i < wordsAdded.size(); i ++){
        newstr += wordsAdded.get(i);
        if (i < wordsAdded.size() - 1){
          newstr += ", ";
        }
      }
      newstr += " (seed: " + seed + ")";
      return newstr;
    }

    private boolean addWord(String word, int r, int c, int rowIncrement, int colIncrement){
      if (c + word.length() * colIncrement > data[0].length || c + word.length() * colIncrement < 0 || r + word.length() * rowIncrement > data.length || r + word.length() * rowIncrement < 0){
        return false;
      }
      if (rowIncrement == 0 && colIncrement == 0){
        return false;
      }
      for (int i = 0; i < word.length(); i ++){
        if (Character.isLetter(data[r + rowIncrement * i][c + colIncrement * i]) && (! (data[r + rowIncrement * i][c + colIncrement * i] == word.charAt(i)))){
          return false;
        }
      }
      for (int i = 0; i < word.length(); i ++){
        data[r + rowIncrement * i][c + colIncrement * i] = word.charAt(i);
      }
      return true;
    }
    private void addAllWords(){
      String next = wordsToAdd.get(Math.abs(randgen.nextInt() % wordsToAdd.size()));
      int colInc = randgen.nextInt() % 2;
      int rowInc = randgen.nextInt() % 2;
      int i = 0;
      int x = 0;
      while (wordsToAdd.size() > 0){
          if (addWord(next, Math.abs(randgen.nextInt() % data.length), Math.abs(randgen.nextInt() % data[0].length), rowInc, colInc)){
            wordsAdded.add(next);
            wordsToAdd.remove(next);
            if (wordsToAdd.size() > 0){
              next = wordsToAdd.get(Math.abs(randgen.nextInt() % wordsToAdd.size()));
              colInc = randgen.nextInt() % 2;
              rowInc = randgen.nextInt() % 2;
              i = 0;
              x = 0;
            }
          }
          i += 1;
          if (i > 1000 && x > 50){
            wordsToAdd.remove(next);
            if (wordsToAdd.size() > 0){
              next = wordsToAdd.get(Math.abs(randgen.nextInt() % wordsToAdd.size()));
              colInc = randgen.nextInt() % 2;
              rowInc = randgen.nextInt() % 2;
              i = 0;
              x = 0;
            }
          }
          if (i > 1000){
            colInc = randgen.nextInt() % 2;
            rowInc = randgen.nextInt() % 2;
            x += 1;
            i = 0;
          }
      }
    }
}
