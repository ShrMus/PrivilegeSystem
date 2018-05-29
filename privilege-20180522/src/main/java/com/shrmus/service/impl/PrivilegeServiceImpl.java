package com.shrmus.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shrmus.mapper.PrivilegeMapper;
import com.shrmus.mapper.RoleMapper;
import com.shrmus.mapper.RolePrivilegeMapper;
import com.shrmus.mapper.UserMapper;
import com.shrmus.mapper.UserPrivilegeMapper;
import com.shrmus.mapper.UserRoleMapper;
import com.shrmus.pojo.Privilege;
import com.shrmus.pojo.PrivilegeExample;
import com.shrmus.pojo.PrivilegeExample.Criteria;
import com.shrmus.pojo.RolePrivilege;
import com.shrmus.pojo.RolePrivilegeExample;
import com.shrmus.pojo.UserPrivilege;
import com.shrmus.pojo.UserPrivilegeExample;
import com.shrmus.pojo.UserRole;
import com.shrmus.pojo.UserRoleExample;
import com.shrmus.service.PrivilegeService;

/**
 * 
 * <p>Title:PrivilegeServiceImpl</p>
 * <p>Description:</p>
 * @author Shr
 * @date 2018年5月22日上午2:37:41
 * @version
 */
@Service
public class PrivilegeServiceImpl implements PrivilegeService {
	@Autowired
	private PrivilegeMapper privilegeMapper;
	@Autowired
	private RolePrivilegeMapper rolePrivilegeMapper;
	@Autowired
	private UserPrivilegeMapper userPrivilegeMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	
	/**
	 * 查找所有父权限
	 */
	@Override
	public List<Privilege> getParentPrivilegeList() {
		PrivilegeExample privilegeExample = new PrivilegeExample();
		Criteria criteria = privilegeExample.createCriteria();
		criteria.andPrivilegeParentIdEqualTo(0);
		List<Privilege> privilegeList = privilegeMapper.selectByExample(privilegeExample);
		return privilegeList;
	}
	
	/**
	 * 查找所有权限
	 */
	@Override
	public List<Privilege> getPrivilegeList() {
		PrivilegeExample privilegeExample = new PrivilegeExample();
		List<Privilege> privilegeList = privilegeMapper.selectByExample(privilegeExample);
		return privilegeList;
	}

	/**
	 * 添加权限
	 */
	@Transactional
	public void addPrivilege(Privilege privilege) {
		privilegeMapper.insert(privilege);
	}

	/**
	 * 根据权限名称获取权限信息
	 */
	@Override
	public Privilege getPrivilegeByName(String privilegeName) {
		PrivilegeExample privilegeExample = new PrivilegeExample();
		Criteria criteria = privilegeExample.createCriteria();
		criteria.andPrivilegeNameEqualTo(privilegeName);
		List<Privilege> privilegeList = privilegeMapper.selectByExample(privilegeExample);
		// 数据库没有设置唯一约束，但是添加时按照权限名唯一的约定，所以获取根据权限名获取时只获得一条记录
		Privilege privilege = privilegeList.get(0);
		return privilege;
	}
	
	/**
	 * 根据权限id查找权限信息
	 */
	@Override
	public Privilege getPrivilegeById(Integer privilegeId) {
		PrivilegeExample privilegeExample = new PrivilegeExample();
		Criteria criteria = privilegeExample.createCriteria();
		criteria.andPrivilegeIdEqualTo(privilegeId);
		List<Privilege> privilegeList = privilegeMapper.selectByExample(privilegeExample);
		// 数据库没有设置唯一约束，但是添加时按照权限名唯一的约定，所以获取根据权限名获取时只获得一条记录
		Privilege privilege = privilegeList.get(0);
		return privilege;
	}

	/**
	 * 分配权限
	 */
	@Transactional
	public void allocationPrivileges(String type, Integer typeId, List<Integer> allocationPrivilegeList) {
		// 接收类型
		// 接收类型id
		// 接收被分配的权限id
		// 从数据库中查找已有的权限
		if("role".equals(type)) {
			allocationRolePrivileges(typeId,allocationPrivilegeList);
		} else if("user".equals(type)) {
			allocationUserPrivileges(typeId,allocationPrivilegeList);
		}
	}

	/**
	 * 给角色分配权限
	 * @param roleId
	 * @param allocationPrivilegeList
	 */
	private void allocationRolePrivileges(Integer roleId, List<Integer> allocationPrivilegeList) {
		// 根据id获得所有的权限
		List<Privilege> privilegeList = privilegeMapper.getPrivilegeListByRoleId(roleId);
		// 如果已有权限为空就全添加到数据库
		if(0 == privilegeList.size()) {
			for(Integer privilegeId : allocationPrivilegeList) {
				// 添加角色权限
				RolePrivilege rolePrivilege = new RolePrivilege(roleId,privilegeId);
				rolePrivilegeMapper.insert(rolePrivilege);
				
				// 给拥有这个角色的用户添加这个权限
				UserRoleExample userRoleExample = new UserRoleExample();
				com.shrmus.pojo.UserRoleExample.Criteria userRoleExampleCriteria = userRoleExample.createCriteria();
				userRoleExampleCriteria.andRoleIdEqualTo(roleId);
				List<UserRole> userRoleList = userRoleMapper.selectByExample(userRoleExample);
				for(UserRole userRole : userRoleList) {
					UserPrivilege userPrivilege = new UserPrivilege(userRole.getUserId(), privilegeId);
					userPrivilegeMapper.insert(userPrivilege);
				}
			}
		}else {
			// 如果不为空，先保存角色已有的权限
			List<Integer> ownedPrivilegeList = new ArrayList<Integer>();
			for(Privilege privilege:privilegeList) {
				ownedPrivilegeList.add(privilege.getPrivilegeId());
			}
			// 先将角色没有的权限添加，已有权限是否包含privilegeArray
			for(Integer privilegeId : allocationPrivilegeList) {
				// 角色是否已有这个权限，如果没有就添加，如果有就什么都不做
				if(false == ownedPrivilegeList.contains(privilegeId)) {
					RolePrivilege rolePrivilege = new RolePrivilege(roleId,privilegeId);
					rolePrivilegeMapper.insert(rolePrivilege);
					
					// TODO 给拥有这个角色的用户添加这个权限,用户分配了权限之后，角色再分配这个权限，会出现重复数据
					UserRoleExample userRoleExample = new UserRoleExample();
					com.shrmus.pojo.UserRoleExample.Criteria userRoleExampleCriteria = userRoleExample.createCriteria();
					userRoleExampleCriteria.andRoleIdEqualTo(roleId);
					List<UserRole> userRoleList = userRoleMapper.selectByExample(userRoleExample);
					for(UserRole userRole : userRoleList) {
						// 查找用户是否有这个权限
						UserPrivilegeExample userPrivilegeExample = new UserPrivilegeExample();
						com.shrmus.pojo.UserPrivilegeExample.Criteria userPrivilegeExampleCriteria = userPrivilegeExample.createCriteria();
						userPrivilegeExampleCriteria.andUserIdEqualTo(userRole.getUserId());
						userPrivilegeExampleCriteria.andPrivilegeIdEqualTo(privilegeId);
						List<UserPrivilege> userPrivilegeList = userPrivilegeMapper.selectByExample(userPrivilegeExample);
						if(0 == userPrivilegeList.size()) {
							UserPrivilege userPrivilege = new UserPrivilege(userRole.getUserId(), privilegeId);
							userPrivilegeMapper.insert(userPrivilege);
						}
					}
				}
			}
			// 再将角色已经存在的权限，但是没有再被分配的权限删除,privilegeArray是否包含已有的权限
			/*
			 	删除角色的权限之后先删除拥有这个角色的用户的权限
			 	查找拥有这个角色的用户
				查找用户的其他角色
				查找用户其他角色的权限
				其他权限没有就删除，有就什么都不做
			 */
			for(Integer privilegeId : ownedPrivilegeList) {
				// 如果角色原来分配的权限不在现在被分配的权限集合中，就删除这个权限，如果还在，就什么都不做
				if(false == allocationPrivilegeList.contains(privilegeId)) {
					// 给拥有这个角色的用户删除这个权限
					// 查找拥有这个角色的用户
					UserRoleExample userRoleExample = new UserRoleExample();
					com.shrmus.pojo.UserRoleExample.Criteria userRoleExampleCriteria = userRoleExample.createCriteria();
					userRoleExampleCriteria.andRoleIdEqualTo(roleId);
					List<UserRole> userRoleList = userRoleMapper.selectByExample(userRoleExample);
					
					// 标志
					boolean tag1 = false;
					// 查找这个用户的其他角色
					for(UserRole userRole : userRoleList) {
						UserRoleExample userRoleExample2 = new UserRoleExample();
						com.shrmus.pojo.UserRoleExample.Criteria userRoleExampleCriteria2 = userRoleExample2.createCriteria();
						userRoleExampleCriteria2.andUserIdEqualTo(userRole.getUserId());
						List<UserRole> userRoleList2 = userRoleMapper.selectByExample(userRoleExample2);
						
						// 标志2
						boolean tag2 = false;
						// 查找用户其他角色的权限
						for(UserRole userRole2 : userRoleList2) {
							// 当前角色不再判断
							if(roleId != userRole2.getRoleId()) {
								// 根据角色id和权限id查找，只有一条记录
								RolePrivilegeExample rolePrivilegeExample = new RolePrivilegeExample();
								com.shrmus.pojo.RolePrivilegeExample.Criteria criteria = rolePrivilegeExample.createCriteria();
								criteria.andRoleIdEqualTo(userRole2.getRoleId());
								criteria.andPrivilegeIdEqualTo(privilegeId);
								List<RolePrivilege> rolePrivilegeList = rolePrivilegeMapper.selectByExample(rolePrivilegeExample);
								// 这个用户的其他角色也有这个权限
								if(0 != rolePrivilegeList.size()) {
									tag2 = true;
									break;
								}
									
							}
							// 标志这个用户的其他角色有这个权限
							if(true == tag2) {
								tag1 = true;
								break;
							}
						}
						
						if(true == tag1) {
							break;
						}
					}
					// 用户的其他角色没有这个权限
					if(false == tag1) {
						// 在用户权限表中删除这个权限
						UserPrivilegeExample userPrivilegeExample = new UserPrivilegeExample();
						com.shrmus.pojo.UserPrivilegeExample.Criteria criteria = userPrivilegeExample.createCriteria();
						for(UserRole userRole : userRoleList) {
							criteria.andUserIdEqualTo(userRole.getUserId());
							criteria.andPrivilegeIdEqualTo(privilegeId);
						}
						userPrivilegeMapper.deleteByExample(userPrivilegeExample);
					}
					// 删除角色的权限
					RolePrivilegeExample rolePrivilegeExample = new RolePrivilegeExample();
					com.shrmus.pojo.RolePrivilegeExample.Criteria criteria = rolePrivilegeExample.createCriteria();
					criteria.andRoleIdEqualTo(roleId);
					criteria.andPrivilegeIdEqualTo(privilegeId);
					rolePrivilegeMapper.deleteByExample(rolePrivilegeExample);
				}
			}
		}
	}
	
	/**
	 * 给用户分配权限，暂时只做到一个用户一个角色的情况
	 * @param userId
	 * @param allocationPrivilegeList
	 */
	private void allocationUserPrivileges(Integer userId, List<Integer> allocationPrivilegeList) {
		List<Privilege> privilegeList = privilegeMapper.getPrivilegeListByUserId(userId);
		// 如果已有权限为空就全添加到数据库
		if(0 == privilegeList.size()) {
			for(Integer privilegeId : allocationPrivilegeList) {
				UserPrivilege userPrivilege = new UserPrivilege(userId,privilegeId);
				userPrivilegeMapper.insert(userPrivilege);
			}
		}else {
			// 如果不为空，先保存用户已有的权限
			List<Integer> ownedPrivilegeList = new ArrayList<Integer>();
			for(Privilege privilege:privilegeList) {
				ownedPrivilegeList.add(privilege.getPrivilegeId());
			}
			// 先将用户没有的权限添加，已有权限是否包含privilegeArray
			for(Integer privilegeId : allocationPrivilegeList) {
				// 角色是否已有这个权限，如果没有就添加，如果有就什么都不做
				if(false == ownedPrivilegeList.contains(privilegeId)) {
					UserPrivilege userPrivilege = new UserPrivilege(userId,privilegeId);
					userPrivilegeMapper.insert(userPrivilege);
				}
			}
			// 再将用户已经存在的权限，但是没有再被分配的权限删除,privilegeArray是否包含已有的权限
			for(Integer privilegeId : ownedPrivilegeList) {
				// 如果用户原来分配的权限不在现在被分配的权限集合中，就删除这个权限，如果还在，就什么都不做
				if(false == allocationPrivilegeList.contains(privilegeId)) {
					UserPrivilegeExample userPrivilegeExample = new UserPrivilegeExample();
					com.shrmus.pojo.UserPrivilegeExample.Criteria criteria = userPrivilegeExample.createCriteria();
					criteria.andUserIdEqualTo(userId);
					criteria.andPrivilegeIdEqualTo(privilegeId);
					userPrivilegeMapper.deleteByExample(userPrivilegeExample);
				}
			}
		}
	}

	/**
	 * 根据角色id获取角色拥有的权限
	 * @return 
	 */
	@Override
	public List<Privilege> getPrivilegeListByRoleId(Integer roleId) {
		List<Privilege> privilegeList = privilegeMapper.getPrivilegeListByRoleId(roleId);
		return privilegeList;
	}

	/**
	 * 根据用户id获取用户拥有的权限
	 */
	@Override
	public List<Privilege> getPrivilegeListByUserId(Integer userId) {
		List<Privilege> privilegeList = privilegeMapper.getPrivilegeListByUserId(userId);
		return privilegeList;
	}

	/**
	 * 修改权限信息
	 */
	@Transactional
	public void updatePrivilege(Privilege privilege) {
		privilegeMapper.updateByPrimaryKeySelective(privilege);
	}

	/**
	 * 删除权限
	 */
	@Transactional
	public void deletePrivilege(Integer privilegeId) {
		// 删除用户拥有的这个权限
		UserPrivilegeExample userPrivilegeExample = new UserPrivilegeExample();
		com.shrmus.pojo.UserPrivilegeExample.Criteria userPrivilegeExampleCriteria = userPrivilegeExample.createCriteria();
		userPrivilegeExampleCriteria.andPrivilegeIdEqualTo(privilegeId);
		userPrivilegeMapper.deleteByExample(userPrivilegeExample);
		// 删除角色拥有的这个权限
		RolePrivilegeExample rolePrivilegeExample = new RolePrivilegeExample();
		com.shrmus.pojo.RolePrivilegeExample.Criteria rolePrivilegeExampleCriteria = rolePrivilegeExample.createCriteria();
		rolePrivilegeExampleCriteria.andPrivilegeIdEqualTo(privilegeId);
		rolePrivilegeMapper.deleteByExample(rolePrivilegeExample);
		// 删除权限
		privilegeMapper.deleteByPrimaryKey(privilegeId);
	}

	/**
	 * 根据分配权限的type和typeId查找信息
	 */
	@Override
	public Object getObjectById(String type, Integer typeId) {
		Object object = new Object();
		if("role".equals(type)) {
			object = roleMapper.selectByPrimaryKey(typeId);
		}
		else if("user".equals(type)) {
			// 查找这个角色的信息
			object = userMapper.selectByPrimaryKey(typeId);
		}
		return object;
	}

}