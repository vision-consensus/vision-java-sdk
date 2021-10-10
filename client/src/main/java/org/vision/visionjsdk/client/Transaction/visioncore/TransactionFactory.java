package org.vision.visionjsdk.client.Transaction.visioncore;

import com.google.protobuf.GeneratedMessageV3;
import org.vision.protos.contract.ShieldContract;
import org.vision.visionjsdk.proto.Contract;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class TransactionFactory {

  private static Map<ContractType, Class<? extends Actuator>> actuatorMap = new ConcurrentHashMap<>();
  private static Map<ContractType, Class<? extends GeneratedMessageV3>> contractMap = new ConcurrentHashMap<>();

  static {
    register(ContractType.CreateSmartContract, null, Contract.CreateSmartContract.class);
    register(ContractType.TriggerSmartContract, null, Contract.TriggerSmartContract.class);
    register(ContractType.VoteWitnessContract, null, Contract.VoteWitnessContract.class);

    register(ContractType.AccountPermissionUpdateContract, null, Contract.AccountPermissionUpdateContract.class);
    register(ContractType.AssetIssueContract, null, Contract.AssetIssueContract.class);
    register(ContractType.ClearABIContract, null, Contract.ClearABIContract.class);
    register(ContractType.AccountCreateContract, null, Contract.AccountCreateContract.class);
    register(ContractType.ExchangeCreateContract, null, Contract.ExchangeCreateContract.class);
    register(ContractType.ExchangeInjectContract, null, Contract.ExchangeInjectContract.class);
    register(ContractType.ExchangeTransactionContract, null, Contract.ExchangeTransactionContract.class);
    register(ContractType.ExchangeWithdrawContract, null, Contract.ExchangeWithdrawContract.class);
    register(ContractType.FreezeBalanceContract, null, Contract.FreezeBalanceContract.class);
    register(ContractType.MarketCancelOrderContract, null, Contract.MarketCancelOrderContract.class);
    register(ContractType.MarketSellAssetContract, null, Contract.MarketSellAssetContract.class);
    register(ContractType.ParticipateAssetIssueContract, null, Contract.ParticipateAssetIssueContract.class);
    register(ContractType.ProposalApproveContract, null, Contract.ProposalApproveContract.class);
    register(ContractType.ProposalCreateContract, null, Contract.ProposalCreateContract.class);
    register(ContractType.ProposalDeleteContract, null, Contract.ProposalDeleteContract.class);
    register(ContractType.SetAccountIdContract, null, Contract.SetAccountIdContract.class);
    register(ContractType.ShieldedTransferContract, null, ShieldContract.ShieldedTransferContract.class);
    register(ContractType.TransferContract, null, Contract.TransferContract.class);
    register(ContractType.TransferAssetContract, null, Contract.TransferAssetContract.class);
    register(ContractType.UnfreezeAssetContract, null, Contract.UnfreezeAssetContract.class);
    register(ContractType.UnfreezeBalanceContract, null, Contract.UnfreezeBalanceContract.class);
    register(ContractType.AccountUpdateContract, null, Contract.AccountUpdateContract.class);
    register(ContractType.UpdateAssetContract, null, Contract.UpdateAssetContract.class);
    register(ContractType.UpdateBrokerageContract, null, Contract.UpdateBrokerageContract.class);
    register(ContractType.UpdateEntropyLimitContract, null, Contract.UpdateEntropyLimitContract.class);
    register(ContractType.UpdateSettingContract, null, Contract.UpdateSettingContract.class);
    register(ContractType.WithdrawBalanceContract, null, Contract.WithdrawBalanceContract.class);
    register(ContractType.WitnessCreateContract, null, Contract.WitnessCreateContract.class);
    register(ContractType.WitnessUpdateContract, null, Contract.WitnessUpdateContract.class);
  }

  public static void register(ContractType type, Class<? extends Actuator> actuatorClass,
      Class<? extends GeneratedMessageV3> clazz) {
    Set<String> actuatorSet = CommonParameter.getInstance().getActuatorSet();
    if (actuatorClass != null && !actuatorSet.isEmpty() && !actuatorSet
        .contains(actuatorClass.getSimpleName())) {
      return;
    }

    if (type != null && actuatorClass != null) {
      actuatorMap.put(type, actuatorClass);
    }
    if (type != null && clazz != null) {
      contractMap.put(type, clazz);
    }
  }

  public static Class<? extends Actuator> getActuator(ContractType type) {
    return actuatorMap.get(type);
  }

  public static Class<? extends GeneratedMessageV3> getContract(ContractType type) {
    return contractMap.get(type);
  }

  public static Map<ContractType, Class<? extends Actuator>> getActuatorMap() {
    return actuatorMap;
  }

  public static Map<ContractType, Class<? extends GeneratedMessageV3>> getContractMap() {
    return contractMap;
  }
}
