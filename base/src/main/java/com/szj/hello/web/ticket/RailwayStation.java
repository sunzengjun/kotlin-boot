package com.szj.hello.web.ticket;

/**
 * RailwayStation 实现 TicketService
 * Created by sunzengjun on 2018/2/27.
 */
public class RailwayStation implements TicketService {

    @Override
    public void sellTicket(){
        System.out.println("售票............");
    }
    @Override
    public void inquire() {
        System.out.println("问询.............");
    }
    @Override
    public void withdraw() {
        System.out.println("退票.............");
    }
}
