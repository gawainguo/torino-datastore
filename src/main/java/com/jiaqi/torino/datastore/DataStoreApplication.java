package com.jiaqi.torino.datastore;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.jiaqi.torino.datastore.dao")
@SpringBootApplication
public class DataStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataStoreApplication.class, args);
	}

	@PostConstruct
	private void setSystemProperties() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
}
