import { api } from '../api/api';
import { useNavigate } from 'react-router-dom';
import { useForm } from 'react-hook-form';

export function UserProfile() {
  const { register, handleSubmit, formState: { errors } } = useForm();

  const navigate = useNavigate();

  //登録処理
  const registProfile = async (form) => {
    try {
      const res = await api("/regist-profile", "POST", {
        age: form.age,
        gender: form.gender,
        height: form.height,
        weight: form.weight,
        activity: form.activity,
      });
  
      if (!res.ok) {
        throw new Error("登録に失敗しました");
      }
  
      const result = await res.json(); // Springからのレスポンスを受け取る
      console.log("登録成功：", result);
      alert("登録が完了しました！");
      navigate("/mypage");
  
    } catch (error) {
      console.error("エラー：", error);
      alert("登録に失敗しました。");
    }
  };

  return (
    <>
      <h2>プロフィール情報入力</h2>

      <form onSubmit={handleSubmit(registProfile)}>
        <div>
          <label htmlFor="age">年齢</label><br />
          <input type="number" id='age' name='age' 
            {...register("age", {
              required: "入力してください",
              minLength: {
                value: 0,
                message: "年齢を入力してください"
              },
              maxLength: {
                value: 150,
                message: "年齢を入力してください"
              }
            })}
          />
          <div className='error'>{errors.age?.message}</div>
        </div>
        <div>
          <label>性別</label><br />
          <label htmlFor="gender">男性</label>
          <input type="radio" id='male' name='gender' value="male" 
            {...register("gender", {
              required: "選択してください"
            })}
          />
          <label htmlFor="gender">女性</label>
          <input type="radio" id='female' name='gender' value="female" 
            {...register("gender", {
              required: "選択してください"
            })}
          /> 
          <div className='error'>{errors.gender?.message}</div>         
        </div>
        <div>
          <label htmlFor="height">身長</label><br />
          <input type="number" id='height' name='height'
            {...register("height", {
              required: "入力してください",
              min: {
                value: 0,
                message: "0以上で入力してください"
              },
              max: {
                  value: 300,
                  message: "300以下で入力してください"
              },
              valueAsNumber: true
            })}
          />
          <div className='error'>{errors.height?.message}</div>
        </div>
        <div>
          <label htmlFor="weight">体重</label><br />
          <input type="number" id='weight' name='weight' 
            {...register("weight", {
              required: "入力してください",
              min: {
                value: 0,
                message: "0以上で入力してください"
              },
              max: {
                  value: 300,
                  message: "300以下で入力してください"
              },
              valueAsNumber: true
            })}
          />
          <div className='error'>{errors.weight?.message}</div>
        </div>
        <div>
          <label htmlFor="activity">日常の活動量</label><br />
          <select name="activity" id="activity" 
            {...register("activity", {
              required: "選択してください",
              valueAsNumber: true
            })}>
            <option value="1">ほとんど運動しない</option>
            <option value="2">軽い運動(週1~2日)</option>
            <option value="3">中程度の運動(週3~5日)</option>
            <option value="4">激しい運動(週6,7日)</option>
            <option value="5">非常に激しい運動(日常的に体を動かす職業)</option>
          </select>
          <div className='error'>{errors.activity?.message}</div>
        </div>

        <div>
          <button type='submit'>登録</button>
        </div>
      </form>
    </>
  )
}


