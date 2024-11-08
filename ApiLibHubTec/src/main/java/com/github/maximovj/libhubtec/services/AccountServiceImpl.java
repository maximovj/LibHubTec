package com.github.maximovj.libhubtec.services;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.maximovj.libhubtec.dao.IAccountDao;
import com.github.maximovj.libhubtec.model.Account;

@Service
public class AccountServiceImpl implements IAccountServiceImpl {
	
	@Autowired
	private IAccountDao iAccountDao;

	@Override
	public List<Account> FindAccountAll() {
		List<Account> accounts =  new ArrayList<Account>();
		
		try {
			accounts = (List<Account>) iAccountDao.findAll();
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("AccountServiceImpl::FindAccountAll | ** Error **");
			System.out.println(e.getMessage());
		}
		
		return accounts;
	}
	
}
