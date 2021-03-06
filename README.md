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
          key: defaultGrayRelease
          values:
            - 100
            - 200
            - 300, 500
        -
          enable: true
          key: myGrayReleaseStrategy
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

### java
```java
@Autowired
private GrayRuleFactory grayRuleFactory;
boolean filterAll = grayRuleFactory.filterAll(target);
```

### 自定义规则
```java
@Slf4j
@Component
public class MyGrayReleaseStrategy implements IGrayRelease {
    @Autowired
    private RuleProperties ruleProperties;

    @Override
    public boolean execute(String s) {
        log.info("执行自定义的灰度规则");
        List<RuleProperties.Feature> featureList = ruleProperties.getFeature().stream()
                                                                 .filter(feature -> feature.getKey().equals(ClassUtils.getShortNameAsProperty(MyGrayReleaseStrategy.class)))
                                                                 .collect(Collectors.toList());
        return featureList.stream().anyMatch(feature -> feature.getValues().contains(s));
    }
}
```