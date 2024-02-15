var WorkflowController = (function(){
	
	function WorkflowController()
	{
		this.initialHash = "";
		$('#btnApprove').click((e) => {sendForApproval();});
		setupHash();
		$('.x-back').click((e) => {window.location = window.contextPath + '/task/list' ;});
		
		$('#btnUpdate').click((e) => sendForUpdate());
	}
	
	function setupHash()
	{
		var hash = "";
		$('input.form-control').each((idx , elem) => {
			hash = hash + "#" + $(elem).val();
		});
		this.initialHash = hash;
	}
	
	function sendForUpdate()
	{
		var parsely = $('#activityForm').parsley();
		parsely.validate();
		var ok = parsely.isValid();
		
		if (ok)
		{
			bootbox.confirm({
			    message: "Do you wish to update the record?",
			    buttons: {
			        confirm: {
			            label: 'Yes',
			            className: 'btn-success'
			        },
			        cancel: {
			            label: 'No',
			            className: 'btn-warning'
			        }
			    },
			    callback: function (result) {
			        if (result)
					{
						$('#activityForm').submit();
					}

			    }
			});
		}
		else
		{
			bootbox.alert("Please correct all errors on the form.")
			return false;
		}
		
	}
	
	function sendForApproval()
	{
		var parsely = $('#activityForm').parsley();
		parsely.validate();
		var ok = parsely.isValid();
		
		if (ok)
		{
			var hash = "";
			$('input.form-control').each((idx , elem) => {
				hash = hash + "#" + $(elem).val();
			});
			if (this.initialHash != hash)
			{
				bootbox.confirm({
				    message: "You have made changes to the form. Do you wish to submit without saving the changes?",
				    buttons: {
				        confirm: {
				            label: 'Yes',
				            className: 'btn-success'
				        },
				        cancel: {
				            label: 'No',
				            className: 'btn-warning'
				        }
				    },
				    callback: function (result) {
				        if (result)
						{
							$('#frmApprove').submit();
						}

				    }
				});
			}
			else
			{
				bootbox.confirm({
				    message: "Do you wish to submit the record for approval?",
				    buttons: {
				        confirm: {
				            label: 'Yes',
				            className: 'btn-success'
				        },
				        cancel: {
				            label: 'No',
				            className: 'btn-warning'
				        }
				    },
				    callback: function (result) {
				        if (result)
						{
							$('#frmApprove').submit();
						}

				    }
				});
			}
		}
		else
		{
			bootbox.alert("Please correct all errors on the form.")
			return false;
		}
		
		
	}
		
	return WorkflowController;
	
})();