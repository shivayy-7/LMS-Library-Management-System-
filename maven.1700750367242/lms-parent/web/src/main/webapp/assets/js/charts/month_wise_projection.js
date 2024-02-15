/**
 * --------------------------------------------------------
 * This demo was created using amCharts V4 preview release.
 *
 * V4 is the latest installement in amCharts data viz
 * library family, to be released in the first half of
 * 2018.
 *
 * For more information and documentation visit:
 * https://www.amcharts.com/docs/v4/
 * --------------------------------------------------------
 */

am4core.useTheme(am4themes_animated);
am4core.useTheme(am4themes_dataviz);

var chart = am4core.create("month_wise_projection", am4charts.XYChart);

chart.legend = new am4charts.Legend()
chart.legend.position = 'top'
chart.legend.paddingBottom = 20
chart.legend.labels.template.maxWidth = 95

chart.data = [
  {
    category: "Month1",
    value1: 1,
    value2: 5,
    value3: 3,
    value4: 3
  },
  {
    category: "Month2",
    value1: 2,
    value2: 5,
    value3: 3,
    value4: 4
  },
  {
    category: "Month3",
    value1: 3,
    value2: 5,
    value3: 4,
    value4: 4
  },
  {
    category: "Month4",
    value1: 4,
    value2: 5,
    value3: 6,
    value4: 4
  }
];

chart.colors.step = 2;

// Modify chart's colors
chart.colors.list = [
  am4core.color("#067b6a"),
  am4core.color("#eeb137"),
  am4core.color("#eeb137"),
  am4core.color("#99402b"),
  am4core.color("#64c6bd"),
  am4core.color("#64c6bd"),
];

var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
categoryAxis.dataFields.category = "category";
categoryAxis.renderer.grid.template.location = 0;
categoryAxis.renderer.line.strokeOpacity = 1;
categoryAxis.renderer.minGridDistance = 30;

categoryAxis.renderer.cellStartLocation = 0.3;
categoryAxis.renderer.cellEndLocation = 0.7;

var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());  

var series1 = chart.series.push(new am4charts.ColumnSeries());
series1.columns.template.width = am4core.percent(100);
series1.columns.template.tooltipText = "{name}: {valueY.value}";
series1.columns.template.strokeWidth = 0;
series1.name = "Input Value";
series1.dataFields.categoryX = "category";
series1.dataFields.valueY = "value1";

var series2 = chart.series.push(new am4charts.ColumnSeries());
series2.columns.template.width = am4core.percent(100);
series2.columns.template.tooltipText = "{name}: {valueY.value}";
series2.columns.template.strokeWidth = 0;
series2.name = "Output Value";
series2.dataFields.categoryX = "category";
series2.dataFields.valueY = "value2";