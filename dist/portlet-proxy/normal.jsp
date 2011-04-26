<%@ page import="javax.portlet.PortletURL,java.util.*" %>
<%@ page import="com.intelie.iem.DashboardResource" %>

<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:defineObjects/>


<%
    re

%>
<h4>Welcome to the Maven 2 World!</h4>

<%
    DashboardResource dashboardResource = new DashboardResource();
%>

Dashboards teste:

<%=dashboardResource.printDashboad(1)%>

----
