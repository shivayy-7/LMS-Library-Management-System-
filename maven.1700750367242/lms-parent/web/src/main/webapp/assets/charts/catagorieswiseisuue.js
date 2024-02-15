/*am4core.ready(function() {*/
function getCAtgorieswiseissue() {
	$.ajax({
		type : "GET",
		url : './dashboard/getcatagorieswiase',
		cache : false,
		async : false,
		success : function(response) {
// Themes begin
am4core.useTheme(am4themes_animated);
// Themes end

// Create chart instance
var chart = am4core.create("catagoriesissue", am4charts.XYChart);

// Add data
chart.data = [

];


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

series1.tooltipText = "TOTAL BOOK: [bold]{valueY}[/]";
series1.name = "Total Book";
series1.dataFields.categoryX = "district";
series1.dataFields.valueY = "totalbook";


var series2 = chart.series.push(new am4charts.ColumnSeries());
series2.columns.template.width = am4core.percent(50);
series2.tooltipText = "ISSUE BOOK: [bold]{valueY}[/]";
series2.name = "Issue Book";
series2.dataFields.categoryX = "district";
series2.dataFields.valueY = "issuebook";
//series2.stacked = true;


chart.cursor = new am4charts.XYCursor();
chart.cursor.lineX.disabled = true;
chart.cursor.lineY.disabled = true;
chart.logo.disabled=true;

if(response != null && response !=""){
                var data =JSON.parse(response);
			  $.each(data, function(idx, elem) {
				let fundwiseData = {};
				fundwiseData.district=elem.catagories;
				fundwiseData.totalbook=elem.totalbook;
				fundwiseData.issuebook=elem.issuebook;			

				chart.data.push(fundwiseData);
			});
	}
},
error : function(error) {
	alert("Something went to wrong!");
},
});
}
/*});*/