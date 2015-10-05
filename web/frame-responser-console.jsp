<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- THIS DOCUMENT IS A DEMO. IT IS NOT MEETING PRODUCTION STANDARD. DO NOT ABUSE. -->
<html>
 <head>
  <title>Responser Console</title>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="bootstrap-3.2.0-dist/css/bootstrap.min.css">
  <script src="jquery-1.11.2.min.js"></script>
  <script src="bootstrap-3.2.0-dist/js/bootstrap.min.js"></script>
  <style>
    html, body {
      width:100%;
      height:100%;
    }

    iframe {
      border: none;
    }

    .full-height {
      height: 100%;
    }

    .full-width {
      width: 100%;
    }

  </style>
 </head>
 <body>
  <div class="container full-height">
    <div class="row full-height">
      <div class="col-xs-3 full-height">
        <iframe name="frame-session-list" class="full-height full-width" src="session-list.jsp">
        </iframe>
      </div>

      <div class="col-xs-9 full-height">
        <iframe name="frame-reply-console" class="full-height full-width" src="data:text/html,<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'></head><body><div style='margin-top:120px; width:100%; text-align:center; font:400 13px sans-serif;'>←(￣▽￣～) ご会話を選択ください...</div></body></html>">
        </iframe>
      </div>
    </div>
  </div>
 </body>
</html>
