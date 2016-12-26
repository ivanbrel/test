package by.ibrel.testapp.bean;

import lombok.Getter;

/**
 * @author ibrel
 * @version 1.0
 * @email ibrel7n@gmail.com
 * @datecreate (26.12.2016)
 * @datechange (26.12.2016)
 */
@Getter
public enum Currency {
    RUB("rub"),
    BYN("byn"),
    USD("usd"),
    EUR("eur");

    private String name;

    Currency(String name) {
        this.name = name;
    }
}
