<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

	<c:if test="${authenticationOk =='true'}">
		<div id="authenticationOk"
			style="color: green; display: block; margin-left: 15px; margin-bottom: 10px;">
			<table>
				<tr>
					<td>
						<ul style="list-style-type: disc">
							<li>
								<h3>
									<spring:message code="label.page.main.authentication.ok" arguments="${username}" />
								</h3>
							</li>
						</ul>
					</td>
				</tr>
			</table>
		</div>
	</c:if>

	<script>
		dojo.addOnLoad(function(){
			function fadeIt() {
				if(dojo.byId("authenticationOk")){
  	    			dojo.style("authenticationOk", "opacity", "1");
					var fadeOutArgs = {node: "authenticationOk", duration: 15000};
           			dojo.fadeOut(fadeOutArgs).play();
				}
			}
   			fadeIt();
		});
	</script>
