package pers.gqh.kp.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Package pers.gqh.kp.entity
 * @Description
 * @Author Simple OR XXX
 * @Date 2022/3/18
 **/
public class Data {
    private ArrayList<Integer> w;
    private ArrayList<Integer> v;
    private int n;
    private int C;

    public Data() {
        this.w = new ArrayList();
        this.v = new ArrayList();

    }

    public Data(ArrayList w, ArrayList v, int n, int c) {
        this.w = w;
        this.v = v;
        this.n = n;
        C = c;
    }

    public ArrayList getW() {
        return w;
    }

    public void setW(ArrayList w) {
        this.w = w;
    }

    public ArrayList getV() {
        return v;
    }

    public void setV(ArrayList v) {
        this.v = v;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getC() {
        return C;
    }

    public void setC(int c) {
        C = c;
    }

    @Override
    public String toString() {
        return "Data{" +
                "w=" + w +
                ", v=" + v +
                ", n=" + n +
                ", C=" + C +
                '}';
    }
}
