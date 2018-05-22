package com.shrmus.utils;

import java.io.Serializable;
import java.util.List;

/**
 * LayUI前端框架返回值
 * <p>Title:LayUIPojo</p>
 * <p>Description:</p>
 * @author Shr
 * @date 2018年5月11日下午5:23:17
 * @version
 */
public class LayUIPojo<T> implements Serializable{
	private int code;
	private String msg;
	private int count;
	private List<T> data;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	} 
}
