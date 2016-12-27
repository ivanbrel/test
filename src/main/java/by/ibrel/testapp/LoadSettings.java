package by.ibrel.testapp;

import by.ibrel.testapp.logic.bean.Brand;
import by.ibrel.testapp.logic.bean.Commission;
import by.ibrel.testapp.logic.bean.Currency;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

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
/*eager initialization*/
public class LoadSettings {

    private final Logger logger = (Logger) LoggerFactory.getLogger(getClass());

    private static final LoadSettings instance = new LoadSettings();

    private LoadSettings() {
    }

    public static LoadSettings getInstance(){
        return instance;
    }

    public void saveSettings(){

        logger.debug("create settings and add to .xml");

        Commission commission = new Commission((long)1, Brand.AMERICAN_EXPRESS, Currency.BYN, BigDecimal.TEN);

        logger.debug(commission.toString());

        try {

            File file = new File("settings.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Commission.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(commission, file);

            logger.debug("settings successfully create and write to file - " + file.getName());

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public Commission getSettings(){

        Commission commission = null;

        logger.debug("getting settings from .xml");
        try {

            File file = new File("settings.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Commission.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            commission = (Commission) jaxbUnmarshaller.unmarshal(file);

            logger.debug("settings successfully getting from file - " + file.getName());

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return commission;
    }
}
