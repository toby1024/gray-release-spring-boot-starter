package com.toby.gray;

import com.toby.rule.RuleProperties;

import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * @Author: zhangbin
 * @Date: 2020/11/23
 */
public class GrayRelease {
    private Logger logger = Logger.getLogger(GrayRelease.class.getName());

    private RuleProperties ruleProperties;

    public GrayRelease(RuleProperties ruleProperties) {
        this.ruleProperties = ruleProperties;
    }

    public boolean execute(String target) {
        logger.info("规则过滤");
        return ruleProperties.getFeature().stream()
                             .filter(feature -> feature.isEnable())
                             .anyMatch(feature -> feature.getValues().stream().anyMatch(v -> Objects.equals(v, target)));
    }
}
