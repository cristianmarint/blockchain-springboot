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
     */
    public static String PROOF_OF_WORK = "0000";

    public static Long proofOfWork(Long lastProof) {

        Long proof = 0L;

        while (validProof(lastProof, proof) != true)
            proof += 1L;

        return proof;
    }

    public static boolean validProof(Long lastProof, Long proof) {

        String s = "" + lastProof + "" + proof;

        String sha256 = Hashing.sha256().hashString(s, StandardCharsets.UTF_8).toString();

        return sha256.endsWith(PROOF_OF_WORK);
    }
}

