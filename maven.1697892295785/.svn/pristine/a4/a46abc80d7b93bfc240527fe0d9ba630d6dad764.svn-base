var TaskListPageController = (function(){
	
	function TaskListPageController(token)
	{
		this.csrfToken = token;
		addPageHandlers();
	}
	
	TaskListPageController.prototype.getActivityForScheme = function(schemeId)
	{
		const me = this;
		
		if (schemeId == "")
		{
			bindActivities([]);
		}
		else
		{
			$.ajax({
            url: window.ctxPath + '/task/lov/activity/' + schemeId ,
            type: 'get',
            success: function( data, textStatus, jQxhr ){
				if (data.outcome == true)
				{
                	bindActivities(data.data);
				}
				else
				{
					bootbox.alert(data.message);
				}
            },
            error: function( jqXhr, textStatus, errorThrown ){
                bootbox.alert("An error occured");
				console.log(errorThrown);
            }
        });
		}
		
	}
	
	TaskListPageController.prototype.search = function()
	{
		const me = this;
		const year = $('#year').val();
		if (year == "")
		{
			bootbox.alert("Please select the Year")
			return false;
		}
		else
		{
			$('#searchTask').submit();
		}
	}
	
	TaskListPageController.prototype.drillDown = function(code, year, month, scheme, activity){
		const status = $('#taskStatus').val();
		$('#wfStatus').val(status);
		$('#monthNav').val(month);
		$('#yearNav').val(year);
		$('#schemeNav').val(scheme);
		$('#activityNav').val(activity);
		$('#levelCodeNav').val(code);
		
		$('#frmNavigate').submit();
	}
	
	
	/* private methods */
	
	function bindActivities(data)
	{
		var ddlActivity = $('#activityId');
		
		$('#activityId option').remove();
		var option = '<option value="">--ALL--</option>';
		$(ddlActivity).append(option);
		
		$.each(data, (idx,elem) => {
			option = '<option value="'+ elem.activityId +'">'+ elem.activityNameEn +'</option>';
			$(ddlActivity).append(option);
		});
	}
	
	function addPageHandlers()
	{
		const me = this;
		
		$('#schemeId').change(() => {
			taskListController.getActivityForScheme($('#schemeId').val());
		});
		
		$('#btnGo').click(() => {
			taskListController.search();
		});
		
		$('.x-status').click((e) => {
			let itemStatus = $(e.target).attr('data-status');
			$('#taskStatus').val(itemStatus);
			taskListController.search();
		});
		
		$('.x-link').click((e) => {
			e.preventDefault();
			const elem = $(e.target).parent();
			
			const code = elem.attr('data-code');
			const year = elem.attr('data-year');
			const month = elem.attr('data-month');
			const scheme = elem.attr('data-scheme');
			const activity = elem.attr('data-activity');
			 
			taskListController.drillDown(code, year, month, scheme, activity);
		});
	}
	
	
	return TaskListPageController;
	
})();


