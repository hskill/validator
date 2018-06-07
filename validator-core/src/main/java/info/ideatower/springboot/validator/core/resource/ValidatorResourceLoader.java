package info.ideatower.springboot.validator.core.resource;

import info.ideatower.springboot.validator.core.rule.Rule;
import info.ideatower.springboot.validator.core.rule.RuleFactory;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 加载项目类路径下 validator 文件夹下，以 yml 与 yaml 结尾的配置文件
 */
@Slf4j
public class ValidatorResourceLoader {

    /**
     * 默认位置
     */
    private static final String DEFAULT_ROOT_RESOURCE_DIR_NAME = "validator";

    /**
     * 默认的item类型
     */
    private static final String DEFAULT_ITEM_TYPE = "parameter";

    /**
     * 默认yaml文件读取后辍名
     */
    private static final String[] YML_FILE_SUFFIX = new String[]{"yaml", "yml"};

    /**
     * rule创建工厂
     */
    @Setter
    private RuleFactory ruleFactory;

    public ValidatorResourceLoader(RuleFactory ruleFactory) {
        this.ruleFactory = ruleFactory;
    }

    /**
     * 加载默认位置资源
     *
     * @return
     */
    public ValidatorResource load() {
        return load(DEFAULT_ROOT_RESOURCE_DIR_NAME);
    }

    /**
     * 加载指定位置资源
     *
     * @param rootResourceDir 根目录
     * @return
     */
    public ValidatorResource load(String rootResourceDir) {

        ValidatorResource resource = new ValidatorResource();

        val classPathResource = new ClassPathResource(rootResourceDir);
        if (classPathResource.exists()) {
            try {
                val yamlFileList = FileUtils.listFiles(classPathResource.getFile(), YML_FILE_SUFFIX, true);
                for (val yamlFile : yamlFileList) {

                    Map<String, Object> yamlResourceObject = loadYamlResourceObject(yamlFile);
                    if (yamlResourceObject != null) {
                        buildYamlResource(
                                yamlFile.getName().substring(0, yamlFile.getName().indexOf('.')),
                                resource,
                                yamlResourceObject);
                    } else {
                        log.warn("{} cannot read", yamlFile.getName());
                    }
                }
            } catch (Exception e) {
                // 不处理异常，因为异常不能影响正常运行
                log.error("load validator resource error", e);
            }
        } else {
            log.warn("specify validator resource path '{}' is not exists", rootResourceDir);
        }
        return resource;
    }

    /**
     * 加载文件为 Yaml 格式的 Map
     * @param file 文件
     * @return
     */
    protected Map<String, Object> loadYamlResourceObject(File file) {
        if (file.exists() && file.canRead()) {
            val yaml = new Yaml();
            try {
                val yamlResourceObject = yaml.load(new FileInputStream(file));
                return (Map<String, Object>) yamlResourceObject;
            } catch (IOException e) {
                // pass
            }
        }
        return null;
    }

    /**
     * 从 MAP 中加载 Yaml 资源为 ValidatorResource
     *
     * @param prefix 标记前辍 一般是文件名称
     * @param resource 资源集合
     * @param yamlObject yml对象
     */
    protected void buildYamlResource(final String prefix, ValidatorResource resource, Map<String, Object> yamlObject) {

        for (Map.Entry<String, Object> validation : yamlObject.entrySet()) {

            String mark = validation.getKey();

            if (validation.getValue() instanceof Map) {
                val itemMap = (Map<String, Object>) validation.getValue();

                for (val item : itemMap.entrySet()) {

                    if (item.getValue() instanceof Map) {

                        Map<String, Object> rules = (Map<String, Object>) item.getValue();

                        ValidatorField field = new ValidatorField();
                        field.setName(item.getKey());

                        // rules 中如果有type，则标明参数所属的类型
                        field.setType(String.valueOf(rules.getOrDefault("type", DEFAULT_ITEM_TYPE)));
                        // 删除type，方便接下来进行遍历
                        rules.remove("type");



                        for (Map.Entry<String, Object> rule : rules.entrySet()) {
                            Rule r = this.ruleFactory.getByName(rule.getKey(), rule.getValue());
                            field.addRule(r);
                        }

                        // 加入resource
                        resource.put(MessageFormat.format("{0}.{1}", prefix, mark), field);
                    }
                }
            }
        }
    }
}
