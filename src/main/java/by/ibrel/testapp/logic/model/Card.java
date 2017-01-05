package by.ibrel.testapp.logic.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


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
    private int numberCard;
    private Date validity;

    public Card() {
    }

    public Card(int numberCard, String validity) {
        this.numberCard = numberCard;
        this.validity = convertStringToDate(validity);
    }

    public Card(int id, int numberCard, String validity) {
        this(numberCard, validity);
        this.id = id;
    }

    private Date convertStringToDate(String date){

        DateFormat df = new SimpleDateFormat("yyyy-mm-DD");

        Date startDate = null;

        try {
            startDate = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return startDate;
    }
}
