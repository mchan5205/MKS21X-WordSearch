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
      if (args.length > 3){
        System.out.println("use java WordSearch rows cols filename randomSeed answers");
        System.out.println("randomSeed and answers is optional");
      }
      try{
        if (args.length > 4){
            Random y = new Random();
            WordSearch x = new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2], y.nextInt(), false);
          }
          if (args.length > 5){
            WordSearch x = new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]), false);
          }
          if (args.length > 6){
            WordSearch x = new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]), true);
          }
      }
      catch(FileNotFoundException e){
        System.out.println("File not found")
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
        System.out.println("File not found: " + fileName);
        System.exit(1);
      }
      randgen = new Random(randSeed);
      addAllWords();
      if (! answers){
        for (int i = 0; i < data.length; i ++){
          for (int x = 0; x < data[0].length; x ++){
            if (! Character.isLetter(data[i][x])){
              data[i][x] = (char)(randgen.nextInt() % 26 + 'a');
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
          newstr += data[i][x];
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
      newstr += "(seed: " + seed + ")";
      return newstr;
    }

    private boolean addWord(String word, int r, int c, int rowIncrement, int colIncrement){
      if (c + word.length() * colIncrement > data[0].length || c + word.length() * colIncrement < 0 && r + word.length() * rowIncrement > data.length || r + word.length() * rowIncrement < 0){
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
      String next = wordsToAdd.get(randgen.nextInt() % wordsToAdd.size());
      int colInc = randgen.nextInt() % 2;
      int rowInc = randgen.nextInt() % 2;
      int i = 0;
      while (wordsToAdd.size() > 0){
          if (addWord(next, randgen.nextInt() % data.length, randgen.nextInt() % data[0].length, rowInc, colInc)){
            wordsAdded.add(next);
            wordsToAdd.remove(next);
            if (wordsToAdd.size() > 0){
              next = wordsToAdd.get(randgen.nextInt() % wordsToAdd.size());
              colInc = randgen.nextInt() % 2;
              rowInc = randgen.nextInt() % 2;
              i = 0;
            }
          }
          i += 1;
          if (i > 1000){
            if (wordsToAdd.size() > 0){
              next = wordsToAdd.get(randgen.nextInt() % wordsToAdd.size());
              colInc = randgen.nextInt() % 2;
              rowInc = randgen.nextInt() % 2;
              i = 0;
            }
          }
      }
    }
}
