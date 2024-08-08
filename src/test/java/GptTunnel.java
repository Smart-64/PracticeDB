import org.junit.Test;

public class GptTunnel {
    // Метод для выполнения быстрой сортировки
    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            // Получаем индекс опорного элемента
            int pivotIndex = partition(array, low, high);

            // Рекурсивно сортируем элементы до и после опорного элемента
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    // Метод для разделения массива на две части
    private static int partition(int[] array, int low, int high) {
        // Выбираем опорный элемент (pivot)
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            // Если текущий элемент меньше или равен опорному
            if (array[j] <= pivot) {
                i++;
                // Меняем местами элементы array[i] и array[j]
                swap(array, i, j);
            }
        }

        // Меняем местами элементы array[i + 1] и array[high] (или pivot)
        swap(array, i + 1, high);

        return i + 1;
    }

    // Метод для обмена элементов массива
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @Test
    // Основной метод для тестирования алгоритма
    public void test_quickSort() {
        int[] array = {10, 7, 8, 9, 1, 5};
        int n = array.length;

        System.out.println("Исходный массив:");
        printArray(array);

        quickSort(array, 0, n - 1);

        System.out.println("Отсортированный массив:");
        printArray(array);
    }

    // Метод для вывода массива на экран
    private static void printArray(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    @Test
    public void test1() {;
        AsyncProcessor processor = new AsyncProcessor();
        System.out.println("test");
        processor.process(result -> {
            System.out.println("result: " + result);
        });
    }

    interface CallBack {
        void onComplete(String string);
    }

    class AsyncProcessor {
        public void process(CallBack callBack) {
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                    System.out.println("woke up");
                    callBack.onComplete("Complete!");
                } catch (InterruptedException exception) {
                    System.out.println(exception.getMessage());
                    callBack.onComplete("Error and not complete!");
                }

            }).start();
        }
    }
}
