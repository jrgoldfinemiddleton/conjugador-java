
/**
 * Returns the complete or partial conjugation for a Portuguese infinitive
 * verb.
 * 
 * @author Jason Goldfine-Middleton
 *
 */
class Tense {

  private static final String[] infTerm = { "ar", "er", "ir" };
  private static final String[] gerund = { "ando", "endo", "indo" };
  private static final String[] pastPart = { "ado", "ido", "ido" };
  
  // -ar verbs
  private static final String[] arPresIndic = { "o", "as", "a", "amos", "ais",
      "am" };
  private static final String[] arImpIndic = { "ava", "avas", "ava", "avamos",
      "avais", "avam" };
  private static final String[] arPastIndic = { "ei", "aste", "ou", "\u00E1mos",
      "astes", "aram" };
  private static final String[] arFutIndic = { "arei", "ar\u00E1s", "ar\u00E1",
      "aremos", "areis", "ar\u00E3o" };
  private static final String[] arPluIndic = { "ara", "aras", "ara", "\u00E1ramos", "\u00E1reis",
      "aram" };
  private static final String[] arPresSubj = { "e", "es", "e", "emos", "eis",
      "em" };
  private static final String[] arImpSubj = { "asse", "asses", "asse", 
      "\u00E1ssemos", "\u00E1sseis", "assem" };
  private static final String[] arFutSubj = { "ar", "ares", "ar", "armos",
      "ardes", "arem" };
  private static final String[] arPresCond = { "aria", "arias", "aria",
      "ariamos", "arieis", "ariam" };
  private static final String[] arPersInf = { "ar", "ares", "ar", "armos",
      "ardes", "arem" };
  private static final String[] arPresImp = { "", "a", "e", "emos", "ai", "em" };
  
  // -er verbs
  private static final String[] erPresIndic = { "o", "es", "e", "emos", "eis",
      "em" };
  private static final String[] erImpIndic = { "ia", "ias", "ia", "\u00EDamos",
      "\u00EDais", "\u00EDam" };
  private static final String[] erPastIndic = { "\u00ED", "este", "eu",
      "\u00E9mos", "estes", "eram" };
  private static final String[] erFutIndic = { "erei", "er\u00E1s", "er\u00E1",
      "eremos", "ereis", "er\u00E3o" };
  private static final String[] erPluIndic = { "era", "eras", "era",
      "\u00E9ramos", "\u00E9reis", "eram" };
  private static final String[] erPresSubj = { "a", "as", "a", "amos", "ais",
      "am" };
  private static final String[] erImpSubj = { "esse", "esses", "esse",
      "\u00E9ssemos", "\u00E9sseis", "essem" };
  private static final String[] erFutSubj = { "er", "eres", "er", "ermos",
      "erdes", "erem" };
  private static final String[] erPresCond = { "er\u00EDa", "er\u00EDas",
      "er\u00EDa", "eriamos", "erieis", "ar\u00EDam" };
  private static final String[] erPersInf = { "er", "eres", "er", "ermos",
      "erdes", "erem" };
  private static final String[] erPresImp = { "", "e", "a", "amos", "ei", "am" };
  
  // -ir verbs
  private static final String[] irPresIndic = { "o", "es", "e", "imos", "is",
      "em" };
  private static final String[] irImpIndic = { "ia", "ias", "ia", "\u00EDamos",
      "\u00EDais", "\u00EDam" };
  private static final String[] irPastIndic = { "\u00ED", "iste", "iu",
      "\u00EDmos", "istes", "iram" };
  private static final String[] irFutIndic = { "irei", "ir\u00E1s", "ir\u00E1",
      "iremos", "ireis", "ir\u00E3o" };
  private static final String[] irPluIndic = { "ira", "iras", "ira",
      "\u00EDramos", "\u00EDreis", "iram" };
  private static final String[] irPresSubj = { "a", "as", "a", "amos", "ais",
      "am" };
  private static final String[] irImpSubj = { "isse", "isses", "isse", "issemos",
      "\u00EDsseis", "issem" };
  private static final String[] irFutSubj = { "ir", "ires", "ir", "irmos",
      "irdes", "irem" };
  private static final String[] irPresCond = { "ir\u00EDa", "ir\u00EDas",
      "ir\u00EDa", "iriamos", "irieis", "ir\u00EDam" };
  private static final String[] irPersInf = { "ir", "ires", "ir", "irmos",
      "irdes", "irem" };
  private static final String[] irPresImp = { "", "e", "a", "amos", "i", "am" };
  
  private static String conjugate(String verb, String tense) throws Exception {
    String stem = verb.substring(0, verb.length() - 2);
    String end = verb.substring(verb.length() - 2);
    if (!end.equals("ar") && !end.equals("er") && !end.equals("ir")) {
      throw new Exception(); // ImproperInfinitiveException
    }
    String out = verb;
      switch (tense) {
        case "PresIndic" :
          return presIndic(stem, end, true);
        case "ImpIndic" :
          return impIndic(stem, end, true);
        case "PastIndic" :
          return pastIndic(stem, end, true);
        case "FutIndic" :
          return futIndic(stem, end, true);
        case "PluIndic" :
          return pluIndic(stem, end, true);
        case "PresSubj" :
          return presSubj(stem, end, true);
        case "ImpSubj" :
          return impSubj(stem, end, true);
        case "FutSubj" :
          return futSubj(stem, end, true);
        case "PresCond" :
          return presCond(stem, end, true);
        case "PersInf" :
          return persInf(stem, end, true);
        case "PresImp" :
          return presImp(stem, end, true);
        default :
          return "An error has occurred.  No correct tense selected.";
      }
      //out += "\n";
    //return out;
  }
  
  private static String presIndic(String stem, String end, boolean chStem) {
    String out = stem + end + "\tPresent Indicative\n\n";
    for (int i = 0; i < 6; i++) {
      out += stem;
      if (end.equals("ar")) {
        out += arPresIndic[i];
      } else if (end.equals("er")) {
        out += erPresIndic[i];
      } else {
        out += irPresIndic[i];
      }
      out += "\n";
    }
    return out;
  }
  
  private static String impIndic(String stem, String end, boolean chStem) {
    String out = stem + end + "\tImperfect Indicative\n\n";
    for (int i = 0; i < 6; i++) {
      out += stem;
      if (end.equals("ar")) {
        out += arImpIndic[i];
      } else if (end.equals("er")) {
        out += erImpIndic[i];
      } else {
        out += irImpIndic[i];
      }
      out += "\n";
    }
    return out;
  }
  
  private static String pastIndic(String stem, String end, boolean chStem) {
    String out = stem + end + "\tPast Indicative\n\n";
    for (int i = 0; i < 6; i++) {
      out += stem;
      if (end.equals("ar")) {
        out += arPastIndic[i];
      } else if (end.equals("er")) {
        out += erPastIndic[i];
      } else {
        out += irPastIndic[i];
      }
      out += "\n";
    }
    return out;
  }
  
  private static String futIndic(String stem, String end, boolean chStem) {
    String out = stem + end + "\tFuture Indicative\n\n";
    for (int i = 0; i < 6; i++) {
      out += stem;
      if (end.equals("ar")) {
        out += arFutIndic[i];
      } else if (end.equals("er")) {
        out += erFutIndic[i];
      } else {
        out += irFutIndic[i];
      }
      out += "\n";
    }
    return out;
  }
  
  private static String pluIndic(String stem, String end, boolean chStem) {
    String out = stem + end + "\tPluperfect Indicative\n\n";
    for (int i = 0; i < 6; i++) {
      out += stem;
      if (end.equals("ar")) {
        out += arPluIndic[i];
      } else if (end.equals("er")) {
        out += erPluIndic[i];
      } else {
        out += irPluIndic[i];
      }
      out += "\n";
    }
    return out;
  }
  
  private static String presSubj(String stem, String end, boolean chStem) {
    String out = stem + end + "\tPresent Subjunctive\n\n";
    for (int i = 0; i < 6; i++) {
      out += stem;
      if (end.equals("ar")) {
        out += arPresSubj[i];
      } else if (end.equals("er")) {
        out += erPresSubj[i];
      } else {
        out += irPresSubj[i];
      }
      out += "\n";
    }
    return out;
  }
  
  private static String impSubj(String stem, String end, boolean chStem) {
    String out = stem + end + "\tImperfect Subjunctive\n\n";
    for (int i = 0; i < 6; i++) {
      out += stem;
      if (end.equals("ar")) {
        out += arImpSubj[i];
      } else if (end.equals("er")) {
        out += erImpSubj[i];
      } else {
        out += irImpSubj[i];
      }
      out += "\n";
    }
    return out;
  }
  
  private static String futSubj(String stem, String end, boolean chStem) {
    String out = stem + end + "\tFuture Subjunctive\n\n";
    for (int i = 0; i < 6; i++) {
      out += stem;
      if (end.equals("ar")) {
        out += arFutSubj[i];
      } else if (end.equals("er")) {
        out += erFutSubj[i];
      } else {
        out += irFutSubj[i];
      }
      out += "\n";
    }
    return out;
  }
  
  private static String presCond(String stem, String end, boolean chStem) {
    String out = stem + end + "\tPresent Conditional\n\n";
    for (int i = 0; i < 6; i++) {
      out += stem;
      if (end.equals("ar")) {
        out += arPresCond[i];
      } else if (end.equals("er")) {
        out += erPresCond[i];
      } else {
        out += irPresCond[i];
      }
      out += "\n";
    }
    return out;
  }

  private static String persInf(String stem, String end, boolean chStem) {
    String out = stem + end + "\tPersonal Infinitive\n\n";
    for (int i = 0; i < 6; i++) {
      out += stem;
      if (end.equals("ar")) {
        out += arPersInf[i];
      } else if (end.equals("er")) {
        out += erPersInf[i];
      } else {
        out += irPersInf[i];
      }
      out += "\n";
    }
    return out;
  }
  
  private static String presImp(String stem, String end, boolean chStem) {
    String out = stem + end + "\tPresent Imperative\n\n";
    out += "  --  \n";
    for (int i = 1; i < 6; i++) {
      out += stem;
      if (end.equals("ar")) {
        out += arPresImp[i];
      } else if (end.equals("er")) {
        out += erPresImp[i];
      } else {
        out += irPresImp[i];
      }
      out += "\n";
    }
    return out;
  }
  
  public static void main(String[] args) throws Exception {
    String verb = "comer";
    String[] tenses = { "PresIndic", "ImpIndic", "PastIndic", "FutIndic",
        "PluIndic", "PresSubj", "ImpSubj", "FutSubj", "PresCond", "PersInf",
        "PresImp" };
    for (String tense : tenses) {
      System.out.println(conjugate(verb,tense));
    }
  }
}
