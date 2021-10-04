package com.lightweight.tower.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.serviceregistry.ServiceRegistry;

public class TowerServiceRegistry implements ServiceRegistry<TowerRegistration> {

    private static final Logger LOG = LoggerFactory.getLogger(TowerServiceRegistry.class);

    @Override
    public void register(final TowerRegistration registration) {
        LOG.info("apply register action. host {}", registration.getHost());
    }

    @Override
    public void deregister(final TowerRegistration registration) {
        LOG.info("apply deregister action.");
    }

    @Override
    public void close() {
        LOG.info("apply close action.");

    }

    @Override
    public void setStatus(final TowerRegistration registration, final String status) {
        LOG.info("apply setStatus action.");
    }

    @Override
    public <T> T getStatus(final TowerRegistration registration) {
        LOG.info("apply getStatus action.");
        return null;
    }
}
