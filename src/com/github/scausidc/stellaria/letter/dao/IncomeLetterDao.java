package com.github.scausidc.stellaria.letter.dao;

import java.util.List;

import org.hibernate.criterion.*;
import com.github.cuter44.nyafx.dao.*;

import com.github.scausidc.stellaria.letter.model.*;

public class IncomeLetterDao extends DaoBase<IncomeLetter>
{
  // CONSTRUCT
    public IncomeLetterDao()
    {
        super();
    }

  // SINGLETON
    private static class Singleton
    {
        public static IncomeLetterDao instance = new IncomeLetterDao();
    }

    public static IncomeLetterDao getInstance()
    {
        return(Singleton.instance);
    }

  // GET
    @Override
    public Class classOfT()
    {
        return(IncomeLetter.class);
    }

  // EXTENDED
    public List<IncomeLetter> forFromUser(String fromUserOpenid)
    {
        DetachedCriteria dc = DetachedCriteria.forClass(this.classOfT())
            .add(Restrictions.eq("fromUser", fromUserOpenid))
            .addOrder(Order.desc("tm"));

        return(
            this.search(dc)
        );
    }
}
