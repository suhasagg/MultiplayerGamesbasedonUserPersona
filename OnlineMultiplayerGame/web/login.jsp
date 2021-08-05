<%--
    Document   : index
    Created on : 2020-6-17, 16:21:10
    Author     : greenhill zone
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <%@page import="beans.*"%>
  <%@page import="java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<LINK href="css/index.css" type="text/css" rel=stylesheet>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

<div id="rap"></div>
<div id="masthead">
<h1><a href="#"><font size="5">Multiplayer puzzle Game</font></a></h1>
</div><!-- end MASTHEAD -->

<div id="main">

<div id="content">
<!-- end header -->


<div class="post">
<div class="postop"><div class="pheadfill">&nbsp;</div></div>
<div class="storycontent">
<form action="loginServlet" method="post">
            <p>
					<INPUT style="WIDTH: 185px" value="用户名" name=account>
				</P>
                <P>
					<INPUT type=password style="WIDTH: 120px" value="" name=password>
                        </p><p>
					<INPUT type=submit name=submit value ="">
				</P>
        </form>
</div><!-- end POST -->
<div id="pagination">

</div>

</div><!-- end MAIN -->
</div><!-- end CONTENT -->



<!-- SIDEBAR LINKS -->
<div id="menu">

<div id="nav">




<div class="p-sideitem">
				 <div class="p-boxhead"><div class="p-headfill">&nbsp;</div></div>

				<div class="p-boxbody">
					<h2></h2>
					<ul>
                   <%
                   DatabaseBean db = new DatabaseBean();
                   ArrayList<UserBean> top = db.getTop(5);
                   db.close();
                   for(int i = 0;i<top.size();i++){
                   %>
                   <li class="page_item"><a href="#"><%=(i+1)%>.<%=top.get(i).getName()%>(<%=top.get(i).getScore()%>)</a></li>
                    <%}%>
					</ul>
				</div>
	</div>

<div class="sidMultiplayer puzzleace">&nbsp;</div>

	<div class="sideitem">
			 <div class="boxhead"><div class="headfill">&nbsp;</div></div>

				<div class="boxbody">

					<h2></h2>
					<ul>
					<%
                   db = new DatabaseBean();
                   ArrayList<PairBean> topp = db.getTopPair(5);
                   db.close();
                   for(int i = 0;i<top.size();i++){
                   %>
                   <li class="page_item"><a href="#"><%=(i+1)%>.<%=topp.get(i).getU1name()%>--<%=topp.get(i).getU2name()%>(<%=topp.get(i).getScore()%>)</a></li>
                    <%}%>
					</ul>
				</div>
			</div>

<div class="sideitem">
			 <div class="boxhead"><div class="headfill">&nbsp;</div></div>

				<div class="boxbody">

					<h2></h2>
					<ul>
					<li class="page_item"><a href="#" title="1"</a></li>
					<li class="page_item"><a href="#" title="2"></a></li>
					</ul>
</div></div>


</div></div></div></body>
</body>




</html>
