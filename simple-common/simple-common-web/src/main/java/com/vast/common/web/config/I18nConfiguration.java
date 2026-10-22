package com.vast.common.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Configuration
public class I18nConfiguration {
    @Bean
    public LocaleResolver localeResolver() {
        return new LocaleResolver() {
            @Override
            public Locale resolveLocale(HttpServletRequest request) {
                String lang = request.getHeader("X-Lang");
                if (StringUtils.hasText(lang)) {
                    return Locale.forLanguageTag(lang.replace('_', '-'));
                }
                return Locale.SIMPLIFIED_CHINESE;
            }

            @Override
            public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
            }
        };
    }
}
