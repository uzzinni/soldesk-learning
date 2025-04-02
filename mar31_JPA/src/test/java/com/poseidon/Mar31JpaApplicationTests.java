package com.poseidon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// 2025-04-02 웹 시스템 구축 junit
/*
 * junit?
 *    java에서 독립된 단위(unit test)테스트를 지원해주는 프레임워크
 *    단정 메소드로 테스트 케이스의 수행 결과를 판정할 수 있다.
 *      단정문(assert) : 예) assertEquals(예상값, 실제값)
 */
@SpringBootTest
class Mar31JpaApplicationTests {

   // @BeforeAll  : 메소드가 시작되기 전에 실행되어야 하는 메소드들(static)
   // @BeforeEach : 메소드가 시작되기 전에 실행되어야 하는 메소드들 
   @BeforeEach
   void before() {
      System.err.println("테스트가 실행되기 전에 시작됩니다.");
   }
   
   @AfterEach
   void after() {
      System.err.println("테스트가 실행된 후 표시될 메소드");
   }
   
   @Test
   @DisplayName("단위 테스트 중입니다.")
   void contextLoads() {
      // 똑같은 값인지 비교하기
      //int num = 100;
      //int num2 = 111;
      String str = "hi";
      String str2 = "hi2";
      assertEquals(str, str2);
   }
   
   

}
