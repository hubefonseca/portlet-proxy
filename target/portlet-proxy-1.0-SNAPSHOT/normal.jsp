<%@ page import="com.intelie.iem.DashboardResource" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet" %>

<portlet :defineObjects/>

<script src="javascript">
    _ResourceUrl = <%=renderResponse.createResourceURL()%>;
</script>

<h4>Welcome to the Maven 2 World!</h4>

<%
    DashboardResource dashboardResource = new DashboardResource();
%>

Dashboards teste:

<%=dashboardResource.printDashboad(1)%>

----
