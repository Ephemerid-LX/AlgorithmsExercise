package _1_Fundamentals._1_1_programmingModel;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * exercise 1.1.35
 */
public class Exe_1_1_35 {
    private static final int SIDES = 6;
    private static final double[] standard = standardProbabilityDistribution();

    public static void main(String[] args){
        StdOut.println(minN());
    }

    /**
     * 获得标准分布概率
     * @return 标准分布概率
     */
    public static double[] standardProbabilityDistribution(){
        double[] dist = new double[2 * SIDES + 1];
        for (int i = 1; i <= SIDES; i++) {
            for (int j = 1; j <= SIDES; j++) {
                dist[i+j] += 1.0;
            }
        }

        for (int k = 2; k <= 2*SIDES; k++) {
            dist[k] /= 36.0;
        }
        return dist;
    }

    /**
     * 获得实验分布概率
     * @param N 实验次数
     * @return 实验分布概率
     */
    public static double[] experimentalData(int N){
        double[] dist = new double[2*SIDES+1];
        for (int l = 0; l < N; l++) {
            int i = StdRandom.uniform(1,7);
            int j = StdRandom.uniform(1,7);
            dist[i+j] += 1.0;
        }
        for (int k = 2; k <= 2*SIDES; k++) {
            dist[k] /= N;
        }
        return dist;
    }

    /**
     * 求最小N,保证实验数据与准确数据吻合程度到达小数点后三位
     * 需要注意的是,
     *  实验具是随机性的,所以每次实验的结果可能不一样
     * @return 最小N
     */
    public static int minN(){
        int N = 0;
        int equalsTimes = 2;
        while (equalsTimes != 13) {
            N++;
            equalsTimes = 2;
            double[] experimental = experimentalData(N);

            for (int k = 2; k <= 2 * SIDES; k++) {
                int point = -1;
                //这部分的值是固定的，可以提出来
                String s = String.format("%.5f", standard[k]);
                point = s.indexOf(".");
                String subS = s.substring(0, point + 4);

                String e = String.format("%.5f", experimental[k]);
                point = e.indexOf(".");
                String subE = e.substring(0, point + 4);
                if (subS.equals(subE)) equalsTimes++;

                StdOut.printf("%-10s", subS);
                StdOut.printf("%-10s", subE);
                StdOut.println();
            }
            StdOut.println("===================================");
        }
        return N;
    }
}
