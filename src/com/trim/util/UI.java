/**
 * 
 */
package com.trim.util;

/**
 * 模拟UI输出
 * 
 * @author MJCoder
 *
 */
public class UI {

	/**
	 * 构造方法
	 */
	public UI() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 向控制台输入
	 * 
	 * @param str
	 */
	public static void print(String str) {
		String border = "────────────────────────────────────────────────────────────────────────────────────────────────────────────────";
		System.out.println("╭─" + border);
		System.out.println("│  " + str + "  ");
		System.out.println("╰─" + border);
	}
}
