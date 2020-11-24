package com.toby.gray;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhangbin
 * @Date: 2020/11/24
 */
@Data
public class GrayRuleFactory {

    private Map<String, IGrayRelease> grayReleaseMap;

    public IGrayRelease get(String clazz) {
        return grayReleaseMap.get(clazz);
    }

    public List<IGrayRelease> getAll() {
        return new ArrayList(grayReleaseMap.values());
    }


    /**
     * 执行单个策略
     *
     * @param strategy
     * @param target
     * @return
     */
    public boolean filter(String strategy, String target) {
        return get(strategy).execute(target);
    }


    /**
     * 执行全部策略
     *
     * @param target
     * @return
     */
    public boolean filterAll(String target) {
        return getAll().stream().anyMatch(strategy -> strategy.execute(target));
    }
}
