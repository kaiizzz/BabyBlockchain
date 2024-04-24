import java.util.ArrayList;
import java.util.List;
import java.time.*;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {
    public int index;
    public OffsetDateTime timestamp;
    public ArrayList<String> data = new ArrayList<String>();
    public String previousHash;
    public String hash;

    public Block(int index, OffsetDateTime timestamp, ArrayList<String> data, String previousHash) {
        this.index = index;
        this.timestamp = timestamp;
        this.data = data;
        this.previousHash = previousHash;
        this.hash = hashBlock();
    }

    public String hashBlock() {
        // obtain the block data
        String blockData = index + timestamp.toString() + data + previousHash;
        try {
            // create a SHA-256 hash
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(blockData.getBytes(StandardCharsets.UTF_8));

            // convert the byte array to a hexadecimal string
            BigInteger number = new BigInteger(1, hash);
            StringBuilder hextString = new StringBuilder(number.toString(16));

            // pad with leading zeros
            while (hextString.length() < 64) {
                hextString.insert(0, '0');
            }
            return hextString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Block genesisBlock() {
        ArrayList<String> data = new ArrayList<>();
        data.add("Genesis Block");
        return new Block(0, OffsetDateTime.now(), data, "0");
    }

    public static Block newBlock(Block previousBlock) {
        return new Block(previousBlock.index + 1, OffsetDateTime.now(), new ArrayList<>(),
                previousBlock.hash);
    }

}
