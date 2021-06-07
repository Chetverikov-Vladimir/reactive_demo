package multithreading.fork_join_framework;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinDemo {

    @Test
    public void forkJoin() {

        List<Integer> forComputeList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            forComputeList.add(2);
        }

        ForkJoinSimpleTask task = new ForkJoinSimpleTask(forComputeList);

        Integer result = new ForkJoinPool(2).invoke(task);
        System.out.println("Result " + result);

    }

    static class ForkJoinSimpleTask extends RecursiveTask<Integer> {

        List<Integer> inputList;

        public ForkJoinSimpleTask(List<Integer> list) {
            this.inputList = list;
        }

        @Override
        protected Integer compute() {
            if (inputList.size() <= 2) {

                System.out.println("Compute list " + inputList + " in thread " + Thread.currentThread().getName());
                return inputList.stream().reduce(Integer::sum).orElse(0);

            } else {

                //Делим задачу на 2 подзадачи
                List<Integer> sublistLeft = inputList.subList(0, inputList.size() / 2);
                List<Integer> sublistRight = inputList.subList(inputList.size() / 2, inputList.size());

                //Форкаем подзадачи
                ForkJoinTask<Integer> left = new ForkJoinSimpleTask(sublistLeft).fork();
                ForkJoinTask<Integer> right = new ForkJoinSimpleTask(sublistRight).fork();

                //Собираем результат
                return left.join() + right.join();
            }
        }
    }
}
