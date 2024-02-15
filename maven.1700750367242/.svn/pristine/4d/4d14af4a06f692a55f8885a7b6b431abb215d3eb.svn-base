
function createPayment(rentMonthId, count) {
	var preference = $("#status" + count).val();
	if (preference == "") {
		bootbox.alert("Please Select Preference");
		return false;
	} else {
		$.ajax({
			type: "GET",
			url: "${contextPath}/tenant/createPayment",
			data: {
				"rentMonthId": rentMonthId,
				"status": preference,
			},
			success: function (response1) {
				var val = JSON.parse(response1);
				var options = {
					"key": val.test_key, // Enter the Key ID generated from the Dashboard
					"amount": val.amount, // Amount is in currency subunits. Default currency is INR. Hence, 50000 refers to 50000 paise
					"currency": "INR",
					"name": "Shop Rent Collection System",
					"description": "Brahmapur Municipal Corporation",
					"image": "https://www.berhampur.gov.in/wp-content/uploads/2020/08/Bemc-Logo-min.png",
					"order_id": val.order_id, //This is a sample Order ID. Pass the `id` obtained in the response of Step 1
					"handler": function (response) {
						$.ajax({
							type: "GET",
							url: "${contextPath}/tenant/afterThePayment",
							data: {
								"payment_id": response.razorpay_payment_id,
								"order_id": response.razorpay_order_id,
								"signature": response.razorpay_signature,
								"status": "SUCCESS",
								"preference": preference,
								"rentMonthId": rentMonthId,
							},
							success: function (response2) {
								var value = JSON.parse(response2);
								if (value.transactionId != "") {
									$("#transactionId").val(value.transactionId);
									$("#formId").submit();
								} else {
									bootbox.alert("Something went Wrong !!");
								}
							},
							error: function (error) {
								bootbox.alert("Something went Wrong !!");
							}
						});
					},
					"prefill": {
						"name": val.name,
						"email": "abc@example.com",
						"contact": "1111111111"
					},
					"notes": {
						"address": "Razorpay Corporate Office"
					},
					"theme": {
						"color": "#3399cc"
					}
				};
				var rzp1 = new Razorpay(options);
				rzp1.on('payment.failed', function (response) {
					$.ajax({
						type: "GET",
						url: "${contextPath}/tenant/afterThePayment",
						data: {
							"payment_id": response.error.metadata.payment_id,
							"order_id": response.error.metadata.payment_id,
							"reason": response.error.reason,
							"status": "FAILED",
							"rentMonthId": rentMonthId,
						},
						success: function (response1) {
							var value = JSON.parse(response2);
							if (value.transactionId != "") {
								$("#transactionId").val(value.transactionId);
								$("#formId").submit()
							} else {
								bootbox.alert("Something went Wrong !!");
							}
						},
						error: function (error) {
							bootbox.alert("Something went Wrong !!")
						}
					});
				});
				rzp1.open();
			},
			error: function (error) {
				//bootbox.alert("Failure"); 
			}
		});
	}
}

/*	<script>
						function createPayment(rentMonthId, count) {
							var preference = $("#status" + count).val();
							if (preference == "") {
								bootbox.alert("Please Select Preference");
								return false;
							} else {
								$.ajax({
									type: "GET",
									url: "${contextPath}/tenant/createPayment",
									data: {
										"rentMonthId": rentMonthId,
										"status": preference,
									},
									success: function (response1) {
										var val = JSON.parse(response1);
										var options = {
											"key": val.test_key, // Enter the Key ID generated from the Dashboard
											"amount": val.amount, // Amount is in currency subunits. Default currency is INR. Hence, 50000 refers to 50000 paise
											"currency": "INR",
											"name": "Shop Rent Collection System",
											"description": "Brahmapur Municipal Corporation",
											"image": "https://www.berhampur.gov.in/wp-content/uploads/2020/08/Bemc-Logo-min.png",
											"order_id": val.order_id, //This is a sample Order ID. Pass the `id` obtained in the response of Step 1
											"handler": function (response) {
												$.ajax({
													type: "GET",
													url: "${contextPath}/tenant/afterThePayment",
													data: {
														"payment_id": response.razorpay_payment_id,
														"order_id": response.razorpay_order_id,
														"signature": response.razorpay_signature,
														"status": "SUCCESS",
														"preference": preference,
														"rentMonthId": rentMonthId,
													},
													success: function (response2) {
														var value = JSON.parse(response2);
														if (value.transactionId != "") {
															$("#transactionId").val(value.transactionId);
															$("#formId").submit();
														} else {
															bootbox.alert("Something went Wrong !!");
														}
													},
													error: function (error) {
														bootbox.alert("Something went Wrong !!");
													}
												});
											},
											"prefill": {
												"name": val.name,
												"email": "abc@example.com",
												"contact": "1111111111"
											},
											"notes": {
												"address": "Razorpay Corporate Office"
											},
											"theme": {
												"color": "#3399cc"
											}
										};
										var rzp1 = new Razorpay(options);
										rzp1.on('payment.failed', function (response) {
											$.ajax({
												type: "GET",
												url: "${contextPath}/tenant/afterThePayment",
												data: {
													"payment_id": response.error.metadata.payment_id,
													"order_id": response.error.metadata.payment_id,
													"reason": response.error.reason,
													"status": "FAILED",
													"rentMonthId": rentMonthId,
												},
												success: function (response1) {
													var value = JSON.parse(response2);
													if (value.transactionId != "") {
														$("#transactionId").val(value.transactionId);
														$("#formId").submit()
													} else {
														bootbox.alert("Something went Wrong !!");
													}
												},
												error: function (error) {
													bootbox.alert("Something went Wrong !!")
												}
											});
										});
										rzp1.open();
									},
									error: function (error) {
										//bootbox.alert("Failure"); 
									}
								});
							}
						}
					</script>  */
			