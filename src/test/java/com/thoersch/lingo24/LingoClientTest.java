package com.thoersch.lingo24;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class LingoClientTest extends TestCase {

    LingoClient lingoClient;

    @Before
    public void setup() {
        lingoClient = new LingoClient("FILL", "ME", "IN", "PLEASE", true);
    }

    @Test
    public void shouldReturnApiInformation() {
        System.out.println(lingoClient.getApiInformation().toString());
    }

    @Test
    public void shouldReturnApiStatus() {
        System.out.println(lingoClient.getApiStatus().toString());
    }

    @Test
    public void shouldAuthenticateUserAndReturnAccessToken() {
        System.out.println(lingoClient.getAccessToken());
    }

    @Test
    public void shouldReturnServicesForAuthenticatedUser() {
        String accessToken = lingoClient.getAccessToken().getAccessToken();
        System.out.println(lingoClient.getServices(accessToken ));
    }

    @Test
    public void shouldReturnSpecificServiceById() {
        String accessToken = lingoClient.getAccessToken().getAccessToken();
        long serviceId = lingoClient.getServices(accessToken).get(0).getId();
        System.out.println(lingoClient.getServiceById(accessToken, serviceId));
    }

    @Test
    public void shouldReturnLocalesForAuthenticatedUser() {
        String accessToken = lingoClient.getAccessToken().getAccessToken();
        System.out.println(lingoClient.getLocales(accessToken));
    }

    @Test
    public void shouldReturnSpecificLocaleById() {
        String accessToken = lingoClient.getAccessToken().getAccessToken();
        long localeId = lingoClient.getLocales(accessToken).get(0).getId();
        System.out.println(lingoClient.getLocaleById(accessToken, localeId));
    }
}
