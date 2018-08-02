package com.ripple.generator.items;

import com.google.common.base.CaseFormat;
import com.ripple.generator.GeneratorConfig;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.JavaFormatter;
import org.mybatis.generator.api.dom.java.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * domain文件生成
 */
public class DomainGenerator {
    /**
     * 生成Dao,DaoEx文件
     */
    public static List<GeneratedJavaFile> generator(GeneratorConfig generatorConfig,TopLevelClass sourceItem, String tableName, JavaFormatter javaFormatter){
        String [] filterColumn = new String[]{"id","createAt","updateAt","createUid","updateUid","status"};
        List<GeneratedJavaFile> results = new ArrayList<>();
        String keyName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName);
        String[] packageNames = tableName.split("_");
        String packageNamesStr = String.join(".", packageNames);
        String domainPackage = generatorConfig.getDomainPackage() + "."+packageNamesStr +"."+ keyName;
        TopLevelClass domainClass = new TopLevelClass(domainPackage);
        domainClass.addImportedTypes(sourceItem.getImportedTypes());
        domainClass.setSuperClass(sourceItem.getSuperClass());
        domainClass.setVisibility(JavaVisibility.PUBLIC);
        for (FullyQualifiedJavaType importedType : sourceItem.getImportedTypes()) {
            domainClass.addImportedType(importedType);
        }
        for (Field field : sourceItem.getFields()) {
            if(!Arrays.asList(filterColumn).contains(field.getName()))
                domainClass.addField(field);
        }
        for (Method method : sourceItem.getMethods()) {
            String methodKeyName = method.getName().replace("get","").replace("set","");
            methodKeyName=(new StringBuilder()).append(Character.toLowerCase(methodKeyName.charAt(0))).append(methodKeyName.substring(1)).toString();
            if(!Arrays.asList(filterColumn).contains(methodKeyName))
                domainClass.addMethod(method);
        }
        results.add(new GeneratedJavaFile(domainClass, generatorConfig.getDomainPath(), javaFormatter));
        // 生成domainEx
        TopLevelClass domainExClass = new TopLevelClass(domainPackage+"Ex");
        domainExClass.setSuperClass(domainClass.getType());
        domainExClass.addImportedType(domainClass.getType());
        domainExClass.setVisibility(JavaVisibility.PUBLIC);
        results.add(new GeneratedJavaFile(domainExClass, generatorConfig.getDomainPath(), javaFormatter));
        return results;
     }
}
