import { render, screen ,fireEvent, waitFor} from '@testing-library/react';
import App from './App';
import Login from './Components/Login';
import { isEmail } from 'validator';
import DoctorLogin from './Components/DoctorLogin';
import { MemoryRouter } from 'react-router-dom';
import LandingPageContent from './Components/LandingPageContent';
import DoctorSignup from './Components/DoctorSignup';
import DoctorDashBoard from './Components/DoctorDashBoard';
import ViewDrugs from './Components/ViewDrugs';
import ViewOrders from './Components/ViewOrders';
import PickupOrders from './Components/PickupOrders';
import userEvent from '@testing-library/user-event';
import { click } from '@testing-library/user-event/dist/click';
import AdminLogin from './Components/Admin/AdminLogin';
import AdminDashboard from './Components/Admin/AdminDashboard';
import SideBarAdmin from './Components/Admin/SideBarAdmin';
import SideBarDoctor from './Components/SideBarDoctor';
import AdminProfileUpdate from './Components/Admin/AdminProfileUpdate';
import DoctorProfileUpdate from './Components/DoctorProfileUpdate';
import DrugInventory from './Components/Admin/DrugInventory';
import AdminDrugs from './Components/Admin/AdminDrugs';
import AdminMoney from './Components/Admin/AdminMoney';
import Analytics from './Components/Admin/Analytics';
import ExpiredDrugs from './Components/Admin/ExpiredDrugs';

test('Home page testing', () => {
  render(<LandingPageContent />);
  const linkElement = screen.getByText(/Empowering doctors with seamless medication procurement/i);
  expect(linkElement).toBeInTheDocument();
});


// Doctor componets testing tests
test('Doctor sidebar', () => {
  render(
    <MemoryRouter>
      <SideBarDoctor />
    </MemoryRouter>
  );
  const Dashboard = screen.getByText(/Dashboard/i);
  const Pickup = screen.getByText(/Pickup/i);
  const Orders = screen.getByText(/Orders/i);
  const Drug = screen.getByText(/Drug/i);
 

  expect(Dashboard).toBeInTheDocument();
  expect(Pickup).toBeInTheDocument();
  expect(Orders).toBeInTheDocument();
  expect(Drug).toBeInTheDocument();
  

});

test('Doctor Login page testing',async () => {
  render(
    <MemoryRouter>
      <DoctorLogin />
    </MemoryRouter>
  );
  const linkElement = screen.getByText(/Login/i);
  const button=await screen.findAllByRole("button")
  const showPassword= await screen.findAllByRole("checkbox")
  expect(button).toHaveLength(1);
  expect(showPassword).toHaveLength(1);
  
  expect(linkElement).toBeInTheDocument();
});


test('Doctor Login page components',async () => {
  render(
    <MemoryRouter>
      <DoctorLogin />
    </MemoryRouter>
  );
  const linkElement =await screen.findAllByRole("textbox")
  expect(linkElement).toHaveLength(2)

 
  
});

test("Doctor email type check", () => {
  render(
    <MemoryRouter>
      <DoctorLogin />
    </MemoryRouter>
  );
  
  const emailInputs = screen.getAllByPlaceholderText("Enter email");
  const emailinput = emailInputs[0]; 

  userEvent.type(emailinput,"test")
  expect(emailinput.value).not.toMatch("test@gmail.com")
  expect(emailinput).toHaveAttribute("type", "email");
});

test("Doctor password type check", () => {
  render(
    <MemoryRouter>
      <DoctorLogin />
    </MemoryRouter>
  );
  
  const passwordInputs = screen.getAllByPlaceholderText("Password");
  const passwordInput = passwordInputs[0]; 

  expect(passwordInput).toHaveAttribute("type", "password");
});


test('Email Validation',() => {
  
  const testemail="test.com";
  expect(isEmail(testemail)).not.toBe(true);
});

test('Doctor Login empty parameter',async () => {
  render(
    <MemoryRouter>
      <DoctorLogin/>
    </MemoryRouter>
  );
  
  const submitBtns=await screen.findAllByRole("button");
  const submitBtn=submitBtns[0];
  const emailInputs = screen.getAllByPlaceholderText("Enter email");
  const emailinput = emailInputs[0]; 

  const passwordInputs = screen.getAllByPlaceholderText("Password");
  const passwordinput = passwordInputs[0]; 
  userEvent.type(emailinput,"")
  userEvent.type(passwordinput,"")
  userEvent.click(submitBtn);

  const loginPage = screen.getByText(/Login/i);
  expect(loginPage).toBeInTheDocument();
  

});

test('Doctor Login fail',async () => {
  render(
    <MemoryRouter>
      <DoctorLogin/>
    </MemoryRouter>
  );
  
  const submitBtns=await screen.findAllByRole("button");
  const submitBtn=submitBtns[0];
  const emailInputs = screen.getAllByPlaceholderText("Enter email");
  const emailinput = emailInputs[0]; 

  const passwordInputs = screen.getAllByPlaceholderText("Password");
  const passwordinput = passwordInputs[0]; 
  userEvent.type(emailinput,"test@gmail.com")
  userEvent.type(passwordinput,"1234")
  userEvent.click(submitBtn);

  const loginPage = screen.getByText(/Login/i);

  expect(loginPage).toBeInTheDocument();
  

});

test('Doctor Signup page component check',async () => {
  render(
    <MemoryRouter>
      <DoctorSignup />
    </MemoryRouter>
  );
  const linkElement =await screen.findAllByRole("textbox")
  const button=await screen.findAllByRole("button")
  expect(linkElement).toHaveLength(4)
  expect(button).toHaveLength(1);
  
});

test('Doctor Signup  empty parameter',async () => {
  render(
    <MemoryRouter>
      <DoctorSignup />
    </MemoryRouter>
  );
  const submitBtns=await screen.findAllByRole("button");
  const submitBtn=submitBtns[0];
  const emailInputs = screen.getAllByPlaceholderText("Enter email");
  const emailinput = emailInputs[0]; 

  const passwordInputs = screen.getAllByPlaceholderText("Password");
  const passwordinput = passwordInputs[0]; 
  userEvent.type(emailinput,"")
  userEvent.type(passwordinput,"")
  userEvent.click(submitBtn);

  const SignUpPage = screen.getByText(/Signup/i);
  expect(SignUpPage).toBeInTheDocument();
});


test('Doctor Dashboard', () => {
  render(
    <MemoryRouter>
      <DoctorDashBoard />
    </MemoryRouter>
  );
  const linkElement = screen.getByText(/View Drugs/i);
  expect(linkElement).toBeInTheDocument();
  
});

test('Doctor View Drugs',async () => {
  render(
    <MemoryRouter>
      <ViewDrugs />
    </MemoryRouter>
  );
  const Searchbox =await screen.findAllByRole("textbox")
  expect(Searchbox).toHaveLength(1);


  
});

test('Doctor View Orders',async () => {
  render(
    <MemoryRouter>
      <ViewOrders />
    </MemoryRouter>
  );
  
  const OrderNotFound = screen.getByText(/No data found!/i);
  expect(OrderNotFound).toBeInTheDocument();


  
});

test('Doctor profile update',async () => {
  render(
    <MemoryRouter>
      <DoctorProfileUpdate />
    </MemoryRouter>
  );
  const submitBtns=await screen.findAllByRole("button");
  const inputbox=await screen.findAllByRole("textbox");
  const img=await screen.findAllByRole("img");

  
  expect(submitBtns).toHaveLength(1);
  expect(inputbox).toHaveLength(4);
  expect(img).toHaveLength(1);

});



////////////Admin Component tests

test('Admin Login page testing',async () => {
  render(
    <MemoryRouter>
      <AdminLogin />
    </MemoryRouter>
  );
  const linkElement = screen.getByText(/Login/i);
  const  inputBoxElement =await screen.findAllByRole("textbox")
  const  checkBoxElement =await screen.findAllByRole("checkbox")
  const button=await screen.findAllByRole("button")
  expect(button).toHaveLength(1);
  expect(inputBoxElement).toHaveLength(2);
  expect(checkBoxElement).toHaveLength(1);
  expect(linkElement).toBeInTheDocument();
});

test("Admin email type check", () => {
  render(
    <MemoryRouter>
      <AdminLogin />
    </MemoryRouter>
  );
  
  const emailInputs = screen.getAllByPlaceholderText("Enter email");
  const emailinput = emailInputs[0]; 

  userEvent.type(emailinput,"test")
  expect(emailinput.value).not.toMatch("test@gmail.com")
  expect(emailinput).toHaveAttribute("type", "email");
});

test("Admin password type check", () => {
  render(
    <MemoryRouter>
      <AdminLogin />
    </MemoryRouter>
  );
  
  const passwordInputs = screen.getAllByPlaceholderText("Password");
  const passwordInput = passwordInputs[0]; 

  expect(passwordInput).toHaveAttribute("type", "password");
});

test('Email Validation for admin',() => {
  
  const testemail="admin.com";
  expect(isEmail(testemail)).not.toBe(true);
});


test('Doctor Login empty parameter',async () => {
  render(
    <MemoryRouter>
      <AdminLogin/>
    </MemoryRouter>
  );
  
  const submitBtns=await screen.findAllByRole("button");
  const submitBtn=submitBtns[0];
  const emailInputs = screen.getAllByPlaceholderText("Enter email");
  const emailinput = emailInputs[0]; 

  const passwordInputs = screen.getAllByPlaceholderText("Password");
  const passwordinput = passwordInputs[0]; 
  userEvent.type(emailinput,"")
  userEvent.type(passwordinput,"")
  userEvent.click(submitBtn);

  const loginPage = screen.getByText(/Login/i);
  expect(loginPage).toBeInTheDocument();
  

});

test('Admin Login fail',async () => {
  render(
    <MemoryRouter>
      <AdminLogin/>
    </MemoryRouter>
  );
  
  const submitBtns=await screen.findAllByRole("button");
  const submitBtn=submitBtns[0];
  const emailInputs = screen.getAllByPlaceholderText("Enter email");
  const emailinput = emailInputs[0]; 

  const passwordInputs = screen.getAllByPlaceholderText("Password");
  const passwordinput = passwordInputs[0]; 
  userEvent.type(emailinput,"Admin@gmail.com")
  userEvent.type(passwordinput,"1234")
  userEvent.click(submitBtn);

  const loginPage = screen.getByText(/Login/i);

  expect(loginPage).toBeInTheDocument();
  

});
test('Admin sidebar', () => {
  render(
    <MemoryRouter>
      <SideBarAdmin />
    </MemoryRouter>
  );
  const Dashboard = screen.getByText(/Dashboard/i);
  const Pickup = screen.getByText(/Pickup/i);
  const Orders = screen.getByText(/Orders/i);
  const DrugInventory = screen.getByText(/Drug Inventory/i);
 
  const Analytics = screen.getByText(/Analytics/i);

  expect(Dashboard).toBeInTheDocument();
  expect(Pickup).toBeInTheDocument();
  expect(Orders).toBeInTheDocument();
  expect(DrugInventory).toBeInTheDocument();
  expect(Analytics).toBeInTheDocument();

});

test('Admin Dashboard', () => {
  render(
    <MemoryRouter>
      <AdminDashboard />
    </MemoryRouter>
  );
  const View_Drugs = screen.getByText(/View Drugs/i);
  

  expect(View_Drugs).toBeInTheDocument();

}); 

test('Drug Inventory',async () => {
  render(
    <MemoryRouter>
      <DrugInventory />
    </MemoryRouter>
  );
  const submitBtns=await screen.findAllByRole("button");
  const SerchBar=await screen.findAllByRole("textbox");

 
  expect(submitBtns).toHaveLength(2);
  expect(SerchBar).toHaveLength(1);

}); 


test('Admin profile update',async () => {
  render(
    <MemoryRouter>
      <AdminProfileUpdate />
    </MemoryRouter>
  );
  const submitBtns=await screen.findAllByRole("button");
  const inputbox=await screen.findAllByRole("textbox");
  const img=await screen.findAllByRole("img");

  
  expect(submitBtns).toHaveLength(1);
  expect(inputbox).toHaveLength(3);
  expect(img).toHaveLength(1);

}); 

 




