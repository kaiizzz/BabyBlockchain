import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<Block> blockchain = new ArrayList<>();

    public static void main(String[] args) {
        Person alice = new Person("Alice");
        Person bob = new Person("Bob");
        CoinProducer coinProducer = CoinProducer.getInstance();
        ArrayList<Coin> coins = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            coins.add(coinProducer.produceCoin());
        }
        blockchain.add(Block.genesisBlock());
        blockchain.add(Block.newBlock(blockchain.get(blockchain.size() - 1)));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a name: ");
        String name = scanner.nextLine();
        Person person = new Person(name);
        while (true) {

            System.out.println("what would you like to do? [BUY/SELL/EXIT] ");
            String action = scanner.nextLine();
            if (action.equals("BUY")) {
                if (coins.size() == 0) {
                    System.out.println("No more coins to buy");
                    continue;
                } else {
                    person.BuyCoin(coins.get(0), blockchain.get(blockchain.size() - 1));
                    coins.trimToSize();
                }
            } else if (action.equals("SELL")) {
                if (person.getWallet().size() == 0) {
                    System.out.println("You have no coins to sell");
                    continue;
                } else {
                    person.sellCoin(blockchain.get(blockchain.size() - 1));
                    coins.trimToSize();
                }
            } else if (action.equals("EXIT")) {
                break;
            }
        }
        scanner.close();

        for (Block block : blockchain) {
            System.out.println("Block: " + block.index);
            System.out.println("Timestamp: " + block.timestamp);
            System.out.println("Data: " + block.data);
            System.out.println("Previous Hash: " + block.previousHash);
            System.out.println("Hash: " + block.hash);
            System.out.println();
        }

    }
}
