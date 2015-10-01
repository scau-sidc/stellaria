package com.github.scausidc.stellaria.letter.model;
// Generated 2015/10/01 13:51:14 by Hibernate Tools 4.0.0


import java.util.Date;

/**
 * IncomeLetter generated by hbm2java
 */
public class IncomeLetter  implements java.io.Serializable {


    protected Long id;
    protected String fromUser;
    protected String content;
    protected String msgType;
    protected Date tm;

    public IncomeLetter() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromUser() {
        return this.fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMsgType() {
        return this.msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public Date getTm() {
        return this.tm;
    }

    public void setTm(Date tm) {
        this.tm = tm;
    }

  // HASH
    @Override
    public int hashCode()
    {
        int hash = 17;

        if (this.id != null)
            hash = hash * 31 + this.id.hashCode();

        return(hash);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return(true);

        if (o==null || !this.getClass().equals(o.getClass()))
            return(false);

        IncomeLetter c = (IncomeLetter)o;

        return(
            (this.id == c.id) ||
            (this.id != null && this.id.equals(c.id))
        );
    }

}


