# spring-demo
```$xslt
토비의 스프링 3.1의 내용을 기록하기 위해서 씁니다.
```

## 1장
관심사의 분리
모든 변경과 발전은 한 번에 한 가지 관심사항에 집중해서 일어난다
문제는 변화에 따른 작업은 한곳에 집중되지 않다는 경우가 많다는 점이다. 예) db 정보 변경
즉, 변화가 한 번에 한가지 관심에 집중되서 발생하기 때문에, 관심이 한군데에 집중되게 하여 작업과 영향도를 최소화

관심사 분리 방법 ( db접속정보에 대한 관심사 분리) 
1. 중복 로직을 메소드로 추출

-> 문제: 제 3자가 해당 클래스를 사용하려고 할 때, 자신들의 상황에 맞게 수정이 불가능

2. 상속을 통한 확장
확장 포인트를 protected로 제공하여 상속을 통한 확장이 가능하도록 제공
```
템플릿 메소드 패턴: 슈퍼클래스에 기본적인 로직의 흐름을 만들고, 그 기능의 일부를 추상 메소드나 오버라이딩이 가능한 protected 메소드 등으로 제공하는 패턴
팩토리 메소드 패턴: 추상 메소드를 구현할 때, 구체적인 오브젝트 생성 방법을 결정하게 하는 패턴
```

-> 문제: 이미 상속중인 클래스는 해당 패턴 적용 불가능, 상속을 통한 클래스 관계 밀접하기 때문에 
슈퍼 클래스의 변화가 서브클래스의 변화에 영향을 주기 쉬움

3. 클래스 분리
db접속정보를 생성하는 새로운 클래스 생성 후 dao에서 참조

-> 문제: 제 3자가 사용할 때, db접속정보를 자신에 맞게 변경하려면 dao를 수정해야 함.
dao가 변경될 수 있는 정보를 담당하는 클래스에 대해 너무 많이 알고있기 때문에 종속적이 됨

4. 인터페이스 분리
dao와 접속정보를 생성하는 인터페이스를 만들어 함께 제공

-> 문제: 인터페이스의 어떤 구현체를 사용할지에 대한 관심사가 아직 생성자에 남아있어서 dao를 수정하기 전에는 자유롭지 못함.

5. 클라이언트 객체로 관심 분리
dao에서 직접 구현체를 정하는 대신 클라이언트 객체에서 dao객체와 db접속정보를 생성하는 객체를 생성하여 객체간 관계를 설정해줌으로써
dao는 데이터 액세스 로직에만 집중할 수 있게됨

6. 개방 폐쇄 원칙
확장포인트는 인터페이스를 통해 개방하고, 인터페이스를 이용하는 클래스는 자신의 변화가 불필요하게 일어나지 않도록 하나의 관심사로 굳게 폐쇄되어 있다.

7. 높은 응집도 / 낮은 결합도
하나의 변경 사항에 수정할 부분이 많다면 그만큼 버그의 위험성이 높아진다. 인터페이스를 이용해 기능을 독립적으로 분리 시킨경우에,
해당 기능의 높은 응집력으로 변화에 대처하기 수월하다.(해당 인터페이스 구현체 교체)

낮은 결합도는 인터페이스를 통해서 느슨한 연결을 의미한다. 느슨한 연결을 통해서 기능이 변경될 시 인터페이스 구현체를 교체함으로써, 
변화에 대응하기 쉽다.

8. 전략 패턴
컨텍스트 - 자신의 기능을 수행하는데 필요한 기능 중에서 변경 가능한 부분을 인터페이스로 확장포인트를 만든다.
보통 컨텍스트의 사용자(클라이언트)가 사용 전략을 컨텍스트에게 제공해주는 방식이 일반적이다.


9. 제어의 역전
제어권 이전을 통한 제어의 역전, 라이브러리와 프레임워크의 차이점을 통해서 제어의 역전을 확인 가능,
라이브러리를 사용하는 어플리케이션 코드는 흐름을 직접 제어하면서 필요한 기능이 있을 경우 라이브러리를 사용한다.
반면, 프레임워크의 경우 어플리케이션 코드가 프레임워크에 의해 사용된다. 보통 프레임워크에 클래스를 등록해두고,
프레임워크가 흐름을 주도하는 중에 어플리케이션 코드를 사용하도록 만드는 방식

10. 어플리케이션 컨텍스트 & 빈팩토리 
어플리케이션 컨텍스트는 앱 전반의 기능을 통틀어 지칭하는 경우가 많고, 빈팩토리는 빈의 생성,조회 등 관리하는 측면을 지칭할 때 쓰인다.
오브젝트 팩토리를 사용하는 대신 스프링 컨테이너를 사용하면 좋은점은 
```
1. 클라이언트가 구체적인 팩토리 클래스를 알 필요가 없고,
2. 오브젝터 생성 및 관계설정 이외에 많은 부가기능이 있기 때문이다.
```
 
11. 싱글톤 레지스트리 
스프링 컨테이너는 기본적으로 컨테이너와 주기를 함께하는 싱글톤 스코프로 단 하나의 객체만이 생성된다.    
```
객체 비교 동일성, 동등성
1. 동일성 == 연산으로 동일한 객체인지 판단
2. 동등성 equals 연산으로 동일한 정보를 담고있는지 판단
```
12. 의존관계 주입(DI)
```
의존
a가 b를 의존한다는 것은 b가 변경되었을 때, a도 영향을 받을 수 있지만 반대의 경우는 영향을 받지 않는다. 즉 의존에는 방향이 존재한다.
```
1. 클래스 모델이나 코드에는 런타임 시점의 관계가 드러나지 않아야 하며, 그러기 위해서는 인터페이스를 의존해야한다.
2. 런타임 시점의 관계는 컨테이너와 같은 제 3의 존재가 구성한다.
3. 의존관계는외부에서 제공해줌으로써 구성된다.

13. 의존관계 검색(DL)
의존관계 주입과 반대로 클래스가 능동적으로 자신이 사용할 클래스를 검색한느 경우를 말한다. 예를 들어 application context의 getBean을 사용한 경우다.
의존관계 검색은 컴포넌트가 컨네이터와 같은 성격이 다른 오브젝트에 의존성을 갖게되므로 바람직하지 않다.
다만 main이나 서버환경에서 사용자의 요청을 받았을 경우에 적어도 한번은 의존관계 검색을 통해서 오브젝트를 가져와야 한다. 왜냐하면 DI를 이용해 주입받을 방법이 없기 때문이다.
또한 의존관게 검색의 경우 클라이언트는 스프링 빈일 필요가 없으나 주입의 경우에는 클라이언트와 의존오브젝트 모두 스프링 빈이어야 한다.

## 2장 
테스트
```
단위 테스트를 이용해서 기존의 코드가 정상 동작하는것을 확신할 수 있고, 테스트하고자 하는 기능에 집중해 테스트 할 수 있으므로, 장애 대응이 용이하다.
단위 테스트를 이용하면 많은 수의 테스트 케이스를 일일이 수행하는 작업과 결과값을 검증하는 작업을 자동화할 수 있다.
```

1. 테스트 코드 작성

    junit은 프레임워크이기 때문에 ioc을 이용하여 테스트를 진행한다. 그렇기 때문에 main의 테스트 함수를 junit 테스트 코드로 변경해야한다.
    main 함수의 경우에는 제어권을 갖는다는 의미이기 때문이다.
    
    테스트 메소드는 "pulic"이어야하고, "@Test" 어노테이션을 붙여야 한다.
    
2. 테스트가 이끄는 개발

   테스트를 먼저 만들어 테스트가 실패하는 것을 보고 나서 실제 비지니스 로직을 수정하여 완성한다.
   테스트 코드를 먼저 작성할 때에는 추가하고 싶은 기능을 코드로 표현해야 하는데,
   테스트 코드의 표현은 조건, 행위, 결과로 표현할 수 있다.
   ```
   조건: 어떤 조건을 가지고 ( 사용자 정보가 없을때)
   행위: 무엇을 할 때 (존재하지 않는 id로 조회를 하면)
   결과: 어떤 결과가 나온다.(exception이 발생한다.)
   ```
   
3. 테스트 컨텍스트 관리

    테스트 시에 공통적인 로직은 @Before 나 @After를 이용해서 각 테스트 전과 후에 수행할 수 있다.
    또한 테스트는 각 메소드마다 독립성을 유지하기 위해서 새롭게 테스트객체가 생성되는데, 매번 테스트 컨텍스트를 인스턴스 변수에 두고 새로 생성하는것은 비효율적이다.
    따라서, 각 테스트 메소드마다 컨텍스트를 공유하기 위해서 클래스마다 딱 한번 실행되는 @BeforeClass를 사용해 스테틱 변수에 저장하여 사용하거나, 스프링이 직접 제공하는
    어플리케이션 컨텍스트 테스트 지원 기능을 사용하면 된다.
    
    어플리케이션 컨텍스트는 @ContextConfiguration을 사용한 뒤, ApplicationContext를 주입받는 식으로 작성하면 된다.
    테스트 컨텍스트는 메소드간의 공유뿐 아니라 테스트 클래스간 컨텍스트 설정이 같다면 공유도 가능하다.
    
4. DI와 테스트
    
    컴포넌트 간, 인스턴스간 관계가 절대 변하지 않는 경우에는 DI를 사용하지 않아도 될까? 그렇지 않다.
    DI는 구현체를 동적으로 변경할 수 있다는 장점(프록시 패턴) 외에도 직접 DI를 통해서 작은 부분만을 테스트할 수 있는 유용한 기술이다.
    ```
    DataSource를 운영계와 테스트계를 따로 분리할 경우에,
    1. @DirtiesContext를 사용하여 @Before시마다 dao의 DataSource를 직접 SingleConnectionDataSource로 DI할 수 있다.
    2. 별도의 DI 설정파일을 만들어(ex: test-applicationContext.xml) @ContextConfiguration의 설정파일로 사용하여 운영계와 별도의 설정을 가지고 테스트를 실행할 수 있다.
    3. 스프링의 컨테이너 없이 @Before를 이용해서 직접 dao객체를 생성하고 SingleConnectionDataSource를 생성하여 DI시켜줄 수 있다.
   
   3가지 방법 중 우선적으로 스프링 컨테이너 없이 테스트할 수 있는 방법을 고려하고, 여러 오브젝트와 복잡한 의존관계를 갖고있는 오브젝트라면
   스프링 설정을 이용한 DI방식을 고려하자 
    ```
5. 학습 테스트
    자신이 작성하지 않은 프레임워크나, 다른 개발팀에서 만든 라이브러리 등에 대해서 테스트를 작성하여, 깊은 이해를 이끌어 내는 테스트 작성 방식이다.
    
     
    
 
