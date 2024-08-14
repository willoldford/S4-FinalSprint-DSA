package com.keyin.finalsprint.repository;


import com.keyin.finalsprint.controller.BSTController;
import com.keyin.finalsprint.model.BSTRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BSTRepository extends JpaRepository<BSTRecord, Long> {
}
