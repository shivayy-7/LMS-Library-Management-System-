function findBottom5AgentStatus(finYearId) {
  $.ajax({
    type: "GET",
    url: contextPath + "/dashboard/findBottom5AgentStatus",
    data: {
      finYearId: finYearId,
    },
    cache: false,
    async: false,
    success: function (response) {
      // Themes begin
      am4core.useTheme(am4themes_animated);
      // Themes end

      var chart = am4core.create("bottom5Agent", am4charts.XYChart);
      chart.padding(10, 10, 10, 10);

      var categoryAxis = chart.yAxes.push(new am4charts.CategoryAxis());
      categoryAxis.renderer.grid.template.location = 0;
      categoryAxis.dataFields.category = "Agent";
      categoryAxis.renderer.minGridDistance = 1;
      categoryAxis.renderer.inversed = false;
      categoryAxis.renderer.grid.template.disabled = true;

      var valueAxis = chart.xAxes.push(new am4charts.ValueAxis());
      valueAxis.min = 0;

      var series = chart.series.push(new am4charts.ColumnSeries());
      series.dataFields.categoryY = "Agent";
      series.dataFields.valueX = "Amount";
      series.tooltipText = "{valueX.value}";
      series.columns.template.strokeOpacity = 0;
      series.columns.template.column.cornerRadiusBottomRight = 5;
      series.columns.template.column.cornerRadiusTopRight = 5;

      var labelBullet = series.bullets.push(new am4charts.LabelBullet());
      labelBullet.label.horizontalCenter = "left";
      labelBullet.label.dx = 10;
      labelBullet.label.text = "{values.valueX.workingValue}";
      labelBullet.locationX = 1;

      // as by default columns of the same series are of the same color, we add adapter which takes colors from chart.colors color set
      series.columns.template.adapter.add("fill", function (fill, target) {
        return chart.colors.getIndex(target.dataItem.index);
      });

      categoryAxis.sortBySeries = series;
      chart.data = [
        /*  {
      "Agent": "Agent- 1",
      "Amount": 19550
    },
    {
      "Agent": "Agent- 2",
      "Amount": 33000
    },
    {
      "Agent": "Agent- 3",
      "Amount": 10000
    },
    {
      "Agent": "Agent- 4",
      "Amount": 34651
    },
    {
      "Agent": "Agent- 5",
      "Amount": 44602
    }*/
      ];
      if (response != null && response != "") {
        var data = JSON.parse(response);
      }

      $.each(data, function (idx, elem) {
        let agentWiseData = {};
        agentWiseData.Agent = elem.name;
        agentWiseData.Amount = elem.total_amt;
        chart.data.push(agentWiseData);
      });
    },
    error: function (error) {
      bootbox.alert("Something went to wrong!");
    },
  });
}
