package com.frw.dev.search;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fruwei on 2016/7/27.
 */
public class ElasticsSearchTest {


    static Logger log = LoggerFactory.getLogger("test");

    public static void main(String args[]) throws UnknownHostException {

        byte[] ipAddr = new byte[]{(byte) 192, (byte) 168, (byte) 99, (byte) 100};
        InetSocketTransportAddress address = new InetSocketTransportAddress(InetAddress.getByAddress(ipAddr), 19300);

        Settings settings = Settings.settingsBuilder()
                .put("client.transport.ping_timeout", "20s")
                .build();

        Client client = TransportClient.builder().settings(settings).build()
                .addTransportAddress(address);


        Map<String, Object> json = new HashMap<String, Object>();
        json.put("user", "frw2");
        json.put("postDate", new Date());
        json.put("message", "test 2");


        String str = JSON.toJSONString(json);
        IndexResponse response = client.prepareIndex("frw", "users", "2").setSource(str).execute().actionGet();


        log.info(JSON.toJSONString(response));

        GetResponse getRep = client.prepareGet("frw", "users", "2").get();


        log.info(JSON.toJSONString(getRep));

        client.close();
    }


}
