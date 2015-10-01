package com.github.scausidc.stellaria.letter.dao;

import java.util.List;

import org.hibernate.criterion.*;
import com.github.cuter44.nyafx.dao.*;

import com.github.scausidc.stellaria.letter.model.*;

public class OutgoLetterDao extends DaoBase<OutgoLetter>
{
  // CONSTRUCT
    public OutgoLetterDao()
    {
        super();
    }

  // SINGLETON
    private static class Singleton
    {
        public static OutgoLetterDao instance = new OutgoLetterDao();
    }

    public static OutgoLetterDao getInstance()
    {
        return(Singleton.instance);
    }

  // GET
    @Override
    public Class classOfT()
    {
        return(OutgoLetter.class);
    }

  // EXTENDED
    public List<OutgoLetter> forToUser(String toUserOpenid)
    {
        DetachedCriteria dc = DetachedCriteria.forClass(this.classOfT())
            .add(Restrictions.eq("toUser", toUserOpenid))
            .addOrder(Order.desc("tm"));

        return(
            this.search(dc)
        );
    }
}
