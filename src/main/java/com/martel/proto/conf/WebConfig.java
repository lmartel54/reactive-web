package com.martel.proto.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableWebFlux
public class WebConfig implements /*ApplicationContextAware,*/ WebFluxConfigurer {

//	private ApplicationContext ctx;
//
//	@Override
//	public void setApplicationContext(ApplicationContext context) {
//		this.ctx = context;
//	}
//
//	@Bean
//	RouterFunction<ServerResponse> staticResourceRouter(){
//	    return RouterFunctions.resources("/**", new ClassPathResource("static/"));
//	}
//	
//	@Bean
//	public SpringResourceTemplateResolver thymeleafTemplateResolver() {
//		final SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
//		resolver.setApplicationContext(this.ctx);
//		resolver.setPrefix("classpath:/templates/");
//		resolver.setSuffix(".html");
//		resolver.setTemplateMode(TemplateMode.HTML);
//		resolver.setCacheable(false);
//		resolver.setCheckExistence(false);
//		return resolver;
//	}
//
//	@Bean
//	public ISpringWebFluxTemplateEngine thymeleafTemplateEngine() {
//		// We override her the SpringTemplateEngine instance that would otherwise be instantiated by
//		// Spring Boot because we want to apply the SpringWebFlux-specific context factory, link builder...
//		final SpringWebFluxTemplateEngine templateEngine = new SpringWebFluxTemplateEngine();
//		templateEngine.setTemplateResolver(thymeleafTemplateResolver());
//		return templateEngine;
//	}
//
//	@Bean
//	public ThymeleafReactiveViewResolver thymeleafChunkedAndDataDrivenViewResolver() {
//		final ThymeleafReactiveViewResolver viewResolver = new ThymeleafReactiveViewResolver();
//		viewResolver.setTemplateEngine(thymeleafTemplateEngine());
////        viewResolver.setOrder(1);
////        viewResolver.setViewNames(new String[]{"home"});
//		viewResolver.setResponseMaxChunkSizeBytes(8192); // OUTPUT BUFFER size limit
//		return viewResolver;
//	}
//
//	@Override
//	public void configureViewResolvers(ViewResolverRegistry registry) {
//		registry.viewResolver(thymeleafChunkedAndDataDrivenViewResolver());
//	}
}
