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
public class StaticArray17<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray17(List<T> values) {
        super(17, values);
    }

    @Deprecated
    @SafeVarargs
    public StaticArray17(T... values) {
        super(17, values);
    }

    public StaticArray17(Class<T> type, List<T> values) {
        super(type, 17, values);
    }

    @SafeVarargs
    public StaticArray17(Class<T> type, T... values) {
        super(type, 17, values);
    }
}
