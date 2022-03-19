package pers.gqh.kp;

import pers.gqh.kp.algorithm.BT;
import pers.gqh.kp.algorithm.DP;
import pers.gqh.kp.algorithm.Greedy;
import pers.gqh.kp.entity.Data;
import pers.gqh.kp.utils.DataUtils;
import pers.gqh.kp.utils.PicUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @Package com.nwnu.kp
 * @Description
 * @Author Simple
 * @Date 2022/3/17
 **/
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        //数据
        Data data;
        //文件序号
        int dataIndex;
        //方法序号
        int methodIndex;
        //时间记录
        long oldTime;
        long newTime;


        System.out.println("=========== 0-1 Knapsack problem solving system ===========");
        System.out.println("请选择要查看的实验数据:");
        fileShow();
        dataIndex = sc.nextInt();
        data = DataUtils.loadData(dataIndex);
        dataShow(data, dataIndex);

        System.out.println("请选择要绘制散点图的实验数据:");
        fileShow();
        dataIndex = sc.nextInt();
        data = DataUtils.loadData(dataIndex);
        PicUtils.scatterPlotPaint(data, dataIndex);

        System.out.println("请选择要按照价值重量比比进行非递增排序的实验数据:");
        fileShow();
        dataIndex = sc.nextInt();
        data = DataUtils.loadData(dataIndex);
        int[] w = new int[data.getN()];
        int[] v = new int[data.getN()];
        //Integer转换为int
        for (int i = 0; i < data.getN(); i++) {
            w[i] = Integer.parseInt(data.getW().get(i).toString());
            v[i] = Integer.parseInt(data.getV().get(i).toString());
        }
        double[][] orderSeq = DataUtils.sortData(w, v);
        sortingDataShow(orderSeq, dataIndex);

        System.out.println("选择要求解的数据文件");
        fileShow();
        dataIndex = sc.nextInt();
        data = DataUtils.loadData(dataIndex);

        System.out.println("选择求解的方法");
        methodShow();
        methodIndex = sc.nextInt();
        int[] resVector = new int[data.getN()];
        w = new int[data.getN()];
        v = new int[data.getN()];
        int res;
        boolean flag = false;
        //Integer转换为int
        for (int i = 0; i < data.getN(); i++) {
            w[i] = Integer.parseInt(data.getW().get(i).toString());
            v[i] = Integer.parseInt(data.getV().get(i).toString());
        }
        if (methodIndex == 0) {
            flag = true;
            oldTime = System.nanoTime();
            res = DP.KnapsackDP(w, v, w.length, data.getC(), resVector);
            newTime = System.nanoTime();
        } else if (methodIndex == 1) {
            flag = true;
            double[][] sortingData = DataUtils.sortData(w, v);
            for (int i = 0; i < data.getN(); i++) {
                w[i] = (int) sortingData[0][i];
                v[i] = (int) sortingData[1][i];
            }
            System.out.println(Arrays.toString(w));
            System.out.println(Arrays.toString(v));

            oldTime = System.nanoTime();
            res = Greedy.KnapsackGreedy(w, v, data.getC(), resVector);
            newTime = System.nanoTime();

        } else if (methodIndex == 2) {
            flag = true;
            BT.wight = w;
            BT.value = v;
            BT.C = data.getC();
            BT.n = data.getN();
            BT.ans = new int[BT.n];
            BT.temp = new int[BT.n];
            oldTime = System.nanoTime();
            BT.backtrack(0, 0, 0);
            newTime = System.nanoTime();
            res = BT.maxValue;
            resVector = BT.ans;
        } else {
            flag = false;
            oldTime = 0;
            newTime = 0;
            res = 0;
            System.out.print("输入错误,请重新输入 :");
            //methodIndex = sc.nextInt();
        }
        if (flag == true) {
            System.out.println("求解时间: " + (double) (newTime - oldTime) / 1000000000 + "s");
            System.out.println("最优解: " + res);
            System.out.println("经过排序后的物品:　" + Arrays.toString(w));
            System.out.println("解向量: " + Arrays.toString(resVector));
        }
    }


    static void fileShow() {
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " - beibao" + i + ".in    ");
        }
        System.out.println();
    }

    static void methodShow() {
        System.out.println("0 - 动态规划    1 - 贪心    2 - 回溯");
    }


    static void sortingDataShow(double[][] orderSeq, int dataIndex) {
        System.out.println("beibao" + dataIndex + ".in排序后的数据");
        System.out.println("重量   价值  价值重量比");
        for (int i = 0; i < orderSeq[0].length; i++) {
            System.out.printf("%-5.0f %-4.0f %-3f \n", orderSeq[0][i], orderSeq[1][i], orderSeq[2][i]);
        }
    }

    static void dataShow(Data data, int number) {
        System.out.println("beibao" + number + ".in的数据");
        System.out.print("物品个数:" + data.getN());
        System.out.println("    背包容量:" + data.getC());
        System.out.println("重量 价值");
        for (int i = 0; i < data.getN(); i++) {
            System.out.printf("%-4s %-4s \n", data.getW().get(i), data.getV().get(i) + " ");
        }
    }
}
