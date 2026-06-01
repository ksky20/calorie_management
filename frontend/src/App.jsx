import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import { Login } from "./components/Login";
import { RegistUser } from "./components/RegistUser";
import { UserProfile } from "./components/UserProfile";
import { EditProfile } from "./components/EditProfile";
import { Mypage } from "./components/Mypage";
import { PastFoodList } from "./components/PastFoodList";

export function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Navigate to="/login" />} />
        <Route path="/login" element={<Login />} />
        <Route path="/regist-user" element={<RegistUser />} />
        <Route path="/user-profile" element={<UserProfile />} />
        <Route path="/edit-profile" element={<EditProfile />}/>
        <Route path="/mypage" element={<Mypage />} />
        <Route path="/past-list" element={<PastFoodList />}/>
      </Routes>
    </BrowserRouter>
  );
}