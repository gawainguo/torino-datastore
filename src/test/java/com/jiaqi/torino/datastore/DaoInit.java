package com.jiaqi.torino.datastore;

import java.util.ArrayList;
import java.util.List;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class DaoInit {

    public static void main(String[] args) throws Exception {

        List<String> warns = new ArrayList<String>();
        ConfigurationParser cp = new ConfigurationParser(warns);
        Configuration config = cp
                .parseConfiguration(
                        DaoInit.class.getClassLoader()
                                .getResourceAsStream("generator/mybatis-generator-config.xml"));
        DefaultShellCallback shellCallback = new DefaultShellCallback(true);
        MyBatisGenerator generator = new MyBatisGenerator(config, shellCallback, warns);
        generator.generate(null);
        boolean res = true;
        StringBuilder note = new StringBuilder();
        for (String string : warns) {
            res = false;
            note.append(string).append("\n");
        }
        if (!res) {
            System.out.println(note.toString());
        }
    }
}
