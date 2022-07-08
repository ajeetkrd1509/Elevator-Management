package com.hcl.elevator.profiles;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevDatasourceConfig implements DataSourceConfig {

    @Override
    public void setup() {
        System.out.println("Setting up datasource for DEV environment. ");
    }
    @PostConstruct
    public void test() {
    	System.out.println("Loaded DEV Profile");
    }

}