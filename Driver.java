import java.util.Random;
public class Driver{
  public static void main(String[] args){
    if (args.length > 3){
      System.out.println("use java WordSearch rows cols filename randomSeed answers");
      System.out.println("randomSeed and answers is optional");
    }
    if (args.length > 4){
      Random y = new Random();
      WordSearch x = new WordSearch(args[0], args[1], args[2], y.nextInt(), false);
    }
    if (args.length > 5){
      WordSearch x = new WordSearch(args[0], args[1], args[2], args[3], false);
    }
    if (args.length > 6){
      WordSearch x = new WordSearch(args[0], args[1], args[2], args[3], args[4]);
    }
  }
}
