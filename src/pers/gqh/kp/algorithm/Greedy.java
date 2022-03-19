package pers.gqh.kp.algorithm;

/**
 * @Package pers.gqh.kp.algorithm
 * @Description
 * @Author Simple OR XXX
 * @Date 2022/3/19
 **/
public class Greedy {
    /**
     * @param w 物品重量
     * @param v 物品价值
     * @param C 背包容量
     * @param x 解向量
     * @return 最大价值
     */
    public static int KnapsackGreedy(int[] w, int[] v, int C, int[] x) {
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
}
