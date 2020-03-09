package GameLogic;

/**
 * This class represents a tradable land. It is abstract as a tradable land is either a property or a station.
 * It features having a landlord (which references a player), as well as cost, rent and value.
 *
 * @author Kingway
 */
public abstract class TradableLand extends Land {
    private Player landlord;
    private int nextCost;
    private int rent;
    private int value;

    public TradableLand(String name, String description, int nextCost, int rent) {
        super(name, description);
        this.nextCost = nextCost;
        this.rent = rent;
    }

    public Player getLandlord() {
        return landlord;
    }

    public void setLandlord(Player landlord) {
        this.landlord = landlord;
    }

    public int getNextCost() {
        return nextCost;
    }

    public void setNextCost(int nextCost) {
        this.nextCost = nextCost;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public int getValue() {
        return value;
    }

    public int getRent() {
        return rent;
    }

    @Override
    public String toString() {
        return super.toString()
                + "Landlord: " + landlord.getName() + '\n'
                + "Rent: " + rent + '\n';
    }
}
