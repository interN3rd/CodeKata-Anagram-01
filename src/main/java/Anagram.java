public class Anagram {

    // attributes
    private String originalWord = "";

    // getter & setter
    public String getOriginalWord() {

        return originalWord;

    }

    // constructor
    public Anagram( String originalWord ) {

        this.originalWord = originalWord;

    }

    //methods
    public void start() {

        if( Validator.hasAnagrams( this.getOriginalWord() ) ) {

            System.out.println("There is at least one anagram for this word");

        } else {

            System.out.println("There is no anagram for this word");

        }

    }

}
