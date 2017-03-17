/**
 * 
 */
package com.weimob.proxy.config;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
@EnableWebMvc
@ComponentScan(value = { "com.weimob.proxy" })
@PropertySource("classpath:/api_proxy_config.properties")
public class ApiProxyApplication extends WebMvcConfigurerAdapter {

	@Autowired
	private @Getter Environment env;

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

		converters.add(new StringHttpMessageConverter());
		converters.add(createJsonHttpMessageConverter());
	}

	@Bean
	public ObjectMapper objectMapper() {

		ObjectMapper jacksonObjectMapper = new ObjectMapper();
		jacksonObjectMapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
		jacksonObjectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		jacksonObjectMapper.setConfig(jacksonObjectMapper.getSerializationConfig()
				.with(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")).with(TimeZone.getDefault()));
		jacksonObjectMapper.setConfig(jacksonObjectMapper.getDeserializationConfig()
				.with(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")).with(TimeZone.getDefault()));
		jacksonObjectMapper.setSerializationInclusion(Include.ALWAYS);

		jacksonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		// jacksonObjectMapper.setPropertyNamingStrategy(new
		// JSONPropertyNamingStrategy());
		return jacksonObjectMapper;
	}

	private HttpMessageConverter<Object> createJsonHttpMessageConverter() {

		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		jsonConverter.setObjectMapper(objectMapper());
		return jsonConverter;
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(false).favorParameter(true).parameterName("mediaType").ignoreAcceptHeader(true)
				.useJaf(false).defaultContentType(MediaType.APPLICATION_JSON)
				.mediaType("json", MediaType.APPLICATION_JSON);
	}

}
