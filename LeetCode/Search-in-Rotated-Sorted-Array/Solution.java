1class Solution {
2    public int search(int[] nums, int target) {
3        int left =0;
4        int right = nums.length-1;
5        
6        while(left<=right){
7
8            int mid = left+ (right-left)/2;
9
10            if(nums[mid]==target){
11                return mid;
12            }
13            
14            if( nums[mid]>=nums[left]){
15                if(target>=nums[left]&& target<nums[mid]){
16                    right= mid-1;
17                }else{
18                    left=mid+1;
19                }
20            }else{
21                if(target<=nums[right]&& target > nums[mid]){
22                    left =mid+1;
23                }else{
24                    right= mid-1;
25                }
26            }
27        }
28
29        return -1;
30    }
31}