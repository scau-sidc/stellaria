<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ page import="
  java.util.List,
  java.text.SimpleDateFormat,

  org.hibernate.criterion.*,

  com.github.scausidc.stellaria.letter.model.*,
  com.github.scausidc.stellaria.letter.dao.*,
  com.github.scausidc.stellaria.letter.core.*,

  java.lang.Object
"%>
<%
  LetterSessionDao letSessDao = LetterSessionDao.getInstance();
  
  try
  {
%>
<!-- THIS DOCUMENT IS A DEMO. IT IS NOT MEETING PRODUCTION STANDARD. DO NOT ABUSE. -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <title>Responser Console</title>
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

    .badge-unread, .badge-unreplied {
      margin: 0px 4px;
    }
  </style>
 </head>
 <body>
  <div class="container">
    <div class="row">
      <div class="col-xs-12 ">
        <button class="btn btn-info" type="button" style="float:right; margin:8px 0px;" onclick="javascript:location.reload();">新着を読み込む</button>
      </div>
    </div>
    <div class="row">
      <div class="col-xs-12 list-group small">
        <%
          DetachedCriteria dc = DetachedCriteria.forClass(LetterSession.class)
            .addOrder(Order.desc("lastReceive"));

          letSessDao.begin();

          List<LetterSession> l = letSessDao.search(dc);

          for (LetterSession ls:l)
          {
        %>
          <a class='list-group-item' href='reply-console.jsp?id=<%=ls.getId()%>' target='frame-reply-console' data-id='<%=ls.getOpenid()%>'>
            <div>
              <span class="large" style="display:inline-block;"><strong><%=ls.getOpenid()%></strong></span>
              <%
                if (ls.getLastRead().before(ls.getLastReceive()))
                {
              %>
                  <span class='badge badge-unread'>未読</span>
              <%
                }

                if (ls.getLastReply().before(ls.getLastReceive()))
                {
              %>
                  <span class='badge badge-unreplied'>返信を待ち</span>
              <%
                }
              %>
            </div>

            <div>
              <span class="tm tm-last-receive tm-to-translate" data-tm="<%=ls.getLastReceive().getTime()%>"></span>
            </div>
          </a>
        <%
          }
        %>
      </div>
    </div>
  </div>
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