package GameLogic;

/**
 * This class represents a station.
 *
 * @author Kingway
 */
public class Station extends TradableLand {

    public Station(String name, String description, int cost, int rent) {
        super(name, description, cost, rent);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public String help() {
        return toString()
                + "If player owns 1 station, rent is £25 \n"
                + "If player owns 2 stations, rent is £50 \n"
                + "If player owns 3 stations, rent is £100 \n"
                + "If player owns 4 stations, rent is £200 \n";
    }

    public int getRent() {
        return super.getRent();
    }
}
