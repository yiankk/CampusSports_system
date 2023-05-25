package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.controller.request.BorrowPageRequest;
import com.example.springboot.entity.Borrow;
import com.example.springboot.entity.Retur;
import com.example.springboot.service.IBorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/borrow")
public class BorrowController {

    @Autowired
    IBorrowService borrowService;
    

    @PostMapping("/save")
    public Result save(@RequestBody Borrow obj){
        borrowService.save(obj);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Borrow obj = borrowService.getById(id);
        return Result.success(obj);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Borrow obj) {
        borrowService.update(obj);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        borrowService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list(){
        List<Borrow> list = borrowService.list();
        return Result.success(list);
        //return borrowService.list();
    }


    @GetMapping("/page")
    public Result page(BorrowPageRequest PageRequest){
        return Result.success(borrowService.page(PageRequest));
    }


    @PostMapping("/saveRetur")
    public Result saveRetur(@RequestBody Retur obj){
        borrowService.saveRetur(obj);
        return Result.success();
    }

    @GetMapping("/pageRetur")
    public Result pageRetur(BorrowPageRequest PageRequest){
        return Result.success(borrowService.pageRetur(PageRequest));
    }

    @DeleteMapping("/deleteRetur/{id}")
    public Result deleteRetur(@PathVariable Integer id) {
        borrowService.deleteReturById(id);
        return Result.success();
    }
}
