package org.vision.visionjsdk.abi.datatypes.generated;

import java.math.BigInteger;
import org.vision.visionjsdk.abi.datatypes.Uint;

/**
 * Auto generated code.
 * <p>Do not modifiy!
 * <p>Please use org.vision.visionjsdk.codegen.AbiTypesGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 */
public class Uint112 extends Uint {
    public static final Uint112 DEFAULT = new Uint112(BigInteger.ZERO);

    public Uint112(BigInteger value) {
        super(112, value);
    }

    public Uint112(long value) {
        this(BigInteger.valueOf(value));
    }
}
