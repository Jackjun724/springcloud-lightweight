package com.lightweight.tower.client.config;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "tower")
public class TowerServerInstanceConfig {

    private static final String DEFAULT_NAMESPACE = "default";

    @NotEmpty(message = "tower server host must be not empty.")
    private List<String> host;

    private int port = 9724;

    private int heartbeatIntervalMilliseconds = 5000;

    private String namespace = DEFAULT_NAMESPACE;

    /**
     * Seconds of registry fetch interval
     */
    private int registryFetchIntervalSeconds = 30;

    /**
     * TCP timeout
     */
    private int serverReadTimeoutSeconds = 10;

    /**
     * Next retry interval after first failure
     */
    private int afterFailureRetryIntervalSeconds = 10;

    /**
     * First failure, after {@link #afterFailureRetryIntervalSeconds} execute retry,
     * The next interval is {@link #afterFailureRetryIntervalSeconds} * (retryCount ^ retryExponential)
     */
    private int retryExponential = 2;

    public List<String> getHost() {
        return host;
    }

    public void setHost(final List<String> host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(final int port) {
        this.port = port;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(final String namespace) {
        this.namespace = namespace;
    }

    public int getHeartbeatIntervalMilliseconds() {
        return heartbeatIntervalMilliseconds;
    }

    public void setHeartbeatIntervalMilliseconds(final int heartbeatIntervalMilliseconds) {
        this.heartbeatIntervalMilliseconds = heartbeatIntervalMilliseconds;
    }

    public int getRegistryFetchIntervalSeconds() {
        return registryFetchIntervalSeconds;
    }

    public void setRegistryFetchIntervalSeconds(final int registryFetchIntervalSeconds) {
        this.registryFetchIntervalSeconds = registryFetchIntervalSeconds;
    }

    public int getServerReadTimeoutSeconds() {
        return serverReadTimeoutSeconds;
    }

    public void setServerReadTimeoutSeconds(final int serverReadTimeoutSeconds) {
        this.serverReadTimeoutSeconds = serverReadTimeoutSeconds;
    }

    public int getAfterFailureRetryIntervalSeconds() {
        return afterFailureRetryIntervalSeconds;
    }

    public void setAfterFailureRetryIntervalSeconds(final int afterFailureRetryIntervalSeconds) {
        this.afterFailureRetryIntervalSeconds = afterFailureRetryIntervalSeconds;
    }

    public int getRetryExponential() {
        return retryExponential;
    }

    public void setRetryExponential(final int retryExponential) {
        this.retryExponential = retryExponential;
    }
}
