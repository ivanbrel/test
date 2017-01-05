package by.ibrel.testapp.logic.bean;

import by.ibrel.testapp.logic.model.Transaction;
import lombok.Getter;

/**
 * @author ibrel
 * @version 1.0
 * @email ibrel7n@gmail.com
 * @datecreate (30.12.2016)
 * @datechange (30.12.2016)
 */

@Getter
public class TransactionDto {

    private String senderName;
    private String senderCardId;
    private String senderCardValidity;
    private String recipientName;
    private String recipientCardId;
    private String recipientCardValidity;
    private String commissionBrand;
    private String commissionCurrency;
    private String commissionValue;
    private String transferAmount;
    private String transactionCost;

    public TransactionDto() {
    }

    public TransactionDto(Transaction transaction) {
        this.senderName = transaction.getSender().getName();
        this.senderCardId = String.valueOf(transaction.getSender().getCard().getNumberCard());
        this.senderCardValidity = transaction.getSender().getCard().getValidity().toString();
        this.recipientName = transaction.getRecipient().getName();
        this.recipientCardId = String.valueOf(transaction.getRecipient().getCard().getNumberCard());
        this.recipientCardValidity = transaction.getRecipient().getCard().getValidity().toString();
        this.commissionBrand = transaction.getCommission().getBrand().getName();
        this.commissionCurrency = transaction.getCommission().getCurrency().getName();
        this.commissionValue = transaction.getCommission().getValue().toString();
        this.transferAmount = transaction.getTransferAmount().toString();
        this.transactionCost = transaction.getOffero().toString();
    }
}
