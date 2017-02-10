package com.thoersch.lingo24;

import com.thoersch.lingo24.representations.*;
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
        LingoInfo info = new LingoInfo();
        info.setClientId("a782c71a");
        info.setClientSecret("a608178e93175ddf19fa67a2f202e97b");
        info.setRedirectUri("http://localhost");
        info.setRefreshToken("17f46572-26b5-4c03-9082-61d88af073ff");
        info.setIsSandbox(true);
        lingoClient = new LingoClient(info);
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
        Service foundService = lingoClient.getServiceById(accessToken, serviceId);
        System.out.println(foundService);

        assertNotNull("Service is null", foundService);
    }

    @Test
    public void shouldReturnLocalesForAuthenticatedUser() {
        String accessToken = lingoClient.getAccessToken().getAccessToken();
        List<Locale> locales = lingoClient.getLocales(accessToken);
        System.out.println(locales);

        assertTrue("Can't fetch locales", locales.size() > 0 && locales.get(0).getId() > 0);
    }

    @Test
    public void shouldReturnSpecificLocaleById() {
        String accessToken = lingoClient.getAccessToken().getAccessToken();
        List<Locale> locales = lingoClient.getLocales(accessToken);
        long localeId = locales.get(0).getId();
        Locale foundLocale = lingoClient.getLocaleById(accessToken, localeId);
        System.out.println(foundLocale);
        assertNotNull("local doesn't exist.", foundLocale);
    }

    @Test
    public void shouldReturnProjects() {
        String accessToken = lingoClient.getAccessToken().getAccessToken();
        List<Project> projects = lingoClient.getProjects(accessToken);
        System.out.println(projects);
        assertTrue("Should return at least one project.", projects.size() > 0);
    }

    @Test
    public void shouldReturnSpecificProjectById() {
        String accessToken = lingoClient.getAccessToken().getAccessToken();

        Project project = lingoClient.createProject(accessToken, getProjectToCreate());

        Project foundProject = lingoClient.getProjectById(accessToken, project.getId());

        assertNotNull(foundProject);
        assertTrue("Project didn't get created.", foundProject.getId() > 0);
        System.out.println(lingoClient.getProjectById(accessToken, project.getId()));

        lingoClient.deleteProjectById(accessToken, project.getId());
    }

    @Test
    public void shouldReturnProjectFiles() {
        String accessToken = lingoClient.getAccessToken().getAccessToken();


        Project project = lingoClient.createProject(accessToken, getProjectToCreate());
        long projectId = project.getId();

        File createdFile = lingoClient.createFile(accessToken, "test.test");
        lingoClient.addFileToProject(accessToken, projectId, createdFile);

        List<File> projectFiles = lingoClient.getProjectFiles(accessToken, projectId);
        assertTrue("Project files not found", projectFiles.size() > 0);
        System.out.println(projectFiles);

        lingoClient.deleteFileById(accessToken, createdFile.getId());
        lingoClient.deleteProjectById(accessToken, projectId);
    }

    @Test
    public void shouldReturnDomainsForAuthenticatedUser() {
        String accessToken = lingoClient.getAccessToken().getAccessToken();
        List<Domain> domains = lingoClient.getDomains(accessToken);
        System.out.println(domains);
        assertTrue("Can't fetch domains", domains.size() > 0);
    }

    @Test
    public void shouldReturnSpecificDomainById() {
        String accessToken = lingoClient.getAccessToken().getAccessToken();
        long domainId = lingoClient.getDomains(accessToken).get(0).getId();
        Domain specificDomain = lingoClient.getDomainById(accessToken, domainId);
        System.out.println(specificDomain);

        assertNotNull("Can't fetch domain.", specificDomain);
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

    private Project getProjectToCreate() {
        Project.Builder projectBuilder = new Project.Builder();
        projectBuilder.name("test project");
        projectBuilder.projectStatus(ProjectStatus.IN_PROGRESS);

        return projectBuilder.build();
    }
}
