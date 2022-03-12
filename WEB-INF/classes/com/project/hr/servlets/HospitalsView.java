package com.project.hr.servlets;
import com.project.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
public class HospitalsView extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
HospitalDAO hospitalDAO;
hospitalDAO=new HospitalDAO();
List<HospitalDTO> hospitals;
hospitals=hospitalDAO.getAll();
PrintWriter pw=response.getWriter();
response.setContentType("text/html");
pw.println("<!DOCTYPE html>");
pw.println("<html>");
pw.println("");
pw.println("<head>");
pw.println("    <title>Hospitals List</title>");
pw.println("    <meta name='viewport' content='width=device-width, initial-scale=1'>");
pw.println("    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>");
pw.println("    <style>");
pw.println("        th {");
pw.println("            border: 1px solid black;");
pw.println("            padding: 10px;");
pw.println("        }");
pw.println("        ");
pw.println("        .upper {");
pw.println("            margin-top: -35px;");
pw.println("            margin-left: 15vh;");
pw.println("            font-size: 25px;");
pw.println("            font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;");
pw.println("        }");
pw.println("        ");
pw.println("        .email-icon a {");
pw.println("            text-decoration: none;");
pw.println("            color: black;");
pw.println("        }");
pw.println("        ");
pw.println("        .email-icon a:hover {");
pw.println("            color: green;");
pw.println("        }");
pw.println("        ");
pw.println("        .header {");
pw.println("            height: 35px;");
pw.println("            width: 35px;");
pw.println("            margin-top: 60px;");
pw.println("            margin-left: 90px;");
pw.println("            position: -webkit-sticky;");
pw.println("            position: sticky;");
pw.println("            overflow: hidden;");
pw.println("        }");
pw.println("        ");
pw.println("        .ujjain {");
pw.println("            font-size: 20px;");
pw.println("            margin-left: 120px;");
pw.println("            margin-top: -32px;");
pw.println("            font-family: Arial, Helvetica, sans-serif;");
pw.println("            position: -webkit-sticky;");
pw.println("            position: sticky;");
pw.println("            overflow: hidden;");
pw.println("        }");
pw.println("        ");
pw.println("        .table {");
pw.println("            margin-left: 0px;");
pw.println("        }");
pw.println("        ");
pw.println("        #customers {");
pw.println("            font-family: Arial, Helvetica, sans-serif;");
pw.println("            border-collapse: collapse;");
pw.println("            width: 100%;");
pw.println("        }");
pw.println("        ");
pw.println("        #customers td,");
pw.println("        #customers th {");
pw.println("            border: 1px solid #ddd;");
pw.println("            padding: 8px;");
pw.println("        }");
pw.println("        ");
pw.println("        #customers tr:nth-child(even) {");
pw.println("            background-color: #f2f2f2;");
pw.println("        }");
pw.println("        ");
pw.println("        #customers tr:hover {");
pw.println("            background-color: #ddd;");
pw.println("        }");
pw.println("        ");
pw.println("        #customers th {");
pw.println("            padding-top: 12px;");
pw.println("            padding-bottom: 12px;");
pw.println("            text-align: left;");
pw.println("            background-color: #04AA6D;");
pw.println("            color: white;");
pw.println("        }");
pw.println("        ");
pw.println("        ul {");
pw.println("            list-style-type: none;");
pw.println("            margin-left: 80vh;");
pw.println("            padding: 0;");
pw.println("            overflow: hidden;");
pw.println("            background-color: white;");
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
pw.println("            display: block;");
pw.println("            color: black;");
pw.println("            text-align: center;");
pw.println("            padding: 14px 16px;");
pw.println("            text-decoration: none;");
pw.println("        }");
pw.println("        ");
pw.println("        li a:hover {");
pw.println("            background-color: rgb(168, 166, 162);");
pw.println("        }");
pw.println("    </style>");
pw.println("</head>");
pw.println("");
pw.println("<body>");
pw.println("    <div>");
pw.println("");
pw.println("        <img src='/minor/images/logo.png' style='height: 30x; width: 30px; margin-left:70px ; position: static;'>");
pw.println("        <p class='upper'>ospital Update At Your Hands</p>");
pw.println("    <div>");
pw.println("        <img src='/minor/images/A-logo.png ' class='header '>");
pw.println("        <p class='ujjain '>ll About Ujjain</p>");
pw.println("</div>");
pw.println("    <div style='margin-top: -55px; '>");
pw.println("        <ul>");
pw.println("");
pw.println("                    <li><a href='/minor/index.html' class='smoothScroll'>Home</a></li>");
pw.println("                    <li><a href='/minor/index.html#aboutUs' class='smoothScroll'>About Us</a></li>");
pw.println("                    <li>");
pw.println("                        <a href='/minor/hospitalsView' class='smoothScroll'>Hospitals</a>");
pw.println("                    </li>");
pw.println("                    <li>");
pw.println("                        <a href='/minor/patientsView' class='smoothscroll'>Patients</a>");
pw.println("                    </li>");
pw.println("                    <li><a href='/minor/index.html#contactUs' class='smoothScroll'>Contact Us</a></li>");
pw.println("                    <li>");
pw.println("                        <a href='https://selfregistration.cowin.gov.in/'>Book Vaccine</a>");
pw.println("                    </li>");
pw.println("");
pw.println("            <li><a href='/minor/index.html#enquiry '>Make an Enquiry</a></li>");
pw.println("");
pw.println("        </ul>");
pw.println("    </div>");
pw.println("    <div>");
pw.println("        <table id='customers' style='margin-top: -770px;' class='table'>");
pw.println("            <img src='/minor/images/hospital-background4.jpg ' style='width: 1500px; '>");
pw.println("");
pw.println("            <tr class='table'>");
pw.println("                <th>S.No.</th>");
pw.println("                <th style='text-align:right'>Hospital</th>");
pw.println("                <th>Total Beds</th>");
pw.println("                <th>Beds Available</th>");
pw.println("                <th>Total Ventilators</th>");
pw.println("                <th>ventilators Available</th>");
pw.println("                <th>ICU Wards</th>");
pw.println("                <th>ICU Available</th>");
pw.println("                <th>Contact</th>");
pw.println("                <th>Book</th>");
pw.println("                <th>Waiting</th>");
pw.println("            </tr>");
int x;
int hospitalNumber;
String hospitalName;
int sno=0;
int totalBeds;
int bedsAvailable;
int totalVentilators;
int ventilatorsAvailable;
int totalICU;
int icuAvailable;
String hospitalContact;
HospitalDTO hospitalDTO;
for(x=0;x<hospitals.size();x++)
{
sno++;
hospitalDTO=hospitals.get(x);
hospitalNumber=hospitalDTO.getHospitalNumber();
hospitalName=hospitalDTO.getHospitalName();
totalBeds=hospitalDTO.getTotalBeds();
bedsAvailable=hospitalDTO.getBedsAvailable();
totalVentilators=hospitalDTO.getTotalVentilators();
ventilatorsAvailable=hospitalDTO.getVentilatorsAvailable();
totalICU=hospitalDTO.getTotalICU();
icuAvailable=hospitalDTO.getICUAvailable();
hospitalContact=hospitalDTO.getHospitalContact();
pw.println("<tr>");
pw.println("<th>"+sno+"</th>");
pw.println("<th style='text-align:right;color:black'>"+hospitalName+"</th>");
pw.println("<th style='text-align:right;color:black'>"+totalBeds+"</th>");
pw.println("<th style='text-align:right;color:black'>"+bedsAvailable+"</th>");
pw.println("<th style='text-align:right;color:black'>"+totalVentilators+"</th>");
pw.println("<th style='text-align:right;color:black'>"+ventilatorsAvailable+"</th>");
pw.println("<th style='text-align:right;color:black'>"+totalICU+"</th>");
pw.println("<th style='text-align:right;color:black'>"+icuAvailable+"</th>");
pw.println("<th style='text-align:right;color:black'>"+hospitalContact+"</th>");
if(bedsAvailable==0 && ventilatorsAvailable==0 && icuAvailable==0)
{
pw.println("<td style='text-align:center'><b>Book</b></td>");
}
else
{
pw.println("<td style='text-align:center'><a href='/minor/getPatientAddForm'>Book</a></td>");
}
if(bedsAvailable==0 && ventilatorsAvailable==0 && icuAvailable==0)
{
pw.println("<td style='text-align:center'><a href='/minor/index.html#enquiry'>Waiting</a></td>");
}
else
{
pw.println("<td style='text-align:center'><b>Waiting</b></td>");
}
pw.println("</tr>");
}
pw.println("        </table>");
pw.println("    </div>");
pw.println("</body>");
pw.println("");
pw.println("</html>");
pw.println("");
pw.println("");
pw.println("</table>");
pw.println("</body>");
pw.println("");
pw.println("</html>");
}catch(DAOException exception)
{
System.out.println(exception.getMessage());
}catch(IOException ioException)
{
System.out.println(ioException.getMessage());
}
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
doGet(request,response);
}
}
