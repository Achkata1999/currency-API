package demo.currency.util;

import demo.currency.model.web.xml.Command;
import demo.currency.model.web.xml.HelperClassXML;

import java.time.Instant;

public class XMLHelper {

    public static HelperClassXML mapRequestData(Command command, Instant timestamp) {
        HelperClassXML requestData = new HelperClassXML();

        if (command.getGet() != null) {

            requestData.setRequestId(command.getId());
            requestData.setClientId(command.getGet().getConsumer());
            requestData.setCurrency(command.getGet().getCurrency());
            requestData.setTimestamp(timestamp);
        } else if (command.getHistory() != null) {

            requestData.setRequestId(command.getId());
            requestData.setClientId(command.getHistory().getConsumer());
            requestData.setCurrency(command.getHistory().getCurrency());
            requestData.setPeriod(command.getHistory().getPeriod());
            requestData.setTimestamp(timestamp);
        }
        return requestData;
    }

}
