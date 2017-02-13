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
        LingoConfiguration info = new LingoConfiguration();
        info.setClientId("a782c71a");
        info.setClientSecret("a608178e93175ddf19fa67a2f202e97b");
        info.setRedirectUri("http://localhost");
        info.setRefreshToken("17f46572-26b5-4c03-9082-61d88af073ff");
        info.setIsSandbox(true);
        lingoClient = new LingoClient(info);
    }

    @Test
    public void shouldReturnApiInformation() {
        ApiInformation info = lingoClient.getApiInformation();
        assertNotNull("Api info's description is null", info.getDescription());
        assertNotNull("Api info's name is null", info.getName());
        assertNotNull("Api info's version is null", info.getVersion());
    }

    @Test
    public void shouldReturnApiStatus() {
        ApiStatus status = lingoClient.getApiStatus();
        assertNotNull("Api status' date is null", status.getDate());
        assertNotNull("Api status' version is null", status.getVersion());
    }

    @Test
    public void shouldAuthenticateUserAndReturnAccessToken() {
        OauthToken accessToken = lingoClient.getAccessToken();
        assertNotNull("Access token is null", accessToken.getAccessToken());
        assertNotNull("Access token expiration is null", accessToken.getExpiresIn());
        assertNotNull("Refresh token is null", accessToken.getRefreshToken());
        assertNotNull("Access token scope is null", accessToken.getScope());
        assertNotNull("Access token type is null", accessToken.getTokenType());
    }

    @Test
    public void shouldReturnServicesForAuthenticatedUser() {
        String accessToken = lingoClient.getAccessToken().getAccessToken();
        List<Service> services = lingoClient.getServices(accessToken);
        assertNotNull("Services are null", services);
        assertTrue("Empty services list", services.size() > 0);

        for(Service service : services) {
            assertNotNull("Service id is null", service.getId());
            assertNotNull("Service name is null", service.getName());
        }
    }

    @Test
    public void shouldReturnSpecificServiceById() {
        String accessToken = lingoClient.getAccessToken().getAccessToken();
        long serviceId = lingoClient.getServices(accessToken).get(0).getId();
        Service foundService = lingoClient.getServiceById(accessToken, serviceId);
        assertNotNull("Service is null", foundService);
        assertNotNull("Service id is null", foundService.getId());
        assertNotNull("Service name is null", foundService.getName());
    }

    @Test
    public void shouldReturnLocalesForAuthenticatedUser() {
        String accessToken = lingoClient.getAccessToken().getAccessToken();
        List<Locale> locales = lingoClient.getLocales(accessToken);
        assertNotNull("Locales is null", locales);
        assertTrue("Empty locales list", locales.size() > 0);

        for(Locale locale : locales) {
            assertNotNull("Locale id is null", locale.getId());
            assertNotNull("Locale country is null", locale.getCountry());
            assertNotNull("Locale language is null", locale.getLanguage());
        }
    }

    @Test
    public void shouldReturnSpecificLocaleById() {
        String accessToken = lingoClient.getAccessToken().getAccessToken();
        List<Locale> locales = lingoClient.getLocales(accessToken);
        long localeId = locales.get(0).getId();
        Locale foundLocale = lingoClient.getLocaleById(accessToken, localeId);
        assertNotNull("Locale is null", foundLocale);
        assertNotNull("Locale id is null", foundLocale.getId());
        assertNotNull("Locale country is null", foundLocale.getCountry());
        assertNotNull("Locale language is null", foundLocale.getLanguage());
    }

    @Test
    public void shouldReturnProjects() {
        String accessToken = lingoClient.getAccessToken().getAccessToken();
        List<Project> projects = lingoClient.getProjects(accessToken);
        assertNotNull("Projects is null", projects);
    }

    @Test
    public void shouldReturnSpecificProjectById() {
        String accessToken = lingoClient.getAccessToken().getAccessToken();
        Project project = lingoClient.createProject(accessToken, getProjectToCreate());
        Project foundProject = lingoClient.getProjectById(accessToken, project.getId());
        lingoClient.deleteProjectById(accessToken, project.getId());
        assertNotNull("Project is null", foundProject);
        assertNotNull("Project id is null", foundProject.getId());
        assertNotNull("Project name is null", foundProject.getName());
        assertNotNull("Project created date is null", foundProject.getCreated());
        assertNotNull("Project status is null", foundProject.getProjectStatus());
    }

    @Test
    public void shouldUpdateProject() {
        String accessToken = lingoClient.getAccessToken().getAccessToken();
        Project project = lingoClient.createProject(accessToken, getProjectToCreate());

        assertNotNull("Project is null", project);

        try {
            final String updatedProjectName = "API - Updated Project Name";
            project.setName(updatedProjectName);
            lingoClient.updateProjectById(accessToken, project.getId(), project);
            Project foundProject = lingoClient.getProjectById(accessToken, project.getId());

            assertNotNull("Project is null", foundProject);
            assertEquals("Project name does not match", updatedProjectName, foundProject.getName());
        } catch (Exception e) {
            fail();
        } finally {
            lingoClient.deleteProjectById(accessToken, project.getId());
        }
    }

    @Test
    public void shouldReturnProjectFiles() {
        String accessToken = lingoClient.getAccessToken().getAccessToken();
        Project project = lingoClient.createProject(accessToken, getProjectToCreate());
        assertNotNull("Project is null", project);
        assertNotNull("Project id is null", project.getId());
        assertNotNull("Project name is null", project.getName());
        assertNotNull("Project created date is null", project.getCreated());
        assertNotNull("Project status is null", project.getProjectStatus());

        long projectId = project.getId();

        File createdFile = lingoClient.createFile(accessToken, "test.test");
        assertNotNull("File is null", createdFile);
        assertNotNull("File id is null", createdFile.getId());
        assertNotNull("File name is null", createdFile.getName());
        assertNotNull("File type is null", createdFile.getType());

        lingoClient.addFileToProject(accessToken, projectId, createdFile);

        List<File> projectFiles = lingoClient.getProjectFiles(accessToken, projectId);
        assertNotNull("Project files is null", projectFiles);
        assertTrue("Project files not found", projectFiles.size() > 0);

        try {
            lingoClient.deleteFileById(accessToken, createdFile.getId());
            lingoClient.deleteProjectById(accessToken, projectId);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void shouldDeleteProjectFileById() {
        String accessToken = lingoClient.getAccessToken().getAccessToken();
        Project project = lingoClient.createProject(accessToken, getProjectToCreate());
        assertNotNull("Project is null", project);
        long projectId = project.getId();

        File createdFile = lingoClient.createFile(accessToken, "test.test");
        assertNotNull("File is null", createdFile);

        try {
            lingoClient.addFileToProject(accessToken, projectId, createdFile);
            lingoClient.deleteProjectFileById(accessToken, projectId, createdFile.getId());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void shouldReturnDomainsForAuthenticatedUser() {
        String accessToken = lingoClient.getAccessToken().getAccessToken();
        List<Domain> domains = lingoClient.getDomains(accessToken);
        assertNotNull("Domains is null", domains);
        assertTrue("Empty domains list", domains.size() > 0);

        for(Domain domain : domains) {
            assertNotNull("Domain is null", domain);
            assertNotNull("Domain id is null", domain.getId());
            assertNotNull("Domain name is null", domain.getName());
        }
    }

    @Test
    public void shouldReturnSpecificDomainById() {
        String accessToken = lingoClient.getAccessToken().getAccessToken();
        List<Domain> domains = lingoClient.getDomains(accessToken);
        assertNotNull("Domains is null", domains);
        assertTrue("Empty domains list", domains.size() > 0);

        long domainId = domains.get(0).getId();
        Domain specificDomain = lingoClient.getDomainById(accessToken, domainId);
        assertNotNull("Domain is null", specificDomain);
        assertNotNull("Domain id is null", specificDomain.getId());
        assertNotNull("Domain name is null", specificDomain.getName());
    }

    @Test
    public void shouldCreateAndDeleteFile() {
        String accessToken = lingoClient.getAccessToken().getAccessToken();
        File file = lingoClient.createFile(accessToken, "API - TEST FILE");

        assertNotNull("File is null", file);
        assertNotNull("File id is null", file.getId());
        assertNotNull("File name is null", file.getName());
        assertNotNull("File type is null", file.getType());

        try {
            file = lingoClient.getFileById(accessToken, file.getId());

            assertNotNull("File is null", file);
            assertNotNull("File id is null", file.getId());
            assertNotNull("File name is null", file.getName());
            assertNotNull("File type is null", file.getType());
        } catch (Exception e) {
            fail();
        } finally {
            lingoClient.deleteFileById(accessToken, file.getId());
        }
    }

    @Test
    public void shouldReturnContentsOfFile() {
        String accessToken = lingoClient.getAccessToken().getAccessToken();
        File file = lingoClient.createFile(accessToken, "API - TEST FILE");

        assertNotNull("File is null", file);
        assertNotNull("File id is null", file.getId());
        assertNotNull("File name is null", file.getName());
        assertNotNull("File type is null", file.getType());

        try {
            final String message = "API TEST CONTENT";
            try {
                lingoClient.updateFileContent(accessToken, file.getId(), message);
            } catch (Exception e) {
                fail();
            }

            String content = lingoClient.getFileContent(accessToken, file.getId()).replace("\"", "");

            assertNotNull("File content is null", content);
            assertEquals("File content does not match", message, content);
        } catch(Exception ex) {
            fail();
        } finally {
            lingoClient.deleteFileById(accessToken, file.getId());
        }
    }

    @Test
    public void shouldAddJobToProject() {
        String accessToken = lingoClient.getAccessToken().getAccessToken();
        Project project = lingoClient.createProject(accessToken, getProjectToCreate());

        assertNotNull("Project is null", project);

        long projectId = project.getId();


        try {
            File createdFile = lingoClient.createFile(accessToken, "test.test");
            assertNotNull("File is null", createdFile);

            lingoClient.addFileToProject(accessToken, projectId, createdFile);

            List<Locale> locales = lingoClient.getLocales(accessToken);
            long sourceLocaleId = locales.get(0).getId();
            long targetLocaleId = locales.get(1).getId();
            List<Service> services = lingoClient.getServices(accessToken);
            long serviceId = services.get(0).getId();

            Job job = new Job.Builder().projectId(projectId).sourceFileId(createdFile.getId()).sourceLocaleId(sourceLocaleId).targetLocaleId(targetLocaleId).serviceId(serviceId).build();
            job = lingoClient.addJobToProject(accessToken, projectId, job);
            assertNotNull("Job is null", job);
            assertNotNull("Job id is null", job.getId());

            Job createdJob = lingoClient.getJobById(accessToken, projectId, job.getId());
            assertNotNull("Job is null", createdJob);
            assertNotNull("Job id is null", createdJob.getId());
            assertNotNull("Job status is null", createdJob.getJobStatus());
            assertNotNull("Job project id is null", createdJob.getProjectId());
            assertNotNull("Job service id is null", createdJob.getServiceId());
            assertNotNull("Job source file id is null", createdJob.getSourceFileId());
            assertNotNull("Job source locale is null", createdJob.getSourceLocale());
            assertNotNull("Job source locale id is null", createdJob.getSourceLocaleId());
            assertNotNull("Job target file id is null", createdJob.getTargetFileId());
            assertNotNull("Job target locale is null", createdJob.getTargetLocale());
            assertNotNull("Job target locale id is null", createdJob.getTargetLocaleId());

            lingoClient.deleteJobById(accessToken, projectId, createdJob.getId());
        } catch(Exception ex) {
            fail();
        } finally {
            lingoClient.deleteProjectById(accessToken, projectId);
        }
    }

    @Test
    public void shouldReturnFilesForJob() {
        String accessToken = lingoClient.getAccessToken().getAccessToken();
        Project project = lingoClient.createProject(accessToken, getProjectToCreate());

        assertNotNull("Project is null", project);

        long projectId = project.getId();

        try {
            File createdFile = lingoClient.createFile(accessToken, "test.test");
            assertNotNull("File is null", createdFile);

            lingoClient.addFileToProject(accessToken, projectId, createdFile);

            List<Locale> locales = lingoClient.getLocales(accessToken);
            long sourceLocaleId = locales.get(0).getId();
            long targetLocaleId = locales.get(1).getId();
            List<Service> services = lingoClient.getServices(accessToken);
            long serviceId = services.get(0).getId();

            Job job = new Job.Builder().projectId(projectId).sourceFileId(createdFile.getId()).sourceLocaleId(sourceLocaleId).targetLocaleId(targetLocaleId).serviceId(serviceId).build();
            job = lingoClient.addJobToProject(accessToken, projectId, job);
            assertNotNull("Job is null", job);

            List<File> files = lingoClient.getFilesInJob(accessToken, projectId, job.getId());
            assertNotNull("Files is null", files);
            assertTrue("Files is empty", files.size() > 0);
        } catch(Exception ex) {
            fail();
        } finally {
            lingoClient.deleteProjectById(accessToken, projectId);
        }
    }

    @Test
    public void shouldReturnJobsForProject() {
        String accessToken = lingoClient.getAccessToken().getAccessToken();
        Project project = lingoClient.createProject(accessToken, getProjectToCreate());

        assertNotNull("Project is null", project);

        long projectId = project.getId();

        try {
            File createdFile = lingoClient.createFile(accessToken, "test.test");
            assertNotNull("File is null", createdFile);

            lingoClient.addFileToProject(accessToken, projectId, createdFile);

            List<Locale> locales = lingoClient.getLocales(accessToken);
            long sourceLocaleId = locales.get(0).getId();
            long targetLocaleId = locales.get(1).getId();
            List<Service> services = lingoClient.getServices(accessToken);
            long serviceId = services.get(0).getId();

            Job job = new Job.Builder().projectId(projectId).sourceFileId(createdFile.getId()).sourceLocaleId(sourceLocaleId).targetLocaleId(targetLocaleId).serviceId(serviceId).build();
            job = lingoClient.addJobToProject(accessToken, projectId, job);
            assertNotNull("Job is null", job);

            List<Job> jobs = lingoClient.getJobsForProject(accessToken, projectId);
            assertNotNull("Jobs is null", jobs);
            assertTrue("Jobs is empty", jobs.size() > 0);
        } catch(Exception ex) {
            fail();
        } finally {
            lingoClient.deleteProjectById(accessToken, projectId);
        }
    }

    @Test
    public void testTranslationCreation() throws Exception {
        long englishLocaleId = 47;
        long espanolMXId = 183;
        long espnanolESId = 56;
        long serviceOnBrandId = 23;

        String accessToken = lingoClient.getAccessToken().getAccessToken();

        ProjectVM projectVM = new ProjectVM();

        projectVM.setName("API - Translation Test Project");
        projectVM.setProjectStatus(ProjectStatus.IN_PROGRESS);

        FileVM fileVM1 = new FileVM();
        fileVM1.setName("testFile.json");
        fileVM1.setContent("{\"Key\": \"Value\"}");

        fileVM1.setType(FileType.SOURCE);

        Job job1 = new Job.Builder()
                .sourceLocaleId(englishLocaleId)
                .targetLocaleId(espanolMXId)
                .serviceId(serviceOnBrandId)
                .jobStatus(JobStatus.NEW).build();

        Job job2 = new Job.Builder()
                .sourceLocaleId(englishLocaleId)
                .targetLocaleId(espnanolESId)
                .serviceId(serviceOnBrandId)
                .jobStatus(JobStatus.NEW).build();

        projectVM.setFile(fileVM1);
        projectVM.addJob(job1);
        projectVM.addJob(job2);
        ProjectVM createdProject = lingoClient.createTranslationJob(accessToken, projectVM);

        System.out.println(createdProject);
        assertNotNull(createdProject.getId());

        lingoClient.deleteProjectById(accessToken, createdProject.getId());
    }

    private Project getProjectToCreate() {
        Project.Builder projectBuilder = new Project.Builder();
        projectBuilder.name("API - Test Project");
        projectBuilder.projectStatus(ProjectStatus.IN_PROGRESS);

        return projectBuilder.build();
    }
}
