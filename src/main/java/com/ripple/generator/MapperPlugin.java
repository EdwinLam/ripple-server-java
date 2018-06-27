package com.ripple.generator;

import com.google.common.base.CaseFormat;
import org.codehaus.plexus.util.StringUtils;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.XmlConstants;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

/**
 * @Title: MapperPlugin.java
 * @Package org.mybatis.generator.plugins
 * @Description: TODO
 * @author fendo
 * @date 2017年12月2日 下午5:35:14
 * @version V1.0
 */
public class MapperPlugin extends PluginAdapter{
    // 注释生成器
    private CommentGeneratorConfiguration commentCfg;
    public static final char UNDERLINE='_';
    private static final String DEFAULT_DAO_SUPER_CLASS = "mybatis.generator.BaseMapper";
    private static final String DEFAULT_EXPAND_DAO_SUPER_CLASS = "com.ripple.base.IBaseDao";
    private static final String DEFAULT_EXPAND_SERVICE_SUPER_CLASS = "com.ripple.base.IBaseService";

    private String daoTargetDir;
    private String daoTargetPackage;

    private String daoSuperClass;

    // 扩展
    private String expandDaoTargetPackage;
    private String expandDaoSuperClass;

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
     * @param warnings
     * @return
     */
    public boolean validate(List<String> warnings) {
        daoTargetDir = properties.getProperty("targetProject");
        boolean valid = stringHasValue(daoTargetDir);

        daoTargetPackage = properties.getProperty("targetPackage");
        boolean valid2 = stringHasValue(daoTargetPackage);

        daoSuperClass = properties.getProperty("daoSuperClass");
        if (!stringHasValue(daoSuperClass)) {
            daoSuperClass = DEFAULT_DAO_SUPER_CLASS;
        }

        expandDaoTargetPackage = properties.getProperty("expandTargetPackage");
        expandDaoSuperClass = properties.getProperty("expandDaoSuperClass");
        if (!stringHasValue(expandDaoSuperClass)) {
            expandDaoSuperClass = DEFAULT_EXPAND_DAO_SUPER_CLASS;
        }
        author = properties.getProperty("author");
        if (!stringHasValue(author)) {
            author = "Edwin";
        }
        return valid && valid2;
    }


    /**
     * 生成mapping 添加自定义sql
     */
    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {

//        //创建Select查询
        XmlElement select = new XmlElement("select");
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
//
        XmlElement parentElement = document.getRootElement();
        List<Attribute> attributes = parentElement.getAttributes();
        Attribute ele = attributes.get(0);

        Document documentEx = new Document(document.getPublicId(),document.getSystemId());
//        parentElement.addElement(select);
//        parentElement.addElement(queryPage);
        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    @Override
    public List<GeneratedXmlFile> contextGenerateAdditionalXmlFiles(
            IntrospectedTable introspectedTable) {
        List<GeneratedXmlFile> addGeneratedXmlFiles = new ArrayList<>();
        List<GeneratedXmlFile> generatedXmlFiles = introspectedTable.getGeneratedXmlFiles();
        String keyName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL,introspectedTable.getFullyQualifiedTable().getIntrospectedTableName());
        String packagePath =daoTargetPackage.replaceAll(escapeExprSpecialWord("."),"\\\\");
        File daoExFile = new File(daoTargetDir+File.separator+packagePath+File.separator+"entity",  keyName + "Ex"+".java");

        if(generatedXmlFiles.size()>0 && !daoExFile.exists()){
            GeneratedXmlFile generatedXmlFile = generatedXmlFiles.get(0);
            Document document = new Document(XmlConstants.MYBATIS3_MAPPER_PUBLIC_ID,XmlConstants.MYBATIS3_MAPPER_SYSTEM_ID);
            XmlElement root =new XmlElement("mapper");
            context.getCommentGenerator().addRootComment(root);
            Attribute attribute = new Attribute("namespace",introspectedTable.getMyBatis3SqlMapNamespace());
            root.addAttribute(attribute);
            document.setRootElement(root);
            String fileName =introspectedTable.getMyBatis3XmlMapperFileName().replace("Dao.xml","ExDao.xml");
            GeneratedXmlFile generatedXmlFileEx = new GeneratedXmlFile(document, fileName,introspectedTable.getMyBatis3XmlMapperPackage(),generatedXmlFile.getTargetProject(), generatedXmlFile.isMergeable(),
                    introspectedTable.getContext().getXmlFormatter());
            addGeneratedXmlFiles.add(generatedXmlFileEx);
        }
        return addGeneratedXmlFiles;
    }

    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        JavaFormatter javaFormatter = context.getJavaFormatter();
        List<GeneratedJavaFile> mapperJavaFiles = new ArrayList<GeneratedJavaFile>();
        // 初始化各种路径
        String entityPackage = daoTargetPackage + ".entity.";
        String keyName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL,introspectedTable.getFullyQualifiedTable().getIntrospectedTableName());
        String packagePath =daoTargetPackage.replaceAll(escapeExprSpecialWord("."),"\\\\");

        String[] checkDirs = new String[]{"dao","entity","service","param"};
        for(String checkDir : checkDirs){
            File dir =new File(daoTargetPackage+"."+checkDir);
            dir.mkdirs();
        }

        File daoExFile = new File(daoTargetDir+File.separator+packagePath+File.separator+"entity",  keyName + "Ex"+".java");

        if (!daoExFile.exists()|| true) {
            // 生成entity扩展类
            String modelExPackage = entityPackage + keyName + "Ex";
            FullyQualifiedJavaType entitySuperType = new FullyQualifiedJavaType(entityPackage + keyName);
            TopLevelClass topLevelClass = new TopLevelClass(modelExPackage);
            topLevelClass.addImportedType(entitySuperType);
            topLevelClass.setSuperClass(entitySuperType);
            topLevelClass.setVisibility(JavaVisibility.PUBLIC);
            topLevelClass.addJavaDocLine("/**");
            topLevelClass.addJavaDocLine(" * " + introspectedTable.getRemarks() + "[扩展]");
            topLevelClass.addJavaDocLine(" * @author " + author);
            topLevelClass.addJavaDocLine(" */");
            GeneratedJavaFile modelExFile = new GeneratedJavaFile(topLevelClass, daoTargetDir, javaFormatter);
            mapperJavaFiles.add(modelExFile);

            // 生成param
            List<GeneratedJavaFile> entityList = introspectedTable.getGeneratedJavaFiles();
            if(entityList.size()>0){
                String paramPackage = daoTargetPackage + ".param." + keyName + "Param";
                TopLevelClass entityItem =(TopLevelClass) entityList.get(0).getCompilationUnit();
                TopLevelClass paramClass = new TopLevelClass(paramPackage);
                for(FullyQualifiedJavaType importedType: entityItem.getImportedTypes()){
                    paramClass.addImportedType(importedType);
                }
                for(Field field: entityItem.getFields()) {
                    FullyQualifiedJavaType StringType = new FullyQualifiedJavaType("String");
                    Field columnField = new Field("F_" + field.getName(), StringType);
                    columnField.setVisibility(JavaVisibility.PUBLIC);
                    columnField.setInitializationString("\""+field.getName()+"\"");
                    columnField.setStatic(true);
                    columnField.setFinal(true);
                    paramClass.addField(columnField);
                }
                for(Field field: entityItem.getFields()){
                    paramClass.addField(field);
                }
                for(Method method: entityItem.getMethods()){
                    if(method.getName().equals("equals")){
                        continue;
                    }
                    paramClass.addMethod(method);
                }
                GeneratedJavaFile paramFile = new GeneratedJavaFile(paramClass, daoTargetDir, javaFormatter);
                mapperJavaFiles.add(paramFile);

                // 生成paramEx
                String paramExPackage = daoTargetPackage + ".param." + keyName + "ParamEx";
                TopLevelClass paramExClass = new TopLevelClass(paramExPackage);
                paramExClass.setSuperClass(paramClass.getType());
                paramExClass.addImportedType(paramClass.getType());
                GeneratedJavaFile paramExFile = new GeneratedJavaFile(paramExClass, daoTargetDir, javaFormatter);
                mapperJavaFiles.add(paramExFile);

            }



            // dao类
            String daoInterfacePackage = daoTargetPackage + ".dao."+keyName+ "DAO";
            Interface daoInterface = new Interface(daoInterfacePackage);
            daoInterface.setVisibility(JavaVisibility.PUBLIC);
            daoInterface.addJavaDocLine("/**");
            daoInterface.addJavaDocLine(" * " + introspectedTable.getRemarks() );
            daoInterface.addJavaDocLine(" * @author " + author );
            daoInterface.addJavaDocLine(" */");
            FullyQualifiedJavaType daoSuperType = new FullyQualifiedJavaType(expandDaoSuperClass);
            daoSuperType.addTypeArgument(entitySuperType);
            daoInterface.addImportedType(entitySuperType);
            daoInterface.addSuperInterface(daoSuperType);
            daoInterface.getType().getPackageName();
            GeneratedJavaFile daoFile = new GeneratedJavaFile(daoInterface, daoTargetDir, javaFormatter);
            mapperJavaFiles.add(daoFile);

            // service
            String serviceInterfacePackage = daoTargetPackage + ".service.I" + keyName + "Service";
            Interface serviceInterface = new Interface(serviceInterfacePackage);
            serviceInterface.setVisibility(JavaVisibility.PUBLIC);
            serviceInterface.addJavaDocLine("/**");
            serviceInterface.addJavaDocLine(" * " + introspectedTable.getRemarks());
            serviceInterface.addJavaDocLine(" * @author " + author);
            serviceInterface.addJavaDocLine(" */");
            FullyQualifiedJavaType serviceSuperType = new FullyQualifiedJavaType(DEFAULT_EXPAND_SERVICE_SUPER_CLASS);
            serviceSuperType.addTypeArgument(daoInterface.getType());
//            serviceSuperType.addTypeArgument(entitySuperType);
            serviceInterface.addImportedType(entitySuperType);
            serviceInterface.addImportedType(daoInterface.getType());
            serviceInterface.addImportedType(serviceSuperType);
            serviceInterface.addSuperInterface(serviceSuperType);
            GeneratedJavaFile serviceFile = new GeneratedJavaFile(serviceInterface, daoTargetDir, javaFormatter);
            mapperJavaFiles.add(serviceFile);

            // serviceImpl
            String serviceImplPackage = daoTargetPackage + ".service." + keyName + "ServiceImpl";
            TopLevelClass serviceImpl = new TopLevelClass(serviceImplPackage);
            serviceImpl.addImportedType("org.springframework.stereotype.Service");
            serviceImpl.addImportedType("org.springframework.beans.factory.annotation.Autowired");
            serviceImpl.addImportedType(serviceInterface.getType());
            serviceImpl.addImportedType(daoInterface.getType());
            serviceImpl.addSuperInterface(serviceInterface.getType());
            serviceImpl.setVisibility(JavaVisibility.PUBLIC);
            serviceImpl.addJavaDocLine("/**");
            serviceImpl.addJavaDocLine(" * " + introspectedTable.getRemarks() );
            serviceImpl.addJavaDocLine(" * @author " + author);
            serviceImpl.addJavaDocLine(" */");
            String keyNameMe = keyName.substring(0,1).toLowerCase()+keyName.substring(1)+"Dao";

            Field daoField = new Field(keyNameMe,daoInterface.getType());
            daoField.setVisibility(JavaVisibility.PRIVATE);
            daoField.addAnnotation("@Autowired");
            serviceImpl.addField(daoField);

            serviceImpl.addAnnotation("@Service(\""+ keyName + "ServiceImpl"+"\")");
            GeneratedJavaFile serviceImplFile = new GeneratedJavaFile(serviceImpl, daoTargetDir, javaFormatter);
            mapperJavaFiles.add(serviceImplFile);

        }
        return mapperJavaFiles;
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

    @Override
    public void initialized(IntrospectedTable introspectedTable) {
        introspectedTable.setMyBatis3XmlMapperPackage("");
    }

}