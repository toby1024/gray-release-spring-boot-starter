package com.toby.rule;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @Author: zhangbin
 * @Date: 2020/11/23
 */
@Data
@ConfigurationProperties(prefix = "gray.release.rule")
public class RuleProperties {

    private List<Feature> feature;

    @Data
    public static class Feature {
        private boolean enable;
        private String key;
        private List<String> values;
    }
}
