package GUI;

import GameLogic.PropertyTycoonBoard;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private enum GameVersion {full, abridged}

    private PropertyTycoonBoard propertyTycoon;
    private GameVersion gameVersion;

    @Override
    public void start(Stage primaryStage) throws Exception {
        propertyTycoon = new PropertyTycoonBoard(3, 3);

        switch (gameVersion) {
            case full:
                while (propertyTycoon.getNumberOfPlayers() > 1) {
                    runTurn(false);
                }
                return;
            case abridged:
                while (propertyTycoon.getCurrentRound() <= 50) {
                    runTurn(false);
                }
                return;
        }


        /*Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();*/
    }

    private void runTurn(boolean isBonusTurn) throws Exception {
        if (!propertyTycoon.isSkippingTurn()) {
            int die1 = PropertyTycoonBoard.throwDice();
            int die2 = PropertyTycoonBoard.throwDice();

            switch (propertyTycoon.move(die1 + die2)) {
                case buyLand:
                    // Wait for player's response. If the player decides to buy the tradable land, call below:

                    // propertyTycoon.buyLand();

                    return;
                case upgradeProperty:
                    // Wait for player's response. If the player decides to upgrade the property, call below:

                    // propertyTycoon.upgradeProperty();

                    return;
                case payRent:
                    // First let the player pay the rent with its balance.
                    // If the balance can not afford it, keep asking the player to sell lands to raise money, until
                    // there is sufficient fund.
                    // If the player refuses to sell lands, automatically sell some to get the game going.
                    // If the player simply can not afford the payment, bankrupt it.

                    // propertyTycoon.payRent();
                    // propertyTycoon.sellLands(*);
                    // propertyTycoon.sellLands();
                    // propertyTycoon.bankrupt();

                    return;
                case payUtilityRent:
                    // First throw a die and let the player pay the rent with its balance according to the dice number.
                    // If the balance can not afford it, keep asking the player to sell lands to raise money, until
                    // there is sufficient fund.
                    // If the player refuses to sell lands, automatically sell some to get the game going.
                    // If the player simply can not afford the payment, bankrupt it.

                    // propertyTycoon.payUtilityRent(*);
                    // propertyTycoon.sellLands(*);
                    // propertyTycoon.sellLands();
                    // propertyTycoon.bankrupt();

                    return;
                case :

                    return;
            }

            if (die1 == die2) {
                if (isBonusTurn) {
                    propertyTycoon.imprison();
                } else {
                    runTurn(true);
                }
            } else {
                propertyTycoon.nextPlayer();
            }

        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
