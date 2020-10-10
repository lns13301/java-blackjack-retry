# java-blackjack-retry
- 블랙잭 게임 구현을 관리하기 위한 저장소

##  준비물
- intelliJ or Gradle
- jdk >= 8

## 요구 사항
- Card, Player, Dealer 객체를 활용하여 구현
    - 기본 생성자를 추가할 수 없다.
    - 필드 변수의 접근자를 변경할 수 없다.
    - 필드를 추가할 수 없다.
    - 객체에 예외처리를 추가한다.
- Player 와 Dealer 의 중복코드를 상속으로 제거한다.
- 자바 컨벤션을 지킨다.
- 3항 연산자를 사용하지 않는다.
- else 예약어를 쓰지 않는다.
- 메소드 길이 <= 10
- indent <= 1
- 메소드 인자 수 <= 3

## 구현
1. CardFactory
    - 카드를 섞어주는 기능

2. Input
    - 유저의 게임 진행을 입력을 처리하는 기능

3. Output
    - 게임 관련 내용을 출력하는 기능
    
4. Validator
    - 유효성을 검사하는 기능

5. GameManager
    - 게임을 관리하는 기능