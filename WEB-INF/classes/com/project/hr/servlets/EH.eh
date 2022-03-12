package com.project.hr.servlets;
import com.project.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;

public class EditHospital extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
int hospitalNumber=0;
try
{
hospitalNumber=Integer.parseInt(request.getParameter("hospitalNumber"));
}catch(NumberFormatException nfe)
{
// send back view
return;
}
HospitalDAO hospitalDAO=new HospitalDAO();
try
{
HospitalDTO hospital=hospitalDAO.getByHospitalNumber(hospitalNumber);
PrintWriter pw=response.getWriter();
response.setContentType("text/html");

pw.println("<!DOCTYPE HTML>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<title>Hospital Updates At Your Hands</title>");
pw.println("<script>");
pw.println("function validateForm(frm)");
pw.println("{");
pw.println("var firstInvalidComponent=null;");
pw.println("var valid=true;");
pw.println("");
pw.println("var hospitalName=frm.hospitalName.value.trim();");
pw.println("var hospitalNameErrorSection=document.getElementById('hospitalNameErrorSection');");
pw.println("hospitalNameErrorSection.innerHTML='';");
pw.println("if(hospitalName.length==0)");
pw.println("{");
pw.println("hospitalNameErrorSection.innerHTML='Hospital Name Required';");
pw.println("valid=false;");
pw.println("firstInvalidComponent=frm.hospitalName;");
pw.println("}");
pw.println("");
pw.println("var hospitalContact=frm.hospitalContact.value.trim();");
pw.println("var hospitalContactErrorSection=document.getElementById('hospitalContactErrorSection');");
pw.println("hospitalContactErrorSection.innerHTML='';");
pw.println("if(hospitalContact.length==0)");
pw.println("{");
pw.println("hospitalContactErrorSection.innerHTML='Hospital Contact Required';");
pw.println("valid=false;");
pw.println("firstInvalidComponent=frm.hospitalContact;");
pw.println("}");
pw.println("if(hospitalContact.length!=10)");
pw.println("{");
pw.println("hospitalContactErrorSection.innerHTML='Contact should be of 10 digits only';");
pw.println("valid=false;");
pw.println("firstInvalidComponent=frm.hospitalContact;");
pw.println("}");
pw.println("");
pw.println("");
pw.println("var totalBeds=frm.totalBeds.value.trim();");
pw.println("var totalBedsErrorSection=document.getElementById('totalBedsErrorSection');");
pw.println("totalBedsErrorSection.innerHTML='';");
pw.println("if(totalBeds.length==0)");
pw.println("{");
pw.println("totalBedsErrorSection.innerHTML='Total Beds Required';");
pw.println("valid=false;");
pw.println("firstInvalidComponent=frm.totalBeds;");
pw.println("}");
pw.println("");
pw.println("var bedsAvailable=frm.bedsAvailable.value.trim();");
pw.println("var bedsAvailableErrorSection=document.getElementById('bedsAvailableErrorSection');");
pw.println("bedsAvailableErrorSection.innerHTML='';");
pw.println("if(bedsAvailable.length==0)");
pw.println("{");
pw.println("bedsAvailableErrorSection.innerHTML='Beds Available Required';");
pw.println("valid=false;");
pw.println("firstInvalidComponent=frm.bedsAvailable;");
pw.println("}");
pw.println("if(bedsAvailable.length>totalBeds.length)");
pw.println("{");
pw.println("bedsAvailableErrorSection.innerHTML='Availability is more than total beds';");
pw.println("valid=false;");
pw.println("firstInvalidComponent=frm.bedsAvailable;");
pw.println("}");
pw.println("");
pw.println("var totalVentilators=frm.totalVentilators.value.trim();");
pw.println("var totalVentilatorsErrorSection=document.getElementById('totalVentilatorsErrorSection');");
pw.println("totalVentilatorsErrorSection.innerHTML='';");
pw.println("if(totalVentilators.length==0)");
pw.println("{");
pw.println("totalVentilatorsErrorSection.innerHTML='Total Ventilators Required';");
pw.println("valid=false;");
pw.println("firstInvalidComponent=frm.totalVentilators;");
pw.println("}");
pw.println("");
pw.println("var ventilatorsAvailable=frm.ventilatorsAvailable.value.trim();");
pw.println("var ventilatorsAvailableErrorSection=document.getElementById('ventilatorsAvailableErrorSection');");
pw.println("ventilatorsAvailableErrorSection.innerHTML='';");
pw.println("if(ventilatorsAvailable.length==0)");
pw.println("{");
pw.println("ventilatorsAvailableErrorSection.innerHTML='Ventilators Available Required';");
pw.println("valid=false;");
pw.println("firstInvalidComponent=frm.ventilatorsAvailable;");
pw.println("}");
pw.println("if(ventilatorsAvailable.length>totalVentilators.length)");
pw.println("{");
pw.println("ventilatorsAvailableErrorSection.innerHTML='Availability is more than total ventilators';");
pw.println("valid=false;");
pw.println("firstInvalidComponent=frm.ventilatorsAvailable;");
pw.println("}");
pw.println("");
pw.println("var totalICU=frm.totalICU.value.trim();");
pw.println("var totalICUErrorSection=document.getElementById('totalICUErrorSection');");
pw.println("totalICUErrorSection.innerHTML='';");
pw.println("if(totalICU.length==0)");
pw.println("{");
pw.println("totalICUErrorSection.innerHTML='Total ICU Required';");
pw.println("valid=false;");
pw.println("firstInvalidComponent=frm.totalICU;");
pw.println("}");
pw.println("");
pw.println("var icuAvailable=frm.icuAvailable.value.trim();");
pw.println("var icuAvailableErrorSection=document.getElementById('icuAvailableErrorSection');");
pw.println("icuAvailableErrorSection.innerHTML='';");
pw.println("if(icuAvailable.length==0)");
pw.println("{");
pw.println("icuAvailableErrorSection.innerHTML='ICU Available Required';");
pw.println("valid=false;");
pw.println("firstInvalidComponent=frm.icuAvailable;");
pw.println("}");
pw.println("if(icuAvailable.length>totalICU.length)");
pw.println("{");
pw.println("icuAvailableErrorSection.innerHTML='Availability is more than total ICUs';");
pw.println("valid=false;");
pw.println("firstInvalidComponent=frm.icuAvailable;");
pw.println("}");
pw.println("if(!valid) firstInvalidComponent.focus();");
pw.println("return valid;");
pw.println("}");
pw.println("function cancelUpdation()");
pw.println("{");
pw.println("document.getElementById('cancelUpdateForm').submit();");
pw.println("}");
pw.println("</script>");
pw.println("</head>");
pw.println("<body>");
pw.println("<!-- Main Container starts here  -->");
pw.println("<div style ='width:90hw;height:auto;border:1px solid black'>");
pw.println("<!-- Header starts here  -->");
pw.println("<div style ='margin:5px;width:90hw;height:auto;border:1px solid black'>");
pw.println("<img src='/minor/images/newicon.png' style='float:left'><div style='margin-top:9px;margin-bottom:9px;font-size:20pt'>Thinking Machines</div>");
pw.println("</div> <!--Header ends here  -->");
pw.println("<!-- Content Section starts here  -->");
pw.println("<div style ='width:90hw;height:70vh;margin:5px;border:1px solid white'>");
pw.println("<!-- Left Panel starts here  -->");
pw.println("<div style ='height:65vh;margin:5px;float:left;padding:5px;border:1px solid black'>");
pw.println("<b>Hosiptals</b><br><br>");
pw.println("<a href='/minor/patientsView'>Patients</a><br><br>");
pw.println("<a href='/minor/vaccinesView'>Book Vaccine</a><br><br>");
pw.println("<a href='/minor/contactUs'>Contact Us</a><br><br>");
pw.println("<a href='/minor/aboutUs'>AboutUs</a><br><br>");
pw.println("<a href='/minor/index.html'>Home</a><br><br>");
pw.println("</div>");
pw.println("<!-- Left Panel ends here  -->");
pw.println("<!-- Right Panel starts here  -->");
pw.println("<div style ='height:65vh;margin-left:105px;margin-right:5px;margin-bottom:px;margin-top:5px;padding:5px;border:1px solid black'>");
pw.println("<h1>Hospital (Edit Module)</h1>");
pw.println("<form method='post' action='/minor/updateHospital' onsubmit='return validateForm(this)'>");

pw.println("<input type='hidden' name='hospitalNumber' id='hospitalNumber' value='"+hospitalNumber+"'");


pw.println("<table>");
pw.println("<tr>");
pw.println("<td>Hospital Name</td>");
pw.println("<td>");

String hospitalName=hospital.getHospitalName();
pw.println("<input type='text' id='hospitalName' name='hospitalName' maxlength='100' size='36'<br>");
pw.println("<span id='hospitalNameErrorSection' style='color: red'></span></td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Total Beds</td>");
pw.println("<td>");
int totalBeds=hospital.getTotalBeds();
pw.println("<input type='number' id='totalBeds' name='totalBeds' maxLength='2' size='36'<br>");
pw.println("<span id='totalBedsErrorSection' style='color: red'></span></td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Beds Available</td>");
pw.println("<td>");
int bedsAvailable=hospital.getBedsAvailable();
pw.println("<input type='number' id='bedsAvailable' name='bedsAvailable' maxLength='2' size='36'<br>");
pw.println("<span id='bedsAvailableErrorSection' style='color: red'></span></td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Total Ventilators</td>");
pw.println("<td>");
int totalVentilators=hospital.getTotalVentilators();
pw.println("<input type='number' id='totalVentilators' name='totalVentilators' maxLength='2' size='36'<br>");
pw.println("<span id='totalVentilatorsErrorSection' style='color: red'></span></td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Ventilators Available</td>");
pw.println("<td>");
int ventilatorsAvailable=hospital.getVentilatorsAvailable();
pw.println("<input type='number' id='ventilatorsAvailable' name='ventilatorsAvailable' maxLength='2' size='36'<br>");
pw.println("<span id='ventilatorsAvailableErrorSection' style='color: red'></span></td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Total ICUs</td>");
pw.println("<td>");
int totalICU=hospital.getTotalICU();
pw.println("<input type='number' id='totalICU' name='totalICU' maxLength='2' size='36'<br>");
pw.println("<span id='totalICUErrorSection' style='color: red'></span></td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>ICUs Available</td>");
pw.println("<td>");
int icuAvailable=hospital.getICUAvailable();
pw.println("<input type='number' id='icuAvailable' name='icuAvailable' maxLength='2' size='36'<br>");
pw.println("<span id='icuAvailableErrorSection' style='color: red'></span></td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Hospital Contact</td>");
pw.println("<td>");
String hospitalContact=hospital.getHospitalContact();
pw.println("<input type='text' id='hospitalContact' name='hospitalContact' maxlength='10' size='36'<br>");
pw.println("<span id='hospitalContactErrorSection' style='color: red'></span></td>");
pw.println("</tr>");
pw.println("<td colspan='2'><button type='submit'>Update</button>");
pw.println("&nbsp;&nbsp;&nbsp;&nbsp;");
pw.println("<button type='button' onclick='cancelUpdation()'>Cancel</button></td>");
pw.println("</table>");
pw.println("</form>");
pw.println("</div>");
pw.println("<!-- Right Panel ends here  -->");
pw.println("</div> <!-- Content Section ends here  -->");
pw.println("<!--footer starts here  -->");
pw.println("<div style ='width:90hw;height:auto;margin:5px;text-align:center;border:1px solid white'>");
pw.println("&copy; Thinking Machines 2021");
pw.println("</div>");
pw.println("</div>");
pw.println("<form id='cancelUpdateForm' action='/minor/adminHospitalsView'>");
pw.println("</form>");
pw.println("</body>");
pw.println("</html>");
}catch(IOException ioException)
{
System.out.println(ioException.getMessage());
}catch(Exception e)
{
System.out.println(e);
}
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
doGet(request,response);
}
private void sendBackView(HttpServletResponse response)
{
// send back view page
}
} // class employees view
