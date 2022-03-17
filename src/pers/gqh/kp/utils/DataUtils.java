package pers.gqh.kp.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package pers.gqh.kp.utils
 * @Description
 * @Author Simple OR XXX
 * @Date 2022/3/17
 **/
public class DataUtils {
    public static void main(String[] args) throws IOException {
        List weight = new ArrayList();
        List value = new ArrayList();
        loadData("D:\\KP\\Data\\beibao0.in", weight, value);
    }



    /**
     *
     * @param fileName
     * @throws IOException
     */
    public static void loadData (String fileName, List weight, List value) throws IOException {
        try (FileReader reader = new FileReader(fileName);
             BufferedReader br = new BufferedReader(reader)) {
            String line;
            while ((line = br.readLine()) != null) {
                // 一次读入一行数据
                String[] s =line.split(" ");
                weight.add(Integer.parseInt(s[0]));
                value.add(Integer.parseInt(s[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(weight);
        System.out.println(value);
    }


    public static void sortByVW() {

    }
}
