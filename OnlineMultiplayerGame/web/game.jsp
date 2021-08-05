<%--
    Document   : index
    Created on : 2020-6-17, 16:21:10
    Author     : greenhill zone
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
 <%@page import="beans.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<LINK href="css/index.css" type="text/css" rel=stylesheet>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>

    <script type="text/javascript" language="javascript"
         src="js/prototype.js" ></script>
    <script type="text/javascript" language="javascript">
        var myUpdate;
        myUpdate = new Ajax.PeriodicalUpdater(
			 'pagination',
			 'http://localhost:8084/multiplayerpuzzleGame/CheckCurrentPicServlet',
			 {
			 	method: 'post',
				evalScripts: true,
				frequency: 1,
				decay: 1
			});

          var timerExe;
          timerExe  = new  PeriodicalExecuter(showTime,1);
          var timerCount = 120;
          function showTime()
          {
              var sec = timerCount % 60;
              var min = (timerCount - sec)/60;
              $('d3').innerHTML = "00 : ";
              if(min<10)
                  $('d3').innerHTML+="0";
              $('d3').innerHTML+=min;
              $('d3').innerHTML+=" : ";
              if(sec<10)
                  $('d3').innerHTML+="0";
              $('d3').innerHTML+=sec;

              timerCount--;
          }

          var myPass = new Ajax.Request(
                  "http://localhost:8084/multiplayerpuzzleGame/PassServlet", {
                  method: 'get'
                  //parameters: pars,
                  //onComplete: showWordRMultiplayer puzzleonse
              });

           function submitword()
           {
                 var pars = "word="+$F("wordlabel");
                 var myAjax = new Ajax.Request(
                  "http://localhost:8084/multiplayerpuzzleGame/AddWordServlet", {
                  method: 'get',
                  parameters: pars,
                  onComplete: showWordRMultiplayer puzzleonse
              });
           }

           function showWordRMultiplayer puzzleonse()
           {
               $('d1').innerHTML =  $('d1').innerHTML+ "<li>"+$F("wordlabel")+"</li>";
               $F("wordlabel")="";
           }

           function processPass()
           {
                var myAjax = new Ajax.Request(
                  "http://localhost:8084/multiplayerpuzzleGame/PassServlet", {
                  method: 'get'
                  //parameters: pars,
                  //onComplete: showWordRMultiplayer puzzleonse
              });
           }
     </script>

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

<img id="gameImg" src="http://img.baidu.com/img/logo-zhidao.gif" width="400" height="300" />
<form>
<input name="label"  id="wordlabel"  value="" >
<input type=button   value="submit" onclick="submitword()">
<input type=button   value="pass" onclick="processPass()">
</form>

<div class="themeta" id="d1">
</div>

<div class="themeta" id="d2">
    score  :   0
</div>

<div class="themeta" id="d3">
</div>

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
					<li class="page_item"><a href="#" title="1">1.1(120123)</a></li>
					<li class="page_item"><a href="#" title="2">2.2(114679)</a></li>
					<li class="page_item"><a href="#" title="3">3.3(809587)</a></li>
					<li class="page_item"><a href="#" title="4">4.4(779877)</a></li>
					<li class="page_item"><a href="#" title="5">5.5(643876)</a></li>
					</ul>
				</div>
	</div>

<div class="sidMultiplayer puzzleace">&nbsp;</div>

	<div class="sideitem">
			 <div class="boxhead"><div class="headfill">&nbsp;</div></div>

				<div class="boxbody">

					<h2>组队排行</h2>
					<ul>
					<li class="page_item"><a href="#" title="1">1.3(120123)</a></li>
					<li class="page_item"><a href="#" title="2">2.3(114679)</a></li>
					<li class="page_item"><a href="#" title="3">3.3.63(809587)</a></li>
					<li class="page_item"><a href="#" title="4">4.4-83(779877)</a></li>
					<li class="page_item"><a href="#" title="5">5.5-37(643876)</a></li>
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
