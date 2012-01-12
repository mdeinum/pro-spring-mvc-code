<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
            <ul>                                                                       
            <li class="selected"><a href="index.html">Home</a></li>
            <li><a href="<c:url value="/about"/>">About Us</a></li>
            <li><a href="<c:url value="/book"/>">Books</a></li>
            <li><a href="<c:url value="/customer/account"/>">Your Account</a></li>
            <li><a href="<c:url value="/customer/register"/>">Register</a></li>
            <li><a href="<c:url value="customer/login"/>">Login</a></li>
            </ul>
        </nav>     
    
    </header>
       <section class="center_content">
        <section class="left_content">
            <decorator:body />            
        </section><!--end of left content-->
        
        <section class="right_content">
            <section class="languages_box">
            <span class="red">Languages:</span>
            <a href="?lang=en" class="selected"><img src="<c:url value="/resources/images/gb.gif"/>" alt="" title="" border="0" /></a>
            <a href="?lang=fr"><img src="<c:url value="/resources/images/fr.gif"/>" alt="" title="" border="0" /></a>
            <a href="?lang=de"><img src="<c:url value="/resources/images/de.gif"/>" alt="" title="" border="0" /></a>
            </section>
                
              <section class="cart">
                  <div class="title"><span class="title_icon"><img src="<c:url value="/resources/images/cart.gif"/>" alt="" title="" /></span>Your Cart</div>
                  <div class="home_cart_content">0 x items | <span class="red">TOTAL: 0.00$</span></div>
                  <a href="cart.html" class="view_cart">view cart</a>
              </section>

             <div class="title"><span class="title_icon"><img src="<c:url value="/resources/images/bullet3.gif"/>" alt="" title="" /></span>User</div>
                <div class="about">
                    <c:if test="${request.principal == null}">
                        <form id="loginForm" method="post" action="<c:url value="/security_check.htm"/>">
                            <table>
                                <tr>
                                    <td><input type="text" id="username" placeholder="Username" maxlength="12" size="10" /></td>
                                    <td><input type="password" id="password" placeholder="Password" /></td>
                                    <td><button name="login">Login</button></td>
                                </tr>
                                <tr><td colspan="3">No Account Yet! <a href="<c:url value="register.htm"/>">register</a></tr>
                            </table>
                        </form>
                    </c:if>
                    <c:if test="${request.principal != null }">
                        <a href="<c:url value="/myaccount.htm"/>">My Account</a>                        
                    </c:if>
                </div>

             <div class="right_box">
             
                <div class="title"><span class="title_icon"><img src="<c:url value="/resources/images/bullet4.gif"/>" alt="" title="" /></span>Random Books</div> 
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
             
             <div class="right_box">
                <div class="title"><span class="title_icon"><img src="<c:url value="/resources/images/bullet5.gif"/>" alt="" title="" /></span>Categories</div> 
                <ul class="list">
                    <c:forEach items="${categories}" var="category">
                        <li><a href="<c:url value="/book/category/${category.id}/"/>">${category.name}</a></li>
                    </c:forEach>
                </ul>
             </div>         
             
        
        </section><!--end of right content-->
        
        
       
       
       <div class="clear"></div>
       </section><!--end of center content-->
       
              
       <footer>
            <div class="left_footer"><img src="<c:url value="/resources/images/footer_logo.gif"/>" alt="" title="" /><br /> <a href="http://csscreme.com/freecsstemplates/" title="free templates"><img src="<c:url value="/resources/images/csscreme.gif"/>" alt="free templates" title="free templates" border="0" /></a></div>
            <div class="right_footer">
                <a href="#">home</a>
                <a href="#">about us</a>
                <a href="#">services</a>
                <a href="#">privacy policy</a>
                <a href="#">contact us</a>
            </div>
       </footer>
    </div>
</body>
</html>

