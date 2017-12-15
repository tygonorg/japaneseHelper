
/**
 * @author Hungtp
 *
 */
public class JapaneseHelper {
    private String romaji;
    private String hiragana;
    private String katakana;
    private char currChar;

    private boolean checkIsHinagana() {
        if (romaji.length() != 0) {
            if (romaji.charAt(0) > 255)
                return true;
        }
        return false;
    }

    public String Convert(String strConvert, boolean isHiragana) {
        romaji = strConvert;
        if (checkIsHinagana())
            return romaji;
        performConversion();
        if (isHiragana)
            return hiragana;
        else
            return katakana;
    }

    private void start() {
        currChar = romaji.charAt(0);
    }

    private void advance() {
        if (romaji.length() != 0) {
            romaji = romaji.substring(1, romaji.length());
            if (romaji.length() == 0) {
                currChar = 0;
                return;
            }
            currChar = romaji.charAt(0);
        }
    }

    private void longACheck() {
        if (currChar == 'a') {
            hiragana += "\u3042";
            katakana += "\u30FC";
            advance();
        }
    }

    private void longECheck() {
        if (currChar == 'e') {
            hiragana += "\u3048";
            katakana += "\u30FC";
            advance();
        }
    }

    private void longICheck() {
        if (currChar == 'i') {
            hiragana += "\u3044";
            katakana += "\u30FC";
            advance();
        }
    }

    private void longOCheck() {
        if (currChar == 'o') {
            hiragana += "\u304A";
            katakana += "\u30FC";
            advance();
        }
    }

    private void longUCheck() {
        if (currChar == 'u') {
            hiragana += "\u3046";
            katakana += "\u30FC";
            advance();
        }
    }

    private void forcedY() {
        if (currChar == 'a') {
            hiragana += "\u3083";
            katakana += "\u30E3";
            advance();
            longACheck();
        } else if (currChar == 'e') {
            hiragana += "\u3047";
            katakana += "\u30A7";
            advance();
            longECheck();
        } else if (currChar == 'i') {
            hiragana += "\u3043";
            katakana += "\u30A3";
            advance();
            longICheck();
        } else if (currChar == 'o') {
            hiragana += "\u3087";
            katakana += "\u30E7";
            advance();
            longOCheck();
        } else if (currChar == 'u') {
            hiragana += "\u3085";
            katakana += "\u30E5";
            advance();
            longUCheck();
        }
        // long a romaji
        else if (currChar == '\u00E2' || currChar == '\u0101') {
            hiragana += "\u3083\u3042";
            katakana += "\u30E3\u30FC";
            advance();
        }
        // long e romaji
        else if (currChar == '\u00EA' || currChar == '\u0113') {
            hiragana += "\u3047\u3048";
            katakana += "\u30A7\u30FC";
            advance();
        }
        // long i romaji
        else if (currChar == '\u00EE' || currChar == '\u012B') {
            hiragana += "\u3043\u3044";
            katakana += "\u30A3\u30FC";
            advance();
        }
        // long o romaji
        else if (currChar == '\u00F4' || currChar == '\u014D') {
            hiragana += "\u3087\u3046";
            katakana += "\u30E7\u30FC";
            advance();
        }
        // long u romaji
        else if (currChar == '\u00FB' || currChar == '\u016B') {
            hiragana += "\u3085\u3046";
            katakana += "\u30E5\u30FC";
            advance();
        }
    }

    // Expect a small "ya", "yu", or "yo" kana, but leave it alone for an i
    // such as for "chi", "ji", etc.
    private void partialForcedY() {
        if (currChar == 'i') {
            advance();
            longICheck();
        }
        // long i romaji
        else if (currChar == '\u00EE' || currChar == '\u012B') {
            hiragana += "\u3044";
            katakana += "\u30FC";
            advance();
        } else {
            forcedY();
        }
    }

    // adds small vowel or semivowel kana after a kana that has been dealt with
    // such as for fa, ve, t'i etc.
    private void forcedVowel() {
        if (currChar == 'a') {
            hiragana += "\u3041";
            katakana += "\u30A1";
            advance();
            longACheck();
        } else if (currChar == 'e') {
            hiragana += "\u3047";
            katakana += "\u30A7";
            advance();
            longECheck();
        } else if (currChar == 'i') {
            hiragana += "\u3043";
            katakana += "\u30A3";
            advance();
            longICheck();
        } else if (currChar == 'o') {
            hiragana += "\u3049";
            katakana += "\u30A9";
            advance();
            longOCheck();
        } else if (currChar == 'u') {
            hiragana += "\u3045";
            katakana += "\u30A5";
            advance();
            longUCheck();
        } else if (currChar == 'y') {
            advance();
            forcedY();
        }
        // long a romaji
        else if (currChar == '\u00E2' || currChar == '\u0101') {
            hiragana += "\u3041\u3042";
            katakana += "\u30A1\u30FC";
            advance();
        }
        // long e romaji
        else if (currChar == '\u00EA' || currChar == '\u0113') {
            hiragana += "\u3047\u3048";
            katakana += "\u30A7\u30FC";
            advance();
        }
        // long i romaji
        else if (currChar == '\u00EE' || currChar == '\u012B') {
            hiragana += "\u3043\u3044";
            katakana += "\u30A3\u30FC";
            advance();
        }
        // long o romaji
        else if (currChar == '\u00F4' || currChar == '\u014D') {
            hiragana += "\u3049\u3046";
            katakana += "\u30A9\u30FC";
            advance();
        }
        // long u romaji
        else if (currChar == '\u00FB' || currChar == '\u016B') {
            hiragana += "\u3045\u3046";
            katakana += "\u30A5\u30FC";
            advance();
        }
    }

    private void romajiA() {
        advance();
        hiragana += "\u3042";
        katakana += "\u30A2";
        longACheck();
    }

    private void romajiB() {
        advance();
        if (currChar == 'a') {
            hiragana += "\u3070";
            katakana += "\u30D0";
            advance();
            longACheck();
        } else if (currChar == 'b') {
            hiragana += "\u3063";
            katakana += "\u30C3";
            romajiB();
        } else if (currChar == 'e') {
            hiragana += "\u3079";
            katakana += "\u30D9";
            advance();
            longECheck();
        } else if (currChar == 'i') {
            hiragana += "\u3073";
            katakana += "\u30D3";
            advance();
            longICheck();
        } else if (currChar == 'o') {
            hiragana += "\u307C";
            katakana += "\u30DC";
            advance();
            longOCheck();
        } else if (currChar == 'u') {
            hiragana += "\u3076";
            katakana += "\u30D6";
            advance();
            longUCheck();
        } else if (currChar == 'y') {
            hiragana += "\u3073";
            katakana += "\u30D3";
            advance();
            forcedY();
        }
        // long a romaji
        else if (currChar == '\u00E2' || currChar == '\u0101') {
            hiragana += "\u3070\u3042";
            katakana += "\u30D0\u30FC";
            advance();
        }
        // long e romaji
        else if (currChar == '\u00EA' || currChar == '\u0113') {
            hiragana += "\u3079\u3048";
            katakana += "\u30D9\u30FC";
            advance();
        }
        // long i romaji
        else if (currChar == '\u00EE' || currChar == '\u012B') {
            hiragana += "\u3073\u3044";
            katakana += "\u30D3\u30FC";
            advance();
        }
        // long o romaji
        else if (currChar == '\u00F4' || currChar == '\u014D') {
            hiragana += "\u307C\u3046";
            katakana += "\u30DC\u30FC";
            advance();
        }
        // long u romaji
        else if (currChar == '\u00FB' || currChar == '\u016B') {
            hiragana += "\u3076\u3046";
            katakana += "\u30D6\u30FC";
            advance();
        } else {
            hiragana += "\u3076";
            katakana += "\u30D6";
        }
    }

    private void romajiC() {
        advance();
        if (currChar == 'c') {
            hiragana += "\u3063";
            katakana += "\u30C3";
            romajiC();
        } else if (currChar == 'h') {
            hiragana += "\u3061";
            katakana += "\u30C1";
            advance();
            partialForcedY();
        } else if (currChar == 'k') {
            hiragana += "\u3063";
            katakana += "\u30C3";
            romajiK();
        } else {
            hiragana += "\u304F";
            katakana += "\u30AF";
        }
    }

    private void romajiD() {
        advance();
        if (currChar == '\'') {
            advance();
            romajiDapos();
        } else if (currChar == 'a') {
            hiragana += "\u3060";
            katakana += "\u30C0";
            advance();
            longACheck();
        } else if (currChar == 'd') {
            hiragana += "\u3063";
            katakana += "\u30C3";
            romajiD();
        } else if (currChar == 'e') {
            hiragana += "\u3067";
            katakana += "\u30C7";
            advance();
            longECheck();
        } else if (currChar == 'i') {
            hiragana += "\u3067\u3043";
            katakana += "\u30C7\u30A3";
            advance();
            longICheck();
        } else if (currChar == 'j') {
            hiragana += "\u3062";
            katakana += "\u30C2";
            advance();
            partialForcedY();
        } else if (currChar == 'o') {
            hiragana += "\u3069";
            katakana += "\u30C9";
            advance();
            longOCheck();
        } else if (currChar == 'u') {
            hiragana += "\u3069\u3045";
            katakana += "\u30C9\u30A5";
            advance();
            longUCheck();
        } else if (currChar == 'y') {
            hiragana += "\u3067";
            katakana += "\u30C7";
            advance();
            forcedY();
        } else if (currChar == 'z') {
            advance();
            romajiDZ();
        }
        // long a romaji
        else if (currChar == '\u00E2' || currChar == '\u0101') {
            hiragana += "\u3060\u3042";
            katakana += "\u30C0\u30FC";
            advance();
        }
        // long e romaji
        else if (currChar == '\u00EA' || currChar == '\u0113') {
            hiragana += "\u3067\u3048";
            katakana += "\u30C7\u30FC";
            advance();
        }
        // long i romaji
        else if (currChar == '\u00EE' || currChar == '\u012B') {
            hiragana += "\u3067\u3043\u3044";
            katakana += "\u30C7\u30A3\u30FC";
            advance();
        }
        // long o romaji
        else if (currChar == '\u00F4' || currChar == '\u014D') {
            hiragana += "\u3069\u3046";
            katakana += "\u30C9\u30FC";
            advance();
        }
        // long u romaji
        else if (currChar == '\u00FB' || currChar == '\u016B') {
            hiragana += "\u3069\u3045\u3046";
            katakana += "\u30C9\u30A5\u30FC";
            advance();
        } else {
            hiragana += "\u3069";
            katakana += "\u30C9";
        }
    }

    private void romajiDapos() {
        if (currChar == 'u') {
            hiragana += "\u3069\u3045";
            katakana += "\u30C9\u30A5";
            advance();
            longUCheck();
        }
        // long u romaji
        else if (currChar == '\u00FB' || currChar == '\u016B') {
            hiragana += "\u3069\u3045\u3046";
            katakana += "\u30C9\u30A5\u30FC";
            advance();
        } else {
            hiragana += "\u3067";
            katakana += "\u30C7";
            forcedVowel();
        }
    }

    private void romajiDZ() {
        if (currChar == 'i') {
            hiragana += "\u3062";
            katakana += "\u30C2";
            advance();
            longICheck();
        } else if (currChar == 'u') {
            hiragana += "\u3065";
            katakana += "\u30C5";
            advance();
            longUCheck();
        } else if (currChar == 'y') {
            hiragana += "\u3062";
            katakana += "\u30C2";
            advance();
            forcedY();
        }
        // long i romaji
        else if (currChar == '\u00EE' || currChar == '\u012B') {
            hiragana += "\u3062\u3044";
            katakana += "\u30C2\u30FC";
            advance();
        }
        // long u romaji
        else if (currChar == '\u00FB' || currChar == '\u016B') {
            hiragana += "\u3065\u3046";
            katakana += "\u30C5\u30FC";
            advance();
        } else {
            hiragana += "\u3062";
            katakana += "\u30C2";
            forcedY();
        }
    }

    private void romajiE() {
        advance();
        hiragana += "\u3048";
        katakana += "\u30A8";
        longECheck();
    }

    private void romajiF() {
        advance();
        if (currChar == 'f') {
            hiragana += "\u3063";
            katakana += "\u30C3";
            romajiF();
        } else if (currChar == 'u') {
            hiragana += "\u3075";
            katakana += "\u30D5";
            advance();
            longUCheck();
        }
        // long u romaji
        else if (currChar == '\u00FB' || currChar == '\u016B') {
            hiragana += "\u3075\u3046";
            katakana += "\u30D5\u30FC";
            advance();
        } else {
            hiragana += "\u3075";
            katakana += "\u30D5";
            forcedVowel();
        }
    }

    private void romajiG() {
        advance();
        if (currChar == 'a') {
            hiragana += "\u304C";
            katakana += "\u30AC";
            advance();
            longACheck();
        } else if (currChar == 'e') {
            hiragana += "\u3052";
            katakana += "\u30B2";
            advance();
            longECheck();
        } else if (currChar == 'g') {
            hiragana += "\u3063";
            katakana += "\u30C3";
            romajiG();
        } else if (currChar == 'i') {
            hiragana += "\u304E";
            katakana += "\u30AE";
            advance();
            longICheck();
        } else if (currChar == 'o') {
            hiragana += "\u3054";
            katakana += "\u30B4";
            advance();
            longOCheck();
        } else if (currChar == 'u') {
            hiragana += "\u3050";
            katakana += "\u30B0";
            advance();
            longUCheck();
        } else if (currChar == 'w') {
            hiragana += "\u3050";
            katakana += "\u30B0";
            advance();
            forcedVowel();
        } else if (currChar == 'y') {
            hiragana += "\u304E";
            katakana += "\u30AE";
            advance();
            forcedY();
        }
        // long a romaji
        else if (currChar == '\u00E2' || currChar == '\u0101') {
            hiragana += "\u304C\u3042";
            katakana += "\u30AC\u30FC";
            advance();
        }
        // long e romaji
        else if (currChar == '\u00EA' || currChar == '\u0113') {
            hiragana += "\u3052\u3048";
            katakana += "\u30B2\u30FC";
            advance();
        }
        // long i romaji
        else if (currChar == '\u00EE' || currChar == '\u012B') {
            hiragana += "\u304E\u3044";
            katakana += "\u30AE\u30FC";
            advance();
        }
        // long o romaji
        else if (currChar == '\u00F4' || currChar == '\u014D') {
            hiragana += "\u3054\u3046";
            katakana += "\u30B4\u30FC";
            advance();
        }
        // long u romaji
        else if (currChar == '\u00FB' || currChar == '\u016B') {
            hiragana += "\u3050\u3046";
            katakana += "\u30B0\u30FC";
            advance();
        } else {
            hiragana += "\u3050";
            katakana += "\u30B0";
        }
    }

    private void romajiH() {
        advance();
        if (currChar == 'a') {
            hiragana += "\u306F";
            katakana += "\u30CF";
            advance();
            longACheck();
        } else if (currChar == 'e') {
            hiragana += "\u3078";
            katakana += "\u30D8";
            advance();
            longECheck();
        } else if (currChar == 'h') {
            hiragana += "\u3063";
            katakana += "\u30C3";
            romajiH();
        } else if (currChar == 'i') {
            hiragana += "\u3072";
            katakana += "\u30D2";
            advance();
            longICheck();
        } else if (currChar == 'o') {
            hiragana += "\u307B";
            katakana += "\u30DB";
            advance();
            longOCheck();
        } else if (currChar == 'u') {
            hiragana += "\u3075";
            katakana += "\u30D5";
            advance();
            longUCheck();
        } else if (currChar == 'y') {
            hiragana += "\u3072";
            katakana += "\u30D2";
            advance();
            forcedY();
        }
        // long a romaji
        else if (currChar == '\u00E2' || currChar == '\u0101') {
            hiragana += "\u306F\u3042";
            katakana += "\u30CF\u30FC";
            advance();
        }
        // long e romaji
        else if (currChar == '\u00EA' || currChar == '\u0113') {
            hiragana += "\u3078\u3048";
            katakana += "\u30D8\u30FC";
            advance();
        }
        // long i romaji
        else if (currChar == '\u00EE' || currChar == '\u012B') {
            hiragana += "\u3072\u3044";
            katakana += "\u30D2\u30FC";
            advance();
        }
        // long o romaji
        else if (currChar == '\u00F4' || currChar == '\u014D') {
            hiragana += "\u307B\u3046";
            katakana += "\u30DB\u30FC";
            advance();
        }
        // long u romaji
        else if (currChar == '\u00FB' || currChar == '\u016B') {
            hiragana += "\u3075\u3046";
            katakana += "\u30D5\u30FC";
            advance();
        } else {
            hiragana += "\u3075";
            katakana += "\u30D5";
        }
    }

    private void romajiI() {
        advance();
        hiragana += "\u3044";
        katakana += "\u30A4";
        longICheck();
    }

    private void romajiJ() {
        advance();
        if (currChar == 'j') {
            hiragana += "\u3063";
            katakana += "\u30C3";
            romajiJ();
        } else {
            hiragana += "\u3058";
            katakana += "\u30B8";
            partialForcedY();
        }
    }

    private void romajiK() {
        advance();
        if (currChar == 'a') {
            hiragana += "\u304B";
            katakana += "\u30AB";
            advance();
            longACheck();
        } else if (currChar == 'e') {
            hiragana += "\u3051";
            katakana += "\u30B1";
            advance();
            longECheck();
        } else if (currChar == 'i') {
            hiragana += "\u304D";
            katakana += "\u30AD";
            advance();
            longICheck();
        } else if (currChar == 'k') {
            hiragana += "\u3063";
            katakana += "\u30C3";
            romajiK();
        } else if (currChar == 'o') {
            hiragana += "\u3053";
            katakana += "\u30B3";
            advance();
            longOCheck();
        } else if (currChar == 'q') {
            hiragana += "\u3063";
            katakana += "\u30C3";
            romajiQ();
        } else if (currChar == 'u') {
            hiragana += "\u304F";
            katakana += "\u30AF";
            advance();
            longUCheck();
        } else if (currChar == 'w') {
            hiragana += "\u304F";
            katakana += "\u30AF";
            advance();
            forcedVowel();
        } else if (currChar == 'y') {
            hiragana += "\u304D";
            katakana += "\u30AD";
            advance();
            forcedY();
        }
        // long a romaji
        else if (currChar == '\u00E2' || currChar == '\u0101') {
            hiragana += "\u304D\u3042";
            katakana += "\u30AD\u30FC";
            advance();
        }
        // long e romaji
        else if (currChar == '\u00EA' || currChar == '\u0113') {
            hiragana += "\u3051\u3048";
            katakana += "\u30B1\u30FC";
            advance();
        }
        // long i romaji
        else if (currChar == '\u00EE' || currChar == '\u012B') {
            hiragana += "\u304D\u3044";
            katakana += "\u30AD\u30FC";
            advance();
        }
        // long o romaji
        else if (currChar == '\u00F4' || currChar == '\u014D') {
            hiragana += "\u3053\u3046";
            katakana += "\u30B3\u30FC";
            advance();
        }
        // long u romaji
        else if (currChar == '\u00FB' || currChar == '\u016B') {
            hiragana += "\u304F\u3046";
            katakana += "\u30AF\u30FC";
            advance();
        } else {
            hiragana += "\u304F";
            katakana += "\u30AF";
        }
    }

    private void romajiL() {
        advance();
        if (currChar == 'a') {
            hiragana += "\u3089";
            katakana += "\u30E9";
            advance();
            longACheck();
        } else if (currChar == 'e') {
            hiragana += "\u308C";
            katakana += "\u30EC";
            advance();
            longECheck();
        } else if (currChar == 'i') {
            hiragana += "\u308A";
            katakana += "\u30EA";
            advance();
            longICheck();
        } else if (currChar == 'l') {
            hiragana += "\u3063";
            katakana += "\u30C3";
            romajiL();
        } else if (currChar == 'o') {
            hiragana += "\u308D";
            katakana += "\u30ED";
            advance();
            longOCheck();
        } else if (currChar == 'u') {
            hiragana += "\u308B";
            katakana += "\u30EB";
            advance();
            longUCheck();
        } else if (currChar == 'y') {
            hiragana += "\u308A";
            katakana += "\u30EA";
            advance();
            forcedY();
        }
        // long a romaji
        else if (currChar == '\u00E2' || currChar == '\u0101') {
            hiragana += "\u3089\u3042";
            katakana += "\u30E9\u30FC";
            advance();
        }
        // long e romaji
        else if (currChar == '\u00EA' || currChar == '\u0113') {
            hiragana += "\u308C\u3048";
            katakana += "\u30EC\u30FC";
            advance();
        }
        // long i romaji
        else if (currChar == '\u00EE' || currChar == '\u012B') {
            hiragana += "\u308A\u3044";
            katakana += "\u30EA\u30FC";
            advance();
        }
        // long o romaji
        else if (currChar == '\u00F4' || currChar == '\u014D') {
            hiragana += "\u308D\u3046";
            katakana += "\u30ED\u30FC";
            advance();
        }
        // long u romaji
        else if (currChar == '\u00FB' || currChar == '\u016B') {
            hiragana += "\u308B\u3046";
            katakana += "\u30EB\u30FC";
            advance();
        } else {
            hiragana += "\u308B";
            katakana += "\u30EB";
        }
    }

    private void romajiM() {
        advance();
        if (currChar == 'a') {
            hiragana += "\u307E";
            katakana += "\u30DE";
            advance();
            longACheck();
        } else if (currChar == 'b') {
            hiragana += "\u3093";
            katakana += "\u30F3";
            romajiB();
        } else if (currChar == 'e') {
            hiragana += "\u3081";
            katakana += "\u30E1";
            advance();
            longECheck();
        } else if (currChar == 'i') {
            hiragana += "\u307F";
            katakana += "\u30DF";
            advance();
            longICheck();
        } else if (currChar == 'm') {
            hiragana += "\u3093";
            katakana += "\u30F3";
            romajiM();
        } else if (currChar == 'o') {
            hiragana += "\u3082";
            katakana += "\u30E2";
            advance();
            longOCheck();
        } else if (currChar == 'p') {
            hiragana += "\u3093";
            katakana += "\u30F3";
            romajiP();
        } else if (currChar == 'u') {
            hiragana += "\u3080";
            katakana += "\u30E0";
            advance();
            longUCheck();
        } else if (currChar == 'y') {
            hiragana += "\u307F";
            katakana += "\u30DF";
            advance();
            forcedY();
        }
        // long a romaji
        else if (currChar == '\u00E2' || currChar == '\u0101') {
            hiragana += "\u307E\u3042";
            katakana += "\u30DE\u30FC";
            advance();
        }
        // long e romaji
        else if (currChar == '\u00EA' || currChar == '\u0113') {
            hiragana += "\u3081\u3048";
            katakana += "\u30E1\u30FC";
            advance();
        }
        // long i romaji
        else if (currChar == '\u00EE' || currChar == '\u012B') {
            hiragana += "\u307F\u3044";
            katakana += "\u30DF\u30FC";
            advance();
        }
        // long o romaji
        else if (currChar == '\u00F4' || currChar == '\u014D') {
            hiragana += "\u3082\u3046";
            katakana += "\u30E2\u30FC";
            advance();
        }
        // long u romaji
        else if (currChar == '\u00FB' || currChar == '\u016B') {
            hiragana += "\u3080\u3046";
            katakana += "\u30E0\u30FC";
            advance();
        } else {
            hiragana += "\u307F";
            katakana += "\u30DF";
        }
    }

    private void romajiN() {
        advance();
        if (currChar == '\'') {
            hiragana += "\u3093";
            katakana += "\u30F3";
            advance();
        } else if (currChar == 'a') {
            hiragana += "\u306A";
            katakana += "\u30CA";
            advance();
            longACheck();
        } else if (currChar == 'e') {
            hiragana += "\u306D";
            katakana += "\u30CD";
            advance();
            longECheck();
        } else if (currChar == 'i') {
            hiragana += "\u306B";
            katakana += "\u30CB";
            advance();
            longICheck();
        } else if (currChar == 'o') {
            hiragana += "\u306E";
            katakana += "\u30CE";
            advance();
            longOCheck();
        } else if (currChar == 'u') {
            hiragana += "\u306C";
            katakana += "\u30CC";
            advance();
            longUCheck();
        } else if (currChar == 'y') {
            hiragana += "\u306B";
            katakana += "\u30CB";
            advance();
            forcedY();
        }
        // long a romaji
        else if (currChar == '\u00E2' || currChar == '\u0101') {
            hiragana += "\u306A\u3042";
            katakana += "\u30CA\u30FC";
            advance();
        }
        // long e romaji
        else if (currChar == '\u00EA' || currChar == '\u0113') {
            hiragana += "\u306D\u3048";
            katakana += "\u30CD\u30FC";
            advance();
        }
        // long i romaji
        else if (currChar == '\u00EE' || currChar == '\u012B') {
            hiragana += "\u306B\u3044";
            katakana += "\u30CB\u30FC";
            advance();
        }
        // long o romaji
        else if (currChar == '\u00F4' || currChar == '\u014D') {
            hiragana += "\u306E\u3046";
            katakana += "\u30CE\u30FC";
            advance();
        }
        // long u romaji
        else if (currChar == '\u00FB' || currChar == '\u016B') {
            hiragana += "\u306C\u3046";
            katakana += "\u30CC\u30FC";
            advance();
        } else {
            hiragana += "\u3093";
            katakana += "\u30F3";
        }
    }

    private void romajiO() {
        advance();
        hiragana += "\u304A";
        katakana += "\u30AA";
        longOCheck();
    }

    private void romajiP() {
        advance();
        if (currChar == 'a') {
            hiragana += "\u3071";
            katakana += "\u30D1";
            advance();
            longACheck();
        } else if (currChar == 'e') {
            hiragana += "\u307A";
            katakana += "\u30DA";
            advance();
            longECheck();
        } else if (currChar == 'i') {
            hiragana += "\u3074";
            katakana += "\u30D4";
            advance();
            longICheck();
        } else if (currChar == 'o') {
            hiragana += "\u307D";
            katakana += "\u30DD";
            advance();
            longOCheck();
        } else if (currChar == 'p') {
            hiragana += "\u3063";
            katakana += "\u30C3";
            romajiP();
        } else if (currChar == 'u') {
            hiragana += "\u3077";
            katakana += "\u30D7";
            advance();
            longUCheck();
        } else if (currChar == 'y') {
            hiragana += "\u3074";
            katakana += "\u30D4";
            advance();
            forcedY();
        }
        // long a romaji
        else if (currChar == '\u00E2' || currChar == '\u0101') {
            hiragana += "\u3071\u3042";
            katakana += "\u30D1\u30FC";
            advance();
        }
        // long e romaji
        else if (currChar == '\u00EA' || currChar == '\u0113') {
            hiragana += "\u307A\u3048";
            katakana += "\u30DA\u30FC";
            advance();
        }
        // long i romaji
        else if (currChar == '\u00EE' || currChar == '\u012B') {
            hiragana += "\u3074\u3044";
            katakana += "\u30D4\u30FC";
            advance();
        }
        // long o romaji
        else if (currChar == '\u00F4' || currChar == '\u014D') {
            hiragana += "\u307D\u3046";
            katakana += "\u30DD\u30FC";
            advance();
        }
        // long u romaji
        else if (currChar == '\u00FB' || currChar == '\u016B') {
            hiragana += "\u3077\u3046";
            katakana += "\u30D7\u30FC";
            advance();
        } else {
            hiragana += "\u3077";
            katakana += "\u30D7";
        }
    }

    private void romajiQ() {
        advance();
        if (currChar == 'q') {
            hiragana += "\u3063";
            katakana += "\u30C3";
            romajiQ();
        } else if (currChar == 'u') {
            hiragana += "\u304F";
            katakana += "\u30AF";
            advance();
            forcedVowel();
        } else {
            hiragana += "\u304F";
            katakana += "\u30AF";
        }
    }

    private void romajiR() {
        advance();
        if (currChar == 'a') {
            hiragana += "\u3089";
            katakana += "\u30E9";
            advance();
            longACheck();
        } else if (currChar == 'e') {
            hiragana += "\u308C";
            katakana += "\u30EC";
            advance();
            longECheck();
        } else if (currChar == 'i') {
            hiragana += "\u308A";
            katakana += "\u30EA";
            advance();
            longICheck();
        } else if (currChar == 'l') {
            hiragana += "\u3063";
            katakana += "\u30C3";
            romajiL();
        } else if (currChar == 'o') {
            hiragana += "\u308D";
            katakana += "\u30ED";
            advance();
            longOCheck();
        } else if (currChar == 'r') {
            hiragana += "\u3063";
            katakana += "\u30C3";
            romajiR();
        } else if (currChar == 'u') {
            hiragana += "\u308B";
            katakana += "\u30EB";
            advance();
            longUCheck();
        } else if (currChar == 'y') {
            hiragana += "\u308A";
            katakana += "\u30EA";
            advance();
            forcedY();
        }
        // long a romaji
        else if (currChar == '\u00E2' || currChar == '\u0101') {
            hiragana += "\u3089\u3042";
            katakana += "\u30E9\u30FC";
            advance();
        }
        // long e romaji
        else if (currChar == '\u00EA' || currChar == '\u0113') {
            hiragana += "\u308C\u3048";
            katakana += "\u30EC\u30FC";
            advance();
        }
        // long i romaji
        else if (currChar == '\u00EE' || currChar == '\u012B') {
            hiragana += "\u308A\u3044";
            katakana += "\u30EA\u30FC";
            advance();
        }
        // long o romaji
        else if (currChar == '\u00F4' || currChar == '\u014D') {
            hiragana += "\u308D\u3046";
            katakana += "\u30ED\u30FC";
            advance();
        }
        // long u romaji
        else if (currChar == '\u00FB' || currChar == '\u016B') {
            hiragana += "\u308B\u3046";
            katakana += "\u30EB\u30FC";
            advance();
        } else {
            hiragana += "\u308B";
            katakana += "\u30EB";
        }
    }

    private void romajiS() {
        advance();
        if (currChar == '\'') {
            hiragana += "\u3059";
            katakana += "\u30B9";
            advance();
            forcedVowel();
        } else if (currChar == 'a') {
            hiragana += "\u3055";
            katakana += "\u30B5";
            advance();
            longACheck();
        } else if (currChar == 'e') {
            hiragana += "\u305B";
            katakana += "\u30BB";
            advance();
            longECheck();
        } else if (currChar == 'h') {
            hiragana += "\u3057";
            katakana += "\u30B7";
            advance();
            partialForcedY();
        } else if (currChar == 'i') {
            hiragana += "\u3057";
            katakana += "\u30B7";
            advance();
            longICheck();
        } else if (currChar == 'o') {
            hiragana += "\u305D";
            katakana += "\u30BD";
            advance();
            longOCheck();
        } else if (currChar == 's') {
            hiragana += "\u3063";
            katakana += "\u30C3";
            romajiS();
        } else if (currChar == 'u') {
            hiragana += "\u3059";
            katakana += "\u30B9";
            advance();
            longUCheck();
        } else if (currChar == 'y') {
            hiragana += "\u3057";
            katakana += "\u30B7";
            advance();
            forcedY();
        }
        // long a romaji
        else if (currChar == '\u00E2' || currChar == '\u0101') {
            hiragana += "\u3055\u3042";
            katakana += "\u30B5\u30FC";
            advance();
        }
        // long e romaji
        else if (currChar == '\u00EA' || currChar == '\u0113') {
            hiragana += "\u305B\u3048";
            katakana += "\u30BB\u30FC";
            advance();
        }
        // long i romaji
        else if (currChar == '\u00EE' || currChar == '\u012B') {
            hiragana += "\u3057\u3044";
            katakana += "\u30B7\u30FC";
            advance();
        }
        // long o romaji
        else if (currChar == '\u00F4' || currChar == '\u014D') {
            hiragana += "\u305D\u3046";
            katakana += "\u30BD\u30FC";
            advance();
        }
        // long u romaji
        else if (currChar == '\u00FB' || currChar == '\u016B') {
            hiragana += "\u3059\u3046";
            katakana += "\u30B9\u30FC";
            advance();
        } else {
            hiragana += "\u3059";
            katakana += "\u30B9";
        }
    }

    private void romajiT() {
        advance();
        if (currChar == '\'') {
            advance();
            romajiTapos();
        } else if (currChar == 'a') {
            hiragana += "\u305F";
            katakana += "\u30BF";
            advance();
            longACheck();
        } else if (currChar == 'c') {
            hiragana += "\u3063";
            katakana += "\u30C3";
            romajiC();
        } else if (currChar == 'e') {
            hiragana += "\u3066";
            katakana += "\u30C6";
            advance();
            longECheck();
        } else if (currChar == 'i') {
            hiragana += "\u3061";
            katakana += "\u30C1";
            advance();
            longICheck();
        } else if (currChar == 'o') {
            hiragana += "\u3068";
            katakana += "\u30C8";
            advance();
            longOCheck();
        } else if (currChar == 's') {
            hiragana += "\u3064";
            katakana += "\u30C4";
            advance();
            romajiTS();
        } else if (currChar == 't') {
            hiragana += "\u3063";
            katakana += "\u30C3";
            romajiT();
        } else if (currChar == 'u') {
            hiragana += "\u3064";
            katakana += "\u30C4";
            advance();
            longUCheck();
        } else if (currChar == 'w') {
            hiragana += "\u3064";
            katakana += "\u30C4";
            advance();
            forcedVowel();
        } else if (currChar == 'y') {
            hiragana += "\u3061";
            katakana += "\u30C1";
            advance();
            forcedY();
        }
        // long a romaji
        else if (currChar == '\u00E2' || currChar == '\u0101') {
            hiragana += "\u305F\u3042";
            katakana += "\u30BF\u30FC";
            advance();
        }
        // long e romaji
        else if (currChar == '\u00EA' || currChar == '\u0113') {
            hiragana += "\u3066\u3048";
            katakana += "\u30C6\u30FC";
            advance();
        }
        // long i romaji
        else if (currChar == '\u00EE' || currChar == '\u012B') {
            hiragana += "\u3061\u3043\u3044";
            katakana += "\u30C1\u30A3\u30FC";
            advance();
        }
        // long o romaji
        else if (currChar == '\u00F4' || currChar == '\u014D') {
            hiragana += "\u3068\u3046";
            katakana += "\u30C8\u30FC";
            advance();
        }
        // long u romaji
        else if (currChar == '\u00FB' || currChar == '\u016B') {
            hiragana += "\u3064\u3046";
            katakana += "\u30C4\u30FC";
            advance();
        } else {
            hiragana += "\u3068";
            katakana += "\u30C8";
        }
    }

    private void romajiTapos() {
        if (currChar == 'u') {
            hiragana += "\u3068\u3045";
            katakana += "\u30C8\u30A5";
            advance();
            longUCheck();
        }
        // long u romaji
        else if (currChar == '\u00FB' || currChar == '\u016B') {
            hiragana += "\u3068\u3045\u3046";
            katakana += "\u30C8\u30A5\u30FC";
            advance();
        } else {
            hiragana += "\u3066";
            katakana += "\u30C6";
            forcedVowel();
        }
    }

    private void romajiTS() {
        if (currChar == 'u') {
            advance();
            longUCheck();
        } else if (currChar == 'y') {
            advance();
            forcedY();
        }
        // long u romaji
        else if (currChar == '\u00FB' || currChar == '\u016B') {
            hiragana += "\u3046";
            katakana += "\u30FC";
            advance();
        } else {
            forcedVowel();
        }
    }

    private void romajiU() {
        advance();
        hiragana += "\u3046";
        katakana += "\u30A6";
        longUCheck();
    }

    private void romajiV() {
        advance();
        if (currChar == '\'') {
            hiragana += "\u3094";
            katakana += "\u30F4";
            advance();
            forcedVowel();
        } else if (currChar == 'u') {
            hiragana += "\u3094";
            katakana += "\u30F4";
            longUCheck();
        } else if (currChar == 'v') {
            hiragana += "\u3063";
            katakana += "\u30C3";
            romajiV();
        } else if (currChar == 'y') {
            hiragana += "\u3094";
            katakana += "\u30F4";
            advance();
            forcedY();
        }
        // long u romaji
        else if (currChar == '\u00FB' || currChar == '\u016B') {
            hiragana += "\u3094\u3046";
            katakana += "\u30F4\u30FC";
            advance();
        } else {
            hiragana += "\u3094";
            katakana += "\u30F4";
            forcedVowel();
        }
    }

    private void romajiW() {
        advance();
        if (currChar == '\'') {
            hiragana += "\u3046";
            katakana += "\u30A6";
            advance();
            forcedVowel();
        } else if (currChar == 'a') {
            hiragana += "\u308F";
            katakana += "\u30EF";
            advance();
            longACheck();
        } else if (currChar == 'e') {
            hiragana += "\u3091";
            katakana += "\u30F1";
            advance();
            longECheck();
        } else if (currChar == 'i') {
            hiragana += "\u3090";
            katakana += "\u30F0";
            advance();
            longICheck();
        } else if (currChar == 'o') {
            hiragana += "\u3092";
            katakana += "\u30F2";
            advance();
            longOCheck();
        } else if (currChar == 'u') {
            hiragana += "\u3046\u3045";
            katakana += "\u30A6\u30A5";
            advance();
            longUCheck();
        } else if (currChar == 'w') {
            hiragana += "\u3063";
            katakana += "\u30C3";
            romajiW();
        } else if (currChar == 'y') {
            hiragana += "\u3046";
            katakana += "\u30A6";
            advance();
            forcedY();
        }
        // long a romaji
        else if (currChar == '\u00E2' || currChar == '\u0101') {
            hiragana += "\u308F\u3042";
            katakana += "\u30EF\u30FC";
            advance();
        }
        // long e romaji
        else if (currChar == '\u00EA' || currChar == '\u0113') {
            hiragana += "\u3091\u3048";
            katakana += "\u30F1\u30FC";
            advance();
        }
        // long i romaji
        else if (currChar == '\u00EE' || currChar == '\u012B') {
            hiragana += "\u3090\u3043\u3044";
            katakana += "\u30F0\u30A3\u30FC";
            advance();
        }
        // long o romaji
        else if (currChar == '\u00F4' || currChar == '\u014D') {
            hiragana += "\u3092\u3046";
            katakana += "\u30F2\u30FC";
            advance();
        }
        // long u romaji
        else if (currChar == '\u00FB' || currChar == '\u016B') {
            hiragana += "\u3046\u3045\u3046";
            katakana += "\u30A6\u30A5\u30FC";
            advance();
        } else {
            hiragana += "\u3046";
            katakana += "\u30A6";
        }
    }

    private void romajiX() {
        advance();
        if (currChar == '\'') {
            hiragana += "\u304D";
            katakana += "\u30AD";
            // let "x'" act like "kis"
            romajiS();
        } else if (currChar == 'a') {
            hiragana += "\u3041";
            katakana += "\u30A1";
            advance();
            longACheck();
        } else if (currChar == 'e') {
            hiragana += "\u3047";
            katakana += "\u30A7";
            advance();
            longECheck();
        } else if (currChar == 'i') {
            hiragana += "\u3043";
            katakana += "\u30A3";
            advance();
            longICheck();
        } else if (currChar == 'o') {
            hiragana += "\u3049";
            katakana += "\u30A9";
            advance();
            longOCheck();
        } else if (currChar == 't') {
            // do some special-case lookaheads instead of using separate
            // functions here
            if (romaji.charAt(1) == 'u') {
                advance();
                advance();
                hiragana += "\u3063";
                katakana += "\u30C3";
            }

            else if (romaji.charAt(1) == 's' && romaji.charAt(2) == 'u') {
                advance();
                advance();
                advance();
                hiragana += "\u3063";
                katakana += "\u30C3";
            }
        } else if (currChar == 'u') {
            hiragana += "\u3045";
            katakana += "\u30A5";
            advance();
            longUCheck();
        } else if (currChar == 'w') {
            if (romaji.charAt(1) == 'a') {
                advance();
                advance();
                hiragana += "\u308E";
                katakana += "\u30EE";
            }
        } else if (currChar == 'y') {
            advance();
            forcedY();
        }
        // long a romaji
        else if (currChar == '\u00E2' || currChar == '\u0101') {
            hiragana += "\u3041\u3042";
            katakana += "\u30A1\u30FC";
            advance();
        }
        // long e romaji
        else if (currChar == '\u00EA' || currChar == '\u0113') {
            hiragana += "\u3047\u3048";
            katakana += "\u30A7\u30FC";
            advance();
        }
        // long i romaji
        else if (currChar == '\u00EE' || currChar == '\u012B') {
            hiragana += "\u3043\u3044";
            katakana += "\u30A3\u30FC";
            advance();
        }
        // long o romaji
        else if (currChar == '\u00F4' || currChar == '\u014D') {
            hiragana += "\u3049\u3046";
            katakana += "\u30A9\u30FC";
            advance();
        }
        // long u romaji
        else if (currChar == '\u00FB' || currChar == '\u016B') {
            hiragana += "\u3045\u3046";
            katakana += "\u30A5\u30FC";
            advance();
        } else {
            hiragana += "\u304D\u3059";
            katakana += "\u30AD\u30B9";
        }
    }

    private void romajiY() {
        advance();
        if (currChar == '\'') {
            hiragana += "\u3044";
            katakana += "\u30A4";
            advance();
            forcedVowel();
        } else if (currChar == 'a') {
            hiragana += "\u3084";
            katakana += "\u30E4";
            advance();
            longACheck();
        } else if (currChar == 'e') {
            hiragana += "\u3044\u3047";
            katakana += "\u30A4\u30A7";
            advance();
            longECheck();
        } else if (currChar == 'i') {
            hiragana += "\u3044\u3043";
            katakana += "\u30A4\u30A3";
            advance();
            longICheck();
        } else if (currChar == 'o') {
            hiragana += "\u3088";
            katakana += "\u30E8";
            advance();
            longOCheck();
        } else if (currChar == 'u') {
            hiragana += "\u3086";
            katakana += "\u30E6";
            advance();
            longUCheck();
        } else if (currChar == 'y') {
            hiragana += "\u3063";
            katakana += "\u30C3";
            romajiY();
        }
        // long a romaji
        else if (currChar == '\u00E2' || currChar == '\u0101') {
            hiragana += "\u3083\u3042";
            katakana += "\u30E3\u30FC";
            advance();
        }
        // long e romaji
        else if (currChar == '\u00EA' || currChar == '\u0113') {
            hiragana += "\u3044\u3047\u3048";
            katakana += "\u30A4\u30A7\u30FC";
            advance();
        }
        // long i romaji
        else if (currChar == '\u00EE' || currChar == '\u012B') {
            hiragana += "\u3044\u3043\u3044";
            katakana += "\u30A4\u30A3\u30FC";
            advance();
        }
        // long o romaji
        else if (currChar == '\u00F4' || currChar == '\u014D') {
            hiragana += "\u3088\u3046";
            katakana += "\u30E8\u30FC";
            advance();
        }
        // long u romaji
        else if (currChar == '\u00FB' || currChar == '\u016B') {
            hiragana += "\u3086\u3045\u3046";
            katakana += "\u30E6\u30A5\u30FC";
            advance();
        } else {
            hiragana += "\u3044";
            katakana += "\u30A4";
        }
    }

    private void romajiZ() {
        advance();
        if (currChar == '\'') {
            hiragana += "\u305A";
            katakana += "\u30BA";
            advance();
            forcedVowel();
        } else if (currChar == 'a') {
            hiragana += "\u3056";
            katakana += "\u30B6";
            advance();
            longACheck();
        } else if (currChar == 'e') {
            hiragana += "\u305C";
            katakana += "\u30BC";
            advance();
            longECheck();
        } else if (currChar == 'i') {
            hiragana += "\u3058";
            katakana += "\u30B8";
            advance();
            longICheck();
        } else if (currChar == 'o') {
            hiragana += "\u305E";
            katakana += "\u30BE";
            advance();
            longOCheck();
        } else if (currChar == 'u') {
            hiragana += "\u305A";
            katakana += "\u30BA";
            advance();
            longUCheck();
        } else if (currChar == 'y') {
            hiragana += "\u3058";
            katakana += "\u30B8";
            advance();
            forcedY();
        } else if (currChar == 'z') {
            hiragana += "\u3063";
            katakana += "\u30C3";
            romajiZ();
        }
        // long a romaji
        else if (currChar == '\u00E2' || currChar == '\u0101') {
            hiragana += "\u3056\u3042";
            katakana += "\u30B6\u30FC";
            advance();
        }
        // long e romaji
        else if (currChar == '\u00EA' || currChar == '\u0113') {
            hiragana += "\u305C\u3048";
            katakana += "\u30BC\u30FC";
            advance();
        }
        // long i romaji
        else if (currChar == '\u00EE' || currChar == '\u012B') {
            hiragana += "\u3058\u3044";
            katakana += "\u30B8\u30FC";
            advance();
        }
        // long o romaji
        else if (currChar == '\u00F4' || currChar == '\u014D') {
            hiragana += "\u305E\u3046";
            katakana += "\u30BE\u30FC";
            advance();
        }
        // long u romaji
        else if (currChar == '\u00FB' || currChar == '\u016B') {
            hiragana += "\u305A\u3046";
            katakana += "\u30BA\u30FC";
            advance();
        } else {
            hiragana += "\u305A";
            katakana += "\u30BA";
        }
    }

    private void performConversion() {
        // romaji = document.forms[0].romaji.value;
        romaji = romaji.toLowerCase();
        hiragana = "";
        katakana = "";
        // loop to convert the romaji
        start();
        while (romaji.length() > 0) {
            if (currChar == 'a')
                romajiA();
            else if (currChar == 'b')
                romajiB();
            else if (currChar == 'c')
                romajiC();
            else if (currChar == 'd')
                romajiD();
            else if (currChar == 'e')
                romajiE();
            else if (currChar == 'f')
                romajiF();
            else if (currChar == 'g')
                romajiG();
            else if (currChar == 'h')
                romajiH();
            else if (currChar == 'i')
                romajiI();
            else if (currChar == 'j')
                romajiJ();
            else if (currChar == 'k')
                romajiK();
            else if (currChar == 'l')
                romajiL();
            else if (currChar == 'm')
                romajiM();
            else if (currChar == 'n')
                romajiN();
            else if (currChar == 'o')
                romajiO();
            else if (currChar == 'p')
                romajiP();
            else if (currChar == 'q')
                romajiQ();
            else if (currChar == 'r')
                romajiR();
            else if (currChar == 's')
                romajiS();
            else if (currChar == 't')
                romajiT();
            else if (currChar == 'u')
                romajiU();
            else if (currChar == 'v')
                romajiV();
            else if (currChar == 'w')
                romajiW();
            else if (currChar == 'x')
                romajiX();
            else if (currChar == 'y')
                romajiY();
            else if (currChar == 'z')
                romajiZ();
            else if (currChar == '-') {
                hiragana += "\u30FC";
                katakana += "\u30FC";
                advance();
            } else if (currChar == ',') {
                hiragana += "\u3001";
                katakana += "\u3001";
                advance();
            } else if (currChar == '.') {
                hiragana += "\u3002";
                katakana += "\u3002";
                advance();
            } else if (currChar == '\\') {
                advance();
                if (currChar == '.') {
                    hiragana += ".";
                    katakana += ".";
                    advance();
                } else {
                    hiragana += currChar;
                    katakana += currChar;
                }
            }
            // long a romaji
            else if (currChar == '\u00E2' || currChar == '\u0101') {
                hiragana += "\u3042\u3042";
                katakana += "\u30A2\u30FC";
                advance();
            }
            // long e romaji
            else if (currChar == '\u00EA' || currChar == '\u0113') {
                hiragana += "\u3048\u3048";
                katakana += "\u30A8\u30FC";
                advance();
            }
            // long i romaji
            else if (currChar == '\u00EE' || currChar == '\u012B') {
                hiragana += "\u3044\u3044";
                katakana += "\u30A4\u30FC";
                advance();
            }
            // long o romaji
            else if (currChar == '\u00F4' || currChar == '\u014D') {
                hiragana += "\u304A\u3046";
                katakana += "\u30AA\u30FC";
                advance();
            }
            // long u romaji
            else if (currChar == '\u00FB' || currChar == '\u016B') {
                hiragana += "\u3046\u3046";
                katakana += "\u30A6\u30FC";
                advance();
            } else if (currChar == ' ') {
                // ignore most spaces
                advance();
            } else {
                hiragana += currChar;
                katakana += currChar;
                advance();
            }
        }

    }
}
