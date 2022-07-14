import java.util.Map;
import java.util.TreeMap;

class RangeList {
    //Initialize treemap intervals
    TreeMap<Integer, Integer> intervals;

    public RangeList() {
        intervals = new TreeMap<>();
    }
    public void add(int[] range) {
        //Get left boundary and right boundary for array range
        int left = range[0];
        int right = range[1];

        //Get the entry with left boundary that is the smallest but greater than the left boundary of range
        Map.Entry<Integer, Integer> entry = intervals.higherEntry(left);
        //Case 1: if entry is not the smallest entry in intervals
        if (entry != intervals.firstEntry()) {
            //If entry is null, start is the entry with the biggest left boundary but smaller than the left boundary of range
            Map.Entry<Integer, Integer> start = entry != null ? intervals.lowerEntry(entry.getKey()) : intervals.lastEntry();
            
            if (start != null && start.getValue() >= right) {
                return;
            }
            if (start != null && start.getValue() >= left) {
                left = start.getKey();
                intervals.remove(start.getKey());
            }
        }
        while (entry != null && entry.getKey() <= right) {
            right = Math.max(right, entry.getValue());
            intervals.remove(entry.getKey());
            entry = intervals.higherEntry(entry.getKey());
        }
        //There is no entry
        intervals.put(left, right);
    }
    public void remove(int[] range) {
        int left = range[0];
        int right = range[1];

        Map.Entry<Integer, Integer> entry = intervals.higherEntry(left);
        if (entry != intervals.firstEntry()) {
            Map.Entry<Integer, Integer> start = entry != null ? intervals.lowerEntry(entry.getKey()) : intervals.lastEntry();
            if (start != null && start.getValue() >= right) {
                int ri = start.getValue();
                if (start.getKey() == left) {
                    intervals.remove(start.getKey());
                } else {
                    intervals.put(start.getKey(), left);
                }
                if (right != ri) {
                    intervals.put(right, ri);
                }
                return;
            } else if (start != null && start.getValue() > left) {
                if (start.getKey() == left) {
                    intervals.remove(start.getKey());
                } else {
                    intervals.put(start.getKey(), left);
                }
            }
        }
        while (entry != null && entry.getKey() < right) {
            if (entry.getValue() <= right) {
                intervals.remove(entry.getKey());
                entry = intervals.higherEntry(entry.getKey());
            } else {
                intervals.put(right, entry.getValue());
                intervals.remove(entry.getKey());
                break;
            }
        }
}
    public void print(){
        for(Map.Entry<Integer, Integer> entry : intervals.entrySet()){
            System.out.print("["+entry.getKey() +","+ entry.getValue()+") ");
        }
        System.out.println('\n');
    }
    public static void main(String[] args){
        RangeList rl  = new RangeList();
        rl.add(new int[]{1,5});
        rl.print();
        rl.add(new int[]{10,20});
        rl.print();
        rl.add(new int[]{20,20});
        rl.print();
        rl.add(new int[]{20,21});
        rl.print();
        rl.add(new int[]{2,4});
        rl.print();
        rl.add(new int[]{3,8});
        rl.print();
        rl.remove(new int[]{10,10});
        rl.print();
        rl.remove((new int[]{10,11}));
        rl.print();
        rl.remove(new int[]{15,17});
        rl.print();
        rl.remove(new int[]{3,19});
        rl.print();
    }
}