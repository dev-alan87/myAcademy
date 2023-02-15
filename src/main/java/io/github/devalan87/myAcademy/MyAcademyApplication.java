package io.github.devalan87.myAcademy;

import io.github.devalan87.myAcademy.domain.entity.enums.UserType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class MyAcademyApplication
		extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MyAcademyApplication.class, args);
	}

}
