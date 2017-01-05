package by.ibrel.testapp;

import by.ibrel.testapp.logic.bean.*;
import by.ibrel.testapp.logic.model.Commission;
import by.ibrel.testapp.logic.model.Commissions;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


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

        Commission commissionByn = new Commission(Brand.AMERICAN_EXPRESS, Currency.BYN, BigDecimal.ONE);
        Commission commissionEur = new Commission(Brand.MASTERCARD, Currency.EUR, BigDecimal.TEN);
        Commission commissionRUB = new Commission(Brand.AMERICAN_EXPRESS, Currency.RUB, new BigDecimal(3));
        Commission commissionUSD = new Commission(Brand.MASTERCARD, Currency.USD, new BigDecimal(5));

        Commissions commissions = new Commissions();
        commissions.add(commissionByn);
        commissions.add(commissionEur);
        commissions.add(commissionRUB);
        commissions.add(commissionUSD);

        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(Commissions.class);

            File file =  new File("settings.xml");
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(commissions, file);

            logger.debug("settings successfully create and write ");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public List<Commission> getSettings(){

        List<Commission> commissionsList = new ArrayList<>();

        logger.debug("getting settings from .xml");
        try {

            File file = new File("settings.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Commissions.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Commissions commissions = (Commissions) jaxbUnmarshaller.unmarshal(file);

              commissionsList.addAll(commissions.getItems());
            logger.debug("settings successfully getting from file - " + file.getName());

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return commissionsList;
    }
}
