package roll.the.block.model.block;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.Hashing;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import roll.the.block.model.transaction.Transaction;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * This class represents a Block in the Blockchain. It is the most basic building
 * block of the Blockchain.
 *
 * Hash should be calculated on the ordered list of attributes and hence keeping
 * them sorted to ensure that hashing is consistent.
 *
 * @author cristianmarint
 * @project RollTheBlock
 * @since v1.0.0 - feb. 2022
 */
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Block {
    private Long index;

    private Long timestamp;

    private List<Transaction> transactions;

    private Long proof;

    private String previousHash;

    public static final Long GENESIS_BLOCK_PROOF = 100L;
    public static final String GENESIS_BLOCK_PREV_HASH = "1";

    public String hash(ObjectMapper mapper) throws JsonProcessingException {
        String json = mapper.writeValueAsString(this);
        return Hashing.sha256().hashString(json, StandardCharsets.UTF_8).toString();
    }
}