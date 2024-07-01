package com.example.projetogestaofaltas.infra.config;


import com.example.projetogestaofaltas.infra.StringToDateConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    private final StringToDateConverter stringToDateConverter;

    public WebMvcConfig(StringToDateConverter stringToDateConverter) {
        this.stringToDateConverter = stringToDateConverter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToDateConverter);
    }
}

