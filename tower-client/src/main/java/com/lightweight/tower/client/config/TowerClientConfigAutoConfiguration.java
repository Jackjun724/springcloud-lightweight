package com.lightweight.tower.client.config;

import com.lightweight.tower.client.TowerClientAutoServiceRegistration;
import com.lightweight.tower.client.TowerDiscoveryClient;
import com.lightweight.tower.client.TowerRegistration;
import com.lightweight.tower.client.TowerServiceRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.AutoServiceRegistrationProperties;
import org.springframework.cloud.client.serviceregistry.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@EnableConfigurationProperties({
    TowerServerInstanceConfig.class,
    AutoServiceRegistrationProperties.class
})
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(value = {TowerServerInstanceConfig.class})
public class TowerClientConfigAutoConfiguration {

    /**
     * Create bean of AutoServiceRegistration for {@link org.springframework.cloud.client.serviceregistry.AutoServiceRegistrationAutoConfiguration}.
     *
     * The {@link org.springframework.cloud.client.serviceregistry.AbstractAutoServiceRegistration#start} will be
     * process service registration when {@link org.springframework.boot.web.context.WebServerInitializedEvent} event.
     *
     * @param properties  service registration properties
     * @param environment spring application env
     * @return AutoServiceRegistration Bean
     */
    @Bean
    @ConditionalOnProperty(value = "spring.cloud.service-registry.auto-registration.enabled", matchIfMissing = true)
    public TowerClientAutoServiceRegistration
    towerClientAutoServiceRegistration(final AutoServiceRegistrationProperties properties,
                                       final Environment environment) {
        ServiceRegistry<TowerRegistration> towerServiceRegistry = new TowerServiceRegistry();
        return new TowerClientAutoServiceRegistration(towerServiceRegistry, properties, environment);
    }

    @Bean
    public DiscoveryClient startServiceFetch() {
        return new TowerDiscoveryClient();
    }
}
