**Java Client SDK for Lingo24's API**
----
  All calls start after you have connected your developer API account to the Ease customer portal in Lingo and that you 
  construct the client with required information from that oauth connection. For information on this process, please consult
  the [Lingo24 official documentation](https://developer.lingo24.com/documentation).
  
## Installation: ##

    <dependency>
        <groupId>com.tylerhoersch.lingo24</groupId>
        <artifactId>lingo24</artifactId>
        <version>1.1</version>
    </dependency>
    

## Sample Usage: ##
      
    LingoConfiguration config = new LingoConfiguration.Builder().clientId("clientId").clientSecret("secret").redirectUri("localhost").refreshToken("refresh").isSandbox(true).build();
    
    LingoClient lingoClient = new LingoClient(config);
    
    String accessToken = lingoClient.getAccessToken();
    
    List<Project> projects = getProjects(accessToken);
 


## LingoClient: ##
  
**Constructor**
        
A builder exists for LingoInfo object 

**Required:**
 
    lingoInfo=[LingoInfo]

   **Sample Call:**

    new LingoClient(new LingoConfiguration.Builder().clientId("clientId").clientSecret("secret").isSandbox(true).redirectUri("localhost").refreshToken("refresh").build());


   **Sandbox:**
   
   For testing purposes, use the `.isSandbox(true)` flag as part of the configuration of the lingo client.

## Individual API Methods ##

    ApiInformation getApiInformation();
    
    ApiStatus getApiStatus();
    
    OauthToken getAccessToken();
    
    List<Service> getServices(String accessToken);
    
    List<Service> getServices(String accessToken, PagingInput pagingInput);
    
    Service getServiceById(String accessToken, long serviceId);
    
    List<Locale> getLocales(String accessToken);
    
    List<Locale> getLocales(String accessToken, PagingInput pagingInput);
    
    Locale getLocaleById(String accessToken, long localeId);
    
    List<Domain> getDomains(String accessToken);
    
    List<Domain> getDomains(String accessToken, PagingInput pagingInput);
    
    Domain getDomainById(String accessToken, long domainId);
    
    File createFile(String accessToken, String fileName);
    
    File getFileById(String accessToken, long fileId);
    
    void deleteFileById(String accessToken, long fileId);
    
    String getFileContent(String accessToken, long fileId);
    
    void updateFileContent(String accessToken, long fileId, String content);
    
    List<Project> getProjects(String accessToken);
    
    List<Project> getProjects(String accessToken, PagingInput pagingInput);
    
    Project createProject(String accessToken, Project project);
    
    Project getProjectById(String accessToken, long projectId);
    
    void deleteProjectById(String accessToken, long projectId);
    
    Project updateProjectById(String accessToken, long projectId, Project project);
    
    List<File> getProjectFiles(String accessToken, long projectId);
    
    List<File> getProjectFiles(String accessToken, long projectId, PagingInput pagingInput);
    
    void addFileToProject(String accessToken, long projectId, File file);
    
    void deleteProjectFileById(String accessToken, long projectId, long fileId);
    
    List<Job> getJobsForProject(String accessToken, long projectId);
    
    List<Job> getJobsForProject(String accessToken, long projectId, PagingInput pagingInput);
    
    Job addJobToProject(String accessToken, long projectId, Job job);
    
    void deleteJobById(String accessToken, long projectId, long jobId);
    
    Job getJobById(String accessToken, long projectId, long jobId);
    
    List<File> getFilesInJob(String accessToken, long projectId, long jobId);
    
    List<File> getFilesInJob(String accessToken, long projectId, long jobId, PagingInput pagingInput);
    
    JobMetrics getProjectJobMetrics(String accessToken, long projectId, long jobId);
    
    Price getProjectJobPrice(String accessToken, long projectId, long jobId);
    
    
## Pagination: ##
  
  Pagination controls are available on all API methods that return a collection of entities. If `PagingInput` is not specified, the default pagination is applied which consists of `pageSize=10`, `pageNumber=0`, and no sort order.

## Notes: ##

  * *Disclaimer*: This is a community project and has no direct ties to Lingo24
  * Please track any and all issues in the github issues tracker
  * Contributions via pull requests are welcome!
