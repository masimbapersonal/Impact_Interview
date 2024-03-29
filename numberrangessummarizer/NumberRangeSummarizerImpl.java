package numberrangesummarizer;

import java.util.*;

public class NumberRangeSummarizerImpl implements NumberRangeSummarizer{
    @Override
    public Collection<Integer> collect(String input) {
        List<String> numbersListString = Arrays.asList(input.split(","));
        List<Integer> numbersListInteger = numbersListString.stream().map(s -> Integer.parseInt(s.trim())).toList();
        return new LinkedList<>(numbersListInteger);
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        Queue <Integer> numbersQueue = (Queue<Integer>) input;
        StringBuilder finalNumbersList = new StringBuilder();
        while (!numbersQueue.isEmpty()){
            Integer firstNumber = numbersQueue.poll();
            Integer secondNumber = numbersQueue.peek();

            boolean isBeginningOfRange = finalNumbersList.isEmpty()||finalNumbersList.toString().endsWith(",");
            boolean isSequential = firstNumber.equals(secondNumber-1);

            if(isSequential && isBeginningOfRange){
                finalNumbersList.append(firstNumber).append("-");
            }else if(isSequential){
                if(numbersQueue.size()!=1){continue;}
            }else if(numbersQueue.size()!=1){
                finalNumbersList.append(firstNumber).append(",");
            }

            if(numbersQueue.size()==1){
                finalNumbersList.append(secondNumber);
                break;
            }
        }
        return finalNumbersList.toString();
    }
}
