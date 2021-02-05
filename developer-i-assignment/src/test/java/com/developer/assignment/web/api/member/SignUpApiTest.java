package com.developer.assignment.web.api.member;

import com.developer.assignment.domain.member.model.*;
import com.developer.assignment.repository.master.MemberMasterRepository;
import com.developer.assignment.test.config.ActiveProfile;
import com.developer.assignment.web.dto.SignUpRequestTestBuilder;
import com.developer.assignment.web.dto.member.request.SignUpRequest;
import com.developer.assignment.web.dto.member.response.MemberResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(ActiveProfile.TEST)
public class SignUpApiTest {

    @LocalServerPort
    private int port;

    @Value("${this.api.url}")
    private String url;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private MemberMasterRepository memberMasterRepository;

    private String getUrl(String directory) {
        return url + ":" + port + directory;
    }

    @BeforeEach
    public void tearDown() {
        memberMasterRepository.deleteAll();
    }

    @DisplayName("회원 가입 검증 :: 이름 :: 한글, 영문 대소문자만 허용 :: 1자 ~ 20자")
    @ParameterizedTest
    @CsvSource({
        /* validate */
        "홍길동", "John", "bobby"

        /* invalidate */
        // "123", "ＡＢＣ", "Mr park", "김수한무거북이와두루미삼천갑자동방삭치치카포사리사리센타워리워리세브리깡무두셀라구름위허리케인담벼락서생원에고양이바둑이는돌돌이"
    })
    public void signUp_validation_name(String name) {
        // given
        SignUpRequest signUpRequest = SignUpRequestTestBuilder.name(Name.of(name));

        // when
        ResponseEntity<MemberResponse> responseEntity = postForEntity(signUpRequest);

        // then
        assertEquals( HttpStatus.OK, responseEntity.getStatusCode() );
    }

    @DisplayName("회원 가입 검증 :: 별명 :: 영문 소문자만 허용 :: 1자 ~ 30자")
    @ParameterizedTest
    @CsvSource({
        /* validate */
        "one", "hello"

        /* invalidate */
        // "홍길동", "Brian", "ＡＢＣ", "eratpellentesqueadipiscingcommodoeliteratpellentesqueadipiscingcommodoelit"
    })
    public void signUp_validation_nickname(String nickName) {
        // given
        SignUpRequest signUpRequest = SignUpRequestTestBuilder.nickName(NickName.of(nickName));

        // when
        ResponseEntity<MemberResponse> responseEntity = postForEntity(signUpRequest);

        // then
        assertEquals( HttpStatus.OK, responseEntity.getStatusCode() );
    }

    @DisplayName("회원 가입 검증 :: 비밀번호 :: 영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함 :: 10자 이상")
    @ParameterizedTest
    @CsvSource({
            /* validate */
            "abcDEF123!@#", "hello1WORLD@", "@O1t@N2w@E3o"

            /* invalidate
             * single : 소문자, 대문자, 숫자, 특수문자
             * duo : 소문자 + 대문자 / 소문자 + 숫자 / 소문자 + 특수문자 / 대문자 + 숫자 / 대문자 + 특수문자 / 숫자 + 특수문자
             * trio : 소문자 + 대문자 + 숫자 / 소문자 + 대문자 + 특수문자 / 소문자 + 숫자 + 특수문자 / 대문자 + 숫자 + 특수문자
             */
//            "aaaaabbbbb", "AAAAABBBBB", "1234567890", "!!!!!@@@@@"
//            "abcdeABCDE", "abcde12345", "abcde!@#$%", "QWERT67890", "QWERT^&*()", "24680!!!!!"
//            "aaaBBB12345", "aaaBBB@@@@@", "bbb246!!!!!", "CCC369$$$$$"
    })
    public void signUp_validation_password(String password) {
        // given
        SignUpRequest signUpRequest = SignUpRequestTestBuilder.password(Password.of(password));

        // when
        ResponseEntity<MemberResponse> responseEntity = postForEntity(signUpRequest);

        // then
        assertEquals( HttpStatus.OK, responseEntity.getStatusCode() );
    }

    @DisplayName("회원 가입 검증 :: 전화번호 :: 숫자 :: 1자 ~ 20자")
    @ParameterizedTest
    @CsvSource({
            /* validate
             * 시작-중간-끝으로 받음
             * 시작 010, 011, 016, 017, 018, 019
             * 중간 3자리, 4자리
             * 끝 4자리
             */
             "010-0000-0000", "011-000-0000", "016-0000-0000", "010-000-0000"

            /* invalidate */
//            "010-0000-000", "02-000-0000", "01000000000"
    })
    public void signUp_validation_phoneNumber(String phoneNumber) {
        // given
        SignUpRequest signUpRequest = SignUpRequestTestBuilder.phoneNumber(PhoneNumber.of(phoneNumber));

        // when
        ResponseEntity<MemberResponse> responseEntity = postForEntity(signUpRequest);

        // then
        assertEquals( HttpStatus.OK, responseEntity.getStatusCode() );
    }

    @DisplayName("회원 가입 검증 :: 이메일 :: 이메일 형식 :: 100자 이하")
    @ParameterizedTest
    @CsvSource({
            /* validate */
            "devpsbin2017@github.com"

            /* invalidate */
//            "devpsbin2017devpsbin2017devpsbin2017devpsbin2017devpsbin2017devpsbin2017devpsbin2017devpsbin2017@github.com"
//            , "devpsbin2017"
//            , "@github.com"
    })
    public void signUp_validation_email(String email) {
        // given
        SignUpRequest signUpRequest = SignUpRequestTestBuilder.email(Email.of(email));

        // when
        ResponseEntity<MemberResponse> responseEntity = postForEntity(signUpRequest);

        // then
        assertEquals( HttpStatus.OK, responseEntity.getStatusCode() );
    }

    @DisplayName("회원 가입 검증 :: 중복 이메일")
    @Test
    public void signUp_validation_duplicateEmail() {
        String email = "devpsbin2017@github.com";

        // given
        SignUpRequest signUpRequest = SignUpRequestTestBuilder.email(Email.of(email));

        // try first signup
        postForEntity(signUpRequest);

        // when (try second signup)
        ResponseEntity<MemberResponse> responseEntity = postForEntity(signUpRequest);

        // then
        assertNotEquals( HttpStatus.OK, responseEntity.getStatusCode() );
    }

    private ResponseEntity<MemberResponse> postForEntity(SignUpRequest signUpRequest) {
        return testRestTemplate.postForEntity(getUrl("/api/v1/member"), signUpRequest, MemberResponse.class);
    }
}
