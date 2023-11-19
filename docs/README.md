# 로또 게임 만들기

# 🚀 기획 요구사항

## 게임 규칙

로또 게임 기능을 구현해야 한다. 로또 게임은 아래와 같은 규칙으로 진행된다.

```
- 로또 번호의 숫자 범위는 1~45까지이다.
- 1개의 로또를 발행할 때 중복되지 않는 6개의 숫자를 뽑는다.
- 당첨 번호 추첨 시 중복되지 않는 숫자 6개와 보너스 번호 1개를 뽑는다.
- 당첨은 1등부터 5등까지 있다. 당첨 기준과 금액은 아래와 같다.
    - 1등: 6개 번호 일치 / 2,000,000,000원
    - 2등: 5개 번호 + 보너스 번호 일치 / 30,000,000원
    - 3등: 5개 번호 일치 / 1,500,000원
    - 4등: 4개 번호 일치 / 50,000원
    - 5등: 3개 번호 일치 / 5,000원
```

- 로또 구입 금액을 입력하면 구입 금액에 해당하는 만큼 로또를 발행해야 한다.
- 로또 1장의 가격은 1,000원이다.
- 당첨 번호와 보너스 번호를 입력받는다.
- 사용자가 구매한 로또 번호와 당첨 번호를 비교하여 당첨 내역 및 수익률을 출력하고 로또 게임을 종료한다.
- 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException`를 발생시키고, "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받는다.
    - `Exception`이 아닌 `IllegalArgumentException`, `IllegalStateException` 등과 같은 명확한 유형을 처리한다.

## 입출력 요구 사항

### 입력

- 로또 구입 금액을 입력 받는다. 구입 금액은 1,000원 단위로 입력 받으며 1,000원으로 나누어 떨어지지 않는 경우 예외 처리한다.
- 당첨 번호를 입력 받는다. 번호는 쉼표(,)를 기준으로 구분한다.
- 보너스 번호를 입력 받는다.

### 출력

- 발행한 로또 수량 및 번호를 출력한다. 로또 번호는 오름차순으로 정렬하여 보여준다.
- 당첨 내역을 출력한다.
- 수익률은 소수점 둘째 자리에서 반올림한다. (ex. 100.0%, 51.5%, 1,000,000.0%)
- 예외 상황 시 에러 문구를 출력해야 한다. 단, 에러 문구는 "[ERROR]"로 시작해야 한다.

### 입출력 예시

```
구입금액을 입력해 주세요.
8000

8개를 구매했습니다.
[8, 21, 23, 41, 42, 43] 
[3, 5, 11, 16, 32, 38] 
[7, 11, 16, 35, 36, 44] 
[1, 8, 11, 31, 41, 42] 
[13, 14, 16, 38, 42, 45] 
[7, 11, 30, 40, 42, 43] 
[2, 13, 22, 32, 38, 45] 
[1, 3, 5, 14, 22, 45]

당첨 번호를 입력해 주세요.
1,2,3,4,5,6

보너스 번호를 입력해 주세요.
7

당첨 통계
---
3개 일치 (5,000원) - 1개
4개 일치 (50,000원) - 0개
5개 일치 (1,500,000원) - 0개
5개 일치, 보너스 볼 일치 (30,000,000원) - 0개
6개 일치 (2,000,000,000원) - 0개
총 수익률은 62.5%입니다.
```

# 🏋️‍♀️ 구현을 위한 기능 설계서

아래는 실제 구현을 위한 기능 설계서입니다.

패키지, 클래스 별로 기능 요구사항을 구현합니다.

## 🖼️ view

### InputView

사용자 입력을 받는 역할

* 로또 구입 금액
    * 구입 금액 입력 문구 출력
        * `구입금액을 입력해 주세요.`
    * 구입 금액 입력 받기
    * 간단한 입력 검증
        * 빈 문자열이면 안 된다.
        * 정수여야 한다.
* 당첨 번호
    * 당첨 번호 입력 문구 출력
        * `당첨 번호를 입력해 주세요.`
    * 당첨 번호 입력 받기
    * 간단한 입력 검증
        * 빈 문자열이면 안 된다.
        * 쉼표 기준으로 구분했을 때 6개의 번호여야 한다.
* 보너스 번호
    * 보너스 번호 입력 문구 출력
        * `보너스 번호를 입력해 주세요.`
    * 보너스 번호 입력 받기
    * 간단한 입력 검증
        * 빈 문자열이면 안 된다.
        * 정수여야 한다.
* 예외 상황 시
    * 에러 문구는 `[ERROR]`로 시작해야 한다.
    * 에러 문구 출력 후 재입력을 받는다.

### OutputView

사용자에게 결과 화면을 출력하는 역할

* 발행한 로또 수량 및 번호 출력
    ```
    8개를 구매했습니다.
    [8, 21, 23, 41, 42, 43]
    [3, 5, 11, 16, 32, 38]
    [7, 11, 16, 35, 36, 44]
    [1, 8, 11, 31, 41, 42]
    [13, 14, 16, 38, 42, 45]
    [7, 11, 30, 40, 42, 43]
    [2, 13, 22, 32, 38, 45]
    [1, 3, 5, 14, 22, 45]
    ```
* 당첨 내역 출력
    * 숫자는 지역화 문자열로 출력(3자리 마다 콤마)

    ```
    당첨 통계
    ---
    3개 일치 (5,000원) - 1개
    4개 일치 (50,000원) - 0개
    5개 일치 (1,500,000원) - 0개
    5개 일치, 보너스 볼 일치 (30,000,000원) - 0개
    6개 일치 (2,000,000,000원) - 0개
    ```

* 수익률 출력
    * 숫자는 지역화 문자열로 출력(3자리 마다 콤마)
    * 소수점 둘째자리에서 반올림하여 출력

    ```
    총 수익률은 62.5%입니다.
    ```

## 🧑 domain

도메인 로직을 수행하는 역할

### 로또

* 발행된 로또에 대한 정보를 갖고 있다.
    * 6개의 숫자로 이루어져 있다.
    * 검증
        * 각 숫자는 1~45 사이 정수여야 한다.
        * 각 숫자는 중복되지 않아야 한다.
    * 6개의 숫자는 오름차순으로 정렬되어 있어야 한다.

### 당첨 번호

* 당첨 번호 정보를 갖고 있다.
    * 6개의 당첨 숫자로 이루어져 있다.
    * 검증
        * 각 숫자는 1~45 사이 정수여야 한다.
        * 각 숫자는 중복되지 않아야 한다.

### 보너스 번호

* 보너스 번호 정보를 갖고 있다.
    * 검증
        * 1~45 사이 정수여야 한다.
        * 당첨 번호와 중복되지 않아야 한다.

### 로또 생산 기계

* 금액만큼 로또를 발행해준다.
* 랜덤으로 로또를 생성하는 기능이 있다.

### 복권 추첨 통계 기계

* 당첨 번호, 보너스 번호, 구입한 로또 목록을 입력하면 당첨 번호와 보너스 번호를 토대로 당첨 통계를 계산해준다.

## 🌉 controller

프로그램의 흐름을 제어하는 역할

* 구입 금액을 입력받도록 한다.
* 구입한 로또 목록을 출력하도록 한다.
* 당첨 번호를 입력받도록 한다.
* 보너스 번호를 입력받도록 한다.
* 당첨 내역을 출력하도록 한다.
* 수익률을 출력도록 한다.