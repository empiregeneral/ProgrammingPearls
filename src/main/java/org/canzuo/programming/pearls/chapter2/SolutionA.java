package org.canzuo.programming.pearls.chapter2;

import java.io.BufferedInputStream;
import java.util.*;

/**
 * @ClassName SolutionA 无处不在的二分法
 * @Description Programming Pearls Ubiquitous Binary Search
 *              用二分法的思维解决，原问题是从40亿个数中找出不存在的数
 *              要求内存使用在250MB以下，时间复杂度为2s以内，显然不能由位图数据结果解决
 *              若干个外部文件倒是没有限制，会有大量IO读取文件的操作
 *
 * @Author canzuo
 * @Date 2023/3/31 20:36
 * @Version 1.0
 **/
public class SolutionA {
    public static void main(String[] args) {
        // Sample样例，位数为4，找出缺少的正整数10
         int[] samples = new int[]{1, 2, 4, 7, 8, 6, 6, 6, 9, 11, 13, 12, 15, 3, 10, 14};

        // 读取40亿个32位整数，找出其中之一缺少的数字
        Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        List<Integer> inputList = new LinkedList<>();

        while(scanner.hasNext()) {
            inputList.add(scanner.nextInt());
        }

        scanner.close();

        int[] origin = inputList.stream().mapToInt(Integer::intValue).toArray();

        System.out.println(getLostNum(origin, 16));

        System.out.println(getDupNum(samples, 4));
    }


    /**
     * 二分搜索并非是升序序列才能使用，可以根据数据的特性进行离散化的划分，对于无序的数组，求出不存在的数算法如下:
     *
     * Programming Peals第2章特别说明 这里要摒弃固定思维，二分法并不是什么情况下都需要排序后才能使用
     * 算法的具体步骤：
     * 测试数组 {1,3,4,5,3,6,3,7,9,4,9,1,2,2,11,12,15,11,14,15} 9二进制数为(1001)2，最高位1， 7二进制数为(0111)2, 最高位为0
     * 1. 任意一个数，最高位是否为1，遍历原始数组, 最高位为1的数放在一个数组中，将最高位为0的数放在一个数组中
     *    第一次二分得出两个数组 A: [9, 11, 12, 14, 15] 注：使用hashSet删除重复元素
     *                       B: [1, 2, 3, 4, 5, 6, 7]
     *                       这次的分割为 result | 1 << 3
     * 2. A数组比B数组数量少，摒弃B数组，将A数组再次二分，这次二分是次高为是否为1，(根据雀巢定理，较少的数组总是缺数的数组)
     *    9的二进制数(1001)2, 次高位为0，14的二进制数为(1110)2，14的次高位为1
     *    第二次二分得出的两个数组  A: [12, 14, 15]  1的分割
     *                         B: [9, 11] 0的分割
     *                         这次分割的结果不变
     * 3. B数组比A数组少，摒弃A数组，将B数组再次二分，这次二分的标准是第1位是否为1 9-> (1001)2 11 -> (1011)2
     *    二分的结果为： A: [11] 1的分割结果
     *                B: [9]  0的分割结果
     *                这次分割的结果为 result | 1 << 1
     * 4. 数组个数相同，取A数组，这次二分的标准是最后一位是否为1
     *    这次二分的结果是：A [11] -- 0分割的结果
     *                  B [] -- 1分割的结果
     *                  这次分割的结果为 这次分割的结果不变
     * 计算出来result = 10 (1010)2
     *
     * @param origin 需要搜索的数组
     * @param bitSlot 整数的最高位数
     * @return origin数组中缺失的元素
     */
    protected static int getLostNum(int[] origin, int bitSlot) {
        int lostNum = 0;
        int[] arr  = origin;

        while( --bitSlot >= 0 ) {
            int partOneIndex = 0;
            int partTwoIndex = 0;
            Set<Integer> partOne = new HashSet<>();
            Set<Integer> partTwo = new HashSet<>();


            for (int i = 0; i < arr.length; i++) {
                if (getBit(arr[i], bitSlot) == 1) {
                    partOne.add(arr[i]);
                    partOneIndex++;
                } else {
                    partTwo.add(arr[i]);
                    partTwoIndex++;
                }
            }

            if (partOneIndex <= partTwoIndex) {
                lostNum = lostNum | (1 << bitSlot);
                arr = partOne.stream().mapToInt(Integer::intValue).toArray();
            } else {
                arr = partTwo.stream().mapToInt(Integer::intValue).toArray();
            }

        }

        return lostNum;
    }

    /**
     * @Description: 给定一个4300 000 000个32位顺序排列的正整数，如何找出一个至少出现两次的整数
     *               可以将问题缩小为65600个16位乱序排列的正整数，如何找出一个至少出现两次的整数
     *               和找出不存在的数算法类似，通过二分法找出多的那个数，不管样本的数据是排序还是未排序，根据1和0进行二分
     *               只不过不能用Set来去重，还有判断条件是partOneIndex > partTwoIndex才计算保留数字
     * @author canzuo
     *
     * @param origin int
     * @param bitSlot int
     *
     * @return int
     */
    protected static int getDupNum(int[] origin, int bitSlot) {
        int dupNum = 0;
        int[] arr = origin;

        while (--bitSlot >= 0) {
            int partOneIndex = 0;
            int partTwoIndex = 0;
            List<Integer> partOne = new LinkedList<>();
            List<Integer> partTwo = new LinkedList<>();

            for (int i = 0; i < arr.length; i++) {
                if (getBit(arr[i], bitSlot) == 1) {
                    partOne.add(arr[i]);
                    partOneIndex++;
                } else {
                    partTwo.add(arr[i]);
                    partTwoIndex++;
                }
            }

            if (partOneIndex > partTwoIndex) {
                dupNum = dupNum | (1 << bitSlot);
                arr = partOne.stream().mapToInt(Integer::intValue).toArray();
            } else {
                arr = partTwo.stream().mapToInt(Integer::intValue).toArray();
            }

        }

        return dupNum;
    }



    /**
     * @Description: 获取当前位的值，1 or 0
     * @author canzuo
     *
     * @param num int
     * @param bitSlot in
     * @return int
     */
    private static int getBit(int num, int bitSlot) {
        return (num >> bitSlot) & 1;
    }
}
