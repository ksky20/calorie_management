import { useNavigate } from "react-router-dom";
import { api } from "../api/api";
import { useForm } from "react-hook-form";


export function Login() {
  const { register, handleSubmit, formState: { errors } } = useForm();
  
  const navigate = useNavigate();

  //ログイン
  const login = async (form) => {
    const formData = new URLSearchParams();
    formData.append("username", form.username);
    formData.append("password", form.password);

    const res = await fetch("http://localhost:8080/login", {
      method: "POST",
      credentials:"include",
      body: formData,
    });

    if(res.ok) {
      const profileRes = await api("/profile-check");
      const isProfile = await profileRes.json();

      if(isProfile) {
        navigate("/mypage");
      } else {
        navigate("/user-profile");
      }
    } else {
      alert("ログイン失敗");
    }
  }

  return (
    <>
      <div className="container">
        <h1>カロリー管理アプリ</h1>
        <h2>~理想の摂取カロリーを計算して、毎日の食事を管理~</h2>
        <p className="title">ログイン画面</p>
        <form onSubmit={handleSubmit(login)} >
          
          <label htmlFor="username">ユーザ名: </label>
          <input type="text" name="username" id="username" 
            {...register("username", {
              required: "入力してください",
              minLength: {
                  value: 4,
                  message: "4文字以上で入力してください"
              },
              maxLength: {
                  value: 20,
                  message: "20文字以下で入力してください"
              }
            })} />
          <div className="error">{errors.username?.message}</div>
          <label htmlFor="password">パスワード: </label>
          <input type="password" name="password" id="password" 
            {...register("password", {
              required: "入力してください",
              minLength: {
                value: 4,
                message: "4文字以上で入力してください"
              },
              maxLength: {
                value: 20,
                message: "20文字以下で入力してください"
            }
          })} />
          <div className="error">{errors.password?.message}</div>
          
          <button type="submit">ログイン</button>
        </form>
      </div>
      <br />
      <div className="container">
        <p className="title">未登録の場合はこちら</p>
        <button type="button" onClick={() => navigate("/regist-user")}>新規登録</button>
      </div>
    </>
  )
}