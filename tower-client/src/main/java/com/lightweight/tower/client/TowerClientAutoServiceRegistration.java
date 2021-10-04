package com.lightweight.tower.client;

import org.springframework.cloud.client.serviceregistry.AbstractAutoServiceRegistration;
import org.springframework.cloud.client.serviceregistry.AutoServiceRegistrationProperties;
import org.springframework.cloud.client.serviceregistry.ServiceRegistry;
import org.springframework.core.env.Environment;

public class TowerClientAutoServiceRegistration extends AbstractAutoServiceRegistration<TowerRegistration> {

    private final TowerRegistration towerRegistration;

    public TowerClientAutoServiceRegistration(final ServiceRegistry<TowerRegistration> serviceRegistry,
                                              final AutoServiceRegistrationProperties properties,
                                              final Environment environment) {
        super(serviceRegistry, properties);
        towerRegistration = new TowerRegistration(environment);
    }

    @Override
    protected Object getConfiguration() {
        return null;
    }

    @Override
    protected boolean isEnabled() {
        return getContext()
            .getEnvironment()
            .getProperty(
                "spring.cloud.service-registry.auto-registration.enabled",
                Boolean.class,
                Boolean.TRUE
            );
    }

    @Override
    protected TowerRegistration getRegistration() {
        return towerRegistration;
    }

    @Override
    protected TowerRegistration getManagementRegistration() {
        return towerRegistration;
    }
}
