package _2_Sorting._2_5_SortingApplications;

/**
 * 任务及时间
 */
public class Job implements Comparable<Job>{

    private final String name;
    private double time;

    public Job(String name, double time){
        this.name = name;
        this.time = time;
    }

    @Override
    public int compareTo(Job that) {
        if (this.time < that.time) return -1;
        if (this.time > that.time) return 1;
        return 0;
    }

    public String toString(){
        return name + " " + time;
    }
}
