public class Sorting1 {
    public static int[] sorting(int[] nums){
        if(nums.length == 0)
            return new int[]{-1, -1};

        //从左向右扫描，得到数组的最右逆序对
        int max = nums[0];
        //最大逆序对的右区间
        int r = -1;
        for(int cur = 1; cur < nums.length; cur++){
            if(nums[cur] >= max){
                max = nums[cur];
            }
            else{
                //产生逆序对右区间
                 r = cur;
            }
        }
        //从右向左扫描，得到数组的最左逆序对
        int min = nums[nums.length - 1];
        //最大逆序对的左区间
        int l = -1;
        for(int cur = nums.length - 2; cur >= 0; cur--){
            if(nums[cur] <= min){
                min = nums[cur];
            }
            else
                //产生逆序对左区间
                l = cur;
        }

        return new int[]{l,r};
    }

    public static void main(String[] args){
        int[] test = {1,1,2,1,4,7,8,9};
        int[] res = sorting(test);
        System.out.println(res[0]);
        System.out.println(res[1]);
    }
}
