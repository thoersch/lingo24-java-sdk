package com.thoersch.lingo24;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.thoersch.lingo24.representations.ApiInformation;
import com.thoersch.lingo24.representations.ApiStatus;
import com.thoersch.lingo24.representations.OauthToken;
import com.thoersch.lingo24.representations.Service;
import org.json.JSONArray;
import java.io.IOException;
import java.util.*;

public class LingoClient {
    private static final Double VERSION = 1.0;

    private static final String USER_AGENT = "User-Agent";
    private static final String USER_AGENT_VALUE_FORMAT = "LingoClient - Java Version: %f";
    private static final String BASE_SANDBOX_URL = "https://api-demo.lingo24.com/docs/v1";
    private static final String BASE_URL = "https://api.lingo24.com/docs/v1";
    private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_SECRET = "client_secret";
    private static final String GRANT_TYPE = "grant_type";
    private static final String REDIRECT_URI = "redirect_uri";
    private static final String REFRESH_TOKEN = "refresh_token";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";
    private static final String AUTHORIZATION_KEY = "Authorization";
    private static final String AUTHORIZATION_VALUE_FORMAT = "Bearer %s";

    private final String refreshToken;
    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;
    private final boolean isSandbox;

    public LingoClient(String refreshToken, String redirectUri, String clientId, String clientSecret, boolean isSandbox) {
        this.refreshToken = refreshToken;
        this.redirectUri = redirectUri;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.isSandbox = isSandbox;

        if(refreshToken == null || redirectUri == null || clientId == null || clientSecret == null) {
            throw new LingoException("All configuration values required.");
        }

        initializeHttp();
    }

    public ApiInformation getApiInformation() {
        final String url = getBaseUrl() + "/";
        try {
            HttpResponse<ApiInformation> response = Unirest.get(url).headers(getHeaders()).asObject(ApiInformation.class);
            return response.getBody();
        } catch (Exception e) {
            throw new LingoException(e);
        }
    }

    public ApiStatus getApiStatus() {
        final String url = getBaseUrl() + "/status";
        try {
            HttpResponse<ApiStatus> response = Unirest.get(url).headers(getHeaders()).asObject(ApiStatus.class);
            return response.getBody();
        } catch (Exception e) {
            throw new LingoException(e);
        }
    }

    public OauthToken getAccessToken() {
        final String url = getBaseUrl() + "/oauth2/access";
        final Map<String, Object> queryStrings = new HashMap<String, Object>() {{
            put(CLIENT_ID, clientId);
            put(CLIENT_SECRET, clientSecret);
            put(REDIRECT_URI, redirectUri);
            put(GRANT_TYPE, REFRESH_TOKEN);
            put(REFRESH_TOKEN, refreshToken);
        }};

        try {
            HttpResponse<OauthToken> response = Unirest.post(url).queryString(queryStrings).headers(getHeaders()).asObject(OauthToken.class);
            OauthToken token = response.getBody();
            return token;
        } catch (Exception e) {
            throw new LingoException(e);
        }
    }

    public List<Service> getServices(String accessToken) {
        final String url = getBaseUrl() + "/services";

        try {
            HttpResponse<JsonNode> response = Unirest.get(url).headers(getHeadersWithAuthorization(accessToken)).asJson();
            JSONArray jsonResponse = response.getBody().getObject().getJSONArray("content");
            return getObjectMapper().readValue(jsonResponse.toString(), new TypeReference<ArrayList<Service>>() {});
        } catch (Exception e) {
            throw new LingoException(e);
        }
    }

    public Service getServiceById(String accessToken, long id) {
        final String url = getBaseUrl() + "/services/{id}";

        try {
            HttpResponse<Service> response = Unirest.get(url).headers(getHeadersWithAuthorization(accessToken)).routeParam("id", Long.toString(id)).asObject(Service.class);
            return response.getBody();
        } catch (Exception e) {
            throw new LingoException(e);
        }
    }

    private String getBaseUrl() {
        return isSandbox ? BASE_SANDBOX_URL : BASE_URL;
    }

    private Map<String, String> getHeaders() {
        return new HashMap<String, String>() {{
            put(CONTENT_TYPE, APPLICATION_JSON);
            put(USER_AGENT, String.format(USER_AGENT_VALUE_FORMAT, VERSION));
        }};
    }

    private Map<String, String> getHeadersWithAuthorization(String accessToken) {
        Map<String, String> defaultHeaders = getHeaders();
        defaultHeaders.put(AUTHORIZATION_KEY, String.format(AUTHORIZATION_VALUE_FORMAT, accessToken));
        return defaultHeaders;
    }

    private com.fasterxml.jackson.databind.ObjectMapper getObjectMapper() {
        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        return mapper;
    }

    private void initializeHttp() {
        Unirest.setObjectMapper(new ObjectMapper() {

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return getObjectMapper().readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public String writeValue(Object value) {
                try {
                    return getObjectMapper().writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
