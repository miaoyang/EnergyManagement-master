package org.zaiqingyang.energymanagement.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by yangz on 2016/12/9.
 */

public class LoginVerify {
    /**
     * 登录
     * @param userid
     * @param password
     * @return
     */
    public static boolean login(String userid,String password){
        String loginPath = "http://zaiqingyang.org/APKService/user/login";
        String requestBody = "userid="+userid+"&password="+password;
        HttpURLConnection con = null;
        InputStream in = null;
        BufferedReader reader = null;
        PrintWriter printWriter = null;
        URL url ;
        try {
            url = new URL(loginPath);
            con = (HttpURLConnection) url.openConnection();
            //设置超时
            con.setReadTimeout(5*1000);
            //设置请求类型
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            //传入参数
            printWriter = new PrintWriter(con.getOutputStream());
            // 发送请求参数
            printWriter.write(requestBody.toString());
            printWriter.flush();
            printWriter.close();
            //获取服务器返回的状态码
            int status = con.getResponseCode();
            System.out.println(status);
            if(status == 200){
                in = con.getInputStream();
                reader = new BufferedReader(new InputStreamReader(in));
                String data;
                while((data = reader.readLine())!=null){
                    return parseJson(data);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(reader!=null){
                    reader.close();
                }if(printWriter!=null){
                    printWriter.close();
                }if (in!=null){
                    in.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    return false;
    }
    private static boolean parseJson(String jsonStr){
        try{
            JSONArray jsonArray = new JSONArray(jsonStr);
            for (int i = 0; i < jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int status = jsonObject.getInt("status");
                if(status == 1){
                    return true;
                }else {
                    return false;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
