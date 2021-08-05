<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
 <%@page import="beans.*"%>
  <%@page import="java.util.*"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<link rel="shortcut icon" href="/favicon.ico" />
<LINK href="css/index.css" type="text/css" rel=stylesheet>
</head>
<body>

<div id="rap">
<div id="masthead">
<h1><a href="#"><font size="5">Multiplayer puzzle Game</font></a></h1>
</div><!-- end MASTHEAD -->

<div id="main">

<div id="content">
<!-- end header -->


<div class="post">
<div class="postop"><div class="pheadfill">&nbsp;</div></div>
<div class="storycontent">
<div class="thecontent">
	<h3 class="storytitle"><a href="#" rel="bookmark"><center>游戏介绍</center></a> </h3>
	<hr />
	<p>How does it work?</p>
	<p>
	You'll be randomly paired with a partner who's online and using the feature. Over a two-minute period, you and your partner will:
	View the same set of images.
	Provide as many labels as possible to describe each image you see.
Receive points when your label matches your partner's label. The number of points will depend on how specific your label is.
See more images until time runs out.
After time expires, you can explore the images you've seen and the websites where those images were found. And we'll show you the points you've earned throughout the session.
</p><p>
Tips:

You may click the "pass" button if you can't think of any more labels for an image. If you and your partner both click "pass" you'll see the next image but receive no points for the passed image
You'll receive more points for matches with more descriptive labels. For example, this image can be described by the labels: sky (50 points), bird (60 points), soaring (120 points), or frigate bird (150 points).
</p>
</div>
	<div class="themeta" id="d1">
	<span class="where"></span>
<%
    UserBean user = new UserBean();
    if(request.getSession().getAttribute("user")==null){
       
    %>
	<form action="loginServlet" method="post">
            <p>
                <br>
					<INPUT style="WIDTH: 120px" value="" name=account>
				</P>
                <P>
					<INPUT type=password style="WIDTH: 120px" value="" name=password>
                        </p><p>
					<INPUT type=submit name=submit value ="提交">
				</P>
        </form>
		</p><p><span class="com"><a href="/multiplayerpuzzlegame/regist.jsp">立即注册</a></span></p>




 	<%}else{
         user = (UserBean)request.getSession().getAttribute("user");

        %>
 		<br>


 		！<%=user.getName()%>
 		<br>
 		:<%=user.getTime()%>秒
 		<br>
 		:<%=user.getScore()%>
 		<br>
 		:
 		<br><p>
        <p><span class="com"><a href="logoutServlet"></a></span>
 		<p><span class="who"><a href="gameWaitServlet"></a></span>

 	<%}%>
<div class="reset"></div>

</div><!-- end STORYCONTENT --> 


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
					<li class="page_item"><a href="#" title="1"></a></li>
					<li class="page_item"><a href="#" title="2"></a></li>
					</ul>
</div></div>


</div></div></div></body>
</body>
</html>
