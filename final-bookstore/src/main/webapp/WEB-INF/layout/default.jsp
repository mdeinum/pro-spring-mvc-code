<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Bookstore | <decorator:title /></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>" >
    <decorator:head />
</head>
<body>
    <div id="wrap">
    <header>
        <div class="logo"><a href="<c:url value="/index.htm"/>"><img src="<c:url value="/resources/images/logo.gif"/>" alt="" title="" border="0" /></a></div>            
        <nav>
            <ul style="float: left;">                                                                       
                <li class="selected"><a href="index.html"><spring:message code="nav.home"/></a></li>
                <li><a href="<c:url value="/book"/>"><spring:message code="nav.books"/></a></li>
                <li><a href="<c:url value="/customer/account"/>"><spring:message code="nav.account"/></a></li>
                <c:if test="${currentUser eq null}">
                    <li><a href="<c:url value="/customer/register"/>"><spring:message code="nav.register"/></a></li>
                    <li><a href="<c:url value="/login"/>"><spring:message code="nav.login"/></a></li>
                </c:if>
                <c:if test="${currentUser ne null}">
                    <li><a href="<c:url value="/logout"/>"><spring:message code="nav.logout"/></a></li>
                </c:if>
                
            </ul>
            <ul style="float: right;">
                <li><a href="?lang=en" class="selected"><img src="<c:url value="/resources/images/gb.gif"/>" alt="" title="" border="0" /></a></li>
                <li><a href="?lang=nl"><img src="<c:url value="/resources/images/nl.gif"/>" alt="" title="" border="0" /></a></li>
            </ul>
        </nav>     
    
    </header>
       <div class="center_content">
        <div class="left_content">
            <decorator:body />            
        </div><!--end of left content-->
        
        <div class="right_content">
              <div class="right_box">
                  <div class="title"><span class="title_icon"><img src="<c:url value="/resources/images/cart.gif"/>" alt="" title="" /></span><spring:message code="main.title.yourcart" /></div>
                  <div class="home_cart_content">${cart.books.size()} x items | <span class="red"><spring:message code="main.cart.total" arguments="0.00"/></span></div>
                  <a href="<c:url value="/cart"/>" class="view_cart"><spring:message code="nav.viewcart"/></a> | <a href="<c:url value="/cart/checkout"/>" class="view_cart"><spring:message code="nav.cartcheckout"/></a>
                  
              </div>

             <div class="right_box">
             
                <div class="title"><span class="title_icon"><img src="<c:url value="/resources/images/bullet4.gif"/>" alt="" title="" /></span><spring:message code="main.title.randombooks"/></div> 
                    <c:forEach items="${randomBooks}" var="book">
                        <div class="new_prod_box">
                            <c:url value="/book/${book.id}" var="bookUrl" />
                            <a href="${bookUrl}">${book.title}</a>
                            <div class="new_prod_bg">
                            <a href="${bookUrl}"><img src="<c:url value="/book/${book.id}/image"/>" alt="" title="" class="thumb" border="0" /></a>
                            </div>           
                        </div>
                    </c:forEach>
             </div>
        
        </div><!--end of right content-->
        
        
       
       
       <div class="clear"></div>
       </div><!--end of center content-->
       
              
       <footer>
            <div class="left_footer"><img src="<c:url value="/resources/images/footer_logo.gif"/>" alt="" title="" /><br /> <a href="http://csscreme.com/freecsstemplates/" title="free templates"><img src="<c:url value="/resources/images/csscreme.gif"/>" alt="free templates" title="free templates" border="0" /></a></div>
            <div class="right_footer">
            </div>
       </footer>
    </div>
</body>
</html>

