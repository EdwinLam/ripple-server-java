package com.ripple.generator;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.config.Context;

import java.io.File;

public class GeneratorConfig {
    private Context context;
    private String targetPackage;
    private String targetProject;
    private String author;
    private String daoPackage;
    private String daoPath;
    private String entityPackage;
    private String entityPath;
    private String paramPackage;
    private String paramPath;
    private String servicePackage;
    private String servicePath;
    private IntrospectedTable introspectedTable;

    GeneratorConfig(Context context,IntrospectedTable introspectedTable,String targetPackage,String targetProject,String auth){
        this.introspectedTable = introspectedTable;
        this.context = context;
        this.targetPackage=targetPackage;
        this.targetProject=targetProject;
        this.author = auth;
        this.daoPackage =  this.targetPackage+".dao";
        this.daoPath =  this.targetProject+ File.separator+"dao";
        this.entityPackage =  this.targetPackage+".entity";
        this.entityPath =  this.targetProject+ File.separator+"entity";
        this.paramPackage =  this.targetPackage+".param";
        this.paramPath =  this.targetProject+ File.separator+"param";
        this.servicePackage =  this.targetPackage+".service";
        this.servicePath =  this.targetProject+ File.separator+"service";

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

    public void setTargetProject(String targetProject) {
        this.targetProject = targetProject;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDaoPackage() {
        return daoPackage;
    }

    public void setDaoPackage(String daoPackage) {
        this.daoPackage = daoPackage;
    }

    public String getDaoPath() {
        return daoPath;
    }

    public void setDaoPath(String daoPath) {
        this.daoPath = daoPath;
    }

    public String getEntityPackage() {
        return entityPackage;
    }

    public void setEntityPackage(String entityPackage) {
        this.entityPackage = entityPackage;
    }

    public String getEntityPath() {
        return entityPath;
    }

    public void setEntityPath(String entityPath) {
        this.entityPath = entityPath;
    }

    public String getParamPackage() {
        return paramPackage;
    }

    public void setParamPackage(String paramPackage) {
        this.paramPackage = paramPackage;
    }

    public String getParamPath() {
        return paramPath;
    }

    public void setParamPath(String paramPath) {
        this.paramPath = paramPath;
    }

    public String getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
    }

    public String getServicePath() {
        return servicePath;
    }

    public void setServicePath(String servicePath) {
        this.servicePath = servicePath;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public IntrospectedTable getIntrospectedTable() {
        return introspectedTable;
    }

    public void setIntrospectedTable(IntrospectedTable introspectedTable) {
        this.introspectedTable = introspectedTable;
    }
}
