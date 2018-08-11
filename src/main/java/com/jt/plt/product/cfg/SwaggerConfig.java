package com.jt.plt.product.cfg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements EnvironmentAware {

	private final Logger logger = LoggerFactory.getLogger(SwaggerConfig.class);
	private static final String DEFAULT_INCLUDE_PATTERN = "/.*";
	private RelaxedPropertyResolver propertyResolver;
	@Override
	public void setEnvironment(Environment environment) {
		this.propertyResolver = new RelaxedPropertyResolver(environment, "swagger.");
	}

	@Bean
	public Docket swaggerSpringfoxDocket() {
		logger.debug("Starting Swagger");
		StopWatch watch = new StopWatch();
		watch.start();
		@SuppressWarnings("unchecked")
		Docket swaggerSpringMvcPlugin = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
				.genericModelSubstitutes(ResponseEntity.class).select().paths(Predicates.or(
                //这里添加你需要展示的接口
                        PathSelectors.ant("/productdetailsvc/**"),
                        PathSelectors.ant("/product_prog/**"),
                        PathSelectors.ant("/product/**"),
                        PathSelectors.ant("/product_factor/**"),
                        PathSelectors.ant("/product_rule/**")
                                    )
						)
				.build().securitySchemes(Collections.singletonList(apiKey()))
				.groupName("G1")
				.securityContexts(Collections.singletonList(securityContext()));
		watch.stop();
		logger.debug("Started Swagger in {} ms", watch.getTotalTimeMillis());
		return swaggerSpringMvcPlugin;
	}

	private ApiInfo apiInfo() {
		logger.info("api docs title : " + propertyResolver.getProperty("title"));
		return new ApiInfo(propertyResolver.getProperty("title"), propertyResolver.getProperty("description"),
				propertyResolver.getProperty("version"), propertyResolver.getProperty("termsOfServiceUrl"),
				new Contact("", "", ""), propertyResolver.getProperty("license"),
				propertyResolver.getProperty("licenseUrl"), new ArrayList<>());
	}

	private ApiKey apiKey() {
		return new ApiKey("access_token", "Authorization", "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth())
				.forPaths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN)).build();
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		return Collections
				.singletonList(new SecurityReference("access_token", new AuthorizationScope[] { authorizationScope }));
	}
}
