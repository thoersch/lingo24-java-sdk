package com.thoersch.lingo24;

import com.thoersch.lingo24.representations.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LingoClient extends BaseClient {
    private static final Double VERSION = 1.0;
    private static final String BASE_SANDBOX_URL = "https://api-demo.lingo24.com/docs/v1";
    private static final String BASE_URL = "https://api.lingo24.com/docs/v1";
    private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_SECRET = "client_secret";
    private static final String GRANT_TYPE = "grant_type";
    private static final String REDIRECT_URI = "redirect_uri";
    private static final String REFRESH_TOKEN = "refresh_token";

    private final String refreshToken;
    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;
    private final boolean isSandbox;
    private final PagingInput defaultPagingInput;

    public LingoClient(LingoConfiguration lingoInfo) {
        super(VERSION);
        this.refreshToken = lingoInfo.getRefreshToken();
        this.redirectUri = lingoInfo.getRedirectUri();
        this.clientId = lingoInfo.getClientId();
        this.clientSecret = lingoInfo.getClientSecret();
        this.isSandbox = lingoInfo.isSandbox();
        this.defaultPagingInput = getDefaultPagingInput();

        if(refreshToken == null || redirectUri == null || clientId == null || clientSecret == null) {
            throw new LingoException("All configuration values required.");
        }

        initializeHttp();
    }

    @Override
    protected String getBaseUrl() {
        return isSandbox ? BASE_SANDBOX_URL : BASE_URL;
    }

    /**
     * Returns information on the API.
     * @return
     */
    public ApiInformation getApiInformation() {
        final String url = "/";
        return get(null, url, ApiInformation.class);
    }

    /**
     * Returns status information for the API.
     * @return
     */
    public ApiStatus getApiStatus() {
        final String url = "/status";
        return get(null, url, ApiStatus.class);
    }

    /**
     * Get an oauth2 access token for interacting with the API.
     * @return
     */
    public OauthToken getAccessToken() {
        final String url = "/oauth2/access";
        final Map<String, Object> queryStrings = new HashMap<String, Object>() {{
            put(CLIENT_ID, clientId);
            put(CLIENT_SECRET, clientSecret);
            put(REDIRECT_URI, redirectUri);
            put(GRANT_TYPE, REFRESH_TOKEN);
            put(REFRESH_TOKEN, refreshToken);
        }};

        return create(null, url, null, queryStrings, OauthToken.class);
    }

    /**
     * Returns a page of Lingo24 services available for translation jobs.
     * @param accessToken
     * @return
     */
    public List<Service> getServices(String accessToken) {
        return getServices(accessToken, defaultPagingInput);
    }

    /**
     * Returns a page of Lingo24 services available for translation jobs.
     * @param accessToken
     * @param pagingInput
     * @return
     */
    public List<Service> getServices(String accessToken, PagingInput pagingInput) {
        final String url = "/services";
        return getList(accessToken, url, pagingInput, Service.class);
    }

    /**
     * Returns a single Lingo24 service.
     * @param accessToken
     * @param serviceId
     * @return
     */
    public Service getServiceById(String accessToken, long serviceId) {
        final String url = String.format("/services/%d", serviceId);
        return get(accessToken, url, Service.class);
    }

    /**
     * Returns a page of Lingo24 locales available for translation jobs.
     * @param accessToken
     * @return
     */
    public List<Locale> getLocales(String accessToken) {
        return getLocales(accessToken, defaultPagingInput);
    }

    /**
     * Returns a page of Lingo24 locales available for translation jobs.
     * @param accessToken
     * @param pagingInput
     * @return
     */
    public List<Locale> getLocales(String accessToken, PagingInput pagingInput) {
        final String url = "/locales";

        return getList(accessToken, url, pagingInput, Locale.class);
    }

    public Locale getLocaleById(String accessToken, long localeId) {
        final String url = String.format("/locales/%d", localeId);
        return get(accessToken, url, Locale.class);
    }

    /**
     * Returns a page of Lingo24 domains available for translation jobs.
     * @param accessToken
     * @return
     */
    public List<Domain> getDomains(String accessToken) {
        return getDomains(accessToken, defaultPagingInput);
    }

    /**
     * Returns a page of Lingo24 domains available for translation jobs.
     * @param accessToken
     * @param pagingInput
     * @return
     */
    public List<Domain> getDomains(String accessToken, PagingInput pagingInput) {
        final String url = "/domains";
        return getList(accessToken, url, pagingInput, Domain.class);
    }

    /**
     * Returns a single Lingo24 domain.
     * @param accessToken
     * @param domainId
     * @return
     */
    public Domain getDomainById(String accessToken, long domainId) {
        final String url = String.format("/domains/%d", domainId);
        return get(accessToken, url, Domain.class);
    }

    /**
     * Create a new file.
     * @param accessToken
     * @param fileName
     * @return
     */
    public File createFile(String accessToken, String fileName) {
        final String url = "/files";
        File payload = new File(0L, fileName, FileType.SOURCE);
        return create(accessToken, url, payload, File.class);
    }

    /**
     * Returns a file's metadata.
     * @param accessToken
     * @param fileId
     * @return
     */
    public File getFileById(String accessToken, long fileId) {
        final String url = String.format("/files/%d", fileId);
        return get(accessToken, url, File.class);
    }

    /**
     * Delete a file.
     * @param accessToken
     * @param fileId
     */
    public void deleteFileById(String accessToken, long fileId) {
        final String url = String.format("/files/%d", fileId);
        delete(accessToken, url);
    }

    /**
     * Returns a file's content.
     * @param accessToken
     * @param fileId
     * @return
     */
    public String getFileContent(String accessToken, long fileId) {
        final String url = String.format("/files/%d/content", fileId);
        return get(accessToken, url, String.class);
    }

    /**
     * Upload a file's content.
     * @param accessToken
     * @param fileId
     * @param content
     * @return
     */
    public void updateFileContent(String accessToken, long fileId, String content) {
        final String url = String.format("/files/%d/content", fileId);
        update(accessToken, url, content, String.class);
    }

    /**
     * Returns a page of projects.
     * @param accessToken
     * @return
     */
    public List<Project> getProjects(String accessToken) {
        return getProjects(accessToken, defaultPagingInput);
    }

    /**
     * Returns a page of projects.
     * @param accessToken
     * @param pagingInput
     * @return
     */
    public List<Project> getProjects(String accessToken, PagingInput pagingInput) {
        final String url = "/projects";
        return getList(accessToken, url, pagingInput, Project.class);
    }

    /**
     * Create a new project.
     * @param accessToken
     * @param project
     * @return
     */
    public Project createProject(String accessToken, Project project) {
        final String url = "/projects";
        return create(accessToken, url, project, Project.class);
    }

    /**
     * Returns a single Lingo24 project.
     * @param accessToken
     * @param projectId
     * @return
     */
    public Project getProjectById(String accessToken, long projectId) {
        final String url = String.format("/projects/%d", projectId);
        return get(accessToken, url, Project.class);
    }

    /**
     * Cancel a created or quoted project.
     * @param accessToken
     * @param projectId
     */
    public void deleteProjectById(String accessToken, long projectId) {
        final String url = String.format("/projects/%d", projectId);
        delete(accessToken, url);
    }

    /**
     * Update a project.
     * @param accessToken
     * @param projectId
     * @param project
     * @return
     */
    public Project updateProjectById(String accessToken, long projectId, Project project) {
        if (projectId != project.getId()) {
            throw new LingoException("Provided project and projectId are different.");
        }

        final String url = String.format("/projects/%d", projectId);
        return update(accessToken, url, project, Project.class);
    }

    /**
     * Returns a page of files associated with a project.
     * @param accessToken
     * @param projectId
     * @return
     */
    public List<File> getProjectFiles(String accessToken, long projectId) {
        return getProjectFiles(accessToken, projectId, defaultPagingInput);
    }

    /**
     * Returns a page of files associated with a project.
     * @param accessToken
     * @param projectId
     * @param pagingInput
     * @return
     */
    public List<File> getProjectFiles(String accessToken, long projectId, PagingInput pagingInput) {
        final String url = String.format("/projects/%d/files", projectId);
        return getList(accessToken, url, pagingInput, File.class);
    }

    /**
     * Link an existing file to a project.
     * @param accessToken
     * @param projectId
     * @param file
     */
    public void addFileToProject(String accessToken, long projectId, File file) {
        final String url = String.format("/projects/%d/files", projectId);
        create(accessToken, url, file);
    }

    /**
     * Removes a file from a project.
     * @param accessToken
     * @param projectId
     * @param fileId
     */
    public void deleteProjectFileById(String accessToken, long projectId, long fileId) {
        final String url = String.format("/projects/%d/files/%d", projectId, fileId);
        delete(accessToken, url);
    }

    /**
     * Returns a page of jobs for a Lingo24 project.
     * @param accessToken
     * @param projectId
     * @return
     */
    public List<Job> getJobsForProject(String accessToken, long projectId) {
        return getJobsForProject(accessToken, projectId, defaultPagingInput);
    }

    /**
     * Returns a page of jobs for a Lingo24 project.
     * @param accessToken
     * @param projectId
     * @param pagingInput
     * @return
     */
    public List<Job> getJobsForProject(String accessToken, long projectId, PagingInput pagingInput) {
        final String url = String.format("/projects/%d/jobs", projectId);
        return getList(accessToken, url, pagingInput, Job.class);
    }

    /**
     * Create a new job.
     * @param accessToken
     * @param projectId
     * @param job
     * @return
     */
    public Job addJobToProject(String accessToken, long projectId, Job job) {
        final String url = String.format("/projects/%d/jobs", projectId);
        return create(accessToken, url, job, Job.class);
    }

    /**
     * Delete a job.
     * @param accessToken
     * @param projectId
     * @param jobId
     */
    public void deleteJobById(String accessToken, long projectId, long jobId) {
        final String url = String.format("/projects/%d/jobs/%d", projectId, jobId);
        delete(accessToken, url);
    }

    /**
     * Returns a single Lingo24 job.
     * @param accessToken
     * @param projectId
     * @param jobId
     * @return
     */
    public Job getJobById(String accessToken, long projectId, long jobId) {
        final String url = String.format("/projects/%d/jobs/%d", projectId, jobId);
        return get(accessToken, url, Job.class);
    }

    /**
     * Returns a page of files associated with a job.
     * @param accessToken
     * @param projectId
     * @param jobId
     * @return
     */
    public List<File> getFilesInJob(String accessToken, long projectId, long jobId) {
        return getFilesInJob(accessToken, projectId, jobId, defaultPagingInput);
    }

    /**
     * Returns a page of files associated with a job.
     * @param accessToken
     * @param projectId
     * @param jobId
     * @param pagingInput
     * @return
     */
    public List<File> getFilesInJob(String accessToken, long projectId, long jobId, PagingInput pagingInput) {
        final String url = String.format("/projects/%d/jobs/%d/files", projectId, jobId);
        return getList(accessToken, url, pagingInput, File.class);
    }

    /**
     * Returns metrics for a Lingo24 job.
     * @param accessToken
     * @param projectId
     * @param jobId
     * @return
     */
    public JobMetrics getProjectJobMetrics(String accessToken, long projectId, long jobId) {
        final String url = String.format("/projects/%d/jobs/%d/metrics", projectId, jobId);
        return get(accessToken, url, JobMetrics.class);
    }

    /**
     * Returns prices for a Lingo24 job.
     * @param accessToken
     * @param projectId
     * @param jobId
     * @return
     */
    public Price getProjectJobPrice(String accessToken, long projectId, long jobId) {
        final String url = String.format("/projects/%d/jobs/%d/price", projectId, jobId);
        return get(accessToken, url, Price.class);
    }


    public ProjectVM createTranslationJob(String accessToken, ProjectVM project) throws Exception {
        if (project == null) {
            return null;
        }

        Project createdProject = project;
        if (project.getId() == null) {
            createdProject = this.createProject(accessToken, project);
            project.setId(createdProject.getId());
        }

        try {

            FileVM file = project.getFile();
            File createdFile = file;
            if (file != null && (file.getId() == null || file.getId() <= 0)) {
                createdFile = this.createFile(accessToken, file.getName());

                this.updateFileContent(accessToken, createdFile.getId(), file.getContent());
                this.addFileToProject(accessToken, createdProject.getId(), createdFile);
            }

            project.setFile(new FileVM(createdFile));

            List<Job> createdJobs = new ArrayList<Job>();
            for (Job job : project.getJobs()) {
                if (job.getId() == null || job.getId() <= 0) {
                    job.setSourceFileId(createdFile.getId());
                    job.setProjectId(createdProject.getId());
                    Job createdJob = this.addJobToProject(accessToken, createdProject.getId(), job);
                    createdJobs.add(createdJob);
                }
            }

            project.setJobs(createdJobs);
        } catch (Exception ex) {
            this.deleteProjectById(accessToken, createdProject.getId());
            throw ex;
        }

        return project;
    }
}
