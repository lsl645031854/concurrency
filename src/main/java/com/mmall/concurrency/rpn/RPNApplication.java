package com.mmall.concurrency.rpn;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

/**
 * @Author homolo
 * @DESC
 * @Create 2019-07-01  下午5:39
 */
public class RPNApplication {
	public static void main(String[] args) {
		RPNCalculator calculator = new RPNCalculator(15);
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入运算式：");
		while (true) {
			String intputMsg = sc.nextLine();
			if (StringUtils.equals(intputMsg, "exit")) {
				break;
			}
			System.out.println("stack:" + calculator.execute(intputMsg));
		}
		System.out.println("运算结束");
	}
}
