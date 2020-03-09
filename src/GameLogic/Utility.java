package GameLogic;

/**
 * This class represents a utility.
 */
public class Utility extends TradableLand {

    public Utility(String name, String description, int nextCost, int rent) {
        super(name, description, nextCost, rent);
    }

    /**
     * A special getter method in Utility, which takes a dice number as coefficient of the resultant rent.
     *
     * @param diceNumber
     * @return
     * @throws Exception
     */
    public int getRent(int diceNumber) throws Exception {
        if (diceNumber <= 0 || diceNumber > 6) {
            throw new Exception("Illegal diceNumber value: " + diceNumber);
        }
        return diceNumber * getRent();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public String help() {
        return toString()
                + "If one utility is owned by a player, rent is 4 times value shown on dice. \n"
                + "If both utilities are owned by a player, rent is 10 time value shown on dice. \n";
    }

}
