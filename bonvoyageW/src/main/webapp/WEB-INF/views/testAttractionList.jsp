<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3>관광지 목록 나와라</h3>
<table class="table table-hover">
    <thead>
    <tr class="text-center">
        <th scope="col">글번호</th>
        <th scope="col">제목</th>
        <th scope="col">작성자</th>
        <th scope="col">조회수</th>
        <th scope="col">작성일</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="attraction" items="${attractions}">
        <tr class="text-center">
            <th scope="row">${attraction.title}</th>
<%--            <td class="text-start">--%>
<%--                <a--%>
<%--                        href="#"--%>
<%--                        class="article-title link-dark"--%>
<%--                        data-no="${attraction.articleNo}"--%>
<%--                        style="text-decoration: none"--%>
<%--                >--%>
<%--                        ${attraction.subject}--%>
<%--                </a>--%>
<%--            </td>--%>
<%--            <td>${attraction.userId}</td>--%>
<%--            <td>${attraction.hit}</td>--%>
<%--            <td>${attraction.registerTime}</td>--%>
        </tr>
    </c:forEach>
    </tbody>
</table>