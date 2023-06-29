package com.pms.AdminMicroservice.Service;

import com.pms.AdminMicroservice.Model.AdminDetails;

public interface AdminProfile {
	
	AdminDetails createAdmin (AdminDetails obj);
	
	AdminDetails updateAdmin(AdminDetails obj);
	
	AdminDetails getAdminDetails(String email);

}
