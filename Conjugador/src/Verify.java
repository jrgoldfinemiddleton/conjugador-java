import org.json.JSONArray;

public class Verify {
  private static boolean reflex = false;
  
//accented and special chars
 private static final char aAcuteUp = '\u00C1';
 private static final char aAcute = '\u00E1';
 private static final char aGraveUp = '\u00C0';
 private static final char aGrave = '\u00E0';
 private static final char aTildeUp = '\u00C3';
 private static final char aTilde = '\u00E3';
 private static final char cCedilUp = '\u00C7';
 private static final char cCedil = '\u00E7';
 private static final char eAcuteUp = '\u00C9';
 private static final char eAcute = '\u00E9';
 private static final char eCFlexUp = '\u00CA';
 private static final char eCFlex = '\u00EA';
 private static final char iAcuteUp = '\u00CD';
 private static final char iAcute = '\u00ED';
 private static final char oAcuteUp = '\u00D3';
 private static final char oAcute = '\u00F3';
 private static final char oCFlexUp = '\u00D4';
 private static final char oCFlex = '\u00F4';
 private static final char oTildeUp = '\u00D5';
 private static final char oTilde = '\u00F5';
 private static final char uAcuteUp = '\u00DA';
 private static final char uAcute = '\u00FA';
 private static final char uTremeUp = '\u00DC';
 private static final char uTreme = '\u00FC';

 // various hyphens
 private static final char minus = '\u002D';
 private static final char hyphen = '\u2010';
 private static final char nbHyphen = '\u2011';
 private static final char figDash = '\u2012';
 private static final char enDash = '\u2013';
 private static final char emDash = '\u2014';
 
 // allowed accented characters
 private static final char[] accents = new char[]{
   aAcute, aGrave, aTilde, cCedil, eAcute, eCFlex, iAcute, oAcute,
   oCFlex, oTilde, uAcute, uTreme
   };
 // allowed hyphens
 private static final char[] dashes = new char[]{
   minus, hyphen, nbHyphen, figDash, enDash, emDash
   };
 
  private static String checkInput(String input) {
    if (input.length() < 2) {
      return null;
    }
    int index = 0;
    StringBuilder s = new StringBuilder(input);
    while (index < s.length()) {
      char cur = s.charAt(index);
      if (isValidChar(cur)) { // if char valid, check next char
        index++;
        continue;
      }
      // now to check for any hyphens
      for (char c: dashes) {
        if (s.charAt(index) == c) {
          if (index > 0 && s.charAt(index - 1) == '-') {
            s.deleteCharAt(index--); // remove double hyphens
            break;
          } else {
            s.setCharAt(index, '-'); // standardize hyphens
            break;
          }
        }
      }
      if (s.charAt(index) != '-') { // remove any non-valid char
        s.deleteCharAt(index);
      } else {
        index++;
      }
    }
    
    if (isReflexive(s.toString())) {
      s = new StringBuilder(s.substring(0, s.length() - 3));
      reflex = true;
    }

    if (hasValidEnding(s.toString())) { // confirm the cleaned up
                                        // infinitive has correct form
      return s.toString();
    }
    return null;  // infinitive not valid
  }
  
  private static boolean isReflexive(String s) {
    if (s.length() < 3) {
      return false;
    }
    if (s.substring(s.length() - 3).equals("-se")) {
      return true;
    }
    return false;
  }
  
  private static boolean hasValidEnding(String s) {
    if (s.length() < 2) {
      return false;
    }
    if (s.equals("p" + oCFlex + "r")) {
      return true;
    }
    if (s.length() >= 3 && s.substring(s.length() - 3).equals("por")) {
      return true;
    }
    String end = s.substring(s.length() - 2);
    return end.equals("ar") || end.equals("er") || end.equals("ir");
  }
  
  /**
   * Checks whether a given character is valid in the Portuguese language.
   * @param c the character to validate.
   * @return true if the character is valid, false otherwise.
   */
  private static boolean isValidChar(char c) {
    for (char ch: accents) { // certain accented letters okay
      if (c == ch) {
        return true;
      }
    }
    if (c >= 97 && c <= 122) { // lowercase letters okay
      return true;
    }
    return false;
  }
  
  private static String isValidVerb(String verb) {
    if (verb != null) {
      return verb;
    }
    return null;
  }
  
  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("FAIL: needs one argument.");
      System.out.println("java Verify verb");
    }
    String verb = checkInput(args[0].toLowerCase());
    System.out.println(new JSONArray(new String[]{ verb,
        Boolean.toString(reflex) }));
  }
}