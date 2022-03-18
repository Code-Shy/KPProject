package pers.gqh.kp;

import pers.gqh.kp.entity.Data;
import pers.gqh.kp.utils.DataUtils;
import pers.gqh.kp.utils.PicUtils;

import java.io.IOException;
import java.util.HashMap;
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
        Data data;
        int dataIndex;
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
        PicUtils.scatterPlotPaint(data);

    }

    static void fileShow() {
        for (int i = 0; i < 10; i++){
            System.out.print(i+ " - beibao" + i + ".in    ");
        }
        System.out.println();
    }


    static void dataShow(Data data, int number) {
        System.out.println("beibao" + number + ".in的数据" );
        System.out.print("数据个数:" + data.getN());
        System.out.println("    背包容量:" + data.getC());
        System.out.print("重量 ");
        for (int i = 0; i < data.getN(); i++) {
            System.out.print(data.getW().get(i) + " ");
        }
        System.out.println();
        System.out.print("价值 ");
        for (int i = 0; i < data.getN(); i++) {
            System.out.print(data.getV().get(i) + " ");
        }
        System.out.println();
       // System.out.println();

    }
}
