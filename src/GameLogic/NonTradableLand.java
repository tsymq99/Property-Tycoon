package GameLogic;

/**
 * This class represents a non-tradable land. It features performing an settlementType at arrival of a player, which is settled
 * in the PrpertyTycoonBoard class instead.
 *
 * @author Kingway
 */
public class NonTradableLand extends Land {
    private PropertyTycoonBoard.SettlementType settlementType;

    public NonTradableLand(String name, String description, PropertyTycoonBoard.SettlementType settlementType) throws Exception {
        super(name, description);
        if (settlementType == PropertyTycoonBoard.SettlementType.none
                || settlementType == PropertyTycoonBoard.SettlementType.buyLand
                || settlementType == PropertyTycoonBoard.SettlementType.upgradeProperty
                || settlementType == PropertyTycoonBoard.SettlementType.payRent
                || settlementType == PropertyTycoonBoard.SettlementType.payUtilityRent) {
            throw  new Exception("Wrong settlement type given. ");
        }
            this.settlementType = settlementType;
    }

    public PropertyTycoonBoard.SettlementType getSettlementType() {
        return settlementType;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
