package by.ibrel.testapp.logic.model;

import by.ibrel.testapp.logic.bean.Commission;
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

    public Transaction(int id, Holder sender, Holder recipient, Commission commission, BigDecimal transferAmount) {
        this.id = id;
        this.sender = sender;
        this.recipient = recipient;
        this.commission = commission;
        this.transferAmount = transferAmount;
    }

    public BigDecimal getOffero(){
        return commission.getValue().multiply(transferAmount).divide(new BigDecimal(100));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SENDER - ").append(sender.getName()).append(";\n")
                .append("RECIPIENT - ").append(recipient.getName()).append(";\n")
                .append("transfer amount - ").append(transferAmount).append(";\n")
                .append("transaction cost - ").append(getOffero()).append(".");
        return builder.toString();
    }
}
