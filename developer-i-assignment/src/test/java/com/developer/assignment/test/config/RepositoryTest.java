package com.developer.assignment.test.config;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * `@DataJpaTest`
 *   JPA 설정 영역만 로드한다.
 *   `@SpringBootTest` 사용시 설정을 두 번 읽어 에러 발생함
 *
 */
@DataJpaTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles(ActiveProfile.TEST)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryTest {

}
