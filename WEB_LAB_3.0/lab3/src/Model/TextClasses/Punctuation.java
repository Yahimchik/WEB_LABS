package Model.TextClasses;

/**
 * Class Punctuation describing part of Sentence
 */
public class Punctuation extends TextPart {

    /**
     * private field puncMark
     */
    private final String puncMark;

    /**
     * GetMethod for private field puncMark
     */
    public String getPuncMark() {
        return puncMark;
    }

    /**
     * Constructor with parameter
     */
    public Punctuation(String mark) {
        this.puncMark = mark;
    }

    /**
     * Override method toString()
     * @return punctuation mark
     */
    @Override
    public String toString() {
        return puncMark;
    }
}
