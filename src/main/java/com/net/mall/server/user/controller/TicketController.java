package com.net.mall.server.user.controller;

import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.common.result.Result;
import com.net.mall.pojo.dto.UseTicketDTO;
import com.net.mall.pojo.vo.TicketVO;
import com.net.mall.server.user.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController("userTicketController")
@RequestMapping("/user/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    /**
     * 用户分页查询优惠券
     * @param query
     * @return
     */
    @GetMapping("page")
    public Result<PageResult> pageById(@RequestParam PageQuery query){

        log.info("用户查询本人优惠券：{},分页：{}",query);
        PageResult page=ticketService.pageById(query);
        return Result.success(page);
    }

    /**
     * 用户列表查询优惠券
     * @param
     * @return
     */
    @GetMapping("/list")
    public Result<List<TicketVO>> listById(@RequestParam Long userId){
        log.info("用户查询本人优惠券");
        List<TicketVO> list =ticketService.listById(userId);
        return Result.success(list);
    }

    @PostMapping("use")
    public Result useTicket(@RequestBody UseTicketDTO dto){
        if (dto==null){
            return Result.error("参数错误");
        }
        log.info("用户使用优惠券：{}",dto);
        Integer i=ticketService.useTicket(dto);
        switch (i){
            case 1:
                return Result.error("优惠券不存在！");
            case 2:
                return Result.error("优惠券无法使用！");
            case 3:
                return Result.error("优惠券已过期！");
            default:
                return Result.success();
        }
    }

}
