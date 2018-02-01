package com.thoersch.lingo24;

import com.fasterxml.jackson.databind.JsonNode;
import com.thoersch.lingo24.representations.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
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
    private final ObjectMapper objectMapper;

    public LingoClient(LingoConfiguration lingoInfo) {
        super(VERSION);
        this.refreshToken = lingoInfo.getRefreshToken();
        this.redirectUri = lingoInfo.getRedirectUri();
        this.clientId = lingoInfo.getClientId();
        this.clientSecret = lingoInfo.getClientSecret();
        this.isSandbox = lingoInfo.isSandbox();
        this.defaultPagingInput = getDefaultPagingInput();
        this.objectMapper = new ObjectMapper();

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
     * @return ApiInformation
     */
    public ApiInformation getApiInformation() {
        final String url = "/";
        try {
            return get(null, url, ApiInformation.class);
        } catch(LingoUnauthorizedException e) {
            throw new LingoException("Unknown authorization error on non-authenticated call.");
        }
    }

    /**
     * Returns status information for the API.
     * @return ApiStatus
     */
    public ApiStatus getApiStatus() {
        final String url = "/status";
        try {
            return get(null, url, ApiStatus.class);
        } catch(LingoUnauthorizedException e) {
            throw new LingoException("Unknown authorization error on non-authenticated call.");
        }
    }

    /**
     * Get an oauth2 access token for interacting with the API.
     * @return OauthToken
     * @throws LingoUnauthorizedException
     */
    public OauthToken getAccessToken() throws LingoUnauthorizedException {
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
     * @param accessToken accessToken
     * @return List of Service
     * @throws LingoUnauthorizedException
     */
    public List<Service> getServices(String accessToken) throws LingoUnauthorizedException {
        return getServices(accessToken, defaultPagingInput);
    }

    /**
     * Returns a page of Lingo24 services available for translation jobs.
     * @param accessToken accessToken
     * @param pagingInput pagingInput
     * @return List of Service
     * @throws LingoUnauthorizedException
     */
    public List<Service> getServices(String accessToken, PagingInput pagingInput) throws LingoUnauthorizedException {
        final String url = "/services";
        return getList(accessToken, url, pagingInput, Service.class);
    }

    /**
     * Returns a single Lingo24 service.
     * @param accessToken accessToken
     * @param serviceId serviceId
     * @return Service
     * @throws LingoUnauthorizedException
     */
    public Service getServiceById(String accessToken, long serviceId) throws LingoUnauthorizedException {
        final String url = String.format("/services/%d", serviceId);
        return get(accessToken, url, Service.class);
    }

    /**
     * Returns a page of Lingo24 locales available for translation jobs.
     * @param accessToken accessToken
     * @return List of Locale
     * @throws LingoUnauthorizedException
     */
    public List<Locale> getLocales(String accessToken) throws LingoUnauthorizedException {
        return getLocales(accessToken, defaultPagingInput);
    }

    /**
     * Returns a page of Lingo24 locales available for translation jobs.
     * @param accessToken accessToken
     * @param pagingInput pagingInput
     * @return List of Locale
     * @throws LingoUnauthorizedException
     */
    public List<Locale> getLocales(String accessToken, PagingInput pagingInput) throws LingoUnauthorizedException {
        final String url = "/locales";

        return getList(accessToken, url, pagingInput, Locale.class);
    }

    /**
     * @param accessToken accessToken
     * @param localeId localeId
     * @return Locale
     * @throws LingoUnauthorizedException
     */
    public Locale getLocaleById(String accessToken, long localeId) throws LingoUnauthorizedException {
        final String url = String.format("/locales/%d", localeId);
        return get(accessToken, url, Locale.class);
    }

    /**
     * Returns a page of Lingo24 domains available for translation jobs.
     * @param accessToken accessToken
     * @return List of Domain
     * @throws LingoUnauthorizedException
     */
    public List<Domain> getDomains(String accessToken) throws LingoUnauthorizedException {
        return getDomains(accessToken, defaultPagingInput);
    }

    /**
     * Returns a page of Lingo24 domains available for translation jobs.
     * @param accessToken accessToken
     * @param pagingInput pagingInput
     * @return List of Domain
     * @throws LingoUnauthorizedException
     */
    public List<Domain> getDomains(String accessToken, PagingInput pagingInput) throws LingoUnauthorizedException {
        final String url = "/domains";
        return getList(accessToken, url, pagingInput, Domain.class);
    }

    /**
     * Returns a single Lingo24 domain.
     * @param accessToken accessToken
     * @param domainId domainId
     * @return Domain
     * @throws LingoUnauthorizedException
     */
    public Domain getDomainById(String accessToken, long domainId) throws LingoUnauthorizedException {
        final String url = String.format("/domains/%d", domainId);
        return get(accessToken, url, Domain.class);
    }

    /**
     * Create a new file.
     * @param accessToken accessToken
     * @param fileName fileName
     * @return File
     * @throws LingoUnauthorizedException
     */
    public File createFile(String accessToken, String fileName) throws LingoUnauthorizedException {
        final String url = "/files";
        File payload = new File(0L, fileName, FileType.SOURCE);
        return create(accessToken, url, payload, File.class);
    }

    /**
     * Returns a file's metadata.
     * @param accessToken accessToken
     * @param fileId fileId
     * @return File
     * @throws LingoUnauthorizedException
     */
    public File getFileById(String accessToken, long fileId) throws LingoUnauthorizedException {
        final String url = String.format("/files/%d", fileId);
        return get(accessToken, url, File.class);
    }

    /**
     * Delete a file.
     * @param accessToken accessToken
     * @param fileId fileId
     * @throws LingoUnauthorizedException
     */
    public void deleteFileById(String accessToken, long fileId) throws LingoUnauthorizedException {
        final String url = String.format("/files/%d", fileId);
        delete(accessToken, url);
    }

    /**
     * Returns a file's content.
     * @param accessToken accessToken
     * @param fileId fileId
     * @return file content
     * @throws LingoUnauthorizedException
     */
    public String getFileContent(String accessToken, long fileId) throws LingoUnauthorizedException {
        final String url = String.format("/files/%d/content", fileId);
        return get(accessToken, url, String.class);
    }

    /**
     * Upload a file's content.
     * @param accessToken accessToken
     * @param fileId fileId
     * @param content content
     * @throws LingoUnauthorizedException
     */
    public void updateFileContent(String accessToken, long fileId, String content) throws LingoUnauthorizedException {
        final String url = String.format("/files/%d/content", fileId);

        try {
            JsonNode node = objectMapper.readTree(content);
            update(accessToken, url, node, JsonNode.class);
        } catch (LingoException | IOException e) {
            throw new LingoException("Unable to update file content" + e.getMessage());
        }
    }

    /**
     * Returns a page of projects.
     * @param accessToken accessToken
     * @return List of Project
     * @throws LingoUnauthorizedException
     */
    public List<Project> getProjects(String accessToken) throws LingoUnauthorizedException {
        return getProjects(accessToken, defaultPagingInput);
    }

    /**
     * Returns a page of projects.
     * @param accessToken accessToken
     * @param pagingInput pagingInput
     * @return List of Project
     * @throws LingoUnauthorizedException
     */
    public List<Project> getProjects(String accessToken, PagingInput pagingInput) throws LingoUnauthorizedException {
        final String url = "/projects";
        return getList(accessToken, url, pagingInput, Project.class);
    }

    /**
     * Create a new project.
     * @param accessToken accessToken
     * @param project project
     * @return Project
     * @throws LingoUnauthorizedException
     */
    public Project createProject(String accessToken, Project project) throws LingoUnauthorizedException {
        final String url = "/projects";
        return create(accessToken, url, project, Project.class);
    }

    /**
     * Returns a single Lingo24 project.
     * @param accessToken accessToken
     * @param projectId project Id
     * @return Project
     * @throws LingoUnauthorizedException
     */
    public Project getProjectById(String accessToken, long projectId) throws LingoUnauthorizedException {
        final String url = String.format("/projects/%d", projectId);
        return get(accessToken, url, Project.class);
    }

    /**
     * Cancel a created or quoted project.
     * @param accessToken accessToken
     * @param projectId project Id
     * @throws LingoUnauthorizedException
     */
    public void deleteProjectById(String accessToken, long projectId) throws LingoUnauthorizedException {
        final String url = String.format("/projects/%d", projectId);
        delete(accessToken, url);
    }

    /**
     * Update a project.
     * @param accessToken accessToken
     * @param projectId project Id
     * @param project project
     * @return Project
     * @throws LingoUnauthorizedException
     */
    public Project updateProjectById(String accessToken, long projectId, Project project) throws LingoUnauthorizedException {
        if (projectId != project.getId()) {
            throw new LingoException("Provided project and projectId are different.");
        }

        final String url = String.format("/projects/%d", projectId);
        return update(accessToken, url, project, Project.class);
    }

    /**
     * Returns a page of files associated with a project.
     * @param accessToken accessToken
     * @param projectId project Id
     * @return List of File
     * @throws LingoUnauthorizedException
     */
    public List<File> getProjectFiles(String accessToken, long projectId) throws LingoUnauthorizedException {
        return getProjectFiles(accessToken, projectId, defaultPagingInput);
    }

    /**
     * Returns a page of files associated with a project.
     * @param accessToken accessToken
     * @param projectId project Id
     * @param pagingInput pagingInput
     * @return List of File
     * @throws LingoUnauthorizedException
     */
    public List<File> getProjectFiles(String accessToken, long projectId, PagingInput pagingInput) throws LingoUnauthorizedException {
        final String url = String.format("/projects/%d/files", projectId);
        return getList(accessToken, url, pagingInput, File.class);
    }

    /**
     * Link an existing file to a project.
     * @param accessToken accessToken
     * @param projectId project Id
     * @param file file
     * @throws LingoUnauthorizedException
     */
    public void addFileToProject(String accessToken, long projectId, File file) throws LingoUnauthorizedException {
        final String url = String.format("/projects/%d/files", projectId);
        create(accessToken, url, file);
    }

    /**
     * Removes a file from a project.
     * @param accessToken accessToken
     * @param projectId project Id
     * @param fileId fileId
     * @throws LingoUnauthorizedException
     */
    public void deleteProjectFileById(String accessToken, long projectId, long fileId) throws LingoUnauthorizedException {
        final String url = String.format("/projects/%d/files/%d", projectId, fileId);
        delete(accessToken, url);
    }

    /**
     * Returns a page of jobs for a Lingo24 project.
     * @param accessToken accessToken
     * @param projectId projectId
     * @return List of Job
     * @throws LingoUnauthorizedException
     */
    public List<Job> getJobsForProject(String accessToken, long projectId) throws LingoUnauthorizedException {
        return getJobsForProject(accessToken, projectId, defaultPagingInput);
    }

    /**
     * Returns a page of jobs for a Lingo24 project.
     * @param accessToken accessToken
     * @param projectId projectId
     * @param pagingInput pagingInput
     * @return List of Job
     * @throws LingoUnauthorizedException
     */
    public List<Job> getJobsForProject(String accessToken, long projectId, PagingInput pagingInput) throws LingoUnauthorizedException {
        final String url = String.format("/projects/%d/jobs", projectId);
        return getList(accessToken, url, pagingInput, Job.class);
    }

    /**
     * Create a new job.
     * @param accessToken accessToken
     * @param projectId projectId
     * @param job job
     * @return Job
     * @throws LingoUnauthorizedException
     */
    public Job addJobToProject(String accessToken, long projectId, Job job) throws LingoUnauthorizedException {
        final String url = String.format("/projects/%d/jobs", projectId);
        return create(accessToken, url, job, Job.class);
    }

    /**
     * Delete a job.
     * @param accessToken accessToken
     * @param projectId projectId
     * @param jobId jobId
     * @throws LingoUnauthorizedException
     */
    public void deleteJobById(String accessToken, long projectId, long jobId) throws LingoUnauthorizedException {
        final String url = String.format("/projects/%d/jobs/%d", projectId, jobId);
        delete(accessToken, url);
    }

    /**
     * Returns a single Lingo24 job.
     * @param accessToken accessToken
     * @param projectId projectId
     * @param jobId jobId
     * @return Job
     * @throws LingoUnauthorizedException
     */
    public Job getJobById(String accessToken, long projectId, long jobId) throws LingoUnauthorizedException {
        final String url = String.format("/projects/%d/jobs/%d", projectId, jobId);
        return get(accessToken, url, Job.class);
    }

    /**
     * Returns a page of files associated with a job.
     * @param accessToken accessToken
     * @param projectId projectId
     * @param jobId jobId
     * @return List of File
     * @throws LingoUnauthorizedException
     */
    public List<File> getFilesInJob(String accessToken, long projectId, long jobId) throws LingoUnauthorizedException {
        return getFilesInJob(accessToken, projectId, jobId, defaultPagingInput);
    }

    /**
     * Returns a page of files associated with a job.
     * @param accessToken accessToken
     * @param projectId projectId
     * @param jobId jobId
     * @param pagingInput pagingInput
     * @return List of File
     * @throws LingoUnauthorizedException
     */
    public List<File> getFilesInJob(String accessToken, long projectId, long jobId, PagingInput pagingInput) throws LingoUnauthorizedException {
        final String url = String.format("/projects/%d/jobs/%d/files", projectId, jobId);
        return getList(accessToken, url, pagingInput, File.class);
    }

    /**
     * Returns metrics for a Lingo24 job.
     * @param accessToken accessToken
     * @param projectId projectId
     * @param jobId jobId
     * @return JobMetrics
     * @throws LingoUnauthorizedException
     */
    public JobMetrics getProjectJobMetrics(String accessToken, long projectId, long jobId) throws LingoUnauthorizedException {
        final String url = String.format("/projects/%d/jobs/%d/metrics", projectId, jobId);
        return get(accessToken, url, JobMetrics.class);
    }

    /**
     * Returns prices for a Lingo24 job.
     * @param accessToken accessToken
     * @param projectId projectId
     * @param jobId jobId
     * @return Price
     * @throws LingoUnauthorizedException
     */
    public Price getProjectJobPrice(String accessToken, long projectId, long jobId) throws LingoUnauthorizedException {
        final String url = String.format("/projects/%d/jobs/%d/price", projectId, jobId);
        return get(accessToken, url, Price.class);
    }
}
