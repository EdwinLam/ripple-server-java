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
 * param文件生成
 */
public class ParamGenerator {
    /**
     * 生成Param,paramEx文件
     */
    public static List<GeneratedJavaFile> generator(GeneratorConfig generatorConfig,TopLevelClass sourceItem, String tableName, JavaFormatter javaFormatter){
        List<GeneratedJavaFile> results = new ArrayList<>();
        String keyName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName);
        String[] packageNames = tableName.split("_");
        String packageNamesStr = String.join(".", packageNames);
        String paramPackage = generatorConfig.getParamPackage() + "."+packageNamesStr +"."+ keyName+"Param";
        TopLevelClass paramClass = new TopLevelClass(paramPackage);
        paramClass.setVisibility(JavaVisibility.PUBLIC);
        FullyQualifiedJavaType daoSuperType = new FullyQualifiedJavaType( generatorConfig.getParamPackage()+".BaseParam");
        paramClass.addImportedType(daoSuperType);
        paramClass.setSuperClass(daoSuperType);
        for(Field field: sourceItem.getFields()) {
            FullyQualifiedJavaType StringType = new FullyQualifiedJavaType("String");
            Field columnField = new Field("F_" + field.getName(), StringType);
            columnField.setVisibility(JavaVisibility.PUBLIC);
            columnField.setInitializationString("\""+field.getName()+"\"");
            columnField.setStatic(true);
            columnField.setFinal(true);
            paramClass.addField(columnField);
        }
        for(Field field: sourceItem.getFields()){
            paramClass.addField(field);
        }
        for(Method method: sourceItem.getMethods()){
            if(method.getName().equals("equals")){
                continue;
            }
            paramClass.addMethod(method);
        }
        results.add(new GeneratedJavaFile(paramClass, generatorConfig.getParamPath(), javaFormatter));

        // 生成paramEx
        TopLevelClass paramExClass = new TopLevelClass(paramPackage+"Ex");
        paramExClass.setVisibility(JavaVisibility.PUBLIC);
        paramExClass.setSuperClass(paramClass.getType());
        paramExClass.addImportedType(paramClass.getType());
        results.add(new GeneratedJavaFile(paramExClass,  generatorConfig.getParamPath(), javaFormatter));
        return results;
    }
}
