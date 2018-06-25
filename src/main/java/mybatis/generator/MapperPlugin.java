package mybatis.generator;

import org.codehaus.plexus.util.StringUtils;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
    private static final String DEFAULT_EXPAND_DAO_SUPER_CLASS = "com.ripple.dao.base.BaseDao";
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

        //创建Select查询
        XmlElement select = new XmlElement("select");
        select.addAttribute(new Attribute("id", "selectAll"));
        select.addAttribute(new Attribute("resultMap", "BaseResultMap"));
        select.addAttribute(new Attribute("parameterType", introspectedTable.getBaseRecordType()));
        select.addElement(new TextElement("select * from "+ introspectedTable.getFullyQualifiedTableNameAtRuntime()));

        XmlElement queryPage = new XmlElement("select");
        queryPage.addAttribute(new Attribute("id", "queryPage"));
        queryPage.addAttribute(new Attribute("resultMap", "BaseResultMap"));
        queryPage.addAttribute(new Attribute("parameterType", "com.fendo.bean.Page"));
        queryPage.addElement(new TextElement("select * from "+ introspectedTable.getFullyQualifiedTableNameAtRuntime()));

        XmlElement parentElement = document.getRootElement();
        parentElement.addElement(select);
        parentElement.addElement(queryPage);
        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        JavaFormatter javaFormatter = context.getJavaFormatter();
        List<GeneratedJavaFile> mapperJavaFiles = new ArrayList<GeneratedJavaFile>();
        // 初始化各种路径
        String modelPackage = daoTargetPackage + ".model";
        // 获取表名
        String tableNamesStr = introspectedTable.getFullyQualifiedTable().getIntrospectedTableName();
        String[] tableNames = tableNamesStr.split("_");
        // 获取目录路径
        String basePackage = daoTargetPackage;
        String packagePath =daoTargetPackage.replaceAll(escapeExprSpecialWord("."),"\\\\");
        String daoPath = daoTargetDir+File.separator+packagePath+File.separator+"dao";
        String tablePath = daoPath;
        String tablePackage = "";
        for(int i=0;i<tableNames.length;i++){
            if(i!=tableNames.length-1) {
                tablePath += File.separator + tableNames[i];
                tablePackage += tableNames[i] + ".";
            }
        }
        File dir =new File(tablePath);
        dir.mkdirs();
        // 生成Model扩展类
        String modelExPackage = modelPackage+tablePackage+underlineToCamel(tableNamesStr) + "Ex";
        TopLevelClass topLevelClass = new TopLevelClass(modelExPackage);
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * " + introspectedTable.getRemarks() );
        topLevelClass.addJavaDocLine(" * @author " + author );
        topLevelClass.addJavaDocLine(" */");
        GeneratedJavaFile modelExFile = new GeneratedJavaFile(topLevelClass, daoTargetDir, javaFormatter);
        mapperJavaFiles.add(modelExFile);

        // dao类
        String daoInterfacePackage = daoTargetPackage + ".dao."+tablePackage+underlineToCamel(tableNamesStr) + "DAO";
        System.out.println(daoInterfacePackage);
        Interface daoInterface = new Interface(daoInterfacePackage);
        daoInterface.setVisibility(JavaVisibility.PUBLIC);
        daoInterface.addJavaDocLine("/**");
        daoInterface.addJavaDocLine(" * " + introspectedTable.getRemarks() );
        daoInterface.addJavaDocLine(" * @author " + author );
        daoInterface.addJavaDocLine(" */");
        FullyQualifiedJavaType daoSuperType = new FullyQualifiedJavaType(expandDaoSuperClass);
        daoSuperType.addTypeArgument(daoInterface.getType());
        daoInterface.addImportedType(daoSuperType);
        daoInterface.addSuperInterface(daoSuperType);
        daoInterface.getType().getPackageName();
        System.out.println(daoPath);
        GeneratedJavaFile daoFile = new GeneratedJavaFile(daoInterface, daoTargetDir, javaFormatter);
        mapperJavaFiles.add(daoFile);



//        for (GeneratedJavaFile javaFile : introspectedTable.getGeneratedJavaFiles()) {
//            CompilationUnit unit = javaFile.getCompilationUnit();
//            FullyQualifiedJavaType baseModelJavaType = unit.getType();
//
//            String shortName = baseModelJavaType.getShortName();
//
//            GeneratedJavaFile mapperJavafile = null;
//
//            if (shortName.endsWith("Mapper")) { // 扩展Mapper
//                if (stringHasValue(expandDaoTargetPackage)) {
//                    Interface mapperInterface = new Interface(
//                            expandDaoTargetPackage + "." + shortName.replace("Mapper", "ExpandMapper"));
//                    mapperInterface.setVisibility(JavaVisibility.PUBLIC);
//                    mapperInterface.addJavaDocLine("/**");
//                    mapperInterface.addJavaDocLine(" * " + shortName + "扩展");
//                    mapperInterface.addJavaDocLine(" */");
//
//                    FullyQualifiedJavaType daoSuperType = new FullyQualifiedJavaType(expandDaoSuperClass);
//                    mapperInterface.addImportedType(daoSuperType);
//                    mapperInterface.addSuperInterface(daoSuperType);
//
//                    mapperJavafile = new GeneratedJavaFile(mapperInterface, daoTargetDir, javaFormatter);
//                    try {
//                        File mapperDir = shellCallback.getDirectory(daoTargetDir, daoTargetPackage);
//                        File mapperFile = new File(mapperDir, mapperJavafile.getFileName());
//                        // 文件不存在
//                        if (!mapperFile.exists()) {
//                            mapperJavaFiles.add(mapperJavafile);
//                        }
//                    } catch (ShellException e) {
//                        e.printStackTrace();
//                    }
//                }
//            } else if (!shortName.endsWith("Example")) { // CRUD Mapper
//                Interface mapperInterface = new Interface(daoTargetPackage + "." + shortName + "Mapper");
//
//                mapperInterface.setVisibility(JavaVisibility.PUBLIC);
//                mapperInterface.addJavaDocLine("/**");
//                mapperInterface.addJavaDocLine(" * MyBatis Generator工具自动生成");
//                mapperInterface.addJavaDocLine(" */");
//
//                FullyQualifiedJavaType daoSuperType = new FullyQualifiedJavaType(daoSuperClass);
//                // 添加泛型支持
//                daoSuperType.addTypeArgument(baseModelJavaType);
//                mapperInterface.addImportedType(baseModelJavaType);
//                mapperInterface.addImportedType(daoSuperType);
//                mapperInterface.addSuperInterface(daoSuperType);
//
//                mapperJavafile = new GeneratedJavaFile(mapperInterface, daoTargetDir, javaFormatter);
//                mapperJavaFiles.add(mapperJavafile);
//
//            }
//        }
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

    public static String underlineToCamel(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=param.charAt(i);
            if (c==UNDERLINE){
                if (++i<len){
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            }else{
                sb.append(i==0?Character.toUpperCase(c):c);
            }
        }
        return sb.toString();
    }

}