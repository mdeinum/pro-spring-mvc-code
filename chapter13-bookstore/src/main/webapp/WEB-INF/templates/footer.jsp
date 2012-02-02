<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

	<jsp:directive.page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" />

	<footer>
		<div class="left_footer">
			<spring:url value="/public/resources/images/footer_logo.gif" var="logo" />
			<img src="${logo}" alt="" title="" /><br/> 
			<a href="http://csscreme.com/freecsstemplates/" title="free templates">
				<spring:url value="/public/resources/images/csscreme.gif" var="csscreme" /> 
				<img src="${csscreme}" alt="free templates" title="free templates" border="0" />
			</a>
		</div>
		<div class="right_footer">
			<a href="#">home</a> <a href="#">about us</a> <a href="#">services</a>
			<a href="#">privacy policy</a> <a href="#">contact us</a>
		</div>
	</footer>

