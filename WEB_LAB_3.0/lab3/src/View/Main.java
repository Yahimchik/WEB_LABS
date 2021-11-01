package View;

import Controller.Parsers.ParseSentence;
import Controller.Parsers.ParseText;
import Model.EmptyFileException;
import Model.TextClasses.Punctuation;
import Model.TextClasses.Sentence;
import Model.TextClasses.Text;
import Model.TextClasses.Word;
import Model.TextException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class Main {


    static ResourceBundle bundle;
    static String stringText = "";
    static ArrayList<Word> stringWords = new ArrayList<>();
    private static final Logger logger = Logger.getLogger( Main.class.getName() );


    public static void main(String[] args) throws EmptyFileException, TextException{
        setLocale();
        if (!readFile("D:\\Java Projects\\WEB_Lab3\\lab3\\text.txt")){
            System.out.println(bundle.getString("ErrorWithReadingFile"));
            return;
        }

        if (stringText == "") {
            logger.info("File is empty!!!");
            //throw new EmptyFileException(bundle.getString("FileIsEmpty"));
            System.out.println(bundle.getString("FileIsEmpty"));
            return;
        }

        ParseText parseText = createParseChain();
        Text text = (Text) parseText.parse(stringText);

        if (text.getSentences().size() == 0 && text.getCode().size() == 0) {
            logger.info("No sentences and parts of code in text!");
            //throw new TextException(bundle.getString("NullArrayParts"));
            System.out.println(bundle.getString("NullArrayParts"));
            return;
        }

        System.out.println("\n\n" + bundle.getString("AllText") + "\n");
        System.out.println(text);


        if (text.getSentences().size() == 0) {
            logger.info( "No sentences in text!");
            //throw new TextException(bundle.getString("NoSentences"));
            System.out.println(bundle.getString("NoSentences"));
            return;
        }
        System.out.println(bundle.getString("Sentences") + "\n");
        ArrayList<Sentence> sentences = text.getSentences();
        for(Sentence sentence: sentences) {
            System.out.println(sentence);
        }
        System.out.println("\n\n" + bundle.getString("Words"));
        ArrayList<Word> words1 = sentences.get(0).getWords();
        for(Word word: words1) {
            System.out.print(word);
            System.out.print(", ");
        }

        System.out.println("\n\n" + bundle.getString("Punctuation"));
        ArrayList<Punctuation> punctuations1 = sentences.get(0).getMarks();
        for(Punctuation punctuation: punctuations1) {
            System.out.print(punctuation);
            System.out.print(" ");
        }

        System.out.println();

        StringBuffer textForProcessing = new StringBuffer(text.toString());
        String subA="WordInsert ";
        int i=0, pos1=0;
        while ((pos1 = textForProcessing.indexOf("же ", i)) != -1)
        {
            pos1 += 3;
            textForProcessing.insert(pos1, subA);
            i = pos1;
        }

        System.out.println();

        System.out.println("textForProcessing: " + textForProcessing);

        /////////////////////////////////////////////////////
        String trek = text.toString();

        String regx = "[^:]//.*|/\\\\*((?!=*/)(?s:.))+\\\\*/";
        char[] ca = regx.toCharArray();
        for (char c : ca) {
            trek = trek.replace(""+c, "");
        }

    }

    private static ParseText createParseChain() {
        ParseText parseText = new ParseText();
        parseText.setNextParse(new ParseSentence());
        return parseText;
    }

    private static boolean readFile(String filename) {
        try {
            Files.lines(Paths.get(filename)).forEach(s -> stringText += s);
        } catch (Exception ex) {
            logger.info(ex.toString());
            return false;
        }
        return true;
    }

    private static void setLocale() {
        Locale enLocale = new Locale("en", "EN", "UNIX");
        Locale ruLocale = new Locale("ru", "RU", "UNIX");
        Locale beLocale = new Locale("be", "BY", "UNIX");
        bundle = ResourceBundle.getBundle("Model.Localization.Localization", enLocale);
    }
}
