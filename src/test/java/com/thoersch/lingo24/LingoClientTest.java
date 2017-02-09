package com.thoersch.lingo24;

import com.thoersch.lingo24.representations.Locale;
import com.thoersch.lingo24.representations.Project;
import com.thoersch.lingo24.representations.File;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@RunWith(JUnit4.class)
public class LingoClientTest extends TestCase {

    LingoClient lingoClient;

    @Before
    public void setup() {
        lingoClient = new LingoClient("17f46572-26b5-4c03-9082-61d88af073ff", "http://localhost", "a782c71a", "a608178e93175ddf19fa67a2f202e97b", true);
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
        List<Locale> locales = lingoClient.getLocales(accessToken);
        long localeId = locales.get(0).getId();
        System.out.println(lingoClient.getLocaleById(accessToken, localeId));
    }

    @Test
    public void shouldReturnProjects() {
        String accessToken = lingoClient.getAccessToken().getAccessToken();
        List<Project> projects = lingoClient.getProjects(accessToken);
        System.out.println(projects);
    }

    @Test
    public void shouldReturnSpecificProjectById() {
        String accessToken = lingoClient.getAccessToken().getAccessToken();
        List<Project> projects = lingoClient.getProjects(accessToken);
        long projectId = projects.get(0).getId();
        System.out.println(lingoClient.getProjectById(accessToken, projectId));
    }

    @Test
    public void shouldReturnDomainsForAuthenticatedUser() {
        String accessToken = lingoClient.getAccessToken().getAccessToken();
        System.out.println(lingoClient.getDomains(accessToken));
    }

    @Test
    public void shouldReturnSpecificDomainById() {
        String accessToken = lingoClient.getAccessToken().getAccessToken();
        long domainId = lingoClient.getDomains(accessToken).get(0).getId();
        System.out.println(lingoClient.getDomainById(accessToken, domainId));
    }

    @Test
    public void shouldCreateAndDeleteFile() {
        String accessToken = lingoClient.getAccessToken().getAccessToken();
        File file = lingoClient.createFile(accessToken, "API - TEST FILE");

        assertNotNull(file.getId());

        file = lingoClient.getFileById(accessToken, file.getId());

        assertNotNull(file.getId());

        try {
            lingoClient.deleteFileById(accessToken, file.getId());
        } catch (Exception e) {
            fail();
        }
    }
}
