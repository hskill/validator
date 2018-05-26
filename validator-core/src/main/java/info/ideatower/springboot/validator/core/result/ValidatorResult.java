package info.ideatower.springboot.validator.core.result;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.val;

import java.util.List;
import java.util.Map;

/**
 * 总体验证结果(错误消息集合)
 *
 */
public class ValidatorResult {

    /**
     * 错误消息集
     */
    private final Map<String, List<ObjectError>> errorsMap;

    /**
     * 错误计数器
     */
    private int errorCounter;

    public ValidatorResult() {
        this.errorsMap = Maps.newHashMap();
        this.errorCounter = 0;
    }

    /**
     * 放置错误信息
     *
     */
    public void reject(ObjectError error) {

        String field = error.getObjectName();

        if (this.errorsMap.containsKey(field)) {
            this.errorsMap.get(field).add(error);
        } else {
            this.errorsMap.put(field, Lists.newArrayList(error));
        }
        this.errorCounter = this.errorCounter + 1;
    }

    /**
     * 返回所有错误
     *
     * @return
     */
    public List getAllErrors() {
        List<ErrorResult> resultList = Lists.newArrayList();
        for (val error : this.errorsMap.entrySet()) {
            ErrorResult item = new ErrorResult();
            item.setName(error.getKey());

            for (val objectError : error.getValue()) {
                item.addError(new ErrorResultItem(objectError.getMessage(), objectError.getRuleName()));
            }
            resultList.add(item);
        }
        return resultList;
    }

    /**
     * 获取所有错误信息
     */
    public Map<String, List<ObjectError>> getOriginalErrorsMap() {
        return this.errorsMap;
    }

    /**
     * 返回是否有错误
     *
     * @return
     */
    public boolean hasErrors() {
        return this.errorCounter != 0;
    }

    /**
     * 返回错误数量
     *
     * @return
     */
    public int getErrorCount() {
        return this.errorCounter;
    }

    @Data
    public static class ErrorResult {
        private String name;
        private List<ErrorResultItem> errors = Lists.newArrayList();

        public void addError(ErrorResultItem item) {
            this.errors.add(item);
        }
    }

    @Data
    public static class ErrorResultItem {
        private String message;
        private String rule;

        public ErrorResultItem(String message, String rule) {
            this.message = message;
            this.rule = rule;
        }
    }
}
