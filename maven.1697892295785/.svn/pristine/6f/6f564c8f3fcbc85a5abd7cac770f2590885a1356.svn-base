function paymentComparisionYearWise(finYearId) {
  $.ajax({
    type: "GET",
    url: contextPath + "/dashboard/paymentComparisionYearWise",
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
      var chart = am4core.create("chartPC_Year", am4charts.XYChart);

      // Add data
      chart.data = [
        /*{
  "year": "Apr",
  "Online": 1,
  "Offline": 5
}, {
  "year": "May",
  "Online": 1,
  "Offline": 4
}, {
  "year": "Jun",
  "Online": 2,
  "Offline": 3
}, {
  "year": "Jul",
  "Online": 3,
  "Offline": 6
}, {
  "year": "Aug",
  "Online": 3,
  "Offline": 6
}, {
  "year": "Sep",
  "Online": 3,
  "Offline": 6
}, {
  "year": "Oct",
  "Online": 3,
  "Offline": 6
}, {
  "year": "Nov",
  "Online": 3,
  "Offline": 6
}, {
  "year": "Jan",
  "Online": 3,
  "Offline": 6
}, {
  "year": "Feb",
  "Online": 3,
  "Offline": 6
}, {
  "year": "Mar",
  "Online": 3,
  "Offline": 6
}*/
      ];

      if (response != null && response != "") {
        var data = JSON.parse(response);
      }

      $.each(data, function (idx, elem) {
        let monthWiseData = {};
        monthWiseData.year = elem.name;
        monthWiseData.Online = elem.online_amt;
        monthWiseData.Offline = elem.offline_amt;
        chart.data.push(monthWiseData);
      });

      // Create category axis
      var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
      categoryAxis.dataFields.category = "year";
      categoryAxis.renderer.opposite = false;
      categoryAxis.renderer.grid.template.location = 0;
      categoryAxis.renderer.labels.template.rotation = 270;
      categoryAxis.renderer.minGridDistance = 10;
      categoryAxis.renderer.cellStartLocation = 0.1;
      categoryAxis.renderer.cellEndLocation = 0.6;

      // Create value axis
      var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
      valueAxis.renderer.inversed = false;
      valueAxis.renderer.minLabelPosition = 0.01;
      valueAxis.title.text = "Amount (lac)";
      // Create series
      var series1 = chart.series.push(new am4charts.LineSeries());
      series1.dataFields.valueY = "Online";
      series1.dataFields.categoryX = "year";
      series1.name = "Online";
      series1.bullets.push(new am4charts.CircleBullet());
      series1.tooltipText = "{name} Payment in {categoryX} : {valueY} lac";
      series1.legendSettings.valueText = "{valueY}";
      series1.visible = false;
      series1.stroke = am4core.color("#ffa500");
      series1.fill = am4core.color("#ffa500");

      var series2 = chart.series.push(new am4charts.LineSeries());
      series2.dataFields.valueY = "Offline";
      series2.dataFields.categoryX = "year";
      series2.name = "Offline";
      series2.bullets.push(new am4charts.CircleBullet());
      series2.tooltipText = "{name} Payment in {categoryX} : {valueY} lac";
      series2.legendSettings.valueText = "{valueY}";
      series2.stroke = am4core.color("#fb5607");
      series2.fill = am4core.color("#fb5607");

      // Add chart cursor
      chart.cursor = new am4charts.XYCursor();
      chart.cursor.behavior = "zoomY";
      // chart.logo.disabled=true;

      let hs1 = series1.segments.template.states.create("hover");
      hs1.properties.strokeWidth = 5;
      series1.segments.template.strokeWidth = 1;

      let hs2 = series2.segments.template.states.create("hover");
      hs2.properties.strokeWidth = 5;
      series2.segments.template.strokeWidth = 1;

      // Add legend
      chart.legend = new am4charts.Legend();
      chart.legend.itemContainers.template.events.on("over", function (event) {
        var segments = event.target.dataItem.dataContext.segments;
        segments.each(function (segment) {
          segment.isHover = true;
        });
      });

      chart.legend.itemContainers.template.events.on("out", function (event) {
        var segments = event.target.dataItem.dataContext.segments;
        segments.each(function (segment) {
          segment.isHover = false;
        });
      });
    },
    error: function (error) {
      bootbox.alert("Something went to wrong!");
    },
  });
}
