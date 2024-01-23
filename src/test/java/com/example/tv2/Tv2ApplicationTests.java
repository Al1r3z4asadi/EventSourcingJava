package com.example.tv2;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Tv2ApplicationTests {

	@BeforeEach
	@DisplayName("fuck you")
	@Disabled
	void init(){
		boolean shit = true ;
		assumeTrue(shit) ; // will run the test if shit is true else will not run the test
		// for example keep going if version of the event is good
		System.out.println(("beforesach"));
	}



	void khareji(){
		System.out.println("khareji");
	}
	@Test
	void contextLoads() {
		System.out.println("mamad");
	}


	@Test
	void ccontextLoads() {
		System.out.println("salam");
	}

}
