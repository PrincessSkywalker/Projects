import java.util.Arrays;
import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;

public class MergeSort {

        /**
         * Internal method that merges two sorted halves of a subarray (from Weiss Data Structures and Algorithm Analysis in Java)
         * @param a an array of Comparable items.
         * @param tmpArray an array to place the merged result.
         * @param leftPos the left-most index of the subarray.
         * @param rightPos the index of the start of the second half.
         * @param rightEnd the right-most index of the subarray.
         */
        private static void merge(Integer[] a, Integer[] tmpArray, int leftPos, int rightPos, int rightEnd) {
            int leftEnd = rightPos - 1;
            int tmpPos = leftPos;
            int numElements = rightEnd - leftPos + 1;

            // Main loop
            while(leftPos <= leftEnd && rightPos <= rightEnd) {
                if( a[leftPos] <= a[rightPos ]) { 
                    tmpArray[tmpPos++] = a[leftPos++];
                } else {
                    tmpArray[tmpPos++] = a[rightPos++];
                }   
            }  

            while( leftPos <= leftEnd ) {   // Copy rest of first half
                tmpArray[tmpPos++] = a[leftPos++];
            }

            while( rightPos <= rightEnd ) { // Copy rest of right half
                tmpArray[tmpPos++] = a[rightPos++];
            }

            // Copy tmpArray back
            for( int i = 0; i < numElements; i++, rightEnd-- ) {
                a[rightEnd] = tmpArray[rightEnd];
            }
        }

        /**
         * Merge Sort algorithm.
         * This is the Merge Sort algorithm from from Weiss, Data Structures and Algorithm Analysis in Java, 
         * as presented in class. 
         * @param a an array of Comparable items.
         */
        public static void mergeSort(Integer[] a ) {
            Integer[] tmpArray = new Integer[a.length];
            mergeSort(a, tmpArray, 0, a.length - 1 );
        }
        /**
         * Internal method that makes recursive calls. 
         * This is part of the MergeSort algorithm from from Weiss, Data Structures and Algorithm Analysis in Java, 
         * as presented in class. 
         * @param a an array of Comparable items.
         * @param tmpArray an array to place the merged result.
         * @param left the left-most index of the subarray.
         * @param right the right-most index of the subarray.
         */
        private static void mergeSort(Integer[] a, Integer[] tmpArray, int left, int right) {
            if(left < right) {
                int center = (left + right) / 2;
                mergeSort(a, tmpArray, left, center);
                mergeSort( a, tmpArray, center + 1, right);
                merge(a, tmpArray, left, center + 1, right);
            }
        }


       /** 
         * Problem 5: Iterative Bottom-up Merge Sort
         */
        public static void mergeSortB(Integer[] inputArray) {
            Integer[] tempArray = new Integer[inputArray.length];

            for(int i = 1; i < inputArray.length; i *= 2) { // log n iterations
                for(int left = 0; left < inputArray.length; left += 2 * i) { // n / 2i iterations

                    int right = Math.min(left + i, inputArray.length);
                    int rightEnd = Math.min(left + 2 * i, inputArray.length) - 1;

                    merge(inputArray, tempArray, left, right, rightEnd); // O(2i)
                }
            }
        }


        /** 
         * Problem 6: Merge Sort for Lists, Without Side Effects
         */

        // We use O(n log n) space, since each merge size creates lists whose total space is n
        // There are log n sizes that are merged
        public static List<Integer> sortList(List<Integer> inputList) {
            if(inputList.size() <= 1){
                return new LinkedList<Integer>(inputList);
            }

            int center = inputList.size() / 2;
            List<Integer> left = sortList(inputList.subList(0, center));
            List<Integer> right = sortList(inputList.subList(center, inputList.size()));
            return mergeLists(left, right);
        }
        

        /**
         * New merge method that merges two lists and returns a new list.
         * Use this method to implement sortList.
         */
        public static List<Integer> mergeLists(List<Integer> left, List<Integer> right) {
            LinkedList<Integer> outputList = new LinkedList<>();
            Iterator<Integer> itLeft = left.iterator();
            Iterator<Integer> itRight = right.iterator();

            boolean loop = itLeft.hasNext() && itRight.hasNext();
            int l = 0, r = 0;

            if(loop) {
                l = itLeft.next();
                r = itRight.next();
            }

            while(loop){
                if(l <= r){
                    outputList.add(l);
                    loop = itLeft.hasNext();
                    if(loop)
                        l = itLeft.next();
                    else
                        outputList.add(r);
                }
                else {
                    outputList.add(r);
                    loop = itRight.hasNext();
                    if(loop)
                        r = itRight.next();
                    else
                        outputList.add(l);
                }
            }

            while(itLeft.hasNext()){
                outputList.add(itLeft.next());
            }

            while(itRight.hasNext()){
                outputList.add(itRight.next());
            }
            return outputList;
        }
        
 
        public static void main(String[] args) {
            // Weiss sort
            Integer[] a = {1,4,9,131,0,2,7,19,245,18};
            List<Integer> a2 = Arrays.asList(a);

            MergeSort.mergeSortB(a);
            System.out.println(Arrays.toString(a)); // Should be [0, 1, 2, 4, 7, 9, 18, 19, 131, 245]
            System.out.println(sortList(a2));
        }

}
