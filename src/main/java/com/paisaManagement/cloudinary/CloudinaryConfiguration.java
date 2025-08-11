package com.paisaManagement.cloudinary;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfiguration {

    @Bean
    public Cloudinary cloudinary(){
        final Map<String,String> config=new HashMap<>();
        config.put("cloud_name","dvtr8vpwc");
        config.put("api_key","729253337763344");
        config.put("api_secret","lYQvhR4HO9IBh4fXjtMWByq-CfQ");
        return new Cloudinary(config);
    }
}
