package com.ripple.generator;

import com.google.common.base.CaseFormat;
//import com.ripple.generator.items.DaoGenerator;
import com.ripple.generator.items.DaoGenerator;
import com.ripple.generator.items.DomainGenerator;
import com.ripple.generator.items.ParamGenerator;
import org.codehaus.plexus.util.StringUtils;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.XmlConstants;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

/**
 * @author fendo
 * @version V1.0
 * @Title: MapperPlugin.java
 * @Package org.mybatis.generator.plugins
 * @Description: TODO
 * @date 2017年12月2日 下午5:35:14
 */
public class MapperPlugin extends PluginAdapter {
    private GeneratorConfig generatorConfig;
    // 注释生成器
    private CommentGeneratorConfiguration commentCfg;
    private String targetProject;
    private String targetPackage;

    private String author;

    private ShellCallback shellCallback = null;

    public MapperPlugin() {
        shellCallback = new DefaultShellCallback(false);
    }

    @Override
    public void setContext(Context context) {
        super.setContext(context);
        // 设置默认的注释生成器
        commentCfg = new CommentGeneratorConfiguration();
        commentCfg.setConfigurationType(GenCommentGenerator.class.getCanonicalName());
        context.setCommentGeneratorConfiguration(commentCfg);
        // 支持oracle获取注释#114
        context.getJdbcConnectionConfiguration().addProperty("remarksReporting", "true");
    }

    /**
     * 验证参数是否有效
     *
     * @param warnings
     * @return
     */
    public boolean validate(List<String> warnings) {
        targetProject = properties.getProperty("targetProject");
        targetPackage = properties.getProperty("targetPackage");
        author = properties.getProperty("author");
        if (!stringHasValue(author)) {
            author = "Edwin";
        }
        return stringHasValue(targetProject) && stringHasValue(targetPackage);
    }


    /**
     * 生成mapping 添加自定义sql
     */
    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {

////        //创建Select查询
//        XmlElement select = new XmlElement("select");
//        select.addAttribute(new Attribute("id", "selectAll"));
//        select.addAttribute(new Attribute("resultMap", "BaseResultMap"));
//        select.addAttribute(new Attribute("parameterType", introspectedTable.getBaseRecordType()));
//        select.addElement(new TextElement("select * from "+ introspectedTable.getFullyQualifiedTableNameAtRuntime()));
//
//        XmlElement queryPage = new XmlElement("select");
//        queryPage.addAttribute(new Attribute("id", "queryPage"));
//        queryPage.addAttribute(new Attribute("resultMap", "BaseResultMap"));
//        queryPage.addAttribute(new Attribute("parameterType", "com.fendo.bean.Page"));
//        queryPage.addElement(new TextElement("select * from "+ introspectedTable.getFullyQualifiedTableNameAtRuntime()));
////
//        XmlElement parentElement = document.getRootElement();
//        List<Attribute> attributes = parentElement.getAttributes();
//        Attribute ele = attributes.get(0);
//
//        Document documentEx = new Document(document.getPublicId(), document.getSystemId());
//        parentElement.addElement(select);
//        parentElement.addElement(queryPage);
        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    @Override
    public List<GeneratedXmlFile> contextGenerateAdditionalXmlFiles(
            IntrospectedTable introspectedTable) {
        String tableName = introspectedTable.getFullyQualifiedTable().getIntrospectedTableName();
        String keyName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName);
        List<GeneratedXmlFile> addGeneratedXmlFiles = new ArrayList<>();
        List<GeneratedXmlFile> generatedXmlFiles = introspectedTable.getGeneratedXmlFiles();

        String[] packageNames = tableName.split("_");
        String packageNamesStr = String.join(".", packageNames);
        String pathStr = String.join("/", packageNames);

        String xmlPackage = generatorConfig.getDaoPackage() + "."+packageNamesStr ;
        String domainPackage = generatorConfig.getDomainPackage() + "."+packageNamesStr +"."+ keyName;
        if(generatedXmlFiles.size()>0){
            StringBuilder xmlBuilder = new StringBuilder();
            // 创建基础列
            XmlElement baseColumnListElement = new XmlElement("sql");
            baseColumnListElement.addAttribute(new Attribute("id", "Base_Column_List"));
            xmlBuilder = new StringBuilder();
            List<IntrospectedColumn> columns=introspectedTable.getBaseColumns();
            for(IntrospectedColumn column: columns){
                xmlBuilder.append(column.getActualColumnName()+",");
            }
            if(xmlBuilder.length()>0)
                xmlBuilder.setLength(xmlBuilder.length()-1);
            baseColumnListElement.addElement(new TextElement(xmlBuilder.toString()));

            //创建fetch
            XmlElement fetchElement = new XmlElement("select");
            fetchElement.addAttribute(new Attribute("id", "fetch"));
            fetchElement.addAttribute(new Attribute("resultType", domainPackage));
            fetchElement.addAttribute(new Attribute("parameterType", "java.util.Map"));
            fetchElement.addElement(new TextElement("select "));
            XmlElement includeElement = new XmlElement("include");
            includeElement.addAttribute(new Attribute("refid","Base_Column_List"));
            fetchElement.addElement(includeElement);
            fetchElement.addElement(new TextElement("from "+ introspectedTable.getFullyQualifiedTableNameAtRuntime()));
            fetchElement.addElement(new TextElement("where id=#{id} "));

            Document document = new Document(XmlConstants.MYBATIS3_MAPPER_PUBLIC_ID,XmlConstants.MYBATIS3_MAPPER_SYSTEM_ID);
            XmlElement root =new XmlElement("mapper");
            context.getCommentGenerator().addRootComment(root);
            String createPath = generatorConfig.getXmlPath()+"/"+pathStr;
            File dir =new File(generatorConfig.getXmlPath()+"/"+pathStr);
            dir.mkdirs();
            Attribute attribute = new Attribute("namespace", generatorConfig.getDaoPackage() + "."+packageNamesStr+"."+"I"+keyName+"DAO" );
            root.addAttribute(attribute);
            root.addElement(baseColumnListElement);
            root.addElement(new TextElement("<!--根据id获取对象-->"));
            root.addElement(fetchElement);
            document.setRootElement(root);
            GeneratedXmlFile generatedXmlFileEx = new GeneratedXmlFile(document, keyName+"DAO.xml",introspectedTable.getMyBatis3XmlMapperPackage(),createPath, false,
                    introspectedTable.getContext().getXmlFormatter());
            addGeneratedXmlFiles.add(generatedXmlFileEx);

            // exhtml
             document = new Document(XmlConstants.MYBATIS3_MAPPER_PUBLIC_ID,XmlConstants.MYBATIS3_MAPPER_SYSTEM_ID);
            root =new XmlElement("mapper");
            root.addAttribute(attribute);
            root.addElement(new TextElement("<!--该xml用于编写自定义方法-->"));
            document.setRootElement(root);
            GeneratedXmlFile generatedXmlFileEx2 = new GeneratedXmlFile(document, keyName+"ExDAO.xml",introspectedTable.getMyBatis3XmlMapperPackage(),createPath, false,
                    introspectedTable.getContext().getXmlFormatter());
            addGeneratedXmlFiles.add(generatedXmlFileEx2);

        }
        return addGeneratedXmlFiles;
    }

    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        List<GeneratedJavaFile> mapperJavaFiles = new ArrayList<>();
        String tableName = introspectedTable.getFullyQualifiedTable().getIntrospectedTableName();
        String keyName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName);
        String[] packageNames = tableName.split("_");
        String packageNamesStr = String.join(".", packageNames);
        List<GeneratedJavaFile> entityList = introspectedTable.getGeneratedJavaFiles();
        if(entityList.size()>0){
            GeneratedJavaFile generatedJavaFile = entityList.get(0);
            TopLevelClass sourceItem = (TopLevelClass) generatedJavaFile.getCompilationUnit();
            // 生成domain
            mapperJavaFiles.addAll(DomainGenerator.generator(generatorConfig,sourceItem, tableName, context.getJavaFormatter()));
            // 生成param
            mapperJavaFiles.addAll(ParamGenerator.generator(generatorConfig,sourceItem, tableName, context.getJavaFormatter()));
            // 生成dao
            mapperJavaFiles.add(DaoGenerator.generator(generatorConfig,tableName));
        }
        return mapperJavaFiles;
    }

    @Override
    public void initialized(IntrospectedTable introspectedTable) {
        generatorConfig = new GeneratorConfig(context, introspectedTable, targetPackage, targetProject, author);
        introspectedTable.setMyBatis3XmlMapperPackage("");
    }

}