package com.ripple.generator;

import org.codehaus.plexus.util.StringUtils;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.config.Context;

import java.io.File;

public class GeneratorConfig {
    private Context context;
    private String targetPackage;
    private String targetProject;
    private String paramPackage;
    private String domainPackage;
    private String daoPackage;
    private String servicePackage;

    private String xmlPath;
    private String paramPath;
    private String domainPath;
    private String daoPath;
    private String servicePath;
    private String author;
    private IntrospectedTable introspectedTable;

    GeneratorConfig(Context context,IntrospectedTable introspectedTable,String targetPackage,String targetProject,String author){
        this.introspectedTable = introspectedTable;
        String basePath = targetPackage.replaceAll(escapeExprSpecialWord("."),"/");
        this.context = context;
        this.targetPackage = targetPackage;
        this.targetProject = targetProject;
        this.domainPath = targetProject + "-domain/src/main/java";
        this.paramPath = targetProject + "-domain/src/main/java";
        this.daoPath = targetProject+ "-service/src/main/java";
        this.servicePath = targetProject+ "-service/src/main/java";
        this.domainPackage = targetPackage+".domain";
        this.paramPackage = targetPackage+".param";
        this.daoPackage = targetPackage+".dao";
        this.servicePath = targetPackage+".service";
        this.xmlPath = targetProject +"-service/src/main/resources/mybatis";
        this.author = author;
    }

    public String getDomainPath() {
        return domainPath;
    }

    public void setDomainPath(String domainPath) {
        this.domainPath = domainPath;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getDaoPath() {
        return daoPath;
    }

    public void setDaoPath(String daoPath) {
        this.daoPath = daoPath;
    }

    public String getServicePath() {
        return servicePath;
    }

    public void setServicePath(String servicePath) {
        this.servicePath = servicePath;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getParamPath() {
        return paramPath;
    }

    public void setParamPath(String paramPath) {
        this.paramPath = paramPath;
    }

    public IntrospectedTable getIntrospectedTable() {
        return introspectedTable;
    }

    public void setIntrospectedTable(IntrospectedTable introspectedTable) {
        this.introspectedTable = introspectedTable;
    }

    public static String escapeExprSpecialWord(String keyword) {
        if (StringUtils.isNotBlank(keyword)) {
            String[] fbsArr = { "\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|" };
            for (String key : fbsArr) {
                if (keyword.contains(key)) {
                    keyword = keyword.replace(key, "\\" + key);
                }
            }
        }
        return keyword;
    }

    public String getTargetPackage() {
        return targetPackage;
    }

    public void setTargetPackage(String targetPackage) {
        this.targetPackage = targetPackage;
    }

    public String getTargetProject() {
        return targetProject;
    }

    public String getParamPackage() {
        return paramPackage;
    }

    public void setParamPackage(String paramPackage) {
        this.paramPackage = paramPackage;
    }

    public String getDomainPackage() {
        return domainPackage;
    }

    public void setDomainPackage(String domainPackage) {
        this.domainPackage = domainPackage;
    }

    public String getDaoPackage() {
        return daoPackage;
    }

    public void setDaoPackage(String daoPackage) {
        this.daoPackage = daoPackage;
    }

    public String getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
    }

    public String getXmlPath() {
        return xmlPath;
    }

    public void setXmlPath(String xmlPath) {
        this.xmlPath = xmlPath;
    }
}
