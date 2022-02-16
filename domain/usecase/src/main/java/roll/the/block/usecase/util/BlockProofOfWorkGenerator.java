package roll.the.block.usecase.util;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

/**
 * BlockProofOfWorkGenerator class
 *
 * @author Praveendra Singh
 * @author cristianmarint
 * @project RollTheBlock
 * @since v1.0.0 - feb. 2022
 */
public class BlockProofOfWorkGenerator {

    public static final String PROOF_OF_WORK = "0000";

    private BlockProofOfWorkGenerator() {
        throw new AssertionError("Suppress default constructor for non-instantiability");
    }

    /**
     * Simple Proof of Work Algorithm:
     * <p>
     * - Find a number p' such that hash(pp') contains leading 4 zeroes, where p
     * is the previous p'
     * <p>
     * - p is the previous proof, and p' is the new proof
     * <p>
     * Find a number p that when hashed with the previous block’s solution a
     * hash with 4 leading 0s is produced.
     *
     * @param lastProof last Block
     * @return Long proof of work
     */
    public static Long proofOfWork(Long lastProof) {

        long proof = 0L;

        while (!validProof(lastProof, proof)) {
            proof += 1L;
        }

        return proof;
    }

    /**
     * Validates the Proof
     *
     * @param lastProof Previous Proof
     * @param proof     Current Proof
     * @return True if correct, False if not.
     */
    @SuppressWarnings("UnstableApiUsage")
    public static boolean validProof(Long lastProof, Long proof) {

        String s = "" + lastProof + "" + proof;

        String sha256 = Hashing.sha256().hashString(s, StandardCharsets.UTF_8).toString();

        return sha256.endsWith(PROOF_OF_WORK);
    }
}

