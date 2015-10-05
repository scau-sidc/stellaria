package com.github.scausidc.stellaria.letter.dao;

import java.util.List;
import java.util.Date;

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

  // CREATE
    public OutgoLetter create(String to)
    {
        OutgoLetter outLet = new OutgoLetter();

        outLet.setTm(new Date(/* now */));
        outLet.setRemote(to);

        this.save(outLet);

        return(outLet);
    }

  // EXTENDED
    public List<OutgoLetter> forTarget(String openid)
    {
        DetachedCriteria dc = DetachedCriteria.forClass(this.classOfT())
            .add(Restrictions.eq("target", openid))
            .addOrder(Order.desc("tm"));

        return(
            this.search(dc)
        );
    }
}
