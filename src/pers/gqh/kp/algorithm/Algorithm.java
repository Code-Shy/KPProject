package pers.gqh.kp.algorithm;

import java.util.Arrays;

/**
 * @Package pers.gqh.kp.algorithm
 * @Description
 * @Author Simple OR XXX
 * @Date 2022/3/17
 **/
public class Algorithm {

    public static void main(String[] args) {
        int[] w = {79, 58, 86, 11, 28, 62, 15, 68};
        int[] v = {83, 14, 54, 79, 72, 52, 48, 62};
        System.out.println(KnapsackDP(w, v, w.length, 200));
    }

    public static int KnapsackDP(int[] w, int[] v, int n, int C) {
        int[][] V = new int[n + 1][C + 1];
        int[] x = new int[n];
        for (int i = 0; i <= n; i++)//初始化第0列
            V[i][0] = 0;
        for (int j = 0; j <= C; j++)//初始化第0行
            V[0][j] = 0;
        for (int i = 1; i <= n; i++) {//计算第i行，进行第i次迭代
            for (int j = 1; j <= C; j++) {
                if (j < w[i-1]) {
                    V[i][j] = V[i - 1][j];
                } else {
                    V[i][j] = Math.max(V[i - 1][j], V[i - 1][j - w[i-1]] + v[i-1]);
                }
            }
        }
        for (int j = C, i = n; i > 0; i--) {//求装入背包的物品
            if (V[i][j] > V[i - 1][j]) {
                x[i-1] = 1;
                j = j - w[i-1];
            } else {
                x[i-1] = 0;
            }
        }
        System.out.println(Arrays.toString(x));
        return V[n][C];//返回背包取得的最大价值
    }

    public static void KnapsackGreedy() {

    }


    public static void KnapsackBT() {

    }
}
