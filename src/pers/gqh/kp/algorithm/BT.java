package pers.gqh.kp.algorithm;

import java.util.Arrays;

/**
 * @Package pers.gqh.kp.algorithm
 * @Description
 * @Author Simple OR XXX
 * @Date 2022/3/19
 **/
public class BT {
    public static int n, C, maxValue;
    public static int[] wight;
    public static int[] value;
    public static int[] ans;
    //临时记录当前被放入背包的物品
    public static int[] temp;
    /**
     * 回溯法
     *
     * @param t
     * @param sumWight
     * @param sumValue
     */
    public static void backtrack(int t, int sumWight, int sumValue) {
        if (t >= n) {
            if (maxValue < sumValue) {
                maxValue = sumValue;
                for (int i = 0; i < n; i++)
                    ans[i] = temp[i];
            }
        } else {
            for (int i = 0; i <= 1; i++) {
                temp[t] = i;
                sumWight = sumWight + i * wight[t];
                sumValue = sumValue + i * value[t];
                if (sumWight <= C) {
                    //前往下一层
                    backtrack(t + 1, sumWight, sumValue);
                    //实际重量大于最大重量时，从背包把物品拿出。
                } else {
                    sumWight = sumWight - i * wight[t];
                    sumValue = sumValue - i * value[t];
                }
            }
        }
    }
}
