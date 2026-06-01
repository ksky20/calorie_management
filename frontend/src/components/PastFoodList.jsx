import { useState } from "react";
import { useForm } from "react-hook-form";
import { api } from "../api/api";
import { useNavigate } from "react-router-dom";

export function PastFoodList() {
  const { register, handleSubmit, formState: { errors } } = useForm();
  const [foodList, setFoodList] = useState([]);
  const [searchedDate, setSearchedDate] = useState("");
  const navigate = useNavigate();

  const searchPastList = async (data) => {
    const res = await api(`/show-past-list?date=${data.date}`, "GET");

    if(res.ok) {
      const body = await res.json();
      setFoodList(body); 
      setSearchedDate(data.date);
    } else {
      alert("検索エラーです");
    }
  }

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
      <div className="container">
        <p className="title">日付の検索</p>
        <form onSubmit={handleSubmit(searchPastList)}>
          <input type="date" {...register("date", { required: "日付を入力してください" })} />
          <div>{errors.date?.message}</div>
          <button type="submit">検索</button>
        </form>

        <ul>
          {searchedDate && <p className="title">{searchedDate}の食べたもの一覧</p>}
          {foodList.map(food => (
            <li key={food.id}>
              {food.foodName}: {food.foodCalorie}kcal
              <button type="button" onClick={() => deleteFood(food.id)}>削除</button>
            </li>
          ))}
        </ul>

        <button type="button" onClick={() => navigate("/mypage")}>戻る</button>
      </div>
    </>
  );
}