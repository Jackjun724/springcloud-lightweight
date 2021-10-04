package com.lightweight.tower.client;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.core.env.Environment;

public class TowerRegistration implements Registration {

    private static final Logger LOG = LoggerFactory.getLogger(TowerRegistration.class);
    private final Environment environment;
    private final String instanceId;

    public TowerRegistration(Environment environment) {
        this.environment = environment;
        this.instanceId = UUID.randomUUID().toString();
    }

    @Override
    public String getInstanceId() {
        return instanceId;
    }

    @Override
    public String getServiceId() {
        return environment.getProperty("spring.application.name", "defaultApplicationService");
    }

    @Override
    public String getHost() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            LOG.error("Unknown host, by default localhost.");
            return "localhost";
        }
    }

    @Override
    public int getPort() {
        return environment.getProperty("server.port", Integer.class, 8080);
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public URI getUri() {
        return null;
    }

    @Override
    public Map<String, String> getMetadata() {
        return null;
    }

    @Override
    public String getScheme() {
        return "tower";
    }
}
