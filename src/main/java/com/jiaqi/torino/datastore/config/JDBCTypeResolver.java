package com.jiaqi.torino.datastore.config;

import java.sql.Types;
import java.time.ZonedDateTime;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

public class JDBCTypeResolver extends JavaTypeResolverDefaultImpl {

    public JDBCTypeResolver() {
        super();
        super.typeMap.put(Types.DATE,
                new JdbcTypeInformation("DATE", new FullyQualifiedJavaType(ZonedDateTime.class.getName())));
        super.typeMap.put(Types.TIMESTAMP, new JdbcTypeInformation("TIMESTAMP",
                new FullyQualifiedJavaType(ZonedDateTime.class.getName())));
    }
}