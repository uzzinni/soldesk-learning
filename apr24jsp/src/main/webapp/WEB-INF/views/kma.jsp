<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['날짜', '평균', '최저', '최고'],
          <c:forEach items="${list }" var="row">
          ['${row.tm}', ${row.avgTa }, ${row.minTa }, ${row.maxTa }],
          </c:forEach>
        ]);

        var options = {
          title: '서울 온도 변화',
          curveType: 'function',
          legend: { position: 'bottom' }
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

        chart.draw(data, options);
      }
    </script>
</head>
<body>
	<div id="curve_chart" style="width: 100%; height: 500px"></div>
	<table border="1">
		<thead>
		<tr>
			<th>번호</th>
			<th>날짜</th>
			<th>평균 온도</th>
			<th>최저 온도</th>
			<th>최고 온도</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${list }" var="row">
		<tr>
			<td>${row.kno }</td>
			<td>${row.tm }</td>
			<td>${row.avgTa }</td>
			<td>${row.minTa }</td>
			<td>${row.maxTa }</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>