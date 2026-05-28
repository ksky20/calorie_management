import { useState, useEffect } from "react"
import { api } from "../api/api";
import { useNavigate } from "react-router-dom";
import { useForm } from "react-hook-form";

export function Mypage() {
  const { register, handleSubmit, formState: { errors } } = useForm();
  const [foodList, setFoodList] = useState([]);
  const [userProfile, setUserProfile] = useState({
    age: "",
    gender: "",
    height: "",
    weight: "",
    activity: "",
    idealCalories: "",
  });
  const navigate = useNavigate();

  //ログアウト
  const logout = async () => {
    const res = await api("/logout", "POST");
    if(res.ok) {
      navigate("/login");
    }
  }

  //画面表示
  useEffect (() => {

    //foodList表示
    const fetchFoodList = async () => {
      const res = await api("/show-food-list", "GET");
      const data = await res.json();
      console.log(Array.isArray(data)); 
      console.log(data);
      setFoodList(data);
    };

    //userProfile表示
    const fetchProfile = async () => {
      const res = await api("/show-user-profile", "GET");
      const data = await res.json();
      console.log(data);
      setUserProfile(data);
    };

    fetchFoodList();
    fetchProfile();
  },[]);

  //食べたものとカロリーを追加
  const registFood = async (form) => {
    try {
      const res = await api("/add-food", "POST", {
       foodDate: form.foodDate,
       foodName: form.foodName,
       foodCalorie: form.foodCalorie,
      });

      if (!res.ok) {
        throw new Error("追加に失敗しました");
      }

      const listRes = await api("/show-food-list", "GET");
      const data = await listRes.json();
      setFoodList(data);
      alert("追加しました");
      console.log("追加しました:", data);
    
    } catch (error) {
      console.error("エラー:", error);
      alert("追加できませんでした");
    }
  }

  //日常の活動量
  const activityLevel = {
    1: "ほとんど運動しない",
    2: "軽い運動(週1〜3回)",
    3: "中程度の運動(週3〜5回)",
    4: "激しい運動(週6〜7回)",
    5: "非常に激しい運動(日常的に体を動かす職業)",
  };  

  //foodListから削除
  const deleteFood = async (id) => {
    const res = await api(`/delete-food/${id}`, "DELETE");

    if(res.ok) {
      const resList = await api("/show-food-list", "GET");
      const data = await resList.json();
      setFoodList(data);
    } else {
      alert("削除できません");
    }
  }

  return (
    <>
      <button id="logout" type="button" onClick={logout}>ログアウト</button>

      <div className="container">
        <p className="title">食べたもの入力</p>
        <form onSubmit={handleSubmit(registFood)}>
          <div>
            <label htmlFor="foodDate">日付</label>
            <input type="date" id="foodDate" name="foodDate" 
              {...register("foodDate", {
                required: "日付を入力してください"
              })}
            />
          <div className="error">{errors.foodDate?.message}</div>
          </div>
          <div>
            <label htmlFor="foodName">食べたもの</label>
            <input type="text" id="foodName" name="foodName" 
              {...register("foodName", {
                required: "入力してください",
                maxLength: {
                  value: 20,
                  message: "20文字以内で入力してください"
                }
              })}
            />
            <div className="foodName">{errors.foodName?.message}</div>
          </div>
          <div>
            <label htmlFor="foodCalorie">摂取カロリー</label> 
            <input type="number" id="foodCalorie" name="foodCalorie" 
              {...register("foodCalorie", {
                required: "入力してください",
                maxLength: {
                  value: 10000,
                  message: "10000 kcal以下で入力してください"
                }
              })}
            />
            <div className="error">{errors.foodCalorie?.message}</div>
          </div>
          <div>
            <button type="submit">追加</button>
          </div>
        </form>
      </div>
      <br />

      <div className="container">
        <p className="title">プロフィール情報</p>
        <p className="info">年齢: {userProfile.age}</p>
        <p className="info">性別: {userProfile.gender === "male" ? "男性" : "女性"}</p>
        <p className="info">身長: {userProfile.height}cm</p>
        <p className="info">体重: {userProfile.weight}kg</p>
        <p className="info">日常の活動量: {activityLevel[userProfile.activity]}</p>
        <p className="info">一日の理想の摂取カロリー: {userProfile.idealCalories}kcal</p>
        <button type="button" onClick={() => navigate("/edit-profile")}>プロフィール情報の編集</button>
      </div>
      <br />

      <div className="container">
        <p className="title">今日食べたもの一覧</p>
        <ul>
          {foodList.map(food => (
            <li key={food.id}>
              {food.foodName}: {food.foodCalorie}kcal
              <button type="button" onClick={() => deleteFood(food.id)}>削除</button>
            </li>
          ))}
        </ul>
      </div>
    </>
  )
}