<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ page import="
  java.util.List,
  java.util.Date,
  java.text.SimpleDateFormat,

  org.hibernate.criterion.*,
  com.github.cuter44.nyafx.servlet.Params,

  com.github.scausidc.stellaria.letter.model.*,
  com.github.scausidc.stellaria.letter.dao.*,
  com.github.scausidc.stellaria.letter.core.*,

  java.lang.Object
"%>
<%
  request.setCharacterEncoding("utf-8");

  LetterSessionDao letSessDao = LetterSessionDao.getInstance();
  LetterDao letDao = LetterDao.getInstance();
  OutgoLetterCtl outLetCtl = OutgoLetterCtl.getInstance();
  
  try
  {
%>
<!-- THIS DOCUMENT IS A DEMO. IT IS NOT MEETING PRODUCTION STANDARD. DO NOT ABUSE. -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="full-height">
 <head>
  <title>Instant Msg Console</title>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="bootstrap-3.2.0-dist/css/bootstrap.min.css">
  <script src="jquery-1.11.2.min.js"></script>
  <script src="date.format.js"></script>
  <script src="bootstrap-3.2.0-dist/js/bootstrap.min.js"></script>
  <style>
    .full-height {
      height: 100%;
    }

    .full-width {
      width: 100%;
    }

    .letter {
      display: inline-block;
      min-width: 55%;
      border: 1px solid #c0c0c0;
      border-radius: 4px;
      clear: both;
      margin: 8px 0px;
    }

    .IncomeLetter {
      float: left;
      background-color: #ffffff;
    }

    .IncomeLetter .letter-me{
      display: none;
    }

    .OutgoLetter {
      float: right;
      background-color: #edf4ed;
    }

    .OutgoLetter .letter-remote{
      display: none;
    }

    .letter-head {
      border-bottom: 1px solid #d0d0d0;
    }

  </style>
 </head>
 <body class="full-height">

  <div id="wrap-history" class="full-width container" style="position:absolute; bottom:240px;">
    <%
      Long id = Params.getLong(request, "id");
      Date since = Params.getDate(request, "since");
      String action = Params.getString(request, "action");
      //String openid = null;

      if ("replyText".equals(action))
      {
        String content = Params.getString(request, "content");

        letSessDao.begin();

        LetterSession ls = letSessDao.get(id);

        outLetCtl.sendText(ls.getOpenid(), content);

        letSessDao.commit();
      }

      if (id == null)
    {
    %>
        <div class='alert alert-danger alert-dismissible'>パラメ—タ— <code>id</code> がありません. </div>
    <%
      }
      else
      {
        if (since == null)
        {
          long now = System.currentTimeMillis();
          now = now - now % 86400000L;
          since = new Date(now);
        }

        letSessDao.begin();

        LetterSession ls = letSessDao.get(id);

        ls.setLastRead(new Date(/* now */));
        letSessDao.update(ls);
        //openid = ls.getOpenid();

        DetachedCriteria dc = DetachedCriteria.forClass(Letter.class)
          .add(Restrictions.eq("remote", ls.getOpenid()))
          .add(Restrictions.ge("tm", since))
          .addOrder(Order.asc("tm"));

        List<Letter> l = letDao.search(dc);

        for (Letter le:l)
        {
    %>
          <div class="letter <%=le.getClazz()%>" data-id="<%=le.getId()%>">
            <p class="letter-head small">
              <span class="letter-me">味方</span>
              <span class="letter-remote"><%=le.getRemote()%></span>
              &nbsp;
              <span class="glyphicon glyphicon-time"></span>
              <span class="letter-tm tm-to-translate" data-tm="<%=le.getTm().getTime()%>"></span>
            </p>
            <p class="letter-body">
              <%
                String type = le.getMsgType();
                String content = le.getContent();

                // Assume text
                content = content.replaceAll("\n", "<br/>");
                out.println(content);
              %>
            </p>
          </div>
    <%
        }

        letSessDao.commit();
      }
    %>
  </div>

  <div id="wrap-reply-area" class="container full-width" style="position:absolute; bottom:0px; height:200px;">
    <form method="POST" class="disable">
      <input type="hidden" name="session" value="<%=id%>" />
      <textarea class="form-control full-width" name="content" rows="5" required ></textarea>
      <br>
      <button class="btn btn-primary <%=((id==null)?"disabled":"")%>" type="submit" name="action" value="replyText" style="float:right;">返信</button>
    </form>
  </div>


  <script>
    var sessionId = <%=id%>;
    var since = <%=since.getTime()%>;
    
  </script>
  <script>
    function translateTm()
    {
      var l = $(".tm-to-translate");
      for (var i=0; i<l.length; i++)
        l[i].innerText = new Date((Number)(l[i].dataset.tm)).format("yyyy-mm-dd HH:MM:ss");
    }

    translateTm.call();
  </script>
 </body>
</html>
<%
  }
  finally
  {
    letSessDao.close();
  }
%>