package com.github.scausidc.stellaria.letter.core;

import java.util.Date;
import java.util.List;
import java.io.IOException;

import com.github.cuter44.nyafx.dao.DaoBase;
import static com.github.cuter44.nyafx.dao.EntityNotFoundException.entFound;
import com.github.cuter44.wxmp.*;
import com.github.cuter44.wxmp.reqs.*;
import com.github.cuter44.wxmp.resps.*;

import com.github.scausidc.stellaria.letter.model.*;
import com.github.scausidc.stellaria.letter.dao.*;

public class OutgoLetterCtl
{
    protected OutgoLetterDao outLetDao;
    protected LetterSessionDao letSessDao;
    protected WxmpFactory wxmpFactory;

  // CONSTRUCT
    public OutgoLetterCtl()
    {
        super();

        this.outLetDao = OutgoLetterDao.getInstance();
        this.letSessDao = LetterSessionDao.getInstance();
        this.wxmpFactory = WxmpFactory.getDefaultInstance();

        return;
    }

  // SINGLETON
    private static class Singleton
    {
        public static OutgoLetterCtl instance = new OutgoLetterCtl();
    }

    public static OutgoLetterCtl getInstance()
    {
        return(Singleton.instance);
    }

  // SEND MSG
    public OutgoLetter sendText(String to, String content)
        throws IOException
    {
        OutgoLetter outLet = this.outLetDao.create(to);

        outLet.setMsgType("text");
        outLet.setContent(content);

        LetterSession ls = this.letSessDao.getOrCreate(outLet.getRemote());

        ls.setLastReply(new Date(/*now*/));

        this.letSessDao.update(ls);

        this.outLetDao.update(outLet);

        this.doSendText(outLet);

        return(outLet);
    }

    protected void doSendText(OutgoLetter outLet)
        throws IOException
    {
        MessageCustomSendText wxreq1 = (MessageCustomSendText)this.wxmpFactory.instantiateWithToken(MessageCustomSendText.class);
        wxreq1.setTouser(outLet.getRemote());
        wxreq1.setContent(outLet.getContent());

        wxreq1.build().execute();

        return;
    }
}
