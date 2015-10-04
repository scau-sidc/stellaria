package com.github.scausidc.stellaria.letter.core;

import java.util.Date;
import java.util.List;

import com.github.cuter44.nyafx.dao.DaoBase;
import static com.github.cuter44.nyafx.dao.EntityNotFoundException.entFound;
import com.github.cuter44.wxmsg.*;
import com.github.cuter44.wxmsg.msg.*;
import com.github.cuter44.wxmsg.constants.*;
import com.github.cuter44.wxmsg.reply.ReplySuccess;

import com.github.scausidc.stellaria.letter.model.*;
import com.github.scausidc.stellaria.letter.dao.*;

public class IncomeLetterCtl
{
    protected IncomeLetterDao inLetDao;
    protected LetterSessionDao letSessDao;

  // CONSTRUCT
    public IncomeLetterCtl()
    {
        super();

        this.inLetDao = IncomeLetterDao.getInstance();
        this.letSessDao = LetterSessionDao.getInstance();

        return;
    }

  // SINGLETON
    private static class Singleton
    {
        public static IncomeLetterCtl instance = new IncomeLetterCtl();
    }

    public static IncomeLetterCtl getInstance()
    {
        return(Singleton.instance);
    }

  // WXMSG HANDLER
    protected class TextMsgSerializer
        implements WxmsgHandler
    {
      // WXMSG HANDLER
        public boolean handle(WxmsgBase msg)
            throws Exception
        {
            if (!MsgType.text.equals(msg.getMsgType()))
                throw(new IllegalArgumentException("Accept only MsgType.text, but providing:"+msg.getMsgType()));

            MsgText m = (MsgText)msg;

            IncomeLetter inLet = new IncomeLetter();

            inLet.setFromUser   (m.getFromUserName()    );
            inLet.setTm         (m.getCreateTime()      );
            inLet.setMsgType    (MsgType.text.toString());
            inLet.setContent    (m.getContent()         );

            try
            {
                IncomeLetterCtl.this.inLetDao.begin();

                LetterSession ls = IncomeLetterCtl.this.letSessDao.getOrCreate(inLet.getFromUser());

                ls.setArchived(false);
                ls.setLastReceive(new Date(/*now*/));

                IncomeLetterCtl.this.letSessDao.update(ls);
                IncomeLetterCtl.this.inLetDao.save(inLet);

                IncomeLetterCtl.this.inLetDao.commit();

                m.setReply(ReplySuccess.getInstance());

                return(true);
            }
            finally
            {
                IncomeLetterCtl.this.inLetDao.close();
            }
        }
    }

    public final TextMsgSerializer textMsgHandler = new TextMsgSerializer();

}
