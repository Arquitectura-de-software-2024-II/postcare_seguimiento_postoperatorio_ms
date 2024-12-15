package com.postcare.seguimiento_postoperatorio_ms;

import com.postcare.seguimiento_postoperatorio_ms.testconfig.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestConfig.class)
@SpringBootTest
class SeguimientoPostoperatorioMsApplicationTests {

	@Test
	void contextLoads() {
	}

}
