package pers.gqh.kp.utils;

import java.io.*;
import java.util.List;

/**
 * @Package pers.gqh.kp.utils
 * @Description
 * @Author Simple OR XXX
 * @Date 2022/3/17
 **/
public class Utils {

    /**
     *
     * @param fileName
     * @throws IOException
     */
    public static void loadData (String fileName, List w, List v) throws IOException {
        try (FileReader reader = new FileReader(fileName);
             BufferedReader br = new BufferedReader(reader)) {
            String line;
            while ((line = br.readLine()) != null) {
                // 一次读入一行数据
                String[] s =line.split(" ");
                w.add(s[0]);
                v.add(s[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(w);
        System.out.println(v);
    }



}
