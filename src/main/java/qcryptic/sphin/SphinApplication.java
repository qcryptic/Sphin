package qcryptic.sphin;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SphinApplication {

    @Value("${allow.remote.db.connection}")
    private boolean allowRemoteDb;

	public static void main(String[] args) {
		SpringApplication.run(SphinApplication.class, args);
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
