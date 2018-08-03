package com.ripple.generator.items;

import com.google.common.base.CaseFormat;
import com.ripple.generator.GeneratorConfig;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.dom.java.*;

/**
 * dao文件生成
 */
public class DaoGenerator {

    /**
     * 生成Dao文件
     */
    public static GeneratedJavaFile generator(GeneratorConfig generatorConfig,String tableName){
        String keyName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL,tableName);
        String[] packageNames = tableName.split("_");
        String packageNamesStr = String.join(".", packageNames);
        String domainPackage =  generatorConfig.getDomainPackage() + "."+packageNamesStr +"."+ keyName;
        String daoPackage = generatorConfig.getDaoPackage() + "."+packageNamesStr +".I"+ keyName+"DAO";
        Interface daoInterface = new Interface(daoPackage);
        FullyQualifiedJavaType entityType = new FullyQualifiedJavaType(domainPackage);
        FullyQualifiedJavaType baseDaoType = new FullyQualifiedJavaType(generatorConfig.getDaoPackage()+".IBaseDAO");
        FullyQualifiedJavaType baseDaoTmpType=  new FullyQualifiedJavaType("IBaseDAO");
        baseDaoTmpType.addTypeArgument(entityType);
        // 设置导入的类
        daoInterface.setVisibility(JavaVisibility.PUBLIC);
        daoInterface.addImportedType(entityType);
        daoInterface.addImportedType(baseDaoType);
        daoInterface.addSuperInterface(baseDaoTmpType);
        baseDaoType.addTypeArgument(entityType);

        return new GeneratedJavaFile(daoInterface, generatorConfig.getDaoPath(), generatorConfig.getContext().getJavaFormatter());
    }
}
