package com.mmall.concurrency.rpn;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author homolo
 * @DESC
 * @Create 2019-07-01  下午5:38
 */
public class RPNCalculator {
    private static Logger LOGGER = LoggerFactory.getLogger(RPNCalculator.class);
	private final int scale;

	private Stack<BigDecimal> nums;  //存入栈中的数

	private Stack<BigDecimal> numsBackUp; //存入的历史记录

	private Stack<String> operatorBackUp; //动作


	public RPNCalculator(int scale) {
		this.scale = scale;

		if (nums == null) {
			nums = new Stack<>();
		}

		if (numsBackUp == null) {
			numsBackUp = new Stack<>();
		}

		if (operatorBackUp == null) {
			operatorBackUp = new Stack<>();
		}
	}

	public String execute(String inputStr) {
	    if (StringUtils.isBlank(inputStr)) {
	        return "不能输入空字符！";
        }
		String[] arrayList = inputStr.split(" ");
	    //构造队列
		Queue<String> strQueue = buildQueue(arrayList);
		return compute(strQueue);
	}

	private Queue<String> buildQueue(String[] arrayList) {
		Queue<String> queue = new LinkedList<>();
        Arrays.stream(arrayList).filter(x -> !x.isEmpty()).forEach(queue::offer);
		return queue;
	}

	private String compute(Queue<String> msgQueue) {
		if (msgQueue.isEmpty() || msgQueue.size() == 0) {
			return null;
		}
		while (msgQueue.size() != 0) {
			String val = msgQueue.poll();

			if (isNumber(val)) {
				nums.push(new BigDecimal(val));
				continue;
			}

			operatorBackUp.push(val);
			switch (val) {
				case "clear":
					clearStack();
					break;
				case "sqrt":
					sqrt();
					break;
				case "undo":
					undo();
					break;
				default:
					if (nums.size() == 1 || nums.size() == 0) {
						return nums.toString() + "\n" + "堆栈参数不足:" + val;
					}
					basicOperate(val);
			}
		}
		return nums.toString().replace(",", " ")
				.replace("[", "").replace("]", "");
	}

	private static boolean isNumber(String str) {
		if (str == null || str.length() == 0) {
			return false;
		}
		//判断是否为数字
        Boolean strResult = str.matches("-?[0-9]+.*[0-9]*");
        return strResult;
    }

	private void undo() {
		if (nums.size() == 0) {
			return;
		}
        try {
            nums.pop();
            operatorBackUp.pop();
            String checkSqrt = operatorBackUp.get(operatorBackUp.size() - 1); //判断undo上一步是不是为开根号
            if (checkSqrt.equals("sqrt")) {
                nums.push(numsBackUp.pop());
            } else {
                nums.push(numsBackUp.pop());
                nums.push(numsBackUp.pop());
            }
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        }
    }


	private void sqrt() {
		BigDecimal num = nums.pop();
        int flag = num.signum(); //非负数判断
        numsBackUp.push(num);
        if (flag != -1) {
        nums.push(new BigDecimal(bigDecimalSqrt(num)));
    }
	}

    /**
     * 清除堆栈信息
     */
	private void clearStack() {
		nums.clear();
		numsBackUp.clear();
		operatorBackUp.clear();
	}

    /**
     * 开根号.
     * @param in 传入的操作数
     * @return 计算结果
     */
	private String bigDecimalSqrt(BigDecimal in) {
		BigDecimal sqrt = new BigDecimal(1);
		sqrt.setScale(scale + 3, RoundingMode.FLOOR);
		BigDecimal store = new BigDecimal(in.toString());
		boolean first = true;
		do {
			if (!first) {
				store = new BigDecimal(sqrt.toString());
			} else first = false;
			store.setScale(scale + 3, RoundingMode.FLOOR);
			sqrt = in.divide(store, scale + 3, RoundingMode.FLOOR).add(store).divide(
					BigDecimal.valueOf(2), scale + 3, RoundingMode.FLOOR);
		} while (!store.equals(sqrt));
		return sqrt.setScale(scale, RoundingMode.FLOOR).stripTrailingZeros().toPlainString();
	}

    /**
     * 基础运算.
     * @param val 操作符.
     */
	private void basicOperate(String val) {
		BigDecimal num2 = nums.pop();
		BigDecimal num1 = nums.pop();
		BigDecimal result = basicOperateResult(val.charAt(0), num1, num2);
		nums.push(result);
		numsBackUp.push(num2);
		numsBackUp.push(num1);
	}

	/**
	 * 加减乘除运算.
	 * @param op 操作符
	 * @param num1 操作数
	 * @param num2 操作数
	 * @return 计算结果
	 */
	private BigDecimal basicOperateResult(char op, BigDecimal num1, BigDecimal num2) {
		switch (op) {
			case '+':
				return num1.add(num2);
			case '-':
				return num1.subtract(num2);
			case '*':
				return num1.multiply(num2);
			case '/':
				return num1.divide(num2);
			default:
				return null;
		}
	}

}
