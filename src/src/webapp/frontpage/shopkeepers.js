function edit_row(no)
{
 document.getElementById("edit_button"+no).style.display="none";
 document.getElementById("save_button"+no).style.display="block";
	
 var name=document.getElementById("name_row"+no);
 var location=document.getElementById("location_row"+no);
 var contact=document.getElementById("contact_row"+no);
 var offers=document.getElementById("offers_row"+no);
		
 var name_data=name.innerHTML;
 var location_data=location.innerHTML;
 var contact_data=contact.innerHTML;
 var offers_data=offers.innerHTML;
	
 name.innerHTML="<input type='text' id='name_text"+no+"' value='"+name_data+"'>";
 location.innerHTML="<input type='text' id='location_text"+no+"' value='"+location_data+"'>";
 contact.innerHTML="<input type='text' id='contact_text"+no+"' value='"+contact_data+"'>";
 offers.innerHTML="<input type='text' id='offers_text"+no+"' value='"+offers_data+"'>";

}

function save_row(no)
{
 var name_val=document.getElementById("name_text"+no).value;
 var location_val=document.getElementById("location_text"+no).value;
 var contact_val=document.getElementById("contact_text"+no).value;
 var offers=document.getElementById("offers_row"+no);

 document.getElementById("name_row"+no).innerHTML=name_val;
 document.getElementById("location_row"+no).innerHTML=location_val;
 document.getElementById("contact_row"+no).innerHTML=contact_val;
 document.getElementById("offers_row"+no).innerHTML=offers_val;

 document.getElementById("edit_button"+no).style.display="block";
 document.getElementById("save_button"+no).style.display="none";
}

function delete_row(no)
{
 document.getElementById("row"+no+"").outerHTML="";
}
