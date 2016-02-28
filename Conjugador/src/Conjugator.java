import org.json.JSONArray;

public class Conjugator {
  
  private static final String[] DOPs = {
    "me", "te", "o", "a", "nos", "vos", "os", "as" };
  private static final String[] IOPs = {
    "me", "te", "lhe", "nos", "vos", "lhes" };
  
  // Currently this method outputs a two-dimensional array containing every
  // conjugation for the given combination of BP/EP and AO/no-AO.
  // 
  // Reason this may be good: easier to parse the JSON perhaps.
  // Reason this may be bad: each time the user changes BP to EP or AO to no-AO
  // or vice-versa, the whole conjugation process will have to run again.
  // 
  // Alternative is to output a three-dimensional array containing every
  // conjugation for all four combinations of BP/EP and AO/no-AO.
  public static void main(String[] args) {
    if (args.length < 4) {
      System.out.println("FAIL: incorrect number of arguments");
      listArgs();
      return;
    }
    // assumes verb was already validated by Validator
    if (!args[1].equals("BP") && !args[1].equals("EP")) {
      System.out.println("FAIL: arg 2 incorrect");
      listArgs();
      return;
    }
    if (!args[2].equals("AO") && !args[2].equals("!AO")) {
      System.out.println("FAIL: arg 3 incorrect");
      listArgs();
      return;
    }
    
    Verb v = new Verb(args[0]);
    int comb = getComb(args[1], args[2]);
    if (args.length == 4) {
      switch (args[3]) {
        case "reg": // if verb has "-se" attached it should automatically
                     // be conjugated reflexively <-- remove this feature
                     // but then we will have to apply the reflexive
                     // manually
          System.out.println(new JSONArray(Verb.conjVariant(
              v.conjAll(), comb)));
          return;
        case "pass":
          System.out.println(
              new JSONArray(Verb.conjVariant(
                  v.conjAllPassive(), comb)));
          return;
        case "prog":
          System.out.println(
              new JSONArray(Verb.conjVariant(
                  v.conjAllProgressive(), comb)));
          //System.out.println("PROGRESSIVE WILL GO HERE.");
          return;
        default:
          System.out.println("FAIL: arg 4 incorrect");
          listArgs();
          return;
      }
    }
    if (args.length == 5) { // there is only one pronoun
      if (validateDOP(args[4]) || validateIOP(args[4])) {
        switch (args[3]) {
          case "reg":
            System.out.println(new JSONArray(Verb.conjVariant(
                v.conjAllWithPronouns(v.conjAll(), args[4], null), comb)));
            return;
          case "pass":
            System.out.println(
                new JSONArray(Verb.conjVariant(
                    v.conjAllPassiveWithPronouns(args[4]), comb)));
            return;
          case "prog":
            System.out.println(
                new JSONArray(Verb.conjVariant(
                    v.conjAllProgressiveWithPronouns(args[4], null), comb)));
            return;
          default:
            System.out.println("FAIL: arg 4 incorrect");
            listArgs();
            return;
        }
      } else if (validateROP(args[4])) {
        switch (args[3]) {
          case "reg":
            System.out.println(new JSONArray(Verb.conjVariant(
                v.conjAllReflex(v.conjAll()), comb)));
            return;
          case "pass":
            System.out.println("FAIL: cannot use reflexive pronoun \"se\" with"
                + " passive voice.");
            return;
          case "prog":
            System.out.println(
                new JSONArray(Verb.conjVariant(
                    v.conjAllProgressiveReflexive(), comb)));
            return;
          default:
            System.out.println("FAIL: arg 4 incorrect");
            listArgs();
            return;
        }
      } else {
        System.out.println("FAIL: arg 5 invalid");
        System.out.print("Valid pronouns: se, me, te, lhe, nos, vos, lhes, ");
        System.out.println("o, a, os, as");
      }
    }
    if (args.length == 6) { // there are two pronouns
      if (validateIOP(args[4]) && validateDOP(args[5])) {
        switch (args[3]) {
          case "reg":
            System.out.println(
                new JSONArray(Verb.conjVariant(
                    v.conjAllWithPronouns(
                        v.conjAll(), args[4], args[5]), comb)));
            return;
          case "pass":
            System.out.println("FAIL: cannot use multiple pronouns with"
                + " passive voice.");
            return;
          case "prog":
            System.out.println(
                new JSONArray(Verb.conjVariant(
                    v.conjAllProgressiveWithPronouns(args[4], args[5]), comb)));
            return;
          default:
            System.out.println("FAIL: arg 4 incorrect");
            listArgs();
            return;
        }
      } else {
        System.out.println("FAIL: invalid pronoun combination");
        System.out.println("Valid pronouns for arg 5: "
            + "me, te, lhe, nos, vos, lhes");
        System.out.println("Valid pronouns for arg 6: "
            + "o, a, os, as");
      }
    }
  }
  
  private static void listArgs() {
    System.out.println("java Conjugator verb BP|EP AO|!AO "
        + "reg|pass|prog [pronoun1] [pronoun2]");
  }
  
  private static boolean validateDOP(String s) {
    for (String pro: DOPs) {
      if (s.equals(pro)) {
        return true;
      }
    }
    return false;
  }
  
  private static boolean validateIOP(String s) {
    for (String pro: IOPs) {
      if (s.equals(pro)) {
        return true;
      }
    }
    return false;
  }
  
  private static boolean validateROP(String s) {
    return s.equals("se");
  }
  
  private static int getComb(String langVar, String AO) {
    if (langVar.equals("BP")) {
      if (AO.equals("!AO")) {
        return 0;
      } else {
        return 1;
      }
    } else {
      if (AO.equals("!AO")) {
        return 2;
      } else {
        return 3;
      }
    }
  }
  
}
