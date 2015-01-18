function getDateDifference(){
		var fromDate = document.getElementById("fromdate").value;
		var toDate = document.getElementById("todate").value;

		/*var fromDateForYear = fromDate.split("-");
	 	var fromYear = fromDateForYear[0];
	 	var toDateForYear = toDate.split("-");
	 	var toYear = toDateForYear[0];
	 		//alert("fromDate-:"+fromDate+"----toDate:--"+toDate)
		 	if (toDate < fromDate){
				alert("To Date should not be less than From Date");
				document.forms[1].todate.focus();
				document.forms[1].todate.value= "";
				return false;
			}else
			if(fromYear != toYear){
		 		alert("You can't apply leaves for different year.Please select from date and to date for same year.");
		 		document.forms[1].leaveType.focus();
		 		document.forms[1].todate.value= "";
		 		return false;
		 	}else {*/
		 		totalWorkingDays = calculateWorkingdays(fromDate,toDate);
				document.forms[0].leaveAvailed.value= totalWorkingDays;
			//}
	}



			function calculateWorkingdays(startDate, endDate){
				var stDate = startDate.split("-");
				var enDate = endDate.split("-");
				ssDate = new Date(stDate[0],Number(stDate[1])-1,stDate[2]);
				eeDate = new Date(enDate[0],Number(enDate[1])-1,enDate[2]);
				var holidayExists="false";
				var isHolidayStr="";
				var holidayList='[26-01-2009,01-05-2009,14-08-2009,21-09-2009,28-09-2009,02-10-2009,19-10-2009,25-12-2009,27-03-2009,14-01-2010,26-01-2010,16-03-2010,02-04-2010,20-08-2010,01-09-2010,10-09-2010,15-10-2010,01-11-2010,05-11-2010,14-01-2011,26-01-2011,04-04-2011,15-08-2011,31-08-2011,01-09-2011,01-11-2011,27-10-2011,02-03-2011,26-12-2011,26-01-2012,23-03-2012,01-05-2012,15-08-2012,20-08-2012,19-09-2012,02-10-2012,01-11-2012,12-11-2012,25-12-2012,23-10-2012,14-01-2013,11-04-2013,01-05-2013,09-08-2013,15-08-2013,09-09-2013,02-10-2013,14-10-2013,01-11-2013,04-11-2013,25-12-2013,01-01-2014,14-01-2014,31-03-2014,01-05-2014,29-07-2014,15-08-2014,29-08-2014,02-10-2014,03-10-2014,23-10-2014,25-12-2014,17-04-2014]';
				var arrayHolidayList = holidayList.split(",");

				if (ssDate > eeDate){
					return -1;
				}
				workingDays=0;
				while (ssDate <= eeDate)
				{
					for(var j=0;j<arrayHolidayList.length;j++)
					{
						var lDate = arrayHolidayList[j].replace("[" , "").replace("]" , "").split("-");
						if( new Date(lDate[0],Number(lDate[1])-1,lDate[2]).toString()==ssDate )
						{
							isHolidayStr="true";
						}
					}
					if(ssDate.getDay()!=0 && ssDate.getDay()!=6  && isHolidayStr!="true" )
					{
						workingDays++;
					}
						ssDate=new Date(ssDate.valueOf()+86400000);
						
						isHolidayStr="false";
				}
				//alert("workingDays:"+workingDays);
				return workingDays;
		}
			
			
			
			function  setHalfDayImage() {
	  	    	var leaveTypeId = document.forms[0].leaveType.value;
	  	    	var div = document.getElementById('halfDayImage');
	  	    	var fromDate = document.getElementById("fromdate").value;
				var toDate = document.getElementById("todate").value;
				var totalWorkingDays = calculateWorkingdays(fromDate,toDate);

	  	    	if((leaveTypeId == '1' || leaveTypeId == '6' || leaveTypeId == '8') && totalWorkingDays == '1') {
		  	   		var innerHtml1 ="<input type='radio' name='selectDay'  onclick='addDay(1);'/> Half Day";
		  	   		innerHtml1 =innerHtml1+"<input type='radio' name='selectDay' checked='checked'  onclick='addDay(2);'/> Full Day";
		  	   		div.innerHTML = innerHtml1;
		  	   		div.style.visibility = 'visible'; 
		  	    }
		  	    else{
		  	    	div.style.visibility = 'hidden'; 
		  	    	return false;
		  	    }
	  	    }
			
			function setTodate(){
				
				document.forms[0].todate.value= "";
				document.forms[0].leaveAvailed.value="";
				return false;
			}