package pers.gqh.kp.algorithm;

/**
 * @Package pers.gqh.kp.algorithm
 * @Description
 * @Author Simple OR XXX
 * @Date 2022/3/19
 **/
public class DP {
    /**
     * @param w 物品重量
     * @param v 物品价值
     * @param n 物品个数
     * @param C 背包容量
     * @param x 解向量
     * @return 最大价值
     */
    public static int KnapsackDP(int[] w, int[] v, int n, int C, int[] x) {
        int[][] V = new int[n + 1][C + 1];
        //初始化第0列
        for (int i = 0; i <= n; i++)
            V[i][0] = 0;
        //初始化第0行
        for (int j = 0; j <= C; j++)
            V[0][j] = 0;
        //计算第i行，进行第i次迭代
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= C; j++) {
                if (j < w[i - 1]) {
                    V[i][j] = V[i - 1][j];
                } else {
                    V[i][j] = Math.max(V[i - 1][j], V[i - 1][j - w[i - 1]] + v[i - 1]);
                }
            }
        }
        //求装入背包的物品
        for (int j = C, i = n; i > 0; i--) {
            if (V[i][j] > V[i - 1][j]) {
                x[i - 1] = 1;
                j = j - w[i - 1];
            } else {
                x[i - 1] = 0;
            }
        }
        //返回背包取得的最大价值
        return V[n][C];
    }
}
