package GameLogic;

/**
 * This is the general superclass of the lands. It is abstract as a land is either tradable or non-tradable.
 *
 * @author HARRIET
 */
public abstract class Land {
    private String name;
    private String description;

    public Land (String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName () {
        return name;
    }

    @Override
    public String toString() {
        return new String(name + '\n'
                + description + '\n');
    }
}
