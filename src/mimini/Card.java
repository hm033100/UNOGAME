package mimini;


/**
 * @coauthor: Hermes Mimini
 * @coauthor: Isaiah DeBenedetto
 * @version: 1.0
 * @date: N/A
 * @Statment: This is my own work.
 * @SIDENOTE: Worked with Isaiah DeBenedetto
 */
public class Card {

    public static final String BLACK_BOLD_BRIGHT = "\033[37m";
    public static final String ANSI_RED = "\033[31m";
    public static final String ANSI_GREEN = "\033[32m";
    public static final String ANSI_YELLOW = "\033[33m";
    public static final String ANSI_BLUE = "\033[34m";
    public static final String ANSI_RESET = "\033[0m";
    private String color;
    private final String value;

    /**
     * Constructor that takes String arguments
     *
     * @param color String
     * @param value String
     */
    public Card(String color, String value) {
        this.color = color;
        this.value = value;
    }

    /**
     * Returns the color of the card.
     *
     * @return String
     */
    public String getColor() {
        return color;
    }
    
    /**
     * This method will be used to display what color was chosen to the user.
     * 
     * @return String
     */
    public String chooseColor(){
        switch (color) {
                    case "R":
                        return ANSI_RED + "RED" + ANSI_RESET;
                    case "G":
                        return ANSI_GREEN + "GREEN" + ANSI_RESET;
                    case "Y":
                        return ANSI_YELLOW + "YELLOW" + ANSI_RESET;
                    case "B":
                        return ANSI_BLUE + "BLUE" + ANSI_RESET;
                    default:
                        return value + "";
                }
    }

    /**
     * Returns the value of the card.
     *
     * @return String
     */
    public String getValue() {
        return value;
    }

    /**
     * This method sets the color of the card.
     *
     * @param color String
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Returns true/false if cards match.
     *
     * @param c Card
     * @return Boolean
     */
    public boolean isMatch(Card c) {
        return c.value.equals(this.value) || c.color.equals(this.color);
    }

    /**
     * Output the string content of the card.
     *
     * @return String
     */
    @Override
    public String toString() {
        switch (value) {
            case "WC":
                return BLACK_BOLD_BRIGHT + value + ANSI_RESET;
            case "+4":
                return BLACK_BOLD_BRIGHT + value + ANSI_RESET;
            default:
                switch (color) {
                    case "R":
                        return ANSI_RED + value + ANSI_RESET;
                    case "G":
                        return ANSI_GREEN + value + ANSI_RESET;
                    case "Y":
                        return ANSI_YELLOW + value + ANSI_RESET;
                    case "B":
                        return ANSI_BLUE + value + ANSI_RESET;
                    default:
                        return value + "";
                }
        }
    }
}
