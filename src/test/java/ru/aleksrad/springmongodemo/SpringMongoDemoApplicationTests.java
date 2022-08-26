package ru.aleksrad.springmongodemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.aleksrad.springmongodemo.service.MongoRepositoryService;
import ru.aleksrad.springmongodemo.service.MongoTemplateService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringMongoDemoApplicationTests {

	@Autowired
	private MongoTemplateService mongoTemplateService;

	@Autowired
	private MongoRepositoryService mongoRepositoryService;

	@Test
	void contextLoads() {
		assertThat(mongoTemplateService).isNotNull();
		assertThat(mongoRepositoryService).isNotNull();
	}

}
