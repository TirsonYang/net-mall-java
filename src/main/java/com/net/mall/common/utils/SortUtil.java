package com.net.mall.common.utils;

import com.net.mall.pojo.vo.OrdersVO;
import com.net.mall.pojo.vo.TicketVO;

import java.time.LocalDateTime;
import java.util.List;

public class SortUtil {

    public static void sortTicketList(List<TicketVO> ticketVOList) {
        // 对列表进行排序
        ticketVOList.sort((t1, t2) -> {
            // 先比较状态，状态为1的排在前面
            boolean t1IsStatus1 = t1.getStatus() == 1;
            boolean t2IsStatus1 = t2.getStatus() == 1;

            if (t1IsStatus1 && !t2IsStatus1) {
                return -1; // t1排在前面
            } else if (!t1IsStatus1 && t2IsStatus1) {
                return 1; // t2排在前面
            } else if (t1IsStatus1 && t2IsStatus1) {
                // 都是状态1的情况下，比较过期时间，时间小的排在前面
                LocalDateTime t1Expire = t1.getExpireTime();
                LocalDateTime t2Expire = t2.getExpireTime();

                // 处理可能的null情况
                if (t1Expire == null && t2Expire == null) {
                    return 0;
                } else if (t1Expire == null) {
                    return 1;
                } else if (t2Expire == null) {
                    return -1;
                }

                return t1Expire.compareTo(t2Expire);
            } else {
                // 都不是状态1的情况，保持原有顺序或任意排列
                return 0;
            }
        });
    }

    public static void sortOrdersList(List<OrdersVO> ordersVOList){
        // 对列表进行排序
        ordersVOList.sort((t1, t2) -> {
            // 先比较状态，状态为1的排在前面
            boolean t1IsStatus1 = t1.getStatus() == 2;
            boolean t2IsStatus1 = t2.getStatus() == 2;

            if (t1IsStatus1 && !t2IsStatus1) {
                return -1; // t1排在前面
            } else if (!t1IsStatus1 && t2IsStatus1) {
                return 1; // t2排在前面
            } else if (t1IsStatus1 && t2IsStatus1) {
                // 都是状态1的情况下，比较过期时间，时间小的排在前面
                LocalDateTime t1CheckoutTime = t1.getCheckoutTime();
                LocalDateTime t2CheckoutTime= t2.getCheckoutTime();

                // 处理可能的null情况
                if (t1CheckoutTime == null && t2CheckoutTime == null) {
                    return 0;
                } else if (t1CheckoutTime == null) {
                    return 1;
                } else if (t2CheckoutTime == null) {
                    return -1;
                }

                return t1CheckoutTime.compareTo(t2CheckoutTime);
            } else {
                // 都不是状态1的情况，保持原有顺序或任意排列
                return 0;
            }
        });
    }
}
