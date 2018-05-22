package com.shrmus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.shrmus.service.PrivilegeService;

/**
 * 
 * <p>Title:PrivilegeController</p>
 * <p>Description:</p>
 * @author Shr
 * @date 2018年5月22日下午11:38:28
 * @version
 */
@Controller
@Scope("ptototype")
public class PrivilegeController {
	@Autowired
	private PrivilegeService privilegeService;
	
	
}
