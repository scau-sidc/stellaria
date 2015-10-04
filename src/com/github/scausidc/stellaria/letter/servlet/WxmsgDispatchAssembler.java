package com.github.scausidc.stellaria.letter.servlet;

import javax.servlet.*;
import javax.servlet.annotation.*;

import com.github.cuter44.wxmsg.*;
import com.github.cuter44.wxmsg.constants.*;

import com.github.scausidc.stellaria.letter.core.*;

@WebListener
public class WxmsgDispatchAssembler implements ServletContextListener
{
    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        WxmsgDispatcher dispatcher = WxmsgDispatcher.getDefaultInstance();
        IncomeLetterCtl inLetCtl = IncomeLetterCtl.getInstance();

        dispatcher.subscribe(MsgType.text, inLetCtl.textMsgHandler);

        return;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {
        return;
    }
}
