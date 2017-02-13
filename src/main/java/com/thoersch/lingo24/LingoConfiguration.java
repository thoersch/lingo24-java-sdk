package com.thoersch.lingo24;

public class LingoConfiguration {
    private String refreshToken;
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private boolean isSandbox;

    public LingoConfiguration() { }

    public LingoConfiguration(Builder builder) {
        this.refreshToken = builder.refreshToken;
        this.clientId = builder.clientId;
        this.clientSecret = builder.clientSecret;
        this.redirectUri = builder.redirectUri;
        this.isSandbox = builder.isSandbox;
    }

    public static class Builder {
        private String refreshToken;
        private String clientId;
        private String clientSecret;
        private String redirectUri;
        private boolean isSandbox;

        public Builder refreshToken(String val) {
            this.refreshToken = val;
            return this;
        }

        public Builder clientId(String val) {
            this.clientId = val;
            return this;
        }

        public Builder clientSecret(String val) {
            this.clientSecret = val;
            return this;
        }

        public Builder redirectUri(String val) {
            this.redirectUri = val;
            return this;
        }

        public Builder isSandbox(boolean val) {
            this.isSandbox = val;
            return this;
        }

        public LingoConfiguration build() {
            return new LingoConfiguration(this);
        }
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public boolean isSandbox() {
        return isSandbox;
    }

    public void setIsSandbox(boolean isSandbox) {
        this.isSandbox = isSandbox;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LingoConfiguration)) return false;

        LingoConfiguration lingoInfo = (LingoConfiguration) o;

        if (isSandbox != lingoInfo.isSandbox) return false;
        if (refreshToken != null ? !refreshToken.equals(lingoInfo.refreshToken) : lingoInfo.refreshToken != null)
            return false;
        if (clientId != null ? !clientId.equals(lingoInfo.clientId) : lingoInfo.clientId != null) return false;
        if (clientSecret != null ? !clientSecret.equals(lingoInfo.clientSecret) : lingoInfo.clientSecret != null)
            return false;
        return !(redirectUri != null ? !redirectUri.equals(lingoInfo.redirectUri) : lingoInfo.redirectUri != null);

    }

    @Override
    public int hashCode() {
        int result = refreshToken != null ? refreshToken.hashCode() : 0;
        result = 31 * result + (clientId != null ? clientId.hashCode() : 0);
        result = 31 * result + (clientSecret != null ? clientSecret.hashCode() : 0);
        result = 31 * result + (redirectUri != null ? redirectUri.hashCode() : 0);
        result = 31 * result + (isSandbox ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LingoInfo{" +
                "refreshToken='" + refreshToken + '\'' +
                ", clientId='" + clientId + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", redirectUri='" + redirectUri + '\'' +
                ", isSandbox=" + isSandbox +
                '}';
    }
}
