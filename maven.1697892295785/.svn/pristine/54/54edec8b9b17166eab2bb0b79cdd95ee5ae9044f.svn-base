function findMonthlyDueCollectionStatus(finYearId) {
  $.ajax({
    type: "GET",
    url: contextPath + "/dashboard/findMonthlyDueCollectionStatus",
    data: {
      finYearId: finYearId,
    },
    cache: false,
    async: false,
    success: function (response) {
      am4core.useTheme(am4themes_animated);

      var chart = am4core.create("monthlydueStatus", am4charts.XYChart);

      chart.data = [
        /*{
  "district": "Apr",
  "Dues_to_be_Collected": 25,
  "Collected_Amount": 25,
  "Remaining_Dues": 10
}, {
  "district": "May",
  "Dues_to_be_Collected": 26,
  "Collected_Amount": 23,
  "Remaining_Dues": 10
}, {
  "district": "Jun",
  "Dues_to_be_Collected": 28,
  "Collected_Amount": 25,
  "Remaining_Dues": 10
}, {
  "district": "Jul",
  "Dues_to_be_Collected": 22,
  "Collected_Amount": 20,
  "Remaining_Dues": 10
}, {
  "district": "Aug",
  "Dues_to_be_Collected": 27,
  "Collected_Amount": 25,
  "Remaining_Dues": 10
}, {
  "district": "Sep",
  "Dues_to_be_Collected": 32,
  "Collected_Amount": 22,
  "Remaining_Dues": 10
}, {
  "district": "Oct",
  "Dues_to_be_Collected": 28,
  "Collected_Amount": 25,
  "Remaining_Dues": 10
}, {
  "district": "Nov",
  "Dues_to_be_Collected": 20,
  "Collected_Amount": 18,
  "Remaining_Dues": 10
}, {
  "district": "Dec",
  "Dues_to_be_Collected": 23,
  "Collected_Amount": 22,
  "Remaining_Dues": 10
}, {
  "district": "Jan",
  "Dues_to_be_Collected": 32,
  "Collected_Amount": 25,
  "Remaining_Dues": 10
}, {
  "district": "Feb",
  "Dues_to_be_Collected": 26,
  "Collected_Amount": 25,
  "Remaining_Dues": 10
}, {
  "district": "Mar",
  "Dues_to_be_Collected": 35,
  "Collected_Amount": 28,
  "Remaining_Dues": 10
}*/
      ];

      if (response != null && response != "") {
        var data = JSON.parse(response);
      }

      $.each(data, function (idx, elem) {
        let monthWiseData = {};
        monthWiseData.district = elem.month;
        monthWiseData.Dues_to_be_Collected = elem.total_amt;
        monthWiseData.Collected_Amount = elem.paid_amt;
        monthWiseData.Remaining_Dues = elem.remain_amt;
        chart.data.push(monthWiseData);
      });

      //chart.cursor = new am4charts.XYCursor();

      /* Create legend and enable default markers */
      chart.legend = new am4charts.Legend();
      chart.legend.useDefaultMarker = true;

      chart.colors.step = 2;

      var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
      categoryAxis.renderer.labels.template.horizontalCenter = "right";
      categoryAxis.renderer.labels.template.verticalCenter = "middle";
      categoryAxis.renderer.labels.template.rotation = 270;
      categoryAxis.dataFields.category = "district";
      categoryAxis.renderer.grid.template.location = 0;
      categoryAxis.renderer.minGridDistance = 10;
      categoryAxis.renderer.cellStartLocation = 0.1;
      categoryAxis.renderer.cellEndLocation = 0.6;

      var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
      valueAxis.min = 0;
      valueAxis.renderer.minWidth = 35;
      valueAxis.title.fontWeight = 600;

      var series1 = chart.series.push(new am4charts.ColumnSeries());
      series1.columns.template.width = am4core.percent(50);

      series1.tooltipText = "Total Dues to be Collected: [bold]{valueY}[/]";
      series1.name = "To be Collected";
      series1.dataFields.categoryX = "district";
      series1.dataFields.valueY = "Dues_to_be_Collected";
      series1.fill = am4core.color("green");
      series1.stroke = am4core.color("green");

      var series2 = chart.series.push(new am4charts.ColumnSeries());
      series2.columns.template.width = am4core.percent(50);
      series2.tooltipText = "Total Collected Amount: [bold]{valueY}[/]";
      series2.name = "Collected";
      series2.dataFields.categoryX = "district";
      series2.dataFields.valueY = "Collected_Amount";
      //series2.stacked = true;
      series2.fill = am4core.color("#f48e70");
      series2.stroke = am4core.color("#ce5a37");

      var series3 = chart.series.push(new am4charts.ColumnSeries());
      series3.columns.template.width = am4core.percent(50);
      series3.tooltipText = "Total Remaining Dues: [bold]{valueY}[/]";
      series3.name = "Remaining ";
      series3.dataFields.categoryX = "district";
      series3.dataFields.valueY = "Remaining_Dues";
      //series3.stacked = true;
      series3.fill = am4core.color("blue");
      series3.stroke = am4core.color("blue");
      chart.cursor = new am4charts.XYCursor();
      chart.cursor.lineX.disabled = true;
      chart.cursor.lineY.disabled = true;
      // chart.logo.disabled = true;
    },
    error: function (error) {
      bootbox.alert("Something went to wrong!");
    },
  });
}
