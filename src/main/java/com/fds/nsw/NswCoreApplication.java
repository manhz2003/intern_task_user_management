package com.fds.nsw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScan({"com.fds.nsw.*", "vn.gt.*"})
public class NswCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(NswCoreApplication.class, args);
	}

}
