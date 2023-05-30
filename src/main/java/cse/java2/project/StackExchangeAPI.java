package cse.java2.project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

public class StackExchangeAPI {
    public static void main(String[] args) {
        StringBuilder response = new StringBuilder();
        String filePath = "D:\\Sources\\2B\\JAVA2\\project\\2023-Spring-Java2-Project-Demo\\data\\data.json";
        try {
            for (int i = 1; i <= 6; i++) {
                String apiUrl = "https://api.stackexchange.com/2.3/questions?page=" + i
                        + "&pagesize=100&order=desc&sort=activity&tagged=java&site=stackoverflow&filter=!lZlIO2jDLprEBU-0rnumi(dLidjK3vXY*X-V0zK7c3-s*mdgcfb(QKD3";
                // 创建URL对象
                URL url = new URL(apiUrl);

                // 创建HTTP连接
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                // 发送请求并获取响应状态码
                int responseCode = conn.getResponseCode();

                // 如果响应成功，读取响应内容并保存到文件
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(new GZIPInputStream(conn.getInputStream())));
                    // BufferedReader in = new BufferedReader(new
                    // InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                } else {
                    System.out.println("请求失败，响应状态码：" + responseCode);
                    return;
                }
                // 将JSON响应保存到文件
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(response.toString());
            writer.close();
            System.out.println("JSON数据已保存到文件：" + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
