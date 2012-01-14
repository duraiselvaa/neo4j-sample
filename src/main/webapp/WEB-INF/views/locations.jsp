<%@ page session="false" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
  <c:when test="${not empty locations}">
      <c:forEach items="${locations}" var="location">
        <li>
          <div>
          	<p><c:out value="${location.id}" /></p>
          	<p><c:out value="${location.latitude}" /></p>
          	<p><c:out value="${location.longitude}" /></p>
          </div>
        </li>
      </c:forEach>
  </c:when>
  <c:otherwise>
    <h2>No locations found</h2>
  </c:otherwise>
</c:choose>