package com.project.hr.dl;
public class HospitalDTO implements java.io.Serializable,Comparable<HospitalDTO>
{
private int hospitalNumber;
private String hospitalName;
private int totalBeds,bedsAvailable;
private int totalVentilators,ventilatorsAvailable;
private int totalICU,icuAvailable;
private String hospitalContact;
public void setHospitalContact(String hospitalContact)
{
this.hospitalContact=hospitalContact;
}
public String getHospitalContact()
{
return this.hospitalContact;
}
public void setTotalBeds(int totalBeds)
{
this.totalBeds=totalBeds;
}
public int getTotalBeds()
{
return this.totalBeds;
}

public void setTotalVentilators(int totalVentilators)
{
this.totalVentilators=totalVentilators;
}
public int getTotalVentilators()
{
return this.totalVentilators;
}
public void setTotalICU(int totalICU)
{
this.totalICU=totalICU;
}
public int getTotalICU()
{
return this.totalICU;
}

public void setBedsAvailable(int bedsAvailable)
{
this.bedsAvailable=bedsAvailable;
}
public int getBedsAvailable()
{
return this.bedsAvailable;
}

public void setVentilatorsAvailable(int ventilatorsAvailable)
{
this.ventilatorsAvailable=ventilatorsAvailable;
}
public int getVentilatorsAvailable()
{
return this.ventilatorsAvailable;
}

public void setICUAvailable(int icuAvailable)
{
this.icuAvailable=icuAvailable;
}
public int getICUAvailable()
{
return this.icuAvailable;
}



public void setHospitalNumber(int hospitalNumber)
{
this.hospitalNumber=hospitalNumber;
}
public int getHospitalNumber()
{
return this.hospitalNumber;
}
public void setHospitalName(String hospitalName)
{
this.hospitalName=hospitalName;
}
public String getHospitalName()
{
return this.hospitalName;
}
public HospitalDTO()
{
this.hospitalNumber=0;
this.hospitalName="";
this.totalBeds=0;
this.totalVentilators=0;
this.totalICU=0;
this.bedsAvailable=0;
this.ventilatorsAvailable=0;
this.hospitalContact="";
this.icuAvailable=0;
}
public int hashCode()
{
return hospitalNumber;
}
public int compareTo(HospitalDTO other)
{
return this.hospitalName.compareToIgnoreCase(other.hospitalName);
}
public boolean equals(Object object)
{
if(!(object instanceof HospitalDTO)) return false;
HospitalDTO other;
other=(HospitalDTO)object;
return this.hospitalNumber==other.getHospitalNumber();
}

}