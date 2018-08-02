package com.ripple.generator.items;

import com.google.common.base.CaseFormat;
import com.ripple.generator.GeneratorConfig;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.dom.java.*;

/**
 * dao文件生成
 */
public class DaoGenerator {
    private  final static String DAO_SUPER_CLASS="base.IBaseDao";
    private GeneratorConfig generatorConfig;
    public DaoGenerator(GeneratorConfig generatorConfig){
        this.generatorConfig = generatorConfig;
    }
    /**
     * 获取fetch方法
     * @param tableName
     * @param returnType
     * @return
     */
    public static Method getFetchMethod(String tableName, FullyQualifiedJavaType returnType){
        Method method = new Method("fetch");
        method.addAnnotation("@Select(\"SELECT * FROM "+tableName+" WHERE id= #{id}\")");
        method.setReturnType(returnType);
        Parameter parameter = new Parameter(new FullyQualifiedJavaType("String"),"id");
        parameter.addAnnotation("@Param(\"id\")");
        method.addParameter(parameter);
        return method;
    }

    /**
     * 生成Dao文件
     */
    public static GeneratedJavaFile generator(GeneratorConfig generatorConfig,String tableName){
        String keyName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL,tableName);
        String[] packageNames = tableName.split("_");
        String packageNamesStr = String.join(".", packageNames);
        String domainPackage =  generatorConfig.getDomainPackage() + "."+packageNamesStr +"."+ keyName;
        String daoPackage = generatorConfig.getDaoPackage() + "."+packageNamesStr +"."+ keyName;
        Interface daoInterface = new Interface(daoPackage);
        FullyQualifiedJavaType entityType = new FullyQualifiedJavaType(domainPackage);
        // 设置导入的类
        daoInterface.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Insert"));
        daoInterface.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Mapper"));
        daoInterface.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Param"));
        daoInterface.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Select"));
        daoInterface.addImportedType(entityType);
        String baseDaoPackage = generatorConfig.getTargetPackage()+"."+DAO_SUPER_CLASS;
        FullyQualifiedJavaType daoSuperType = new FullyQualifiedJavaType(baseDaoPackage);
        daoInterface.addImportedType(daoSuperType);
        daoInterface.addSuperInterface(daoSuperType);
        // 类的一些设置
        daoInterface.setVisibility(JavaVisibility.PUBLIC);
        daoInterface.addAnnotation("@Mapper");
        daoInterface.addJavaDocLine("/**");
        daoInterface.addJavaDocLine(" * " + generatorConfig.getIntrospectedTable().getRemarks() );
        daoInterface.addJavaDocLine(" * @author " + generatorConfig.getAuthor() );
        daoInterface.addJavaDocLine(" */");
        // 添加方法
        Method  fetchMethod = getFetchMethod(tableName,entityType);
        daoInterface.addMethod(fetchMethod);
        return new GeneratedJavaFile(daoInterface, generatorConfig.getTargetProject(), generatorConfig.getContext().getJavaFormatter());
    }
}
