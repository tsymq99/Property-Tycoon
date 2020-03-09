package GameLogic;

/**
 * This class represents a player of Property Tycoon game.
 *
 * @author HARRIET
 */
public class Player {
    public enum Name {boot, smartphone, goblet, hatstand, cat, spoon}

    private Name name;
    //a land that the player is at
    private Land location;
    //the money the player currently possesses
    private int balance;
    //how many turns he/she is busted for. Value 0 stands for 'not busted'.
    private int bustedRounds;
    //whether the player is allowed to buy properties.
    //It should be true once the player passes the "Go" land for the first time.
    private boolean mayBuyProperties;

    public Player(Name name) {
        this.name = name;
        balance = 1500;
        bustedRounds = 0;
        mayBuyProperties = false;
    }

    public String getName() {
        return name.toString();
    }

    /**
     * @return the land
     */
    public Land getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Land location) {
        if (location.equals(""))
            this.location = null;
        this.location = location;
    }

    /**
     * @return the balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * @return the bustedRounds
     */
    public int getBustedRounds() {
        return bustedRounds;
    }

    public void setBustedRounds(int bustedRounds) {
        this.bustedRounds = bustedRounds;
    }

    /**
     * @return the mayBuyProperties
     */
    public boolean mayBuyProperties() {
        return mayBuyProperties;
    }

    public void mayBuyProperties(boolean mayBuyProperties) {
        this.mayBuyProperties = mayBuyProperties;
    }


    public int makeTransaction(int amount) throws Exception {
        if (balance + amount < 0) {
            throw new Exception("This player can not afford the amount. ");
        }
        balance += amount;
        return balance;
    }

    @Override
    public String toString() {
        String msg = new String(name.toString() + '\n'
                + "Balance " + balance + '\n');
        if (bustedRounds > 0) {
            msg += "busted (" + bustedRounds + " rounds left)";
        }
        return msg;
    }

}

