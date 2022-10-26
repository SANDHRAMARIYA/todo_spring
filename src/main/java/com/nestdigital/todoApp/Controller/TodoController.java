package com.nestdigital.todoApp.Controller;

import com.nestdigital.todoApp.Dao.TodoDao;
import com.nestdigital.todoApp.Model.TodoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;


@RestController
public class TodoController {
    @Autowired
    private TodoDao dao;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/addpost",consumes = "application/json",produces = "application/json")
    public String Addpost(@RequestBody TodoModel post){
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now() ;
        String currentdate = String.valueOf(dt.format(now));
        post.setPosted_date(currentdate);
        System.out.println(post.toString());
        dao.save(post);
        return "{status:'success'}";
    }
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/viewSingle",consumes = "application/json",produces = "application/json")
    public List<Map<String,String>> ViewSingle(@RequestBody TodoModel post){
        return  (List<Map<String, String>>) dao.ViewAllSingle(post.getUser_id());
    }
}

