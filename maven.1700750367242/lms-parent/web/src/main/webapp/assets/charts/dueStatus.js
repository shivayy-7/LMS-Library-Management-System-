function findDueAndPaidStatus(finYearId) {
  $.ajax({
    type: "GET",
    url: contextPath + "/dashboard/findDueAndPaidStatus",
    data: {
      finYearId: finYearId,
    },
    cache: false,
    async: false,
    success: function (response) {
      // Themes begin
      am4core.useTheme(am4themes_animated);
      // Themes end

      var chart = am4core.create("dueStatus", am4charts.PieChart);
      chart.hiddenState.properties.opacity = 0; // this creates initial fade-in
      chart.radius = am4core.percent(85);
      chart.data = [
        /* {
    country: "Paid",
    litres: 3019
  }*/
      ];
      var obj = JSON.parse(response);
      let dueAndPaidStatus = {};
      dueAndPaidStatus.country = "Pending";
      dueAndPaidStatus.litres = obj[0].pending;
      chart.data.push(dueAndPaidStatus);
      $("#totalPending").text(obj[0].pending);
      let dueAndPaidStatus1 = {};
      dueAndPaidStatus1.country = "Paid";
      dueAndPaidStatus1.litres = obj[0].paid;
      chart.data.push(dueAndPaidStatus1);

      chart.innerRadius = 40;

      var series = chart.series.push(new am4charts.PieSeries3D());
      series.dataFields.value = "litres";
      series.dataFields.category = "country";
      series.labels.template.text = "{category}: {value}";

      series.labels.template.maxWidth = 90;
      series.labels.template.wrap = true;

      series.colors.list = [
        am4core.color("#ffa500"),
        am4core.color("#23b100"),
        am4core.color("#009688"),
        am4core.color("#2bb907"),
        am4core.color("#3cb44b"),
        am4core.color("#42d4f4"),
        am4core.color("#4363d8"),
        am4core.color("#911eb4"),
        am4core.color("#f032e6"),
      ];
      series.labels.template.disabled = true;
      // chart.logo.disabled=true;
      chart.legend = new am4charts.Legend();
      //legend.valueLabels.template.disabled = true;
      chart.legend.labels.template.text = "{name}";
      chart.legend.valueLabels.template.text = "";
      // chart.legend.useDefaultMarker = true;
      // var marker = chart.legend.markers.template.children.getIndex(0);
      // marker.cornerRadius(12, 12, 12, 12);
      // markerTemplate.width = 10;
      // markerTemplate.height = 10;
      // marker.strokeWidth = 1;
      // marker.strokeOpacity = 1;
      // marker.stroke = am4core.color("#ccc");
    },
    error: function (error) {
      bootbox.alert("Something went to wrong!");
    },
  });
}
