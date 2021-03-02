package ru.job4j.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import ru.job4j.forum.filter.AuthenticateFilter;

@SpringBootApplication
public class Main extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Main.class);
    }

    @Bean
    public FilterRegistrationBean<AuthenticateFilter> loggingFilter() {
        FilterRegistrationBean<AuthenticateFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new AuthenticateFilter());
        registrationBean.addUrlPatterns("/**");

        return registrationBean;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}