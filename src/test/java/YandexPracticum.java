import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;


/**
 * Тестовый класс для прохождения задач Яндекс практикума.
 */
public class YandexPracticum {

    @Test
    public void equalTwoNumbers() {
        int actually = 12;
        int expect = 12;
        assertEquals(expect, actually);
    }

    @Test
    public void findIndexOfElement() {
        int[] array = new int[]{1, 2, 3, 4, 5};
        int actually = findIndex(array, 5);
        int expected = 4;

        assertEquals(expected, actually);
    }

    private Integer findIndex(int[] array, int number) {
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (number == array[i]) {
                index = i;
                break;
            }
        }
        return index;
    }

    private void chooseNumber() {
        Random random = new Random();
        int target = random.nextInt(1000) + 1;
        System.out.println("Я загадал число. Попробуйте угадать!");

        Scanner s = new Scanner(System.in);
        int userGuess = s.nextInt();
        while (userGuess != target) {
            if (userGuess < target) {
                System.out.println("Ваше число меньше");
                userGuess = s.nextInt();
            }
            if (userGuess > target) {
                System.out.println("Ваше число больше");
                userGuess = s.nextInt();
            }
        }

        System.out.println("Правильный ответ");

    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    @Test
    public void resolveAlgorithmTask() {
        LinkedList<Integer> linkedList = new LinkedList<>() {
        };
        linkedList.add(1);
        linkedList.add(1);
        linkedList.add(2);
        linkedList.forEach(System.out::print);
        ListNode node1 = new ListNode(1, new ListNode());
        ListNode node2 = new ListNode(1, new ListNode());
        ListNode node3 = new ListNode(2, new ListNode());
        ListNode resultNode = deleteDuplicate(node1);
        System.out.println(resultNode);
    }

    private ListNode deleteDuplicate(ListNode head) {
        ListNode currentNode = head;
        ListNode nextNode = head.next;

        while (nextNode.next != null) {
            if (currentNode.val == nextNode.val) {
                currentNode.next = nextNode.next;
                nextNode = currentNode.next;
            } else {
                currentNode = nextNode;
                nextNode = currentNode.next;

            }
        }
        return head;
    }

    @Test
    public void testBinarySearch_giveIndex_whenInputIntArray() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));

        assertEquals(4, binarySearch(list, 5));
    }

    public int binarySearch(ArrayList<Integer> inputSortedList, int searchValue) {
        int low = 0;
        int high = inputSortedList.size() - 1;
        int mid;

        while (low <= high) {
            mid = (low + high) / 2;
            int guessValue = inputSortedList.get(mid);

            if (guessValue == searchValue)
                return mid;

            if (guessValue > searchValue)
                high = mid - 1;

            if (guessValue < searchValue)
                low = mid + 1;
        }

        return -1;
    }

    String film_category;

    private static int[] sortArray(int[] inputArray) {
        int min;
        int pos;
        for (int i = 0; i < inputArray.length; i++) {
            min = inputArray[i];
            pos = i;
            for (int j = i + 1; j < inputArray.length; j++) {
                if (min > inputArray[j]) {
                    min = inputArray[j];
                    pos = j;
                }
            }
            inputArray[pos] = inputArray[i];
            inputArray[i] = min;

        }

        return inputArray;
    }

    private static int findMin(int[] inputArray) {
        int min = inputArray[0];
        for (int i = 1; i < inputArray.length; i++) {
            if (min > inputArray[i])
                min = inputArray[i];
        }
        return min;
    }

    private static void printRecursion(int i) {
        System.out.println(i);
        if (i <= 1) return;
        else {
            i = i - 1;
            printRecursion(i);
        }
    }

    private static int sumRecursion(int[] arr, int n) {
        if (n <= 0) {
            return 0;
        } else {
            return arr[n - 1] + sumRecursion(arr, n-1);
        }
    }

    private static int countElements(int[] arr, int length) {
        if (length <= 0) {
            return 0;
        } else {
            return 1 + countElements(arr, length - 1);
        }
    }

    private static int findMaxRecursion(int[] arr, int length, int max) {
        if (length <= 0) {
            return max;
        } else {
            if (arr[length - 1] > max) {
                max = arr[length - 1];
                return findMaxRecursion(arr, length - 1, max);
            }
            return findMaxRecursion(arr, length - 1, max);
        }
    }

    @Test
    public void test_findMaxRecursion() {
        int[] arr = new int[]{111, 22, 3, 4};
        Assert.assertEquals(111, findMaxRecursion(arr, arr.length, arr[arr.length - 1]) );
    }

    @Test
    public void test_countElementsRecursion() {
        int[] arr = new int[]{1, 2, 3};
        Assert.assertEquals(3, countElements(arr, arr.length));
    }

    @Test
    public void test_sumRecursion() {
        int[] arr = new int[]{1, 2 , 3};
        Assert.assertEquals(6, sumRecursion(arr, arr.length));
    }

    @Test
    public void test_printRecursion() {
        printRecursion(3);
    }

    @Test
    public void test_findMinElement() {
        int[] array = new int[]{1, 2, 3, 4, 5, -2};
        int[] newArray = sortArray(array);

        Arrays.stream(newArray).forEach(System.out::println);
    }


    public static int findMissing(int[] numbers) {
        int different = Math.abs(numbers[1] - numbers[0]);

        return 0;
    }

    @Test
    public void testIncreasing_1() {
        assertEquals(3, findMissing(new int[]{1, 2, 4}));
    }

    @Test
    public void testIncreasing_2() {
        assertEquals(2, findMissing(new int[]{1, 3, 4}));
    }

    @Test
    public void testDecreasing_1() {
        assertEquals(3, findMissing(new int[]{4, 2, 1}));
    }

    @Test
    public void testDecreasing_2() {
        assertEquals(2, findMissing(new int[]{4, 3, 1}));
    }

    @Test
    public void testConstant() {
        assertEquals(1, findMissing(new int[]{1, 1, 1}));
    }

}
