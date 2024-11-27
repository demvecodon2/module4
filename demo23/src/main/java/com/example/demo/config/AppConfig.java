package com.example.demo.config;

import jakarta.persistence.Entity;
import jakarta.websocket.Session;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.nio.charset.CharacterCodingException;
import java.util.Locale;

public class AppConfig implements WebMvcConfigurer {
    @Bean
    public LocaleChangeInterceptor locakeChangeinterceptor(){
        LocaleChangeInterceptor localeChangeinterceptor = new LocaleChangeInterceptor();
        localeChangeinterceptor.setParamName("lang");
        return localeChangeinterceptor;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(locakeChangeinterceptor());

    }
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasename("classpath: messages");
        return messageSource;
    }
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.ENGLISH);
        return sessionLocaleResolver;


//        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
//        cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);
//        cookieLocaleResolver.setCookieName("myLocaleCookie");
//        cookieLocaleResolver.setCookieMaxAge(3600);
//        return cookieLocaleResolver;
    }

}
