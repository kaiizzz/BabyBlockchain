public class CoinProducer {
    private static CoinProducer instance;

    private CoinProducer() {
        // Private constructor to prevent instantiation
    }

    public static CoinProducer getInstance() {
        if (instance == null) {
            synchronized (CoinProducer.class) {
                if (instance == null) {
                    instance = new CoinProducer();
                }
            }
        }
        return instance;
    }

    public Coin produceCoin() {
        // Add code here to produce a Coin object
        return new Coin(0, null);
    }
}
