import { useNavigate } from "react-router-dom";
import { api } from "../api/api"
import { useForm } from "react-hook-form";

export function RegistUser () {
  const { register, handleSubmit, formState: { errors } } = useForm();

  const navigate = useNavigate();
 
  //登録処理
  const registUser = async (form) => {
    const res = await api("/regist", "POST", {
      username: form.username,
      password: form.password,
    });

    if(res.ok) {
      navigate("/login");
    } else {
      alert("登録できませんでした");
    }
  };

  return (
    <>
      <div className="container">
        <h2>登録画面</h2>
        <form onSubmit={handleSubmit(registUser)} >
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
          <br />
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
            })}/>
          <div className="error">{errors.password?.message}</div>
          <button type="submit">登録</button><br />
          <button onClick={() => navigate("/login")}>戻る</button>
        </form>
      </div>
    </>
  )
}