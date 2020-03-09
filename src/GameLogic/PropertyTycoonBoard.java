package GameLogic;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents the main logic of the game. The UI shall reference an instance of it. The variables and
 * methods should be accessed to perform a complete functional game. Several methods are deprecated as they are not
 * reasonably encapsulated but offers good interfaces for JUnit tests.
 *
 * @author HARRIET
 */
public class PropertyTycoonBoard {

    //all existing players
    private ArrayList<Player> players = new ArrayList();
    //all lands on the game board
    private ArrayList<Land> lands = new ArrayList();

    //total sum of money of the bank
    private int bankAsset;

    //a counter on whose turn it is in the game
    //the value of the variable corresponds to the index in the player
    //indicating which player's turn is currently.

    // = Player.
    private Player playerInTurn;

    //how many turns has the game been running
    private int currentRound;

    //the type of settlement when a player arrives in a land
    public enum SettlementType {none, buyLand, upgradeProperty, payRent, payUtilityRent, Go, PotLuck, OpportunityKnocks, SuperTax, IncomeTax, FreeParking, Jail}

    private static Random random = new Random();

    /**
     * This method initializes the game with given collection of players and lands. It also sets up the basic game
     * variables.
     *
     * @param players
     * @param lands
     * @throws Exception
     */
    public PropertyTycoonBoard(Player[] players, Land[] lands) throws Exception {
        if (players == null || lands == null || players.length == 0 || lands.length == 0) {
            throw new Exception("");
        }

        for (Land l : lands) {
            this.lands.add(l);
        }

        for (Player p : players) {
            this.players.add(p);
        }

        currentRound = 1;
        bankAsset = 50000;
        playerInTurn = this.players.get(0);

    }

    /**
     * This constructor method overloads PropertyTycoon(Player[], Land[]). It automatically initializes the game with
     * given number of players and AI players. Each player will be assigned to a random token. The board will consist
     * of fixed 40-land map.
     *
     * @param numberOfPlayers
     * @param numberOfBots
     */
    public PropertyTycoonBoard(int numberOfPlayers, int numberOfBots) {
        Player[] players = new Player[numberOfPlayers + numberOfBots];
        // Land[] lands = new Land[40];

        ArrayList<Player.Name> names = new ArrayList<>();
        names.add(Player.Name.boot);
        names.add(Player.Name.smartphone);
        names.add(Player.Name.goblet);
        names.add(Player.Name.hatstand);
        names.add(Player.Name.cat);
        names.add(Player.Name.spoon);

        for (int i = 0; i < numberOfPlayers; i++) {
            if (players[random.nextInt(numberOfPlayers + numberOfBots)] == null) {
                players[i] = new Player(names.remove(random.nextInt(6)));
            } else {
                i--;
            }
        }
        for (int i = 0; i < numberOfBots; i++) {
            if (players[random.nextInt(numberOfPlayers + numberOfBots)] == null) {
                players[i] = new PlayerBot(names.remove(random.nextInt(6)));
            } else {
                i--;
            }
        }
        for (Player p : players) {
            this.players.add(p);
        }

        // lands.add();

        /*
        lands[0] = new NonTradableLand("Go", "Pass here to get £200. ", LandEvent.Go);
        lands[1] = new;
        lands[2] = new;
        lands[3] = new;
        lands[4] = new;
        lands[5] = new;
        lands[6] = new;
        lands[7] = new;
        lands[8] = new;
        lands[9] = new;
        lands[10] = new;
        lands[11] = new;
        lands[12] = new;
        lands[13] = new;
        lands[14] = new;
        lands[15] = new;
        lands[16] = new;
        lands[17] = new;
        lands[18] = new;
        lands[19] = new;
        lands[20] = new;
        lands[21] = new;
        lands[22] = new;
        lands[23] = new;
        lands[24] = new;
        lands[25] = new;
        lands[26] = new;
        lands[27] = new;
        lands[28] = new;
        lands[29] = new;
        lands[30] = new;
        lands[31] = new;
        lands[32] = new;
        lands[33] = new;
        lands[34] = new;
        lands[35] = new;
        lands[36] = new;
        lands[37] = new;
        lands[38] = new;
        lands[39] = new;
        */

    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getNumberOfPlayers() {
        return players.size();
    }

    public ArrayList<Land> getLands() {
        return lands;
    }

    public int bankTransaction() {
        return bankAsset;
    }

    public Player getPlayerInTurn() {
        return playerInTurn;
    }

    public int getCurrentRound() {
        return currentRound;
    }


    /**
     * Generates a random integer between 1 and 6 inclusive.
     *
     * @return A random integer between 1 and 6 inclusive.
     */
    public static int throwDice() {
        //return random 1 to 6;
        return random.nextInt(6) + 1;
    }

    /**
     * This method examines if the current player is busted, namely shall skip its turn. If so, it will decrease the
     * busted rounds by 1. This method should be called at the beginning of a turn.
     *
     * @return True if the current player is busted, false otherwise. This value shall be used to judge if the current
     * player is skipping its turn.
     */
    public boolean isSkippingTurn() {
        if (playerInTurn.getBustedRounds() > 0) {
            playerInTurn.setBustedRounds(playerInTurn.getBustedRounds() - 1);
            return true;
        }
        return false;
    }

    /**
     * Switch to the next player. This shall be the end of current player's turn, as well as beginning of the next
     * player's turn.
     *
     * @return Text message of the event.
     */
    public String nextPlayer() {
        String msg = "End of turn (" + playerInTurn.getName() + ") \n";
        playerInTurn = players.get(players.indexOf(playerInTurn) + 1);
        if (players.indexOf(playerInTurn) == 0) {
            currentRound++;
        }
        return msg + "Start of turn (" + playerInTurn + ") \n";
    }

    /**
     * This method lets the specified player buy the specified land if it belongs to the bank.
     *
     * @return Text message of the event.
     * @throws Exception
     */
    public String buyLand() throws Exception {
        TradableLand land = (TradableLand) playerInTurn.getLocation();
        int price = ((TradableLand) land).getNextCost();

        if (land instanceof TradableLand) {
            if (playerInTurn.getBalance() >= price) {
                makeBankTransaction(playerInTurn, -price);    //player pays the bank the cost of it
                ((TradableLand) land).setLandlord(playerInTurn);   //ownership of the land is changed

                if (land instanceof Property) {
                    updatePropertyColorGroup((Property) land);
                } else if (land instanceof Station) {
                    updateStationRent((Station) land);
                } else {
                    //
                }

                return playerInTurn.getName() + " bought " + land.getName() + " with cost of " + price;
            } else {
                return "Insufficient balance. ";
            }
        } else {
            throw new Exception("This land is not tradable.");
        }
    }

    /**
     * This method upgrades the specified property if it belongs to the specified player.
     *
     * @return Text message of the event.
     */
    public String upgradeProperty() throws Exception {
        TradableLand land = (TradableLand) playerInTurn.getLocation();
        int upgradeCost = ((Property) land).getNextCost();

        if (land instanceof Property && ((Property) land).isUpgradable()) {
            if (!((Property) land).getLandlord().equals(playerInTurn)) {
                throw new Exception("The property (" + land.getName() + ") does not belong to the player ("
                        + playerInTurn.getName() + ").");
            }

            if (playerInTurn.getBalance() >= upgradeCost) {
                makeBankTransaction(playerInTurn, -upgradeCost);   //The player pays the bank the cost of it
                ((Property) land).setLandlord(playerInTurn);

                return playerInTurn.getName() + " upgraded " + land.getName() + " with cost of " + upgradeCost + '\n';
            } else {
                return "Insufficient funds. \n";
            }
        } else {
            throw new Exception("This land is not upgradable. ");
        }
    }

    /**
     * This method lets the specified player sell the specified lands that belongs to him.
     *
     * @param landsToSell
     * @return Text message of the event.
     * @throws Exception
     */
    public int sellLands(TradableLand[] landsToSell) throws Exception {
        int amount = 0;
        for (TradableLand l : landsToSell) {
            if (!((TradableLand) l).getLandlord().equals(playerInTurn)) {
                throw new Exception("Lands belonging to different players are selected. " + l.toString());
            } else {
                amount += ((TradableLand) l).getNextCost();
                ((TradableLand) l).setLandlord(null);
                if (l instanceof Property) {
                    updatePropertyColorGroup((Property) l);
                } else {
                    updateStationRent((Station) l);
                }
            }
        }
        makeBankTransaction(playerInTurn, amount);
        return amount;
    }

    /**
     * This method overrides sellLands(TradableLand[]). It uses greedy algorithm to sell the best combination of lands
     * belonging to the current player. This method is to call when the player can not afford a mandatory payment and
     * refuses to sell more lands to raise sufficient fund to pay.
     *
     * @return Amount paid to the player by selling the lands.
     */
    public int sellLands() {

    }

    /**
     * @return
     * @throws Exception
     */
    public int payRent() throws Exception {
        TradableLand location = (TradableLand) playerInTurn.getLocation();
        if (location instanceof Utility) {
            throw new Exception("Wrong method called. Use payUtilityRent(int). ");
        }
        int amount = location.getRent();
        makePlayerTransaction(playerInTurn, location.getLandlord(), amount);
        return amount;
    }

    /**
     * @param diceNumber
     * @return
     * @throws Exception
     */
    public int payUtilityRent(int diceNumber) throws Exception {
        Utility location = (Utility) playerInTurn.getLocation();
        /*if (land instanceof Property || land instanceof Station) {
            throw new Exception("Wrong method called. Use payUtilityRent(). ");
        }*/
        if (diceNumber <= 0 || diceNumber > 6) {
            throw new Exception("Illegal dice number. ");
        }
        int amount = location.getRent() * diceNumber;
        makePlayerTransaction(playerInTurn, location.getLandlord(), amount);
        return amount;
    }

    /**
     * This method randomly draws an 'Pot Luck' card and performs the corresponding actions.
     *
     * @return Text message of the event.
     */
    public String potLuck() {
        //randomly draws a pot luck card
        //player acts accordingly
        Random random = new Random();
        int index = random.nextInt(15);
        return "\n";
    }

    /**
     * This method randomly draws an 'Opportunity Knocks' card and performs the corresponding actions.
     *
     * @return Text message of the event.
     */
    public String opportunityKnocks() {
        //randomly draws an opportunity knocks card
        //player acts accordingly
        Random random = new Random();
        int index = random.nextInt(15);
        return "\n";
    }

    /**
     * This method calculates the sum of money, as well as values of all lands, held by the specified player.
     *
     * @param player
     * @return Text message of the event.
     */
    public int totalAssets(Player player) {
        int totalAsset = player.getBalance();

        for (Land l : lands) {
            if (l instanceof TradableLand && ((TradableLand) l).getLandlord().equals(player)) {
                totalAsset += ((TradableLand) l).getValue();
            }
        }

        return totalAsset;
    }

    /**
     * This method settles the references when a player can not afford a mandatory payment in any ways, and hence have
     * to quit from the game. It first returns all money and lands possessed by the player to the bank, then removes
     * the player from the player list.
     *
     * @return Text message of the event.
     * @throws Exception
     */
    public String bankrupt() throws Exception {
        for (Land l : lands) {
            if (l instanceof TradableLand && ((TradableLand) l).getLandlord().equals(playerInTurn)) {
                ((TradableLand) l).setLandlord(null);
            }
        }
        makeBankTransaction(playerInTurn, -playerInTurn.getBalance());
        players.remove(playerInTurn);
        return new String(playerInTurn + " has left the game. \n");
    }

    /**
     * This method represents the specified player going to jail. It alters the corresponding attribute so that the
     * player will skip its next 2 turns.
     */
    public void imprison() {
        //This represents when the currently active player is busted.
        if (playerInTurn.getBustedRounds() <= 0) {
            playerInTurn.setLocation(lands.get(10));
            playerInTurn.setBustedRounds(2);
        }
    }

    /**
     * A process-oriented method that encapsulates all events when a player moves in the game.
     * It does NOT represent a complete turn of a player, as feedback of decisions are required from UI.
     * (e.g. The player needs to throw dice at the beginning of its turn, and may buy or upgrade a property by the end
     * of it. )
     *
     * @param steps
     * @return Settlement type after the movement.
     * @throws Exception
     */
    public SettlementType move(int steps) throws Exception {
        int destinationIndex = lands.indexOf(playerInTurn.getLocation()) + steps;
        if (destinationIndex >= 40) {
            makeBankTransaction(playerInTurn, 200);
            playerInTurn.mayBuyProperties(true);
        }
        playerInTurn.setLocation(lands.get(destinationIndex % 40));

        Land currentLocation = playerInTurn.getLocation();
        if (currentLocation instanceof TradableLand) {
            if (((TradableLand) currentLocation).getLandlord().equals(playerInTurn)) {
                if (currentLocation instanceof Property) {
                    return SettlementType.upgradeProperty;
                }
            } else if (((TradableLand) currentLocation).getLandlord() == null) {
                if (playerInTurn.mayBuyProperties()) {
                    return SettlementType.buyLand;
                }
            } else if (currentLocation instanceof Utility) {
                return SettlementType.payUtilityRent;
            } else {
                return SettlementType.payRent;
            }
        } else {
            return ((NonTradableLand) currentLocation).getSettlementType();
        }

        return SettlementType.none;
    }

    /******************************************************************************************************************
     * Following are private methods.
     ******************************************************************************************************************/

    /**
     * This method looks up all lands and find tradable lands that belongs to the specified player.
     *
     * @param player The player whose lands are to look up.
     * @return List of tradable lands that belongs to the specified player.
     */
    private ArrayList<TradableLand> getTradableLands(Player player) {
        ArrayList<TradableLand> list = new ArrayList<>();

        for (Land l : lands) {
            if (l instanceof TradableLand && ((TradableLand) l).getLandlord().equals(player)) {
                list.add((TradableLand) l);
            }
        }

        return list;
    }

    /**
     * This method represents player paying to, or receiving from bank. The method should be called in all sensible
     * context to ensure the amount is paid by one side as well as received by the other.
     *
     * @param player
     * @param amount Net amount received by the player, where negative stands for player's payment, vice versa.
     *               Value 0 is illegal and will throw an exception.
     * @return Text message of the event.
     * @throws Exception
     */
    private String makeBankTransaction(Player player, int amount) throws Exception {
        String msg = "";

        if (amount > 0) {
            msg = "The bank pays " + player.getName() + " ￡" + amount + '\n';
        } else if (amount < 0) {
            if (player.getBalance() + amount < 0) {
                throw new Exception("The payer " + player.getName() + " can not afford the payment. ");
            }
            msg = player.getName() + " pays the bank " + " ￡" + amount + '\n';
        } else {
            throw new Exception("The transaction amount must not be 0. ");
        }

        player.makeTransaction(amount);
        bankAsset -= amount;

        return msg;
    }

    /**
     * This method represents player paying to, or receiving from another player. The method should be called in all
     * sensible context to ensure the amount is paid by one side as well as received by the other.
     *
     * @param payer  The player paying money.
     * @param payee  The player receiving money.
     * @param amount Amount paid from payer to payee. Non-positive values are illegal and will throw an exception.
     * @return Text message of the event.
     * @throws Exception
     */
    private String makePlayerTransaction(Player payer, Player payee, int amount) throws Exception {
        String msg = "";

        if (amount > 0) {
            if (payer.getBalance() < amount) {
                throw new Exception("The payer " + payer.getName() + " can not afford the payment. ");
            } else {
                msg = payer.getName() + " pays " + payee.getName() + " ￡" + amount;
            }
        } else {
            throw new Exception("The transaction amount must not be greater than 0. ");
        }

        payer.makeTransaction(-amount);
        payee.makeTransaction(amount);

        return msg;
    }

    /**
     * This method updates the upgradability state of all properties which are possessed by the same landlord, and are
     * of the same color group as the specified property. The properties can not be upgraded unless all ones of the
     * same color group are possessed by the same landlord.
     *
     * @param property
     */
    private void updatePropertyColorGroup(Property property) {
        //call each time when there is any change to any land
        ArrayList<Property> colorGroup = new ArrayList();

        for (Land l : lands) {
            if (l instanceof Property && ((Property) l).getColorGroup() == property.getColorGroup()) {
                colorGroup.add((Property) l);
            }
        }

        boolean upgradable = true;
        for (Property p : colorGroup) {
            if (!p.getLandlord().equals(property.getLandlord())) {
                upgradable = false;
                break;
            }
        }

        if (upgradable) {
            colorGroup.forEach(c -> {
                c.setUpgradable(true);
            });
        } else {
            colorGroup.forEach(c -> {
                c.setUpgradable(false);
            });
        }

    }

    /**
     * This method updates the rent of all stations which are possessed by the same landlord as the specified station.
     * The rents alters according to the number of stations possessed by the same landlord.
     *
     * @param station
     */
    private void updateStationRent(Station station) {
        ArrayList<Station> stations = new ArrayList<>();

        for (Land l : lands) {
            if (l instanceof Station && ((Station) l).getLandlord().equals(station.getLandlord())) {
                stations.add((Station) l);
            }
        }

        if (stations.size() > 0) {
            stations.forEach(c -> {
                c.setRent((int) (25 * Math.pow(2, stations.size() - 1)));
            });
        }
    }

    /**
     * This method updates the rent of all utilities which are possessed by the same landlord as the specified station.
     * The rents alters according to the number of utilities possessed by the same landlord.
     *
     * @param utility
     */
    private void updateUtilityRent(Utility utility) {
        ArrayList<Utility> utilities = new ArrayList<>();

        for (Land l : lands) {
            if (l instanceof Utility && ((Utility) l).getLandlord().equals(utility.getLandlord())) {
                utilities.add((Utility) l);
            }
        }

        if (utilities.size() == 1) {
            utilities.forEach(c -> {
                c.setRent(4);
            });
        } else if (utilities.size() == 2) {
            utilities.forEach(c -> {
                c.setRent(10);
            });
        }
    }
}
