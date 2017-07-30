package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.HometownRepository;

@Service
public class HometownService {

	private final HometownRepository repository;

	@Autowired
	public HometownService(HometownRepository repository) {
		this.repository = repository;
	}

	/**
	 * 出身地の名前が正しいか判定する。
	 * 
	 * @param name
	 *            出身地の名前
	 * @return 出身地の名前が正しいとき true, そうでないとき false
	 */
	public boolean isValidName(String name) {
		return repository.countByName(name) > 0;
	}
}
