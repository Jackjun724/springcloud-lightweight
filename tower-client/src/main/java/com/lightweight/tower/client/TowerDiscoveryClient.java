package com.lightweight.tower.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

public class TowerDiscoveryClient implements DiscoveryClient {

    private final List<DiscoveryClient> discoveryClients = new ArrayList<>();

    public TowerDiscoveryClient() {
        // start demon thread to fetch other service
    }

    @Override
    public String description() {
        return "Composite Discovery Client";
    }

    @Override
    public List<ServiceInstance> getInstances(String serviceId) {
        if (!this.discoveryClients.isEmpty()) {
            for (DiscoveryClient discoveryClient : this.discoveryClients) {
                List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
                if (instances != null && !instances.isEmpty()) {
                    return instances;
                }
            }
        }
        return Collections.emptyList();
    }

    @Override
    public List<String> getServices() {
        LinkedHashSet<String> services = new LinkedHashSet<>();
        if (!this.discoveryClients.isEmpty()) {
            for (DiscoveryClient discoveryClient : this.discoveryClients) {
                List<String> serviceForClient = discoveryClient.getServices();
                if (serviceForClient != null) {
                    services.addAll(serviceForClient);
                }
            }
        }
        return new ArrayList<>(services);
    }

    public List<DiscoveryClient> getDiscoveryClients() {
        return this.discoveryClients;
    }
}
