function getTotalIncome_Month(finYearId) {
  $.ajax({
    type: "GET",
    url: contextPath + "/dashboard/getTotalIncome_Month",
    data: {
      finYearId: finYearId,
    },
    cache: false,
    async: false,
    success: function (response) {
      // Themes begin
      am4core.useTheme(am4themes_animated);
      // Themes end

      var chart = am4core.create("totalIncome_Month", am4charts.XYChart);

      var data = [];

      chart.data = [
        /*{
  "month": "Apr",
  "income": 23.5,
  "lineColor": chart.colors.next()
}, {
  "month": "May",
  "income": 26.2,
  "lineColor": chart.colors.next()
}, {
  "month": "Jun",
  "income": 30.1,
  "lineColor": chart.colors.next()
}, {
  "month": "Jul",
  "income": 20.5,
  "lineColor": chart.colors.next()
}, {
  "month": "Aug",
  "income": 22,
  "expenses": 28.2,
  "lineColor": chart.colors.next()
}, {
  "month": "Sep",
  "income": 18,
  "lineColor": chart.colors.next()
}, {
  "month": "Oct",
  "income": 25,
  "lineColor": chart.colors.next()
}, {
  "month": "Nov",
  "income": 24,
  "expenses": 31.9,
  "lineColor": chart.colors.next()
}, {
  "month": "Dec",
  "income": 34.1,
  "lineColor": chart.colors.next()
}, {
  "month": "Jan",
  "income": 34.1,
  "lineColor": chart.colors.next()
}, {
  "month": "Feb",
  "income": 34.1,
  "lineColor": chart.colors.next()
}, {
  "month": "Mar",
  "income": 34.1,
  "lineColor": chart.colors.next()
}*/
      ];

      if (response != null && response != "") {
        var data = JSON.parse(response);
      }

      $.each(data, function (idx, elem) {
        let monthWiseData = {};
        monthWiseData.month = elem.name;
        monthWiseData.income = elem.total_amt;
        monthWiseData.lineColor = chart.colors.next();
        chart.data.push(monthWiseData);
      });

      var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
      categoryAxis.renderer.grid.template.location = 0;
      categoryAxis.renderer.ticks.template.disabled = true;
      categoryAxis.renderer.line.opacity = 0;
      categoryAxis.renderer.grid.template.disabled = true;
      categoryAxis.renderer.minGridDistance = 40;
      categoryAxis.renderer.labels.template.rotation = 270;
      categoryAxis.dataFields.category = "month";
      categoryAxis.startLocation = 0.4;
      categoryAxis.endLocation = 0.6;

      var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
      valueAxis.tooltip.disabled = true;
      valueAxis.renderer.line.opacity = 0;
      valueAxis.renderer.ticks.template.disabled = true;
      valueAxis.min = 0;

      var lineSeries = chart.series.push(new am4charts.LineSeries());
      lineSeries.dataFields.categoryX = "month";
      lineSeries.dataFields.valueY = "income";
      lineSeries.tooltipText = "Total Income: {valueY.value} lac";
      lineSeries.fillOpacity = 0.5;
      lineSeries.strokeWidth = 3;
      lineSeries.propertyFields.stroke = "lineColor";
      lineSeries.propertyFields.fill = "lineColor";

      // Cursor
      chart.cursor = new am4charts.XYCursor();
      chart.colors.list = [
        am4core.color("#5189cf"),
        am4core.color("#558ed5"),
        am4core.color("#8dcee0"),
        am4core.color("#93cddd"),
        am4core.color("#b7ce87"),
        am4core.color("#c3d69b"),
        am4core.color("#fbac6b"),
        am4core.color("#fac090"),
        am4core.color("#d1807e"),
        am4core.color("#d99694"),
        am4core.color("#3df7b8"),
        am4core.color("#6ff7c9"),
      ];

      var bullet = lineSeries.bullets.push(new am4charts.CircleBullet());
      bullet.circle.radius = 6;
      bullet.circle.fill = am4core.color("#fff");
      bullet.circle.strokeWidth = 3;
      // chart.logo.disabled=true;
      chart.cursor = new am4charts.XYCursor();
      chart.cursor.behavior = "panX";
      chart.cursor.lineX.opacity = 0;
      chart.cursor.lineY.opacity = 0;
    },
    error: function (error) {
      bootbox.alert("Something went to wrong!");
    },
  });
}
