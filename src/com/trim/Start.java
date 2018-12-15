/**
 * 
 */
package com.trim;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.trim.bean.Account;
import com.trim.util.UI;

/**
 * @author MJCoder
 *
 */
public class Start {
	private static List<Account> accounts = new ArrayList<Account>(); // 存储银行所有的账户信息
	private final static String ABNORMAL_CHARACTER = "非正常字符，无法识别；请重新输入。";
	private static Account account = null; // 当前用户使用的当前账户：始终仅保存一个账户；
	private static boolean isLiquidation = false; // 是否执行清算；true：进行清算
	private static List<Thread> threads = new ArrayList<Thread>(); // 线程池

	/**
	 * 
	 */
	public Start() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 从键盘获取获取用户输入
	 * 
	 * @return
	 */
	private static String getInput() {
		Scanner scan = new Scanner(System.in);
		// 从键盘接收数据
		// next方式接收字符串
		// 判断是否还有输入
		if (scan.hasNext()) {
			return scan.next();
		}
		scan.close();
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UI.print("开户，请输入：\"KH\"；已有账户登录，请输入：\"DL\"；资金清算，请输入：\"QS\"");
		init();
	}

	/**
	 * 初始化进入系统操作：开户还是登录
	 */
	private static void init() {
		String str = getInput();
		if ("KH".equalsIgnoreCase(str)) {
			UI.print(
					"请选择开户类型\n│\tSaver account（存入一段时间内不能取），请输入：\"SAVER\"\n│\tJunior account（年龄小于16岁），请输入：\"JUNIOR\"\n│\tCurrent account，请输入：\"CURRENT\"\n│\t返回上级菜单，请输入：\"FH\"");
			openAccount();
		} else if ("DL".equalsIgnoreCase(str)) {
			UI.print("返回上级菜单，请输入：\"FH\"；请输入账号：");
			inputId();
		} else if ("QS".equalsIgnoreCase(str)) {
			liquidation();
		} else {
			if (str != null && str.length() > 0) {
				UI.print(ABNORMAL_CHARACTER);
				init();
			}
		}
	}

	/**
	 * 清算
	 */
	private static void liquidation() {
		UI.print("正在清算....");
		do {
			isLiquidation = true;
		} while (!isAllThreadEnd());
		isLiquidation = false;
		UI.print("清算结束\n│\t开户，请输入：\"KH\"；已有账户登录，请输入：\"DL\"；资金清算，请输入：\"QS\"");
		init();
	}

	/*
	 * 判断所有线程是否都执行完毕了
	 */
	private static boolean isAllThreadEnd() {
		for (Thread thread : threads) {
			if (thread.isAlive()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 开户流程
	 */
	private static void openAccount() {
		String str = getInput();
		if ("SAVER".equalsIgnoreCase(str)) {
			account = new Account();
			account.setId(System.currentTimeMillis());
			account.setType("SAVER");
			account.setCreditStatus(70);
			account.setStatus("正常");
			UI.print("请输入出生日期：");
			inputBirthday(false);
		} else if ("JUNIOR".equalsIgnoreCase(str)) {
			account = new Account();
			account.setId(System.currentTimeMillis());
			account.setType("JUNIOR");
			account.setCreditStatus(70);
			account.setStatus("正常");
			UI.print("请输入出生日期：");
			inputBirthday(true);
		} else if ("CURRENT".equalsIgnoreCase(str)) {
			account = new Account();
			account.setId(System.currentTimeMillis());
			account.setType("CURRENT");
			account.setCreditStatus(70);
			account.setStatus("正常");
			UI.print("请输入出生日期：");
			inputBirthday(false);
		} else if ("FH".equalsIgnoreCase(str)) {
			UI.print("开户，请输入：\"KH\"；已有账户登录，请输入：\"DL\"；资金清算，请输入：\"QS\"");
			init();
		} else {
			if (str != null && str.length() > 0) {
				UI.print(ABNORMAL_CHARACTER);
				openAccount();
			}
		}
	}

	/**
	 * 请输入姓名
	 */
	private static void inputName() {
		String str = getInput();
		if (str != null && str.length() > 0) {
			account.setName(str);
			UI.print("请输入地址：");
			inputAddress();
		}
	}

	/**
	 * 请输入地址
	 */
	private static void inputAddress() {
		String str = getInput();
		if (str != null && str.length() > 0) {
			account.setAddress(str);
			UI.print("请输入密码：");
			inputPassword(false);
		}
	}

	/**
	 * 请输入出生日期
	 */
	private static void inputBirthday(boolean isJunior) {
		String str = getInput();
		if (str != null && str.length() > 0) {
			if (isJunior) {
				int year = Integer.parseInt(str.substring(0, 4));
				if (2018 - year < 16) {
					account.setBirthday(str);
					UI.print("请输入姓名：");
					inputName();
				} else {
					UI.print(
							"年龄已大于16岁；不可开此类型的账户\n│\t请重新选择开户类型\n│\tSaver account（存入一段时间内不能取），请输入：\"SAVER\"\n│\tJunior account（年龄小于16岁），请输入：\"JUNIOR\"\n│\tCurrent account，请输入：\"CURRENT\"\n│\t返回上级菜单，请输入：\"FH\"");
					openAccount();
				}
			} else {
				account.setBirthday(str);
				UI.print("请输入姓名：");
				inputName();
			}
		}
	}

	/**
	 * 请输入密码
	 * 
	 * @param isLogin
	 */
	private static void inputPassword(boolean isLogin) {
		String str = getInput();
		if (str != null && str.length() > 0) {
			if (isLogin) {
				if (str.equals(account.getPassword())) {
					UI.print("登录成功；以下是您的账户信息" + "\n│\t" + account.toString());
					if ("正常".equals(account.getStatus())) {
						UI.print(
								"存款请输入：\"CK\"；取款请输入：\"QK\"；冻结请输入：\"DJ\"；解除冻结请输入：\"JC\"；退出请输入：\"TC\"；销户请输入：\"XH\"；返回上级菜单，请输入：\"FH\"");
					} else {
						UI.print("冻结请输入：\"DJ\"；解除冻结请输入：\"JC\"；退出请输入：\"TC\"；销户请输入：\"XH\"；返回上级菜单，请输入：\"FH\"");
					}
					operate();
				} else {
					UI.print("密码错误，请重新输入：");
					inputPassword(isLogin);
				}
			} else {
				account.setPassword(str);
				accounts.add(account);
				UI.print("开户成功；以下是您的账户信息" + "\n│\t" + account.toString());
				if ("正常".equals(account.getStatus())) {
					UI.print(
							"存款请输入：\"CK\"；取款请输入：\"QK\"；冻结请输入：\"DJ\"；解除冻结请输入：\"JC\"；退出请输入：\"TC\"；销户请输入：\"XH\"；返回上级菜单，请输入：\"FH\"");
				} else {
					UI.print("冻结请输入：\"DJ\"；解除冻结请输入：\"JC\"；退出请输入：\"TC\"；销户请输入：\"XH\"；返回上级菜单，请输入：\"FH\"");
				}
				operate();
			}
		}
	}

	/**
	 * 退出（ Close Account：关闭删除账户）
	 */
	private static void exitAccount() {
		UI.print(account.getName() + "正在退出...");
		account = null;
		UI.print("已经安全退出。\n│开户，请输入：\"KH\"；已有账户登录，请输入：\"DL\"；资金清算，请输入：\"QS\"");
		init();
	}

	/**
	 * 销户（ Close Account：关闭删除账户）
	 */
	private static void deleteAccount() {
		UI.print(account.getName() + "正在销户...");
		accounts.remove(account);
		account = null;
		UI.print("已销户。\n│开户，请输入：\"KH\"；已有账户登录，请输入：\"DL\"；资金清算，请输入：\"QS\"");
		init();
	}

	/**
	 * 请输入账号
	 */
	private static void inputId() {
		String str = getInput();
		if (str != null && str.length() > 0) {
			if ("FH".equalsIgnoreCase(str)) {
				UI.print("开户，请输入：\"KH\"；已有账户登录，请输入：\"DL\"；资金清算，请输入：\"QS\"");
				init();
			} else {
				for (Account temp : accounts) {
					if (str.equals(temp.getId() + "")) {
						account = temp;
						break;
					}
				}
				if (account == null) {
					UI.print("抱歉，系统未找到该账号；返回上级菜单，请输入：\"FH\"；也可重新输入正确的账号：");
					inputId();
				} else {
					UI.print("请输入密码：");
					inputPassword(true);
				}
			}
		}
	}

	/**
	 * 某个具体的账户进入系统后的操作
	 * 
	 * 操作：存款、取款、（解除）冻结账户、退出、销户
	 */
	private static void operate() {
		String str = getInput();
		if ("CK".equalsIgnoreCase(str)) {
			if ("正常".equals(account.getStatus())) {
				UI.print("快速到账请输入：\"KS\"；24小时候到账请输入：\"AQ\"；返回上级菜单，请输入：\"FH\"");
				deposit();
			} else {
				UI.print("冻结请输入：\"DJ\"；解除冻结请输入：\"JC\"；退出请输入：\"TC\"；销户请输入：\"XH\"；返回上级菜单，请输入：\"FH\"");
				operate();
			}
		} else if ("QK".equalsIgnoreCase(str)) {
			if ("正常".equals(account.getStatus())) {
				UI.print("返回上级菜单，请输入：\"FH\"；请输入取款金额：");
				withdrawal();
			} else {
				UI.print("冻结请输入：\"DJ\"；解除冻结请输入：\"JC\"；退出请输入：\"TC\"；销户请输入：\"XH\"；返回上级菜单，请输入：\"FH\"");
				operate();
			}
		} else if ("DJ".equalsIgnoreCase(str)) {
			account.setStatus("已冻结；不能操作");
			UI.print(account.getStatus()
					+ "\n│\t冻结请输入：\"DJ\"；解除冻结请输入：\"JC\"；退出请输入：\"TC\"；销户请输入：\"XH\"；返回上级菜单，请输入：\"FH\"");
			operate();
		} else if ("JC".equalsIgnoreCase(str)) {
			account.setStatus("正常");
			UI.print(account.getStatus()
					+ "\n│存款请输入：\"CK\"；取款请输入：\"QK\"；冻结请输入：\"DJ\"；解除冻结请输入：\"JC\"；退出请输入：\"TC\"；销户请输入：\"XH\"；返回上级菜单，请输入：\"FH\"");
			operate();
		} else if ("TC".equalsIgnoreCase(str)) {
			exitAccount();
		} else if ("XH".equalsIgnoreCase(str)) {
			deleteAccount();
		} else if ("FH".equalsIgnoreCase(str)) {
			UI.print("开户，请输入：\"KH\"；已有账户登录，请输入：\"DL\"；资金清算，请输入：\"QS\"");
			init();
		} else {
			if (str != null && str.length() > 0) {
				UI.print(ABNORMAL_CHARACTER);
				operate();
			}
		}
	}

	/**
	 * 取款
	 */
	private static void withdrawal() {
		String str = getInput();
		if ("FH".equalsIgnoreCase(str)) {
			if ("正常".equals(account.getStatus())) {
				UI.print(
						"存款请输入：\"CK\"；取款请输入：\"QK\"；冻结请输入：\"DJ\"；解除冻结请输入：\"JC\"；退出请输入：\"TC\"；销户请输入：\"XH\"；返回上级菜单，请输入：\"FH\"");
			} else {
				UI.print("冻结请输入：\"DJ\"；解除冻结请输入：\"JC\"；退出请输入：\"TC\"；销户请输入：\"XH\"；返回上级菜单，请输入：\"FH\"");
			}
			operate();
		} else {
			try {
				float value = Float.parseFloat(str);
				if ("SAVER".equalsIgnoreCase(account.getType()) || "JUNIOR".equalsIgnoreCase(account.getType())) { // 取款不能超过存款
					if (account.getMoney() - value >= 0) {
						account.setMoney(account.getMoney() - value);
						UI.print("取出：" + value + "元；" + "所剩余额：" + account.getMoney() + "元。");
						if ("正常".equals(account.getStatus())) {
							UI.print(
									"存款请输入：\"CK\"；取款请输入：\"QK\"；冻结请输入：\"DJ\"；解除冻结请输入：\"JC\"；退出请输入：\"TC\"；销户请输入：\"XH\"；返回上级菜单，请输入：\"FH\"");
						} else {
							UI.print("冻结请输入：\"DJ\"；解除冻结请输入：\"JC\"；退出请输入：\"TC\"；销户请输入：\"XH\"；返回上级菜单，请输入：\"FH\"");
						}
						operate();
					} else {
						UI.print("返回上级菜单，请输入：\"FH\"；您当前没有这么多钱，请重新输入：");
						withdrawal();
					}
				} else if ("CURRENT".equalsIgnoreCase(account.getType())) { // 取款能有一个overdraft limit
					if ((account.getMoney() + 500) - value >= 0) {
						account.setMoney(account.getMoney() - value);
						UI.print("取出：" + value + "元；" + "所剩余额：" + account.getMoney() + "元。");
						if ("正常".equals(account.getStatus())) {
							UI.print(
									"存款请输入：\"CK\"；取款请输入：\"QK\"；冻结请输入：\"DJ\"；解除冻结请输入：\"JC\"；退出请输入：\"TC\"；销户请输入：\"XH\"；返回上级菜单，请输入：\"FH\"");
						} else {
							UI.print("冻结请输入：\"DJ\"；解除冻结请输入：\"JC\"；退出请输入：\"TC\"；销户请输入：\"XH\"；返回上级菜单，请输入：\"FH\"");
						}
						operate();
					} else {
						UI.print("返回上级菜单，请输入：\"FH\"；您当前透支金额为500元，请重新输入取款金额：");
						withdrawal();
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				if (str != null && str.length() > 0) {
					UI.print(ABNORMAL_CHARACTER);
					withdrawal();
				}
			}
		}
	}

	/**
	 * 存款
	 */
	private static void deposit() {
		String str = getInput();
		if ("KS".equalsIgnoreCase(str)) {
			UI.print("返回上级菜单，请输入：\"FH\"；请输入存款金额：");
			clearDeposit(true);
		} else if ("AQ".equalsIgnoreCase(str)) {
			UI.print("返回上级菜单，请输入：\"FH\"；请输入存款金额：");
			clearDeposit(false);
		} else if ("FH".equalsIgnoreCase(str)) {
			UI.print(
					"存款请输入：\"CK\"；取款请输入：\"QK\"；冻结请输入：\"DJ\"；解除冻结请输入：\"JC\"；退出请输入：\"TC\"；销户请输入：\"XH\"；返回上级菜单，请输入：\"FH\"");
			operate();
		} else {
			if (str != null && str.length() > 0) {
				UI.print(ABNORMAL_CHARACTER);
				deposit();
			}
		}
	}

	/**
	 * 存款清算
	 * 
	 * @param isKS：true(代表快速到账)；false(24小时后到账)
	 */
	private static void clearDeposit(boolean isKS) {
		String str = getInput();
		if ("FH".equalsIgnoreCase(str)) {
			UI.print("快速到账请输入：\"KS\"；24小时候到账请输入：\"AQ\"；返回上级菜单，请输入：\"FH\"");
			deposit();
		} else {
			try {
				float value = Float.parseFloat(str);
				if (isKS) {
					account.setMoney(account.getMoney() + value);
					UI.print("存入：" + value + "元；" + "当前余额：" + account.getMoney() + "元。");
					if ("正常".equals(account.getStatus())) {
						UI.print(
								"存款请输入：\"CK\"；取款请输入：\"QK\"；冻结请输入：\"DJ\"；解除冻结请输入：\"JC\"；退出请输入：\"TC\"；销户请输入：\"XH\"；返回上级菜单，请输入：\"FH\"");
					} else {
						UI.print("冻结请输入：\"DJ\"；解除冻结请输入：\"JC\"；退出请输入：\"TC\"；销户请输入：\"XH\"；返回上级菜单，请输入：\"FH\"");
					}
					operate();
				} else {
					Thread thread = new Thread() {
						public void run() {
							Account accountCache = account;
							long temp = System.currentTimeMillis();// 用户选择24小时到账后的那个时间点
							while (System.currentTimeMillis() - temp < 24000 && !isLiquidation) { // 这里以24秒代替24小时
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							accountCache.setMoney(accountCache.getMoney() + value);
						};
					};
					threads.add(thread);
					thread.start();
					UI.print("24小时后将会自动到账");
					if ("正常".equals(account.getStatus())) {
						UI.print(
								"存款请输入：\"CK\"；取款请输入：\"QK\"；冻结请输入：\"DJ\"；解除冻结请输入：\"JC\"；退出请输入：\"TC\"；销户请输入：\"XH\"；返回上级菜单，请输入：\"FH\"");
					} else {
						UI.print("冻结请输入：\"DJ\"；解除冻结请输入：\"JC\"；退出请输入：\"TC\"；销户请输入：\"XH\"；返回上级菜单，请输入：\"FH\"");
					}
					operate();
				}
			} catch (Exception e) {
				// TODO: handle exception
				if (str != null && str.length() > 0) {
					UI.print(ABNORMAL_CHARACTER);
					clearDeposit(isKS);
				}
			}
		}
	}

}
