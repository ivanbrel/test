package by.ibrel.testapp.logic.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;


/**
 * @author ibrel
 * @version 1.0
 * @email ibrel7n@gmail.com
 * @datecreate (27.12.2016)
 * @datechange (27.12.2016)
 */
@ToString
@EqualsAndHashCode
@Getter @Setter
public class Card implements Serializable{

    private static final long serialVersionUID = 1L;

    private int id;
    private Integer numberCard;
    private Date validity;

    public Card() {
    }

    public Card(Integer numberCard, Date validity) {
        this.numberCard = numberCard;
        this.validity = validity;
    }

    public Card(int id, Integer numberCard, Date validity) {
        this(numberCard, validity);
        this.id = id;
    }
}
