package GameLogic;

/**
 * This class represents a property. It features sharing a color group with some others of the type, as well as being
 * upgradable.
 *
 * @author HARRIET
 */
public class Property extends TradableLand {

    public ColorGroup getColorGroup() {
        return colorGroup;
    }

    private enum Level {
        unimproved, houseOf1, housesOf2, housesOf3, housesOf4, hotel;

        public Level getNext() {
            switch (this) {
                case hotel:
                    return null;
            }
            return values()[ordinal() + 1];
        }
    }

    private enum ColorGroup {brown, blue, purple, orange, red, yellow, green, deepBlue}

    private Level level;
    private ColorGroup colorGroup;
    private boolean upgradable;


    public Property(String name, String description, ColorGroup colorGroup, int cost, int rent) {
        super(name, description, cost, rent);
        level = Level.unimproved;
        this.colorGroup = colorGroup;
        //since none of the lands belong to a player at this point
        upgradable = false;
    }

    /**
     * @return the isPromoted
     */
    public boolean isUpgradable() {
        return upgradable;
    }

    /**
     * @param isPromoted the isPromoted to set
     */
    public void setUpgradable(boolean isPromoted) {
        this.upgradable = isPromoted;
    }

    /**
     * @return
     */
    public String upgrade() {
        if (level.getNext() == null) {
            return null;
        } else {
            level = level.getNext();
        }

        // Rents and costs have to be updated.

        return "<" + level + "> : <" + getRent() + ">";

    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * @return
     */
    public String help() {
        // find all costs/rents instead of current ones
        return "";
    }
}

