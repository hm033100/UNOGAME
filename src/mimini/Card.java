package mimini;


/**
 * @coauthor: Hermes Mimini
 * @coauthor: Isaiah DeBenedetto
 * @version: 1.0
 * @date: N/A
 * @Statment: This is my own work.
 * @SIDENOTE: Worked with Isaiah DeBenedetto
 */
public record Card(String color, String value) {

    public static final String BLACK_BOLD_BRIGHT = "\033[37m";
    public static final String ANSI_RED = "\033[31m";
    public static final String ANSI_GREEN = "\033[32m";
    public static final String ANSI_YELLOW = "\033[33m";
    public static final String ANSI_BLUE = "\033[34m";
    public static final String ANSI_RESET = "\033[0m";

    /**
     * Constructor that takes String arguments
     *
     * @param color String
     * @param value String
     */
    public Card {
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
    public String chooseColor() {
        return switch (color) {
            case "R" -> ANSI_RED + "RED" + ANSI_RESET;
            case "G" -> ANSI_GREEN + "GREEN" + ANSI_RESET;
            case "Y" -> ANSI_YELLOW + "YELLOW" + ANSI_RESET;
            case "B" -> ANSI_BLUE + "BLUE" + ANSI_RESET;
            default -> value + "";
        };
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
        return switch (value) {
            case "WC", "+4" -> BLACK_BOLD_BRIGHT + value + ANSI_RESET;
            default -> switch (color) {
                case "R" -> ANSI_RED + value + ANSI_RESET;
                case "G" -> ANSI_GREEN + value + ANSI_RESET;
                case "Y" -> ANSI_YELLOW + value + ANSI_RESET;
                case "B" -> ANSI_BLUE + value + ANSI_RESET;
                default -> value + "";
            };
        };
    }
}
