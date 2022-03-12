package com.project.hr.dl;
import java.sql.*;
import java.util.*;
import java.math.*;
import java.io.*;
import java.text.*;
public class HospitalDAO
{
public void add(HospitalDTO hospital) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from hospital where hospitalName=?");
preparedStatement.setString(1,hospital.getHospitalName());
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Hospital :"+hospital.getHospitalName()+" exists.");
}
String hospitalContact=hospital.getHospitalContact();
String hospitalName=hospital.getHospitalName();
preparedStatement=connection.prepareStatement("select hospitalNumber from hospital where hospitalName=?");
preparedStatement.setString(1,hospitalName);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Hospital Name already exists :"+hospitalName);
}
resultSet.close();
preparedStatement.close();

preparedStatement=connection.prepareStatement("select hospitalNumber from hospital where hospitalContact=?");
preparedStatement.setString(1,hospitalContact);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("This contact already exists :"+hospitalContact);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("insert into hospital(hospitalName,totalBeds,bedsAvailable,totalVentilators,ventilatorsAvailable,totalICU,icuAvailable,hospitalContact) values(?,?,?,?,?,?,?,?);",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,hospital.getHospitalName());
preparedStatement.setInt(2,hospital.getTotalBeds());
preparedStatement.setInt(3,hospital.getBedsAvailable());
preparedStatement.setInt(4,hospital.getTotalVentilators());
preparedStatement.setInt(5,hospital.getVentilatorsAvailable());
preparedStatement.setInt(6,hospital.getTotalICU());
preparedStatement.setInt(7,hospital.getICUAvailable());
preparedStatement.setString(8,hospital.getHospitalContact());
preparedStatement.executeUpdate();
resultSet=preparedStatement.getGeneratedKeys();
resultSet.next();
int hospitalNumber=resultSet.getInt(1);
resultSet.close();
preparedStatement.close();
connection.close();
hospital.setHospitalNumber(hospitalNumber);
}catch(SQLException sql)
{
throw new DAOException(sql.getMessage());
}
}
public List<HospitalDTO> getAll() throws DAOException
{
List<HospitalDTO> hospitals;
hospitals=new LinkedList<>();
try
{
Connection connection=DAOConnection.getConnection();
Statement statement;
statement=connection.createStatement();
ResultSet resultSet;
resultSet=statement.executeQuery("select * from hospital order by hospitalName");
HospitalDTO hospitalDTO;
int hospitalNumber;
String hospitalName;
int totalBeds,bedsAvailable;
int totalVentilators,ventilatorsAvailable;
int totalICU,icuAvailable;
String hospitalContact;
while(resultSet.next())
{
hospitalNumber=resultSet.getInt("hospitalNumber");
hospitalName=resultSet.getString("hospitalName").trim();
totalBeds=resultSet.getInt("totalBeds");
bedsAvailable=resultSet.getInt("bedsAvailable");
totalVentilators=resultSet.getInt("totalVentilators");
ventilatorsAvailable=resultSet.getInt("ventilatorsAvailable");
totalICU=resultSet.getInt("totalICU");
icuAvailable=resultSet.getInt("icuAvailable");
hospitalContact=resultSet.getString("hospitalContact").trim();
hospitalDTO=new HospitalDTO();
hospitalDTO.setHospitalNumber(hospitalNumber);
hospitalDTO.setHospitalName(hospitalName);
hospitalDTO.setTotalBeds(totalBeds);
hospitalDTO.setBedsAvailable(bedsAvailable);
hospitalDTO.setTotalVentilators(totalVentilators);
hospitalDTO.setVentilatorsAvailable(ventilatorsAvailable);
hospitalDTO.setTotalICU(totalICU);
hospitalDTO.setICUAvailable(icuAvailable);
hospitalDTO.setHospitalContact(hospitalContact);
hospitals.add(hospitalDTO);
}
resultSet.close();
statement.close();
connection.close();
}catch(SQLException exception)
{
throw new DAOException(exception.getMessage());
}
return hospitals;
}
public HospitalDTO getByHospitalNumber(int hospitalNumber) throws DAOException
{
try
{
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
throw new DAOException("Invalid hospitalNumber :"+hospitalNumber);
}
String hospitalName;
int totalBeds,bedsAvailable;
int totalVentilators,ventilatorsAvailable;
int totalICU,icuAvailable;
String hospitalContact;
HospitalDTO hospitalDTO=new HospitalDTO();
hospitalDTO.setHospitalNumber(resultSet.getInt("hospitalNumber"));
hospitalDTO.setHospitalName(resultSet.getString("hospitalName").trim());
hospitalDTO.setTotalBeds(resultSet.getInt("totalBeds"));
hospitalDTO.setBedsAvailable(resultSet.getInt("bedsAvailable"));
hospitalDTO.setTotalVentilators(resultSet.getInt("totalVentilators"));
hospitalDTO.setVentilatorsAvailable(resultSet.getInt("ventilatorsAvailable"));
hospitalDTO.setTotalICU(resultSet.getInt("totalICU"));
hospitalDTO.setICUAvailable(resultSet.getInt("icuAvailable"));
hospitalDTO.setHospitalContact(resultSet.getString("hospitalContact").trim());
resultSet.close();
preparedStatement.close();
connection.close();
return hospitalDTO;
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}
public void update(HospitalDTO hospital) throws DAOException
{
try
{
int hospitalNumber=hospital.getHospitalNumber();
String hospitalName=hospital.getHospitalName();
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
throw new DAOException("Invalid hospitalNumber :"+hospitalNumber);
}
resultSet.close();
preparedStatement.close();

String hospitalContact=hospital.getHospitalContact();
preparedStatement=connection.prepareStatement("select hospitalNumber from hospital where hospitalName=? and hospitalNumber<>?");
preparedStatement.setString(1,hospitalName);
preparedStatement.setInt(2,hospitalNumber);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Hospital Name exists :"+hospitalName);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select hospitalNumber from hospital where hospitalContact=? and hospitalNumber<>?");
preparedStatement.setString(1,hospitalContact);
preparedStatement.setInt(2,hospitalNumber);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Hospital Contact exists :"+hospitalContact);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("update hospital set hospitalName=?,totalBeds=?,bedsAvailable=?,totalVentilators=?,ventilatorsAvailable=?,totalICU=?,icuAvailable=?,hospitalContact=? where hospitalContact=?");
preparedStatement.setString(1,hospital.getHospitalName());
preparedStatement.setInt(2,hospital.getTotalBeds());
preparedStatement.setInt(3,hospital.getBedsAvailable());
preparedStatement.setInt(4,hospital.getTotalVentilators());
preparedStatement.setInt(5,hospital.getVentilatorsAvailable());
preparedStatement.setInt(6,hospital.getTotalICU());
preparedStatement.setInt(7,hospital.getICUAvailable());
preparedStatement.setString(8,hospital.getHospitalContact());
preparedStatement.setInt(9,hospitalNumber);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}
public void deleteByHospitalNumber(int hospitalNumber) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select hospitalNumber from hospital where hospitalNumber=?");
preparedStatement.setInt(1,hospitalNumber);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid hospitalNumber :"+hospitalNumber);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select gender from patient where hospital_code=?");
preparedStatement.setInt(1,hospitalNumber);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Cannot delete hospital as it has been alloted to patient");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("delete from hospital where hospitalNumber=?");
preparedStatement.setInt(1,hospitalNumber);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}
public boolean hospitalNumberExists(int hospitalNumber) throws DAOException
{
boolean exists=false;
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select hospitalNumber from hospital where hospitalNumber=?");
preparedStatement.setInt(1,hospitalNumber);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
exists=resultSet.next();
resultSet.close();
preparedStatement.close();
connection.close();
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
return exists;
}
}