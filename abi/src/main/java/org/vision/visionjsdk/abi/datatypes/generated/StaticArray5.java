package org.vision.visionjsdk.abi.datatypes.generated;

import java.util.List;
import org.vision.visionjsdk.abi.datatypes.StaticArray;
import org.vision.visionjsdk.abi.datatypes.Type;

/**
 * Auto generated code.
 * <p>Do not modifiy!
 * <p>Please use org.vision.visionjsdk.codegen.AbiTypesGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 */
public class StaticArray5<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray5(List<T> values) {
        super(5, values);
    }

    @Deprecated
    @SafeVarargs
    public StaticArray5(T... values) {
        super(5, values);
    }

    public StaticArray5(Class<T> type, List<T> values) {
        super(type, 5, values);
    }

    @SafeVarargs
    public StaticArray5(Class<T> type, T... values) {
        super(type, 5, values);
    }
}
