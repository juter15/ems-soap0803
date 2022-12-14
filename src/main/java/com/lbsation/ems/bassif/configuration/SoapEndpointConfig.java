package com.lbsation.ems.bassif.configuration;

import com.lbsation.ems.bassif.service.EmsServiceEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;

import javax.xml.ws.Endpoint;

@Configuration
public class SoapEndpointConfig {
    @Bean
    public Endpoint endpoint(SpringBus bus) {
        EndpointImpl endpoint = new EndpointImpl(bus, new EmsServiceEndpoint());
        endpoint.publish("");
        return endpoint;
    }
}
