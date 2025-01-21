package com.ict.esg.unit.configuration;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Field;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@EnableConfigurationProperties(Myconfig.class)
@SpringBootTest
class CloudConfigTest {

  @Autowired
  private Myconfig myconfig;


  @Test
  @DisplayName("Config Server 연동 테스트")
  void getConfig() throws IllegalAccessException {
    Field[] fields = myconfig.getClass().getSuperclass().getDeclaredFields();
    // 리플렉션으로 필드 이름을 가져옴
    for (Field field : fields) {
      // 접근 제한자 등의 이유로 접근이 안되는 경우 강제로 접근 허용
      field.setAccessible(true);
      Object value = field.get(myconfig);

      log.info("name : {}, value : {}", field.getName(), value);
      // 필드의 값에 null 이 있으면 실패
      assertThat(value).isNotNull();
    }
  }
}
