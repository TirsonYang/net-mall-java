package com.net.mall.server.boss.controller;

import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.common.result.Result;
import com.net.mall.pojo.dto.TicketDTO;
import com.net.mall.pojo.vo.TicketVO;
import com.net.mall.server.boss.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("bossTicketController")
@RequestMapping("/boss/ticket")
@Slf4j
public class TicketController {


    @Autowired
    private TicketService ticketService;



    /**
     * 发放优惠券
     * @param dto
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody TicketDTO dto){
        if (dto==null){
            return Result.error("参数错误");
        }
        log.info("超管新增优惠券：{}",dto);
        ticketService.add(dto);
        return Result.success();
    }

    /**
     * 列表根据用户ID查询优惠券
     * @param userId
     * @return
     */
    @GetMapping("list")
    public Result<List<TicketVO>> listById(@RequestParam(required = false) Long userId){
        log.info("超管查询用户优惠券：{}",userId);
        List<TicketVO> list=ticketService.listById(userId);
        return Result.success(list);
    }

    /**
     * 分页根据用户ID分页查询优惠券
     * @param query
     * @param userId
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult> pageById(@RequestParam PageQuery query, @RequestParam Long userId){
        if (userId==null){
            return Result.error("参数错误!");
        }
        log.info("超管查询用户优惠券：{},分页查询：{}",userId,query);
        PageResult page=ticketService.pageById(userId,query);
        return Result.success(page);
    }

}
