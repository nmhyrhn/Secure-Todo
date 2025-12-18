import { useState } from "react";
import API from "../api/api";

export default function TodoForm({ onAdd }) {
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await API.post("/todos", { title, content });
      onAdd(res.data);
      setTitle("");
      setContent("");
    } catch (err) {
      alert("Todo 생성 실패");
    }
  };

  return (
    <form onSubmit={handleSubmit} className="flex flex-col gap-2 mb-4">
      <input value={title} onChange={e => setTitle(e.target.value)} placeholder="제목" className="border p-2"/>
      <input value={content} onChange={e => setContent(e.target.value)} placeholder="내용" className="border p-2"/>
      <button type="submit" className="bg-blue-500 text-white p-2">추가</button>
    </form>
  );
}
