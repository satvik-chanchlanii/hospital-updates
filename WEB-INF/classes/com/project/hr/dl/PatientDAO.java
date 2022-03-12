package com.project.hr.dl;
import java.sql.*;
import java.util.*;
import java.math.*;
import java.io.*;
import java.text.*;
public class PatientDAO
{
public List<PatientDTO> getAll() throws DAOException
{
List<PatientDTO> patients;
patients=new LinkedList<>();
try
{
Connection connection=DAOConnection.getConnection();
Statement statement;
statement=connection.createStatement();
ResultSet resultSet;
resultSet=statement.executeQuery("select patient.patient_id,patient.name,patient.hospital_code,hospital.hospitalName,patient.gender,patient.is_vaccinated,patient.date_of_booking,patient.registration_amount,patient.disease,patient.aadhar_card_number from patient inner join hospital on patient.hospital_code=hospital.hospitalNumber order by patient.name");
PatientDTO patientDTO;
int patientId;
String name;
int hospitalCode;
String hospital;
java.sql.Date dateOfBooking;
String gender;
boolean isVaccinated;
BigDecimal registrationAmount;
String disease;
String aadharCardNumber;
while(resultSet.next())
{
patientId=resultSet.getInt("patientId");
name=resultSet.getString("name").trim();
hospitalCode=resultSet.getInt("hospital_code");
hospital=resultSet.getString("hospital").trim();
gender=resultSet.getString("gender");
isVaccinated=resultSet.getBoolean("is_vaccinated");
dateOfBooking=resultSet.getDate("date_of_booking");
registrationAmount=resultSet.getBigDecimal("registration_amount");
disease=resultSet.getString("disease").trim();
aadharCardNumber=resultSet.getString("aadhar_card_number").trim();
patientDTO=new PatientDTO();
patientDTO.setPatientId("PATIENT"+patientId);
patientDTO.setName(name);
patientDTO.setHospitalCode(hospitalCode);
patientDTO.setHospital(hospital);
patientDTO.setGender(gender);
patientDTO.setIsVaccinated(isVaccinated);
patientDTO.setDateOfBooking(dateOfBooking);
patientDTO.setRegistrationAmount(registrationAmount);
patientDTO.setDisease(disease);
patientDTO.setAadharCardNumber(aadharCardNumber);
patients.add(patientDTO);
}
resultSet.close();
statement.close();
connection.close();
}catch(SQLException exception)
{
System.out.println(exception);
throw new DAOException(exception.getMessage());
}
return patients;
}
public void add(PatientDTO patient) throws DAOException
{
try
{
String aadharCardNumber=patient.getAadharCardNumber();
String disease=patient.getDisease();
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
ResultSet resultSet;
preparedStatement=connection.prepareStatement("select patient_id from patient where aadhar_card_number=?");
preparedStatement.setString(1,aadharCardNumber);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Aadhar Card Number exists :"+aadharCardNumber);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("insert into patient (name,hospital_code,gender,is_vaccinated,date_of_booking,registration_amount,disease,aadhar_card_number) values(?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,patient.getName());
preparedStatement.setInt(2,patient.getHospitalCode());
preparedStatement.setString(3,patient.getGender());
preparedStatement.setBoolean(4,patient.getIsVaccinated());
java.util.Date dateOfBooking=patient.getDateOfBooking();
java.sql.Date sqlDate=new java.sql.Date(dateOfBooking.getYear(),dateOfBooking.getMonth(),dateOfBooking.getDate());
preparedStatement.setDate(5,sqlDate);
preparedStatement.setBigDecimal(6,patient.getRegistrationAmount());
preparedStatement.setString(7,disease);
preparedStatement.setString(8,aadharCardNumber);
preparedStatement.executeUpdate();
resultSet=preparedStatement.getGeneratedKeys();
resultSet.next();
int patientId=resultSet.getInt(1);
patient.setPatientId("PATIENT"+patientId);
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sql)
{
System.out.println(sql);
throw new DAOException(sql.getMessage());
}
}
public boolean aadharCardNumberExists(String aadharCardNumber) throws DAOException
{
boolean exists=false;
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select gender from patient where aadhar_card_number=?");
preparedStatement.setString(1,aadharCardNumber);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
exists=resultSet.next();
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sql)
{
System.out.println(sql);
throw new DAOException(sql.getMessage());
}
return exists;
}

public List<PatientDTO> getPatientByHospitalNumber(HospitalDTO hospitalDTO) throws DAOException
{
List<PatientDTO> patients;
patients=new LinkedList<>();
try
{
int hospitalNumber=hospitalDTO.getHospitalNumber();
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from hospital where hospitalNumber=?");
preparedStatement.setInt(1,hospitalNumber);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid hospital number :"+hospitalNumber);
}
resultSet.close();
preparedStatement.close();
Statement statement;
statement=connection.createStatement();
resultSet=statement.executeQuery("select patient.patient_id,patient.name,patient.gender,patient.is_vaccinated,patient.date_of_booking,patient.registration_amount,patient.disease,patient.aadhar_card_number from patient,hospital where patient.hospital_code=hospital.hospitalNumber and hospital.hospitalNumber=?");
int hospitalCode;
int patientId;
String name;
java.sql.Date dateOfBooking;
String gender;
boolean isVaccinated;
BigDecimal registrationAmount;
String disease;
String aadharCardNumber;
PatientDTO patientDTO;
while(resultSet.next())
{
patientId=resultSet.getInt("patient_id");
name=resultSet.getString("name").trim();
gender=resultSet.getString("gender");
isVaccinated=resultSet.getBoolean("is_vaccinated");
dateOfBooking=resultSet.getDate("date_of_booking");
registrationAmount=resultSet.getBigDecimal("registration_amount");
disease=resultSet.getString("disease").trim();
aadharCardNumber=resultSet.getString("aadhar_card_number").trim();
patientDTO=new PatientDTO();
patientDTO.setPatientId("PATIENT"+patientId);
patientDTO.setName(name);
patientDTO.setGender(gender);
patientDTO.setIsVaccinated(isVaccinated);
patientDTO.setDateOfBooking(dateOfBooking);
patientDTO.setRegistrationAmount(registrationAmount);
patientDTO.setDisease(disease);
patientDTO.setAadharCardNumber(aadharCardNumber);
patients.add(patientDTO);
}
resultSet.close();
statement.close();
connection.close();
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
return patients;
}


public Set<PatientDTO> getByHospitalCode(int hospitalCode) throws DAOException
{
Set<PatientDTO> patients=new TreeSet<>();
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select hospitalNumber from hospital where hospitalNumber=?");
preparedStatement.setInt(1,hospitalCode);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid hospital code :"+hospitalCode);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select patient.patient_id,patient.name,patient.gender,patient.is_vaccinated,patient.date_of_booking,patient.registration_amount,patient.disease,patient.aadhar_card_number from patient,hospital where patient.hospital_code=hospital.hospitalNumber and patient.hospital_code=?");
//preparedStatement=connection.prepareStatement("select * from patient where hospital_code=?");
preparedStatement.setInt(1,hospitalCode);
resultSet=preparedStatement.executeQuery();
PatientDTO patientDTO;
int patientId;
String name;
java.sql.Date dateOfBooking;
String gender;
boolean isVaccinated;
BigDecimal registrationAmount;
String disease;
String aadharCardNumber;
while(resultSet.next())
{
patientDTO=new PatientDTO();
patientId=resultSet.getInt("patient_id");
name=resultSet.getString("name").trim();
//hospitalCode=resultSet.getInt("hospital_code");
gender=resultSet.getString("gender");
isVaccinated=resultSet.getBoolean("is_vaccinated");
dateOfBooking=resultSet.getDate("date_of_booking");
registrationAmount=resultSet.getBigDecimal("registration_amount");
disease=resultSet.getString("disease").trim();
aadharCardNumber=resultSet.getString("aadhar_card_number").trim();
patientDTO.setPatientId("PATIENT"+patientId);
patientDTO.setName(name);
patientDTO.setHospitalCode(hospitalCode);
patientDTO.setGender(gender);
patientDTO.setIsVaccinated(isVaccinated);
patientDTO.setDateOfBooking(dateOfBooking);
patientDTO.setRegistrationAmount(registrationAmount);
patientDTO.setDisease(disease);
patientDTO.setAadharCardNumber(aadharCardNumber);
patients.add(patientDTO);
}
resultSet.close();
preparedStatement.close();
connection.close();
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
return patients;
}
public PatientDTO getByPatientId(String id) throws DAOException
{
PatientDTO patientDTO=null;
int actualPatientId=0;
try
{
actualPatientId=Integer.parseInt(id.substring(7));
}catch(Exception exception)
{
throw new DAOException("Invalid patient id 3:"+id);
}
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select patient.patient_id,patient.name,patient.hospital_code,patient.gender,patient.is_vaccinated,patient.date_of_booking,patient.registration_amount,patient.disease,patient.aadhar_card_number from patient inner join hospital on patient.hospital_code=hospital.hospitalNumber and patient_id=?");
preparedStatement.setInt(1,actualPatientId);
System.out.println("11");
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid patient id 4:"+id);
}
int patientId;
String name;
int hospitalCode;
//String hospital;
java.sql.Date dateOfBooking;
String gender;
boolean isVaccinated;
BigDecimal registrationAmount;
String disease;
String aadharCardNumber;
System.out.println("11");
patientId=resultSet.getInt("patient_id");
name=resultSet.getString("name").trim();
hospitalCode=resultSet.getInt("hospital_code");
//hospital=resultSet.getString("hospital").trim();
gender=resultSet.getString("gender");
isVaccinated=resultSet.getBoolean("is_vaccinated");
dateOfBooking=resultSet.getDate("date_of_booking");
registrationAmount=resultSet.getBigDecimal("registration_amount");
disease=resultSet.getString("disease").trim();
aadharCardNumber=resultSet.getString("aadhar_card_number").trim();
patientDTO=new PatientDTO();
patientDTO.setPatientId("PATIENT"+patientId);
patientDTO.setName(name);
patientDTO.setHospitalCode(hospitalCode);
//patientDTO.setHospital(hospital);
patientDTO.setGender(gender);
patientDTO.setIsVaccinated(isVaccinated);
patientDTO.setDateOfBooking(dateOfBooking);
patientDTO.setRegistrationAmount(registrationAmount);
patientDTO.setDisease(disease);
patientDTO.setAadharCardNumber(aadharCardNumber);
resultSet.close();
preparedStatement.close();
connection.close();
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
return patientDTO;
}
public void deleteByPatientId(String patientId) throws DAOException
{
int actualPatientId=0;
try
{
actualPatientId=Integer.parseInt(patientId.substring(7));
}catch(Exception exception)
{
throw new DAOException("Invalid patient id 1:"+patientId);
}
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select gender from patient where patient_id=?");
preparedStatement.setInt(1,actualPatientId);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid patient id 2:"+patientId);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("delete from patient where patient_id=?");
preparedStatement.setInt(1,actualPatientId);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}
}