package com.project.hr.dl;
import java.sql.*;
import java.util.*;
import java.math.*;
import java.io.*;
public class PatientDTO implements java.io.Serializable,Comparable<PatientDTO>
{
private String patientId;
private String name;
private int hospitalCode;
private String hospital;
private java.util.Date dateOfBooking;
private String gender;
private boolean isVaccinated;
private BigDecimal registrationAmount;
private String disease;
private String aadharCardNumber;
public PatientDTO()
{
this.patientId="";
this.name="";
this.hospitalCode=0;
this.hospital="";
this.dateOfBooking=null;
this.gender="";
this.isVaccinated=false;
this.registrationAmount=null;
this.disease="";
this.aadharCardNumber="";
}
public void setPatientId(java.lang.String patientId)
{
this.patientId=patientId;
}
public java.lang.String getPatientId()
{
return this.patientId;
}
public void setName(java.lang.String name)
{
this.name=name;
}
public java.lang.String getName()
{
return this.name;
}
public void setHospitalCode(int hospitalCode)
{
this.hospitalCode=hospitalCode;
}
public int getHospitalCode()
{
return this.hospitalCode;
}
public void setHospital(String hospital)
{
this.hospital=hospital;
}
public String getHospital()
{
return this.hospital;
}
public void setDateOfBooking(java.util.Date dateOfBooking)
{
this.dateOfBooking=dateOfBooking;
}
public java.util.Date getDateOfBooking()
{
return this.dateOfBooking;
}
public void setGender(java.lang.String gender)
{
this.gender=gender;
}
public java.lang.String getGender()
{
return this.gender;
}
public void setIsVaccinated(boolean isVaccinated)
{
this.isVaccinated=isVaccinated;
}
public boolean getIsVaccinated()
{
return this.isVaccinated;
}
public void setRegistrationAmount(java.math.BigDecimal registrationAmount)
{
this.registrationAmount=registrationAmount;
}
public java.math.BigDecimal getRegistrationAmount()
{
return this.registrationAmount;
}
public void setDisease(java.lang.String disease)
{
this.disease=disease;
}
public java.lang.String getDisease()
{
return this.disease;
}
public void setAadharCardNumber(java.lang.String aadharCardNumber)
{
this.aadharCardNumber=aadharCardNumber;
}
public java.lang.String getAadharCardNumber()
{
return this.aadharCardNumber;
}
public int hashCode()
{
return patientId.hashCode();
}
public int compareTo(PatientDTO other)
{
return this.patientId.compareToIgnoreCase(other.patientId);
}
public boolean equals(Object object)
{
if(!(object instanceof PatientDTO)) return false;
PatientDTO other;
other=(PatientDTO)object;
return this.patientId.equalsIgnoreCase(other.patientId);
}

}