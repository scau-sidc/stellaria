package com.github.scausidc.stellaria.letter.dao;

import java.util.List;

import org.hibernate.criterion.*;
import com.github.cuter44.nyafx.dao.*;

import com.github.scausidc.stellaria.letter.model.*;

public class LetterDao extends DaoBase<Letter>
{
  // CONSTRUCT
    public LetterDao()
    {
        super();
    }

  // SINGLETON
    private static class Singleton
    {
        public static LetterDao instance = new LetterDao();
    }

    public static LetterDao getInstance()
    {
        return(Singleton.instance);
    }

  // GET
    @Override
    public Class classOfT()
    {
        return(Letter.class);
    }

  // EXTENDED
    public List<Letter> forTarget(String openid)
    {
        DetachedCriteria dc = DetachedCriteria.forClass(this.classOfT())
            .add(Restrictions.eq("target", openid))
            .addOrder(Order.desc("tm"));

        return(
            this.search(dc)
        );
    }
}
