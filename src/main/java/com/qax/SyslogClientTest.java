package com.qax;

import com.alibaba.fastjson.JSONObject;
import org.productivity.java.syslog4j.Syslog;
import org.productivity.java.syslog4j.SyslogConstants;
import org.productivity.java.syslog4j.SyslogIF;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class SyslogClientTest {
    // 目标Syslog服务器ip
    private static final String HOST = "127.0.0.1";
    // 目标Syslog服务器端口
    private static final int PORT = 514;

    // Syslog编码
    private static final String ENCODING = "utf-8";

    public void generate() {
        SyslogIF syslog = Syslog.getInstance(SyslogConstants.UDP);
        syslog.getConfig().setHost(HOST);
        syslog.getConfig().setPort(PORT);

        JSONObject msgJson = new JSONObject();
        // 操作日志字段数据
        msgJson.put("occur_time", System.currentTimeMillis());
        msgJson.put("system_name", "XXX平台");
        msgJson.put("system_ip", "156.10.12.56");
        msgJson.put("system_ipv4", "156.10.12.56");
        msgJson.put("op_user_name", "");
        msgJson.put("system_ipv6", "admin");
        msgJson.put("op_user_company", "浙江省纪委");
        msgJson.put("op_user_role", "审计员");
        msgJson.put("op_user_ip", "10.91.7.45");
        msgJson.put("op_user_ipv4", "10.91.7.45");
        msgJson.put("op_user_ipv6", "2001:0DB8:0000:0023:0008:0800:200C:417A");
        msgJson.put("op_time", System.currentTimeMillis());
        msgJson.put("op_type", "增加");
        msgJson.put("op_module", "一级模块/二级模块");
        msgJson.put("op_content", "新增记录");
        msgJson.put("op_record", "json文件");
        msgJson.put("op_result", "是");
        msgJson.put("sens_data", "15577787957");
        msgJson.put("is_encryption", "是");
        msgJson.put("encr_method", "MD5");

        try {
            syslog.log(0, URLDecoder.decode(msgJson.toJSONString(), ENCODING));
            System.out.println("generate log: " + msgJson.toJSONString());
        } catch (UnsupportedEncodingException e) {
            System.out.println("generate log get exception " + e);
        }
        System.out.println("--  success --");
    }

    public static void main(String[] args) {
        new SyslogClientTest().generate();
    }

}
