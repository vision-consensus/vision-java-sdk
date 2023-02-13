package org.vision.visionjsdk.abi.datatypes.generated;

import java.math.BigInteger;
import org.vision.visionjsdk.abi.datatypes.Uint;

/**
 * Auto generated code.
 * <p>Do not modifiy!
 * <p>Please use org.vision.visionjsdk.codegen.AbiTypesGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 */
public class Uint216 extends Uint {
    public static final Uint216 DEFAULT = new Uint216(BigInteger.ZERO);

    public Uint216(BigInteger value) {
        super(216, value);
    }

    public Uint216(long value) {
        this(BigInteger.valueOf(value));
    }
}
