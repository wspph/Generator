package org.wspph.web;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wspph.service.GeneratorService;
import org.wspph.util.GenUtils;
import org.wspph.util.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by wangjh on 2018/7/7
 */
@Controller
public class GeneratorController {

    @Autowired
    private GeneratorService service;

    @GetMapping("/index")
    String index(Model model) {
        Configuration conf = GenUtils.getConfig();
        Map<String, Object> property = new HashMap<>(16);
        property.put("author", conf.getProperty("author"));
        property.put("package", conf.getProperty("package"));
        property.put("autoRemovePre", conf.getProperty("autoRemovePre"));
        property.put("tablePrefix", conf.getProperty("tablePrefix"));
        model.addAttribute("property", property);
        return "index";
    }

    @GetMapping("list")
    @ResponseBody
    String list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        Map resultMap = new HashMap();
        resultMap.put("data",service.list(query));
        resultMap.put("code",0);
        resultMap.put("count",service.queryTotal(query));
        return JSON.toJSONString(resultMap);
    }

    @RequestMapping(path = "/code",method = RequestMethod.GET)
    public void code(HttpServletRequest request, HttpServletResponse response) throws IOException {
        byte[] data = service.generatorCode(Arrays.asList(request.getParameter("tables").split(",")));
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"code.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }

    @PostMapping("modify/strategy")
    String modifyStrategy(String packageName,String author,String autoRemovePre,String tablePre){
        try {
            PropertiesConfiguration conf = new PropertiesConfiguration("generator.properties");
            conf.setProperty("author", author);
            conf.setProperty("package", packageName);
            conf.setProperty("autoRemovePre", autoRemovePre == null?"false":"true");
            conf.setProperty("tablePrefix", tablePre);
            conf.save();
        } catch (ConfigurationException e) {
            return null;
        }
        return "success";
    }
}
