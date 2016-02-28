import org.json.JSONArray;

public class Tensor {
  
  public static void main(String[] args) {
    if (args.length < 2) {
      System.out.println("FAIL: incorrect number of arguments");
      listArgs();
      return;
    }
    
    String[] tenseNames = new String[Integer.parseInt(args[0])];
    String type = args[1];
    for (int tense = 0; tense < tenseNames.length; tense++) {
      tenseNames[tense] = Verb.getTenseName(tense, type);
    }
    
    if (args.length >= 3) {
      for (int tense = 0; tense < tenseNames.length; tense++) {
        tenseNames[tense] += " [" + args[2];
      }
      
      if (args.length == 4) {
        for (int tense = 0; tense < tenseNames.length; tense++) {
          tenseNames[tense] += ", " + args[3] + ""; 
        }
      }
      
      for (int tense = 0; tense < tenseNames.length; tense++) {
        tenseNames[tense] += "]";
      }
    }
    
    System.out.println(new JSONArray(tenseNames));
  }
  
  private static void listArgs() {
    System.out.println("java Tensor numTenses reg|pass|prog [pronoun1] [pronoun2]");
  }
}