package by.ibrel.testapp.logic.bean;

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
@Getter@Setter
@ToString
@EqualsAndHashCode
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Commission {

    @XmlAttribute(name = "id")
    private Long id;
    @XmlElement(name = "brand")
    private Brand brand;
    @XmlElement(name = "currency")
    private Currency currency;
    @XmlElement(name = "values")
    private BigDecimal value;

    public Commission() {
    }

    public Commission(Long id, Brand brand, Currency currency, BigDecimal value) {
        this.id = id;
        this.brand = brand;
        this.currency = currency;
        this.value = value;
    }
}
