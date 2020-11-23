# gray-release-spring-boot-starter

## 一个模拟灰度发布的demo
原理很简单，通过规则匹配返回boolean值，决定后续的调用api走向

## usage
### config
```yml
gray:
  release:
    enabled: true
    rule:
      feature:
        -
          enable: true
          key: a
          values:
            - 100
            - 200
            - 300, 500
        -
          enable: true
          key: b
          values:
            - 700
            - 800
            - 900, 1600
```
### maven
```xml
<dependency>
    <groupId>com.toby</groupId>
    <artifactId>gray-release-spring-boot-starter</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```