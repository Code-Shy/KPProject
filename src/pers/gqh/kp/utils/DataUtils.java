package pers.gqh.kp.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
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

    /**
     *
     * @param w 物品重量
     * @param v 物品价值
     * @return orderSeq 经过排序后的物品序列，第一维为重量，第二维为价值
     */
    public static int[][] sortData(int[] w, int[] v) {


        double[] vw = new double[w.length];
        int[] index = new int[w.length];
        //要返回的有序序列
        int[][] orderSeq = new int[2][w.length];

        for (int i = 0; i < w.length; i++) {
            vw[i] = (double) v[i] / (double) w[i];
            index[i] = i;
        }
        double temp;
        for (int i = 0; i < w.length - 1; i++) {
            for (int j = i + 1; j < w.length; j++) {
                if (vw[i] < vw[j]) {
                    temp = vw[i];
                    vw [i] = vw[j];
                    vw[j] = temp;
                    //交换i，j的下标
                    int x = index[i];
                    index[i] = index[j];
                    index[j] = x;
                }
            }
        }
        for (int i = 0; i < w.length; i++) {
            orderSeq[0][i] = w[index[i]];
            orderSeq[1][i] = v[index[i]];
        }
        System.out.println(Arrays.toString(orderSeq[0]));
        System.out.println(Arrays.toString(orderSeq[1]));

        return orderSeq;

    }
}
