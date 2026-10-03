1class Solution {
2    public int findPeakElement(int[] nums) {
3        int left =0;
4        int right = nums.length-1;
5        while(left<right){
6            int mid = left + (right- left)/2;
7
8            if (nums[mid]> nums[mid+1]){
9                right=mid;
10
11            }else{
12                left=mid+1;
13            }
14           
15
16        }
17        return left;
18    }
19
20}