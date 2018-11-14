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

    public WordSearch(int rows,int cols){
      data = new char[rows][cols];
      clear();
    }

    public WordSearch(int rows, int cols, String fileName) throws FileNotFoundException{
      data = new char[rows][cols];
      wordsToAdd = new ArrayList<>();
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
      return newstr;
    }


    public boolean addWordHorizontal(String word,int row, int col){
      if (word.length() + col > data[0].length){
        return false;
      }
      for (int i = 0; i < word.length(); i ++){
        if (Character.isLetter(data[row][col + i]) && (! (data[row][col + i] == word.charAt(i)))){
          return false;
        }
      }
      for (int i = 0; i < word.length(); i ++){
        data[row][col + i] = word.charAt(i);
      }
      return true;
    }


    public boolean addWordVertical(String word,int row, int col){
      if (word.length() + row > data.length){
        return false;
      }
      for (int i = 0; i < word.length(); i ++){
        if (Character.isLetter(data[row + i][col]) && (! (data[row + i][col] == word.charAt(i)))){
          return false;
        }
      }
      for (int i = 0; i < word.length(); i ++){
        data[row + i][col] = word.charAt(i);
      }
      return true;
    }
    public boolean addWordDiagonal(String word, int row, int col){
      if (word.length() + row > data.length || word.length() + col > data[0].length){
        return false;
      }
      for (int i = 0; i < word.length(); i ++){
        if (Character.isLetter(data[row + i][col + i]) && (! (data[row + i][col + i] == word.charAt(i)))){
          return false;
        }
      }
      for (int i = 0; i < word.length(); i ++){
        data[row + i][col + i] = word.charAt(i);
      }
      return true;
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

    }
}
