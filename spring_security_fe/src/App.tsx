import { Routes,Route } from "react-router-dom";
import LoginPage from './Components/login.component';
import AdminPage from './Components/admin.component';
import MemberPage from './Components/member.component';
import Dashboard from "./Components/dashboard.component";

function App() {
  return (
    <div className="app">
      <Routes>
        <Route path="/" element={<Dashboard/>}>Dashboard</Route>
        <Route path="/login" element={<LoginPage/>}>Login</Route>
        <Route path="/admin/:username" element={<AdminPage/>} >Admin</Route>
        <Route path="/member/:username" element={<MemberPage/>} >Member</Route>
      </Routes>
    </div>
  );
}

export default App;
