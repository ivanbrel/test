package by.ibrel.testapp.logic.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author ibrel
 * @version 1.0
 * @email ibrel7n@gmail.com
 * @datecreate (27.12.2016)
 * @datechange (27.12.2016)
 */
@Getter @Setter
@ToString
@EqualsAndHashCode
public class Holder implements Serializable{

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private Card card;

    public Holder() {
    }

    public Holder(String name, Card card) {
        this.name = name;
        this.card = card;
    }

    public Holder(int id, String name, Card card) {
        this(name,card);
        this.id = id;
    }
}
