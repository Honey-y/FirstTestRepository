package com.yangbowen.mytestwork;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void Test(){
        int[] list = {41,5,23,6,59,7,94,23,63,8,51,28,14,30};
        System.out.println(Arrays.toString(list));
        fastSort(list,0,list.length-1);
        System.out.println(Arrays.toString(list));
        System.out.println(binSearch(list,0,list.length - 1,1));
    }

    private void fastSort(int[] list,int start,int end){

        if (start >= end)
            return;

        int i = start;
        int j = end;
        int key = list[start];
        int temp ;
        while (i < j){
            while (list[j] >= key && i < j)
                j--;

            while (list[i] <= key && i < j)
                i++;

            temp = list[j];
            list[j] = list[i];
            list[i] = temp;
        }

        list[start] = list[i];
        list[i] = key;

        fastSort(list,start,i-1);
        fastSort(list,i+1,end);

    }

    private int binSearch(int[] list,int start,int end,int key){
        int errValue = -1;
        if (start > end)
            return errValue;
        int mid = (end - start)/2 + start;
        if (key == list[mid])
            return mid;
        else if (key > list[mid])
            return binSearch(list,mid + 1,end,key);
        else if (key < list[mid])
            return binSearch(list,start,mid - 1,key);
        return -2;
    }

}