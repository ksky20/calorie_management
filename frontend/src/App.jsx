import { useEffect } from "react";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import { setCsrfToken } from "./api/api";
import { Login } from "./components/Login";
import { RegistUser } from "./components/RegistUser";
import { UserProfile } from "./components/UserProfile";
import { EditProfile } from "./components/EditProfile";
import { Mypage } from "./components/Mypage";
import { PastFoodList } from "./components/PastFoodList";

export function App() {

  //CSRFトークンを取得
  useEffect(() => {
    const fetchCsrfToken = async () => {
        const res = await fetch("http://localhost:8080/csrf-token", {
            credentials: "include"
        });
        const data = await res.json();
        setCsrfToken(data.token); 
    };
    fetchCsrfToken();
  }, []);

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