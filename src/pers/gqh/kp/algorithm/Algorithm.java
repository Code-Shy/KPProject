package pers.gqh.kp.algorithm;

import pers.gqh.kp.utils.DataUtils;

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
        System.out.println(KnapsackGreedy(w, v, w.length, 200));
    }

    /**
     *
     * @param w 物品重量
     * @param v 物品价值
     * @param n 物品个数
     * @param C 背包容量
     * @return  最大价值
     */
    public static int KnapsackDP(int[] w, int[] v, int n, int C) {
        int[][] V = new int[n + 1][C + 1];
        int[] x = new int[n];
        //初始化第0列
        for (int i = 0; i <= n; i++)
            V[i][0] = 0;
        //初始化第0行
        for (int j = 0; j <= C; j++)
            V[0][j] = 0;
        //计算第i行，进行第i次迭代
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= C; j++) {
                if (j < w[i-1]) {
                    V[i][j] = V[i - 1][j];
                } else {
                    V[i][j] = Math.max(V[i - 1][j], V[i - 1][j - w[i-1]] + v[i-1]);
                }
            }
        }
        //求装入背包的物品
        for (int j = C, i = n; i > 0; i--) {
            if (V[i][j] > V[i - 1][j]) {
                x[i-1] = 1;
                j = j - w[i-1];
            } else {
                x[i-1] = 0;
            }
        }
        System.out.println(Arrays.toString(x));
        //返回背包取得的最大价值
        return V[n][C];
    }

    /**
     *
     * @param w 物品重量
     * @param v 物品价值
     * @param n 物品个数
     * @param C 背包容量
     * @return  最大价值
     */
    public static int KnapsackGreedy(int[] w, int[] v, int n, int C) {

        //解向量
        int[] x = new int[n];
        //最大价值
        int maxValue = 0;
        int i = 0;
        while (w[i] <= C) {
            x[i] = 1;
            C = C - w[i];
            maxValue += v[i];
            i++;
        }
        return maxValue;
    }

    /**
     *
     * @param w 物品重量
     * @param v 物品价值
     * @param n 物品个数
     * @param C 背包容量
     * @return  最大价值
     */
    public static void KnapsackBT(int[] w, int[] v, int n, int C) {

    }

}
