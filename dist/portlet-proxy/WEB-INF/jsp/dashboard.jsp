<%@ page session="false" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>

<portlet:defineObjects/>

<script type="text/javascript">
    ResourceURL = "<%=renderResponse.createResourceURL()%>";
    alert(ResourceURL);
</script>

<MAP NAME=MyMap>
    <AREA SHAPE=rect
          HREF="<portlet:actionURL><portlet:param name='continent' value='NorthAmerica'/></portlet:actionURL>"
          COORDS=0,0,45,35>
    <AREA SHAPE=rect
          HREF="<portlet:actionURL><portlet:param name='continent' value='SouthAmerica'/></portlet:actionURL>"
          COORDS=10,20,40,90>
    <AREA SHAPE=rect HREF="<portlet:actionURL><portlet:param name='continent' value='Africa'/></portlet:actionURL>"
          COORDS=50,45,85,90>
    <AREA SHAPE=rect HREF="<portlet:actionURL><portlet:param name='continent' value='Asia'/></portlet:actionURL>"
          COORDS=45,0,140,45>
    <AREA SHAPE=rect HREF="<portlet:actionURL><portlet:param name='continent' value='Australia'/></portlet:actionURL>"
          COORDS=110,50,140,197>
</MAP>

<IMG USEMAP=#MyMap SRC="<%=renderResponse.encodeURL(request.getContextPath()+"/World.jpg")%>" BORDER=1>
