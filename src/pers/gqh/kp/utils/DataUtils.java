package pers.gqh.kp.utils;

import pers.gqh.kp.entity.Data;

import java.io.*;
import java.util.*;

/**
 * @Package pers.gqh.kp.utils
 * @Description
 * @Author Simple OR XXX
 * @Date 2022/3/17
 **/
public class DataUtils {
    /**
     * 将求得的结果写入对应文件
     *
     * @param time      求解时间
     * @param res       最优解
     * @param resVector 解向量
     * @param dataIndex 文件序号
     * @throws IOException
     */
    public static void writeFile(double time, int res, int[] resVector, int dataIndex) throws IOException {
        String filePath = "res/beibao" + dataIndex + ".txt";
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath);
            fileWriter.write("数据集: beibao" + dataIndex + "\n");
            fileWriter.write("最优解: " + res + "\n");
            fileWriter.write("解向量: " + Arrays.toString(resVector) + "\n");
            fileWriter.write("求解时间: " + time + "s");
            System.out.println("数据已写入到KP/res/beibao" + dataIndex + ".txt");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileWriter.close();
        }
    }

    /**
     * 加载数据
     *
     * @param i 文件序号
     * @return data 要读入的数据
     * @throws IOException
     */
    public static Data loadData(int i) throws IOException {
        Data data;
        String filePath = "..\\..\\..\\data\\beibao";
        data = readFile(filePath + i + ".in");
        return data;
    }

    /**
     * 从文件中读入数据
     *
     * @param fileName
     * @return
     * @throws
     */
    public static Data readFile(String fileName) {
        Data data = new Data();
        try (FileReader reader = new FileReader(fileName);
             BufferedReader br = new BufferedReader(reader)) {
            String line;
            int flag = 0;
            while ((line = br.readLine()) != null) {
                // 读入一行数据
                String[] s = line.split(" ");
                if (flag == 0) {
                    //读取背包容量和物品数
                    data.setC(Integer.parseInt(s[0]));
                    data.setN(Integer.parseInt(s[1]));
                    flag = 1;
                } else {
                    //将字符串s通过转化为int类型
                    data.getW().add(Integer.parseInt(s[0]));
                    data.getV().add(Integer.parseInt(s[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;

    }

    /**
     * 将数据按价值重量比非递增排序
     *
     * @param w 物品重量
     * @param v 物品价值
     * @return orderSeq 经过排序后的物品序列，第一维为重量，第二维为价值，第三维为价值重量比
     */
    public static double[][] sortData(int[] w, int[] v) {
        double[] vw = new double[w.length];
        int[] index = new int[w.length];
        //要返回的有序序列
        double[][] orderSeq = new double[3][w.length];

        for (int i = 0; i < w.length; i++) {
            vw[i] = (double) v[i] / (double) w[i];
            index[i] = i;
        }
        double temp;
        for (int i = 0; i < w.length - 1; i++) {
            for (int j = i + 1; j < w.length; j++) {
                if (vw[i] < vw[j]) {
                    temp = vw[i];
                    vw[i] = vw[j];
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
            orderSeq[2][i] = vw[i];
        }

        return orderSeq;
    }


}
