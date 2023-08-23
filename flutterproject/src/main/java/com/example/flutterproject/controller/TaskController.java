package com.example.flutterproject.controller;

import com.example.flutterproject.entity.Taskitem;
import com.example.flutterproject.repository.TaskRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/tasks")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    @GetMapping
    public List<Taskitem> getItem(){
        return taskRepository.findAll();
    }
   @PostMapping("/add")
    public Taskitem addTask(@Valid @RequestBody Taskitem taskitem){
        return taskRepository.save(taskitem);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateTask(@PathVariable Long id){
        boolean exist = taskRepository.existsById(id);
        if(exist){
            Taskitem task = taskRepository.getReferenceById(id);
            boolean done = task.isDone();
            task.setDone(!done);
            taskRepository.save(task);
            return new ResponseEntity("Task is updated", HttpStatus.OK);
        }
        return new ResponseEntity("Task does not exist", HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id){
        boolean exist = taskRepository.existsById(id);
        if(exist) {
           taskRepository.deleteById(id);
            return new ResponseEntity("Task is deleted", HttpStatus.OK);
        }
        return new ResponseEntity("Task does not exist", HttpStatus.BAD_REQUEST);
    }
}
