package _2_Sorting._2_5_SortingApplications;

/**
 * 记录字符串以及频率
 */
public class Record implements Comparable<Record>{
    private final String word;
    private int frequency;

    public Record(String word){
        this(word, 1);
    }

    public Record(String word, int frequency){
        this.word = word;
        this.frequency = frequency;
    }

    @Override
    public int compareTo(Record that) {
        if (this.frequency < that.frequency) return -1;
        if (this.frequency > that.frequency) return 1;
        return 0;
    }

    public String toString(){
        return frequency + " " + word;
    }
}
