var IncomeGroupReportController = (function() {

	function IncomeGroupReportController(token) {
		this.csrfToken = token;
		this.schemeId = 0;
		this.payload = {};
	}

	IncomeGroupReportController.prototype.render = function(fromDate, toDate, comparePrevYear,
		schemeId, hierarchyLevel, hierarchyId, whereClause, reportingLevel) {
		const me = this;
		
		this.schemeId = schemeId;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.comparePrevYear = comparePrevYear;
		this.hierarchyLevel = hierarchyLevel;
		this.hierarchyId = hierarchyId;
		this.whereClause = whereClause;
		this.reportingLevel = reportingLevel;

		/* Create the payload */
		let payload = {};
		payload["fromDate"] = fromDate;
		payload["toDate"] = toDate;
		payload["comparePrevYear"] = comparePrevYear;
		payload["schemeId"] = schemeId;
		payload["hierarchyLevel"] = hierarchyLevel;
		payload["hierarchyId"] = hierarchyId;
		payload["whereClause"] = whereClause;
		payload["reportingLevel"] = reportingLevel;
		this.payload = payload;

		try
		{
			$("#pnlReport").fancytree("destroy");
		}
		catch(ex)
		{
			//Ignore;
		}

		$("#pnlReport").fancytree({
			source: {
				url: window.contextPath + "/reports/getReportData",
				data : me.payload,
				dataType: "json"
			},
			extensions: ["table"],
			postProcess : function(event, data)
			{
				if (data.response.outcome)
				{
					data.result = processDataTOP(data.response.data);
				}
			},
			
			renderColumns : function(event, data) {
              var node = data.node,
                $tdList = $(node.tr).find(">td");

              $tdList
                .eq(1)
                .text(node.data.levelCount);

              $tdList
                .eq(2)
                .text(node.data.income);

            },

			lazyLoad: function(event, data) {
				let node = data.node;
				switch (node.getLevel())
				{
					case 1 :
						me.payload.hierarchyLevel = "DISTRICT";
					 	break;
					case 2 :
						me.payload.hierarchyLevel = "BLOCK";
					 	break;
					case 3 :
						me.payload.hierarchyLevel = "GP";
					 	break;
					case 4 :
						me.payload.hierarchyLevel = "VILLAGE";
					 	break;
					case 5 :
						me.payload.hierarchyLevel = "SHG";
					 	break;
				}
				me.payload.hierarchyId = node.data.id;
				
				var promiseObj = new Promise(function(resolve, reject){
					$.ajax({
						url: window.contextPath + "/reports/getReportData",
						data : me.payload,
						dataType: "json",
						success :  function(json)
						{
							resolve(processDataCHILD(json.data, node));
						}
					});
				});
				
				data.result = promiseObj;
			},
			expand : function(event, data) {
		        console.log(data.node);

		    }
		});

	}


	function processDataTOP(json)
	{
		let keys = [];
		let global = [];
		$.each(json , (idx, elem) => {
			keys.push(elem.title);
		});
		let set = new Set(keys);
		keys = [...set.keys()];
		
		$.each(keys, (idx1, key) =>{
			let children = json.filter(e => e.title == key);
			let parent = {};
			parent.title = key;
			parent.income = 0;
			parent.levelCount = 0;
			parent.children = new Array(); 
			
			$.each(children, (idx2,elem2) => {
				let child = {};
				
				child.title = elem2.activityName;
				child.id = elem2.id;
				child.income = elem2.income;
				child.levelCount = elem2.levelCount;
				child.lazy = true;
				child.folder = true;
				
				parent.children.push(child);
				parent.folder = true;
				parent.income += child.income;
				parent.levelCount += child.levelCount;
			});
			global.push(parent);
		});
		
		return global;
	};
	
	function processDataCHILD(json , parentNode)
	{
		let children = [];
		$.each(json, (idx2, elem) => {
			let child = {};
			
			if (elem.activityName == parentNode.title && parentNode.getLevel() == 2 )
			{
				child.title = elem.levelName;
				child.id = elem.levelId;
				child.income = elem.income;
				child.levelCount = elem.levelCount;
				child.lazy = true;
				child.folder = true;
				
				children.push(child);
			}
			
			if (parentNode.getLevel() == 3 && elem.activityName == parentNode.parent.title )
			{
				child.title = elem.levelName;
				child.id = elem.levelId;
				child.income = elem.income;
				child.levelCount = elem.levelCount;
				child.lazy = true;
				child.folder = true;
				
				children.push(child);
			}
			
			if (parentNode.getLevel() == 4 && elem.activityName == parentNode.parent.parent.title )
			{
				child.title = elem.levelName;
				child.id = elem.levelId;
				child.income = elem.income;
				child.levelCount = elem.levelCount;
				child.lazy = true;
				child.folder = true;
				
				children.push(child);
			}
			
			if (parentNode.getLevel() == 5 && elem.activityName == parentNode.parent.parent.parent.title )
			{
				child.title = elem.levelName;
				child.id = elem.levelId;
				child.income = elem.income;
				child.levelCount = elem.levelCount;
				//child.lazy = true;
				//child.folder = true;
				
				children.push(child);
			}
			
		

		});
		
		return children;
	};
	
	return IncomeGroupReportController;
	
})();