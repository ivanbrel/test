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
public enum Brand {

    VISA("visa"),
    MASTERCARD("mastercard"),
    AMERICAN_EXPRESS("american express");

    private String name;

    Brand(String name) {
        this.name = name;
    }
}
