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
        int dataIndex = -1;
        //方法序号
        int methodIndex;
        //时间记录
        long oldTime;
        long newTime;
        double time;
        //功能序号
        int funIndex;



        System.out.println("============================ 0-1 Knapsack problem solving system ============================");
        while (true){
            System.out.println("-------------------------------------- 请选择您需要的功能 --------------------------------------");
            System.out.println("1 - 查看实验数据  2 - 绘制散点图并导出   3 - 对实验数据进行排序   4 - 对实验数据求解并导出结果    0 - 退出");
            funIndex = sc.nextInt();
            switch(funIndex){
                case 0:
                    System.out.println("系统已退出...");
                    return;
                case 1:
                    System.out.println("请选择要查看的实验数据:");
                    while(true){
                        fileShow();
                        dataIndex = sc.nextInt();
                        if (dataIndex < 0 || dataIndex > 9){
                            System.out.println("输入错误，请重新输入");
                        }else {
                            break;
                        }
                    }
                    data = DataUtils.loadData(dataIndex);
                    dataShow(data, dataIndex);
                    break;
                case 2:
                    System.out.println("请选择要绘制散点图的实验数据:");
                    while(true){
                        fileShow();
                        dataIndex = sc.nextInt();
                        if (dataIndex < 0 || dataIndex > 9){
                            System.out.println("输入错误，请重新输入");
                        }else {
                            break;
                        }
                    }
                    data = DataUtils.loadData(dataIndex);
                    PicUtils.scatterPlotPaint(data, dataIndex);
                    break;
                case 3:
                    System.out.println("请选择要按照价值重量比比进行非递增排序的实验数据:");
                    while(true){
                        fileShow();
                        dataIndex = sc.nextInt();
                        if (dataIndex < 0 || dataIndex > 9){
                            System.out.println("输入错误，请重新输入");
                        }else {
                            break;
                        }
                    }
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
                    break;
                case 4:
                    System.out.println("选择要求解的数据文件");
                    while(true){
                        fileShow();
                        dataIndex = sc.nextInt();
                        if (dataIndex < 0 || dataIndex > 9){
                            System.out.println("输入错误，请重新输入");
                        }else {
                            break;
                        }
                    }
                    data = DataUtils.loadData(dataIndex);
                    System.out.println("选择求解的方法");
                    while (true){
                        methodShow();
                        methodIndex = sc.nextInt();
                        if (methodIndex < 0 || methodIndex > 2){
                            System.out.println("输入错误，请重新输入");
                        }else {
                            break;
                        }
                    }
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
                    }
                    if (flag == true) {
                        time = (double) (newTime - oldTime) / 1000000000;
                        System.out.println("求解时间: " + time + "s");
                        System.out.println("最优解: " + res);
                        System.out.println("经过排序后的物品:　" + Arrays.toString(w));
                        System.out.println("解向量: " + Arrays.toString(resVector));
                        DataUtils.writeFile(time, res, resVector, dataIndex);
                    }
                    break;
                default:
                    System.out.println("输入错误，请重新输入:");
            }
        }
    }


    static void fileShow() {
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                System.out.println();
            }
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
