package com.project.hr.servlets;
import com.project.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
public class ConfirmDeletePatient extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
String patientId;
patientId=request.getParameter("patientId");
System.out.println(patientId);
PatientDAO patientDAO=new PatientDAO();
try
{
PatientDTO patientDTO=patientDAO.getByPatientId(patientId);
PrintWriter pw=response.getWriter();
response.setContentType("text/html");
pw.println("<!DOCTYPE html>");
pw.println("<html>");
pw.println("");
pw.println("<head>");
pw.println("    <title>Hospital Delete Module</title>");
pw.println("    <meta name='viewport' content='width=device-width, initial-scale=1'>");
pw.println("    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>");
pw.println("    <style>");
pw.println("        .upper {");
pw.println("            margin-top: -35px;");
pw.println("            margin-left: 15vh;");
pw.println("            font-size: 25px;");
pw.println("            font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;");
pw.println("        }");
pw.println("        ");
pw.println("        .ujjain {");
pw.println("            color: rgb(134, 134, 131);");
pw.println("            font-size: 25px;");
pw.println("            margin-left: 130px;");
pw.println("            margin-top: -40px;");
pw.println("            font-family: 'Times New Roman', Times, serif;");
pw.println("            position: -webkit-sticky;");
pw.println("            position: sticky;");
pw.println("            overflow: hidden;");
pw.println("            font-size: 25px;");
pw.println("        }");
pw.println("        ");
pw.println("        .header {");
pw.println("            height: 45px;");
pw.println("            width: 45px;");
pw.println("            margin-top: 50px;");
pw.println("            margin-left: 90px;");
pw.println("            position: -webkit-sticky;");
pw.println("            position: sticky;");
pw.println("            overflow: hidden;");
pw.println("        }");
pw.println("        ");
pw.println("        ul {");
pw.println("            list-style-type: none;");
pw.println("            margin-left: 70vh;");
pw.println("            padding: 0;");
pw.println("            overflow: hidden;");
pw.println("            position: -webkit-sticky;");
pw.println("            position: sticky;");
pw.println("            top: 0;");
pw.println("        }");
pw.println("        ");
pw.println("        li {");
pw.println("            float: left;");
pw.println("        }");
pw.println("        ");
pw.println("        li a {");
pw.println("            font-size: 19px;");
pw.println("            background-color: rgb(250, 252, 252);");
pw.println("            display: block;");
pw.println("            color: rgb(12, 12, 12);");
pw.println("            ;");
pw.println("            text-align: center;");
pw.println("            padding: 14px 16px;");
pw.println("            text-decoration: none;");
pw.println("        }");
pw.println("        ");
pw.println("        li a:hover {");
pw.println("            background-color: rgb(170, 173, 173);");
pw.println("        }");
pw.println("        ");
pw.println("        .mail:hover {");
pw.println("            color: lawngreen;");
pw.println("        }");
pw.println("    </style>");
pw.println("<script>");
pw.println("function cancelDeletion()");
pw.println("{");
pw.println("document.getElementById('cancelDeletionForm').submit();");
pw.println("}");
pw.println("</script>");
pw.println("</head>");
pw.println("");
pw.println("<body>");
pw.println("    <div>");
pw.println("");
pw.println("        <img src='images/logo.png' style='height: 30x; width: 30px; margin-left:80px ; position: static;'>");
pw.println("        <u><p class='upper'>ospital Update At Your Hands</p></u>");
pw.println("    </div>");
pw.println("    <hr style='margin-top: -10px;'>");
pw.println("");
pw.println("    <div style='margin-top: -30px;'>");
pw.println("");
pw.println("        <img src='images/A-logo.png ' class='header '>");
pw.println("        <u><b><i> <p class='ujjain' style='background-color: rgba(15, 15, 15, 0.986); width: 160px;'>ll About Ujjain</p></i></b></u>");
pw.println("    </div>");
pw.println("    <img src='images/add_hospital1.jpg' height='696px' width='1530px' style='margin-top: -65px; margin-left: -10px;'>");
pw.println("    <div style='margin-top: -710px; ; '>");
pw.println("");
pw.println("        <i><b><ul>");
pw.println("            ");
pw.println("");
pw.println("<li><a href='/minor/index.html' class='smoothScroll'>Home</a></li>\");");
pw.println("                    <li><a href='/minor/index.html#aboutUs' class='smoothScroll'>About Us</a></li>");
pw.println("                    <li>");
pw.println("                        <a href='/minor/hospitalsView' class='smoothScroll'>Hospitals</a>");
pw.println("                    </li>");
pw.println("                    <li>");
pw.println("                        <a href='/minor/PatientLogin.html' class='smoothscroll'>Patients</a>");
pw.println("                    </li>");
pw.println("                    <li><a href='/minor/index.html#contactUs' class='smoothScroll'>Contact Us</a></li>");
pw.println("                    <li>");
pw.println("                        <a href='https://selfregistration.cowin.gov.in/'>Book Vaccine</a>");
pw.println("                    </li>");
pw.println("            <li><a href='/minor/index.html#enquiry '>Make an Enquiry</a></li>");
pw.println("");
pw.println("</ul></b></i>");
pw.println("    </div>");
pw.println("    <table style='border: 1px;'>");
pw.println("        <div style='height:65vh ;margin-left:10vh; margin-right:5px; margin-bottom:px; margin-top:125px; padding:5px; border:1px solid black'>");
pw.println("            <h1 style='font-size: 50px; color: white;'>");
pw.println("                <span style='background-color: rgb(240, 15, 8) ;;'>Patient (Delete Module)</h1>");
pw.println("");
pw.println("<h1>Patient (Delete Module)</h1>");
pw.println("<form method='post' action='/minor/deletePatient'>");
pw.println("<input type='hidden' id='patientId' name='patientId' value='"+patientId+"'>");
int hospitalCode=patientDTO.getHospitalCode();
HospitalDAO hospitalDAO=new HospitalDAO();
HospitalDTO hospitalDTO=hospitalDAO.getByHospitalNumber(hospitalCode);
String hospitalName=hospitalDTO.getHospitalName();
pw.println("Name :");
pw.println( "<b>"+patientDTO.getName()+"</b><br>");
pw.println("Hospital :");
pw.println( "<b>"+hospitalName+"</b><br>");
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
String stringDateOfBooking=sdf.format(patientDTO.getDateOfBooking());
pw.println("Date Of Booking :");
pw.println( "<b>"+stringDateOfBooking+"</b><br>");
pw.println("Gender :");
if(patientDTO.getGender().equals("M"))
{
pw.println("<b>Male</b><br>");
}
else
{
pw.println("<b>Female</b><br>");
}
pw.println("Vaccination :");
if(patientDTO.getIsVaccinated())
{
pw.println( "<b>Vaccinated</b><br>");
}
else
{
pw.println( "<b>Not Vaccinated</b><br>");
}
pw.println("Registration Amount :");
pw.println( "<b>"+patientDTO.getRegistrationAmount().toPlainString()+"</b><br>");
pw.println("Disease :");
pw.println( "<b>"+patientDTO.getDisease()+"</b><br>");
pw.println("Aadhar Card Number :");
pw.println( "<b>"+patientDTO.getAadharCardNumber()+"</b><br>");
pw.println("<button type='submit' style='background-color: lawngreen; padding: 7px; margin-left: 25vh;'>Yes</button> &nbsp;&nbsp;&nbsp;&nbsp;");
pw.println("<button type='button' onclick='cancelDeletion()'style='background-color: lawngreen; padding:7px;'>No</button>");
pw.println("</form>");
pw.println("");
pw.println("    </div>");
pw.println("</table>");
pw.println("<form id='cancelDeletionForm' action='/minor/hospitalPatientsView'>");
pw.println("</form>");
pw.println("</body>");
pw.println("");
pw.println("</html>");
}catch(DAOException dao)
{
System.out.println(dao.getMessage());
// send Back
}
} catch(Exception exception)
{
System.out.println(exception.getMessage());
}
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
doGet(request,response);
}

}