# 🧮 Java Kiosk

콘솔 기반의 키오스크를 단계적으로 구현하는 프로젝트입니다.  
기본 입력/출력부터 클래스, 컬렉션, 제네릭, Enum, 람다 & 스트림까지 점진적으로 개선합니다.

---

## 📗 v 1. 기본적인 키오스크를 프로그래밍해보자

### ✅ 기능 요구사항

- **햄버거 메뉴 출력 및 선택하기**
    - `Scanner`를 사용하여 여러 햄버거 메뉴를 출력합니다.
    -  제시된 메뉴 중 입력받은 숫자에 따라 다른 로직을 실행하는 코드를 작성합니다.
    -  반복문을 이용해서 특정 번호가 입력되면 프로그램을 종료합니다.


<br>

### 📦 프로젝트 구조
```
lv1/
├── Main.java
├── Kiosk.java
├── MenuItem.java
├── input/
    ├── InputProvider.java        // interface
    └── ConsoleInputProvider.java // 구현체
└── output/
    ├── OutputWriter.java         // interface
    └── ConsoleOutputWriter.java  // 구현체
```
<br>

---

## 📘 v 2. 객체 지향 설계를 적용해 햄버거 메뉴를 클래스로 관리하기

### ✅ 기능 요구사항

-  **`MenuItem` 클래스 생성하기**
    -  설명 : 개별 음식 항목을 관리하는 클래스입니다. 현재는 햄버거만 관리합니다.
    -  클래스는 `이름`, `가격`, `설명` 필드를 갖습니다.
-  `main` 함수에서 `MenuItem` 클래스를 활용하여 햄버거 메뉴를 출력합니다.
    -  `MenuItem` 객체 생성을 통해 `이름`, `가격`, `설명`을 세팅합니다.
    -  `List`를 선언하여 여러 `MenuItem`을 추가합니다.
    -   반복문을 활용해 `menuItems`를 탐색하면서 하나씩 접근합니다.

<br>

### 📦 프로젝트 구조
```
lv2/
├── Main.java
├── Kiosk.java
├── MenuItem.java
├── input/
    ├── InputProvider.java        // interface
    └── ConsoleInputProvider.java // 구현체
└── output/
    ├── OutputWriter.java         // interface
    └── ConsoleOutputWriter.java  // 구현체
```
<br>

---

## 🚀 Lv 3. 객체 지향 설계를 적용해 순서 제어를 클래스로 관리하기

### ✅ 고급 기능 요구사항

-  **`Kiosk` 클래스 생성하기**
    -  **설명**: 키오스크 프로그램의 메뉴를 관리하고 사용자 입력을 처리하는 클래스입니다.
    -  `MenuItem`을 관리하는 리스트가 필드로 존재합니다.
    -   `main` 함수에서 관리하던 입력과 반복문 로직은 이제 `start` 함수를 만들어 관리합니다.
    -  `List<MenuItem> menuItems` 는 `Kiosk` 클래스 생성자를 통해 값을 할당합니다.
        -  `Kiosk` 객체를 생성하고 사용하는 `main` 함수에서 객체를 생성할 때 값을 넘겨줍니다.

<br>

### 📦 프로젝트 구조
```
lv3/
├── Main.java
├── Kiosk.java
├── MenuItem.java
└── utils/
    └── Parser.java
└── input/
    ├── InputProvider.java        // interface
    └── ConsoleInputProvider.java // 구현체
└── output/
    ├── OutputWriter.java         // interface
    └── ConsoleOutputWriter.java  // 구현체
```

<br>


---

## 🚀 Lv 4. 객체 지향 설계를 적용해 음식 메뉴와 주문 내역을 클래스 기반으로 관리하기

### ✅ 고급 기능 요구사항

-  **`Menu` 클래스 생성하기**
    -  설명 : MenuItem 클래스를 관리하는 클래스입니다.
      예를 들어, 버거 메뉴, 음료 메뉴 등 각 카테고리 내에 여러 `MenuItem`을 포함합니다.
    -   `List<MenuItem>` 은 `Kiosk` 클래스가 관리하기에 적절하지 않으므로 Menu 클래스가 관리하도록 변경합니다.
    -  여러 버거들을 포함하는 상위 개념 ‘버거’ 같은 `카테고리 이름` 필드를 갖습니다.
    -  메뉴 카테고리 이름을 반환하는 메서드가 구현되어야 합니다.
<br>

### 📦 프로젝트 구조
```
lv4/
├── Main.java
├── Kiosk.java
└── domain/
    ├── Menu.java
    └── MenuItem.java
└── enums/
    ├── Category.java
    └── KioskStatus.java
└── input/
    ├── InputProvider.java
    └── ConsoleInputProvider.java
└── output/
    ├── OutputWriter.java
    └── ConsoleOutputWriter.java
└── repository/
    ├── MenuRepository.java
    └── MemoryMenuRepository.java
└── service/
    └── MenuService.java
└── utils/
    └── Parser.java
```

<br>


---

# 도전 기능

## 🚀 Lv 1. 장바구니 및 구매하기 기능을 추가하기

### ✅ 고급 기능 요구사항

-  **장바구니 생성 및 관리 기능**
    -  사용자가 선택한 메뉴를 장바구니에 추가할 수 있는 기능을 제공합니다.
    -  장바구니는 메뉴명, 수량, 가격 정보를 저장하며, 항목을 동적으로 추가 및 조회할 수 있어야 합니다.
    -  사용자가 잘못된 선택을 했을 경우 예외를 처리합니다(예: 유효하지 않은 메뉴 번호 입력)
-  **장바구니 출력 및 금액 계산**
    -  사용자가 결제를 시도하기 전에, 장바구니에 담긴 모든 메뉴와 총 금액을 출력합니다.
    -  출력 예시
        -  각 메뉴의 이름, 가격, 수량
        -  총 금액 합계
-  **장바구니 담기 기능**
    -  메뉴를 클릭하면 장바구니에 추가할 지 물어보고, 입력값에 따라 “추가”, “취소” 처리합니다.
    -  장바구니에 담은 목록을 출력합니다.

-  **주문 기능**
    -  장바구니에 담긴 모든 항목을 출력합니다.
    -  합산하여 총 금액을 계산하고, “주문하기”를 누르면 장바구니를 초기화합니다.
<br>

---

## 🚀 Lv 2. Enum, 람다 & 스트림을 활용한 주문 및 장바구니 관리

### ✅ 고급 기능 요구사항

-  **Enum을 활용한 사용자 유형별 할인율 관리하기**
    -  사용자 유형의 Enum 정의 및 각 사용자 유형에 따른 할인율 적용
        -   예시 : 군인, 학생, 일반인
    -  주문 시, 사용자 유형에 맞는 할인율 적용해 총 금액 계산
-  **람다 & 스트림을 활용한 장바구니 조회 기능**
    -  기존에 생성한 Menu의 MenuItem을 조회 할 때 스트림을 사용하여 출력하도록 수정
    -  기존 장바구니에서 특정 메뉴 빼기 기능을 통한 스트림 활용

### 📦 프로젝트 구조
```
challenge/
├── Main.java
├── Kiosk.java
├── domain/
│   ├── Cart.java
│   ├── CartItem.java
│   ├── Menu.java
│   └── MenuItem.java
│       
└── enums/
    ├── Category.java
    ├── Discount.java
    ├── KioskOption.java
    └── KioskStatus.java
    └── cart/
        └── CartOption.java
    └── menu/
         ├── MenuOption.java
         ├── MenuItemOption.java
         ├── BurgerOption.java
         ├── DrinkOption.java
         └── DessertOption.java
└── view/
    ├── MenuView.java
    ├── CartView.java
    └── OrderView.java

└── input/
    ├── InputProvider.java
    └── ConsoleInputProvider.java

└── output/
    ├── OutputWriter.java 
    └── ConsoleOutputWriter.java
└── repository/
    ├── MenuRepository.java
    └── MemoryMenuRepository.java

└── service/
    └── MenuService.java
└── utils/
    ├── Parser.java
    └── MenuItemsUtil.java
└── exception/
    ├── EmptyCartException.java
    ├── InvalidCategoryException.java
    ├── InvalidDiscountOptionException.java
    └── InvalidOptionException.java
```

<br>

## 🛠 기술 키워드

- Java 기본 문법 (`Scanner`, `if`, `switch`, `while`)
- 클래스 및 캡슐화
- 컬렉션 (`List`, `Queue` 등)
- Enum, 제네릭(Generic)
- 람다식, 스트림(Stream API)

---

## 📅 개발 기간
2025.07.17 ~ 2025.07.23 (총 7일)

---

## 📒 개발 기록 (TIL)

- [키오스크 개발 시작!! 요구 사항대로 차근차근...](https://s-y-130.tistory.com/502)
- [Lv2 객체 지향 설계를 해보자](https://s-y-130.tistory.com/503)
- [Lv3. Kiosk 를 분리해보자](https://s-y-130.tistory.com/504)
- [음식 메뉴를 본격적으로 관리해보자](https://s-y-130.tistory.com/505)
- [도전 과제 - 장바구니 및 구매하기 기능 추가!!](https://s-y-130.tistory.com/506)



