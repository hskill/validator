

[TOC]

# 一、数据校验

## 应用场景

信息系统的在Web层的输入校验，检验数据的完整、一致性、业务性。

## 配置文件

开发人员只需编写校验规则+结果处理，其中的处理过程由计算机程序自动处理。

通过配置文件，管理**请求数据（非实体结构）**的校验规则

考虑，使用在项目类路径下`validator`文件夹下，所有 `yml`/`yaml` 的文件

## 使用

以 `mark` 为识别主体

```yaml
# user.yml
# mark => 'user.edit' 注意mark是一个组合参数，使用了文件名与文件中的标记
edit:
  -  type: query
     name: id
     rules:
       notNull: ~
       max:
         value: 1000
         message: error message
```

其中，type可以验证以下部分
* query get/ form post 参数 默认
* header
* session
* cookie
* path springmvc中的path参数


通过 `SpringBoot` 的 `AutoConfiguration` 进行处理

### (1) 返回`user.yml`中`edit`的验证规则（json）
GET `/validator/rule/user/edit`



### (2) 验证结果处理 — 显式控制

**ValidatorResult**

```java
public class UserController {

    @PostMapping("/edit/{id}")
    public String editForm(
        @RequestParam("id") String id, 
        User user, 
        Model model,
        @ValidatorRule("user.edit") ValidatorResult result) {

        if (result.hasErrors()) {
            // ...
            model.addAtrribute("error", result);
            return "error_view";
        }
        return "redirect:/list?repage";
    }
}
```



## 自动处理并返回结果

可以自动处理并返回json，或view的视图结果

```java
@PostMapping("/edit/{id}")
@JsonValidatorResult(value = "user.edit", handler = SimpleJsonResultHandler.class)
public String editForm(
    @RequestParam("id") String id, 
    User user) {
    
    return "redirect:/list?repage";
}

@PostMapping("/edit/{id}")
@JsonValidatorResult("user.edit")
public String editForm(
    @RequestParam("id") String id, 
    User user) {
    
    // ...
    
    return "redirect:/list?repage";
}

```



```java
public class SimpleJsonResultHandler implements JsonResultHandler {

    @Override
    public void handle(HttpServletResponse response, ValidatorResult result) {
        // 对response进行响应内容处理
    }
}

```



# 二、使用办法

## （一）gradle中加入依赖

需要先加入maven repo，==暂无==



然后加入依赖


```
dependencies {
    compile 'info.ideatower.springboot:validator-web:0.1.0-SNAPSHOT' //
}
```

## （二）编写验证文件

在项目文件夹下的 `resources` ，新增一个 `validator` 文件夹，然后新增yml验证规则文件

在这个位置：`src/main/resources/validator/`

假设编写一个`user.yml`来验证登录

文件格式需要保持正确的yaml规范

```yaml
# 唯一的名称（mark）
# mark ==> 'user.login' 注意，mark：文件名 + login
login:
  # 验证参数列表
  -
    # 表单的参数名称
	name: loginName
	# 规则列表
	rules:
	  # 参数必须存在
	  notNull: ~
	  # 长度必须在6到12之间
	  size:
	    min: 6
	    max: 12
      # 还可以加上message，如果不加则使用默认message
      message: 长度不对
    - name: password
      rules:
        notNull: ~
        size:
          min: 6
          max: 12
    - name: captchaCode
      rules:
        # 注意没有required的情况，required不存在时，其它规则不验证
        # 固定长度
        size: 4
```



在`LoginController.java`文件的Action中加入参数注解

```java
public class LoginController {

    # 在验证规则文件中的mark
    public String loginForm(
        @RequestParam("loginName") String loginName,
        @RequestParam("password") String password,
        @RequestParam("captchaCode") String captchaCode
        @ValidatorResult("user.login") ValidatorResult result,
        Model model
        ) {

        if (result.hasErrors()) {
            // result.getAllErrors() ==> Map<String, List<ObjectError>>
            model.addAttribute("error", result.getAllErrors());
            return "login";
        }

        // do something
        return "other result";
    }
}
```





## （三）哪些验证规则可以用

- notNull
- size
- between
- equalsTo
- cellPhone
- telPhone

# 三、自定义规则

## （一）实现Rule接口

`info.ideatower.springboot.validator.core.rule`



```
package foo.bar;

public class OtherRule implements Rule {

    private String v;
    
    public boolean isValid(ValidatorContext context, Object target, ValidatorResult errors) {
        ...
    }
}
```

或继承``info.ideatower.springboot.validator.core.AbstractRule``



##（二）在规则文件中，使用类全名

user.yml

```yaml
edit:
    - name: id
      rules:
        foo.bar.OtherRule:
            v: 1
```

