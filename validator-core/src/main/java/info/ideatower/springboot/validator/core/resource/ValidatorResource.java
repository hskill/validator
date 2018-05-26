package info.ideatower.springboot.validator.core.resource;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * 验证的资源文件
 * <p>
 * 所有验证配置项的集合
 * <p>
 * 配置文件结构示例：
 * <code>
 * <p>
 * # mark
 * user.edit:
 *   -
 *   # 类型：[parameter, cookie, header, session]
 *     type: parameter
 *     name: name
 *     rules:
 *       required: {}
 *       size: 16
 *   # mark
 * user.crate:
 *   -
 *     type: parameter
 *     name: name
 *     rules:
 *       required: {}
 *       size: 16
 * </code>
 * <p>
 * 注意：多个文件中mark不能重复，否则会覆盖
 */
@ToString
public class ValidatorResource {

    @Getter
    private final Map<String, List<ValidatorField>> infoMap = Maps.newHashMap();

    /**
     * 获取相关配置项
     *
     * @param mark
     * @return
     */
    public List<ValidatorField> get(String mark) {
        return infoMap.get(mark);
    }

    public List<ValidatorField> get(String file, String mark) {
        return get(file + "." + mark);
    }

    /**
     * 增加相关的配置项
     *
     * @param item
     * @param field
     */
    public void put(String item, ValidatorField field) {

        if (infoMap.containsKey(item)) {
            infoMap.get(item).add(field);
        } else {
            infoMap.put(item, Lists.newArrayList(field));
        }

    }

    /**
     * 是否存在指定的mark
     *
     * @param mark
     * @return
     */
    public boolean has(String mark) {
        return infoMap.containsKey(mark);
    }

    public boolean has(String file, String mark) {
        return has(file + "." + mark);
    }
}
