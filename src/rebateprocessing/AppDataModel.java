/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rebateprocessing;

/**
 *
 * @author achaturvedi
 */
public class AppDataModel {
    private String firstName;
    private String mInitial;
    private String lastName;
    private String addrLine1;
    private String addrLine2;
    private String city;
    private String state;
    private String zipCode;
    private String phone;
    private String email;
    private String dateReceived;
    private boolean poa;
    
    AppDataModel(){
        AppDataModel appDataModel = new AppDataModel();
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setMInitial(String mInitial){
        this.mInitial = mInitial;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setAddrLine1(String addrLine1){
        this.addrLine1 = addrLine1;
    }
    public void setAddrLine2(String addrLine2){
        this.addrLine2 = addrLine2;
    }
    public void setCity(String city){
        this.city = city;
    }
    public void setState(String state){
        this.state = state;
    }
    public void setZipCode(String zipCode){
        this.zipCode = zipCode;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setDateReceived(String dateReceived){
        this.dateReceived = dateReceived;
    }
    public void setPoa(boolean poa){
        this.poa = poa;
    }
}
