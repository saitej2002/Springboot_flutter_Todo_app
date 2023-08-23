package com.example.flutterproject.repository;

import com.example.flutterproject.entity.Taskitem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Taskitem,Long> {
}
