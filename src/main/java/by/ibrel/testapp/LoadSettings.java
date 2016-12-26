package by.ibrel.testapp;

import by.ibrel.testapp.bean.Brand;
import by.ibrel.testapp.bean.Commission;
import by.ibrel.testapp.bean.Currency;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.math.BigDecimal;

/**
 * @author ibrel
 * @version 1.0
 * @email ibrel7n@gmail.com
 * @datecreate (26.12.2016)
 * @datechange (26.12.2016)
 */
public class LoadSettings {

    public void saveSettings(){
        Commission commission = new Commission((long)1, Brand.AMERICAN_EXPRESS, Currency.BYN, BigDecimal.TEN);

        try {

            File file = new File("file.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Commission.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(commission, file);
//            jaxbMarshaller.marshal(commission, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void getSettings(){
        try {

            File file = new File("file.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Commission.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Commission commission = (Commission) jaxbUnmarshaller.unmarshal(file);
//            System.out.println(commission);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
