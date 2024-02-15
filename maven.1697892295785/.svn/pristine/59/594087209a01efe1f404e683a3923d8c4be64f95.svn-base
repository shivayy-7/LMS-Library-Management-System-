// am4core.ready(function() {

function getrcondtionchat() {
	$.ajax({
		type : "GET",
		url : './dashboard/getDashboardCondtionData',
		cache : false,
		async : false,
		success : function(response) {

// Themes begin
am4core.useTheme(am4themes_animated);
// Themes end

var chart = am4core.create("condwisestatus", am4charts.PieChart3D);
chart.hiddenState.properties.opacity = 0; // this creates initial fade-in

chart.legend = new am4charts.Legend();

var colorSet = new am4core.ColorSet();
  colorSet.list = ["#057d8b", "#F44336","#1ea74f","#FFBF00","#8E24AA"].map(function(color) {
  return new am4core.color(color);
});

//chart.data = [
//  {
//    status: "Applied",
//   value: 501.9
// },
//  {
//    status: "Pending",
// value: 301.9
//  },
//  {
//   status: "Rejected",
//  value: 201.1
//  },
//  {
//   status: "Approved",
//   value: 165.8
//  }
//];

var series = chart.series.push(new am4charts.PieSeries3D());
series.dataFields.value = "value";
series.dataFields.category = "status";
series.colors = colorSet;


if(response != null && response !=""){
                 var data =JSON.parse(response);
			 // $.each(data, function(idx,elem){
			 
			         //let fundwiseData = {};
					//fundwiseData.status = 'totalApplied';
				//	fundwiseData.value = data.totalApplied;
					
					
					let fundwiseData2 = {};
					fundwiseData2.status = 'totalgood';
					fundwiseData2.value = data.totalgood;
					
					
					let fundwiseData3= {};
					fundwiseData3.status = 'totaldamged';
					fundwiseData3.value = data.totaldamaged;
					
					let fundwiseData4 = {};
					fundwiseData4.status = 'totallost';
					fundwiseData4.value = data.totallost;
					chart.data.push(fundwiseData2,fundwiseData3,fundwiseData4);
					
				
			//});
	}
},
error : function(error) {
	alert("Something went to wrong!");
},
});
}

// }); // end am4core.ready()