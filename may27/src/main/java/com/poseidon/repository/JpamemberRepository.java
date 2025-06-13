package com.poseidon.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.poseidon.entity.Member;

public interface JpamemberRepository extends JpaRepository<Member, Integer>{
	// 로그인 할 때 jpamember 테이블에서 mid가 있는지 검사하기
	//SELECT * FROM japmember WHERE id=#{id}
	Optional<Member> findByMid(String id);

}
