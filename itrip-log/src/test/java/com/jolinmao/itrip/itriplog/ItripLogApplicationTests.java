package com.jolinmao.itrip.itriplog;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ItripLogApplicationTests {
	private static final Logger LOGGER = LoggerFactory.getLogger(ItripLogApplication.class);

	@Test
	void contextLoads() {
		LOGGER.info("hello");
	}

}
