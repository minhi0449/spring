package com.tenco.mustache.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/*
    Mustache 문법 학습을 위한 단계별 예제
 */
@Controller
@RequestMapping("/mustache") // 대문 달기 - 각 메서드에서 공통 사용
public class Exam2MustacheController {
    /*
        1. 기본 변수 출력 ({{key}}) 학습
        URL : http://localhost:8080/mustache/basic-variables
     */
    @GetMapping("/basic-variables")
    public String basicVariables(Model model){
        // 다양한 데이터 타입의 기본 변수들
        model.addAttribute("key", "key");
        model.addAttribute("key", "key");
        model.addAttribute("pageTitle", "기본 변수 출력 학습");
        model.addAttribute("message", "안녕하세요, Mustache입니다!");
        model.addAttribute("userName", "김개발자");
        model.addAttribute("userAge", 28);
        model.addAttribute("userScore", 95.5);
        model.addAttribute("isActive", true);
        model.addAttribute("currentDate", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        return "examples/basic1";
    }
    /*
        2. HTML 이스케이프 해제 처리 ({{key}})
        이스케이프 처리 ({{key}}):
        HTML 태그 (예: <strong>) 를 특수문자(예: &lt;strong&gt;) 로 변환해 텍스트로만 보여줍니다.
        이렇게 하면 태그가 실행되지 않아 보안 위협(예: XSS 공격)을 방지할 수 있어요.

        이스케이프 해제 ({{{key}}}) : 태그를 그대로 렌더링해서 HTML로 해석합니다.
        URL : http://localhost:8080/mustache/html-escape
     */
    @GetMapping("/html-escape")
    public String htmlEscape(Model model){
        model.addAttribute("key", "key");
        model.addAttribute("pageTitle", "HTML 이스케이프 개념 학습");

        // 이스케이프 처리 비교용 데이터
        model.addAttribute("plainText", "<strong>굵은 글씨</strong>");
        model.addAttribute("htmlContent", "<div class='alert alert-success'><strong>성공!</strong> HTML이 정상 렌더링 됩니다.</div>");
        model.addAttribute("linkHtml", "<a href='https://spring.io' target='_blank'>Spring 공식 사이트</a>");

        // 보안 위험 예시 (실제로는 사용하지 말 것)
        model.addAttribute("scriptTag", "<script>alert('XSS 테스트 - 위험!');</script>");
        return "examples/basic2";
    }

    /*
        3. 조건부 섹션({{#key}} ... {{/key}}) 학습
        URL : http://localhost:8080/mustache/conditional-sections
     */
    @GetMapping("/conditional-sections")
    public String conditionalSections(Model model){
        model.addAttribute("key", "key");
        model.addAttribute("pageTitle", "조건부 섹션 학습");

        // truthy/falsy 값들
        // 단, 구현체에 따라(자바) falsy 값이 다를 수 있음 ** 빈 문자열, 0 ** 은 truthy 로 판별됨
        model.addAttribute("hasPermission", true);  // true
        model.addAttribute("isLoggedIn", false);    // false
        model.addAttribute("adminRole", "ADMIN");   // 문자열 (thruthy)
        model.addAttribute("emptyString", "");      // 빈 문자열
        model.addAttribute("nullValue", null);      // null (falsy)
        model.addAttribute("zeroValue", 0);         // 0 (truthy)
        model.addAttribute("nonZeroValue", 42);     // 0이 아닌 숫자 (truthy)

        // 실제 사용 예시
        model.addAttribute("userRole", "USER");
        model.addAttribute("isVip", true);
        model.addAttribute("balance", 50_000);

        return "examples/basic3";
    }

    /*
        4. 부정 섹션 ({{^key}} .. {{key}}) 학습
        URL : http://localhost:8080/mustache/inverted-sections
     */
    @GetMapping("/inverted-sections")
    public String invertedSections(Model model){
        model.addAttribute("key", "key");
        model.addAttribute("pageTitle", "부정 섹션 학습");

        // 부정 조건 테스트용 데이터
        model.addAttribute("errorMessage", null);   // null (부정 조건 true)
        model.addAttribute("warningMessage", "");   // 빈 문자열 (부정 조건 true)
        model.addAttribute("successMessage", "작업 완료"); // 값 있음 (부정 조건 false)

        // 실제 사용 시나리오
        model.addAttribute("isLoggedOut", false);
        model.addAttribute("hasNoItems", true);
        model.addAttribute("isEmpty", null);

        return "examples/basic4";
    }

    /*
        5. 컬렉션 반복 처리 학습
        URL: http://localhost:8080/mustache/collections
     */
    @GetMapping("/collections")
    public String collections(Model model){
        model.addAttribute("pageTitle", "컬렉션 반복 처리 학습");

        // 단순 문자열 리스트
        List<String> fruits = Arrays.asList("사과", "바나나", "오렌지", "포도", "딸기");
        model.addAttribute("fruits", fruits);

        // 숫자 리스트
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        model.addAttribute("numbers", numbers);

        // 빈 리스트 (부정 조건 테스트용)
        model.addAttribute("emptyList", Arrays.asList());

        return "examples/basic5";
    }

    /*
        6. 부분 템플릿 포함 ({{> partialName}}) 학습
        URL: http://localhost:8080/mustache/partials
     */
    @GetMapping("partials")
    public String partials(Model model){
        model.addAttribute("pageTitle", "부분 템플릿 학습");

        // 부분 템플릿에서 사용한 공통 데이터
        model.addAttribute("siteName", "Mustache 학습 사이트");
        model.addAttribute("currentYear", "2025");
        model.addAttribute("companyName", "코딩 교육센터");

        // 메인 콘텐츠 데이터
        model.addAttribute("mainContent", "부분 템플릿을 활용하여 재사용 가능한 컴포넌트를 만들어 보세요!");

        return "examples/basic6";
    }

}

