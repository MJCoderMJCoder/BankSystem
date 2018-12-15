/**
 * 
 */
package com.trim.bean;

/**
 * @author MJCoder
 *
 */
public class Account {
	private long id;
	private String name;
	private String address;
	private String birthday;
	private String type; // Saver（存入一段时间内不能取）；Junior（年龄小于16岁）；Current
	private String password; // 设置6位pin密码
	private int creditStatus; // 0到100，默认的初始值为70（良好）：越高代表信誉越好
	private float money; // 用户的钱
	private String status; // 状态：Suspend Account：（悬帐？）类似于冻结账户，不能操作，解除（re- instated）后能正常操作

	/**
	 * 
	 */
	public Account() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the creditStatus
	 */
	public int getCreditStatus() {
		return creditStatus;
	}

	/**
	 * @param creditStatus
	 *            the creditStatus to set
	 */
	public void setCreditStatus(int creditStatus) {
		this.creditStatus = creditStatus;
	}

	/**
	 * @return the money
	 */
	public float getMoney() {
		return money;
	}

	/**
	 * @param money
	 *            the money to set
	 */
	public void setMoney(float money) {
		this.money = money;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "账号：" + id + "，姓名：" + name + "，地址：" + address + "，出生日期：" + birthday + "，账户类型：" + type + "，密码：" + password
				+ "，信誉值：" + creditStatus + (creditStatus >= 70 ? " ☺ 良好" : " ☹  差") + "，余额：" + money + "，状态：" + status;
	}

}
