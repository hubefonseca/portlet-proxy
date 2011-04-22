<%@ page import="com.intelie.iem.DashboardResource" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<h4>Welcome to the Maven 2 World!</h4>

<%
    DashboardResource dashboardResource = new DashboardResource();
%>

Dashboards teste:

<%=dashboardResource.allDashboards()%>

----
