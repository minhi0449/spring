package com.ch01;

/**
 * 날짜 : 2024.09.02 (월)
 * 이름 : 김민희
 * 내용 : 1장 Spring 개요 및 개발환경 구축
 *
 *  기본설정
 * 1. New UI 설정
 * - File > Settings > Appearance & Behavior > New UI > Enable new UI 체크 > Compact mode 체크 > Show main menu… 체크
 * 2. 대소문자 구분 안하기
 *  - File > Settings > Editor > General > Code Completion > Match case 체크 해제
 * 3. 메서드 기본 접히기 해제
 * - File > Settings > Editor > General > Code Folding > One-line methods 체크 해제
 * 4. 마우스 휠 줌
 *  - File > Settings > Editor > General > Mouse Control > Change font size with… 체크 *
 *
 * 프로젝트 생성
 * - Maven Archetype: maven-archetype-quickstart
 *
 * 라이브러리
 * - spring-context 6.1.12
 * - spring-context만 추가하면 의존하는 관련 다른 dependency를 함께 다운로드/적용
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        System.out.println( "Hello Spring!" );
    }
}
