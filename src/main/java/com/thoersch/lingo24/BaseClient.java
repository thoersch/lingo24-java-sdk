package com.thoersch.lingo24;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.thoersch.lingo24.representations.PagingInput;
import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseClient {
    private static final String USER_AGENT = "User-Agent";
    private static final String USER_AGENT_VALUE_FORMAT = "LingoClient - Java Version: %f";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";
    private static final String AUTHORIZATION_KEY = "Authorization";
    private static final String AUTHORIZATION_VALUE_FORMAT = "Bearer %s";
    private static final String CONTENT = "content";
    private double version;

    public BaseClient(double version) {
        this.version = version;
    }

    protected abstract String getBaseUrl();

    protected PagingInput getDefaultPagingInput() {
        return new PagingInput(10, 0, null);
    }

    private String getUrl(String url) {
        String result;

        if (url.startsWith("http")) {
            return url;
        }

        result = getBaseUrl();

        if (!url.startsWith("/") && !result.endsWith("/")) {
            url = "/" + url;
        }

        result += url;

        return result;
    }

    protected <T> T get(String accessToken, String url, Class<T> clazz) throws LingoUnauthorizedException {
        try {
            HttpResponse<T> response = Unirest.get(getUrl(url)).headers(getHeadersWithAuthorization(accessToken)).asObject(clazz);
            handleErrorResponse(response);
            return response.getBody();
        } catch (LingoException | UnirestException e) {
            throw new LingoException(e);
        }
    }

    protected <T> List<T> getList(String accessToken, String url, PagingInput pagingInput, Class<T> clazz) throws LingoUnauthorizedException {
        try {

            HttpResponse<JsonNode> response = Unirest.get(getUrl(url)).headers(getHeadersWithAuthorization(accessToken)).queryString(getPagingMap(pagingInput)).asJson();
            handleErrorResponse(response);
            JSONArray jsonResponse = response.getBody().getObject().getJSONArray(CONTENT);
            TypeFactory t = TypeFactory.defaultInstance();
            return getObjectMapper().readValue(jsonResponse.toString(), t.constructCollectionType(ArrayList.class, clazz));
        } catch (LingoException | UnirestException | IOException e) {
            throw new LingoException(e);
        }
    }

    protected <T> T create(String accessToken, String url, T payload, Class<T> clazz) throws LingoUnauthorizedException {
        try {
            HttpResponse<T> response = Unirest.post(getUrl(url)).headers(getHeadersWithAuthorization(accessToken)).body(payload).asObject(clazz);
            handleErrorResponse(response);
            return response.getBody();
        } catch (LingoException | UnirestException e) {
            throw new LingoException(e);
        }
    }

    protected void create(String accessToken, String url, Object payload) throws LingoUnauthorizedException {
        try {
            HttpResponse<JsonNode> response = Unirest.post(getUrl(url)).headers(getHeadersWithAuthorization(accessToken)).body(payload).asJson();
            handleErrorResponse(response);
        } catch (LingoException | UnirestException e) {
            throw new LingoException(e);
        }
    }

    protected <T> T create(String accessToken, String url, Object payload, Map<String, Object> queryStringMap, Class<T> clazz) throws LingoUnauthorizedException {
        try {
            HttpResponse<T> response = Unirest.post(getUrl(url)).headers(getHeadersWithAuthorization(accessToken)).queryString(queryStringMap).body(payload).asObject(clazz);
            handleErrorResponse(response);
            return response.getBody();
        } catch (LingoException | UnirestException e) {
            throw new LingoException(e);
        }
    }

    protected <T> T update(String accessToken, String url, Object payload, Class<T> clazz) throws LingoUnauthorizedException {
        try {
            HttpResponse<T> response = Unirest.put(getUrl(url)).headers(getHeadersWithAuthorization(accessToken)).body(payload).asObject(clazz);
            handleErrorResponse(response);
            return response.getBody();
        } catch (LingoException | UnirestException e) {
            throw new LingoException(e);
        }
    }

    protected void delete(String accessToken, String url) throws LingoUnauthorizedException {
        try {
            HttpResponse<JsonNode> response = Unirest.delete(getUrl(url)).headers(getHeadersWithAuthorization(accessToken)).asJson();
            handleErrorResponse(response);
        } catch (LingoException | UnirestException e) {
            throw new LingoException(e);
        }
    }

    protected void initializeHttp() {
        Unirest.setObjectMapper(new ObjectMapper() {

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return getObjectMapper().readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return getObjectMapper().writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private Map<String, String> getHeaders() {
        return new HashMap<String, String>() {{
            put(CONTENT_TYPE, APPLICATION_JSON);
            put(USER_AGENT, String.format(USER_AGENT_VALUE_FORMAT, version));
        }};
    }

    private Map<String, String> getHeadersWithAuthorization(String accessToken) {
        Map<String, String> defaultHeaders = getHeaders();
        if (accessToken != null) {
            defaultHeaders.put(AUTHORIZATION_KEY, String.format(AUTHORIZATION_VALUE_FORMAT, accessToken));
        }
        return defaultHeaders;
    }

    private com.fasterxml.jackson.databind.ObjectMapper getObjectMapper() {
        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        mapper.enable( DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        return mapper;
    }

    private Map<String, Object> getPagingMap(PagingInput pagingInput) {
        Map<String, Object> result = new HashMap<String, Object>();

        if (pagingInput == null) {
            return result;
        }

        int pageSize = pagingInput.getSize() > 0 ? pagingInput.getSize() : 10;
        result.put("size", pageSize);

        int page = pagingInput.getPage() > 0 ? pagingInput.getPage() : 0;
        result.put("page", page);

        if (pagingInput.getSort() != null && pagingInput.getSort().trim().length() > 0) {
            result.put("sort", pagingInput.getSort());
        }

        return result;
    }

    private void handleErrorResponse(HttpResponse response) throws LingoUnauthorizedException {
        if(response.getStatus() >= 401 && response.getStatus() <= 403) {
            throw new LingoUnauthorizedException(response.getStatusText());
        } else if(response.getStatus() >= 400) {
            throw new LingoException(response.getStatusText());
        }
    }
}
