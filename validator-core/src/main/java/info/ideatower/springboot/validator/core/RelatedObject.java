package info.ideatower.springboot.validator.core;

/**
 * 对象的操作接口。
 * 通过统一的操作接口，适用不同类型的对象，如Bean、Map、HttpServletRequest
 */
public interface RelatedObject {

    /**
     * 判断是否有指定名称的值
     *
     * @param field
     * @return
     */
    boolean contains(String field);

    /**
     * 获取值
     *
     * @param field
     * @return
     */
    Object getValue(String field);

}
