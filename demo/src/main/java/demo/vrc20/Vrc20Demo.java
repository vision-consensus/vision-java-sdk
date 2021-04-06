package demo.vrc20;

import org.vision.visionjsdk.client.VisionClient;
import org.vision.visionjsdk.client.contract.Contract;
import org.vision.visionjsdk.client.contract.Vrc20Contract;

public class Vrc20Demo {

    VisionClient c = VisionClient.ofVtest("88849fe172d869fa15a560ae1462b0bbc43e49c31ca8431d563b15466472d383");
    String contractAddress = "4648dd91ff64c11eba559e9f49c5094c97e43c6ab9" ;
    String ownerAddress = "VNTxQP1qojBwiMkVfjfwcZ9vj7LF3DRQPn" ;
    String reciverAddress = "VBZsQtsKdzFU5fLyHWeduQLHAw6HvSDd7i" ;
    public void getName() {
        Contract cntr = c.getContract(contractAddress);
        Vrc20Contract token = new Vrc20Contract(cntr, ownerAddress, c);
        try {
            System.out.println("Name: " + token.name());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void getSymbol() {
        Contract cntr = c.getContract(contractAddress);
        Vrc20Contract token = new Vrc20Contract(cntr, ownerAddress, c);
        try {
            System.out.println("Symbol: " + token.symbol());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void getDecimals() {
        Contract cntr = c.getContract(contractAddress);
        Vrc20Contract token = new Vrc20Contract(cntr, ownerAddress, c);
        try {
            System.out.println("Decimals: " + token.decimals());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void getTotalSupply() {
        Contract cntr = c.getContract(contractAddress);
        Vrc20Contract token = new Vrc20Contract(cntr, ownerAddress, c);
        try {
            System.out.println("Total Supply: " + token.totalSupply());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void getBalanceOf() {
        Contract cntr = c.getContract(contractAddress);
        Vrc20Contract token = new Vrc20Contract(cntr, ownerAddress, c);
        try {
            System.out.println("Balance: " + token.balanceOf(ownerAddress));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void transfer() {
        Contract cntr = c.getContract(contractAddress);
        Vrc20Contract token = new Vrc20Contract(cntr, ownerAddress, c);
        try {
            // destnation address, amount, memo, feelimit
            System.out.println(token.transfer(reciverAddress, 10L, "go!", 100000000L));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void transferFrom() {
        Contract cntr = c.getContract(contractAddress);
        Vrc20Contract token = new Vrc20Contract(cntr, ownerAddress, c);
        try {
            // destnation address, amount, memo, feelimit
            System.out.println(token.transferFrom(ownerAddress, reciverAddress, 10L, "go!", 100000000L));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void approve() {
        Contract cntr = c.getContract(contractAddress);
        Vrc20Contract token = new Vrc20Contract(cntr, ownerAddress, c);
        try {
            // destnation address, amount, memo, feelimit
            System.out.println(token.approve(reciverAddress, 10L, "go!", 100000000L));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAllowance() {
        Contract cntr = c.getContract(contractAddress);
        Vrc20Contract token = new Vrc20Contract(cntr, ownerAddress, c);
        try {
            System.out.println("Allowance: " + token.allowance(ownerAddress, reciverAddress));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}