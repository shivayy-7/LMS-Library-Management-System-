function findVacantvsOccupied(finYearId) {
  $.ajax({
    type: "GET",
    url: contextPath + "/dashboard/findVacantvsOccupied",
    data: {
      finYearId: finYearId,
    },
    cache: false,
    async: false,
    success: function (response) {
      // Themes begin
      am4core.useTheme(am4themes_animated);
      // Themes end

      var chart = am4core.create("vacantOccupied", am4charts.PieChart);
      chart.hiddenState.properties.opacity = 0; // this creates initial fade-in
      chart.radius = am4core.percent(85);
      chart.data = [
        /* {
    country: "Vacant",
    litres: 299
  },
  {
    country: "Occupied",
    litres: 1019
  }*/
      ];
      var obj = JSON.parse(response);
      let dueAndPaidStatus = {};
      dueAndPaidStatus.country = "Vacant";
      dueAndPaidStatus.litres = obj[0].vacant;
      chart.data.push(dueAndPaidStatus);

      let dueAndPaidStatus1 = {};
      dueAndPaidStatus1.country = "Occupied";
      dueAndPaidStatus1.litres = obj[0].accupied;
      chart.data.push(dueAndPaidStatus1);
      chart.innerRadius = 40;

      var series = chart.series.push(new am4charts.PieSeries3D());
      series.dataFields.value = "litres";
      series.dataFields.category = "country";
      series.labels.template.text = "{category}: {value}";

      series.labels.template.maxWidth = 90;
      series.labels.template.wrap = true;

      series.colors.list = [
        am4core.color("#fb5607"),
        am4core.color("#087d72"),
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
      chart.legend = new am4charts.Legend();
      chart.logo.disabled = true;
      chart.legend.labels.template.text = "{name}";
      chart.legend.valueLabels.template.text = "";
    },
    error: function (error) {
      bootbox.alert("Something went to wrong!");
    },
  });
}
