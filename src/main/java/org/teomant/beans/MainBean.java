package org.teomant.beans;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.teomant.clent.DateTimeClient;
import org.teomant.server.GetDateTimeRequest;
import org.teomant.server.GetDateTimeResponse;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.soap.SOAPBinding;
import java.net.MalformedURLException;
import java.net.URL;

@ManagedBean(name = "mainBean")
@SessionScoped
public class MainBean {

    @PostConstruct
    private void init() {
        updateDateTime();
    }
    private String dateTime;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void updateDateTime() {

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("org.teomant.server");

        DateTimeClient client = new DateTimeClient();
        client.setDefaultUri("http://localhost:8080/server");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        dateTime = client.getDateTime().getDate().toString();
    }
}