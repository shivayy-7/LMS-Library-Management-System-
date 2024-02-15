<div class="container-fluid" style="margin-top: -100px; position: relative; z-index:99">
   <!--   <div class="row mt-2"> -->
   <!--     <div class="col-md-4"> -->
   <!--       <div class="cardbox"> -->
   <!--         <h5 id="">Total Books</h5> -->
   <!--         <h5 id="totalbbok"></h5> -->
   <!--       </div> -->
   <!--     </div> -->
   <!--     <div class="col-md-4" style="color: red"> -->
   <!--       <div class="cardbox"> -->
   <!--         <h5 id="">Total Books Issued</h5> -->
   <!--         <h5 id="totalbookissued"></h5> -->
   <!--       </div> -->
   <!--     </div> -->
   <!--     <div class="col-md-4"> -->
   <!--       <div class="cardbox"> -->
   <!--         <h5 id="">Total Books Available</h5> -->
   <!--         <h5 id="totalbookavliable"></h5> -->
   <!--       </div> -->
   <!--     </div> -->
   <!--   </div> -->
   <div class="row mt-4 mb-4">
      <div class="col-md-9">
	      <div class="row cardbox cardbox-leftdetails" style="height:170px; margin-left:2px">	       
	      		<div class="col-md-4" style="position:relative; z-index:99">
	      			<h3 style="font-size:22px; font-weight:700; line-height:24px; margin-top:25px">Total Visitors</h3>
	      			 <div class="data">
      					 <h5 id="totalvistors" style="font-size:24px; font-weight:800;     margin-bottom: 0;"></h5>       
					        <div class="range">
					            <div class="fill"></div>
				        </div>
				    </div>
	      		</div>
	      		<div class="col-md-8 cardrgt-details">
	      		     <div style="display:flex; justify-content: space-between; ">   <h5 id="">Total Card Generation</h5>
	      		      <h5 id="totalicardgen"></h5>
                    </div>
                     
                      <div style="display:flex; justify-content: space-between;"><h5 id="">Total Visitors Pending </h5>
                      <h5 id="totalcardpen"></h5></div>
                      
                        <div style="display:flex; justify-content: space-between;"><h5 id="">Total Visitors Rejected</h5>
                        <h5 id="totalcardrej"></h5></div>
	      		</div>	       
	      </div>         
      </div>
    
  
      <div class="col-md-3">
         <div class="cardbox" style="height:170px; background:#e5faff; margin: auto; text-align: center;">  
         	    <img src="${contextPath}/assets/image/bookicon-dashboard.png" class="img-fluid" style="margin-top: -17px;
    margin-bottom: 5px;">  
         	    <a href="${contextPath}/mst/add-book" style="background: #288ea5;
    text-decoration: none;
    color: #fff;
    font-size: 13px;
    padding: 5px 15px;">Add Book</a>
         
<!--               <h5 id="">Total Card Generation</h5> -->
<!--             <h5 id="totalicardgen"></h5> -->
         </div>
      </div>
   </div>
</div>
<div class="container-fluid mt-4 mb-4">
   <div class="row mt-2">
      <div class="col-md-5" style="position:relative">
         <div class="cardbox" style=" height: auto; position: absolute; left: 24px;
            right: 24px; bottom: 14px; background:#f2fdff; padding-bottom: 6px;">
            <div style="display:flex; justify-content: space-between;">
               <h6 id="">Total Books</h6>
               <h5 id="totalbbok"></h5>
            </div>
            <div style="display:flex; justify-content: space-between;">
               <h6 id="">Total Books Issued</h6>
               <h5 id="totalbookissued"></h5>
            </div>
            <div style="display:flex; justify-content: space-between;">
               <h6 id="">Total Books Available</h6>
               <h5 id="totalbookavliable"></h5>
            </div>
         </div>
         <img src="${contextPath}/assets/image/bookbg.jpg" class="img-fluid" style="border-radius:15px; height:295px">        
      </div>
      <div class="col-md-7">
         <div class="cardbox" style="height:auto">
            <h4 style="font-weight:600">Return Book Condition</h4>
            <div class="panel-body">
               <div id="condwisestatus" style="width: 100%; height: 214px; margin: 0px auto; font-size:14px;"></div>
            </div>
         </div>
      </div>
   </div>
</div>
<div class="container-fluid">
   <div class="row mt-2">
      <div class="col-md-12 mb-4">
         <div class="cardbox" style="height:auto">
            <h4 style="font-weight:600">Today Return List</h4>
            <div class="tab-content">
               <div class="tab-pane fade active show">
                  <div class="table-responsive mt-3">
                     <table class="table table-striped export-table">
                        <thead>
                           <tr>
                              <th>SL#</th>
                              <th>Name</th>
                              <th>Contact Number</th>
                              <th>Adress</th>
                              <th>Catagories</th>
                              <th>Sub-catagories</th>
                              <th>Book Name</th>
                           </tr>
                        </thead>
                        <tbody id="retuenlist"></tbody>
                     </table>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <div class="col-md-12 mb-4">
         <div class="cardbox" style="height:auto;">
            <h4 style="font-weight:600">Status</h4>
            <div id="catagoriesissue" style="width: 100%; height: 250px; margin: 0px auto; font-size:12px;"></div>
         </div>
      </div>
   </div>
</div>
<script src="assets/vendor/highcharts/highcharts.js"></script>
<script src="assets/vendor/highcharts/highcharts-3d.js"></script>
<script src="assets/vendor/highcharts/drilldown.js"></script>
<script src="assets/vendor/highcharts/variable-pie.js"></script>
<script src="assets/vendor/highcharts/exporting.js"></script>
<script src="assets/charts/core.js"></script>
<script src="assets/charts/charts.js"></script>
<script src="assets/charts/animated.js"></script>
<script src="assets/charts/condtionchat.js"></script>
<script src="assets/charts/catagorieswiseisuue.js"></script>
<script>
   $(function() {
     getDashboardCardData();
     getrcondtionchat();
     getCAtgorieswiseissue();
     changeTabData();
   });
   
   function getDashboardCardData() {
     debugger
     $.ajax({
       type: "GET",
       url: '${contextPath}/dashboard/getDashboardCardData',
       dataType: "json",
       cache: false,
       async: false,
       cache: false,
       async: false,
       success: function(response) {
         $("#totalvistors").text(response.totalvistors);
         $("#totalbookissued").text(response.totalbookissued);
         $("#totalbookavliable").text(response.totalbbok - response.totalbookissued);
         $("#totalbbok").text(response.totalbbok);
         $("#totalicardgen").text(response.totalicardgen);
         $("#totalcardrej").text(response.totalcardrej);
         $("#totalcardpen").text(response.totalvistors - (response.totalicardgen + response.totalcardrej));
       }
     });
   }
   
   function changeTabData(){
   $.ajax({
   url : '${contextPath}/dashboard/getreturnlist',
   method : 'GET',
   	cache : false,
   	async : false,
   success : function(response) {
   	if(response != null && response !=""){
               var data =JSON.parse(response);
               var html="";
   $.each(data, function(idx, elem) {
   var i=idx+1;
   html=html+'<tr><td>'+i+'</td><td><b>'+elem.name+'</b></td><td>'+elem.phoneno+'</td><td>'+elem.address+'</td><td>'+elem.catgoriesname+'</td><td>'+elem.subcatname+'</td><td>'+elem.booktitel+'</td></tr>';
   	});
   
   $("#retuenlist").empty().append(html);
   }
   	
   
   }
   });
   
   }
</script>