package com.project.hr.servlets;
import com.project.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.text.*;
public class PatientsView extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
int hospitalCode=0;
try
{
hospitalCode=Integer.parseInt(request.getParameter("hospitalNumber"));
}catch(NumberFormatException nfe)
{
sendBackView(response);
return;
}
HospitalDTO hospital=new HospitalDTO();
PatientDAO patientDAO=new PatientDAO();
PatientDTO patientDTO=new PatientDTO();
Set<PatientDTO> patients;
patients=patientDAO.getByHospitalCode(hospitalCode);
PrintWriter pw=response.getWriter();
response.setContentType("text/html");

pw.println("<!DOCTYPE html>");
pw.println("<html>");
pw.println("");
pw.println("<head>");
pw.println("    <title>Patient View</title>");
pw.println("    <meta name='viewport' content='width=device-width, initial-scale=1'>");
pw.println("    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>");
pw.println("");
pw.println("    <script>");
pw.println("        function Patient() {");
pw.println("            this.patientId = '';");
pw.println("            this.name = '';");
pw.println("            this.hospitalCode = 0;");
pw.println("            this.dateOfBooking = '';");
pw.println("            this.gender = '';");
pw.println("            this.isVaccinated = '';");
pw.println("            this.registrationAmount = '';");
pw.println("            this.disease = '';");
pw.println("            this.aadharCardNumber = '';");
pw.println("        }");
pw.println("        var selectedRow = null;");
pw.println("        var patients = [];");
int i=0;
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
for(PatientDTO patient:patients)
{
pw.println("var patient=new Patient();");
pw.println("patient.patientId=\""+patient.getPatientId()+"\";");
pw.println("patient.name=\""+patient.getName()+"\";");
pw.println("patient.hospitalCode=\""+patient.getHospitalCode()+"\";");
pw.println("patient.gender=\""+patient.getGender()+"\";");
pw.println("patient.isVaccinated="+patient.getIsVaccinated()+";");
pw.println("patient.dateOfBooking=\""+sdf.format(patient.getDateOfBooking())+"\";");
pw.println("patient.registrationAmount="+patient.getRegistrationAmount().toPlainString()+";");
pw.println("patient.disease=\""+patient.getDisease()+"\";");
pw.println("patient.aadharCardNumber=\""+patient.getAadharCardNumber()+"\";");
pw.println("patients["+i+"]=patient;");
i++;
}
pw.println("");
pw.println("");
pw.println("        function selectPatient(row, patientId) {");
pw.println("            if (selectedRow == row) return;");
pw.println("            if (selectedRow != null) {");
pw.println("                selectedRow.style.background = 'white';");
pw.println("                selectedRow.style.color = 'black';");
pw.println("            }");
pw.println("            row.style.background = '#7C7B7B';");
pw.println("            row.style.color = 'white';");
pw.println("            selectedRow = row;");
pw.println("            var i;");
pw.println("            for (i = 0; i < patients.length; i++) {");
pw.println("                if (patients[i].patientId == patientId) {");
pw.println("                    break;");
pw.println("                }");
pw.println("            }");
pw.println("            var emp = patients[i];");
pw.println("            document.getElementById('detailPanel_patientId').innerHTML = emp.patientId;");
pw.println("            document.getElementById('detailPanel_name').innerHTML = emp.name;");
pw.println("            document.getElementById('detailPanel_gender').innerHTML = emp.gender;");
pw.println("            if (emp.isVaccinated) {");
pw.println("                document.getElementById('detailPanel_isVaccinated').innerHTML = 'Yes';");
pw.println("            } else {");
pw.println("                document.getElementById('detailPanel_isVaccinated').innerHTML = 'No';");
pw.println("            }");
pw.println("            document.getElementById('detailPanel_dateOfBooking').innerHTML = emp.dateOfBooking;");
pw.println("            document.getElementById('detailPanel_registrationAmount').innerHTML = emp.registrationAmount;");
pw.println("            document.getElementById('detailPanel_disease').innerHTML = emp.disease;");
pw.println("            document.getElementById('detailPanel_aadharCardNumber').innerHTML = emp.aadharCardNumber;");
pw.println("        }");
pw.println("    </script>");
pw.println("    <style>");
pw.println("        .upper {");
pw.println("            color: white;");
pw.println("            margin-top: -35px;");
pw.println("            margin-left: 15vh;");
pw.println("            font-size: 25px;");
pw.println("            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;");
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
pw.println("            background-color: #19dddd;");
pw.println("            display: block;");
pw.println("            color: hsl(0, 0%, 4%);");
pw.println("            text-align: center;");
pw.println("            padding: 14px 16px;");
pw.println("            text-decoration: none;");
pw.println("        }");
pw.println("");
pw.println("li a:hover {");
pw.println("            background-color: white;");
pw.println("        }");
pw.println("        ");
pw.println("        .mail:hover {");
pw.println("            color: lawngreen;");
pw.println("        }");
pw.println("        ");
pw.println("        .table {");
pw.println("            border: 1px;");
pw.println("            padding: 20px;");
pw.println("            position: sticky;");
pw.println("            border: solid yellow;");
pw.println("            background-color: rgb(31, 152, 161);");
pw.println("            opacity: 0.8;");
pw.println("        }");
pw.println("        ");
pw.println("        .table2 {");
pw.println("            border: 1px;");
pw.println("            padding: 20px;");
pw.println("            position: sticky;");
pw.println("            border: solid yellow;");
pw.println("            background-color: rgb(13, 13, 14);");
pw.println("            opacity: 0.8;");
pw.println("        }");
pw.println("    </style>");
pw.println("</head>");
pw.println("");
pw.println("<body style='background-color: #050505;'>");
pw.println("    <div>");
pw.println("");
pw.println("        <img src='images/logo.png ' style='height: 30x; width: 30px; margin-left:80px ; position: static; '>");
pw.println("        <u><p class='upper '>ospital Update At Your Hands</p></u>");
pw.println("    </div>");
pw.println("    <hr style='margin-top: -10px; '>");
pw.println("    <div style='margin-top: -30px; '>");
pw.println("");
pw.println("        <img src='images/A-logo.png ' class='header '>");
pw.println("        <u><b><i> <p class='ujjain ' style='background-color: rgba(15, 15, 15, 0.986); width: 160px; '>ll About Ujjain</p></i></b></u>");
pw.println("    </div>");
pw.println("    <img src='images/login.jpg' style='height: 632px; width:1550px ;  '>");
pw.println("    <div style='margin-top: -710px; '>");
pw.println("");
pw.println("        <i><b><ul>");
pw.println("");
pw.println("    <li><a href='/minor/index.html#top' class='smoothScroll'>Home</a></li>");
pw.println("                    <li><a href='/minor/index.html#about' class='smoothScroll'>About Us</a></li>");
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
pw.println("");
pw.println("            <li><a href='/minor/index.html#enquiry '>Make an Enquiry</a></li>");
pw.println("");
pw.println("    </ul></b></i>");
pw.println("    </div>");
pw.println("");
pw.println("    <div>");
pw.println("        <lable style='background:gray;color: white;font-size: 50px; '>Patient</lable>");
pw.println("");
pw.println("        <table class='table'>");
pw.println("            <thead>");
pw.println("                <tr style='font-size: 25px;'>");
pw.println("                    <th style=' margin-left: 10px; color: red; '>S.No</th>");
pw.println("                    <th style='padding-left: 35px;color: red; '>Patient ID</th>");
pw.println("                    <th style='padding-left: 20px;color: red; '>Name</th>");
pw.println("                    <th style='padding-left: 20px;color: red; '>Delete</th>");
pw.println("");
pw.println("                </tr>");
pw.println("            </thead>");
pw.println("");
pw.println("            <tbody>");
pw.println("                </tr>");
pw.println("<tbody>");
int sno=0;
for(PatientDTO patient:patients)
{
sno++;
pw.println("<tr style='cursor:pointer' onclick='selectPatient(this,\""+patient.getPatientId()+"\")'>");
pw.println("<td style='text-align:right'>"+sno+".</td>");
pw.println("<td>"+patient.getPatientId()+"</td>");
pw.println("<td>"+patient.getName()+"</td>");
pw.println("<td style='text-align:center'><a href='/minor/confirmDeletePatient?patientId="+patient.getPatientId()+"'>Delete</a></td>");
pw.println("</tr>");
pw.println("</tr>");
}
pw.println("            </tbody>");
pw.println("        </table>");
pw.println("</div>");
pw.println("    <div style='height:20vh; margin-left:5px; margin-right:5px; margin-bottom:px; margin-top:5px; padding:5px; border:1px solid black;padding-top: 90px;'>");
pw.println("        <label style='background:gray;color:white;padding-left:5px;padding-right:5px;font-size: 50px ;'>Details</label>");
pw.println("        <table class='table2' border='0' width='100%'>");
pw.println("            <tr>");
pw.println("                <td style='padding-top: 10px;font-size: 22px;color: lawngreen;'>Patient Id :<span id='detailPanel_patientId' style='margin-right:100px;'></span></td>");
pw.println("                <td style='font-size: 22px;color: lawngreen;'>Name :<span id='detailPanel_name' style='margin-right:100px'></span></td>");
pw.println("            </tr>");
pw.println("            <tr>");
pw.println("                <td style='padding-top: 25px;font-size: 22px;color: lawngreen;'>Date Of Booking :<span id='detailPanel_dateOfBooking' style='margin-right:100px'></span></td>");
pw.println("                <td style='font-size: 22px;color: lawngreen;'> Gender :<span id='detailPanel_gender' style='margin-right:100px'></span></td>");
pw.println("");
pw.println("");
pw.println("                <td style='font-size: 22px;color: lawngreen;'>Is Vaccinated :<span id='detailPanel_isVaccinated' style='margin-right:100px'></span></td>");
pw.println("            </tr>");
pw.println("            <tr>");
pw.println("                <td style='padding-top: 25px;font-size: 22px;color: lawngreen;'>Registration Amount :<span id='detailPanel_registrationAmount' style='margin-right:100px'></span></td>");
pw.println("                <td style='font-size: 22px;color: lawngreen;'>Disease :<span id='detailPanel_disease' style='margin-right:100px'></span></td>");
pw.println("                <td style='font-size: 22px;color: lawngreen;'>Aadhar Card Number :<span id='detailPanel_aadharCardNumber' style='margin-right:100px'></span></td>");
pw.println("            </tr>");
pw.println("");
pw.println("    </div>");
pw.println("</body>");
pw.println("");
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
}
} // class patients view

