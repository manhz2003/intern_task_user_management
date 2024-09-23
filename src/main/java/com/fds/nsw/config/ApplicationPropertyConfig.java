package com.fds.nsw.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;

import com.fds.nsw.properties.PropKey;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class ApplicationPropertyConfig {

	@Autowired
	private ConfigurableEnvironment env;
	
	@Autowired
	private Environment environment;
	
	@Bean("applicationProperty")
	public Map<String, Object> applicationProperty() {
		PropKey.setKeyMap(new HashMap<>());
		for (PropertySource<?> propertySource : env.getPropertySources()) {
			if (propertySource instanceof EnumerablePropertySource && (propertySource.getName().contains(".properties")
					|| propertySource.getName().contains(".yml"))) {
				for (String key : ((EnumerablePropertySource) propertySource).getPropertyNames()) {
					String value = environment.getProperty(key);
					log.info("key={}, value={}, origin={}", key, propertySource.getProperty(key), value);
					PropKey.getKeyMap().put(key, value);
					
				}
			}
		}
		return PropKey.getKeyMap();
	}

}
