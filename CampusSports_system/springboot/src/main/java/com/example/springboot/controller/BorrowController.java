package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.controller.request.SportsPageRequest;
import com.example.springboot.entity.Sports;
import com.example.springboot.service.ISportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/sports")
public class SportsController {

    @Autowired
    ISportsService sportsService;
    

    @PostMapping("/save")
    public Result save(@RequestBody Sports obj){
        sportsService.save(obj);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Sports obj = sportsService.getById(id);
        return Result.success(obj);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Sports obj) {
        sportsService.update(obj);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        sportsService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list(){
        List<Sports> list = sportsService.list();
        return Result.success(list);
        //return sportsService.list();
    }


    @GetMapping("/page")
    public Result page(SportsPageRequest PageRequest){
        return Result.success(sportsService.page(PageRequest));
    }
}
