package com.github.scausidc.stellaria.letter.dao;

import java.util.List;

import org.hibernate.criterion.*;
import com.github.cuter44.nyafx.dao.*;

import com.github.scausidc.stellaria.letter.model.*;

public class LetterSessionDao extends DaoBase<LetterSession>
{
  // CONSTRUCT
    public LetterSessionDao()
    {
        super();
    }

  // SINGLETON
    private static class Singleton
    {
        public static LetterSessionDao instance = new LetterSessionDao();
    }

    public static LetterSessionDao getInstance()
    {
        return(Singleton.instance);
    }

  // GET
    @Override
    public Class classOfT()
    {
        return(LetterSession.class);
    }

  // EXTENDED
    public LetterSession forOpenid(String openid)
    {
        DetachedCriteria dc = DetachedCriteria.forClass(this.classOfT())
            .add(Restrictions.eq("openid", openid));

        return(
            this.get(dc)
        );
    }

    public LetterSession getOrCreate(String openid)
    {
        LetterSession ls = this.forOpenid(openid);

        if (ls != null)
            return(ls);

        // else
        ls = new LetterSession();
        ls.setOpenid(openid);
        ls.setArchived(false);

        return(ls);
    }
}
