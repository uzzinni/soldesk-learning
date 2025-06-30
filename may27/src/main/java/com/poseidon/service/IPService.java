package com.poseidon.service;

import java.util.Arrays;
import org.springframework.stereotype.Service;
import com.poseidon.entity.IPLog;
import com.poseidon.repository.IPRepository;
import com.poseidon.util.Util;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IPService {
	private final IPRepository ipRepository;
	private final Util util;

	public void save(String name, Object[] args) {
		IPLog ipLog = new IPLog();
		ipLog.setIdata(Arrays.toString(args));
		ipLog.setIurl(name);
		ipLog.setIip(util.getIP());
		ipRepository.save(ipLog);
	}
}
