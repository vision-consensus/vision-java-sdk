package org.vision.visionjsdk.client.Transaction.visioncore;

/**
 * Protobuf enum {@code protocol.Transaction.Contract.ContractType}
 */
public enum ContractType
        implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>AccountCreateContract = 0;</code>
     */
    AccountCreateContract(0),
    /**
     * <code>TransferContract = 1;</code>
     */
    TransferContract(1),
    /**
     * <code>TransferAssetContract = 2;</code>
     */
    TransferAssetContract(2),
    /**
     * <code>VoteAssetContract = 3;</code>
     */
    VoteAssetContract(3),
    /**
     * <code>VoteWitnessContract = 4;</code>
     */
    VoteWitnessContract(4),
    /**
     * <code>WitnessCreateContract = 5;</code>
     */
    WitnessCreateContract(5),
    /**
     * <code>AssetIssueContract = 6;</code>
     */
    AssetIssueContract(6),
    /**
     * <code>WitnessUpdateContract = 8;</code>
     */
    WitnessUpdateContract(8),
    /**
     * <code>ParticipateAssetIssueContract = 9;</code>
     */
    ParticipateAssetIssueContract(9),
    /**
     * <code>AccountUpdateContract = 10;</code>
     */
    AccountUpdateContract(10),
    /**
     * <code>FreezeBalanceContract = 11;</code>
     */
    FreezeBalanceContract(11),
    /**
     * <code>UnfreezeBalanceContract = 12;</code>
     */
    UnfreezeBalanceContract(12),
    /**
     * <code>WithdrawBalanceContract = 13;</code>
     */
    WithdrawBalanceContract(13),
    /**
     * <code>UnfreezeAssetContract = 14;</code>
     */
    UnfreezeAssetContract(14),
    /**
     * <code>UpdateAssetContract = 15;</code>
     */
    UpdateAssetContract(15),
    /**
     * <code>ProposalCreateContract = 16;</code>
     */
    ProposalCreateContract(16),
    /**
     * <code>ProposalApproveContract = 17;</code>
     */
    ProposalApproveContract(17),
    /**
     * <code>ProposalDeleteContract = 18;</code>
     */
    ProposalDeleteContract(18),
    /**
     * <code>SetAccountIdContract = 19;</code>
     */
    SetAccountIdContract(19),
    /**
     * <code>CustomContract = 20;</code>
     */
    CustomContract(20),
    /**
     * <code>CreateSmartContract = 30;</code>
     */
    CreateSmartContract(30),
    /**
     * <code>TriggerSmartContract = 31;</code>
     */
    TriggerSmartContract(31),
    /**
     * <code>GetContract = 32;</code>
     */
    GetContract(32),
    /**
     * <code>UpdateSettingContract = 33;</code>
     */
    UpdateSettingContract(33),
    /**
     * <code>ExchangeCreateContract = 41;</code>
     */
    ExchangeCreateContract(41),
    /**
     * <code>ExchangeInjectContract = 42;</code>
     */
    ExchangeInjectContract(42),
    /**
     * <code>ExchangeWithdrawContract = 43;</code>
     */
    ExchangeWithdrawContract(43),
    /**
     * <code>ExchangeTransactionContract = 44;</code>
     */
    ExchangeTransactionContract(44),
    /**
     * <code>UpdateEntropyLimitContract = 45;</code>
     */
    UpdateEntropyLimitContract(45),
    /**
     * <code>AccountPermissionUpdateContract = 46;</code>
     */
    AccountPermissionUpdateContract(46),
    /**
     * <code>ClearABIContract = 48;</code>
     */
    ClearABIContract(48),
    /**
     * <code>UpdateBrokerageContract = 49;</code>
     */
    UpdateBrokerageContract(49),
    /**
     * <code>ShieldedTransferContract = 51;</code>
     */
    ShieldedTransferContract(51),
    /**
     * <code>MarketSellAssetContract = 52;</code>
     */
    MarketSellAssetContract(52),
    /**
     * <code>MarketCancelOrderContract = 53;</code>
     */
    MarketCancelOrderContract(53),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>AccountCreateContract = 0;</code>
     */
    public static final int AccountCreateContract_VALUE = 0;
    /**
     * <code>TransferContract = 1;</code>
     */
    public static final int TransferContract_VALUE = 1;
    /**
     * <code>TransferAssetContract = 2;</code>
     */
    public static final int TransferAssetContract_VALUE = 2;
    /**
     * <code>VoteAssetContract = 3;</code>
     */
    public static final int VoteAssetContract_VALUE = 3;
    /**
     * <code>VoteWitnessContract = 4;</code>
     */
    public static final int VoteWitnessContract_VALUE = 4;
    /**
     * <code>WitnessCreateContract = 5;</code>
     */
    public static final int WitnessCreateContract_VALUE = 5;
    /**
     * <code>AssetIssueContract = 6;</code>
     */
    public static final int AssetIssueContract_VALUE = 6;
    /**
     * <code>WitnessUpdateContract = 8;</code>
     */
    public static final int WitnessUpdateContract_VALUE = 8;
    /**
     * <code>ParticipateAssetIssueContract = 9;</code>
     */
    public static final int ParticipateAssetIssueContract_VALUE = 9;
    /**
     * <code>AccountUpdateContract = 10;</code>
     */
    public static final int AccountUpdateContract_VALUE = 10;
    /**
     * <code>FreezeBalanceContract = 11;</code>
     */
    public static final int FreezeBalanceContract_VALUE = 11;
    /**
     * <code>UnfreezeBalanceContract = 12;</code>
     */
    public static final int UnfreezeBalanceContract_VALUE = 12;
    /**
     * <code>WithdrawBalanceContract = 13;</code>
     */
    public static final int WithdrawBalanceContract_VALUE = 13;
    /**
     * <code>UnfreezeAssetContract = 14;</code>
     */
    public static final int UnfreezeAssetContract_VALUE = 14;
    /**
     * <code>UpdateAssetContract = 15;</code>
     */
    public static final int UpdateAssetContract_VALUE = 15;
    /**
     * <code>ProposalCreateContract = 16;</code>
     */
    public static final int ProposalCreateContract_VALUE = 16;
    /**
     * <code>ProposalApproveContract = 17;</code>
     */
    public static final int ProposalApproveContract_VALUE = 17;
    /**
     * <code>ProposalDeleteContract = 18;</code>
     */
    public static final int ProposalDeleteContract_VALUE = 18;
    /**
     * <code>SetAccountIdContract = 19;</code>
     */
    public static final int SetAccountIdContract_VALUE = 19;
    /**
     * <code>CustomContract = 20;</code>
     */
    public static final int CustomContract_VALUE = 20;
    /**
     * <code>CreateSmartContract = 30;</code>
     */
    public static final int CreateSmartContract_VALUE = 30;
    /**
     * <code>TriggerSmartContract = 31;</code>
     */
    public static final int TriggerSmartContract_VALUE = 31;
    /**
     * <code>GetContract = 32;</code>
     */
    public static final int GetContract_VALUE = 32;
    /**
     * <code>UpdateSettingContract = 33;</code>
     */
    public static final int UpdateSettingContract_VALUE = 33;
    /**
     * <code>ExchangeCreateContract = 41;</code>
     */
    public static final int ExchangeCreateContract_VALUE = 41;
    /**
     * <code>ExchangeInjectContract = 42;</code>
     */
    public static final int ExchangeInjectContract_VALUE = 42;
    /**
     * <code>ExchangeWithdrawContract = 43;</code>
     */
    public static final int ExchangeWithdrawContract_VALUE = 43;
    /**
     * <code>ExchangeTransactionContract = 44;</code>
     */
    public static final int ExchangeTransactionContract_VALUE = 44;
    /**
     * <code>UpdateEntropyLimitContract = 45;</code>
     */
    public static final int UpdateEntropyLimitContract_VALUE = 45;
    /**
     * <code>AccountPermissionUpdateContract = 46;</code>
     */
    public static final int AccountPermissionUpdateContract_VALUE = 46;
    /**
     * <code>ClearABIContract = 48;</code>
     */
    public static final int ClearABIContract_VALUE = 48;
    /**
     * <code>UpdateBrokerageContract = 49;</code>
     */
    public static final int UpdateBrokerageContract_VALUE = 49;
    /**
     * <code>ShieldedTransferContract = 51;</code>
     */
    public static final int ShieldedTransferContract_VALUE = 51;
    /**
     * <code>MarketSellAssetContract = 52;</code>
     */
    public static final int MarketSellAssetContract_VALUE = 52;
    /**
     * <code>MarketCancelOrderContract = 53;</code>
     */
    public static final int MarketCancelOrderContract_VALUE = 53;


    public final int getNumber() {
        if (this == UNRECOGNIZED) {
            throw new java.lang.IllegalArgumentException(
                    "Can't get the number of an unknown enum value.");
        }
        return value;
    }

    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static ContractType valueOf(int value) {
        return forNumber(value);
    }

    public static ContractType forNumber(int value) {
        switch (value) {
            case 0: return AccountCreateContract;
            case 1: return TransferContract;
            case 2: return TransferAssetContract;
            case 3: return VoteAssetContract;
            case 4: return VoteWitnessContract;
            case 5: return WitnessCreateContract;
            case 6: return AssetIssueContract;
            case 8: return WitnessUpdateContract;
            case 9: return ParticipateAssetIssueContract;
            case 10: return AccountUpdateContract;
            case 11: return FreezeBalanceContract;
            case 12: return UnfreezeBalanceContract;
            case 13: return WithdrawBalanceContract;
            case 14: return UnfreezeAssetContract;
            case 15: return UpdateAssetContract;
            case 16: return ProposalCreateContract;
            case 17: return ProposalApproveContract;
            case 18: return ProposalDeleteContract;
            case 19: return SetAccountIdContract;
            case 20: return CustomContract;
            case 30: return CreateSmartContract;
            case 31: return TriggerSmartContract;
            case 32: return GetContract;
            case 33: return UpdateSettingContract;
            case 41: return ExchangeCreateContract;
            case 42: return ExchangeInjectContract;
            case 43: return ExchangeWithdrawContract;
            case 44: return ExchangeTransactionContract;
            case 45: return UpdateEntropyLimitContract;
            case 46: return AccountPermissionUpdateContract;
            case 48: return ClearABIContract;
            case 49: return UpdateBrokerageContract;
            case 51: return ShieldedTransferContract;
            case 52: return MarketSellAssetContract;
            case 53: return MarketCancelOrderContract;
            default: return null;
        }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<ContractType>
    internalGetValueMap() {
        return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
            ContractType> internalValueMap =
            new com.google.protobuf.Internal.EnumLiteMap<ContractType>() {
                public ContractType findValueByNumber(int number) {
                    return ContractType.forNumber(number);
                }
            };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
    getValueDescriptor() {
        return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
    getDescriptorForType() {
        return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
    getDescriptor() {
        return null;
        // return org.vision.protos.Protocol.Transaction.Contract.getDescriptor().getEnumTypes().get(0);
    }

    private static final ContractType[] VALUES = values();

    public static ContractType valueOf(
            com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
        if (desc.getType() != getDescriptor()) {
            throw new java.lang.IllegalArgumentException(
                    "EnumValueDescriptor is not for this type.");
        }
        if (desc.getIndex() == -1) {
            return UNRECOGNIZED;
        }
        return VALUES[desc.getIndex()];
    }

    private final int value;

    private ContractType(int value) {
        this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:protocol.Transaction.Contract.ContractType)
}

