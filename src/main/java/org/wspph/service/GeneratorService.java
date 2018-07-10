package org.wspph.service;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wspph.mapper.GeneratorMapper;
import org.wspph.util.GenUtils;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * Created by wangjh on 2018/7/7
 */
@Service
public class GeneratorService {

    @Autowired
    private GeneratorMapper generatorMapper;

    public byte[] generatorCode(List<String> tableNames){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for(String tableName : tableNames){
            //查询表信息
            Map<String, String> table = generatorMapper.get(tableName);
            //查询列信息
            List<Map<String, String>> columns = generatorMapper.listColumns(tableName);
            //生成代码
            GenUtils.generatorCode(table, columns, zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    public List<Map<String, Object>> list(Map<String, Object> map){
        return generatorMapper.list(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return generatorMapper.count(map);
    }
}
