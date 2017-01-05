package by.ibrel.testapp.logic.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ibrel
 * @version 1.0
 * @email ibrel7n@gmail.com
 * @datecreate (27.12.2016)
 * @datechange (27.12.2016)
 */

@EqualsAndHashCode
@Getter @Setter
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private Holder sender;
    private Holder recipient;
    private Commission commission;
    private BigDecimal transferAmount;

    public Transaction() {
    }

    public Transaction(Holder sender, Holder recipient, Commission commission, BigDecimal transferAmount){
        this.sender = sender;
        this.recipient = recipient;
        this.commission = commission;
        this.transferAmount = transferAmount;
    }

    public Transaction(int id, Holder sender, Holder recipient, Commission commission, BigDecimal transferAmount) {
        this(sender,recipient,commission,transferAmount);
        this.id = id;
    }

    public BigDecimal getOffero(){
        return commission.getValue().multiply(transferAmount).divide(new BigDecimal(100));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SENDER - ").append(sender.getName()).append("; ")
                .append("RECIPIENT - ").append(recipient.getName()).append("; ")
                .append("transfer amount - ").append(transferAmount).append("; ")
                .append("transaction cost - ").append(getOffero()).append(".");
        return builder.toString();
    }
}
