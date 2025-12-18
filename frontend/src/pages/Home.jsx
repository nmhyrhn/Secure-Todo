import { useState, useEffect } from "react";
import API from "../api/api";
import TodoForm from "../components/TodoForm";
import TodoList from "../components/TodoList";
import { useContext } from "react";
import { AuthContext } from "../context/AuthContext";
import { useNavigate } from "react-router-dom";

export default function Home() {
  const [todos, setTodos] = useState([]);
  const { logout } = useContext(AuthContext);
  const navigate = useNavigate();

  const fetchTodos = async () => {
    try {
      const res = await API.get("/todos");
      setTodos(res.data);
    } catch (err) {
      alert("Todo 불러오기 실패");
    }
  };

  useEffect(() => {
    fetchTodos();
  }, []);

  return (
    <div className="w-96 mx-auto mt-10">
      <div className="flex justify-between mb-4">
        <h1 className="text-2xl">My Todos</h1>
        <button onClick={() => { logout(); navigate("/login"); }} className="bg-gray-500 text-white p-2">Logout</button>
      </div>
      <TodoForm onAdd={todo => setTodos([...todos, todo])}/>
      <TodoList todos={todos} setTodos={setTodos}/>
    </div>
  );
}
