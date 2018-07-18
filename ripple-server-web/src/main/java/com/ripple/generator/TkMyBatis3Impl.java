package com.ripple.generator;

import com.google.common.base.CaseFormat;
import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;


public class TkMyBatis3Impl  extends IntrospectedTableMyBatis3Impl {
    @Override
    protected String calculateMyBatis3XmlMapperFileName() {
        StringBuilder sb = new StringBuilder();
        sb.append(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, fullyQualifiedTable.getIntrospectedTableName()));
        sb.append("Dao.xml");
        return sb.toString();
    }
}