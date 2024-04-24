import java.util.ArrayList;

public class Person {
    private String name;
    private ArrayList<Coin> wallet = new ArrayList<Coin>();

    public Person(String name) {
        this.name = name;
        this.wallet = new ArrayList<>();
    }

    public void addCoin(Coin coin) {
        wallet.add(coin);
    }

    public void removeCoin(Coin coin) {
        wallet.remove(coin);
    }

    public ArrayList<Coin> getWallet() {
        return wallet;
    }

    public Coin sellCoin(Block currentBlock) {
        Coin coin = wallet.get(0);
        wallet.remove(0);
        wallet.trimToSize();
        if (currentBlock.data.size() == 3) {  
            currentBlock = Block.newBlock(currentBlock);
            Main.blockchain.add(currentBlock);
            currentBlock.data.add("Block: " + currentBlock.index + " "  +name + " sold a coin");
            return coin;
        }
        currentBlock.data.add("Block: " + currentBlock.index + " "  +name + " sold a coin");
        return coin; 
    }

    public void BuyCoin(Coin coin, Block currentBlock) {
        wallet.add(coin);
        if (currentBlock.data.size() == 3) {
            currentBlock = Block.newBlock(currentBlock);
            Main.blockchain.add(currentBlock);
            currentBlock.data.add("Block: " + currentBlock.index + " " + name + " bought a coin");
            return;
        }
        currentBlock.data.add("Block: " + currentBlock.index + " " + name + " bought a coin");
    }
}
