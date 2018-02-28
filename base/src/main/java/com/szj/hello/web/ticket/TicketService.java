package com.szj.hello.web.ticket;

/**
 * 售票服务
 * Created by sunzengjun on 2018/2/27.
 */
public interface TicketService {

    //售票
    public void sellTicket();

    //问询
    public void inquire();

    //退票
    public void withdraw();
}
