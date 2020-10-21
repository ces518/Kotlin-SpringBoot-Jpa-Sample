# Kotlin - SpringBoot - JPA sample

## 코틀린 + JPA 사용시 주의점

### 엔티티는 기본 생성자가 필요하다.
- 자바에서는 기존에 롬복을 사용해서 해결하였으나 코틀린에서는 코틀린 No-arg 플러그인을 사용
```gradle

plugins {
    id "org.jetbrains.kotlin.plugin.jpa" version "1.3.61"
}
```

### Entity 는 final 클래스일 수 없다. 코틀린은 기본이 final
- JPA 에서 프록시 객체를 사용하기 때문에 final 클래스를 사용할 수 없다.
- 하지만 코틀린에서의 기본 클래서는 final 이다.
- 이를 해결하면 open 변경자를 사용하면 되는데 이것 또한 보일러 코드가 된다.
- 코틀린의 allopen 플러그인 사용

```gradle
plugins {
    id "org.jetbrains.kotlin.plugin.allopen" version "1.3.61"
}

allOpen { 
    annotation("javax.persistence.Entity") 
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}
```

### Entity 의 상태는 반드시 본인만이 접근해야 한다.
- 엔티티의 속성을 val 로 선언하지 않는한 getter, setter 가 모두 열리게 되어 변경가능성이 열린다.
- 이는 유지보수에 좋지 못한 코드.
- 이를 위한 해결방법은 크게 두가지가 있다.
- 1. 모든 변수를 var로 선언하고, setter를 protected set 으로 선언한다.
    - open 키워드가 있는 곳에는 private 을 사용할 수 없다. (allopen 플러그인 사용으로 인함)
    - 단점 보일러 코드가 생긴다.
    - 하지만 Lazy loading, 더티 체킹등 기능 모두 이용이 가능하다.
- 2. 모든 변수를 val 로 선언하고, 변경이 필요한 경우 copy() 함수를 이용해서 새로운 객체를 생성하는 방식
    - 보일러 코드는 사라지지만, Lazy loading , 더티체킹 이용을 하지못한다.

> 여기서 선택을 해야한다. JPA 를 실무에서 경험하면서 Lazy loading 으로 인해 이득을 보는 상황은 크게 없었다.
> 대부분 해당 그래프에 맞는 쿼리 메소드를 만들어서 사용하거나, @EntityGraph 등을 사용했었다..

- 우선은 샘플 코드이기 떄문에 전자로 먼저 진행해보기로 결정

### 이슈
- Spring Redis CacheManager 사용시 두가지 방법 존재
- 1. 객체를 그대로 Redis 에 저장한다.
    - Spring Boot Default 설정 사용시 문제 없음
    - Serialize 대상 객체는 Serializable 인터페이스를 구현해야함
- 2. JSON 형태로 변환하여 Redis 에 저장한다.
    - Custom 설정이 필요하며, 현재 DeSerialize 할때 예외 발생..