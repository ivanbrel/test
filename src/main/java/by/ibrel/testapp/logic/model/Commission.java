package by.ibrel.testapp.logic.model;

import by.ibrel.testapp.logic.bean.Brand;
import by.ibrel.testapp.logic.bean.Currency;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

/**
 * @author ibrel
 * @version 1.0
 * @email ibrel7n@gmail.com
 * @datecreate (26.12.2016)
 * @datechange (26.12.2016)
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@XmlRootElement(name = "commission")
@XmlAccessorType(XmlAccessType.FIELD)
public class Commission {

    @XmlElement(name = "brand")
    private Brand brand;
    @XmlElement(name = "currency")
    private Currency currency;
    @XmlElement(name = "values")
    private BigDecimal value;
    Boolean childrenAllowed;

    public Commission() {
    }

    public Commission(Brand brand, Currency currency, BigDecimal value) {
        this.brand = brand;
        this.currency = currency;
        this.value = value;
    }

}
