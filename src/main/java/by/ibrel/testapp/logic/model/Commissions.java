package by.ibrel.testapp.logic.model;

import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ibrel
 * @version 1.0
 * @email ibrel7n@gmail.com
 * @datecreate (03.01.2017)
 * @datechange (03.01.2017)
 */
@XmlRootElement(namespace = "by.ibrel.testapp.logic.midel")
public class Commissions {

    @XmlElementWrapper(name = "COMMISSIONS")
    @XmlElement( name = "COMMISSION" )
    @Getter
    private List<Commission> items;

    public void add(Commission commission){
        if (this.items == null){
            this.items = new ArrayList<>();
        }
        items.add(commission);
    }
}
