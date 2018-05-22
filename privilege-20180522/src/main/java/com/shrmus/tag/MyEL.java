package com.shrmus.tag;

import java.util.List;

import com.shrmus.pojo.Privilege;

/**
 * 自定义el表达式，判断集合是否包含一个元素
 * <p>Title:MyEL</p>
 * <p>Description:</p>
 * @author Shr
 * @date 2018年3月6日下午12:22:55
 * @version
 */
public class MyEL{

	public static boolean contains(List<Object> objects,Object element) {
		for(Object obj:objects) {
			Privilege temp = (Privilege)obj; 
			Privilege elementPrivilege = (Privilege)element;
			if(temp.getPrivilegeId() == elementPrivilege.getPrivilegeId()) {
				return true;
			}
		}
		return false;
	}
}
