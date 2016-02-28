import java.util.Scanner;

// make tester class so that only valid verbs are searched

public class Verb {

  // fields
  private static final int BP = 0;
  private static final int EP = 1;
  private static final boolean AO = true;
  
  // constants
  private static final int PRESENT_INDICATIVE = 0;
  private static final int PRETERITE_IMPERFECT = 1;
  private static final int PRETERITE_PERFECT = 2;
  private static final int PLUPERFECT_INDICATIVE = 3;
  private static final int FUTURE_INDICATIVE = 4;
  private static final int CONDITIONAL = 5;
  private static final int PRESENT_SUBJUNCTIVE = 6;
  private static final int IMPERFECT_SUBJUNCTIVE = 7;
  private static final int FUTURE_SUBJUNCTIVE = 8;
  private static final int PERSONAL_INFINITIVE = 9;
  private static final int IMPERSONAL_INFINITIVE = 10;
  private static final int IMPERATIVE_AFF = 11;
  private static final int IMPERATIVE_NEG = 12;
  private static final int GERUND = 13;
  private static final int PAST_PARTICIPLE = 14;
  private static final int PRESENT_PERFECT = 15;
  private static final int COMPOUND_PAST_PERFECT = 16;
  private static final int COMPOUND_PLUPERFECT = 17;
  private static final int FUTURE_PERFECT = 18;
  private static final int CONDITIONAL_PERFECT = 19;
  private static final int PRESENT_PERFECT_SUBJ = 20;
  private static final int PAST_PERFECT_SUBJ = 21;
  private static final int FUTURE_PERFECT_SUBJ = 22;
  private static final int INFINITIVE_PERFECT = 23;
  private static final int IMP_INFINITIVE_PERFECT = 24;
  
  private static final int NUM_TENSES = 25;


  // accented and special chars
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
  
  // verb derivative lists
  private static final String[] darDerivs = new String[]{
      "desdar", "redar"
  };
  private static final String[] estarDerivs = new String[]{
      "sobestar", "sobre-estar", "sobreestar", "sobrestar"
  };
  private static final String[] lerDerivs = new String[]{
      "reler", "treler", "tresler"
  };
  private static final String[] terDerivs = new String[]{
      "abster", "ater", "conter", "deter", "entreter", "manter", "obter",
      "reter", "suster"
  };
  private static final String[] verDerivs = new String[]{
      "antever", "circunver", "entrever", "interver", "prever", "prover",
      "rever", "telever"
  };
  private static final String[] virDerivs = new String[]{
      "advir", "avir", "contravir", "convir", "desavir", "desconvir",
      "devir", "entrevir", "intervir", "obvir", "provir", "reavir",
      "reconvir", "revir", "sobrevir", "subvir"
  };
  // verbs only conjugated in the third-person singular
  private static final String[] thPerSing = new String[]{
    "borra" + cCedil + "ar", "carujar", "chuvinhar", "merujar",
    "relampar", "trovejar"
  };
  // verbs only conjugated in the third-person
  private static final String[] thPerBoth = new String[]{
      "aprazer", "aulir", "concernir", "condoer", "desaprazer", "doer",
      "grassitar", "later", "prazer", "precludir", "precluir", "reaprazer",
      "zinir", "zornar"
  };
  // verbs conjugated in only the first- and second-person plural
  // for the present indicative tense
  private static final String[] onlyArriz = new String[]{
      "adir", "aducir", "aguerrir", "combalir", "condir", "desempedernir",
      "desflorir", "desgornir", "despavorir", "desprecaver", "embair",
      "empedernir", "enfortir", "entalir", "esbaforir", "escarnir",
      "espavorir", "estransir", "estresir", "exinanir", "exir", "falir",
      "florir", "fornir", "fretenir", "garnir", "garrir", "gornir",
      "gualdir", "guarnir", "inanir", "lenir", "manutenir", "moquir",
      "pertransir", "precaver", "reaver", "reflorir", "remir", "renhir",
      "ressequir", "retransir", "suquir", "susquir", "transir"
  };
  // verbs not conjugated in the first-person singular of the present
  // indicative tense
  private static final String[] noFstPerSing = new String[]{
      "abolir", "aborrir", "acupremir", "adurir", "apodrir", "balir", "banir",
      "barrir", "bramir", "brandir", "branquir", "buir", "carpir", "cernir",
      "colorir", "comburir", "comedir", "delir", "demolir", "demulcir",
      "descolorir", "descomedir", "emolir", "enganir", "esmarrir", "excelir",
      "extorquir", "fremir", "ganir", "guarir", "languir", "monir",
      "multicolorir", "parturir", "premir", "pruir", "prurir", "puir", "raer",
      "rebolir", "recolorir", "relinquir", "relinq" + uTreme + "ir", "reprurir",
      "retorquir", "retorq" + uTreme + "ir", "ruir", "soer"
  };
  
  private static String[] bpConjAO;
  private static String[] bpConjNoAO;
  private static String[] epConjAO;
  private static String[] epConjNoAO;
  private static String[] ends;
  private static int[] stems;
  private static String stem, stem2, stem3, stem4;

  private final String verb;
  private final String ending;
  private String[][][] conjTable = null;
  
  private static final String[][][] estarTable;
  private static final String[][][] haverTable;
  private static final String[][][] serTable;
  private static final String[][][] terTable;
  
  static {
    Verb estar = new Verb("estar");
    Verb haver = new Verb("haver");
    Verb ser = new Verb("ser");
    Verb ter = new Verb("ter");
    haver.conjUpTo(PAST_PARTICIPLE);
    haverTable = haver.conjTable;
    ter.conjUpTo(PAST_PARTICIPLE);
    terTable = ter.conjTable;
    estar.conjUpTo(IMP_INFINITIVE_PERFECT);
    estarTable = estar.conjTable;
    ser.conjUpTo(IMP_INFINITIVE_PERFECT);
    serTable = ser.conjTable;
  }
  
  /**
   * Modifies a string by replacing the nth to last character with a new
   * character.
   * 
   * @param n which character from the end is to be changed.
   * @param stem the original string.
   * @param c the new character.
   * @return the modified string.
   */
  private static String chCharToLast(int n, String stem, char c) {
    // use StringBuilder
    int len = stem.length();
    String newStem = stem.substring(0, len - n) + c;
    if (n > 1) {
      newStem += stem.substring(len - n + 1);
    }
    return newStem;
  }
  
  /**
   * Modifies a string by replacing the nth to last character with a new
   * string.
   * 
   * @param n which character from the end is to be changed.
   * @param stem the original string.
   * @param s the new string.
   * @return the modified string.
   */
  private static String chCharToLast(int n, String stem, String s) {
    // use StringBuilder
    int len = stem.length();
    String newStem = stem.substring(0, len - n) + s;
    if (n > 1) {
      newStem += stem.substring(len - n + 1);
    }
    return newStem;
  }
  
  /*
   * Resets the static fields used to conjugate each tense.
   */
  private static void resetTables() {
    // reset conjugation tables
    bpConjAO = new String[]{ "", "", "", "", "", "" };
    bpConjNoAO = new String[]{ "", "", "", "", "", "" };
    epConjAO = new String[]{ "", "", "", "", "", "" };
    epConjNoAO = new String[]{ "", "", "", "", "", "" };
    // reset conjugation endings
    ends = null;
    stems = null;
    stem = null;
    stem2 = null;
    stem3 = null;
    stem4 = null;
  }

  /**
   * Constructs a new verb from the specified infinitive.  The constructor
   * cleans up the input but does not throw an error if the input string
   * is an invalid infinitive form.
   * 
   * To determine whether a verb has valid form, use isValidVerb().
   * 
   * @param infinitive the given infinitive.
   */
  public Verb(String infinitive) {
    // use error code checking to ensure correct verb infinitive form
    verb = checkInput(infinitive.toLowerCase()); // maybe move into checkInput method
    if (verb != null) {
      ending = verb.substring(verb.length() - 2);
    } else {
      ending = "ERROR";
    }
    resetTables();
  }
  
  /**
   * Determines whether the infinitive is valid.
   * 
   * @return true if verb is valid, false otherwise.
   */
  private boolean isValidVerb() {
    return verb != null;
  }
  
  private String checkInput(String input) {
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

    if (hasValidEnding(s.toString())) { // confirm the cleaned up
                                        // infinitive has correct form
      return s.toString();
    }
    return null;  // infinitive not valid
  }
  
  /**
   * Determines whether a string is a valid Portuguese infinitive.
   * @param s the string to check.
   * @return true if the string is a valid infinitive, false otherwise.
   */
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
  
  
  /**
   * Determines whether this verb is conjugated only in the third-person
   * singular for all tenses.
   * 
   * @return true if it has limited conjugation, false otherwise.
   */
  private boolean hasOnlyThPerSing() {
    // verbs only conjugated in the third person singular
    for (String s: thPerSing) {
      if (verb.equals(s)) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Determines whether this verb is conjugated only in the third-person
   * (singular and plural) for all tenses.
   * 
   * @return true if it has limited conjugation, false otherwise.
   */
  private boolean hasOnlyThPer() {
    // verbs only conjugated in the third person
    for (String s: thPerBoth) {
      if (verb.equals(s)) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Determines whether this verb is conjugated only in the first- and second-
   * person plural of the present indicative tense (and correspondingly, not
   * conjugated whatsoever in the present subjunctive tense).
   * 
   * @return true if it has limited conjugation, false otherwise.
   */
  private boolean hasOnlyArriz() {
    // verbs only conjugated in the nos and vos forms in present indicative
    for (String s: onlyArriz) {
      if (verb.equals(s)) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Determines whether this verb is not conjugated in the first-person
   * singular of the present indicative tense (and correspondingly, not
   * conjugated whatsoever in the present subjunctive tense).
   * 
   * @return true if it has limited conjugation, false otherwise.
   */
  private boolean hasNoFstPerSing() {
    // verbs not conjugated in the first person singular present indicative // ADD THE OTHER VERBS FROM THE AO CHANGE SETS
    for (String s: noFstPerSing) {
      if (verb.equals(s)) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Determines and returns the conjugation paradigm for this verb in the
   * present indicative tense.
   * 
   * @param verb the infinitive.
   * @return the conjugation paradigm for the present indicative tense.
   */
  private static String getVerbTypePresInd(String verb) {
    // uses shadowing for now

    for (String s: darDerivs) {
      if (verb.equals(s)) {
        return "-DAR";
      }
    }
    for (String s: estarDerivs) {
      if (verb.equals(s)) {
        return "-ESTAR";
      }
    }
    for (String s: lerDerivs) {
      if (verb.equals(s)) {
        return "-LER";
      }
    }
    for (String s: terDerivs) {
      if (verb.equals(s)) {
        return "-TER";
      }
    }
    for (String s: verDerivs) {
      if (verb.equals(s)) {
        return "-VER";
      }
    }
    for (String s: virDerivs) {
      if (verb.equals(s)) {
        return "-VIR";
      }
    }
    switch (verb) {
      case "abaiucar":
        return "ABAIUCAR";
      case "afiuzar":
        return "AFIUZAR";
      case "ajesuitar":
        return "AJESUITAR";
      case "agenciar":
        return "AGENCIAR";
      case "ansiar":
        return "ANSIAR";
      case "apaular":
        return "APAULAR";
      case "aprazer":
        return "APRAZER";
      case "apresenciar":
        return "APRESENCIAR";
      case "arremediar":
        return "ARREMEDIAR";
      case "aspergir":
        return "ASPERGIR";
      case "ateizar":
        return "ATEIZAR";
      case "aunar":
        return "AUNAR";
      case "aviusar":
        return "AVIUSAR";
      case "aziumar":
        return "AZIUMAR";
      case "cadenciar":
        return "CADENCIAR";
      case "cerzir":
        return "CERZIR";
      case "comerciar":
        return "COMERCIAR";
      case "consumir":
        return "CONSUMIR";
      case "convergir":
        return "CONVERGIR";
      case "crer":
        return "CRER";
      case "dar":
        return "DAR";
      case "denegrir":
        return "DENEGRIR";
      case "desaprazer":
        return "DESAPRAZER";
      case "desarremediar":
        return "DESARREMEDIAR";
      case "descrer":
        return "DESCRER";
      case "desembaular":
        return "DESEMBAULAR";
      case "desmilinguir":
      case "desmiling" + uTreme + "ir":
        return "DESMILINGUIR";
      case "desnegociar":
        return "DESNEGOCIAR";
      case "despremiar":
        return "DESPREMIAR";
      case "desremediar":
        return "DESREMEDIAR";
      case "destruir":
        return "DESTRUIR";
      case "diligenciar":
        return "DILIGENCIAR";
      case "divergir":
        return "DIVERGIR";
      case "embaular":
        return "EMBAULAR";
      case "embaucar":
        return "EMBAUCAR";
      case "ensimesmar":
        return "ENSIMESMAR";
      case "enviusar":
        return "ENVIUSAR";
      case "esmiu" + cCedil + "ar":
        return "ESMIU" + cCedilUp + "AR";
      case "estar":
        return "ESTAR";
      case "explodir":
        return "EXPLODIR";
      case "faiscar":
        return "FAISCAR";
      case "faular":
        return "FAULAR";
      case "frigir":
        return "FRIGIR";
      case "fugir":
        return "FUGIR";
      case "haver":
        return "HAVER";
      case "incendiar":
        return "INCENDIAR";
      case "intermediar":
        return "INTERMEDIAR";
      case "ir":
        return "IR";
      case "ler":
        return "LER";
      case "licenciar":
        return "LICENCIAR";
      case "mediar":
        return "MEDIAR";
      case "obsequiar":
        return "OBSEQUIAR";
      case "odiar":
        return "ODIAR";
      case "parar":
        return "PARAR";
      case "p" + oCFlex + "r":
        return "P" + oCFlexUp + "R";
      case "prazer":
        return "PRAZER";
      case "premiar":
        return "PREMIAR";
      case "presenciar":
        return "PRESENCIAR";
      case "promediar":
        return "PROMEDIAR";
      case "puitar":
        return "PUITAR";
      case "reaprazer":
        return "REAPRAZER";
      case "requerer":
        return "REQUERER";
      case "rer":
        return "RER";
      case "retorquir":
        return "RETORQUIR";
      case "retorq" + uTreme + "ir":
        return "RETORQ" + uTremeUp + "IR";
      case "reunir":
        return "REUNIR";
      case "rir":
        return "RIR";
      case "ruidar":
        return "RUIDAR";
      case "saudar":
        return "SAUDAR";
      case "ser":
        return "SER";
      case "sobreir":
        return "SOBREIR";
      case "sobresser":
        return "SOBRESSER";
      case "sortir":
        return "SORTIR";
      case "subir":
        return "SUBIR";
      case "sumir":
        return "SUMIR";
      case "ter":
        return "TER";
      case "tossir":
        return "TOSSIR";
      case "ver":
        return "VER";
      case "vir":
        return "VIR";
      default:
          break;
    }
    int len = verb.length();
    if (len >= 10) {
      String last10 = verb.substring(len - 10);
      if (last10.equals("balaustrar")) {
        return "-BALAUSTRAR";
      }
    }
    if (len >= 9) {
      String last9 = verb.substring(len - 9);
      if (last9.equals("construir")) {
        return "-CONSTRUIR";
      }
      if (last9.equals("delinquir") || last9.equals("delinq" + uTreme + "ir")) {
        return "-DELINQUIR";
      }
    }
    if (len >= 8) {
      String last8 = verb.substring(len - 8);
      if (last8.equals("negociar")) {
        return "-NEGOCIAR";
      }
      if (last8.equals("mobiliar")) {
        return "-MOBILIAR";
      }
    }
    if (len >= 7) {
      String last7 = verb.substring(len - 7);
      if (last7.equals("engolir")) {
        return "-ENGOLIR";
      }
      if (last7.equals("entupir")) {
        return "-ENTUPIR";
      }
      if (last7.equals("gauchar")) {
        return "-GAUCHAR";
      }
    }
    if (len >= 6) {
      String last6 = verb.substring(len - 6);
      switch (last6) {
        case "acudir":
          return "-ACUDIR";
        case "anquir":
          return "-ANQUIR";
        case "arguir":
        case "arg" + uTreme + "ir":
          return "-ARGUIR";
        case "baular":
          return "-BAULAR";
        case "ciumar":
          return "-CIUMAR";
        case "cobrir":
          return "-COBRIR";
        case "cuspir":
          return "-CUSPIR";
        case "dormir":
          return "-DORMIR";
        case "embair":
          return "-EMBAIR";
        case "erguer":
          return "-ERGUER";
        case "gredir":
          return "-GREDIR";
        case "guizar":
          return "-GUIZAR";
        case "guspir":
          return "-GUSPIR";
        case "inguar":
          return "-INGUAR";
        case "inguir":
        case "ing" + uTreme + "ir":
          return "-INGUIR";
        case "inquar":
          return "-INQUAR";
        case "perder":
          return "-PERDER";
        case "prazer":
          return "-PRAZER";
        case "querer":
          return "-QUERER";
        case "quizar":
          return "-QUIZAR";
        case "ruinar":
          return "-RUINAR";
        case "sorrir":
          return "-SORRIR";
        case "trazer":
          return "-TRAZER";
        case "viuvar":
          return "-VIUVAR";
        default:
          break;
      }
    }
    if (len >= 5) {
      String last5 = verb.substring(len - 5);
      switch (last5) {
        case "aguar":
          return "-AGUAR";
        case "aizar":
          return "-AIZAR";
        case "caber":
          return "-CABER";
        case "dizer":
          return "-DIZER";
        case "ectir":
          return "-ECTIR";
        case "eizar":
          return "-EIZAR";
        case "ertir":
          return "-ERTIR";
        case "espir":
          return "-ESPIR";
        case "estir":
          return "-ESTIR";
        case "eguar":
          return "-EGUAR";
        case "eguir":
          return "-EGUIR";
        case "eg" + uTreme + "ir":
          return "-EG" + uTremeUp + "IR";
        case "enhir":
          return "-ENHIR";
        case "entir":
          return "-ENTIR";
        case "equar":
          return "-EQUAR";
        case "ergir":
          return "-ERGIR";
        case "ernir":
          return "-ERNIR";
        case "ervir":
          return "-ERVIR";
        case "erzir":
          return "-ERZIR";
        case "fazer":
          return "-FAZER";
        case "iguar":
          return "-IGUAR";
        case "iquar":
          return "-IQUAR";
        case "jazer":
          return "-JAZER";
        case "medir":
          return "-MEDIR";
        case "pedir":
          return "-PEDIR";
        case "oibir":
          return "-OIBIR";
        case "oizar":
          return "-OIZAR";
        case "ouvir":
          return "-OUVIR";
        case "parir":
          return "-PARIR";
        case "poder":
          return "-PODER";
        case "polir":
          return "-POLIR";
        case "saber":
          return "-SABER";
        case "uizar":
          return "-UIZAR";
        case "valer":
          return "-VALER";
        default:
          break;
      }
    }
    if (len >= 4) {
      String last4 = verb.substring(len - 4);
      switch (last4) {
        case "agir":
          return "-AGIR";
        case "edir":
          return "-EDIR";
        case "eger":
          return "-EGER";
        case "elir":
          return "-ELIR";
        case "emir":
          return "-EMIR";
        case "enir":
          return "-ENIR";
        case "erir":
          return "-ERIR";
        case "etir":
          return "-ETIR";
        case "g" + uTreme + "er":
          return "-G" + uTremeUp + "ER";
        case "g" + uTreme + "ir":
          return "-G" + uTremeUp + "IR";
        case "guir":
          return "-GUIR";
        case "oiar":
          return "-OIAR";
        case "quir":
          return "-QUIR";
        case "q" + uTreme + "ir":
          return "-Q" + uTremeUp + "IR";
        case "ulir":
          return "-ULIR";
        case "uzir":
          return "-UZIR";
        default:
          break;
      }  
    }
    if (len >= 3) {
      String last3 = verb.substring(len - 3);
      switch (last3) {
        case "aer":
          return "-AER";
        case "air":
          return "-AIR";
        case "cer":
          return "-CER";
        case "cir":
          return "-CIR";
        case "ear":
          return "-EAR";
        case "ger":
          return "-GER";
        case "gir":
          return "-GIR";
        case "oar":
          return "-OAR";
        case "oer":
          return "-OER";
        case "por":
          return "-POR";
        case "uir":
          return "-UIR";
        default:
          break;
      }
    }
    String last2 = verb.substring(len - 2);
    switch (last2) {
      case "ar":
        return "-AR";
      case "er":
        return "-ER";
      case "ir":
        return "-IR";
      default:
        return null;
    }
  }
  
  /**
   * Determines and returns the conjugation paradigm for this verb in the
   * preterite imperfect tense.
   * 
   * @param verb the infinitive.
   * @return the conjugation paradigm for the preterite imperfect tense.
   */
  private static String getVerbTypePretImp(String verb) {
    // uses shadowing for now

    for (String s: terDerivs) {
      if (verb.equals(s)) {
        return "-TER";
      }
    }
    for (String s: virDerivs) {
      if (verb.equals(s)) {
        return "-VIR";
      }
    }
    switch (verb) {
      case "ensimesmar":
        return "ENSIMESMAR";
      case "p" + oCFlex + "r":
        return "-POR";
      case "ser":
        return "SER";
      case "sobresser":
        return "SOBRESSER";
      case "ter":
        return "TER";
      case "vir":
        return "VIR";
      default:
          break;
    }
    int len = verb.length();
    if (len >= 4) {
      String last4 = verb.substring(len - 4);
      switch (last4) {
        case "guer":
          return "-GUER";
        case "guir":
        case "g" + uTreme + "ir":
          return "-GUIR";
        case "quir":
        case "q" + uTreme + "ir":
          return "-QUIR";
        default:
          break;
      }  
    }
    if (len >= 3) {
      String last3 = verb.substring(len - 3);
      switch (last3) {
        case "aer":
          return "-AER";
        case "air":
          return "-AIR";
        case "oer":
          return "-OER";
        case "oir":
          return "-OIR";
        case "por":
          return "-POR";
        case "uer":
          return "-UER";
        case "uir":
          return "-UIR";
        default:
          break;
      }
    }
    String last2 = verb.substring(len - 2);
    switch (last2) {
      case "ar":
        return "-AR";
      case "er":
        return "-ER";
      case "ir":
        return "-IR";
      default:
        return null;
    }
  }
  
  /**
   * Determines and returns the conjugation paradigm for this verb in the
   * preterite perfect tense.
   * 
   * @param verb the infinitive.
   * @return the conjugation paradigm for the preterite perfect tense.
   */
  private static String getVerbTypePretPerf(String verb) {
    // uses shadowing for now

    for (String s: darDerivs) {
      if (verb.equals(s)) {
        return "-DAR";
      }
    }
    for (String s: estarDerivs) {
      if (verb.equals(s)) {
        return "-ESTAR";
      }
    }
    for (String s: terDerivs) {
      if (verb.equals(s)) {
        return "-TER";
      }
    }
    for (String s: verDerivs) {
      if (verb.equals(s)) {
        return "-VER";
      }
    }
    for (String s: virDerivs) {
      if (verb.equals(s)) {
        return "-VIR";
      }
    }
    switch (verb) {
      case "dar":
        return "DAR";
      case "ensimesmar":
        return "ENSIMESMAR";
      case "estar":
        return "ESTAR";
      case "haver":
        return "HAVER";
      case "ir":
        return "IR";
      case "poder":
        return "PODER";
      case "p" + oCFlex + "r":
        return "-POR";
      case "reaver":
        return "REAVER";
      case "requerer":
        return "REQUERER";
      case "ser":
        return "SER";
      case "sobreir":
        return "SOBREIR";
      case "sobresser":
        return "SOBRESSER";
      case "ter":
        return "TER";
      case "ver":
        return "VER";
      case "vir":
        return "VIR";
      default:
        break;
    }
    
    int len = verb.length();
    if (len >= 6) {
      String last6 = verb.substring(len - 6);
      switch (last6) {
        case "prazer":
          return "-PRAZER";
        case "querer":
          return "-QUERER";
        case "trazer":
          return "-TRAZER";
        default:
          break;
      }
    }
    if (len >= 5) {
      String last5 = verb.substring(len - 5);
      if (last5.equals("fazer")) {
        return "-FAZER";
      }
      if (last5.equals("dizer")) {
        return "-DIZER";
      }
    }
    if (len >= 4) {
      String last4 = verb.substring(len - 4);
      switch (last4) {
        case "aber":
          return "-ABER";
        case "guar":
          return "-GUAR";
        case "guir":
        case "g" + uTreme + "ir":
          return "-GUIR";
        case "quar":
          return "-QUAR";
        case "quir":
        case "q" + uTreme + "ir":
          return "-QUIR";
        default:
          break;
      }  
    }
    if (len >= 3) {
      String last3 = verb.substring(len - 3);
      switch (last3) {
        case "aer":
          return "-AER";
        case "air":
          return "-AIR";
        case "car":
          return "-CAR";
        case "gar":
          return "-GAR";
        case "oer":
          return "-OER";
        case "por":
          return "-POR";
        case "uir":
          return "-UIR";
        default:
          break;
      }
    }
    String last2 = verb.substring(len - 2);
    switch (last2) {
      case "ar":
        return "-AR";
      case "er":
        return "-ER";
      case "ir":
        return "-IR";
      default:
        return null;
    }
  }
  
  /**
   * Determines and returns the conjugation paradigm for this verb in the
   * future indicative tense.
   * 
   * @param verb the infinitive.
   * @return the conjugation paradigm for the future indicative tense.
   */
  private static String getVerbTypeFutInd(String verb) {
    // uses shadowing for now

    if (verb.equals("ensimesmar")) {
      return "ENSIMESMAR";
    }
    if (verb.equals("p" + oCFlex + "r")) {
      return "POR";
    }
    
    int len = verb.length();
    if (len >= 6) {
      String last6 = verb.substring(len - 6);
      if (last6.equals("trazer")) {
        return "-TRAZER";
      }
    }
    if (len >= 5) {
      String last5 = verb.substring(len - 5);
      if (last5.equals("fazer")) {
        return "-FAZER";
      }
      if (last5.equals("dizer")) {
        return "-DIZER";
      }
    }
    return "REG";
  }
  
  /**
   * Determines and returns the conjugation paradigm for this verb in the
   * present subjunctive tense.
   * 
   * @param verb the infinitive.
   * @return the conjugation paradigm for the present subjunctive tense.
   */
  private static String getVerbTypePresSubj(String verb) {
    // uses shadowing for now

    for (String s: darDerivs) {
      if (verb.equals(s)) {
        return "-DAR";
      }
    }
    for (String s: estarDerivs) {
      if (verb.equals(s)) {
        return "-ESTAR";
      }
    }
    switch (verb) {
      case "abaiucar":
        return "ABAIUCAR";
      case "afiuzar":
        return "AFIUZAR";
      case "ajesuitar":
        return "AJESUITAR";
      case "ansiar":
        return "ANSIAR";
      case "apaular":
        return "APAULAR";
      case "arremediar":
        return "ARREMEDIAR";
      case "aunar":
        return "AUNAR";
      case "aviusar":
        return "AVIUSAR";
      case "aziumar":
        return "AZIUMAR";
      case "dar":
        return "DAR";
      case "desarremediar":
        return "DESARREMEDIAR";
      case "desembaular":
        return "DESEMBAULAR";
      case "desremediar":
        return "DESREMEDIAR";
      case "embaular":
        return "EMBAULAR";
      case "embaucar":
        return "EMBAUCAR";
      case "ensimesmar":
        return "ENSIMESMAR";
      case "esmiu" + cCedil + "ar":
        return "ESMIU" + cCedilUp + "AR";
      case "estar":
        return "ESTAR";
      case "explodir":
        return "EXPLODIR";
      case "faiscar":
        return "FAISCAR";
      case "faular":
        return "FAULAR";
      case "haver":
        return "HAVER";
      case "incendiar":
        return "INCENDIAR";
      case "intermediar":
        return "INTERMEDIAR";
      case "ir":
        return "IR";
      case "mediar":
        return "MEDIAR";
      case "odiar":
        return "ODIAR";
      case "promediar":
        return "PROMEDIAR";
      case "puitar":
        return "PUITAR";
      case "requerer":
        return "REQUERER";
      case "reunir":
        return "REUNIR";
      case "ruidar":
        return "RUIDAR";
      case "saudar":
        return "SAUDAR";
      case "ser":
        return "SER";
      case "sobreir":
        return "SOBREIR";
      case "sobresser":
        return "SOBRESSER";
      default:
          break;
    }
    int len = verb.length();
    if (len >= 10) {
      String last10 = verb.substring(len - 10);
      if (last10.equals("balaustrar")) {
        return "-BALAUSTRAR";
      }
    }
    if (len >= 9) {
      String last9 = verb.substring(len - 9);
      if (last9.equals("delinquir") || last9.equals("delinq" + uTreme + "ir")) {
        return "-DELINQUIR";
      }
    }
    if (len >= 8) {
      String last8 = verb.substring(len - 8);
      if (last8.equals("mobiliar")) {
        return "-MOBILIAR";
      }
    }
    if (len >= 7) {
      String last7 = verb.substring(len - 7);
      if (last7.equals("gauchar")) {
        return "-GAUCHAR";
      }
    }
    if (len >= 6) {
      String last6 = verb.substring(len - 6);
      switch (last6) {
        case "baular":
          return "-BAULAR";
        case "ciumar":
          return "-CIUMAR";
        case "inguar":
          return "-INGUAR";
        case "inquar":
          return "-INQUAR";
        case "prazer":
          return "-PRAZER";
        case "querer":
          return "-QUERER";
        case "ruinar":
          return "-RUINAR";
        case "viuvar":
          return "-VIUVAR";
        default:
          break;
      }
    }
    if (len >= 5) {
      String last5 = verb.substring(len - 5);
      switch (last5) {
        case "aguar":
          return "-AGUAR";
        case "aizar":
          return "-AIZAR";
        case "eizar":
          return "-EIZAR";
        case "eguar":
          return "-EGUAR";
        case "equar":
          return "-EQUAR";
        case "iguar":
          return "-IGUAR";
        case "iquar":
          return "-IQUAR";
        case "oibir":
          return "-OIBIR";
        case "oizar":
          return "-OIZAR";
        case "ouvir":
          return "-OUVIR";
        case "parir":
          return "-PARIR";
        case "saber":
          return "-SABER";
        case "uizar":
          return "-UIZAR";
        default:
          break;
      }
    }
    if (len >= 4) {
      String last4 = verb.substring(len - 4);
      switch (last4) {
        case "guar":
          return "-GUAR";
        case "oiar":
          return "-OIAR";
        case "quar":
          return "-QUAR";
        case "quir":
          return "-QUIR";
        case "q" + uTreme + "ir":
          return "-Q" + uTremeUp + "IR";
        default:
          break;
      }  
    }
    if (len >= 3) {
      String last3 = verb.substring(len - 3);
      switch (last3) {
        case "car":
          return "-CAR";
        case cCedil + "ar":
          return cCedilUp + "AR";
        case "gar":
          return "-GAR";
        case "oar":
          return "-OAR";
        case "oer":
          return "-OER";
        case "p" + oCFlex + "r":
        case "por":
          return "-POR";
        default:
          break;
      }
    }
    String last2 = verb.substring(len - 2);
    switch (last2) {
      case "ar":
        return "-AR";
      case "er":
        return "-ER";
      case "ir":
        return "-IR";
      default:
        return null;
    }
  }
  
  /**
   * Determines and returns the conjugation paradigm for this verb in the
   * imperfect subjunctive tense.
   * 
   * @param verb the infinitive.
   * @return the conjugation paradigm for the imperfect subjunctive tense.
   */
  private static String getVerbTypeImpSubj(String verb) {
    // uses shadowing for now

    for (String s: darDerivs) {
      if (verb.equals(s)) {
        return "-DAR";
      }
    }
    for (String s: estarDerivs) {
      if (verb.equals(s)) {
        return "-ESTAR";
      }
    }
    for (String s: terDerivs) {
      if (verb.equals(s)) {
        return "-TER";
      }
    }
    for (String s: verDerivs) {
      if (verb.equals(s)) {
        return "-VER";
      }
    }
    for (String s: virDerivs) {
      if (verb.equals(s)) {
        return "-VIR";
      }
    }
    switch (verb) {
      case "dar":
        return "DAR";
      case "ensimesmar":
        return "ENSIMESMAR";
      case "estar":
        return "ESTAR";
      case "haver":
        return "HAVER";
      case "ir":
        return "IR";
      case "poder":
        return "PODER";
      case "p" + oCFlex + "r":
        return "-POR";
      case "ser":
        return "SER";
      case "sobreir":
        return "SOBREIR";
      case "sobresser":
        return "SOBRESSER";
      case "ter":
        return "TER";
      case "ver":
        return "VER";
      case "vir":
        return "VIR";
      default:
          break;
    }
    int len = verb.length();
    if (len >= 6) {
      String last6 = verb.substring(len - 6);
      switch (last6) {
        case "querer":
          return "-QUERER";
        case "trazer":
          return "-TRAZER";
        default:
          break;
      }  
    }
    if (len >= 5) {
      String last5 = verb.substring(len - 5);
      switch (last5) {
        case "caber":
          return "-CABER";
        case "dizer":
          return "-DIZER";
        case "fazer":
          return "-FAZER";
        case "saber":
          return "-SABER";
        default:
          break;
      }  
    }
    if (len >= 4) {
      String last4 = verb.substring(len - 4);
      switch (last4) {
        case "guir":
        case "g" + uTreme + "ir":
          return "-GUIR";
        case "quir":
        case "q" + uTreme + "ir":
          return "-QUIR";
        default:
          break;
      }  
    }
    if (len >= 3) {
      String last3 = verb.substring(len - 3);
      switch (last3) {
        case "aer":
          return "-AER";
        case "air":
          return "-AIR";
        case "oer":
          return "-OER";
        case "oir":
          return "-OIR";
        case "por":
          return "-POR";
        case "uer":
          return "-UER";
        case "uir":
          return "-UIR";
        default:
          break;
      }
    }
    String last2 = verb.substring(len - 2);
    switch (last2) {
      case "ar":
        return "-AR";
      case "er":
        return "-ER";
      case "ir":
        return "-IR";
      default:
        return null;
    }
  }
  
  /**
   * Determines and returns the conjugation paradigm for this verb's past
   * participle.
   * 
   * @param verb the infinitive.
   * @return the conjugation paradigm for the past participle.
   */
  private static String getVerbTypePastPart(String verb) {
    // uses shadowing for now
    for (String s: verDerivs) {
      if (verb.equals(s)) {
        return "-VER";
      }
    }
    for (String s: virDerivs) {
      if (verb.equals(s)) {
        return "-VIR";
      }
    }
    switch (verb) {
      case "absolver":
      case "aceitar":
      case "acender":
      case "anexar":
      case "assentar":
      case "benzer":
      case "despertar":
      case "dispersar":
      case "distender":
      case "distinguir":
      case "eleger":
      case "encher":
      case "entregar":
      case "envolver":
      case "enxugar":
      case "expressar":
      case "exprimir":
      case "expulsar":
      case "extinguir":
      case "fartar":
      case "findar":
      case "frigir":
      case "ganhar":
      case "gastar":
      case "isentar":
      case "juntar":
      case "libertar":
      case "limpar":
      case "manifestar":
      case "matar":
      case "malquerer":
      case "morrer":
      case "murchar":
      case "ocultar":
      case "pagar":
      case "pegar":
      case "prender":
      case "romper":
      case "salvar":
      case "secar":
      case "segurar":
      case "soltar":
      case "sujeitar":
      case "vagar":
      case "ver":
      case "vir":
        return verb.toUpperCase();
      case "p" + oCFlex + "r":
        return "-POR";
      default:
          break;
    }
    int len = verb.length();
    if (len >= 8) {
      String last8 = verb.substring(len - 8);
      if (last8.equals("imprimir")) {
        return "-IMPRIMIR";
      }
    }
    if (len >= 7) {
      String last7 = verb.substring(len - 7);
      if (last7.equals("screver")) {
        return "-SCREVER";
      }
    }
    if (len >= 6) {
      String last6 = verb.substring(len - 6);
      if (last6.equals("cobrir")) {
        return "-COBRIR";
      }
    }
    if (len >= 5) {
      String last5 = verb.substring(len - 5);
      switch (last5) {
        case "abrir":
        case "argir":
        case "dizer":
        case "ergir":
        case "fazer":
          return "-" + last5.toUpperCase();
        default:
          break;
      }
    }
    if (len >= 4) {
      String last4 = verb.substring(len - 4);
      switch (last4) {
        case "g" + uTreme + "er":
          return "-GUER";
        case "g" + uTreme + "ir":
          return "-GUIR";
        case "q" + uTreme + "er":
          return "-QUER";
        case "q" + uTreme + "ir":
          return "-QUIR";
        case "guer":
        case "guir":
        case "quer":
        case "quir":
          return "-" + last4.toUpperCase();
        default:
          break;
      }  
    }
    if (len >= 3) {
      String last3 = verb.substring(len - 3);
      switch (last3) {
        case "aer":
        case "air":
        case "oer":
        case "oir":
        case "uer":
        case "uir":
        case "por":
          return "-" + last3.toUpperCase();
        default:
          break;
      }
    }
    String last2 = verb.substring(len - 2);
    // first check specific special verbs
    // then check verb endings that are 7 letters long
    // then 6, then 5, then 4, then 3
    // a switch statement would work best
    switch (last2) {
      case "ar":
        return "-AR";
      case "er":
        return "-ER";
      case "ir":
        return "-IR";
      default:
        return null;
    }
  }
  
  private static String getVerbType(String infinitive, int tense) {
    switch (tense) {
      case PRESENT_INDICATIVE:
        return getVerbTypePresInd(infinitive);
      case PRETERITE_IMPERFECT:
        return getVerbTypePretImp(infinitive);
      case PRETERITE_PERFECT:
        return getVerbTypePretPerf(infinitive);
      case PLUPERFECT_INDICATIVE:
        return getVerbTypePretPerf(infinitive);
      case FUTURE_INDICATIVE:
      case CONDITIONAL:
        return getVerbTypeFutInd(infinitive);
      case PRESENT_SUBJUNCTIVE:
        return getVerbTypePresSubj(infinitive);
      case IMPERFECT_SUBJUNCTIVE:
      case FUTURE_SUBJUNCTIVE:
        return getVerbTypeImpSubj(infinitive);
      case PAST_PARTICIPLE:
        return getVerbTypePastPart(infinitive);
      default:
        return null;
    }
  }
  
  private String getStem(int var, boolean AO) {
    String tempInf = getInfinitive(var, AO);
    String stem = tempInf.substring(0, tempInf.length() - 2);
    return stem;
  }
  
  private String getInfinitive(int var, boolean AO) {
    if (var == BP && AO) {
      switch (verb) {
        case "accionar":
          return "acionar";
        case "activar":
          return "ativar";
        case "actualizar":
          return "atualizar";
        case "actuar":
          return "atuar";
        case "adjectivar":
          return "adjetivar";
        case "agro-alimentar":
          return "agroalimentar";
        case "anti-sepsiar":
          return "antissepsiar";
        case "arg" + uTreme + "ir":
          return "arguir";
        case "arquitectar":
          return "arquitetar";
        case "auto-abastecer":
          return "autoabastecer";
        case "auto-administrar":
          return "autoadministrar";
        case "auto-excitar":
          return "autoexcitar";
        case "auto-excluir":
          return "autoexcluir";
        case "auto-sugestionar":
          return "autossugestionar";
        case "auto-suspender":
          return "autossuspender";
        case "baptizar":
          return "batizar";
        case "co-administrar":
          return "coadministrar";
        case "co-arrendar":
          return "coarrendar";
        case "co-delinquir":
        case "co-delinq" + uTreme + "ir":
          return "codelinquir";
        case "co-dirigir":
          return "codirigir";
        case "co-editar":
          return "coeditar";
        case "co-financiar":
          return "cofinanciar";
        case "co-gerir":
          return "cogerir";
        case "co-incinerar":
          return "coincinerar";
        case "co-litigar":
          return "colitigar";
        case "co-obrigar":
          return "coobrigar";
        case "co-participar":
          return "coparticipar";
        case "co-produzir":
          return "coproduzir";
        case "co-responsabilizar":
          return "corresponsabilizar";
        case "coleccionar":
          return "colecionar";
        case "concetualizar":
          return "conceptualizar";
        case "confecionar":
          return "confeccionar";
        case "contra-indicar":
          return "contraindicar";
        case "contra-informar":
          return "contrainformar";
        case "contra-ordenar":
          return "contraordenar";
        case "contra-revolucionar":
          return "contrarrevolucionar";
        case "contra-selar":
          return "contrasselar";
        case "dececionar":
          return "decepcionar";
        case "delinq" + uTreme + "ir":
          return "delinquir";
        case "deliq" + uTreme + "escer":
          return "deliquescer";
        case "desactivar":
          return "desativar";
        case "desactualizar":
          return "desatualizar";
        case "desarquitectar":
          return "desarquitetar";
        case "desbaptizar":
          return "desbatizar";
        case "desensang" + uTreme + "entar":
          return "desensanguentar";
        case "desfreq" + uTreme + "entar":
          return "desfrequentar";
        case "desmiling" + uTreme + "ir":
          return "desmilinguir";
        case "difractar":
          return "difratar";
        case "direccionar":
          return "direcionar";
        case "efectivar":
          return "efetivar";
        case "efectuar":
          return "efetuar";
        case "ensang" + uTreme + "entar":
          return "ensanguentar";
        case "eq" + uTreme + "idistar":
          return "equidistar";
        case "eq" + uTreme + "ipoler":
          return "equipoler";
        case "eq" + uTreme + "iponderar":
          return "equiponderar";
        case "exactificar":
          return "exatificar";
        case "exig" + uTreme + "ificar":
          return "exiguificar";
        case "facturar":
          return "faturar";
        case "freq" + uTreme + "entar":
          return "frequentar";
        case "inactivar":
          return "inativar";
        case "infra-escapular":
          return "infraescapular";
        case "injectar":
          return "injetar";
        case "inspeccionar":
          return "inspecionar";
        case "inspectar":
          return "inspetar";
        case "interactuar":
          return "interatuar";
        case "intercetar":
          return "interceptar";
        case "intersectar":
          return "intersetar";
        case "liq" + uTreme + "efazer":
          return "liquefazer";
        case "liq" + uTreme + "escer":
          return "liquescer";
        case "liq" + uTreme + "idar":
          return "liquidar";
        case "liq" + uTreme + "idificar":
          return "liquidificar";
        case "percecionar":
          return "percepcionar";
        case "perfetibilizar":
          return "perfectibilizar";
        case "preleccionar":
          return "prelecionar";
        case "projectar":
          return "projetar";
        case "reactivar":
          return "reativar";
        case "reactualizar":
          return "reatualizar";
        case "rebaptizar":
          return "rebatizar";
        case "rececionar":
          return "recepcionar";
        case "recetar":
          return "receptar";
        case "redarg" + uTreme + "ir":
          return "redarguir";
        case "redireccionar":
          return "redirecionar";
        case "reflectir":
          return "refletir";
        case "relinq" + uTreme + "ir":
          return "relinquir";
        case "retractar":
          return "retratar";
        case "retroprojectar":
          return "retroprojetar";
        case "sectorizar":
          return "setorizar";
        case "seleccionar":
          return "selecionar";
        case "selectar":
          return "seletar";
        case "seq" + uTreme + "enciar":
          return "sequenciar";
        case "seq" + uTreme + "estrar":
          return "sequestrar";
        case "sobreelevar":
          return "sobre-elevar";
        case "sobreendividar":
          return "sobre-endividar";
        case "sobreerguer":
          return "sobre-erguer";
        case "sobreexaltar":
          return "sobre-exaltar";
        case "sobreexceder":
          return "sobre-exceder";
        case "sobreexcitar":
          return "sobre-excitar";
        case "sobreexpor":
          return "sobre-expor";
        case "subjectivar":
          return "subjetivar";
        case "subjectivizar":
          return "subjetivizar";
        case "supra-excitar":
          return "supraexcitar";
        case "teledetetar":
          return "teledetectar";
        case "traccionar":
          return "tracionar";
        case "tranq" + uTreme + "ilizar":
          return "tranquilizar";
        case "transaccionar":
          return "transacionar";
        case "ultra-romantizar":
          return "ultrarromantizar";
        case "ung" + uTreme + "entar":
          return "unguentar";
        default:
          String s = verb.replace(uTreme, 'u');
          return s;
      }
    } else if (var == BP && !AO) {
      switch (verb) {
        case "accionar":
          return "acionar";
        case "activar":
          return "ativar";
        case "actualizar":
          return "atualizar";
        case "actuar":
          return "atuar";
        case "adjectivar":
          return "adjetivar";
        case "agroalimentar":
          return "agro-alimentar";
        case "amnistiar":
          return "anistiar";
        case "antissepsiar":
          return "anti-sepsiar";
        case "arguir":
          return "arg" + uTreme + "ir";
        case "arquitectar":
          return "arquitetar";
        case "autoabastecer":
          return "auto-abastecer";
        case "autoadministrar":
          return "auto-administrar";
        case "autoexcitar":
          return "auto-excitar";
        case "autoexcluir":
          return "auto-excluir";
        case "autossugestionar":
          return "auto-sugestionar";
        case "autossuspender":
          return "auto-suspender";
        case "baptizar":
          return "batizar";
        case "coadministrar":
          return "co-administrar";
        case "coarrendar":
          return "co-arrendar";
        case "co-delinquir":
        case "codelinquir":
          return "co-delinq" + uTreme + "ir";
        case "codirigir":
          return "co-dirigir";
        case "coeditar":
          return "co-editar";
        case "cofinanciar":
          return "co-financiar";
        case "cogerir":
          return "co-gerir";
        case "coincinerar":
          return "co-incinerar";
        case "colitigar":
          return "co-litigar";
        case "coobrigar":
          return "co-obrigar";
        case "coparticipar":
          return "co-participar";
        case "coproduzir":
          return "co-produzir";
        case "corresponsabilizar":
          return "co-responsabilizar";
        case "coatar":
          return "coactar";
        case "coleccionar":
          return "colecionar";
        case "concetualizar":
          return "conceptualizar";
        case "confecionar":
          return "confeccionar";
        case "contraindicar":
          return "contra-indicar";
        case "contrainformar":
          return "contra-informar";
        case "contraordenar":
          return "contra-ordenar";
        case "contrarrevolucionar":
          return "contra-revolucionar";
        case "contrasselar":
          return "contra-selar";
        case "dececionar":
          return "decepcionar";
        case "delinquir":
          return "delinq" + uTreme + "ir";
        case "deliquescer":
          return "deliq" + uTreme + "escer";
        case "desactivar":
          return "desativar";
        case "desactualizar":
          return "desatualizar";
        case "desarquitectar":
          return "desarquitetar";
        case "desbaptizar":
          return "desbatizar";
        case "desensanguentar":
          return "desensang" + uTreme + "entar";
        case "desfrequentar":
          return "desfreq" + uTreme + "entar";
        case "desmilinguir":
          return "desmiling" + uTreme + "ir";
        case "difractar":
          return "difratar";
        case "direccionar":
          return "direcionar";
        case "efectivar":
          return "efetivar";
        case "efectuar":
          return "efetuar";
        case "ensanguentar":
          return "ensang" + uTreme + "entar";
        case "equidistar":
          return "eq" + uTreme + "idistar";
        case "equipoler":
          return "eq" + uTreme + "ipoler";
        case "equiponderar":
          return "eq" + uTreme + "iponderar";
        case "exactificar":
          return "exatificar";
        case "exiguificar":
          return "exig" + uTreme + "ificar";
        case "facturar":
          return "faturar";
        case "frequentar":
          return "freq" + uTreme + "entar";
        case "inactivar":
          return "inativar";
        case "infraescapular":
          return "infra-escapular";
        case "injectar":
          return "injetar";
        case "inspeccionar":
          return "inspecionar";
        case "inspectar":
          return "inspetar";
        case "interactuar":
          return "interatuar";
        case "intercetar":
          return "interceptar";
        case "intersetar":
          return "intersectar";
        case "jactar":
          return "jatar";
        case "liquefazer":
          return "liq" + uTreme + "efazer";
        case "liquescer":
          return "liq" + uTreme + "escer";
        case "liquidar":
          return "liq" + uTreme + "idar";
        case "liquidificar":
          return "liq" + uTreme + "idificar";
        case "percecionar":
          return "percepcionar";
        case "perfetibilizar":
          return "perfectibilizar";
        case "preleccionar":
          return "prelecionar";
        case "projectar":
          return "projetar";
        case "reactivar":
          return "reativar";
        case "reactualizar":
          return "reatualizar";
        case "rebaptizar":
          return "rebatizar";
        case "rececionar":
          return "recepcionar";
        case "recetar":
          return "receptar";
        case "redarguir":
          return "redarg" + uTreme + "ir";
        case "redireccionar":
          return "redirecionar";
        case "reflectir":
          return "refletir";
        case "relinquir":
          return "relinq" + uTreme + "ir";
        case "retractar":
          return "retratar";
        case "retroprojectar":
          return "retroprojetar";
        case "sectorizar":
          return "setorizar";
        case "seleccionar":
          return "selecionar";
        case "selectar":
          return "seletar";
        case "sequenciar":
          return "seq" + uTreme + "enciar";
        case "sequestrar":
          return "seq" + uTreme + "estrar";
        case "sobre-elevar":
          return "sobreelevar";
        case "sobre-endividar":
          return "sobreendividar";
        case "sobre-erguer":
          return "sobreerguer";
        case "sobre-exaltar":
          return "sobreexaltar";
        case "sobre-exceder":
          return "sobreexceder";
        case "sobre-excitar":
          return "sobreexcitar";
        case "sobre-expor":
          return "sobreexpor";
        case "subjectivar":
          return "subjetivar";
        case "subjectivizar":
          return "subjetivizar";
        case "supraexcitar":
          return "supra-excitar";
        case "teledetetar":
          return "teledetectar";
        case "traccionar":
          return "tracionar";
        case "tranquilizar":
          return "tranq" + uTreme + "ilizar";
        case "transaccionar":
          return "transacionar";
        case "ultrarromantizar":
          return "ultra-romantizar";
        case "unguentar":
          return "ung" + uTreme + "entar";
        default:
          return verb;
      }
    } else if (var == EP && AO) {
      switch (verb) {
        case "accionar":
          return "acionar";
        case "activar":
          return "ativar";
        case "actualizar":
          return "atualizar";
        case "actuar":
          return "atuar";
        case "adjectivar":
          return "adjetivar";
        case "adoptar":
          return "adotar";
        case "afectar":
          return "afetar";
        case "agro-alimentar":
          return "agroalimentar";
        case "anti-sepsiar":
          return "antissepsiar";
        case "arg" + uTreme + "ir":
          return "arguir";
        case "arquitectar":
          return "arquitetar";
        case "auto-abastecer":
          return "autoabastecer";
        case "auto-administrar":
          return "autoadministrar";
        case "auto-excitar":
          return "autoexcitar";
        case "auto-excluir":
          return "autoexcluir";
        case "auto-sugestionar":
          return "autossugestionar";
        case "auto-suspender":
          return "autossuspender";
        case "baptizar":
          return "batizar";
        case "circunspeccionar":
          return "circunspecionar";
        case "co-administrar":
          return "coadministrar";
        case "co-arrendar":
          return "coarrendar";
        case "co-delinquir":
        case "co-delinq" + uTreme + "ir":
          return "codelinquir";
        case "co-dirigir":
          return "codirigir";
        case "co-editar":
          return "coeditar";
        case "co-financiar":
          return "cofinanciar";
        case "co-gerir":
          return "cogerir";
        case "co-incinerar":
          return "coincinerar";
        case "co-litigar":
          return "colitigar";
        case "co-obrigar":
          return "coobrigar";
        case "co-participar":
          return "coparticipar";
        case "co-produzir":
          return "coproduzir";
        case "co-responsabilizar":
          return "corresponsabilizar";
        case "coactar":
          return "coatar";
        case "coarctar":
          return "coartar";
        case "coleccionar":
          return "colecionar";
        case "colectar":
          return "coletar";
        case "colectivizar":
          return "coletivizar";
        case "confeccionar":
          return "confecionar";
        case "conjecturar":
          return "conjeturar";
        case "contra-indicar":
          return "contraindicar";
        case "contra-informar":
          return "contrainformar";
        case "contra-ordenar":
          return "contraordenar";
        case "contra-revolucionar":
          return "contrarrevolucionar";
        case "contra-selar":
          return "contrasselar";
        case "decepcionar":
          return "dececionar";
        case "dejectar":
          return "dejetar";
        case "delinq" + uTreme + "ir":
          return "delinquir";
        case "deliq" + uTreme + "escer":
          return "deliquescer";
        case "desactivar":
          return "desativar";
        case "desactualizar":
          return "desatualizar";
        case "desafectar":
          return "desafetar";
        case "desarquitectar":
          return "desarquitetar";
        case "desbaptizar":
          return "desbatizar";
        case "deselectrizar":
          return "deseletrizar";
        case "desensang" + uTreme + "entar":
          return "desensanguentar";
        case "desfreq" + uTreme + "entar":
          return "desfrequentar";
        case "desinfeccionar":
          return "desinfecionar";
        case "desinfectar":
          return "desinfetar";
        case "desmiling" + uTreme + "ir":
          return "desmilinguir";
        case "detectar":
          return "detetar";
        case "dialectizar":
          return "dialetizar";
        case "difractar":
          return "difratar";
        case "direccionar":
          return "direcionar";
        case "efectivar":
          return "efetivar";
        case "efectuar":
          return "efetuar";
        case "ejectar":
          return "ejetar";
        case "electrificar":
          return "eletrificar";
        case "electrizar":
          return "eletrizar";
        case "electrocutar":
          return "eletrocutar";
        case "electrocutir":
          return "eletrocutir";
        case "electrolisar":
          return "eletrolisar";
        case "ensang" + uTreme + "entar":
          return "ensanguentar";
        case "eq" + uTreme + "idistar":
          return "equidistar";
        case "eq" + uTreme + "ipoler":
          return "equipoler";
        case "eq" + uTreme + "iponderar":
          return "equiponderar";
        case "exactificar":
          return "exatificar";
        case "excepcionar":
          return "excecionar";
        case "exceptuar":
          return "excetuar";
        case "exig" + uTreme + "ificar":
          return "exiguificar";
        case "expectorar":
          return "expetorar";
        case "extractar":
          return "extratar";
        case "factorizar":
          return "fatorizar";
        case "facturar":
          return "faturar";
        case "flectir":
          return "fletir";
        case "fraccionar":
          return "fracionar";
        case "fracturar":
          return "fraturar";
        case "freq" + uTreme + "entar":
          return "frequentar";
        case "genuflectir":
          return "genufletir";
        case "inactivar":
          return "inativar";
        case "infeccionar":
          return "infecionar";
        case "infectar":
          return "infetar";
        case "inflectir":
          return "infletir";
        case "infra-escapular":
          return "infraescapular";
        case "injectar":
          return "injetar";
        case "inspeccionar":
          return "inspecionar";
        case "inspectar":
          return "inspetar";
        case "insurreccionar":
          return "insurrecionar";
        case "interactuar":
          return "interatuar";
        case "interceptar":
          return "intercetar";
        case "interjeccionar":
          return "interjecionar";
        case "invectivar":
          return "invetivar";
        case "leccionar":
          return "lecionar";
        case "liq" + uTreme + "efazer":
          return "liquefazer";
        case "liq" + uTreme + "escer":
          return "liquescer";
        case "liq" + uTreme + "idar":
          return "liquidar";
        case "liq" + uTreme + "idificar":
          return "liquidificar";
        case "manufacturar":
          return "manufaturar";
        case "objectar":
          return "objetar";
        case "objectivar":
          return "objetivar";
        case "olfactar":
          return "olfatar";
        case "optimizar":
          return "otimizar";
        case "percepcionar":
          return "percecionar";
        case "perspectivar":
          return "perspetivar";
        case "preleccionar":
          return "prelecionar";
        case "projectar":
          return "projetar";
        case "prospectar":
          return "prospetar";
        case "reactivar":
          return "reativar";
        case "reactualizar":
          return "reatualizar";
        case "readoptar":
          return "readotar";
        case "rebaptizar":
          return "rebatizar";
        case "recepcionar":
          return "rececionar";
        case "receptar":
          return "recetar";
        case "rectificar":
          return "retificar";
        case "redarg" + uTreme + "ir":
          return "redarguir";
        case "redireccionar":
          return "redirecionar";
        case "reflectir":
          return "refletir";
        case "refractar":
          return "refratar";
        case "relinq" + uTreme + "ir":
          return "relinquir";
        case "retractar":
          return "retratar";
        case "retroflectir":
          return "retrofletir";
        case "retroprojectar":
          return "retroprojetar";
        case "setorizar":
          return "sectorizar";
        case "seleccionar":
          return "selecionar";
        case "selectar":
          return "seletar";
        case "seq" + uTreme + "enciar":
          return "sequenciar";
        case "seq" + uTreme + "estrar":
          return "sequestrar";
        case "sobreelevar":
          return "sobre-elevar";
        case "sobreendividar":
          return "sobre-endividar";
        case "sobreerguer":
          return "sobre-erguer";
        case "sobreexaltar":
          return "sobre-exaltar";
        case "sobreexceder":
          return "sobre-exceder";
        case "sobreexcitar":
          return "sobre-excitar";
        case "sobreexpor":
          return "sobre-expor";
        case "subjectivar":
          return "subjetivar";
        case "subjectivizar":
          return "subjetivizar";
        case "supra-excitar":
          return "supraexcitar";
        case "susceptibilizar":
          return "suscetibilizar";
        case "tactear":
          return "tatear";
        case "teledetectar":
          return "teledetetar";
        case "traccionar":
          return "tracionar";
        case "tranq" + uTreme + "ilizar":
          return "tranquilizar";
        case "transaccionar":
          return "transacionar";
        case "ultra-romantizar":
          return "ultrarromantizar";
        case "ung" + uTreme + "entar":
          return "unguentar";
        case "vectorizar":
          return "vetorizar";
        default:
          String s = verb.replace(uTreme, 'u');
          return s;
      }
    } else { // var == EP && !AO)
      switch (verb) {
        case "acionar":
          return "accionar";
        case "ativar":
          return "activar";
        case "atualizar":
          return "actualizar";
        case "atuar":
          return "actuar";
        case "adjetivar":
          return "adjectivar";
        case "adotar":
          return "adoptar";
        case "afetar":
          return "afectar";
        case "agroalimentar":
          return "agro-alimentar";
        case "amidalar":
          return "amigdalar";
        case "anistiar":
          return "amnistiar";
        case "antissepsiar":
          return "anti-sepsiar";
        case "aquapunturar":
          return "aquapuncturar";
        case "arg" + uTreme + "ir":
          return "arguir";
        case "arquitetar":
          return "arquitectar";
        case "autoabastecer":
          return "auto-abastecer";
        case "autoadministrar":
          return "auto-administrar";
        case "autoexcitar":
          return "auto-excitar";
        case "autoexcluir":
          return "auto-excluir";
        case "autossugestionar":
          return "auto-sugestionar";
        case "autossuspender":
          return "auto-suspender";
        case "batizar":
          return "baptizar";
        case "bissetar":
          return "bissectar";
        case "caraterizar":
          return "caracterizar";
        case "circunspecionar":
          return "circunspeccionar";
        case "coadministrar":
          return "co-administrar";
        case "coarrendar":
          return "co-arrendar";
        case "codelinquir":
        case "co-delinq" + uTreme + "ir":
          return "co-delinquir";
        case "codirigir":
          return "co-dirigir";
        case "coeditar":
          return "co-editar";
        case "cofinanciar":
          return "co-financiar";
        case "cogerir":
          return "co-gerir";
        case "coincinerar":
          return "co-incinerar";
        case "colitigar":
          return "co-litigar";
        case "coobrigar":
          return "co-obrigar";
        case "coparticipar":
          return "co-participar";
        case "coproduzir":
          return "co-produzir";
        case "corresponsabilizar":
          return "co-responsabilizar";
        case "coatar":
          return "coactar";
        case "coartar":
          return "coarctar";
        case "colecionar":
          return "coleccionar";
        case "coletar":
          return "colectar";
        case "coletivizar":
          return "colectivizar";
        case "confecionar":
          return "confeccionar";
        case "conjeturar":
          return "conjecturar";
        case "contraindicar":
          return "contra-indicar";
        case "contrainformar":
          return "contra-informar";
        case "contraordenar":
          return "contra-ordenar";
        case "contrarrevolucionar":
          return "contra-revolucionar";
        case "contrasselar":
          return "contra-selar";
        case "datilar":
          return "dactilar";
        case "datilografar":
          return "dactilografar";
        case "dececionar":
          return "decepcionar";
        case "defletir":
          return "deflectir";
        case "dejetar":
          return "dejectar";
        case "delinq" + uTreme + "ir":
          return "delinquir";
        case "deliq" + uTreme + "escer":
          return "deliquescer";
        case "desativar":
          return "desactivar";
        case "desatualizar":
          return "desactualizar";
        case "desafetar":
          return "desafectar";
        case "desarquitetar":
          return "desarquitectar";
        case "desbatizar":
          return "desbaptizar";
        case "descaraterizar":
          return "descaracterizar";
        case "deseletrizar":
          return "deselectrizar";
        case "desensang" + uTreme + "entar":
          return "desensanguentar";
        case "desfreq" + uTreme + "entar":
          return "desfrequentar";
        case "desinfecionar":
          return "desinfeccionar";
        case "desinfetar":
          return "desinfectar";
        case "desmiling" + uTreme + "ir":
          return "desmilinguir";
        case "detetar":
          return "detectar";
        case "dialetizar":
          return "dialectizar";
        case "difratar":
          return "difractar";
        case "direcionar":
          return "direccionar";
        case "efetivar":
          return "efectivar";
        case "efetuar":
          return "efectuar";
        case "ejetar":
          return "ejectar";
        case "eletrificar":
          return "electrificar";
        case "eletrizar":
          return "electrizar";
        case "eletrocutar":
          return "electrocutar";
        case "eletrocutir":
          return "electrocutir";
        case "eletrolisar":
          return "electrolisar";
        case "ensang" + uTreme + "entar":
          return "ensanguentar";
        case "eq" + uTreme + "idistar":
          return "equidistar";
        case "eq" + uTreme + "ipoler":
          return "equipoler";
        case "eq" + uTreme + "iponderar":
          return "equiponderar";
        case "exatificar":
          return "exactificar";
        case "excecionar":
          return "excepcionar";
        case "excetuar":
          return "exceptuar";
        case "exig" + uTreme + "ificar":
          return "exiguificar";
        case "expetorar":
          return "expectorar";
        case "extratar":
          return "extractar";
        case "facionar":
          return "faccionar";
        case "fatorizar":
          return "factorizar";
        case "faturar":
          return "facturar";
        case "fletir":
          return "flectir";
        case "fracionar":
          return "fraccionar";
        case "fraturar":
          return "fracturar";
        case "freq" + uTreme + "entar":
          return "frequentar";
        case "genufletir":
          return "genuflectir";
        case "inativar":
          return "inactivar";
        case "infecionar":
          return "infeccionar";
        case "infetar":
          return "infectar";
        case "infletir":
          return "inflectir";
        case "infraescapular":
          return "infra-escapular";
        case "injetar":
          return "injectar";
        case "inspecionar":
          return "inspeccionar";
        case "inspetar":
          return "inspectar";
        case "insurrecionar":
          return "insurreccionar";
        case "interatuar":
          return "interactuar";
        case "intercetar":
          return "interceptar";
        case "interjecionar":
          return "interjeccionar";
        case "intersetar":
          return "intersectar";
        case "invetivar":
          return "invectivar";
        case "jatanciar":
          return "jactanciar";
        case "jatar":
          return "jactar";
        case "lecionar":
          return "leccionar";
        case "liq" + uTreme + "efazer":
          return "liquefazer";
        case "liq" + uTreme + "escer":
          return "liquescer";
        case "liq" + uTreme + "idar":
          return "liquidar";
        case "liq" + uTreme + "idificar":
          return "liquidificar";
        case "manufaturar":
          return "manufacturar";
        case "objetar":
          return "objectar";
        case "objetivar":
          return "objectivar";
        case "olfatar":
          return "olfactar";
        case "otimizar":
          return "optimizar";
        case "percecionar":
          return "percepcionar";
        case "perfetibilizar":
          return "perfectibilizar";
        case "perspetivar":
          return "perspectivar";
        case "prelecionar":
          return "preleccionar";
        case "projetar":
          return "projectar";
        case "prospetar":
          return "prospectar";
        case "reativar":
          return "reactivar";
        case "reatualizar":
          return "reactualizar";
        case "readotar":
          return "readoptar";
        case "rebatizar":
          return "rebaptizar";
        case "rececionar":
          return "recepcionar";
        case "recetar":
          return "receptar";
        case "retificar":
          return "rectificar";
        case "redarg" + uTreme + "ir":
          return "redarguir";
        case "redirecionar":
          return "redireccionar";
        case "refletir":
          return "reflectir";
        case "refratar":
          return "refractar";
        case "relinq" + uTreme + "ir":
          return "relinquir";
        case "retratar":
          return "retractar";
        case "retrofletir":
          return "retroflectir";
        case "retroprojetar":
          return "retroprojectar";
        case "secionar":
          return "seccionar";
        case "setorizar":
          return "sectorizar";
        case "selecionar":
          return "seleccionar";
        case "seletar":
          return "selectar";
        case "setuplicar":
          return "septuplicar";
        case "seq" + uTreme + "enciar":
          return "sequenciar";
        case "seq" + uTreme + "estrar":
          return "sequestrar";
        case "sobre-elevar":
          return "sobreelevar";
        case "sobre-endividar":
          return "sobreendividar";
        case "sobre-erguer":
          return "sobreerguer";
        case "sobre-exaltar":
          return "sobreexaltar";
        case "sobre-exceder":
          return "sobreexceder";
        case "sobre-excitar":
          return "sobreexcitar";
        case "sobre-expor":
          return "sobreexpor";
        case "subjetivar":
          return "subjectivar";
        case "subjetivizar":
          return "subjectivizar";
        case "sutilizar":
          return "subtilizar";
        case "supraexcitar":
          return "supra-excitar";
        case "suscetibilizar":
          return "susceptibilizar";
        case "tatear":
          return "tactear";
        case "teledetetar":
          return "teledetectar";
        case "tracionar":
          return "traccionar";
        case "tranq" + uTreme + "ilizar":
          return "tranquilizar";
        case "transacionar":
          return "transaccionar";
        case "ultrarromantizar":
          return "ultra-romantizar";
        case "ung" + uTreme + "entar":
          return "unguentar";
        case "vetorizar":
          return "vectorizar";
        case "volutuar":
          return "voluptuar";
        default:
          String s = verb.replace(uTreme, 'u');
          return s;
      }
    }
  }
  
  private String[] conjPresInd(int var, boolean AO) {
    // shadowing AO
    resetTables(); // wipe the tables for the current verb
    // determine which paradigm to use when
    // when conjugating this verb
    String type = getVerbType(getInfinitive(var, AO), PRESENT_INDICATIVE);
    stems = new int[]{ 1, 1, 1, 1, 1, 1 };
    stem = getStem(var, AO);
    int len = stem.length();
    
    final String[] regAr = new String[]{ "o", "as", "a", "amos", "ais", "am" };
    final String[] regEr = new String[]{ "o", "es", "e", "emos", "eis", "em" };
    final String[] regIr = new String[]{ "o", "es", "e", "imos", "is", "em" };

    switch (ending) {
      case "ar":
        ends = regAr;
        break;
      case "er":
        ends = regEr;
        break;
      case "ir":
        ends = regIr;
        break;
      default:
        break;
    }

    switch (type) {
      case "ABAIUCAR":
        stem2 = chCharToLast(2, stem, uAcute);
        stems[0] = 14; stems[1] = 14; stems[2] = 14;
        stems[5] = 14;
        break;
      case "-ACUDIR":
        stem2 = chCharToLast(2, stem, 'o');
        stems[1] = 2; stems[2] = 2; 
        stems[5] = 2;
        break;
      case "-AER":
        ends = new String[]{ "io", "is", "i", "emos", "eis", "em" };
        break;
      case "AFIUZAR":
      case "APAULAR":
      case "AUNAR":
      case "AVIUSAR":
      case "AZIUMAR":
      case "-BAULAR":
      case "-CIUMAR":
      case "DESEMBAULAR":
      case "EMBAULAR":
      case "ENVIUSAR":
      case "FAULAR":
      case "SAUDAR":
      case "-VIUVAR":
        stem2 = chCharToLast(2, stem, uAcute);
        stems[0] = 2; stems[1] = 2; stems[2] = 2;
        stems[5] = 2;
        break;
      case "AGENCIAR":   // -iar verbs with two possible conjugations
      case "APRESENCIAR":
      case "CADENCIAR":
      case "COMERCIAR":
      case "DESNEGOCIAR":
      case "DESPREMIAR":
      case "DILIGENCIAR":
      case "LICENCIAR":
      case "OBSEQUIAR":
      case "-NEGOCIAR":
      case "PREMIAR":
      case "PRESENCIAR":
        stem2 = chCharToLast(1, stem, "ei");
        stems[0] = 4; stems[1] = 4; stems[2] = 4;
        stems[5] = 4;
        break;
      case "-AGIR":
        stem2 = chCharToLast(1, stem, 'j');
        stems[0] = 2;
        break;
      case "-AGUAR":
        stem2 = chCharToLast(3, stem, aAcute);
        stems[0] = 22; stems[1] = 22; stems[2] = 22;
        stems[5] = 22;
        break;
      case "-AIR":
        ends = new String[]{ "io", "is", "i", iAcute + "mos", iAcute + "s",
        "em" };
        break;
      case "-AIZAR":
      case "-EIZAR":
      case "-OIZAR":
      case "-UIZAR":
        stem2 = chCharToLast(2, stem, iAcute);
        stems[0] = 2; stems[1] = 2; stems[2] = 2;
        stems[5] = 2;
        break;
      case "AJESUITAR":
      case "RUIDAR":
      case "-RUINAR":
        stem2 = chCharToLast(2, stem, iAcute);
        stems[0] = 2; stems[1] = 2; stems[2] = 2;
        stems[5] = 2;
        break;
      case "-ANQUIR":
        stem2 = stem.substring(0, len - 2) + 'c';
        stems[0] = 2;
        break;
      case "ANSIAR":      // irregular -iar verbs
      case "ARREMEDIAR":
      case "DESARREMEDIAR":
      case "DESREMEDIAR":
      case "INCENDIAR":
      case "INTERMEDIAR":
      case "MEDIAR":
      case "ODIAR":
      case "PROMEDIAR":
        stem2 = chCharToLast(1, stem, "ei");
        stems[0] = 2; stems[1] = 2; stems[2] = 2;
        stems[5] = 2;
        break;
      case "APRAZER":
      case "DESAPRAZER":
      case "PRAZER":
      case "-PRAZER":
        ends = new String[]{ "o", "es", "", "emos", "eis", "em" };
        break;
      case "-ARGUIR": // takes care of u with treme for brSemAO
      case "-ARG" + uTremeUp + "IR":
        ends = new String[]{ "o", "is", "i", "imos", "is", "em" };
        stem2 = chCharToLast(1, stem, uTreme);
        stem3 = chCharToLast(1, stem, uAcute);
        stem4 = chCharToLast(1, stem, 'u');
        stems[0] = 31; stems[1] = 20; stems[2] = 20;
        stems[3] = 16; stems[4] = 16; stems[5] = 20;
        break;
      case "ASPERGIR":
      case "CONVERGIR":
      case "DIVERGIR":
        stem2 = stem.substring(0, len - 3) + "irj";
        stems[0] = 2;
        break;
      case "ATEIZAR":
        stem2 = chCharToLast(2, stem, iAcute);
        stems[0] = 10; stems[1] = 10; stems[2] = 10;
        stems[5] = 10;
        break;
      case "-BALAUSTRAR":
        stem2 = chCharToLast(4, stem, uAcute);
        stems[0] = 2; stems[1] = 2; stems[2] = 2;
        stems[5] = 2;
        break;
      case "-CABER":
        stem2 = chCharToLast(2, stem, "ai");
        stems[0] = 2;
        break;
      case "-CER":
        stem2 = chCharToLast(1, stem, cCedil);
        stems[0] = 2;
        break;
      case "CERZIR":
        stem2 = chCharToLast(3, stem, 'i');
        stems[1] = 4; stems[2] = 4;
        stems[5] = 4;
        break;
      case "-CIR":
        stem2 = chCharToLast(1, stem, cCedil);
        stems[0] = 2;
        break;
      case "-COBRIR":
        stem2 = chCharToLast(3, stem, 'u');
        stems[0] = 2;
        break;
      case "-CONSTRUIR":
      case "DESTRUIR":
        ends = new String[]{ "o", "is", "i", iAcute + "mos", iAcute + "s",
            "em" };
        stem2 = chCharToLast(1, stem, oAcute);
        stems[1] = 4; stems[2] = 4;
        stems[5] = 4;
        break;
      case "CONSUMIR":
      case "SUBIR":
      case "SUMIR":
        stem2 = chCharToLast(2, stem, 'o');
        stems[1] = 2; stems[2] = 2;
        stems[5] = 2;
        break;
      case "CRER": // double check this one
      case "DESCRER":
      case "LER":
      case "-LER":
      case "RER": // special case, antiquated verb so does AO apply?
        ends = new String[]{ "io", "s", "", "mos", "des", "em" };
        stem2 = stem + 'e';
        stem3 = stem + eCFlex;
        stems[0] = 2; stems[1] = 3; stems[2] = 3;
        stems[3] = 2; stems[4] = 2; stems[5] = 7;
        break;
      case "-CUSPIR":
      case "-GUSPIR":
        stem2 = chCharToLast(3, stem, 'o');
        stems[1] = 2; stems[2] = 2;
        stems[5] = 2;
        break;
      case "DAR":
      case "-DAR":
        ends = new String[]{ "ou", aAcute + "s", aAcute + "", "amos", "ais",
            aTilde + "o" };
        break;
      case "-DELINQUIR":
        stem2 = stem.substring(0, stem.length() - 4);
        // need special behavior for this very unique verb
        bpConjAO = new String[]{ stem2 + "inquo/" + stem2 + iAcute + "nquo",
             stem2 + "inquis/" + stem2 + iAcute + "nques", 
             stem2 + "inqui/" + stem2 + iAcute + "nque",
             stem2 + "inquimos", stem2 + "inquis", stem2 + "inquem/" + 
             stem2 + iAcute + "nquem" };
        bpConjNoAO = new String[]{ null, stem2 + iAcute + "nq" + uTreme + "es",
            stem2 + iAcute + "nq" + uTreme + "e",
            stem2 + "inq" + uTreme + "imos",
            stem2 + "inq" + uTreme + "is",
            stem2 + iAcute + "nq" + uTreme + "em" };
        epConjAO = new String[]{ stem2 + "inquo/" + stem2 + iAcute + "nquo",
            stem2 + "inquis/" + stem2 + iAcute + "nques", 
            stem2 + "inqui/" + stem2 + iAcute + "nque",
            stem2 + "inquimos", stem2 + "inquis", stem2 + "inquem/" + stem2 + 
            iAcute + "nquem" };
        epConjNoAO = new String[]{ stem2 + "inquo/" + stem2 + iAcute + "nquo",
            stem2 + "inq" + uAcute + "is/" + stem2 + iAcute + "nques",
            stem2 + "inq" + uAcute + "i/" + stem2 + iAcute + "nque",
            stem2 + "inquimos", stem2 + "inquis",
            stem2 + "inq" + uAcute + "em/" + stem2 + iAcute + "nquem" };
        stems = new int[]{ 19, 19, 19, 19, 19, 19 };
        break;
      case "DENEGRIR":
        stem2 = chCharToLast(3, stem, 'i');
        stems[0] = 2; stems[1] = 2; stems[2] = 2;
        stems[5] = 2;
        break;
      case "DESMILINGUIR":
        bpConjAO = new String[]{ "desmil" + iAcute + "nguo",
            "desmil" + iAcute + "ngues", "desmil" + iAcute + "ngue",
            "desmil" + iAcute + "nguimos", "desmil" + iAcute + "nguis",
            "desmil" + iAcute + "nguem" };
        bpConjNoAO = new String[]{ "desmil" + iAcute + "nguo",
            "desmil" + iAcute + "ng" + uTreme + "es",
            "desmil" + iAcute + "ng" + uTreme + "e",
            "desmil" + iAcute + "ng" + uTreme + "imos",
            "desmil" + iAcute + "ng" + uTreme + "is",
            "desmil" + iAcute + "ng" + uTreme + "em"};
        epConjAO = new String[]{ "desmilinguo", "desmiling" + uAcute + "is",
            "desmiling" + uAcute + "i", "desmilinguimos", "desmilinguis",
            "desmiling" + uAcute + "em" };
        epConjNoAO = new String[]{ "desmilinguo", "desmiling" + uAcute + "is",
            "desmiling" + uAcute + "i", "desmilinguimos", "desmilinguis",
            "desmiling" + uAcute + "em" };
        stems = new int[]{ 19, 19, 19, 19, 19, 19 };
        break;
      case "-DIZER":
        ends = new String[]{ "o", "es", "", "emos", "eis", "em" };
        stem2 = chCharToLast(1, stem, 'g');
        stems[0] = 2;
        break;
      case "-DORMIR":
        stem2 = chCharToLast(3, stem, 'u');
        stems[0] = 2;
        break;
      case "-EAR":
        stem2 = stem + 'i';
        stems[0] = 2; stems[1] = 2; stems[2] = 2;
        stems[5] = 2;
        break;
      case "-ECTIR":
      case "-ERTIR":
      case "-ESPIR":
      case "-ESTIR":
        stem2 = chCharToLast(3, stem, 'i');
        stems[0] = 2;
        break;
      case "-EDIR":
        stem2 = chCharToLast(2, stem, 'i');
        stems[0] = 2;
        break;
      case "-EGER":
        stem2 = chCharToLast(1, stem, 'j');
        stems[0] = 2;
        break;
      case "-EGUAR": // verify
        stem2 = chCharToLast(3, stem, eAcute);
        stems[0] = 29; stems[1] = 29; stems[2] = 29;
        stems[5] = 29;
        break;
      case "-EGUIR":
        stem2 = stem.substring(0, len - 3) + "ig";
        stems[0] = 2;
        break;
      case "-EG" + uTremeUp + "IR":
        stem2 = chCharToLast(1, stem, 'u');
        stem2 = chCharToLast(3, stem2, 'i');
        stems[0] = 2;
        break;
      case "-ELIR":
      case "-EMIR":
      case "-ENIR":
      case "-ERIR":
      case "-ETIR":
        stem2 = chCharToLast(2, stem, 'i');
        stems[0] = 2;
        break;
      case "-EMBAIR": // irregular defective
        ends = new String[]{ "io", "es", "e", iAcute + "mos", iAcute + "s",
            "em" };
        break;
      case "EMBAUCAR":
      case "ESMIU" + cCedilUp + "AR":
        stem2 = chCharToLast(2, stem, uAcute);
        stems[0] = 2; stems[1] = 2; stems[2] = 2;
        stems[5] = 2;
        break;
      case "-ENGOLIR":
        stem2 = chCharToLast(2, stem, 'u');
        stems[0] = 2;
        break;
      case "-ENHIR":
      case "-ENTIR":
      case "-ERVIR":
      case "-ERZIR":
        stem2 = chCharToLast(3, stem, 'i');
        stems[0] = 2;
        break;
      case "ENSIMESMAR":
        bpConjAO = new String[]{ "ensimesmo/enmimmesmo",
            "ensimesmas/entimesmas", "ensimesma", "ensimesmamos/ennosmesmamos",
            "ensimesmais/envosmesmais", "ensimesmam" };
        bpConjNoAO = new String[]{ "ensimesmo/enmimmesmo",
            "ensimesmas/entimesmas", "ensimesma", "ensimesmamos/ennosmesmamos",
            "ensimesmais/envosmesmais", "ensimesmam" };
        epConjAO = new String[]{ "ensimesmo/enmimmesmo",
            "ensimesmas/entimesmas", "ensimesma", "ensimesmamos/ennosmesmamos",
            "ensimesmais/envosmesmais", "ensimesmam" };
        epConjNoAO = new String[]{ "ensimesmo/enmimmesmo",
            "ensimesmas/entimesmas", "ensimesma", "ensimesmamos/ennosmesmamos",
            "ensimesmais/envosmesmais", "ensimesmam" };
        stems = new int[]{ 19, 19, 19, 19, 19, 19 };
        break;
      case "-ENTUPIR":
        stem2 = chCharToLast(2, stem, 'o');
        stems[1] = 4; stems[2] = 4;
        stems[5] = 4;
        break;
      case "-EQUAR":  // both forms in br perhaps?
        stem2 = chCharToLast(3, stem, eAcute);
        stems[0] = 15; stems[1] = 15; stems[2] = 15;
        stems[5] = 15;
        break;
      case "-ERGIR":
        stem2 = stem.substring(0, len - 3) + "irj";
        stem3 = chCharToLast(1, stem, 'j');
        stems[0] = 5; // both??
        break;
      case "-ERGUER":
        stem2 = chCharToLast(1, stem, "");
        stems[0] = 2;
        break;
      case "-ERNIR":
        stem2 = chCharToLast(3, stem, 'i');
        stems[0] = 2;
        break;
      case "ESTAR":
      case "-ESTAR":
        ends = new String[]{ "ou", aAcute + "s", aAcute + "", "amos", "ais",
            aTilde + "o" };
        break;
      case "EXPLODIR":
        stem2 = chCharToLast(2, stem, 'u');
        stems[0] = 4;
        break;
      case "FAISCAR":
        stem2 = chCharToLast(3, stem, iAcute);
        stems[0] = 2; stems[1] = 2; stems[2] = 2;
        stems[5] = 2;
        break;
      case "-FAZER":
        ends = new String[]{ "o", "es", "", "emos", "eis", "em" };
        stem2 = chCharToLast(1, stem, cCedil);
        stems[0] = 2;
        break;
      case "FRIGIR":
        stem2 = chCharToLast(1, stem, 'j');
        stem3 = chCharToLast(2, stem, 'e');
        stems[0] = 2; stems[1] = 3; stems[2] = 3;
        stems[5] = 3;
        break;
      case "FUGIR":
        stem2 = chCharToLast(1, stem, 'j');
        stem3 = chCharToLast(2, stem, 'o');
        stems[0] = 2; stems[1] = 3; stems[2] = 3;
        stems[5] = 3;
        break;
      case "-GAUCHAR":
        stem2 = chCharToLast(3, stem, uAcute);
        stems[0] = 2; stems[1] = 2; stems[2] = 2;
        stems[5] = 2;
        break;
      case "-GER":
        stem2 = chCharToLast(1, stem, 'j');
        stems[0] = 2;
        break;
      case "-GIR":
        stem2 = chCharToLast(1, stem, 'j');
        stems[0] = 2;
        break;
      case "-GREDIR":
        stem2 = chCharToLast(2, stem, 'i');
        stems[0] = 2; stems[1] = 2; stems[2] = 2;
        stems[5] = 2;
        break;
      case "-G" + uTremeUp + "ER":
        stem2 = chCharToLast(1, stem, "u");
        stems[0] = 2;
        break;
      case "-GUIR":
        String last2 = verb.substring(verb.length() - 4, verb.length() - 2);
        if (last2.equals("g" + uTreme)) {
          stem2 = stem;
        } else {
          stem2 = chCharToLast(1, stem, "");
        }
        stems[0] = 2;
        break;
      case "-G" + uTremeUp + "IR":
        stem2 = chCharToLast(1, stem, "u");
        stems[0] = 2;
        break;
      case "-GUIZAR":
      case "-QUIZAR":
        // regular -ar verb as 'u' is silent
        break;
      case "HAVER":
        ends = new String[]{ "ei", aAcute + "s", aAcute + "", "emos", "eis",
            aTilde + "o" };
        stem2 = stem.substring(0, len - 2);
        stems[0] = 2; stems[1] = 2; stems[2] = 2;
        stems[3] = 4; stems[4] = 4; stems[5] = 2;
        break;
      case "-IGUAR":
      case "-IQUAR":
        stem2 = chCharToLast(3, stem, iAcute);
        stems[0] = 15; stems[1] = 15; stems[2] = 15;
        stems[5] = 15;
        break;
      case "-INGUAR":
      case "-INQUAR":
        stem2 = chCharToLast(4, stem, iAcute);
        stems[0] = 4; stems[1] = 4; stems[2] = 4;
        stems[5] = 4;
        break;
      case "IR":
      case "SOBREIR":
        ends = new String[]{ "vou", "v" + aAcute + "s", "v" + aAcute, "vamos",
            "ides", "v" + aTilde + "o" };
        break;
      case "-JAZER":
        ends = new String[]{ "o", "es", "", "emos", "eis", "em" };
        break;
      case "-MEDIR":
      case "-PEDIR":
        stem2 = chCharToLast(1, stem, cCedil);
        stems[0] = 2;
        break;
      case "-MOBILIAR":
        stem2 = chCharToLast(3, stem, iAcute);
        stems[0] = 2; stems[1] = 2; stems[2] = 2;
        stems[5] = 2;
        break;
      case "-OAR":
        stem2 = chCharToLast(1, stem, oCFlex);
        stems[0] = 16;
        break;
      case "-OER":
        ends = new String[]{ "o", "is", "i", "emos", "eis", "em" };
        stem2 = chCharToLast(1, stem, oCFlex);
        stem3 = chCharToLast(1, stem, oAcute);
        stems[0] = 16;
        stems[1] = 3; stems[2] = 3;
        break;
      case "-OIAR":
        stem2 = chCharToLast(2, stem, oAcute);
        stems[0] = 13; stems[1] = 13; stems[2] = 13;
        stems[5] = 13;
        break;
      case "-OIBIR":
        stem2 = chCharToLast(2, stem, iAcute);
        stems[0] = 2; stems[1] = 2; stems[2] = 2;
        stems[5] = 2;
        break;
      case "-OUVIR":
        stem2 = chCharToLast(1, stem, cCedil);
        stem3 = chCharToLast(2, stem2, 'i');
        stems[0] = 5;
        break;
      case "PARAR":
        stem2 = chCharToLast(2, stem, aAcute);
        stems[2] = 13;
        break;
      case "-PARIR": // needs more research
        stem2 = stem;
        stem = chCharToLast(1, stem, "ir");
        stems[0] = 4;
        break;
      case "-PERDER":
        stem2 = chCharToLast(1, stem, 'c');
        stems[0] = 2;
        break;
      case "-PODER":
        stem2 = chCharToLast(1, stem, "ss");
        stems[0] = 2;
        break;
      case "-POLIR":
        stem2 = chCharToLast(2, stem, 'u');
        stems[0] = 2; stems[1] = 2; stems[2] = 2;
        stems[5] = 2;
        break;
      case "P" + oCFlexUp + "R":
      case "-POR":
        ends = new String[]{ "onho", oTilde + "es", oTilde + "e", "omos",
            "ondes", oTilde + "em" };
        break;
      case "PUITAR":
        stem2 = chCharToLast(2, stem, iAcute);
        stems[0] = 2; stems[1] = 2; stems[2] = 2;
        stems[5] = 2;
        break;
      case "-QUERER":
        ends = new String[]{ "o", "es", "e", "emos", "eis", "em" };
        stem2 = stem + "/" + stem;
        stems[2] = 2;
        break;
      case "-QUIR":
      case "-Q" + uTremeUp + "IR":
        stems[0] = 0;
        break;
      case "REQUERER":
        ends = new String[]{ "o", "es", "e", "emos", "eis", "em" };
        stem2 = stem + "/" + stem;
        stem3 = chCharToLast(2, stem, "ei");
        stems[0] = 3; stems[2] = 2;
        break;
      case "RETORQUIR":
        //stem2 = chCharToLast(1, stem, uTreme);
        //stem3 = chCharToLast(4, stem2, oAcute);
        stems[0] = 0; 
        //stems[1] = 17; stems[2] = 17;
        //stems[3] = 18; stems[4] = 18; stems[5] = 17;
        break;
      case "RETORQ" + uTremeUp + "IR":
        stem2 = chCharToLast(4, stem, oAcute);
        stems[0] = 0; stems[1] = 2; stems[2] = 2;
        stems[5] = 2;
        break;
      case "REUNIR":
        stem2 = chCharToLast(2, stem, uAcute);
        stems[0] = 2; stems[1] = 2; stems[2] = 2;
        stems[5] = 2;
        break;
      case "RIR":
      case "-SORRIR":
        ends = new String[] { "io", "is", "i", "imos", "ides", "iem" };
        break;
      case "-SABER":
        ends = new String[]{ "ei", "es", "e", "emos", "eis", "em" };
        stem2 = stem.substring(0, len - 2);
        stems[0] = 2;
        break;
      case "SER":
        ends = new String[]{ "ou", eAcute + "s", eAcute + "", "omos", "ois",
            aTilde + "o" };
        stem2 = stem.substring(0, len - 1);
        stems[1] = 2;
        stems[2] = 2;
        break;
      case "SOBRESSER": // two 's' situation
        ends = new String[]{ "ou", eAcute + "s", eAcute + "", "omos", "ois",
            aTilde + "o" };
        stem2 = stem.substring(0, len - 2);
        stems[1] = 2;
        stems[2] = 2;
        break;
      case "SORTIR":
        stem2 = chCharToLast(3, stem, 'u');
        stems[0] = 2; stems[1] = 2; stems[2] = 2;
        stems[5] = 2;
        break;
      case "TER":
        ends = new String[]{ "enho", "ens", "em", "emos", "endes", 
            eCFlex + "m" };
        break;
      case "-TER":
        ends = new String[]{ "enho", eAcute + "ns", eAcute + "m", "emos",
            "endes", eCFlex + "m" };
        break;
      case "TOSSIR":
        stem2 = chCharToLast(3, stem, 'u');
        stems[0] = 2;
        break;
      case "-TRAZER":
        ends = new String[]{ "o", "es", "", "emos", "eis", "em" };
        stem2 = chCharToLast(1, stem, 'g');
        stems[0] = 2;
        break;
      case "-UIR":
        ends = new String[]{ "o", "is", "i", iAcute + "mos", iAcute + "s",
            "em" };
        break;
      case "-ULIR":
        stem2 = chCharToLast(2, stem, 'o');
        stems[1] = 2; stems[2] = 2;
        stems[5] = 2;
        break;
      case "-UZIR":
        ends = new String[]{ "o", "es", "", "imos", "eis", "em" };
        break;
      case "-VALER":
        ends = new String[]{ "o", "es", "e", "emos", "eis", "em" };
        stem2 = stem + 'h';
        stems[0] = 2;
        break;
      case "VER":
      case "-VER":
        ends = new String[]{ "jo", "s", "", "mos", "des", "em" };
        stem2 = stem + 'e';
        stem3 = stem + eCFlex;
        stems[0] = 2; stems[1] = 3; stems[2] = 3;
        stems[3] = 2; stems[4] = 2; stems[5] = 7;
        break;
      case "VIR":
        ends = new String[] { "enho", "ens", "em", "imos", "indes", eCFlex +
            "m" };
        break;
      case "-VIR":
        ends = new String[] { "enho", eAcute + "ns", eAcute + "m", "imos",
            "indes", eCFlex + "m" };
        break;
      default: // regular verbs
        break;
    }
    
    if (hasNoFstPerSing()) {
      stems[0] = 0;
    } else if (hasOnlyArriz()) {
      stems[0] = 0; stems[1] = 0; stems[2] = 0;
      stems[5] = 0;
    } else if (hasOnlyThPerSing()) {
      stems[0] = 0; stems[1] = 0;
      stems[3] = 0; stems[4] = 0; stems[5] = 0;
    } else if (hasOnlyThPer()) {
      stems[0] = 0; stems[1] = 0;
      stems[3] = 0; stems[4] = 0;
    }

    for (int i = 0; i < 6; i++) {
      switch (stems[i]) {
        case 0:
          bpConjAO[i] = null;
          bpConjNoAO[i] = null;
          epConjAO[i] = null;
          epConjNoAO[i] = null;
          break;
        case 1:
          bpConjAO[i] = stem + ends[i];
          bpConjNoAO[i] = stem + ends[i];
          epConjAO[i] = stem + ends[i];
          epConjNoAO[i] = stem + ends[i];
          break;
        case 2:
          bpConjAO[i] = stem2 + ends[i];
          bpConjNoAO[i] = stem2 + ends[i];
          epConjAO[i] = stem2 + ends[i];
          epConjNoAO[i] = stem2 + ends[i];
          break;
        case 3:
          bpConjAO[i] = stem3 + ends[i];
          bpConjNoAO[i] = stem3 + ends[i];
          epConjAO[i] = stem3 + ends[i];
          epConjNoAO[i] = stem3 + ends[i];
          break;
        case 4: // use stems "stem" and "stem2" both
          bpConjAO[i] = stem + ends[i] + "/" + stem2 + ends[i];
          bpConjNoAO[i] = stem + ends[i] + "/" + stem2 + ends[i];
          epConjAO[i] = stem + ends[i] + "/" + stem2 + ends[i];
          epConjNoAO[i] = stem + ends[i] + "/" + stem2 + ends[i];
          break;
        case 5: // use stems "stem2" and "stem3" both
          bpConjAO[i] = stem2 + ends[i] + "/" + stem3 + ends[i];
          bpConjNoAO[i] = stem2 + ends[i] + "/" + stem3 + ends[i];
          epConjAO[i] = stem2 + ends[i] + "/" + stem3 + ends[i];
          epConjNoAO[i] = stem2 + ends[i] + "/" + stem3 + ends[i];
          break;
        case 7: // use "stem3" for ptSemAO and brSemAO,
          // and "stem2" for ptComAO and brComAO
          bpConjAO[i] = stem2 + ends[i];
          bpConjNoAO[i] = stem3 + ends[i];
          epConjAO[i] = stem2 + ends[i];
          epConjNoAO[i] = stem3 + ends[i];
          break;
        case 10: // different stem between br and pt
          bpConjAO[i] = stem2 + ends[i];
          bpConjNoAO[i] = stem2 + ends[i];
          epConjAO[i] = stem + ends[i];
          epConjNoAO[i] = stem + ends[i];
          break;
        case 13: // use "stem2" for ptSemAO and brSemAO,
          // and "stem" for ptComAO and brComAO
          bpConjAO[i] = stem + ends[i];
          bpConjNoAO[i] = stem2 + ends[i];
          epConjAO[i] = stem + ends[i];
          epConjNoAO[i] = stem2 + ends[i];
          break;
        case 14: // use "stem2" for pt and brSemAO,
          // and "stem" for brComAO
          bpConjAO[i] = stem + ends[i];
          bpConjNoAO[i] = stem2 + ends[i];
          epConjAO[i] = stem2 + ends[i];
          epConjNoAO[i] = stem2 + ends[i];
          break;
        case 15: // use stems "stem" and "stem2" both in br
          bpConjAO[i] = stem + ends[i] + "/" + stem2 + ends[i];
          bpConjNoAO[i] = stem + ends[i] + "/" + stem2 + ends[i];
          epConjAO[i] = stem + ends[i];
          epConjNoAO[i] = stem + ends[i];
          break;
        case 16: // use "stem" for pt and brComAO,
          // and "stem2" for brSemAO
          bpConjAO[i] = stem + ends[i];
          bpConjNoAO[i] = stem2 + ends[i];
          epConjAO[i] = stem + ends[i];
          epConjNoAO[i] = stem + ends[i];
          break;
        case 17: // use "stem" for pt and brComAO,
          // and both "stem" and "stem2" for brSemAO
          bpConjAO[i] = stem + ends[i];
          bpConjNoAO[i] = stem + ends[i] + "/" + stem2 + ends[i];
          epConjAO[i] = stem + ends[i];
          epConjNoAO[i] = stem + ends[i];
          break;
        case 18: // use "stem" for pt and brComAO,
          // and both "stem" and "stem3" for brSemAO
          bpConjAO[i] = stem + ends[i];
          bpConjNoAO[i] = stem + ends[i] + "/" + stem3 + ends[i];
          epConjAO[i] = stem + ends[i];
          epConjNoAO[i] = stem + ends[i];
          break;
        case 20: // use "stem3" for ptSemAO
          bpConjAO[i] = stem + ends[i];
          bpConjNoAO[i] = stem + ends[i];
          epConjAO[i] = stem + ends[i];
          epConjNoAO[i] = stem3 + ends[i];
          break;
        case 22: // use both "stem" and "stem2" for ptComAO and brComAO,
                 // and "stem2" for brSemAO
          bpConjAO[i] = stem + ends[i] + "/" + stem2 + ends[i];
          bpConjNoAO[i] = stem2 + ends[i];
          epConjAO[i] = stem + ends[i] + "/" + stem2 + ends[i];
          epConjNoAO[i] = stem + ends[i];
          break;
        case 23: // use "stem" for pt and brComAO,
          // and "stem3" for brSemAO
          bpConjAO[i] = stem + ends[i];
          bpConjNoAO[i] = stem3 + ends[i];
          epConjAO[i] = stem + ends[i];
          epConjNoAO[i] = stem + ends[i];
          break;
        case 29: // use stems "stem" and "stem2" both in brComAO
          bpConjAO[i] = stem + ends[i] + "/" + stem2 + ends[i];
          bpConjNoAO[i] = stem + ends[i];
          epConjAO[i] = stem + ends[i];
          epConjNoAO[i] = stem + ends[i];
          break;
        case 31:
          bpConjAO[i] = stem4 + ends[i];
          bpConjNoAO[i] = stem4 + ends[i];
          epConjAO[i] = stem4 + ends[i];
          epConjNoAO[i] = stem4 + ends[i];
          break;
        default: // do absolutely nothing (delinquir/ensimesmar)
          break;
      }
    }

    if (var == BP && AO) {
      return bpConjAO;
    } else if (var == BP && !AO) {
      return bpConjNoAO;
    } else if (var == EP && AO) {
      return epConjAO;
    } else {
      return epConjNoAO;
    }
  }
  
  private String[] conjPretImp(int var, boolean AO) {
    // shadowing AO
    resetTables(); // wipe the tables for the current verb
    // determine which paradigm to use when
    // when conjugating this verb
    String type = getVerbType(getInfinitive(var, AO), PRETERITE_IMPERFECT);
    stems = new int[]{ 1, 1, 1, 1, 1, 1 };
    stem = getStem(var, AO);
    int len = stem.length();
    
    final String[] regAr = new String[]{
        "ava", "avas", "ava", aAcute + "vamos", aAcute + "veis", "avam" };
    final String[] regEr = new String[]{
        "ia", "ias", "ia", iAcute + "amos", iAcute + "eis", "iam" };
    final String[] regIr = new String[]{
        "ia", "ias", "ia", iAcute + "amos", iAcute + "eis", "iam" };
    
    switch (ending) {
      case "ar":
        ends = regAr;
        break;
      case "er":
        ends = regEr;
        break;
      case "ir":
        ends = regIr;
        break;
      default:
        break;
    }
    
    switch (type) {
      case "-AER":
      case "-AIR":
      case "-OER":
      case "-OIR":
      case "-UER":
      case "-UIR":
        ends = new String[]{ iAcute + "a", iAcute + "as", iAcute + "a",
            iAcute + "amos", iAcute + "eis", iAcute + "am" };
        break;
      case "ENSIMESMAR":
        if (var == BP) {
          return new String[]{ "ensimesmava/enmimmesmava",
            "ensimesmavas/entimesmavas", "ensimesmava",
            "ensimesm" + aAcute + "vamos/ennosmesm" + aAcute + "vamos",
            "ensimesm" + aAcute + "veis/envosmesm" + aAcute + "veis",
            "ensimesmavam" };
        } else {
          return new String[]{ "ensimesmava/enmimmesmava",
              "ensimesmavas/entimesmavas", "ensimesmava",
              "ensimesm" + aAcute + "vamos/ennosmesm" + aAcute + "vamos",
              "ensimesm" + aAcute + "veis/envosmesm" + aAcute + "veis",
              "ensimesmavam" };
        }
      case "-GUER":
      case "-GUIR":
      case "-QUIR":
        break;
      case "-POR":
        ends = new String[]{ "unha", "unhas", "unha", uAcute + "nhamos",
            uAcute + "nheis", "unham" };
        break;
      case "SER":
        stem = stem.substring(0, len - 1);
        ends = new String[]{ "era", "eras", "era", eAcute + "ramos",
            eAcute + "reis", "eram" };
        break;
      case "SOBRESSER":
        stem = stem.substring(0, len - 2);
        ends = new String[]{ "era", "eras", "era", eAcute + "ramos",
            eAcute + "reis", "eram" };
        break;
      case "TER":
      case "-TER":
      case "VIR":
      case "-VIR":
        ends = new String[]{ "inha", "inhas", "inha", iAcute + "nhamos",
            iAcute + "nheis", "inham" };
        break;
      default:
        break;
    } 
    if (hasOnlyThPerSing()) {
      stems[0] = 0; stems[1] = 0;
      stems[3] = 0; stems[4] = 0; stems[5] = 0;
    } else if (hasOnlyThPer()) {
      stems[0] = 0; stems[1] = 0;
      stems[3] = 0; stems[4] = 0;
    }

    // there is no known distinction between semAO/comAO or BP/EP
    String[] allConj = new String[6];
    for (int i = 0; i < 6; i++) {
      if (stems[i] != 0) {
        allConj[i] = stem + ends[i];
      } else {
        allConj[i] = null;
      }
    }

    return allConj;
  }
  
  private String[] conjPretPerf(int var, boolean AO) {
    // shadowing AO
    resetTables(); // wipe the tables for the current verb
    // determine which paradigm to use when
    // when conjugating this verb
    String type = getVerbType(getInfinitive(var, AO), PRETERITE_PERFECT);
    stems = new int[]{ 1, 1, 1, 1, 1, 1 };
    stem = getStem(var, AO);
    int len = stem.length();
    
    final String[] regAr = new String[]{ "ei", "aste", "ou", "amos",
        "astes", "aram" };
    final String[] regEr = new String[]{ "i", "este", "eu", "emos", "estes",
        "eram" };
    final String[] regIr = new String[]{ "i", "iste", "iu", "imos", "istes",
        "iram" };
    
    switch (ending) {
      case "ar":
        ends = regAr;
        break;
      case "er":
        ends = regEr;
        break;
      case "ir":
        ends = regIr;
        break;
      default:
        break;
    }

    switch (type) {
      case "-AER":
      case "-OER":
        ends = new String[]{ iAcute + "", "este", "eu", "emos", "estes",
        "eram" };
        break;
      case "-AIR":
      case "-UIR":
        ends = new String[]{ iAcute + "", iAcute + "ste", "iu", iAcute + "mos",
            iAcute + "stes", iAcute + "ram" };
        break;
      case "-ABER":
        ends = new String[]{ "e", "este", "e", "emos", "estes",
        "eram" };
        stem2 = stem.substring(0, len - 2) + "oub";
        stems[0] = 2; stems[1] = 2; stems[2] = 2;
        stems[3] = 2; stems[4] = 2; stems[5] = 2;
        break;
      case "-CAR":
        stem2 = chCharToLast(1, stem, "qu");
        stems[0] = 2;
        break;
      case "-" + cCedilUp + "AR":
        stem2 = chCharToLast(1, stem, 'c');
        stems[0] = 2;
        break;
      case "DAR":
      case "-DAR":
        ends = new String[]{ "ei", "este", "eu", "emos", "estes", "eram" };
        break;
      case "-DIZER":
        ends = new String[]{ "e", "este", "e", "emos", "estes", "eram" };
        stem2 = chCharToLast(1, stem, "ss");
        stems[0] = 2; stems[1] = 2; stems[2] = 2;
        stems[3] = 2; stems[4] = 2; stems[5] = 2;
        break;
      case "ENSIMESMAR":
        bpConjAO = new String[]{ "ensimesmei/enmimmesmei",
            "ensimesmaste/entimesmaste", "ensimesmou", "ensimesmamos/ennosmesmamos",
            "ensimesmastes/envosmesmastes", "ensimesmaram" };
        bpConjNoAO = new String[]{ "ensimesmei/enmimmesmei",
            "ensimesmaste/entimesmaste", "ensimesmou", "ensimesmamos/ennosmesmamos",
            "ensimesmastes/envosmesmastes", "ensimesmaram" };
        epConjAO = new String[]{ "ensimesmei/enmimmesmei",
            "ensimesmaste/entimesmaste", "ensimesmou", 
            "ensimesm" + aAcute + "mos/ensimesmamos/ennosmesm" + aAcute + "mos/" +
            "ennosmesmamos", "ensimesmastes/envosmesmastes", "ensimesmaram" };
        epConjNoAO = new String[]{ "ensimesmei/enmimmesmei",
            "ensimesmaste/entimesmaste", "ensimesmou", 
            "ensimesm" + aAcute + "mos/ennosmesm" + aAcute + "mos",
            "ensimesmastes/envosmesmastes", "ensimesmaram" };
        stems = new int[]{ 19, 19, 19, 19, 19, 19 };
        break;
      case "ESTAR":
      case "-ESTAR":
        ends = new String[]{ "e", "este", "e", "emos", "estes", "eram" };
        stem2 = stem + "iv";
        stems[0] = 2; stems[1] = 2; stems[2] = 2;
        stems[3] = 2; stems[4] = 2; stems[5] = 2;
        break;
      case "-FAZER":
        ends = new String[]{ "iz", "izeste", "ez", "izemos", "izestes",
            "izeram" };
        stem2 = stem.substring(0, len - 2);
        stems[0] = 2; stems[1] = 2; stems[2] = 2;
        stems[3] = 2; stems[4] = 2; stems[5] = 2;
        break;
      case "-GAR":
        stem2 = stem + "u";
        stems[0] = 2;
        break;
      case "-GUAR":
      case "-QUAR":
        stem2 = chCharToLast(1, stem, uTreme);
        stems[0] = 16;
        break;
      case "-GUIR": // regular unlike rest of -uir verbs
      case "-QUIR":
        break;
      case "HAVER":
      case "REAVER":
        ends = new String[]{ "e", "este", "e", "emos", "estes",
        "eram" };
        stem2 = stem.substring(0, len - 2) + "ouv";
        stems[0] = 2; stems[1] = 2; stems[2] = 2;
        stems[3] = 2; stems[4] = 2; stems[5] = 2;
        break;
      case "IR":
      case "SOBREIR":
        ends = new String[]{ "fui", "foste", "foi", "fomos", "fostes",
            "foram" };
        break;
      case "-QUERER":
        ends = new String[]{ "", "este", "", "emos", "estes", "eram" };
        stem2 = stem.substring(0, len - 2) + "is";
        stems[0] = 2; stems[1] = 2; stems[2] = 2;
        stems[3] = 2; stems[4] = 2; stems[5] = 2;
        break;
      case "PODER":
        ends = new String[]{ "ude", "udeste", oCFlex + "de", "udemos",
            "udestes", "uderam" };
        stem2 = stem.substring(0, len - 2);
        stems[0] = 2; stems[1] = 2; stems[2] = 2;
        stems[3] = 2; stems[4] = 2; stems[5] = 2;
        break;
      case "-POR":
        ends = new String[]{ "us", "useste", oCFlex + "s", "usemos",
            "usestes", "useram" };
        break;
      case "-PRAZER":
        ends = new String[]{ "e", "este", "e", "emos", "estes", "eram",
            "i", "este", "eu", "emos", "estes", "eram" };
        stem2 = stem.substring(0, len - 2) + "ouv";
        stems[0] = 24; stems[1] = 24; stems[2] = 24;
        stems[3] = 24; stems[4] = 24; stems[5] = 24;
        break;
      case "REQUERER": // regular unlike rest of -querer verbs
        break;
      case "SER":
        ends = new String[]{ "fui", "foste", "foi", "fomos", "fostes",
            "foram" };
        stem2 = "";
        stems[0] = 2; stems[1] = 2; stems[2] = 2;
        stems[3] = 2; stems[4] = 2; stems[5] = 2;
        break;
      case "SOBRESSER":
        ends = new String[]{ "fui", "foste", "foi", "fomos", "fostes",
        "foram" };
        stem2 = stem.substring(0, len - 2);
        stems[0] = 2; stems[1] = 2; stems[2] = 2;
        stems[3] = 2; stems[4] = 2; stems[5] = 2;
        break;
      case "TER":
      case "-TER":
        ends = new String[]{ "ive", "iveste", "eve", "ivemos", "ivestes",
            "iveram" };
        break;
      case "-TRAZER":
        ends = new String[]{ "e", "este", "e", "emos", "estes", "eram" };
        stem2 = stem.substring(0, len - 2) + "oux";
        stems[0] = 2; stems[1] = 2; stems[2] = 2;
        stems[3] = 2; stems[4] = 2; stems[5] = 2;
        break;
      case "VER":
      case "-VER":
        ends = new String[]{ "i", "iste", "iu", "imos", "istes", "iram" };
        break;
      case "VIR":
      case "-VIR":
        ends = new String[]{ "im", "ieste", "eio", "iemos", "iestes",
            "ieram" };
        break;
      default: // regular verbs
        break;
    }
    
    if (hasOnlyThPerSing()) {
      stems[0] = 0; stems[1] = 0;
      stems[3] = 0; stems[4] = 0; stems[5] = 0;
    } else if (hasOnlyThPer()) {
      stems[0] = 0; stems[1] = 0;
      stems[3] = 0; stems[4] = 0;
    }
    
    if (var == EP && ends[3].equals("amos")) {
      ends[3] = aAcute + "mos";
    }

    for (int i = 0; i < 6; i++) {
      switch (stems[i]) {
        case 0:
          bpConjAO[i] = null;
          bpConjNoAO[i] = null;
          epConjAO[i] = null;
          epConjNoAO[i] = null;
          break;
        case 1:
          bpConjAO[i] = stem + ends[i];
          bpConjNoAO[i] = stem + ends[i];
          epConjAO[i] = stem + ends[i];
          epConjNoAO[i] = stem + ends[i];
          break;
        case 2:
          bpConjAO[i] = stem2 + ends[i];
          bpConjNoAO[i] = stem2 + ends[i];
          epConjAO[i] = stem2 + ends[i];
          epConjNoAO[i] = stem2 + ends[i];
          break;
        case 16: // use "stem" for pt and brComAO,
          // and "stem2" for brSemAO
          bpConjAO[i] = stem + ends[i];
          bpConjNoAO[i] = stem2 + ends[i];
          epConjAO[i] = stem + ends[i];
          epConjNoAO[i] = stem + ends[i];
          break;
        case 24: // for -prazer
          bpConjAO[i] = stem2 + ends[i] + "/" + stem + ends[i + 6];
          bpConjNoAO[i] = stem2 + ends[i] + "/" + stem + ends[i + 6];
          epConjAO[i] = stem2 + ends[i] + "/" + stem + ends[i + 6];
          epConjNoAO[i] = stem2 + ends[i] + "/" + stem + ends[i + 6];
          break;
        default: // do absolutely nothing
          break;
      }
    }
    
    if (var == BP && AO) {
      return bpConjAO;
    } else if (var == BP && !AO) {
      return bpConjNoAO;
    } else if (var == EP && AO) { // two nos forms for EP with AO
      String nosForm = epConjAO[3];
      if (nosForm != null &&
          nosForm.substring(nosForm.length() - 4).equals(aAcute + "mos")) {
        nosForm = chCharToLast(4, nosForm, "a");
        epConjAO[3] += "/" + nosForm;
      }
      return epConjAO;
    } else {
      return epConjNoAO;
    }
  }
  
  private String[] conjPluInd(int var, boolean AO) {
    // shadowing AO
    resetTables(); // wipe the tables for the current verb
    // determine which paradigm to use when
    // when conjugating this verb
    String type = getVerbType(getInfinitive(var, AO), PLUPERFECT_INDICATIVE);
    stems = new int[]{ 1, 1, 1, 1, 1, 1 };
    String pretPerfStem = getConjugation(conjTable, PRETERITE_PERFECT, 5,
        var, AO);
    stem = pretPerfStem.substring(0, pretPerfStem.length() - 4);
    
    final String[] regAr = new String[]{ "ara", "aras", "ara", aAcute + "ramos",
        aAcute + "reis", "aram" };
    final String[] regEr = new String[]{ "era", "eras", "era", eCFlex + "ramos",
        eCFlex + "reis", "eram" };
    final String[] regIr = new String[]{ "ira", "iras", "ira", iAcute + "ramos",
        iAcute + "reis", "iram" };
    
    switch (ending) {
      case "ar":
        ends = regAr;
        break;
      case "er":
        ends = regEr;
        break;
      case "ir":
        ends = regIr;
        break;
      default:
        break;
    }

    switch (type) {
      case "-ABER":
      case "DAR":
      case "-DAR":
      case "-DIZER":
      case "ESTAR":
      case "-ESTAR":
      case "-FAZER":
      case "HAVER":
      case "PODER":
      case "-POR":
      case "-QUERER":
      case "TER":
      case "-TER":
      case "-TRAZER": 
      case "VIR":
      case "-VIR":
        ends = new String[]{ "era", "eras", "era", eAcute + "ramos",
            eAcute + "reis", "eram" };
        break;
      case "-AIR":
      case "-UIR":
        ends = new String[]{ iAcute + "ra", iAcute + "ras", iAcute + "ra",
            iAcute + "ramos", iAcute + "reis", iAcute + "ram" };
        break;
      case "ENSIMESMAR":
        if (var == BP) {
          return new String[]{ "ensimesmara/enmimmesmara",
            "ensimesmaras/entimesmaras", "ensimesmara",
            "ensimesm" + aAcute + "ramos/ennosmesm" + aAcute + "ramos",
            "ensimesm" + aAcute + "reis/envosmesm" + aAcute + "reis",
            "ensimesmaram" };
        } else {
          return new String[]{ "ensimesmara/enmimmesmara",
              "ensimesmaras/entimesmaras", "ensimesmara",
              "ensimesm" + aAcute + "ramos/ennosmesm" + aAcute + "ramos",
              "ensimesm" + aAcute + "reis/envosmesm" + aAcute + "reis",
              "ensimesmaram" };
        }
      case "-GUIR":
      case "-QUIR":
        break;
      case "IR":
      case "SER":
      case "SOBREIR": // verify
      case "SOBRESSER":
        ends = new String[]{ "ora", "oras", "ora", oCFlex + "ramos",
            oCFlex + "reis", "oram" };
        break;
      case "-PRAZER":
        ends = new String[]{ "era", "eras", "era", eAcute + "ramos",
            eAcute + "reis", "eram" };
        stem2 = getStem(var, AO);
        stem = stem2.substring(0, stem2.length() - 2) + "ouv";
        stems[0] = 4; stems[1] = 4; stems[2] = 4;
        stems[3] = 4; stems[4] = 4; stems[5] = 4;
        break;
      case "VER":
      case "-VER":
        ends = new String[]{ "ira", "iras", "ira", iAcute + "ramos",
            iAcute + "reis", "iram" };
        break;
      default:
        break;
    }
    
    if (hasOnlyThPerSing()) {
      stems[0] = 0; stems[1] = 0;
      stems[3] = 0; stems[4] = 0; stems[5] = 0;
    } else if (hasOnlyThPer()) {
      stems[0] = 0; stems[1] = 0;
      stems[3] = 0; stems[4] = 0;
    }

    for (int i = 0; i < 6; i++) {
      switch (stems[i]) {
        case 0:
          bpConjAO[i] = null;
          bpConjNoAO[i] = null;
          epConjAO[i] = null;
          epConjNoAO[i] = null;
          break;
        case 1:
          bpConjAO[i] = stem + ends[i];
          bpConjNoAO[i] = stem + ends[i];
          epConjAO[i] = stem + ends[i];
          epConjNoAO[i] = stem + ends[i];
          break;
        case 4: // use stems "stem" and "stem2" both
          bpConjAO[i] = stem + ends[i] + "/" + stem2 + ends[i];
          bpConjNoAO[i] = stem + ends[i] + "/" + stem2 + ends[i];
          epConjAO[i] = stem + ends[i] + "/" + stem2 + ends[i];
          epConjNoAO[i] = stem + ends[i] + "/" + stem2 + ends[i];
          break;
        default:
          break;
      }
    }
    
    if (var == BP && AO) {
      return bpConjAO;
    } else if (var == BP && !AO) {
      return bpConjNoAO;
    } else if (var == EP && AO) {
      return epConjAO;
    } else {
      return epConjNoAO;
    }
  }
  
  private String[] conjFutInd(int var, boolean AO) {
    // shadowing AO
    resetTables(); // wipe the tables for the current verb
    // determine which paradigm to use when
    // when conjugating this verb
    String type = getVerbType(getInfinitive(var, AO), FUTURE_INDICATIVE);
    stems = new int[]{ 1, 1, 1, 1, 1, 1 };
    String stem = getInfinitive(var, AO);
    int len = stem.length();
    
    final String[] regEnds = new String[]{
        "ei", aAcute + "s", aAcute + "", "emos", "eis", aTilde + "o" };
    ends = regEnds;
    
    switch (type) {
      case "-DIZER":
      case "-FAZER":
      case "-TRAZER":
        stem = stem.substring(0, len - 3) + 'r';
        break;
      case "ENSIMESMAR":
        return new String[]{ "ensimesmarei/enmimmesmarei",
            "ensimesmar" + aAcute + "s/entimesmar" + aAcute + "s",
            "ensimesmar" + aAcute,
            "ensimesmaremos/ennosmesmaremos",
            "ensimesmareis/envosmesmareis",
            "ensimesmar" + aTilde + "o" };
      case "POR":
        stem = stem.substring(0, len - 2) + "or";
        break;
      default:
        break;
    } 
    if (hasOnlyThPerSing()) {
      stems[0] = 0; stems[1] = 0;
      stems[3] = 0; stems[4] = 0; stems[5] = 0;
    } else if (hasOnlyThPer()) {
      stems[0] = 0; stems[1] = 0;
      stems[3] = 0; stems[4] = 0;
    }

    // there is no known distinction between semAO/comAO or BP/EP
    String[] allConj = new String[6];
    for (int i = 0; i < 6; i++) {
      if (stems[i] != 0) {
        allConj[i] = stem + ends[i];
      } else {
        allConj[i] = null;
      }
    }
    return allConj;
  }
  
  private String[] conjCond(int var, boolean AO) {
    // shadowing AO
    resetTables(); // wipe the tables for the current verb
    // determine which paradigm to use when
    // when conjugating this verb
    String type = getVerbType(getInfinitive(var, AO), CONDITIONAL);
    stems = new int[]{ 1, 1, 1, 1, 1, 1 };
    stem = getInfinitive(var, AO);
    int len = stem.length();

    final String[] regEnds = new String[]{
        "ia", "ias", "ia", iAcute + "amos", iAcute + "eis", "iam" };
    ends = regEnds;
    
    switch (type) {
      case "-DIZER":
      case "-FAZER":
      case "-TRAZER":
        stem = stem.substring(0, len - 3) + 'r';
        break;
      case "ENSIMESMAR":
        return new String[]{ "ensimesmaria/enmimmesmaria",
            "ensimesmarias/entimesmarias",
            "ensimesmaria",
            "ensimesmar" + iAcute + "amos/ennosmesmar" + iAcute + "amos",
            "ensimesmar" + iAcute + "eis/envosmesmar" + iAcute + "eis",
            "ensimesmariam" };
      case "POR":
        stem = stem.substring(0, len - 2) + "or";
        break;
      default:
        break;
    } 
    if (hasOnlyThPerSing()) {
      stems[0] = 0; stems[1] = 0;
      stems[3] = 0; stems[4] = 0; stems[5] = 0;
    } else if (hasOnlyThPer()) {
      stems[0] = 0; stems[1] = 0;
      stems[3] = 0; stems[4] = 0;
    }

    // there is no known distinction between semAO/comAO or BP/EP
    String[] allConj = new String[6];
    for (int i = 0; i < 6; i++) {
      if (stems[i] != 0) {
        allConj[i] = stem + ends[i];
      } else {
        allConj[i] = null;
      }
    }
    return allConj;
  }
  
  private String[] conjPresSubj(int var, boolean AO) {
    // shadowing AO
    resetTables(); // wipe the tables for the current verb
    // determine which paradigm to use when
    // when conjugating this verb
    String inf = getInfinitive(var, AO);
    String type = getVerbType(inf, PRESENT_SUBJUNCTIVE);
    stems = new int[]{ 1, 1, 1, 1, 1, 1 };
    stem = getConjugation(conjTable, PRESENT_INDICATIVE, 0, var, AO);

    final String[] regAr = new String[]{ "e", "es", "e", "emos",
        "eis", "em"};
    final String[] regEr = new String[]{ "a", "as", "a", "amos",
        "ais", "am"};
    final String[] regIr = regEr;
    
    if (stem == null) {
      for (String s: onlyArriz) {
        if (s.equals(verb)) {
          return new String[]{ null, null, null, null, null, null };
        }
      }
      for (String s: noFstPerSing) {
        if (s.equals(verb)) {
          return new String[]{ null, null, null, null, null, null };
        }
      }
      if (type.equals("-QUIR") || type.equals("-Q" + uTremeUp + "IR")
          || type.equals("-DELINQUIR")) { 
        // had to do this because of third-person verbs
        // these are verbs with no FPS in present indicative
        return new String[]{ null, null, null, null, null, null };
      }
      stem = inf.substring(0, inf.length() - 2);  // third-person only verbs
      // don't currently have any such verbs that are irregular
    } else if (stem.contains("/")) { // verbs with two forms
      int slashIndex = stem.indexOf('/');
      stem2 = stem.substring(slashIndex + 1, stem.length() - 1);
      stem = stem.substring(0, slashIndex - 1);
      stems = new int[]{ 4, 4, 4, 1, 1, 4 }; // ?? (for delinquir)
    } else {
      stem = stem.substring(0, stem.length() - 1);
    }
    int len = stem.length();
    
    switch (ending) {
      case "ar":
        ends = regAr;
        break;
      case "er":
        ends = regEr;
        break;
      case "ir":
        ends = regIr;
        break;
      default:
        break;
    }
    if (var == BP && !AO) {
      if (verb.endsWith("guar") || verb.endsWith("quar")) {
        stem = chCharToLast(1, stem, uTreme);
      }
    }

    switch (type) {
      case "ABAIUCAR":
        stem2 = chCharToLast(3, stem, 'u');
        stem = chCharToLast(1, stem, "qu");
        stems[3] = 2; stems[4] = 2;
        break;
      case "AFIUZAR":
      case "APAULAR":
      case "AUNAR":
      case "AVIUSAR":
      case "AZIUMAR":
      case "-BAULAR":
      case "-CIUMAR":
      case "DESEMBAULAR":
      case "EMBAULAR":
      case "ENVIUSAR":
      case "FAULAR":
      case "SAUDAR":
      case "-VIUVAR":
        stem2 = chCharToLast(2, stem, 'u');
        stems[3] = 2; stems[4] = 2;
        break;
      case "-AGUAR": // may need to fix this
        stem2 = chCharToLast(1, stem, uAcute);
        stem3 = chCharToLast(1, stem, uTreme);
        stem4 = chCharToLast(3, stem3, 'a');
        stems[3] = 26; stems[4] = 26;
        break;
      case "-AIZAR":
      case "-EIZAR": // do we need ateizar in presInd?
      case "-OIZAR":
      case "-UIZAR":
      case "AJESUITAR":
      case "ATEIZAR":
      case "RUIDAR":
      case "-RUINAR":
        stem2 = chCharToLast(2, stem, 'i');
        stems[3] = 2; stems[4] = 2;
        break;
      case "ANSIAR":      // irregular -iar verbs
      case "ARREMEDIAR":
      case "DESARREMEDIAR":
      case "DESREMEDIAR":
      case "INCENDIAR":
      case "INTERMEDIAR":
      case "MEDIAR":
      case "ODIAR":
      case "PROMEDIAR":
        stem2 = chCharToLast(2, stem, "");
        stems[3] = 2; stems[4] = 2;
        break;
      case "-BALAUSTRAR":
        stem2 = chCharToLast(4, stem, 'u');
        stems[3] = 2; stems[4] = 2;
        break;
      case "-CAR":
        stem = chCharToLast(1, stem, "qu");
        break;
      case cCedilUp + "AR":
        stem = chCharToLast(1, stem, "c");
        break;
      case "DAR":
      case "-DAR":
        ends = new String[]{ "", "s", "", "mos", "is", "em" };
        stem = stem.substring(0, len - 1);
        stem2 = stem + eCFlex;
        stem = stem + 'e';
        stems[0] = 2; stems[1] = 2; stems[2] = 2;
        stems[3] = 4; stems[5] = 13;
        break;
      case "-DELINQUIR":
        stems[3] = 1; stems[4] = 1;
        break;
      case  "DESMILINGUIR":
        stem = chCharToLast(4, stem, 'i');
        break;
      case "-EGUAR":
        stem3 = chCharToLast(3, stem, 'e');
        stem4 = chCharToLast(1, stem3, uAcute);
        stems[0] = 30; stems[1] = 30; stems[2] = 30;
        stems[3] = 27; stems[4] = 27; stems[5] = 30;
        break;
      case "EMBAUCAR":
        stem = chCharToLast(1, stem, "qu");
        stem2 = chCharToLast(3, stem, 'u');
        stems[3] = 2; stems[4] = 2;
        break;
      case "-EQUAR":
        stem3 = chCharToLast(3, stem, eAcute);
        stems[0] = 32; stems[1] = 32; stems[2] = 32;
        stems[5] = 32;
        break;
      case "ENSIMESMAR":
        bpConjAO = new String[]{ "ensimesme/enmimmesme",
            "ensimesmes/entimesmes", "ensimesme", "ensimesmemos/ennosmesmemos",
            "ensimesmeis/envosmesmeis", "ensimesmem" };
        bpConjNoAO = new String[]{ "ensimesme/enmimmesme",
            "ensimesmes/entimesmes", "ensimesme", "ensimesmemos/ennosmesmemos",
            "ensimesmeis/envosmesmeis", "ensimesmem" };
        epConjAO = new String[]{ "ensimesme/enmimmesme",
            "ensimesmes/entimesmes", "ensimesme", "ensimesmemos/ennosmesmemos",
            "ensimesmeis/envosmesmeis", "ensimesmem" };
        epConjNoAO = new String[]{ "ensimesme/enmimmesme",
            "ensimesmes/entimesmes", "ensimesme", "ensimesmemos/ennosmesmemos",
            "ensimesmeis/envosmesmeis", "ensimesmem" };
        stems = new int[]{ 19, 19, 19, 19, 19, 19 };
        break;
      case "ESMIU" + cCedilUp + "AR":
        stem = chCharToLast(1, stem, 'c');
        stem2 = chCharToLast(2, stem, 'u');
        stems[3] = 2; stems[4] = 2;
        break;
      case "ESTAR":
      case "-ESTAR":
        ends = new String[]{ "a", "as", "a", "amos", "ais", "am" };
        stem = chCharToLast(1, stem, "") + "ej";
        break;
      case "EXPLODIR":
      case "-OUVIR":
        stems = new int[]{ 4, 4, 4, 4, 4, 4 };
        break;
      case "FAISCAR":
        stem2 = chCharToLast(1, stem, "qu");
        stem = chCharToLast(4, stem2, 'i');
        stems[0] = 2; stems[1] = 2; stems[2] = 2;
        stems[5] = 2;
        break;
      case "-GAR":
        stem = stem + 'u';
        break;
      case "-GAUCHAR":
        stem2 = chCharToLast(3, stem, 'u');
        stems[3] = 2; stems[4] = 2;
        break;
      case "-GUAR": // may need to fix this
      case "-QUAR":
        stem2 = chCharToLast(1, stem, uAcute);
        stem3 = chCharToLast(1, stem, uTreme);
        stems = new int[]{ 24, 24, 24, 27, 27, 24 };
        break;
      case "HAVER":
        stem = "haj";
        break;
      case "-IGUAR":
        stem2 = chCharToLast(3, stem, iAcute);
        stem3 = chCharToLast(1, stem, uAcute);
        stem4 = chCharToLast(1, stem, uTreme);
        stems[0] = 25; stems[1] = 25; stems[2] = 25;
        stems[3] = 26; stems[4] = 26; stems[5] = 25;
        break;
      case "-INGUAR":
      case "-INQUAR":
      case "-IQUAR":
        if (var == BP && !AO) {
          stem2 = chCharToLast(1, stem2, uTreme);
        }
        break;
      case "IR":
      case "SOBREIR":
        ends = new String[]{ aAcute + "", aAcute + "s", aAcute + "", "amos",
            "ades", aTilde + "o" };
        stem = chCharToLast(1, stem, "");
        break;
      case "-MOBILIAR":
        stem2 = chCharToLast(3, stem, 'i');
        stems[3] = 2; stems[4] = 2;
        break;
      case "-OAR":
      case "-OER":
        stem = chCharToLast(1, stem, 'o');
        break;
      case "-OIAR":
        stem2 = chCharToLast(2, stem, 'o');
        stems[3] = 2; stems[4] = 2;
        break;
      case "-OIBIR":
      case "PUITAR":
        stem2 = chCharToLast(2, stem, 'i');
        stems[3] = 2; stems[4] = 2;
        break;
      case "-PARIR":
        stems = new int[]{ 4, 4, 4, 4, 4, 4 };
        break;
      case "-POR":
        ends = new String[]{ "a", "as", "a", "amos", "ais", "am" };
        break;
      case "-QUERER":
        stem = stem.substring(0, len - 1) + "ir";
        break;
      case "REQUERER":
        break;
      case "REUNIR":
        stem2 = chCharToLast(2, stem, 'u');
        stems[3] = 2; stems[4] = 2;
        break;
      case "-SABER":
        stem = stem.substring(0, len - 1) + "aib";
        break;
      case "SER":
      case "SOBRESSER":
        ends = new String[]{ "a", "as", "a", "amos", "ais", "am" };
        stem = stem.substring(0, len - 1) + "ej";
        break;
      default:
        break;
    }
    
    if (hasOnlyThPerSing()) {
      stems[0] = 0; stems[1] = 0;
      stems[3] = 0; stems[4] = 0; stems[5] = 0;
    } else if (hasOnlyThPer()) {
      stems[0] = 0; stems[1] = 0;
      stems[3] = 0; stems[4] = 0;
    }

    for (int i = 0; i < 6; i++) {
      switch (stems[i]) {
        case 0:
          bpConjAO[i] = null;
          bpConjNoAO[i] = null;
          epConjAO[i] = null;
          epConjNoAO[i] = null;
          break;
        case 1:
          bpConjAO[i] = stem + ends[i];
          bpConjNoAO[i] = stem + ends[i];
          epConjAO[i] = stem + ends[i];
          epConjNoAO[i] = stem + ends[i];
          break;
        case 2:
          bpConjAO[i] = stem2 + ends[i];
          bpConjNoAO[i] = stem2 + ends[i];
          epConjAO[i] = stem2 + ends[i];
          epConjNoAO[i] = stem2 + ends[i];
          break;
        case 4: // use stems "stem" and "stem2" both
          bpConjAO[i] = stem + ends[i] + "/" + stem2 + ends[i];
          bpConjNoAO[i] = stem + ends[i] + "/" + stem2 + ends[i];
          epConjAO[i] = stem + ends[i] + "/" + stem2 + ends[i];
          epConjNoAO[i] = stem + ends[i] + "/" + stem2 + ends[i];
          break;
        case 13: // use "stem2" for ptSemAO and brSemAO,
          // and "stem" for ptComAO and brComAO
          bpConjAO[i] = stem + ends[i];
          bpConjNoAO[i] = stem2 + ends[i];
          epConjAO[i] = stem + ends[i];
          epConjNoAO[i] = stem2 + ends[i];
          break;
        case 19: // do absolutely nothing (delinquir/ensimesmar)
          break;
        case 24: // use "stem" for ptComAO and brComAO,
                 // "stem2" for ptSemAO, and "stem3" for brSemAO
          bpConjAO[i] = stem + ends[i];
          bpConjNoAO[i] = stem3 + ends[i];
          epConjAO[i] = stem + ends[i];
          epConjNoAO[i] = stem2 + ends[i];
          break;
        case 25: // use "stem" and "stem2" for ptComAO and brComAO
                 // and "stem3" for brSemAO and ptSemAO
          bpConjAO[i] = stem + ends[i] + "/" + stem2 + ends[i];
          bpConjNoAO[i] = stem3 + ends[i];
          epConjAO[i] = stem + ends[i] + "/" + stem2 + ends[i];
          epConjNoAO[i] = stem3 + ends[i];
          break;
        case 26: // use "stem4" for brSemAO
          bpConjAO[i] = stem + ends[i];
          bpConjNoAO[i] = stem4 + ends[i];
          epConjAO[i] = stem + ends[i];
          epConjNoAO[i] = stem + ends[i];
          break;
        case 27: // use "stem3" for brSemAO
          bpConjAO[i] = stem + ends[i];
          bpConjNoAO[i] = stem3 + ends[i];
          epConjAO[i] = stem + ends[i];
          epConjNoAO[i] = stem + ends[i];
          break;
        case 30: // use "stem" for pt and brComAO,
          // and "stem2" for brSemAO
          bpConjAO[i] = stem + ends[i] + "/" + stem2 + ends[i];
          bpConjNoAO[i] = stem4 + ends[i];
          epConjAO[i] = stem + ends[i];
          epConjNoAO[i] = stem + ends[i];
          break;
        case 32:
          bpConjAO[i] = stem + ends[i] + "/" + stem2 + ends[i];
          bpConjNoAO[i] = stem + ends[i] + "/" + stem2 + ends[i];
          epConjAO[i] = stem + ends[i];
          epConjNoAO[i] = stem3 + ends[i];
          break;
      }
    }
    
    
    if (var == BP && AO) {
      return bpConjAO;
    } else if (var == BP && !AO) {
      return bpConjNoAO;
    } else if (var == EP && AO) {
      return epConjAO;
    } else {
      return epConjNoAO;
    }
  }
  
  private String[] conjImpSubj(int var, boolean AO) {
    // shadowing AO
    resetTables(); // wipe the tables for the current verb
    // determine which paradigm to use when
    // when conjugating this verb
    String type = getVerbType(getInfinitive(var, AO), IMPERFECT_SUBJUNCTIVE);
    stems = new int[]{ 1, 1, 1, 1, 1, 1 };
    stem = getConjugation(conjTable, PRETERITE_PERFECT, 5, var, AO);
    stem = stem.substring(0, stem.length() - 4);

    final String[] regAr = new String[]{
        "asse", "asses", "asse", aAcute + "ssemos", aAcute + "sseis", "assem" };
    final String[] regEr = new String[]{
        "esse", "esses", "esse", eCFlex + "ssemos", eCFlex + "sseis", "essem" };
    final String[] regIr = new String[]{
        "isse", "isses", "isse", iAcute + "ssemos", iAcute + "sseis", "issem" };

    switch (ending) {
      case "ar":
        ends = regAr;
        break;
      case "er":
        ends = regEr;
        break;
      case "ir":
        ends = regIr;
        break;
      default:
        break;
    }
    
    switch (type) {
      case "-AIR":
      case "-UIR":
        ends = new String[]{ iAcute + "sse", iAcute + "sses", iAcute + "sse",
            iAcute + "ssemos", iAcute + "sseis", iAcute + "ssem" };
        break;
      case "-CABER":
      case "DAR":
      case "-DAR":
      case "-DIZER":
      case "ESTAR":
      case "-ESTAR":
      case "-FAZER":
      case "HAVER":
      case "PODER":
      case "-POR":
      case "-QUERER":
      case "-SABER":
      case "TER":
      case "-TER":
      case "-TRAZER":
      case "VIR":
      case "-VIR":
        ends = new String[]{ "esse", "esses", "esse", eAcute + "ssemos",
            eAcute + "sseis", "essem" }; 
        break;
      case "ENSIMESMAR":
        return new String[]{ "ensimesmasse/enmimmesmasse",
            "ensimesmasses/entimesmasses",
            "ensimesmasse",
            "ensimesm" + aAcute + "ssemos/ennosmesm" + aAcute + "ssemos",
            "ensimesm" + aAcute + "sseis/envosmesm" + aAcute + "sseis",
            "ensimesmassem" };
      case "-GUIR": // regular -uir verbs
      case "-QUIR":
        break;
      case "IR":
      case "SER":
      case "SOBREIR":
      case "SOBRESSER":
        ends = new String[]{ "osse", "osses", "osse", oCFlex + "ssemos",
            oCFlex + "sseis", "ossem" };
        break;
      case "VER":
      case "-VER":
        ends = new String[]{ "isse", "isses", "isse", iAcute + "ssemos",
            iAcute + "sseis", "issem" };
        break;
      default:
        break;
    } 
    if (hasOnlyThPerSing()) {
      stems[0] = 0; stems[1] = 0;
      stems[3] = 0; stems[4] = 0; stems[5] = 0;
    } else if (hasOnlyThPer()) {
      stems[0] = 0; stems[1] = 0;
      stems[3] = 0; stems[4] = 0;
    }

    // there is no known distinction between semAO/comAO or BP/EP
    String[] allConj = new String[6];
    for (int i = 0; i < 6; i++) {
      if (stems[i] != 0) {
        allConj[i] = stem + ends[i];
      } else {
        allConj[i] = null;
      }
    }
    return allConj;
  }
  
  private String[] conjFutSubj(int var, boolean AO) {
    // shadowing AO
    resetTables(); // wipe the tables for the current verb
    // determine which paradigm to use when
    // when conjugating this verb
    String type = getVerbType(getInfinitive(var, AO), FUTURE_SUBJUNCTIVE);
    stems = new int[]{ 1, 1, 1, 1, 1, 1 };
    stem = getConjugation(conjTable, PRETERITE_PERFECT, 5, var, AO);
    stem = stem.substring(0, stem.length() - 4); 
    
    final String[] regAr = new String[]{
        "ar", "ares", "ar", "armos", "ardes", "arem" };
    final String[] regEr = new String[]{
        "er", "eres", "er", "ermos", "erdes", "erem" };
    final String[] regIr = new String[]{
        "ir", "ires", "ir", "irmos", "irdes", "irem" };

    switch (ending) {
      case "ar":
        ends = regAr;
        break;
      case "er":
        ends = regEr;
        break;
      case "ir":
        ends = regIr;
        break;
      default:
        break;
    }
    
    switch (type) {
      case "-AIR":
      case "-UIR":
        ends = new String[]{ "ir", iAcute + "res", "ir", "irmos", "irdes",
            iAcute + "rem" };
        break;
      case "-CABER":
      case "DAR":
      case "-DAR":
      case "-DIZER":
      case "ESTAR":
      case "-ESTAR":
      case "-FAZER":
      case "HAVER":
      case "PODER":
      case "-POR":
      case "-QUERER":
      case "-SABER":
      case "TER":
      case "-TER":
      case "-TRAZER":
      case "VIR":
      case "-VIR":
        ends = new String[]{ "er", "eres", "er", "ermos", "erdes", "erem" };
        break;
      case "ENSIMESMAR":
        return new String[]{ "ensimesmar/enmimmesmar",
            "ensimesmares/entimesmares",
            "ensimesmar",
            "ensimesmaremos/ennosmesmaremos",
            "ensimesmareis/envosmesmareis",
            "ensimesmarem" };
      case "-GUIR": // regular -uir verbs
      case "-QUIR":
        break;
      case "IR":
      case "SER":
      case "SOBREIR":
      case "SOBRESSER":
        ends = new String[]{ "or", "ores", "or", "ormos", "ordes", "orem" };
        break;
      case "VER":
      case "-VER":
        ends = new String[]{ "ir", "ires", "ir", "irmos", "irdes", "irem" };
        break;
      default:
        break;
    } 
    if (hasOnlyThPerSing()) {
      stems[0] = 0; stems[1] = 0;
      stems[3] = 0; stems[4] = 0; stems[5] = 0;
    } else if (hasOnlyThPer()) {
      stems[0] = 0; stems[1] = 0;
      stems[3] = 0; stems[4] = 0;
    }

    // there is no known distinction between semAO/comAO or BP/EP
    String[] allConj = new String[6];
    for (int i = 0; i < 6; i++) {
      if (stems[i] != 0) {
        allConj[i] = stem + ends[i];
      } else {
        allConj[i] = null;
      }
    }
    return allConj;
  }
  
  private String[] conjPersInf(int var, boolean AO) {
    // shadowing AO
    resetTables(); // wipe the tables for the current verb
    // determine which paradigm to use when
    // when conjugating this verb
    stems = new int[]{ 1, 1, 1, 1, 1, 1 };
    stem = getInfinitive(var, AO);

    final String[] ends = new String[]{ "", "es", "", "mos", "des", "em" };

    if (verb.equals("ensimesmar")) {
        return new String[]{ "ensimesmar/enmimmesmar",
            "ensimesmares/entimesmares",
            "ensimesmar",
            "ensimesmarmos/ennosmesmarmos",
            "ensimesmardes/envosmesmardes",
            "ensimesmarem" };
    } 
    
    if (hasOnlyThPerSing()) {
      stems[0] = 0; stems[1] = 0;
      stems[3] = 0; stems[4] = 0; stems[5] = 0;
    } else if (hasOnlyThPer()) {
      stems[0] = 0; stems[1] = 0;
      stems[3] = 0; stems[4] = 0;
    }

    // there is no known distinction between semAO/comAO or BP/EP
    String[] allConj = new String[6];
    for (int i = 0; i < 6; i++) {
      if (stems[i] != 0) {
        allConj[i] = stem + ends[i];
      } else {
        allConj[i] = null;
      }
    }
    return allConj;
  }
  
  private String conjGerund(int var, boolean AO) {
    String inf = getInfinitive(var, AO);
    stem = inf.substring(0, inf.length() - 1);
    if (inf.equals("p" + oCFlex + "r")) {
      return "pondo";
    }
    return stem + "ndo";
  }
  
  private String[] conjImpAff(int var, boolean AO) {
	if (hasOnlyThPerSing() || hasOnlyThPer()) {
	  return new String[]{ null, null, null, null, null, null };
	}
	
    String vos = getConjugation(conjTable, PRESENT_INDICATIVE, 4, var, AO);
    if (vos != null) {
      vos = vos.substring(0, vos.length() - 1);
    }
    String[] conj = new String[]{
       null,
       getConjugation(conjTable, PRESENT_INDICATIVE, 2, var, AO),
       getConjugation(conjTable, PRESENT_SUBJUNCTIVE, 2, var, AO),
       getConjugation(conjTable, PRESENT_SUBJUNCTIVE, 3, var, AO),
       vos,
       getConjugation(conjTable, PRESENT_SUBJUNCTIVE, 5, var, AO)
    };
    if (verb.length() >= 9) {
      if (verb.equals("sobresser")) {
        conj[1] = "sobress" + eCFlex;
        conj[4] = "sobressede";
      }
    }
    if (verb.length() >= 8) {
      if (verb.substring(verb.length() - 8).equals("conduzir") ||
    	  verb.substring(verb.length() - 8).equals("traduzir")) { // just added
        conj[1] = conj[1] + "/" + conj[1] + 'e';
      }
    }
    if (verb.length() >= 6) {
        if (verb.substring(verb.length() - 6).equals("trazer")) {
          conj[1] = conj[1] + "/" + conj[1] + 'e';
        }
      }
    if (verb.length() >= 3) {
      if (verb.equals("ser")) {
        conj[1] = "s" + eCFlex;
        conj[4] = "sede";
      }
    }
    
    return conj;
  }
  
  private String[] conjImpNeg(int var, boolean AO) {
    if (hasOnlyThPerSing() || hasOnlyThPer()) {
      return new String[]{ null, null, null, null, null, null };
    }
    
    String[] conj = new String[]{
        null,
        getConjugation(conjTable, PRESENT_SUBJUNCTIVE, 1, var, AO),
        getConjugation(conjTable, PRESENT_SUBJUNCTIVE, 2, var, AO),
        getConjugation(conjTable, PRESENT_SUBJUNCTIVE, 3, var, AO),
        getConjugation(conjTable, PRESENT_SUBJUNCTIVE, 4, var, AO),
        getConjugation(conjTable, PRESENT_SUBJUNCTIVE, 5, var, AO)
     };
    return conj;
  }
  
  private String conjPastPart(int var, boolean AO) {
 // shadowing AO
    resetTables(); // wipe the tables for the current verb
    // determine which paradigm to use when
    // when conjugating this verb
    String type = getVerbType(getInfinitive(var, AO), PAST_PARTICIPLE);
    stem = getStem(var, AO);
    int len = stem.length();
    
    String end = null;
    String end2 = null;
    
    final String regAr = "ado";
    final String regEr = "ido";
    final String regIr = regEr;
    
    switch (ending) {
      case "ar":
        end = regAr;
        break;
      case "er":
        end = regEr;
        break;
      case "ir":
        end = regIr;
        break;
      default:
        break;
    }

    switch (type) {
      case "-AER":
      case "-AIR":
      case "-OER":
      case "-OIR":
      case "-UER":
      case "-UIR":
        end = iAcute + "do";
        break;
      case "-ABRIR":
      case "-COBRIR":
        stem = stem.substring(0, len - 1);
        end = "erto";
        break;
      case "ABSOLVER":
      case "BENZER":
      case "MORRER":
        stem2 = stem.substring(0, len - 1);
        end2 = "to";
        return stem + end + "/" + stem2 + end2;
      case "ACEITAR":
        if (var == BP) {
          end2 = "o";
        } else {
          end2 = "e";
        }
        return stem + end + "/" + stem + end2;
      case "ACENDER":
      case "DISTENDER":
      case "PRENDER":
      case "SUSPENDER":
        stem2 = stem.substring(0, len - 2);
        end2 = "so";
        return stem + end + "/" + stem2 + end2;
      case "-ARGIR":
        stem = stem.substring(0, len - 1);
        end = "erso";
        break;
      case "ASSENTAR": // assente used in Portugal!!!
        end2 = "e";
        return stem + end + "/" + stem + end2;
      case "DISTINGUIR":
      case "EXTINGUIR":
      case "ROMPER":
        stem2 = stem.substring(0, len - 2);
        end2 = "to";
        return stem + end + "/" + stem2 + end2;
      case "-DIZER":
        stem = stem.substring(0, len - 1);
        end = "to";
        break;
      case "ELEGER":
        stem2 = stem.substring(0, len - 1);
        end2 = "ito";
        return stem + end + "/" + stem2 + end2;
      case "ENCHER":
        stem2 = "ch";
        end2 = "eio";
        return stem + end + "/" + stem2 + end2;
      case "ENTREGAR":
        end2 = "ue";
        return stem + end + "/" + stem + end2;
      case "ENVOLVER":
      case "ENXUGAR":
      case "FRIGIR":
        stem2 = stem.substring(0, len - 1);
        end2 = "to";
        return stem + end + "/" + stem2 + end2;
      case "-ERGIR":
        stem2 = stem.substring(0, len - 1);
        end2 = "so";
        return stem + end + "/" + stem2 + end2;
      case "EXPRIMIR":
      case "-IMPRIMIR":
        stem2 = stem.substring(0, len - 2);
        end2 = "esso";
        return stem + end + "/" + stem2 + end2;
      case "ANEXAR":
      case "DESPERTAR":
      case "DISPERSAR":
      case "EXPRESSAR":
      case "EXPULSAR":
      case "FARTAR":
      case "FINDAR":
      case "GANHAR":
      case "GASTAR":
      case "ISENTAR":
      case "JUNTAR":
      case "LIBERTAR":
      case "LIMPAR":
      case "MANIFESTAR":
      case "MURCHAR":
      case "OCULTAR":
      case "PAGAR":
      case "PEGAR":
      case "SALVAR":
      case "SECAR":
      case "SEGURAR":
      case "SOLTAR":
      case "SUJEITAR":
      case "VAGAR":
        end2 = "o";
        return stem + end + "/" + stem + end2;
      case "-FAZER":
        stem = stem.substring(0, len - 2);
        end = "eito";
        break;
      case "-GUER": // include treme in these
      case "-GUIR":
      case "-QUER":
      case "-QUIR":
        break;
      case "MATAR":
        stem2 = stem.substring(0, len - 2);
        end2 = "orto";
        return stem + end + "/" + stem2 + end2;
      case "MALQUERER":
        stem2 = stem.substring(0, len - 2);
        end2 = "isto";
        return stem + end + "/" + stem2 + end2;
      case "-SCREVER":
        stem = stem.substring(0, len - 2);
        end = "ito";
        break;
      case "P" + oCFlexUp + "R":
      case "-POR":
        end = "osto";
        break;
      case "VER":
      case "-VER":
        end = "isto";
        break;
      case "VIR":
      case "-VIR":
        end = "indo";
      default:
        break;
    }
    return stem + end;
  }
  
  private String[] conjCompTense(int tense, int var, boolean AO) {
    int auxTense = PRESENT_INDICATIVE;
    switch (tense) {
      case COMPOUND_PAST_PERFECT:
        auxTense = PRETERITE_IMPERFECT;
        break;
      case COMPOUND_PLUPERFECT:
        auxTense = PLUPERFECT_INDICATIVE;
        break;
      case FUTURE_PERFECT:
        auxTense = FUTURE_INDICATIVE;
        break;
      case CONDITIONAL_PERFECT:
        auxTense = CONDITIONAL;
        break;
      case PRESENT_PERFECT_SUBJ:
        auxTense = PRESENT_SUBJUNCTIVE;
        break;
      case PAST_PERFECT_SUBJ:
        auxTense = IMPERFECT_SUBJUNCTIVE;
        break;
      case FUTURE_PERFECT_SUBJ:
        auxTense = FUTURE_SUBJUNCTIVE;
        break;
      case INFINITIVE_PERFECT:
        auxTense = PERSONAL_INFINITIVE;
        break;
    }
    
    String[] conj = new String[6];
    String part = getConjugation(conjTable, PAST_PARTICIPLE, 0, var, AO);
    if (part.contains("/")) {
      part = part.substring(0, part.indexOf("/"));
    }
    
    for (int i = 0; i < 6; i++) {
      conj[i] = getConjugation(terTable, auxTense, i, var, AO);
      conj[i] += "/";
      conj[i] += getConjugation(haverTable, auxTense, i, var, AO);
      conj[i] += " " + part;
    }
    
    if (hasOnlyThPerSing()) {
      conj[0] = null; conj[1] = null;
      conj[3] = null; conj[4] = null; conj[5] = null;
    } else if (hasOnlyThPer()) {
      conj[0] = null; conj[1] = null;
      conj[3] = null; conj[4] = null;
    }
    return conj;
  }
  
  
  private String conjCompImpInf(int var, boolean AO) {
    String conj = "";
    String part = getConjugation(conjTable, PAST_PARTICIPLE, 0, var, AO);
    if (part.contains("/")) {
      part = part.substring(0, part.indexOf("/"));
    }
      conj = getConjugation(terTable, IMPERSONAL_INFINITIVE, 0, var, AO);
      conj += "/";
      conj += getConjugation(haverTable, IMPERSONAL_INFINITIVE, 0, var, AO);
      conj += " " + part;
    return conj;
  }
  
  private String[][] conjProgressive(int var, boolean AO) {
    // this should never allow reflexive
    String[][] conj = new String[NUM_TENSES][6];
    String inf = getInfinitive(var, AO);
    String gerund = conjGerund(var, AO);
    for (int i = 0; i < NUM_TENSES; i++) {
      for (int j = 0; j < 6; j++) {
        if (j == 0 && (i == IMPERATIVE_AFF || i == IMPERATIVE_NEG)) {
          conj[i][j] = null;
        } else if (i == IMPERSONAL_INFINITIVE || i == GERUND ||
            i == PAST_PARTICIPLE || i == IMP_INFINITIVE_PERFECT) {
          if (var == EP) {
            conj[i][0] = getConjugation(estarTable, i, 0, var, AO) + " a " + 
                inf;
          } else {
            conj[i][0] = getConjugation(estarTable, i, 0, var, AO) + " " + gerund;
          }
          break;
        } else {
          if (var == EP) {
            conj[i][j] = getConjugation(estarTable, i, j, var, AO) + " a " + 
                inf;
          } else {
            conj[i][j] = getConjugation(estarTable, i, j, var, AO) + " " + gerund;
          }
        }
      }
      if (i != GERUND && i != PAST_PARTICIPLE &&
          i != IMPERSONAL_INFINITIVE && i != IMP_INFINITIVE_PERFECT) {
    	if (hasOnlyThPerSing()) {
          conj[i][0] = null; conj[i][1] = null;
          conj[i][3] = null; conj[i][4] = null; conj[i][5] = null;
        } else if (hasOnlyThPer()) {
          conj[i][0] = null; conj[i][1] = null;
          conj[i][3] = null; conj[i][4] = null;
        }
      }
      // why is this part below in the loop?!?
      conj[GERUND] = new String[]{
          conj[GERUND][0] };
      conj[PAST_PARTICIPLE] = new String[]{
          conj[PAST_PARTICIPLE][0] };
      conj[IMPERSONAL_INFINITIVE] = new String[]{
          conj[IMPERSONAL_INFINITIVE][0] };
      conj[IMP_INFINITIVE_PERFECT] = new String[]{
          conj[IMP_INFINITIVE_PERFECT][0] };
    }
    return conj;
  }
  
  String[][][] conjAllProgressive() {
    String[][][] conj = new String[NUM_TENSES][4][6];
    String[][] brAO = conjProgressive(BP, AO);
    String[][] brNoAO = conjProgressive(BP, !AO);
    String[][] ptAO = conjProgressive(EP, AO);
    String[][] ptNoAO = conjProgressive(EP, !AO);
    for (int i = 0; i < NUM_TENSES; i++) {
      conj[i][1] = brAO[i];
      conj[i][0] = brNoAO[i];
      conj[i][3] = ptAO[i];
      conj[i][2] = ptNoAO[i];
    }
    return conj;
  }
  
  private String[][] conjPassive(int var, boolean AO) {
    // this should never allow reflexive
    String[][] conj = new String[NUM_TENSES][6];
    String part = conjPastPart(var, AO);
    if (part.contains("/")) {
      part = part.substring(part.indexOf("/") + 1);
    }
    for (int i = 0; i < NUM_TENSES; i++) {
      for (int j = 0; j < 6; j++) {
        if (j == 0 && (i == IMPERATIVE_AFF || i == IMPERATIVE_NEG)) {
          conj[i][j] = null;
        } else if (i == IMPERSONAL_INFINITIVE || i == GERUND ||
            i == PAST_PARTICIPLE || i == IMP_INFINITIVE_PERFECT) {
          conj[i][0] = getConjugation(serTable, i, 0, var, AO) + " " + part;
          break;
        } else {
          conj[i][j] = getConjugation(serTable, i, j, var, AO) + " " + part;
          if (j > 2) {
            conj[i][j] += 's';
          }
        }
      }
      if (i != GERUND && i != PAST_PARTICIPLE &&
          i != IMPERSONAL_INFINITIVE && i != IMP_INFINITIVE_PERFECT) {
        if (hasOnlyThPerSing()) {
          conj[i][0] = null; conj[i][1] = null;
          conj[i][3] = null; conj[i][4] = null; conj[i][5] = null;
        } else if (hasOnlyThPer()) {
          conj[i][0] = null; conj[i][1] = null;
          conj[i][3] = null; conj[i][4] = null;
        }
      }
      conj[GERUND] = new String[]{
          conj[GERUND][0] };
      conj[PAST_PARTICIPLE] = new String[]{
          conj[PAST_PARTICIPLE][0] };
      conj[IMPERSONAL_INFINITIVE] = new String[]{
          conj[IMPERSONAL_INFINITIVE][0] };
      conj[IMP_INFINITIVE_PERFECT] = new String[]{
          conj[IMP_INFINITIVE_PERFECT][0] };
    }
    return conj;
  }
  
  String[][][] conjAllPassive() {
    String[][][] conj = new String[NUM_TENSES][4][6];
    String[][] bpAO = conjPassive(BP, AO);
    String[][] bpNoAO = conjPassive(BP, !AO);
    String[][] epAO = conjPassive(EP, AO);
    String[][] epNoAO = conjPassive(EP, !AO);
    for (int i = 0; i < NUM_TENSES; i++) {
      conj[i][1] = bpAO[i];
      conj[i][0] = bpNoAO[i];
      conj[i][3] = epAO[i];
      conj[i][2] = epNoAO[i];
    }
    return conj;
  }
  
  private String insertHelper(String conj, String pro, int ind) {
    int len = conj.length();
    String str = conj.substring(0, len - ind - 2);
    String mid = conj.substring(len - ind - 2, len - ind);
    String end = conj.substring(len - ind);
    switch (pro) {
      case "a":
      case "as":  
      case "o":
      case "os":
        switch (mid) {
          case "ar":
            mid = aAcute + "";
            break;
          case "er":
            mid = eCFlex + "";
            break;
          case "ir":
            String last4 = verb.substring(verb.length() - 4);
            if (last4.equals("guir") || last4.equals("quir") ||
                last4.equals("g" + uTreme + "ir") ||
                last4.equals("q" + uTreme + "ir")) {
              mid = "i";
              break;
            }
            String last3 = verb.substring(verb.length() - 3);
            if (last3.equals("air") || last3.equals("uir")) {
              mid = iAcute + "";
              break;
            }
            mid = "i";
            break;
          default:
            mid = oCFlex + "";
            break;
        }
        return str + mid + "-l" + pro + "-" + end;  
      default:
        break;
    }
    return str + mid + "-" + pro + "-" + end;  
  }
  
  private String insertPronoun(String conj, String pro, int tense, int pos) {
    String temp = null;
    String out = "";

    int ind = 0;
    if (conj == null) {
      return null;
    }
    String tail = "";
    if (conj.contains(" ")) {
      tail = conj.substring(conj.indexOf(' '));
      conj = conj.substring(0, conj.indexOf(' '));
    }
    if (tense == FUTURE_INDICATIVE || tense == FUTURE_PERFECT) {
      switch (pos) {
        case 0: case 1: case 5:
          ind = 2;
          break;
        case 2:
          ind = 1;
          break;
        case 3:
          ind = 4;
          break;
        case 4:
          ind = 3;
          break;
        default:
          break;
      }
    } else if (tense == CONDITIONAL || tense == CONDITIONAL_PERFECT) {
      switch (pos) {
        case 0: case 2:
          ind = 2;
          break;
        case 1: case 5:
          ind = 3;
          break;
        case 4:
          ind = 4;
          break;
        case 3:
          ind = 5;
          break;
        default:
          break;
      }
    }
    if (!conj.contains("/")) {
      out = conj;
      return insertHelper(conj, pro, ind) + tail;
    } else {
      for (int i = 0; i < 4; i++) {
        if (!conj.contains("/")) {
          out += "/" + insertHelper(conj, pro, ind);
          break;
        }
        temp = conj.substring(0, conj.indexOf("/"));
        if (i != 0) {
          out += "/";
        }
        out += insertHelper(temp, pro, ind);
        conj = conj.substring(conj.indexOf("/") + 1);
      }
    }
    return out + tail;
  }

  private String appendHelper(String conj, String pro) {
    // vos does not change anything
    if (conj == null) {
      return null;
    }
    int len = conj.length();
    switch (pro) {
      case "nos":
      case "no-lo":
      case "no-la":
      case "no-los":
      case "no-las":
        if (conj.length() > 2 && conj.substring(len - 3).equals("mos")) {
          return conj.substring(0, len - 1) + "-" + pro;
        }
        break;
      case "a":
      case "as":  
      case "o":
      case "os":
        if (len >= 2) {
          String last2 = conj.substring(len - 2);
          switch (last2) {
            case "ar":
            case aAcute + "s":
            case "az":
              return conj.substring(0, len - 2) + aAcute + "-l" + pro;
            case "er":
            case eCFlex + "s":
            case "ez":
              return conj.substring(0, len - 2) + eCFlex + "-l" + pro;
            case "as":
            case "es":
            case eAcute + "s":
            case "is":
            case iAcute + "s":
            case "os":
            case "uz": // just added for conduzir and traduzir
              return conj.substring(0, len - 1) + "-l" + pro;
            case "ir":
              if (len >= 4) {
                String last4 = conj.substring(len - 4);
                if (last4.equals("guir") || last4.equals("quir") ||
                    last4.equals("g" + uTreme + "ir") ||
                    last4.equals("q" + uTreme + "ir")) {
                  return conj.substring(0, len - 2) + "i-l" + pro;
                }
              }
              if (len >= 3) {
                String last3 = conj.substring(conj.length() - 3);
                if (last3.equals("air") || last3.equals("uir")) {
                  return conj.substring(0, len - 2) + iAcute + "-l" + pro;
                }
              }
              return conj.substring(0, len - 2) + "i-l" + pro;
            case "or":
            case oCFlex + "r":
            case oCFlex + "s":
              return conj.substring(0, len - 2) + oCFlex + "-l";
            case aTilde + "o":
            case oTilde + "e":
              return conj + "-n" + pro;
            case "ns":
              return conj.substring(0, len - 2) + "m-l" + pro;
          }
        }
        char lastChar = conj.charAt(len - 1);
        if (lastChar == 'm' || lastChar == 'n') {
          return conj + "-n" + pro;
        }
        break;
      default:
        break;
    }
    return conj + "-" + pro;  
  }
  
  private String appendPronoun(String conj, String pro, int var) {
    if (conj == null) {
      return null;
    }
    String tail = "";
    if (conj.contains(" ")) {
      tail = conj.substring(conj.indexOf(' '));
      conj = conj.substring(0, conj.indexOf(' '));
      if (var == BP) {
        return conj + " " + pro + tail;
      }
    }
    String temp = null;
    String out = "";
    if (verb.length() >= 6 && conj.length() >= 5 &&
        (pro.equals("o") || pro.equals("a") ||
         pro.equals("os") || pro.equals("as"))) {
      String last6 = verb.substring(verb.length() - 6);
      String last5 = conj.substring(conj.length() - 5);
      if (last6.equals("querer") && last5.equals("quere")) {
        String quer = conj.substring(0, conj.length() - 6);
        out = last5;
        return appendHelper(quer, pro) + "/" +
               appendHelper(out, pro);
      }
    }
    if (!conj.contains("/")) {
      out = conj;
      return appendHelper(out, pro) + tail;
    } else {
      for (int i = 0; i < 4; i++) {
        if (!conj.contains("/")) {
          out += "/" + appendHelper(conj, pro);
          break;
        }
        temp = conj.substring(0, conj.indexOf("/"));
        if (i != 0) {
          out += "/";
        }
        out += appendHelper(temp, pro);
        conj = conj.substring(conj.indexOf("/") + 1);
      }
    }
    return out + tail;
  }
  
  private String prependPronoun(String conj, String pro) {
    if (conj == null) {
      return null;
    } else {
      return pro + " " + conj;
    }
  }
  
  private String[][][] conjAllWithPronounsHelper(String[][][] simpConj, 
      String[] pros) {
    String[][][] conj = new String[simpConj.length][4][6];
    for (int i = 0; i < 4; i++) {
      conj[PAST_PARTICIPLE][i] = new String[1];
      conj[GERUND][i] = new String[1];
      conj[IMPERSONAL_INFINITIVE][i] = new String[1];
      if (simpConj.length > IMP_INFINITIVE_PERFECT) {
        conj[IMP_INFINITIVE_PERFECT][i] = new String[1];
      }
    }
    /*
    if (conjTable == null) { // set as null initially
      conjUpTo(IMP_INFINITIVE_PERFECT);
    }*/
    for (int i = 0; i < simpConj.length; i++) {
      for (int j = 0; j < 4; j++) {
        if (i == PAST_PARTICIPLE) {
          conj[i][j][0] = simpConj[i][j][0];
        } else if (i == GERUND || i == IMPERSONAL_INFINITIVE) {
          if (j < 2) {
            //conj[i][j][0] = prependPronoun(simpConj[i][j][0], pros[2]);
            conj[i][j][0] = appendPronoun(simpConj[i][j][0], pros[2], BP);
          } else {
            conj[i][j][0] = appendPronoun(simpConj[i][j][0], pros[2], EP);
          } // combine these two !!
        } else if (i == IMP_INFINITIVE_PERFECT) {
          if (j <  2) {
            conj[i][j][0] = appendPronoun(simpConj[i][j][0], pros[2], BP);
          } else {
            conj[i][j][0] = appendPronoun(simpConj[i][j][0], pros[2], EP);
          }
        } else {
          for (int k = 0; k < 6; k++) {
            if (i == IMPERATIVE_AFF) {
              conj[i][j][k] = appendPronoun(simpConj[i][j][k], pros[k], EP);
            } else if (i == PRESENT_PERFECT_SUBJ || i == PAST_PERFECT_SUBJ ||
                i == FUTURE_PERFECT_SUBJ) {
              conj[i][j][k] = prependPronoun(simpConj[i][j][k], pros[k]);
            } else if (i == CONDITIONAL || i == FUTURE_INDICATIVE ||
                i == CONDITIONAL_PERFECT || i == FUTURE_PERFECT) {
              conj[i][j][k] = insertPronoun(simpConj[i][j][k], pros[k], i, k);
            } else if (j < 2 || i == PRESENT_SUBJUNCTIVE ||
                i == IMPERFECT_SUBJUNCTIVE ||
                i == FUTURE_SUBJUNCTIVE ||
                i == IMPERATIVE_NEG) { // Brazilian
              if (i > GERUND) {
                if (pros[k].equals("o") || pros[k].equals("a") ||
                    pros[k].equals("os") || pros[k].equals("as")) {
                  conj[i][j][k] = appendPronoun(simpConj[i][j][k], pros[k], BP);
                } else {
                  conj[i][j][k] = appendPronoun(simpConj[i][j][k], pros[k], EP);
                }
              } else {
                conj[i][j][k] = prependPronoun(simpConj[i][j][k], pros[k]);
              }
            } else {
              conj[i][j][k] = appendPronoun(simpConj[i][j][k], pros[k], EP);
            }
          }
        }
      }
    }
    return conj;
  }
  
  // if pro2 is not null, pro1 = indirect object and pro2 = direct object
  String[][][] conjAllWithPronouns(String[][][] simpConj, String pro1, String pro2) {
    String comb;
    String[] pros = new String[6];
    if (pro2 == null) {
      for (int i = 0; i < 6; i++) {
        pros[i] = pro1;
      }
      return conjAllWithPronounsHelper(simpConj, pros);
    } else {
      comb = contractPronouns(pro1, pro2);
      for (int i = 0; i < 6; i++) {
        pros[i] = comb;
      }
      return conjAllWithPronounsHelper(simpConj, pros);
    }
  }
  
  String[][][] conjAllReflex(String[][][] simpConj) {
    String[] pros = new String[]{ "me", "te", "se", "nos", "vos", "se" };
    return conjAllWithPronounsHelper(simpConj, pros);
  }
  
  String[][][] conjAllProgressiveReflexive() {
    String[] pros = new String[]{ "me", "te", "se", "nos", "vos", "se" };
    String[][][] conj = conjAllProgressive();
    for (int i = 0; i < NUM_TENSES; i++) {
      for (int m = 0; m < 6; m++) {
        if (conj[i][0].length == 1) {
          conj[i][0][0] += "-" + pros[2];
          conj[i][1][0] += "-" + pros[2];
          conj[i][2][0] = appendHelper(conj[i][2][0], pros[2]);
          conj[i][3][0] = appendHelper(conj[i][3][0], pros[2]);

          
          //conj[i][0][0] = appendPronoun(conj[i][0][0], pros[2], BP);
          //conj[i][1][0] = appendPronoun(conj[i][1][0], pros[2], BP);
          //conj[i][2][0] = appendPronoun(conj[i][2][0], pros[2], EP);
          //conj[i][3][0] = appendPronoun(conj[i][3][0], pros[2], EP);
          break;
        }
        if (i == PRESENT_SUBJUNCTIVE || i == PRESENT_PERFECT_SUBJ ||
            i == IMPERFECT_SUBJUNCTIVE || i == PAST_PERFECT_SUBJ ||
            i == FUTURE_SUBJUNCTIVE || i == FUTURE_PERFECT_SUBJ ||
            i == IMPERATIVE_NEG) {
          conj[i][0][m] = prependPronoun(conj[i][0][m], pros[m]);
          conj[i][1][m] = prependPronoun(conj[i][1][m], pros[m]);
          conj[i][2][m] = prependPronoun(conj[i][2][m], pros[m]);
          conj[i][3][m] = prependPronoun(conj[i][3][m], pros[m]);
        } else {
          if (conj[i][0][m] != null) {
            conj[i][0][m] += "-" + pros[m];
          }
          if (conj[i][1][m] != null) {
            conj[i][1][m] += "-" + pros[m];
          }
          conj[i][2][m] = appendHelper(conj[i][2][m], pros[m]);
          conj[i][3][m] = appendHelper(conj[i][3][m], pros[m]);
          
          //conj[i][0][m] = appendPronoun(conj[i][0][m], pros[m], BP);
          //conj[i][1][m] = appendPronoun(conj[i][1][m], pros[m], BP);
          //conj[i][2][m] = appendPronoun(conj[i][2][m], pros[m], EP);
          //conj[i][3][m] = appendPronoun(conj[i][3][m], pros[m], EP);
        }
      }
    }

    return conj;
  }
  
  String[][][] conjAllProgressiveWithPronouns(String pro1, String pro2) {
    String pro = contractPronouns(pro1, pro2);
    String[][][] conj = conjAllProgressive();
    for (int i = 0; i < NUM_TENSES; i++) {
      for (int m = 0; m < 6; m++) {
        if (conj[i][0].length == 1) {
          conj[i][0][0] += "-" + pro;
          conj[i][1][0] += "-" + pro;
          conj[i][2][0] = appendHelper(conj[i][2][0], pro);
          conj[i][3][0] = appendHelper(conj[i][3][0], pro);
          /*
          conj[i][0][0] = appendPronoun(conj[i][0][0], pro, BP);
          conj[i][1][0] = appendPronoun(conj[i][1][0], pro, BP);
          conj[i][2][0] = appendPronoun(conj[i][2][0], pro, EP);
          conj[i][3][0] = appendPronoun(conj[i][3][0], pro, EP);
          */
          break;
        }
        if (i == PRESENT_SUBJUNCTIVE || i == PRESENT_PERFECT_SUBJ ||
            i == IMPERFECT_SUBJUNCTIVE || i == PAST_PERFECT_SUBJ ||
            i == FUTURE_SUBJUNCTIVE || i == FUTURE_PERFECT_SUBJ ||
            i == IMPERATIVE_NEG) {
          conj[i][0][m] = prependPronoun(conj[i][0][m], pro);
          conj[i][1][m] = prependPronoun(conj[i][1][m], pro);
          conj[i][2][m] = prependPronoun(conj[i][2][m], pro);
          conj[i][3][m] = prependPronoun(conj[i][3][m], pro);
        } else {
          if (conj[i][0][m] != null) {
            conj[i][0][m] += "-" + pro;
          }
          if (conj[i][1][m] != null) {
            conj[i][1][m] += "-" + pro;
          }
          conj[i][2][m] = appendHelper(conj[i][2][m], pro);
          conj[i][3][m] = appendHelper(conj[i][3][m], pro);
          /*
          conj[i][0][m] = appendPronoun(conj[i][0][m], pro, BP);
          conj[i][1][m] = appendPronoun(conj[i][1][m], pro, BP);
          conj[i][2][m] = appendPronoun(conj[i][2][m], pro, EP);
          conj[i][3][m] = appendPronoun(conj[i][3][m], pro, EP);
          */
        }
      }
    }

    return conj;
    
    /*
    
    //String[][][] simpConj = conjAllProgressive();
    //return conjAllWithPronouns(simpConj, pro1, pro2);
    
    String pro = contractPronouns(pro1, pro2);
    String[][][] simpConj = conjAllProgressive();
    String[][][] temp = conjAllWithPronouns(simpConj, pro1, pro2);
    String[][][] conjPros = new String[NUM_TENSES][4][];
    String gerundEPAO = appendPronoun(conjGerund(EP, AO), pro, EP);
    String gerundEPnoAO = appendPronoun(conjGerund(EP, !AO), pro, EP);
    for (int i = 0; i < NUM_TENSES; i++) {
      conjPros[i][0] = temp[i][0];
      conjPros[i][1] = temp[i][1];
      conjPros[i][2] = new String[temp[i][2].length];
      conjPros[i][3] = new String[temp[i][2].length];
      for (int j = 0; j < simpConj[i][2].length; j++) {
        if (simpConj[i][2][j] != null) {
            conjPros[i][2][j] = estarTable[i][2][j] + " a " + gerundEPnoAO; 
            conjPros[i][3][j] = estarTable[i][3][j] + " a " + gerundEPAO;
        }
      }
    }
    return conjPros;*/
  }
  
  String[][][] conjAllPassiveWithPronouns(String pro) {
    String[] validPros = new String[]{
        "me", "te", "lhe", "nos", "vos", "lhes" };
    String[][][] conj = null;
    for (String s: validPros) {
      if (s.equals(pro)) {
        conj = conjAllPassive();
        for (int i = 0; i < NUM_TENSES; i++) {
          for (int m = 0; m < 6; m++) {
            if (conj[i][0].length == 1) {
              conj[i][0][0] = appendPronoun(conj[i][0][0], pro, BP);
              conj[i][1][0] = appendPronoun(conj[i][1][0], pro, BP);
              conj[i][2][0] = appendPronoun(conj[i][2][0], pro, EP);
              conj[i][3][0] = appendPronoun(conj[i][3][0], pro, EP);
              break;
            }
            if (i == PRESENT_SUBJUNCTIVE ||
                i == IMPERFECT_SUBJUNCTIVE ||
                i == FUTURE_SUBJUNCTIVE ||
                i == PRESENT_PERFECT_SUBJ ||
                i == PAST_PERFECT_SUBJ ||
                i == FUTURE_PERFECT_SUBJ) {
              conj[i][0][m] = prependPronoun(conj[i][0][m], pro);
              conj[i][1][m] = prependPronoun(conj[i][1][m], pro);
              conj[i][2][m] = prependPronoun(conj[i][2][m], pro);
              conj[i][3][m] = prependPronoun(conj[i][3][m], pro);
            } else if (i > GERUND) {
              conj[i][0][m] = appendPronoun(conj[i][0][m], pro, BP);
              conj[i][1][m] = appendPronoun(conj[i][1][m], pro, BP);
              conj[i][2][m] = appendPronoun(conj[i][2][m], pro, EP);
              conj[i][3][m] = appendPronoun(conj[i][3][m], pro, EP);
            } else {
              conj[i][0][m] = prependPronoun(conj[i][0][m], pro);
              conj[i][1][m] = prependPronoun(conj[i][1][m], pro);
              conj[i][2][m] = appendPronoun(conj[i][2][m], pro, EP);
              conj[i][3][m] = appendPronoun(conj[i][3][m], pro, EP);
            }
          }
        }
      }
    }

    return conj;
    
    /*
    String[][][] simpConj = conjAllPassive();
    return conjAllWithPronouns(simpConj, pro1, pro2);
    */
  }
  
  private String contractPronouns(String pro1, String pro2) {
    if (pro2 == null) {
      return pro1;
    }
    if (pro1 == null) {
      System.out.println("This is unnacceptable, soldier!  I need a pronoun.");
    }
    switch (pro2) {
      case "o":
        switch (pro1) {
          case "me":
            return "mo";
          case "te":
            return "to";
          case "lhe":
            return "lho";
          case "nos":
            return "no-lo";
          case "vos":
            return "vo-lo";
          case "lhes":
            return "lho";
        }
      case "a":
        switch (pro1) {
          case "me":
            return "ma";
          case "te":
            return "ta";
          case "lhe":
            return "lha";
          case "nos":
            return "no-la";
          case "vos":
            return "vo-la";
          case "lhes":
            return "lha";
        }
      case "os":
        switch (pro1) {
          case "me":
            return "mos";
          case "te":
            return "tos";
          case "lhe":
            return "lhos";
          case "nos":
            return "no-los";
          case "vos":
            return "vo-los";
          case "lhes":
            return "lhos";
        }
      case "as":
        switch (pro1) {
          case "me":
            return "mas";
          case "te":
            return "tas";
          case "lhe":
            return "lhas";
          case "nos":
            return "no-las";
          case "vos":
            return "vo-las";
          case "lhes":
            return "lhas";
        }
    }
    return pro1 + "-" + pro2;
  }
  
  private String getConjugation(String[][][] conjTable, int tense, int whom,
      int var, boolean AO) {
    if (var == BP) {
      if (AO) {
        return conjTable[tense][1][whom];
      } else {
        return conjTable[tense][0][whom];
      }
    } else {
      if (AO) {
        return conjTable[tense][3][whom];
      } else {
        return conjTable[tense][2][whom];
      }
    }
  }
 
  private void conjUpTo(int tense) {
    conjTable = new String[NUM_TENSES][4][];
    for (int i = 0; i <= tense; i++) {
      conjTable[i][0] = conjTense(i, BP, !AO);
      conjTable[i][1] = conjTense(i, BP, AO);
      conjTable[i][2] = conjTense(i, EP, !AO);
      conjTable[i][3] = conjTense(i, EP, AO);
    }
  }
  
  private String[] conjTense(int tense, int var, boolean AO) {
    switch (tense) {
      case PRESENT_INDICATIVE:
        return conjPresInd(var, AO);
      case PRETERITE_IMPERFECT:
        return conjPretImp(var, AO);
      case PRETERITE_PERFECT:
        return conjPretPerf(var, AO);
      case PLUPERFECT_INDICATIVE:
        return conjPluInd(var, AO);
      case FUTURE_INDICATIVE:
        return conjFutInd(var, AO);
      case CONDITIONAL:
        return conjCond(var, AO);
      case PRESENT_SUBJUNCTIVE:
        return conjPresSubj(var, AO);
      case IMPERFECT_SUBJUNCTIVE:
        return conjImpSubj(var, AO);
      case FUTURE_SUBJUNCTIVE:
        return conjFutSubj(var, AO);
      case PERSONAL_INFINITIVE:
        return conjPersInf(var, AO);
      case IMPERSONAL_INFINITIVE:
        return new String[]{ getInfinitive(var, AO) };
      case IMPERATIVE_AFF:
        return conjImpAff(var, AO);
      case IMPERATIVE_NEG:
        return conjImpNeg(var, AO);
      case GERUND:
        return new String[]{ conjGerund(var, AO) };
      case PAST_PARTICIPLE:
        return new String[]{ conjPastPart(var, AO) };
      case IMP_INFINITIVE_PERFECT:
        return new String[]{ conjCompImpInf(var, AO) };
      default:
        return conjCompTense(tense, var, AO);
    }
  }

  String[][][] conjAll() {
    conjUpTo(IMP_INFINITIVE_PERFECT);
    return conjTable;
  }
  
  static String[][] conjVariant(String[][][] conj, int var) {
    String[][] varConj = new String[conj.length][];
    for (int i = 0; i < conj.length; i++) {
      varConj[i] = conj[i][var];
    }
    return varConj;
  }

  private void printTenses(String type, String pro1, String pro2) {
    if (isValidVerb()) {
      String[][][] conj = null;
      if (type.equals("reg")) {
        conj = conjAll();
        if (pro1 != null && pro1.equals("se")) {
          conj = conjAllReflex(conj);
        } else if (pro1 != null) {
          conj = conjAllWithPronouns(conj, pro1, pro2);
        } 
      } else if (type.equals("prog")) {
        if (pro1 != null) {
          if (pro1.equals("se")) {
            conj = conjAllProgressiveReflexive();
          } else {
            conj = conjAllProgressiveWithPronouns(pro1, pro2);
          }
        } else {
          conj = conjAllProgressive();
        }
      } else {
        if (pro1 != null) {
          conj = conjAllPassiveWithPronouns(pro1);
        } else {
          conj = conjAllPassive();
        }
      }
      String pronoun = "";
      if (pro1 != null) {
        if (pro1.equals("se")) {
          pronoun += " [Reflexive]";
        } else {
          if (pro2 == null) {
            pronoun += " [\"" + pro1 + "\"]";
          } else {
            pronoun += " [\"" + pro1 + "\",\"" + pro2 + "\"]";
          }
        }
      }
      int len;
      if (hasOnlyThPerSing() || hasOnlyThPer()) {
    	  len = conj[PAST_PERFECT_SUBJ][0][2].length() + 2;
      }
      else {
    	  len = conj[PAST_PERFECT_SUBJ][0][3].length();
      }
      if (pro1 != null) {
        len += 12;
      }
      for (int i = 0; i < NUM_TENSES; i++) {
        System.out.print(getTenseName(i, type) + pronoun);
        System.out.println();
        System.out.println("\n");
        System.out.printf("%-" + len + "." + len + "s %-" + len + "." + len + 
            "s " + "%-" + len + "." + len + "s %-" + len + "." + len + "s%n%n",
            "BP (no AO)", "BP (AO)", "EP (no AO)", "EP (AO)");
        for (int j = 0; j < 6; j++) {
          String out1 = conj[i][0][j];
          String out2 = conj[i][1][j];
          String out3 = conj[i][2][j];
          String out4 = conj[i][3][j];
          int cur = 0;
          for (String s: new String[]{out1, out2, out3, out4 }) {
            if (s == null) {
              conj[i][cur][j] = " -- ";
            }
            cur++;
          }
          System.out.printf("%-" + len + "." + len + "s %-" + len + "." + len + 
              "s " + "%-" + len + "." + len + "s %-" + len + "." + len + "s%n",
              conj[i][0][j], conj[i][1][j], conj[i][2][j], conj[i][3][j]);
          if (conj[i][0].length == 1) {
            break;
          }
        }
        System.out.println("\n");
      }
    }
  }
  
  public static String getTenseName(int tense, String type) {
    String out = "";
    switch (tense) {
      case PRESENT_INDICATIVE:
        out += "Present Indicative"; break;
      case PRETERITE_IMPERFECT:
        out += "Preterite Imperfect"; break;
      case PRETERITE_PERFECT:
        out += "Preterite Perfect"; break;
      case PLUPERFECT_INDICATIVE:
        out += "Pluperfect"; break;
      case FUTURE_INDICATIVE:
        out += "Future Indicative"; break;
      case CONDITIONAL:
        out += "Conditional"; break;
      case PRESENT_SUBJUNCTIVE:
        out += "Present Subjunctive"; break;
      case IMPERFECT_SUBJUNCTIVE:
        out += "Past Subjunctive"; break;
      case FUTURE_SUBJUNCTIVE:
        out += "Future Subjunctive"; break;
      case PERSONAL_INFINITIVE:
        out += "Personal Infinitive"; break;
      case IMPERSONAL_INFINITIVE:
        out += "Impersonal Infinitive"; break;
      case IMPERATIVE_AFF:
        out += "Imperative (Affirmative)"; break;
      case IMPERATIVE_NEG:
        out += "Imperative (Negative)"; break;
      case GERUND:
        out += "Present Participle"; break;
      case PAST_PARTICIPLE:
        out += "Past Participle"; break;
      case PRESENT_PERFECT:
        out += "Present Perfect"; break;
      case COMPOUND_PAST_PERFECT:
        out += "Compound Preterite Perfect"; break;
      case COMPOUND_PLUPERFECT:
        out += "Compound Pluperfect"; break;
      case FUTURE_PERFECT:
        out += "Future Perfect"; break;
      case CONDITIONAL_PERFECT:
        out += "Conditional Perfect"; break;
      case PRESENT_PERFECT_SUBJ:
        out += "Present Perfect Subjunctive"; break;
      case PAST_PERFECT_SUBJ:
        out += "Past Perfect Subjunctive"; break;
      case FUTURE_PERFECT_SUBJ:
        out += "Future Perfect Subjunctive"; break;
      case INFINITIVE_PERFECT:
        out += "Personal Infinitive Perfect"; break;
      case IMP_INFINITIVE_PERFECT:
        out += "Impersonal Infinitive Perfect"; break;
    }
    
    switch (type) {
      case "reg":
        out += ""; break;
      case "prog":
        out += " [Progressive]"; break;
      case "pass":
        out += " [Passive]"; break;
    }
    
    return out;
  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Please enter a verb:\n");
    while(input.hasNext()) {
      String inf = input.next();
      System.out.println();
      if (inf.toLowerCase().equals("done")) {
        break;
      }
      Verb v = new Verb(inf);/*
      if (v.isValidVerb()) {
        String[][][] conj = v.conjAll();
        for (int i = 0; i <= INFINITIVE_PERFECT; i++) {
          if (i != GERUND && i != PAST_PARTICIPLE  &&
              i != IMPERSONAL_INFINITIVE &&
              i != IMP_INFINITIVE_PERFECT) {
            for (int j = 0; j < 6; j++) {
              for (int k = 0; k < 4; k++) {
                System.out.print(conj[i][k][j] + "\t");
              }
              System.out.println();
            }
          } else {
            for (int k = 0; k < 4; k++) {
              System.out.print(v.conjTable[i][k][0] + "\t");
            }
            System.out.println();
          }
          System.out.println();
        }
        
        String[][][] passiveConj = v.conjAllPassive();
        for (int i = 0; i < NUM_TENSES; i++) {
            for (int j = 0; j < passiveConj[i][0].length; j++) {
              for (int k = 0; k < 4; k++) {
                System.out.print(passiveConj[i][k][j] + "\t");
              }
              System.out.println();
            }
          System.out.println();
        }
        
        String[][][] proConj = v.conjAllWithPronouns(v.conjTable, "os", null);
        //String[][][] proConj = v.conjAllReflex(v.conjTable);
        for (int i = 0; i <= IMP_INFINITIVE_PERFECT; i++) {
          for (int j = 0; j < 6; j++) {
            for (int k = 0; k < 4; k++) {
              System.out.print(proConj[i][k][j] + "\t");
            }
            System.out.println();
            if (i == GERUND || i == PAST_PARTICIPLE ||
                i == IMPERSONAL_INFINITIVE ||
                i == IMP_INFINITIVE_PERFECT) {
              break;
            }
          }
        System.out.println();
        }
      }
      String[][][] progConj = v.conjAllProgressiveWithPronouns("nos", "as");
      System.out.println();
      progConj = v.conjAllProgressiveReflexive();
      for (int i = 0; i <= IMP_INFINITIVE_PERFECT; i++) {
        for (int j = 0; j < 6; j++) {
          for (int k = 0; k < 4; k++) {
            System.out.print(progConj[i][k][j] + "\t");
          }
          System.out.println();
          if (i == GERUND || i == PAST_PARTICIPLE ||
              i == IMPERSONAL_INFINITIVE ||
              i == IMP_INFINITIVE_PERFECT) {
            break;
          }
        }
      System.out.println();
      }
      
      progConj = v.conjAllProgressiveWithPronouns("os", null);
      for (int i = 0; i <= IMP_INFINITIVE_PERFECT; i++) {
        for (int j = 0; j < 6; j++) {
          for (int k = 0; k < 4; k++) {
            System.out.print(progConj[i][k][j] + "\t");
          }
          System.out.println();
          if (i == GERUND || i == PAST_PARTICIPLE ||
              i == IMPERSONAL_INFINITIVE ||
              i == IMP_INFINITIVE_PERFECT) {
            break;
          }
        }
      System.out.println();
      }
      
      progConj = v.conjAllPassiveWithPronouns("vos");
      for (int i = 0; i <= IMP_INFINITIVE_PERFECT; i++) {
        for (int j = 0; j < 6; j++) {
          for (int k = 0; k < 4; k++) {
            System.out.print(progConj[i][k][j] + "\t");
          }
          System.out.println();
          if (i == GERUND || i == PAST_PARTICIPLE ||
              i == IMPERSONAL_INFINITIVE ||
              i == IMP_INFINITIVE_PERFECT) {
            break;
          }
        }
      System.out.println();
      }
      */
      v.printTenses("reg", null, null);
      v.printTenses("reg", "se", null);
      v.printTenses("reg", "o", null);
      v.printTenses("reg", "vos", "as");
      v.printTenses("prog", null, null);
      v.printTenses("prog", "se", null);
      v.printTenses("prog", "nos", null);
      v.printTenses("prog", "lhes", "o");
      v.printTenses("pass", null, null);
      v.printTenses("pass", "lhe", null);

      System.out.println();
      System.out.print("Please enter another verb (type DONE if done): ");
    }
    input.close();
    System.out.println("Thanks for using Conjugador!");
  }
}
