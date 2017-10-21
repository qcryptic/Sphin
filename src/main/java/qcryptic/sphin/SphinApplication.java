package qcryptic.sphin;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;

@SpringBootApplication
public class SphinApplication {

    @Value("${allow.remote.db.connection}")
    private boolean allowRemoteDb;

	public static void main(String[] args) {
        String propLoc = System.getProperty("user.home") + File.separator + "Sphin" + File.separator + "sphin.properties";
		new SpringApplicationBuilder(SphinApplication.class).properties("spring.config.location:"+propLoc).build().run(args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
	}

    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/console/*");
        if (allowRemoteDb)
            registration.addInitParameter("webAllowOthers", "true");
        return registration;
    }

}

