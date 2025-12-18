import { useState, useContext } from "react";
import API from "../api/api";
import { AuthContext } from "../context/AuthContext";
import { useNavigate } from "react-router-dom";

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const { login } = useContext(AuthContext);
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await API.post("/auth/login", { email, password });
      login(res.data.token);
      navigate("/"); // 로그인 후 홈 이동
    } catch (err) {
      alert("로그인 실패");
    }
  };

  return (
    <form onSubmit={handleSubmit} className="flex flex-col gap-2 w-80 mx-auto mt-20">
      <input type="email" placeholder="Email" value={email} onChange={e => setEmail(e.target.value)} className="border p-2"/>
      <input type="password" placeholder="Password" value={password} onChange={e => setPassword(e.target.value)} className="border p-2"/>
      <button type="submit" className="bg-blue-500 text-white p-2">Login</button>
    </form>
  );
}
