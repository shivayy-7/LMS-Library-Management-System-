function getAgentWiseDueCollectionStatus(finYearId) {
  $.ajax({
    type: "GET",
    url: contextPath + "/dashboard/getAgentWiseDueCollectionStatus",
    data: {
      finYearId: finYearId,
    },
    cache: false,
    async: false,
    success: function (response) {
      // Themes begin
      am4core.useTheme(am4themes_animated);
      // Themes end

      // Create chart instance
      var chart = am4core.create("agentdueStatus", am4charts.XYChart);

      // Add data
      chart.data = [
        /*{
  "district": "Agent- 1",
  "Dues to be Collected": 25,
  "Collected Amount": 25,
  "Remaining Dues": 10
}, {
  "district": "Agent- 2",
  "Dues to be Collected": 26,
  "Collected Amount": 23,
  "Remaining Dues": 10
}, {
  "district": "Agent- 3",
  "Dues to be Collected": 28,
  "Collected Amount": 25,
  "Remaining Dues": 10
}, {
  "district": "Agent- 4",
  "Dues to be Collected": 22,
  "Collected Amount": 20,
  "Remaining Dues": 10
}, {
  "district": "Agent- 5",
  "Dues to be Collected": 27,
  "Collected Amount": 25,
  "Remaining Dues": 10
}, {
  "district": "Agent- 6",
  "Dues to be Collected": 32,
  "Collected Amount": 22,
  "Remaining Dues": 10
}, {
  "district": "Agent- 7",
  "Dues to be Collected": 28,
  "Collected Amount": 25,
  "Remaining Dues": 10
}, {
  "district": "Agent- 8",
  "Dues to be Collected": 20,
  "Collected Amount": 18,
  "Remaining Dues": 10
}, {
  "district": "Agent- 9",
  "Dues to be Collected": 23,
  "Collected Amount": 22,
  "Remaining Dues": 10
}, {
  "district": "Agent- 10",
  "Dues to be Collected": 32,
  "Collected Amount": 25,
  "Remaining Dues": 10
}, {
  "district": "Agent- 11",
  "Dues to be Collected": 26,
  "Collected Amount": 25,
  "Remaining Dues": 10
}, {
  "district": "Agent- 12",
  "Dues to be Collected": 35,
  "Collected Amount": 28,
  "Remaining Dues": 10
}, {
  "district": "Agent- 13",
  "Dues to be Collected": 27,
  "Collected Amount": 25,
  "Remaining Dues": 10
}, {
  "district": "Agent- 14",
  "Dues to be Collected": 32,
  "Collected Amount": 22,
  "Remaining Dues": 10
}, {
  "district": "Agent- 15",
  "Dues to be Collected": 28,
  "Collected Amount": 25,
  "Remaining Dues": 10
}, {
  "district": "Agent- 16",
  "Dues to be Collected": 20,
  "Collected Amount": 18,
  "Remaining Dues": 10
}, {
  "district": "Agent- 17",
  "Dues to be Collected": 23,
  "Collected Amount": 22,
  "Remaining Dues": 10
}, {
  "district": "Agent- 18",
  "Dues to be Collected": 26,
  "Collected Amount": 25,
  "Remaining Dues": 10
}, {
  "district": "Agent- 19",
  "Dues to be Collected": 35,
  "Collected Amount": 28,
  "Remaining Dues": 10
}, {
  "district": "Agent- 20",
  "Dues to be Collected": 27,
  "Collected Amount": 25,
  "Remaining Dues": 10
}*/
      ];

      if (response != null && response != "") {
        var data = JSON.parse(response);
      }

      $.each(data, function (idx, elem) {
        let agentWiseData = {};
        agentWiseData.district = elem.month;
        agentWiseData.Dues_to_be_Collected = elem.total_amt;
        agentWiseData.Collected_Amount = elem.paid_amt;
        agentWiseData.Remaining_Dues = elem.remain_amt;
        chart.data.push(agentWiseData);
      });

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

      // Configure axis label
      var label = categoryAxis.renderer.labels.template;
      label.truncate = true;
      label.maxWidth = 80;

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
      series2.fill = am4core.color("#f48e70");
      series2.stroke = am4core.color("#ce5a37");
      //series2.stacked = true;

      var series3 = chart.series.push(new am4charts.ColumnSeries());
      series3.columns.template.width = am4core.percent(50);
      series3.tooltipText = "Total Remaining Dues: [bold]{valueY}[/]";
      series3.name = "Remaining";
      series3.dataFields.categoryX = "district";
      series3.dataFields.valueY = "Remaining_Dues";
      series3.fill = am4core.color("blue");
      series3.stroke = am4core.color("blue");
      //series3.stacked = true;
      chart.cursor = new am4charts.XYCursor();
      chart.cursor.lineX.disabled = true;
      chart.cursor.lineY.disabled = true;
    },
    error: function (error) {
      bootbox.alert("Something went to wrong!");
    },
  });
}
